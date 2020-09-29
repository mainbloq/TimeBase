package deltix.qsrv.hf;

import deltix.qsrv.hf.tickdb.pub.Messages;
import deltix.streaming.MessageChannel;
import deltix.timebase.messages.InstrumentMessage;
import deltix.qsrv.hf.pub.md.RecordClassDescriptor;
import deltix.qsrv.hf.tickdb.pub.topic.MessagePoller;
import deltix.qsrv.hf.tickdb.pub.topic.MessageProcessor;
import deltix.qsrv.hf.topic.consumer.DirectReaderFactory;
import deltix.qsrv.hf.topic.loader.DirectLoaderFactory;
import deltix.timebase.messages.service.ErrorMessage;
import deltix.util.io.idlestrat.YieldingIdleStrategy;
import deltix.util.time.TimeKeeper;
import io.aeron.Aeron;
import io.aeron.CommonContext;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Alexei Osipov
 */
@Ignore // This test does not stop by itself
public class DirectChannelPollerWithSpeedControlBenchmark extends BaseAeronTest {

    @Test
    public void testSpeedControlWithFastAndSlowConsumers() throws Exception {

        String channel = CommonContext.IPC_CHANNEL;
        int dataStreamId = new Random().nextInt();
        int serverMetadataStreamId = dataStreamId + 1;
        List<RecordClassDescriptor> types = Collections.singletonList(Messages.ERROR_MESSAGE_DESCRIPTOR);
        byte loaderNumber = 1;

        Thread loaderThread = new Thread(new MessageSenderRunnable(aeron, channel, dataStreamId, serverMetadataStreamId, types, loaderNumber));
        loaderThread.setName("SENDER");
        loaderThread.start();

        RatePrinter ratePrinter = new RatePrinter("Reader");
        MessagePoller messagePoller = new DirectReaderFactory().createPoller(aeron, false, channel, dataStreamId, types, StubData.getStubMappingProvider());
        ratePrinter.start();
        YieldingIdleStrategy idleStrategy = new YieldingIdleStrategy();

        MessageProcessor mainProcessor = m -> {
            ErrorMessage msg = (ErrorMessage) m;
            if (!msg.getSymbol().equals("ABC")) {
                throw new AssertionError("Wrong symbol");
            }
            if (msg.getSeqNum() != 234567890) {
                throw new AssertionError("Wrong OriginalTimestamp");
            }
            ratePrinter.inc();
            // Do something slow
        };

        MessageProcessor fastProcessor = m -> {
            // This processor just skips messages
        };


        while (true) {
            int workCount;
            if (messagePoller.getBufferFillPercentage() < 70) {
                // Main path
                workCount = messagePoller.processMessages(100, mainProcessor);
            } else {
                // Fast path
                workCount = messagePoller.processMessages(1000, fastProcessor);
            }
            idleStrategy.idle(workCount);
        }
    }

    @Test
    public void testSpeedControlWithDiscardOnSlow() throws Exception {

        String channel = CommonContext.IPC_CHANNEL;
        int dataStreamId = new Random().nextInt();
        int serverMetadataStreamId = dataStreamId + 1;
        List<RecordClassDescriptor> types = Collections.singletonList(Messages.ERROR_MESSAGE_DESCRIPTOR);
        byte loaderNumber = 1;

        Thread loaderThread = new Thread(new MessageSenderRunnable(aeron, channel, dataStreamId, serverMetadataStreamId, types, loaderNumber));
        loaderThread.setName("SENDER");
        loaderThread.start();

        RatePrinter ratePrinter = new RatePrinter("Reader");
        MessagePoller messagePoller = new DirectReaderFactory().createPoller(aeron, false, channel, dataStreamId, types, StubData.getStubMappingProvider());
        ratePrinter.start();
        YieldingIdleStrategy idleStrategy = new YieldingIdleStrategy();

        MessageProcessor messageProcessor = m -> {
            if (messagePoller.getBufferFillPercentage() < 70) {
                // Do something slow
                ErrorMessage msg = (ErrorMessage) m;
                if (!msg.getSymbol().equals("ABC")) {
                    throw new AssertionError("Wrong symbol");
                }
                if (msg.getSeqNum() != 234567890) {
                    throw new AssertionError("Wrong OriginalTimestamp");
                }
                ratePrinter.inc();
            } else {
                // Do something fast
                // Just skip message
            }
        };


        while (true) {
            idleStrategy.idle(messagePoller.processMessages(1000, messageProcessor));
        }
    }

    private static class MessageSenderRunnable implements Runnable {
        private final Aeron aeron;
        private final String channel;
        private final int dataStreamId;
        private final int serverMetadataStreamId;
        private final List<RecordClassDescriptor> types;
        private final byte loaderNumber;

        MessageSenderRunnable(Aeron aeron, String channel, int dataStreamId, int serverMetadataStreamId, List<RecordClassDescriptor> types, byte loaderNumber) {
            this.aeron = aeron;
            this.channel = channel;
            this.dataStreamId = dataStreamId;
            this.serverMetadataStreamId = serverMetadataStreamId;
            this.types = types;
            this.loaderNumber = loaderNumber;
        }

        @Override
        public void run() {

            MessageChannel<InstrumentMessage> channel2 = new DirectLoaderFactory().create(aeron, false, channel, channel, dataStreamId, serverMetadataStreamId, types, loaderNumber, new ByteArrayOutputStream(8 * 1024), Collections.emptyList(), null, null);

            ErrorMessage msg = new ErrorMessage();
            msg.setSymbol("ABC");
            msg.setSeqNum(234567890);

            while (true) {
                msg.setTimeStampMs(TimeKeeper.currentTime);
                channel2.send(msg);
            }
        }
    }
}