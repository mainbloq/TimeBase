package deltix.qsrv.hf.tickdb.testframework;

import deltix.qsrv.hf.tickdb.StreamConfigurationHelper;
import deltix.qsrv.hf.tickdb.impl.TickDBImpl;
import deltix.qsrv.hf.tickdb.pub.*;
import deltix.qsrv.hf.ui.repair.TBRecover;
import deltix.util.lang.Util;
import deltix.util.time.TimeKeeper;

import java.io.File;
import java.security.*;

/**
 *
 */
class ExitInterceptor extends SecurityManager {
    static volatile int             exitCode = 0;
    
    @Override
	public void     checkExit (int status) {
		exitCode = status;
	}
		
    @Override
	public void     checkPermission (Permission perm) {
	}
					
    @Override
	public void     checkPermission (Permission perm, Object context) {
	}     
}

public class TestMessageGenerator {
    public static final int         REPORT_INTERVAL = 2000;
    private static final int        NUM_SYMBOLS = 200;
    private static final int        NUM_MSGS_PER_SECOND = 2000000;

    private static final String []  SYMBOLS = new String [NUM_SYMBOLS];

    static {
        for (int ii = 0; ii < NUM_SYMBOLS; ii++)
            SYMBOLS [ii] = "DLX" + ii;
    }

    private static DXTickDB                 db;
    private static DXTickStream             stream;
    private static TickLoader               loader;

    public static void main (String [] args) throws Exception {

        System.setProperty(TickDBImpl.VERSION_PROPERTY, "5.0");

        TBRecover.run(args[0]);

        //
        //  Intercept exit
        //
        System.setSecurityManager (new ExitInterceptor ());
        
        Runtime.getRuntime ().addShutdownHook (
            new Thread () {
                @Override
                public void run () {
                    onShutdown ();
                }                
            }
        );
        
        new Thread ("Graceful shutdown listener") {
            @Override
            public void run () {
                shutdownCommandListener ();
            }
        }.start ();
        
        System.out.println ("MESSAGE GENERATOR STARTED.");

        DataCacheOptions cacheOptions = new DataCacheOptions();
        cacheOptions.fs = new FSOptions();
        cacheOptions.fs.maxFileSize = 1 << 16;
        cacheOptions.fs.maxFolderSize = 10;

        db = new TickDBImpl(cacheOptions, new File(args [0]));
        db.open (false);

        String              key = args [1];

        stream = db.getStream (key);

        if (stream == null) {
            StreamOptions options = new StreamOptions(StreamScope.DURABLE, key, key, 0);
            stream = db.createStream (key, options);
            StreamConfigurationHelper.setTradeOrBBO (stream, true, "", 840);
        } else {
            long[] range = stream.getTimeRange();
            stream.purge((range[0] + range[1])/2);
        }



        loader = stream.createLoader ();
        deltix.timebase.api.messages.TradeMessage msg = new deltix.timebase.api.messages.TradeMessage();

        long                start = TimeKeeper.currentTime;
        long                msgsSent = 0;
        long                sendDelay = 0;
        long                sequence = 0;
        
        long                nextReport = start + REPORT_INTERVAL;

        try {
            for (;;) {

                msg.setPrice(sequence);
                
                for (int ii = 0; ii < NUM_SYMBOLS; ii++) {
                    msg.setSymbol(SYMBOLS [ii]);
                    msg.setSize(ii);

                    long    before = TimeKeeper.currentTime;
                    msg.setTimeStampMs(before);

                    loader.send (msg);

                    long    after = TimeKeeper.currentTime;
                    long    delay = after - before;

                    if (delay > sendDelay)
                        sendDelay = delay;
                    
                    msgsSent++;
                }

                sequence++;
                
                long        virtualTime =
                    start + 1000 * msgsSent / NUM_MSGS_PER_SECOND;

                long        d = virtualTime - TimeKeeper.currentTime;

                if (d > 100) 
                    Thread.sleep (d);

                long        realTime = TimeKeeper.currentTime;

                if (realTime >= nextReport) {
                    System.out.printf (
                        "Sent %,d messages; rate = %,.0f msg/s; max send delay: %.3fs\n",
                        msgsSent,
                        msgsSent * 1000.0 / (realTime - start),
                        sendDelay * 0.001
                    );

                    nextReport = TimeKeeper.currentTime + REPORT_INTERVAL;
                    sendDelay = 0;
                }
            }
        } catch (InterruptedException | WriterClosedException x) {
            //
        } finally {
            Util.close(loader);
        }
    }
    
    private static void     onShutdown () {
        int exitCode = ExitInterceptor.exitCode;
        
        if (exitCode == 0)
            System.out.println ("Exiting with code " + exitCode);                
    }
    
    private static void     shutdownCommandListener () {
        try {
            int        c = System.in.read ();
            
            if (c == 'g') {
                System.out.println ("GRACEFUL SHUTDOWN COMMAND RECEIVED");
                db.close ();
                System.exit (0);
            } else if (c == 'c') {
                System.out.println ("GRACEFUL SHUTDOWN COMMAND RECEIVED. CLOSING LOADER.");
                loader = Util.close(loader);
                Thread.sleep(5000);
                db.close ();
                System.exit (0);
            }

        } catch (Throwable x) {
            x.printStackTrace(System.out);
            System.exit (1002);
        }
    }
}
