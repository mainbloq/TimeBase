package deltix.qsrv.hf.tickdb;

import deltix.qsrv.hf.tickdb.comm.server.TomcatServer;
import deltix.qsrv.hf.tickdb.pub.DXTickDB;
import deltix.qsrv.hf.tickdb.pub.DXTickStream;
import deltix.qsrv.hf.tickdb.pub.StreamOptions;
import deltix.util.concurrent.QuickExecutor;
import deltix.util.io.GUID;
import deltix.util.io.Home;
import deltix.util.io.IOUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.File;

public abstract class TDBRunnerBase {

    protected static TDBRunner runner;

    @BeforeClass
    public static void      start() throws Throwable {
        //long time = System.currentTimeMillis();

        runner = new TDBRunner(true, true, TDBRunner.getTemporaryLocation(), new TomcatServer());
        runner.startup();
        
        //System.out.println("TDBRunner start-up time: " + (System.currentTimeMillis() - time)/1000.0 + " sec");
    }

    @AfterClass
    public static void      stop() throws Throwable {
        runner.shutdown();
        runner = null;
    }

    public static DXTickDB  getTickDb() {
        return runner.getTickDb();
    }

    public static DXTickDB  getServerDb() {
        return runner.getServerDb();
    }

    public DXTickStream     createStream(DXTickDB db, String key, StreamOptions so) {
        DXTickStream stream = db.getStream(key);
        if (stream != null)
            stream.delete();

        return db.createStream(key, so);
    }
}
