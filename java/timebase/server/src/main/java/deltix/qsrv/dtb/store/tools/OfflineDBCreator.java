package deltix.qsrv.dtb.store.tools;

import deltix.qsrv.dtb.fs.pub.*;
import deltix.qsrv.dtb.store.impl.PDSFactory;
import deltix.qsrv.dtb.store.pub.*;
import deltix.qsrv.hf.pub.*;
import deltix.qsrv.hf.pub.codec.*;
import deltix.qsrv.hf.pub.md.*;
import deltix.qsrv.hf.tickdb.impl.TickDBImpl;
import deltix.qsrv.hf.tickdb.pub.*;
import deltix.qsrv.hf.tickdb.pub.query.InstrumentMessageSource;
import deltix.util.collections.*;
import deltix.util.io.*;
import deltix.util.lang.Disposable;
import deltix.util.memory.*;
import java.io.*;

public class OfflineDBCreator implements Disposable {    
    static class RawMessageAdapter implements TSMessageProducer {
        RawMessage          msg;
        
        @Override
        public void         writeBody (MemoryDataOutput out) {
            out.write (msg.data, msg.offset, msg.length);
        }                
    }
    
    public static final int                     NTSFS = 10 << 20;
    
    private final AbstractFileSystem            fs;
    private TSRoot                              root;
    private final PersistentDataStore           cache = PDSFactory.create ();
    
    private CharSequenceToIntegerMap            entityMap = 
        new CharSequenceToIntegerMap ();
        
    private DataWriter                        acc;
    
    private RecordTypeMap<RecordClassDescriptor> typeMap;
    private final RawMessageAdapter             rma = new RawMessageAdapter ();
    
    public OfflineDBCreator (AbstractFileSystem fs) {
        this.fs = fs;
    }
    
    public void         prepare (
        RecordClassDescriptor []        types,
        String                          outPath
    )
    {
        entityMap.clear ();
        
        typeMap = new RecordTypeMap<RecordClassDescriptor> (types);
        
        root = cache.createRoot (null, fs, outPath);
    }
    
    public void         send (RawMessage msg) throws InterruptedException {
        int                 eid = entityMap.get (msg.getSymbol(), -1);
        
        if (eid == -1) {
            eid = entityMap.size ();
            
            entityMap.put (msg.getSymbol(), eid);
        }
        
        if (acc == null) {
            acc = cache.createWriter ();
            
            acc.associate (root);
            
            acc.open (msg.getNanoTime (), null);            
        }
        
        rma.msg = msg;
        
        int         typeCode = typeMap.getCode (msg.type);        
        
        acc.insertMessage (eid, msg.getNanoTime (), typeCode, rma);
    }
    
    @Override
    public void         close () {
        acc.close ();
        root.close ();
    }
    
    public void         copyFromTimeBaseStream (
        String              path,
        String              streamKey,
        String              outPath
    )
        throws InterruptedException 
    {
        try (DXTickDB db = new TickDBImpl(new File(path))) {
            db.open(false);
            DXTickStream    stream = db.getStream (streamKey);
            
            prepare (stream.getPolymorphicDescriptors (), outPath);
            
            try (InstrumentMessageSource cur = stream.select (-1, new SelectionOptions (true, false))) {
                while (cur.next ())
                    send ((RawMessage) cur.getMessage ());
            }
        }
    }
    
    public static void main (String [] args) throws Exception {
        String      path = "o:\\dtb";
        
        IOUtil.removeRecursive (new File (path), null, false);
                
        try (OfflineDBCreator dbc = new OfflineDBCreator (FSFactory.getLocalFS())) {
            dbc.copyFromTimeBaseStream ("O:\\qshomes\\QSH_SandBox\\tickdb", "ticks", path);
        }
    }
}
