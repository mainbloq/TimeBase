package deltix.qsrv.hf.tickdb.impl.streamqueue.utilityclasses;

import deltix.timebase.messages.TimeStampedMessage;

import java.util.Comparator;

/**
 * @author Alexei Osipov
 */
public final class MessageTimeComparator<T extends TimeStampedMessage> implements Comparator<T> {
    @Override
    public final int compare(T o1, T o2) {
        long time1 = o1.getNanoTime();
        long time2 = o2.getNanoTime();

        // Unfortunately there is no easy way to simplify this. See Long.compare()
        return Long.compare(time1, time2);
    }
}
