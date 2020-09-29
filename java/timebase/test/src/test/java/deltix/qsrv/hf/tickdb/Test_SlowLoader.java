package deltix.qsrv.hf.tickdb;


import deltix.qsrv.hf.tickdb.comm.client.TickDBClient;
import deltix.qsrv.hf.tickdb.comm.server.TickDBServer;
import deltix.qsrv.hf.tickdb.pub.*;
import deltix.util.lang.Util;

import java.io.File;
import org.junit.*;
import static org.junit.Assert.*;

import org.junit.experimental.categories.Category;
import deltix.util.JUnitCategories.TickDBFast;

/**
 *  Test that we can pause for a significant amount of time while loading
 *  messages.
 */
@Category(TickDBFast.class)
public class Test_SlowLoader {
    public static final int         PAUSE_MILLIS = 1000;
    public static final int         NUM_TRIES = 300;
    public static final int         NUM_PER_BURST = 30;

    private File                    dbFile = new File (TDBRunner.getTemporaryLocation());

    @Test
    public void         go () throws InterruptedException {
        DXTickDB                db = TickDBFactory.create (dbFile);

        db.format ();

        try {
            TickDBServer        server = new TickDBServer (0, db);

            server.start ();

            DXTickDB            conn = new TickDBClient ("localhost", server.getPort ());

            conn.open (false);

            System.out.println ("Connected to " + conn.getId ());

            doLoad (conn);

            conn.close ();
            server.shutdown (true);           
        } finally {
            db.close ();
            db.delete ();           
        }
    }

    private void        doLoad (DXTickDB db) throws InterruptedException {
        DXTickStream        stream =
            db.createStream ("test", null, null, 0);

        StreamConfigurationHelper.setTradeNoExchNoCur (stream);

        deltix.timebase.api.messages.TradeMessage trade = new deltix.timebase.api.messages.TradeMessage();

        trade.setSymbol("DLTX");
        
        TickLoader          loader = stream.createLoader ();

        Thread.sleep (PAUSE_MILLIS);
        
        for (int ii = 0; ii < NUM_TRIES; ) {
            trade.setTimeStampMs(ii);
            trade.setPrice(ii);
            
            loader.send (trade);

            ii++;

            if (ii % NUM_PER_BURST == 0)
                Thread.sleep (PAUSE_MILLIS);
        }

        loader.close ();

        TickCursor check = null;

        try {
            check = TickCursorFactory.create(stream, 0);

            for (int ii = 0; ii < NUM_TRIES; ii++) {
                assertTrue ("Failed to select message #" + ii, check.next ());

                deltix.timebase.api.messages.TradeMessage msg = (deltix.timebase.api.messages.TradeMessage) check.getMessage ();

                assertEquals (ii, msg.getTimeStampMs());
            }

            assertFalse (check.next ());
        } finally {
            Util.close(check);
        }

        check.close();
    }   
}
