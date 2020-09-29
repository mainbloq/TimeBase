package deltix.qsrv.hf.tickdb.ui.tbshell.virtualplayer;

import deltix.streaming.MessageChannel;
import deltix.timebase.messages.InstrumentMessage;
import deltix.qsrv.hf.pub.TimeMessage;
import deltix.qsrv.hf.tickdb.ui.tbshell.virtualplayer.time.MessageTimeConverter;
import deltix.qsrv.hf.tickdb.ui.tbshell.virtualplayer.time.OriginalMessageTimeConverter;
import deltix.qsrv.hf.tickdb.ui.tbshell.virtualplayer.time.ScaledTimeConverter;
import deltix.util.lang.Disposable;
import deltix.util.time.TimeKeeper;
import net.jcip.annotations.GuardedBy;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents virtual playback.
 * Note: if you want to restart playback you will have to 1) stop this playback and 2) create new playback.
 *
 * Implementation detail: we use synchronization on field virtualTimePeriodicTaskExecutor because of {@link #pausedOnEndTime} method.
 *
 * @author Alexei Osipov
 */
class VirtualPlayerPlayback implements Disposable {
    private final long startFromTimeStamp;
    private final VirtualTimePeriodicTaskExecutor virtualTimePeriodicTaskExecutor;

    @GuardedBy("virtualTimePeriodicTaskExecutor")
    private State state = State.NEW;
    private List<Disposable> disposableResources = new ArrayList<>();

    private final MessageChannel<InstrumentMessage> timeMessageChannel;

    private enum State {
        NEW, // Initial state.
        RUNNING,
        PAUSED,
        STOPPED // Final state
    }

    @SuppressWarnings("unchecked")
    VirtualPlayerPlayback(
            MessageChannel<InstrumentMessage> timeMessageChannel,
            Collection<CopyStreamEntry> streamMapping,
            double speed,
            int virtualTimeSliceDurationMs,
            long startFromTimeStamp,
            Long stopAtTimestamp,
            Long destinationVirtualTimestamp) {

        this.timeMessageChannel = timeMessageChannel;
        this.startFromTimeStamp = startFromTimeStamp;

        MessageTimeConverter timeConverter;
        if (destinationVirtualTimestamp == null) {
            timeConverter = new OriginalMessageTimeConverter();
        } else {
            timeConverter = new ScaledTimeConverter(startFromTimeStamp, destinationVirtualTimestamp, speed);
        }

        ArrayList<VirtualPlayerStreamCopyTask> tasks = new ArrayList<>(streamMapping.size());
        for (CopyStreamEntry entry : streamMapping) {
            tasks.add(new VirtualPlayerStreamCopyTask(entry.getSrc(), entry.getDst(), entry.getConverter(), timeConverter));
            disposableResources.add(entry.getSrc());
            disposableResources.add(entry.getDst());
        }

        VirtualPlaybackTask virtualPlaybackTask = new VirtualPlaybackTask(tasks, this.timeMessageChannel);
        this.virtualTimePeriodicTaskExecutor = new VirtualTimePeriodicTaskExecutor(speed, virtualTimeSliceDurationMs, virtualPlaybackTask, stopAtTimestamp, VirtualPlayerPlayback.this::pausedOnEndTime);
    }

    /**
     * Starts of resumes playback.
     * Can't start if already started or stopped.
     */
    public void start() {
        synchronized (this.virtualTimePeriodicTaskExecutor) {
            if (state == State.NEW) {
                sendPlaybackEvent(PlaybackEventType.STARTED, startFromTimeStamp);
                virtualTimePeriodicTaskExecutor.startFromTimestamp(startFromTimeStamp);
                state = State.RUNNING;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public void resume() {
        synchronized (this.virtualTimePeriodicTaskExecutor) {
            if (state == State.PAUSED) {
                sendPlaybackEvent(PlaybackEventType.RESUMED, virtualTimePeriodicTaskExecutor.getCurrentVirtualTime());
                virtualTimePeriodicTaskExecutor.resume();
                state = State.RUNNING;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    /**
     * Pauses running playback.
     */
    public void pause() {
        synchronized (this.virtualTimePeriodicTaskExecutor) {
            if (state == State.RUNNING) {
                state = State.PAUSED;
                virtualTimePeriodicTaskExecutor.pause();
                sendPlaybackEvent(PlaybackEventType.PAUSED, virtualTimePeriodicTaskExecutor.getCurrentVirtualTime());
            } else {
                throw new IllegalStateException();
            }
        }
    }

    /**
     * This method is called by executor thread when stream reaches end limit.
     */
    private void pausedOnEndTime() {
        synchronized (this.virtualTimePeriodicTaskExecutor) {
            switch (state) {
                case RUNNING:
                    state = State.PAUSED;
                    sendPlaybackEvent(PlaybackEventType.PAUSED, virtualTimePeriodicTaskExecutor.getCurrentVirtualTime());
                    return;
                case PAUSED:
                case STOPPED:
                    // Do nothing
                    return;
                default:
                    throw new IllegalStateException();
            }
        }
    }

    /**
     * Stops playback and releases resources.
     */
    public void stop() {
        synchronized (this.virtualTimePeriodicTaskExecutor) {
            if (state != State.STOPPED) {
                state = State.STOPPED;
                virtualTimePeriodicTaskExecutor.stop();
                virtualTimePeriodicTaskExecutor.close();
                sendPlaybackEvent(PlaybackEventType.STOPPED, virtualTimePeriodicTaskExecutor.getCurrentVirtualTime());
                disposableResources.forEach(Disposable::close);
                timeMessageChannel.close();
                disposableResources = null;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public void setSpeed(double speed) {
        synchronized (this.virtualTimePeriodicTaskExecutor) {
            boolean wasRunning = state == State.RUNNING;
            if (wasRunning) {
                virtualTimePeriodicTaskExecutor.pause();
            }
            virtualTimePeriodicTaskExecutor.setSpeed(speed);
            PlaybackSpeedChangedEvent msg = new PlaybackSpeedChangedEvent();
            msg.setSpeed(speed);
            sendPlaybackEvent(msg, virtualTimePeriodicTaskExecutor.getCurrentVirtualTime());
            if (wasRunning) {
                virtualTimePeriodicTaskExecutor.resume();
            }
        }
    }

    public void setTimeSliceDuration(int durationMs) {
        synchronized (this.virtualTimePeriodicTaskExecutor) {
            boolean wasRunning = state == State.RUNNING;
            if (wasRunning) {
                virtualTimePeriodicTaskExecutor.pause();
            }
            virtualTimePeriodicTaskExecutor.setTimeSliceDuration(durationMs);
            PlaybackFrequencyChangedEvent msg = new PlaybackFrequencyChangedEvent();
            msg.setFrequency(durationMs);
            sendPlaybackEvent(msg, virtualTimePeriodicTaskExecutor.getCurrentVirtualTime());
            if (wasRunning) {
                virtualTimePeriodicTaskExecutor.resume();
            }
        }
    }

    public void setStopAtVirtualTimestamp(@Nullable Long stopAtVirtualTimestamp) {
        virtualTimePeriodicTaskExecutor.setStopAtVirtualTimestamp(stopAtVirtualTimestamp);
    }

    private void sendPlaybackEvent(PlaybackEventType eventType, long virtualTime) {
        PlaybackEvent msg = new PlaybackEvent();
        msg.setEventType(eventType);
        sendPlaybackEvent(msg, virtualTime);
    }

    private void sendPlaybackEvent(PlaybackEvent msg, long virtualTime) {
        msg.setNanoTime(TimeKeeper.currentTimeNanos);
        msg.setPlaybackTime(virtualTime);
        timeMessageChannel.send(msg);
    }

    @Override
    public void close() {
        stop();
    }

    /**
     * Processes data copying for all threads.
     */
    private static class VirtualPlaybackTask implements VirtualClockTask {
        private final List<VirtualPlayerStreamCopyTask> tasks;
        private final MessageChannel<InstrumentMessage> timeMessageChannel;
        private final TimeMessage timeMessage = new TimeMessage();

        public VirtualPlaybackTask(List<VirtualPlayerStreamCopyTask> tasks, MessageChannel<InstrumentMessage> timeMessageChannel) {
            this.tasks = tasks;
            this.timeMessageChannel = timeMessageChannel;
        }

        @Override
        public void run(long virtualClockTime) {
            for (VirtualPlayerStreamCopyTask task : tasks) {
                task.copyMessagesUpToTime(virtualClockTime);
            }
            sendTimeMessage(virtualClockTime);
        }

        private void sendTimeMessage(long virtualClockTime) {
            timeMessage.setTimeStampMs(virtualClockTime);
            timeMessageChannel.send(timeMessage);
        }
    }

}
