package deltix.qsrv.hf.tickdb.pub;

import deltix.data.stream.DXChannel;
import deltix.streaming.MessageChannel;
import deltix.streaming.MessageSource;
import deltix.timebase.messages.IdentityKey;
import deltix.timebase.messages.InstrumentMessage;
import deltix.qsrv.hf.pub.md.MetaData;
import deltix.qsrv.hf.pub.md.RecordClassDescriptor;
import deltix.qsrv.hf.tickdb.pub.query.InstrumentMessageSource;
import deltix.qsrv.hf.tickdb.pub.query.Parameter;
import deltix.qsrv.hf.tickdb.pub.topic.ConsumerPreferences;
import deltix.qsrv.hf.tickdb.pub.topic.DirectChannel;
import deltix.qsrv.hf.tickdb.pub.topic.MessagePoller;
import deltix.qsrv.hf.tickdb.pub.topic.MessageProcessor;
import deltix.qsrv.hf.tickdb.pub.topic.TopicDB;
import deltix.qsrv.hf.tickdb.pub.topic.PublisherPreferences;
import deltix.qsrv.hf.tickdb.pub.topic.exception.DuplicateTopicException;
import deltix.qsrv.hf.tickdb.pub.topic.exception.TopicNotFoundException;
import deltix.qsrv.hf.tickdb.pub.topic.settings.TopicSettings;
import deltix.util.io.idlestrat.IdleStrategy;
import deltix.util.lang.Disposable;
import deltix.util.lang.GrowthPolicy;
import deltix.util.parsers.CompilationException;
import deltix.util.parsers.Element;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 *  In-process client with separate open/close lifecycle.
 */
public class DirectTickDBClient implements DXTickDB {
    private volatile Boolean        readOnly = null;
    private final DXTickDB          server;
    private final TopicDB           topicDBWrapper;

    public DirectTickDBClient (DXTickDB delegate) {
        this.server = delegate;
        this.topicDBWrapper = new TopicDBWrapper();
    }

    @Override
    public String                   toString () {
        return getId ();
    }

    @Override
    public void                     close () {
        readOnly = null;
    }

    @Override
    public void                     open (boolean readOnly) {
        this.readOnly = readOnly; 
    }

    protected final void            assertOpen () {
        if (readOnly == null)
            throw new IllegalStateException ("closed");
    }
    
    protected final void            assertWritable () {
        if (!Boolean.FALSE.equals (readOnly))
            throw new IllegalStateException ("closed or read-only");
    }
    
    protected final UnsupportedOperationException USX () {
        return new UnsupportedOperationException ("Not allowed on client");
    }
    
    @Override
    public boolean                  isReadOnly () {
        assertOpen ();
        return readOnly;
    }

    @Override
    public boolean                  isOpen () {
        return readOnly != null;
    }

    @Override
    public String                   getId () {
        return "DTC (" + server.getId () + ")";
    }

    @Override
    public void                     format () {
        throw USX ();
    }

    @Override
    public void                     delete () {
        throw USX ();
   }

//    @Override
//    public TickCursor               select (
//        long                            time,
//        SelectionOptions                options,
//        String []                       types,
//        IdentityKey []                  entities,
//        TickStream ...                  streams
//    )
//    {
//        assertOpen ();
//        return server.select (time, options, types, entities, streams);
//    }

    @Override
    public TickCursor select(long time, SelectionOptions options, String[] types, CharSequence[] symbols, TickStream... streams) {
        assertOpen ();
        return server.select (time, options, types, symbols, streams);
    }

    @Override
    public TickCursor select(long time, SelectionOptions options, String[] types, IdentityKey[] ids, TickStream... streams) {
        assertOpen ();
        return server.select (time, options, types, ids, streams);
    }

    @Override
    public TickCursor select(long time, SelectionOptions options, String[] types, TickStream... streams) {
        assertOpen ();
        return server.select (time, options, types, streams);
    }

    @Override
    public TickCursor select(long time, SelectionOptions options, TickStream... streams) {
        assertOpen ();
        return server.select (time, options, streams);
    }

    //    @Override
//    public long                     getStreamVersion () {
//        assertOpen ();
//        return server.getStreamVersion ();
//    }

    @Override
    public TickCursor               createCursor (
        SelectionOptions                options,
        TickStream ...                  streams
    )
    {
        assertOpen ();
        return server.createCursor (options, streams);
    }

    @Override
    public void                     warmUp () {
        throw USX ();
    }

    @Override
    public void                     trimToSize () {
        assertWritable ();
        server.trimToSize ();
    }

    @Override
    public void                     setGrowthPolicy (GrowthPolicy policy) {
        throw USX ();
    }

    @Override
    public DXTickStream []          listStreams () {
        assertOpen ();
        return server.listStreams ();
    }

    @Override
    public DXTickStream             getStream (String key) {
        assertOpen ();
        return server.getStream (key);
    }

    @Override
    public DXChannel[]              listChannels() {
        assertOpen ();
        return server.listChannels ();
    }

    @Override
    public long                     getSizeOnDisk () {
        assertOpen ();
        return server.getSizeOnDisk ();
    }

    @Override
    public long                     getServerTime () {
        assertOpen ();
        return server.getServerTime ();
    }

    @Override
    public long                     getMetaDataVersion () {
        assertOpen ();
        return server.getMetaDataVersion ();
    }

    @Override
    public MetaData                 getMetaData () {
        assertOpen ();
        return server.getMetaData ();
    }

    @Override
    public File []                  getDbDirs () {
        throw USX ();
    }

    @Override
    public InstrumentMessageSource  executeQuery (
        Element                         qql,
        SelectionOptions                options,
        TickStream []                   streams,
        CharSequence []                 ids,
        long                            time, 
        Parameter ...                   params
    )
        throws CompilationException 
    {
        assertOpen ();
        return server.executeQuery (qql, options, streams, ids, time, params);
    }

    @Override
    public InstrumentMessageSource  executeQuery (
        String qql,
        SelectionOptions options,
        TickStream[] streams,
        CharSequence[] ids,
        long time, 
        Parameter... params
    ) 
        throws CompilationException 
    {
        assertOpen ();
        return server.executeQuery (qql, options, streams, ids, time, params);
    }

    @Override
    public InstrumentMessageSource  executeQuery (
        String                          qql,
        Parameter ...                   params
    )
        throws CompilationException 
    {
        assertOpen ();
        return (executeQuery (qql, null, null, params));
    }

    @Override
    public InstrumentMessageSource  executeQuery (
        String                          qql,
        SelectionOptions                options,
        Parameter ...                   params
    )
        throws CompilationException 
    {
        assertOpen ();
        return (executeQuery (qql, options, null, params));
    }

    @Override
    public InstrumentMessageSource  executeQuery (
        String qql,
        SelectionOptions options,
        CharSequence[] ids,
        Parameter ... params
    )
        throws CompilationException 
    {
        assertOpen ();
        return server.executeQuery (qql, options, ids, params);
    }

    @Override
    public DXTickStream             createStream (
        String                          key, 
        String                          name,
        String                          description, 
        int                             distributionFactor
    )
    {
        assertWritable ();
        return server.createStream (key, name, description, distributionFactor);
    }

    @Override
    public DXTickStream             createStream (String key, StreamOptions options) {
        assertWritable ();
        return server.createStream (key, options);
    }

    @Override
    public DXTickStream             createFileStream (String key, String dataFile) {
        assertWritable ();
        return server.createFileStream (key, dataFile);
    }

    @Override
    @Deprecated
    public DXTickStream             createAnonymousStream (StreamOptions options) {
        assertWritable ();
        return server.createAnonymousStream (options);
    }

    @Override
    public void                     coolDown () {
        throw USX ();
    }

    @Override
    public TopicDB getTopicDB() {
        assertOpen();
        return topicDBWrapper;
    }

    @Override
    public boolean isTopicDBSupported() {
        return server.isTopicDBSupported();
    }

    @ParametersAreNonnullByDefault
    private class TopicDBWrapper implements TopicDB {
        @Override
        public DirectChannel createTopic(String topicKey, RecordClassDescriptor[] types, @Nullable TopicSettings topicSettings) throws DuplicateTopicException {
            assertWritable();
            return server.getTopicDB().createTopic(topicKey, types, topicSettings);
        }

        @Nullable
        @Override
        public DirectChannel getTopic(String topicKey) {
            assertOpen();
            return server.getTopicDB().getTopic(topicKey);
        }

        @Override
        public void deleteTopic(String topicKey) throws TopicNotFoundException {
            assertWritable();
            server.getTopicDB().deleteTopic(topicKey);
        }

        @Override
        public List<String> listTopics() {
            assertOpen();
            return server.getTopicDB().listTopics();
        }

        @Override
        public RecordClassDescriptor[] getTypes(String topicKey) throws TopicNotFoundException {
            assertOpen();
            return server.getTopicDB().getTypes(topicKey);
        }

        @Override
        public MessageChannel<InstrumentMessage> createPublisher(String topicKey, @Nullable PublisherPreferences channelPreferences, @Nullable IdleStrategy idleStrategy) throws TopicNotFoundException {
            assertWritable();
            return server.getTopicDB().createPublisher(topicKey, channelPreferences, idleStrategy);
        }

        @Override
        public Disposable createConsumerWorker(String topicKey, @Nullable ConsumerPreferences preferences, @Nullable IdleStrategy idleStrategy, @Nullable ThreadFactory threadFactory, MessageProcessor processor) throws TopicNotFoundException {
            assertOpen();
            return server.getTopicDB().createConsumerWorker(topicKey, preferences, idleStrategy, threadFactory, processor);
        }

        @Override
        public MessagePoller createPollingConsumer(String topicKey, @Nullable ConsumerPreferences preferences) throws TopicNotFoundException {
            assertOpen();
            return server.getTopicDB().createPollingConsumer(topicKey, preferences);
        }

        @Override
        public MessageSource<InstrumentMessage> createConsumer(String topicKey, @Nullable ConsumerPreferences preferences, @Nullable IdleStrategy idleStrategy) throws TopicNotFoundException {
            assertOpen();
            return server.getTopicDB().createConsumer(topicKey, preferences, idleStrategy);
        }
    }
}
