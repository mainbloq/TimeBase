package deltix.qsrv.hf.topic.consumer;

import deltix.streaming.MessageSource;
import deltix.timebase.messages.InstrumentMessage;
import deltix.qsrv.hf.pub.TypeLoader;
import deltix.qsrv.hf.pub.TypeLoaderImpl;
import deltix.qsrv.hf.pub.codec.CodecFactory;
import deltix.qsrv.hf.pub.md.RecordClassDescriptor;
import deltix.qsrv.hf.tickdb.pub.topic.MessagePoller;
import deltix.qsrv.hf.tickdb.pub.topic.MessageProcessor;
import deltix.util.io.idlestrat.IdleStrategy;
import deltix.util.io.idlestrat.YieldingIdleStrategy;
import io.aeron.Aeron;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * @author Alexei Osipov
 */
@ParametersAreNonnullByDefault
public class DirectReaderFactory {
    private final CodecFactory codecFactory;
    private final TypeLoader typeLoader;

    public DirectReaderFactory() {
        this(CodecFactory.newCompiledCachingFactory());
    }

    public DirectReaderFactory(CodecFactory codecFactory) {
        this(codecFactory, TypeLoaderImpl.DEFAULT_INSTANCE);
    }

    public DirectReaderFactory(CodecFactory codecFactory, TypeLoader typeLoader) {
        this.codecFactory = codecFactory;
        this.typeLoader = typeLoader;
    }

    public SubscriptionWorker createListener(Aeron aeron, boolean raw, String channel, int dataStreamId,
                                             List<RecordClassDescriptor> types, MessageProcessor processor,
                                             @Nullable IdleStrategy idleStrategy, MappingProvider mappingProvider) {
        if (idleStrategy == null) {
            idleStrategy = new YieldingIdleStrategy();
        }
        return new DirectMessageListenerProcessor(processor, aeron, raw, channel, dataStreamId, codecFactory, typeLoader, types, idleStrategy, mappingProvider);
    }

    public MessagePoller createPoller(Aeron aeron, boolean raw, String channel, int dataStreamId,
                                      List<RecordClassDescriptor> types, MappingProvider mappingProvider) {
        return new DirectMessageNonblockingPoller(aeron, raw, channel, dataStreamId, types, codecFactory, typeLoader, mappingProvider);
    }

    public MessageSource<InstrumentMessage> createMessageSource(Aeron aeron, boolean raw, String channel, int dataStreamId,
                                                                List<RecordClassDescriptor> types, @Nullable IdleStrategy idleStrategy, MappingProvider mappingProvider) {
        if (idleStrategy == null) {
            idleStrategy = new YieldingIdleStrategy();
        }
        return new DirectMessageSource(aeron, raw, channel, dataStreamId, codecFactory, typeLoader, types, idleStrategy, mappingProvider);
    }
}
