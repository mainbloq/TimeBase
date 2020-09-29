package deltix.qsrv.hf.tickdb;

import deltix.timebase.messages.ConstantIdentityKey;
import deltix.timebase.messages.TimeStampedMessage;;
import deltix.qsrv.hf.pub.*;
import deltix.qsrv.hf.tickdb.pub.*;
import deltix.timebase.messages.IdentityKey;

import deltix.timebase.api.messages.TradeMessage;
import deltix.util.lang.Util;
import java.io.File;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.experimental.categories.Category;
import deltix.util.JUnitCategories.TickDBFast;

@Category(TickDBFast.class)
/** 
 *  Test that we can independently load information into the same stream from
 *  multiple loaders, as long as loaders do not overlap on symbols.
 */
public class Test_MultiLoadersOneStream {
    public static final int         NUM_LOADERS = 100;
    public static final int         NUM_MSGS_PER_SYMBOL = 10000;
    //public static final int         SAVE_INTERVAL = NUM_MSGS_PER_SYMBOL / 10;

    private static final String     STREAM_KEY = "Test Stream";
    private static final String     STREAM_NAME = "Test Name";

    private static final String []  symbols = new String [NUM_LOADERS];

    private File                    dbFile = new File (TDBRunner.getTemporaryLocation());
    private DXTickDB                db;
    private DXTickStream            stream;
    

    static {
        for (int ii = 0; ii < NUM_LOADERS; ii++)
            symbols [ii] = "DLX" + ('A' + ii);  // Deltix' subsidiaries :)
    }

    @Before
    public void         createDB () {
        db = TickDBFactory.create (dbFile);
        
        db.format ();
        
        stream = 
            db.createStream (
                STREAM_KEY, 
                STREAM_NAME, 
                "Test Description",
                0
            );
        
        StreamConfigurationHelper.setTradeNoExchNoCur (stream);
    }

    @After
    public void         removeDB () {
        db.close();
//        if (db != null)
//            db.delete ();
    }

    private deltix.timebase.api.messages.TradeMessage createMessage (String symbol) {
        TradeMessage trade = new TradeMessage();

        trade.setSymbol(symbol);
        
        return (trade);
    }

    private void        doLoad (TickLoader loader, TradeMessage trade, int tag) {
        trade.setSize(tag);
        trade.setPrice(tag);
        trade.setTimeStampMs(TimeStampedMessage.TIMESTAMP_UNKNOWN); //System.currentTimeMillis ();

        loader.send (trade);
    }

    private void        checkLoad () {
        for (String symbol : symbols) {

            try (TickCursor          cur = stream.select (0, null, null,
                    new IdentityKey[] { new ConstantIdentityKey(symbol) }
            ))
            {

                for (int ii = 0; ii < NUM_MSGS_PER_SYMBOL; ii++) {
                    assertTrue(cur.next());

                    deltix.timebase.api.messages.TradeMessage msg = (deltix.timebase.api.messages.TradeMessage) cur.getMessage();

                    assertTrue(Util.equals(symbol, msg.getSymbol()));
                    assertEquals(symbol, ii, (int) msg.getSize());
                    assertEquals(ii, (int) msg.getPrice());
                }

            }
        }
    }
    
    @Test
    public void         syncLoad () {
        TickLoader []       loaders = new TickLoader [NUM_LOADERS];
        deltix.timebase.api.messages.TradeMessage msg = createMessage (null);
        
        for (int ii = 0; ii < NUM_LOADERS; ii++) 
            loaders [ii] = stream.createLoader ();        

        for (int ii = 0; ii < NUM_MSGS_PER_SYMBOL; ii++) {
            for (int n = 0; n < NUM_LOADERS; n++) {
                msg.setSymbol(symbols [n]);
                
                doLoad (loaders [n], msg, ii);
            }
        }        
           
        for (TickLoader loader : loaders)
            loader.close ();

        checkLoad ();
    }

    class LoaderThread extends Thread {
        private final deltix.timebase.api.messages.TradeMessage msg;

        public LoaderThread (String symbol) {
            super ("Loader for " + symbol);
            msg = createMessage (symbol);
        }

        @Override
        public void         run () {
            try (TickLoader loader = stream.createLoader()) {
                for (int ii = 0; ii < NUM_MSGS_PER_SYMBOL; ii++) {
                    doLoad(loader, msg, ii);
                }
            } catch (Throwable ex) {
                ex.printStackTrace(System.out);
            }

        }
    }

    @Test
    public void         asyncLoad () throws InterruptedException {
        LoaderThread []       threads = new LoaderThread [NUM_LOADERS];

        for (int ii = 0; ii < NUM_LOADERS; ii++)
            threads [ii] = new LoaderThread (symbols [ii]);

        for (LoaderThread t : threads)
            t.start ();

        for (LoaderThread t : threads)
            t.join ();

        checkLoad ();
    }    
}
