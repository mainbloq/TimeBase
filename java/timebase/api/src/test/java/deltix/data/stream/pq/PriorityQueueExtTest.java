package deltix.data.stream.pq;

import deltix.data.stream.PriorityQueue;
import deltix.data.stream.pq.utilityclasses.MessageTimeComparator;
import deltix.data.stream.pq.utilityclasses.TimeStampedMessageMessageSource;
import deltix.streaming.MessageSource;
import deltix.timebase.messages.TimeStampedMessage;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * @author Alexei Osipov
 */
public class PriorityQueueExtTest {
    @Test
    public void testCorrectness() throws Exception {
        MessageTimeComparator<TimeStampedMessage> c = new MessageTimeComparator<>();
        PriorityQueue<TimeStampedMessage> pq1 = new PriorityQueue<>(256, true, c);
        PriorityQueueExt<MessageSource<TimeStampedMessage>> pq2 = new PriorityQueueExt<>(256, true);
        Random r1 = new Random(0);
        Random r2 = new Random(0);

        long baseTimestamp = 1000000000000L; // ~2001.09.09

        // Init streams
        int streamsInQueuue = 1000;
        int step = streamsInQueuue;
        for (int i = 0; i < streamsInQueuue; i++) {
            TimeStampedMessageMessageSource s1 = new TimeStampedMessageMessageSource(baseTimestamp, new Random(r1.nextInt()), step, 1);
            s1.next();
            pq1.offer(s1);

            TimeStampedMessageMessageSource s2 = new TimeStampedMessageMessageSource(baseTimestamp, new Random(r2.nextInt()), step, 1);
            s2.next();
            pq2.offer(s2, s2.getMessage().getNanoTime());
        }
        //pq.dump(System.out);
        //pqe.dump(System.out);

        // Perform iterations
        int iterations = 10_000_000;
        for (int i = 0; i < iterations; i++) {
            MessageSource<TimeStampedMessage> s1 = pq1.poll();
            MessageSource<TimeStampedMessage> s2 = pq2.poll();

            long t1old = s1.getMessage().getNanoTime();
            long t2old = s2.getMessage().getNanoTime();
            if (t1old != t2old) {
                // Dump tree content before throwing exception
                pq1.dump(System.out);
                pq2.dump(System.out);
            }
            Assert.assertEquals(t1old, t2old);

            s1.next();
            s2.next();

            pq1.offer(s1);
            pq2.offer(s2, s2.getMessage().getNanoTime());
        }

        // Drain queues
        for (int i = 0; i < streamsInQueuue; i++) {
            Assert.assertFalse(pq1.isEmpty());
            Assert.assertFalse(pq2.isEmpty());

            MessageSource<TimeStampedMessage> s1 = pq1.poll();
            MessageSource<TimeStampedMessage> s2 = pq2.poll();

            long t1old = s1.getMessage().getNanoTime();
            long t2old = s2.getMessage().getNanoTime();
            Assert.assertEquals(t1old, t2old);
        }

        Assert.assertTrue(pq1.isEmpty());
        Assert.assertTrue(pq2.isEmpty());

        Assert.assertNull(pq1.poll());
        Assert.assertNull(pq2.poll());
    }

}