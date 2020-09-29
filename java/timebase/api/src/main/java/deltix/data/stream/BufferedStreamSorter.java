package deltix.data.stream;

import deltix.streaming.MessageChannel;
import deltix.timebase.messages.TimeStamp;
import deltix.timebase.messages.TimeStampedMessage;

import java.util.PriorityQueue;

/**
 *  <p>This class enables the sorting of messages in situations when
 *  an upper bound can be placed on how badly unsorted the messages are.
 *  This class will successfully sort a "slightly unordered" stream of messages,
 *  as long as no message is older than any preceding message by more than
 *  a specified time interval, called <i>buffer width</i>.</p>
 */
public class BufferedStreamSorter <T extends TimeStampedMessage>
    implements MessageChannel<T>
{
    private final MessageChannel <T>    delegate;
    private final long                  width;
    private final PriorityQueue <T>     queue;
    private long                        maxTicks = Long.MIN_VALUE;
    private long                        maxViolation = 0;

    /**
     *  Creates a message sorter.
     * 
     *  @param delegate     The channel that will receive sorted messages.
     *  @param width        Buffer width in milliseconds.
     *  @param capacity     Initial buffer capacity in messages.
     */
    public BufferedStreamSorter (
        MessageChannel <T>              delegate,
        long                            width,
        int                             capacity
    )
    {
        this.delegate = delegate;
        this.width = width * TimeStamp.NANOS_PER_MS;
        this.queue = new PriorityQueue <T> (capacity, TimeMsgComparator.INSTANCE);
    }

    /**
     *  Creates a message sorter with a small starting capacity.
     *
     *  @param delegate     The channel that will receive sorted messages.
     *  @param width        Buffer width BufferedStreamSorter in milliseconds.
     */
    public BufferedStreamSorter (
        MessageChannel <T>              delegate,
        long                            width
    )
    {
        this (delegate, width, 64);
    }

    /**
     *  This method takes ownership of the supplied object. Therefore, the
     *  supplied message object may not be reused by the caller.
     * 
     *  @param msg      A message to buffer and eventually send down the
     *                  delegate channel.
     * 
     *  @throws IllegalArgumentException
     *                  When the new message is older than last flushed message,
     *                  which can happen if the set queue width is too small.
     */
    public void                 send (T msg) {
        final long              ticks = msg.getNanoTime();

        if (ticks > maxTicks) {
            //  Flush messages that are too old
            maxTicks = ticks;

            //width is already in nanos
            final long          limit = ticks - width;

            for (;;) {
                final T         top = queue.peek ();

                if (top == null)
                    break;

                final long      topTS = top.getNanoTime();

                if (topTS >= limit)
                    break;
                
                final T         check = queue.poll ();

                if (check != top)
                    throw new RuntimeException ("peek != poll");

                delegate.send (top);
            }
        }
        else {
            final long          violation = maxTicks - ticks;

            if (violation > width) {
                throw new IllegalArgumentException (
                    "Message " + msg +
                    " is older than latest preceding (@" + maxTicks +
                    ") by violation " + violation + " more than allowed queue width " + width
                );
            }

            if (violation > maxViolation)
                maxViolation = violation;
        }        

        queue.offer (msg);
    }

    public MessageChannel <T>   getDelegate () {
        return (delegate);
    }

    /*
        Returns max time seen in ticks
     */
    public long                 getMaxTime () {
        return (maxTicks);
    }

    /*
        Returns max violation in ticks
    */
    public long                 getMaxViolation () {
        return (maxViolation);
    }

    public long                 getWidth () {
        return (width);
    }
    
    public int                  getQueueSize () {
        return (queue.size ());
    }

    public void                 flush () {
        for (;;) {
            T       msg = queue.poll ();

            if (msg == null)
                break;

            delegate.send (msg);
        }
    }

    public void             close () {
        flush ();
        delegate.close ();
    }
}
