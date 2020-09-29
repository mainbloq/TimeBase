package deltix.qsrv.hf.tickdb.lang.runtime.msgsrcs;

import deltix.qsrv.hf.blocks.InstrumentIndex;
import deltix.qsrv.hf.pub.*;
import deltix.qsrv.hf.pub.codec.*;
import deltix.qsrv.hf.pub.md.*;
import deltix.qsrv.hf.tickdb.pub.TickStream;
import deltix.qsrv.hf.tickdb.pub.query.InstrumentMessageSource;
import deltix.streaming.MessageChannel;
import deltix.timebase.messages.IdentityKey;
import deltix.timebase.messages.InstrumentMessage;
import deltix.util.collections.IndexedArrayList;
import deltix.util.concurrent.UncheckedInterruptedException;
import deltix.util.memory.MemoryDataOutput;
import deltix.util.time.TimeKeeper;
import java.util.concurrent.*;

/**
 *
 */
public class RawMessagePipe {
    private static final RawMessage                         END = 
        new RawMessage ();
    
    private volatile LinkedBlockingQueue <RawMessage>       q =
        new LinkedBlockingQueue <RawMessage> ();
    
    private volatile Runnable                               alnr = null;
    
    private final String                                    streamKey;
    private final Introspector                              ix =
        Introspector.createEmptyMessageIntrospector ();
    
    private final MemoryDataOutput                          mdo = 
        new MemoryDataOutput ();
    
    private final CodecFactory                              codecs =
        CodecFactory.newInterpretingCachingFactory ();
    
    public final MessageChannel<InstrumentMessage> writer =
        new MessageChannel <InstrumentMessage> () {
            public void                 send (InstrumentMessage msg) {
                final RecordClassDescriptor       type;
                
                try {
                    type = ix.introspectRecordClass (msg.getClass ());
                } catch (Introspector.IntrospectionException x) {
                    throw new RuntimeException (x);
                }
                
                final FixedBoundEncoder           encoder = 
                    codecs.createFixedBoundEncoder (TypeLoaderImpl.DEFAULT_INSTANCE, type);
                
                mdo.reset ();
                encoder.encode (msg, mdo);
                
                RawMessage                  rawMessage = new RawMessage (type);
                
                rawMessage.copyBytes (mdo, 0);
                rawMessage.setSymbol(msg.getSymbol().toString ());
                
                sendRaw (rawMessage);
            }
            
            private void                 sendRaw (RawMessage rawMessage) {
                final LinkedBlockingQueue <RawMessage> qq = q;
                
                if (qq != null) {
                    rawMessage.setTimeStampMs(TimeKeeper.currentTime);
                    
                    qq.add (rawMessage);
                    
                    final Runnable  lnr = alnr;
                    
                    if (lnr != null)
                        lnr.run ();
                }
            }

            public void                 close () {
                sendRaw (END);
            }            
        };
    
    public final InstrumentMessageSource                    reader =
        new InstrumentMessageSource () {
            private boolean                             atEnd = false;
            private RawMessage                          msg = null;
            private final InstrumentIndex               instrumentIndex =
                new InstrumentIndex ();

            private final IndexedArrayList <String>     streamKeyIndex =
                new IndexedArrayList <String> ();

            private final IndexedArrayList <RecordClassDescriptor> typeIndex =
                new IndexedArrayList <RecordClassDescriptor> ();
            
            public InstrumentMessage getMessage () {
                if (msg == null)
                    throw new IllegalStateException ();
                
                return (msg);
            }

            public boolean              isAtEnd () {
                return (atEnd);
            }

            public boolean              next () {
                final LinkedBlockingQueue <RawMessage> qq = q;
                
                if (qq == null)
                    throw new IllegalStateException ("closed");
                
                try {
                    msg = qq.take ();
                } catch (InterruptedException x) {
                    throw new UncheckedInterruptedException (x);
                } 
                                
                if (msg == END) {
                    msg = null;
                    atEnd = true;
                    return (false);
                }
                
                return (true);
            }

            public void                 close () {
                q = null;
            }        
            
            public boolean              isClosed () {
                return (q == null);
            }

            @Override
            public void add(IdentityKey[] ids, String[] types) {
                throw new UnsupportedOperationException ();
            }

            @Override
            public void remove(IdentityKey[] ids, String[] types) {
                throw new UnsupportedOperationException ();
            }

            public void                 addEntities (IdentityKey[] ids, int offset, int length) {
                throw new UnsupportedOperationException ();
            }

            public void                 addEntity (IdentityKey id) {
                throw new UnsupportedOperationException ();
            }

            public void                 clearAllEntities () {
                throw new UnsupportedOperationException ();
            }

            public void                 removeEntities (IdentityKey[] ids, int offset, int length) {
                throw new UnsupportedOperationException ();
            }

            public void                 removeEntity (IdentityKey id) {
                throw new UnsupportedOperationException ();
            }

            public void                 subscribeToAllEntities () {        
            }

            public void                 addStream (TickStream... tickStreams) {
                throw new UnsupportedOperationException ();
            }

            public void                 removeAllStreams () {
                throw new UnsupportedOperationException ();
            }

            public void                 removeStream (TickStream... tickStreams) {
                throw new UnsupportedOperationException ();
            }

            public void                 addTypes (String... names) {
                throw new UnsupportedOperationException ();
            }

            public void                 removeTypes (String... names) {
                throw new UnsupportedOperationException ();
            }

            public void                 setTypes (String... names) {
                if (names != null)
                    throw new UnsupportedOperationException ();
            }

            @Override
            public void add(CharSequence[] symbols, String[] types) {
                throw new UnsupportedOperationException ();
            }

            @Override
            public void remove(CharSequence[] symbols, String[] types) {
                throw new UnsupportedOperationException ();
            }

            @Override
            public void subscribeToAllSymbols() {
                throw new UnsupportedOperationException ();
            }

            @Override
            public void clearAllSymbols() {
                throw new UnsupportedOperationException ();
            }

            @Override
            public void addSymbol(CharSequence symbol) {
                throw new UnsupportedOperationException ();
            }

            @Override
            public void addSymbols(CharSequence[] symbols, int offset, int length) {
                throw new UnsupportedOperationException ();
            }

            @Override
            public void removeSymbol(CharSequence symbol) {
                throw new UnsupportedOperationException ();
            }

            @Override
            public void removeSymbols(CharSequence[] symbols, int offset, int length) {
                throw new UnsupportedOperationException ();
            }

            @Override
            public boolean isRealTime() {
                return false;
            }

            @Override
            public boolean realTimeAvailable() {
                return false;
            }

            public void                 subscribeToAllTypes () {
            }

            public void                 reset (long time) {
            }

            public void                 setTimeForNewSubscriptions (long time) {
                throw new UnsupportedOperationException ();
            }

            @Override
            public int                  getCurrentEntityIndex () {
                return (instrumentIndex.getOrAdd (getMessage ()));
            }

            public TickStream           getCurrentStream () {
                return (null);
            }

            public int                  getCurrentStreamIndex () {
                return (-1);
            }

            public String               getCurrentStreamKey () {
                return (streamKey);
            }

            public RecordClassDescriptor getCurrentType () {
                return (msg.type);
            }

            @Override
            public int                  getCurrentTypeIndex () {
                return (typeIndex.getIndexOrAdd (getCurrentType ()));
            }

            public void                 setAvailabilityListener (Runnable alnr) {
                RawMessagePipe.this.alnr = alnr;
            }
        };

    /**
     * Creates a message pipe.
     * 
     * @param streamKey     The stream key that will be returned by the
     *                      reader message source.
     */
    public RawMessagePipe (String streamKey) {
        this.streamKey = streamKey;
    }        
}
