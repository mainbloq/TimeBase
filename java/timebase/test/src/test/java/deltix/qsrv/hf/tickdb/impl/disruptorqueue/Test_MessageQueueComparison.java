package deltix.qsrv.hf.tickdb.impl.disruptorqueue;

import deltix.qsrv.hf.pub.*;
import deltix.qsrv.hf.tickdb.TDBTestBase;
import deltix.qsrv.hf.tickdb.Test_MemoryExchangeThroughput;
import deltix.qsrv.hf.tickdb.impl.TickStreamImpl;
import deltix.qsrv.hf.tickdb.pub.*;
import deltix.timebase.messages.ConstantIdentityKey;
import deltix.timebase.messages.InstrumentMessage;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Helps tp compare performance of {@link deltix.qsrv.hf.tickdb.impl.disruptorqueue.DisruptorMessageQueue} and {@link deltix.qsrv.hf.tickdb.impl.MessageQueue}.
 */
// TODO: Move to timebase-server module
public class Test_MessageQueueComparison extends TDBTestBase {
    private static final int QUEUE_SIZE = 64 * 1024;//16*1024;
    public static final boolean IS_REMOTE = true;

    public Test_MessageQueueComparison() {
        super(IS_REMOTE);
    }

    // LOSSY Old

    @Test
    public void testAnonymousStreamOldP1C1() throws InterruptedException {
        testAnonymousStream(false, false, 1, 1);
    }

    @Test
    public void testAnonymousStreamOldP1C2() throws InterruptedException {
        testAnonymousStream(false, false, 1, 2);
    }

    @Test
    public void testAnonymousStreamOldP1C4() throws InterruptedException {
        testAnonymousStream(false, false, 1, 4);
    }

    @Test
    public void testAnonymousStreamOldP1C8() throws InterruptedException {
        testAnonymousStream(false, false, 1, 8);
    }

    @Test
    public void testAnonymousStreamOldP1C16() throws InterruptedException {
        testAnonymousStream(false, false, 1, 16);
    }

    // LOSSY Dis

    @Test
    public void testAnonymousStreamDisruptorP1C1() throws InterruptedException {
        testAnonymousStream(false, true, 1, 1);
    }

    @Test
    public void testAnonymousStreamDisruptorP1C2() throws InterruptedException {
        testAnonymousStream(false, true, 1, 2);
    }

    @Test
    public void testAnonymousStreamDisruptorP1C4() throws InterruptedException {
        testAnonymousStream(false, true, 1, 4);
    }

    @Test
    public void testAnonymousStreamDisruptorP1C8() throws InterruptedException {
        testAnonymousStream(false, true, 1, 8);
    }

    @Test
    public void testAnonymousStreamDisruptorP1C16() throws InterruptedException {
        testAnonymousStream(false, true, 1, 16);
    }

    // LossLESS Old

    @Test
    public void testAnonymousStreamOldP1C1Lossless() throws InterruptedException {
        testAnonymousStream(true, false, 1, 1);
    }

    @Test
    public void testAnonymousStreamOldP1C2Lossless() throws InterruptedException {
        testAnonymousStream(true, false, 1, 2);
    }

    @Test
    public void testAnonymousStreamOldP1C4Lossless() throws InterruptedException {
        testAnonymousStream(true, false, 1, 4);
    }

    @Test
    public void testAnonymousStreamOldP1C8Lossless() throws InterruptedException {
        testAnonymousStream(true, false, 1, 8);
    }

    @Test
    public void testAnonymousStreamOldP1C16Lossless() throws InterruptedException {
        testAnonymousStream(true, false, 1, 16);
    }

    // LossLESS Dist

    @Test
    public void testAnonymousStreamDisP1C1Lossless() throws InterruptedException {
        testAnonymousStream(true, true, 1, 1);
    }

    @Test
    public void testAnonymousStreamDisP1C2Lossless() throws InterruptedException {
        testAnonymousStream(true, true, 1, 2);
    }

    @Test
    public void testAnonymousStreamDisP1C4Lossless() throws InterruptedException {
        testAnonymousStream(true, true, 1, 4);
    }

    @Test
    public void testAnonymousStreamDisP1C8Lossless() throws InterruptedException {
        testAnonymousStream(true, true, 1, 8);
    }

    @Test
    public void testAnonymousStreamDisP1C16Lossless() throws InterruptedException {
        testAnonymousStream(true, true, 1, 16);
    }

    private void testAnonymousStream(boolean lossless, boolean disruptor, int producerCount, int consumerCount) throws InterruptedException {
        StreamOptions options = new StreamOptions();
        options.name = "test";
        options.scope = StreamScope.RUNTIME;
        options.bufferOptions = new BufferOptions();
        options.bufferOptions.initialBufferSize = options.bufferOptions.maxBufferSize = QUEUE_SIZE;
        options.bufferOptions.lossless = lossless;
        options.setFixedType(Messages.BINARY_MESSAGE_DESCRIPTOR);

        System.setProperty(TickStreamImpl.USE_DISRUPTOR_QUEUE_PROPERTY_NAME, Boolean.toString(disruptor));
        DXTickStream stream = getTickDb().createAnonymousStream(options);


        final LoadingOptions lo = new LoadingOptions(true);
        final SelectionOptions so = new SelectionOptions(true, true);

        class MessageProducer implements Runnable, MessageCounter {
            private final AtomicLong publicMessageCounter = new AtomicLong();
            private final TickLoader loader;
            private final int number;

            public MessageProducer(DXTickStream stream, int number) {
                loader = stream.createLoader(lo);
                this.number = number;
            }

            @Override
            public void run() {
                final InstrumentMessage msg = Test_MemoryExchangeThroughput.createRawMessage("MSFT");
                Thread.currentThread().setName("PRODUCER-" + number);
                long messageCounter = 0;
                while (!Thread.currentThread().isInterrupted()) {
                    loader.send(msg); // loader will do memcopy() of the message payload
                    messageCounter++;
                    publicMessageCounter.lazySet(messageCounter);
                }
            }

            @Override
            public long getMessageCount() {
                return publicMessageCounter.get();
            }
        }

        class MessageConsumer implements Runnable, MessageCounter {
            private final AtomicLong publicMessageCounter = new AtomicLong();
            private final TickCursor cursor;
            private final int number;

            public MessageConsumer(DXTickStream stream, int number) {
                cursor = stream.createCursor(so);
                this.number = number;
                cursor.addEntity(new ConstantIdentityKey("MSFT"));
            }

            @Override
            public void run() {
                Thread.currentThread().setName("CONSUMER-" + number);
                long messageCounter = 0;
                while (!Thread.currentThread().isInterrupted()) {
                    cursor.next();
                    RawMessage received = (RawMessage) cursor.getMessage();
                    processPayload(received.data, 0);
                    messageCounter++;
                    publicMessageCounter.lazySet(messageCounter);
                }
            }

            @Override
            public long getMessageCount() {
                return publicMessageCounter.get();
            }
        }

        List<MessageConsumer> consumers = new ArrayList<>();
        for (int i = 1; i <= consumerCount; i++) {
            consumers.add(new MessageConsumer(stream, i));
        }

        List<MessageProducer> producers = new ArrayList<>();
        for (int i = 1; i <= producerCount; i++) {
            producers.add(new MessageProducer(stream, i));
        }

        ExecutorService executor = Executors.newCachedThreadPool();
        for (MessageConsumer consumer : consumers) {
            executor.execute(consumer);
        }

        for (MessageProducer producer : producers) {
            executor.execute(producer);
        }

        measureThroughput(producers, consumers, (lossless ? "LossLESS-" : "LOSSY-") + (disruptor ? "Dis" : "Old") + "P" + producerCount + "C" + consumerCount, executor);
    }

    public static void processPayload(byte[] event, int offset) {
        if (event == null || event[offset] == 0) {
            System.err.println("Never!");
            System.exit(-1);
        }
    }

    private static void measureThroughput(List<? extends MessageCounter> producers, List<? extends MessageCounter> consumers, String testName, ExecutorService executor) throws InterruptedException {
        long lastSeenMessageCount = 0;
        long lastTime = System.currentTimeMillis();
        DecimalFormat df = new DecimalFormat("##,###,###,###");
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(5000);
            long messageCount = 0;
            for (MessageCounter consumer : consumers) {
                messageCount += consumer.getMessageCount();
            }
            final long now = System.currentTimeMillis();
            long throughput = (messageCount - lastSeenMessageCount) / ((now - lastTime) / 1000);

            System.out.println("Test " + testName + " processed " + df.format(throughput) + " messages/sec");
            lastSeenMessageCount = messageCount;
            lastTime = now;
        }
        executor.shutdownNow();
        if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
            throw new AssertionError("Failed to terminate workers");
        }

    }

    public interface MessageCounter {
        long getMessageCount();
    }


    //@Test
    public void testNanoTome() throws InterruptedException {
        long t0 = System.nanoTime();
        for (int i = 0; i < 1000000000; i++) {
            System.nanoTime();
        }
        long t1 = System.nanoTime();
        System.out.println(TimeUnit.NANOSECONDS.toSeconds(t1 - t0));
    }
}

