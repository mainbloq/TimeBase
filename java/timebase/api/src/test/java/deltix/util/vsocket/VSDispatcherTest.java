package deltix.util.vsocket;

import deltix.util.ContextContainer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexei Osipov
 */
public class VSDispatcherTest {

    /**
     * Test for https://gitlab.deltixhub.com/Deltix/QuantServer/QuantServer/issues/43
     */
    @Test (timeout = 5_000) // Note: test timeout must be greater than reconnectInterval + 2000ms
    public void testNoHangsOnConcurrentDisconnects() throws IOException, InterruptedException {
        int reconnectInterval = 2000;

        VSDispatcher dispatcher = new VSDispatcher("TEST_SERVER", false, new ContextContainer());
        dispatcher.setLingerInterval(reconnectInterval);
        dispatcher.setStateListener(new ConnectionStateListener() {
            @Override
            void onDisconnected() {
            }

            @Override
            void onReconnected() {
            }

            @Override
            boolean onTransportStopped(VSocketRecoveryInfo recoveryInfo) {
                return false;
            }

            @Override
            boolean onTransportBroken(VSocketRecoveryInfo recoveryInfo) {
                return true;
            }
        });

        int transportCount = 2;
        int channelCount = 2;

        for (int i = 0; i < transportCount; i++) {
            MemorySocket receiverSocket = new MemorySocket(i + 1);
            MemorySocket senderSocket = new MemorySocket(receiverSocket, i + 1);
            dispatcher.addTransportChannel(senderSocket);
        }

        ArrayList<VSTransportChannel> transports = new ArrayList<>();

        for (int i = 0; i < transportCount; i++) {
            transports.add(dispatcher.checkOut());
        }

        for (VSTransportChannel transport : transports) {
            dispatcher.checkIn(transport);
        }

        CountDownLatch startBarrier = new CountDownLatch(1);
        List<Thread> errorThreads = new ArrayList<>();
        for (int i = 0; i < transportCount; i++) {
            VSTransportChannel transport = transports.get(i);
            int index = i + 1;
            Thread thread = new Thread(() -> {
                Thread.currentThread().setName("Error thread " + index);

                try {
                    startBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }

                try {
                    dispatcher.transportStopped(transport, new Exception("EX" + index));
                } finally {
                    System.out.println("Error thread finished: " + index);
                }

            });
            thread.start();
            errorThreads.add(thread);
        }

        // Put some data into VSOutputStream
        List<VSChannelImpl> channels = new ArrayList<>();
        for (int i = 0; i < channelCount; i++) {
            VSChannelImpl channel = dispatcher.newChannel(1024, 1024, true);
            channel.onRemoteConnected(i, 64*1024, i);
            channels.add(channel);
            byte[] buffer = new byte[1024];
            VSOutputStream out = channel.getOutputStream();
            out.write(buffer, 0, buffer.length);
        }

        assertTrue(dispatcher.hasAvailableTransport());

        System.out.println("Emulating broken transports...");
        startBarrier.countDown();
        Thread.sleep(100); // Let threads get into blocked state

        // Now no transports should be available
        assertFalse(dispatcher.hasAvailableTransport());

        // Emulate Flusher thread
        VSChannelImpl vsChannel = channels.get(0);
        boolean gotChanelClosedException = false;
        try {
            // Note: test may hang here if Dispatcher bug still present
            vsChannel.getOutputStream().flushAvailable();
        } catch (ConnectionAbortedException e) {
            gotChanelClosedException = true;
            System.out.println("Got ConnectionAbortedException as expected");
        }
        Assert.assertTrue(gotChanelClosedException);

        // Waiting for error threads to finish
        for (Thread thread : errorThreads) {
            thread.join();
        }
    }
}