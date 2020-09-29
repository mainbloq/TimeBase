package deltix.qsrv.hf.tickdb.impl.topic;

import deltix.qsrv.hf.tickdb.comm.server.aeron.AeronThreadTracker;
import deltix.qsrv.hf.tickdb.comm.server.aeron.DXServerAeronContext;
import deltix.qsrv.hf.tickdb.pub.DXTickDB;
import deltix.qsrv.hf.tickdb.impl.topic.topicregistry.DirectTopicRegistry;
import deltix.util.concurrent.QuickExecutor;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Adds support of {@link deltix.qsrv.hf.tickdb.pub.topic.TopicDB} for embedded TimeBase instances.
 *
 * @author Alexei Osipov
 */
@ParametersAreNonnullByDefault
public class TopicSupportWrapper extends BaseDXTickDBWrapper {
    public static final int STUB_PORT_NUMBER = 100_000;

    private final TickDBTopicDBImpl topicDB;
    private final boolean aeronScopeIsBoundToWrapper;

    private TopicSupportWrapper(DXTickDB delegate, TickDBTopicDBImpl topicDB, boolean aeronScopeIsBoundToWrapper) {
        super(delegate);
        this.aeronScopeIsBoundToWrapper = aeronScopeIsBoundToWrapper;
        if (delegate.isTopicDBSupported()) {
            throw new RuntimeException("Delegate already supports TopicDB. The wrapping is unnecessary");
        }
        this.topicDB = topicDB;
    }

    public static DXTickDB wrap(DXTickDB delegate, DXServerAeronContext aeronContext, DirectTopicRegistry topicRegistry, QuickExecutor executor, AeronThreadTracker aeronThreadTracker) {
        return new TopicSupportWrapper(delegate, new TickDBTopicDBImpl(delegate, aeronContext, topicRegistry, executor, aeronThreadTracker), false);
    }

    /**
     * This is factory method that is used by {@link deltix.qsrv.hf.tickdb.pub.TopicDBFactory}.
     */
    @SuppressWarnings("unused")
    public static DXTickDB wrapStandalone(DXTickDB delegate) {
        DXServerAeronContext aeronContext = DXServerAeronContext.createDefault(STUB_PORT_NUMBER, null, null);
        DirectTopicRegistry topicRegistry = TopicRegistryFactory.initRegistryAtQSHome(aeronContext);
        // TODO: Design a way to use shared QuickExecutor
        QuickExecutor qeForTopics = QuickExecutor.createNewInstance("TopicSupportWrapper-Topics", null);
        if (delegate.isOpen()) {
            aeronContext.start();
        }
        AeronThreadTracker aeronThreadTracker = new AeronThreadTracker();
        return new TopicSupportWrapper(delegate, new TickDBTopicDBImpl(delegate, aeronContext, topicRegistry, qeForTopics, aeronThreadTracker), true);
    }

    @Override
    public DXTickDB getNestedInstance() {
        return delegate;
    }

    @Override
    public TickDBTopicDBImpl getTopicDB() {
        return topicDB;
    }

    @Override
    public boolean isTopicDBSupported() {
        return true;
    }

    @Override
    public void open(boolean readOnly) {
        if (aeronScopeIsBoundToWrapper && !delegate.isOpen()) {
            topicDB.getAeronContext().start();
        }
        super.open(readOnly);
    }

    @Override
    public void close() {
        if (aeronScopeIsBoundToWrapper && delegate.isOpen()) {
            topicDB.getAeronContext().stop();
        }
        super.close();
    }
}
