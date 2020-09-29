package deltix.qsrv.hf.topic.loader;

import deltix.streaming.MessageChannel;
import deltix.timebase.messages.ConstantIdentityKey;
import deltix.qsrv.hf.topic.DirectProtocol;
import deltix.timebase.messages.InstrumentMessage;
import deltix.qsrv.hf.pub.TypeLoader;
import deltix.qsrv.hf.pub.TypeLoaderImpl;
import deltix.qsrv.hf.pub.codec.CodecFactory;
import deltix.qsrv.hf.pub.md.RecordClassDescriptor;
import deltix.util.io.idlestrat.IdleStrategy;
import deltix.util.io.idlestrat.YieldingIdleStrategy;
import deltix.util.memory.MemoryDataOutput;
import io.aeron.Aeron;
import io.aeron.ExclusivePublication;
import io.aeron.Subscription;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.OutputStream;
import java.util.List;

/**
 * @author Alexei Osipov
 */
@ParametersAreNonnullByDefault
public class DirectLoaderFactory {
    private final CodecFactory codecFactory;
    private final TypeLoader typeLoader;

    public DirectLoaderFactory(CodecFactory codecFactory, TypeLoader typeLoader) {
        this.codecFactory = codecFactory;
        this.typeLoader = typeLoader;
    }

    public DirectLoaderFactory(CodecFactory codecFactory) {
        this(codecFactory, TypeLoaderImpl.DEFAULT_INSTANCE);
    }

    public DirectLoaderFactory() {
        this(CodecFactory.newCompiledCachingFactory());
    }

    public MessageChannel<InstrumentMessage> create(Aeron aeron, boolean raw, String publisherChannel, String metadataSubscriberChannel, int dataStreamId, int serverMetadataUpdatesStreamId, List<RecordClassDescriptor> typeList, byte loaderNumber, OutputStream outputStream, List<ConstantIdentityKey> mapping, @Nullable Runnable closeCallback, @Nullable IdleStrategy publicationIdleStrategy) {
        if (publicationIdleStrategy == null) {
            publicationIdleStrategy = new YieldingIdleStrategy();
        }

        ExclusivePublication publication = aeron.addExclusivePublication(publisherChannel, dataStreamId);
        Subscription serverMetadataUpdates = aeron.addSubscription(metadataSubscriberChannel, serverMetadataUpdatesStreamId);

        //recordClassSet.addClasses();

        MessageChannel<MemoryDataOutput> serverPublicationChannel = new MemoryDataOutputStreamChannel(outputStream);
        int firstTempEntityIndex = DirectProtocol.getFirstTempEntryIndex(loaderNumber);
        //int minTempEntityIndex = DirectProtocol.getMinTempEntryIndex(1);
        //int maxTempEntityIndex = DirectProtocol.getMaxTempEntryIndex(1);
        RecordClassDescriptor[] types = typeList.toArray(new RecordClassDescriptor[0]);
        return new DirectLoaderChannel(publication, codecFactory, raw, typeLoader, firstTempEntityIndex, serverPublicationChannel, serverMetadataUpdates, types, mapping, closeCallback, publicationIdleStrategy);
    }

}
