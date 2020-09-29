package deltix.qsrv.hf.tickdb.purge;

import deltix.qsrv.hf.tickdb.TDBRunner;
import deltix.qsrv.hf.tickdb.pub.DXTickDB;
import deltix.qsrv.hf.tickdb.server.ServerRunner;
import deltix.qsrv.hf.tickdb.tests.procs.TestLoader;
import deltix.qsrv.hf.tickdb.tests.procs.TestPurge;
import deltix.qsrv.hf.tickdb.tests.procs.TestReader;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Ignore
public class JUnitPurgeTestRunner {

    private static TDBRunner runner;

    @BeforeClass
    public static void beforeClass() throws Exception {
        runner = ServerRunner.create(true, false, 1 << 24);
        runner.startup();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        runner.shutdown();
        runner = null;
    }

    @Test
    public void test() throws InterruptedException {
        runTest("testStream", 1, 10, 30 * 1000, 60 * 1000);
    }

    public String getServerUrl() {
        return String.format("dxtick://localhost:%d", runner.getPort());
    }

    public DXTickDB getTickDb() {
        return runner.getTickDb();
    }


    public void runTest(String stream, int loaders, int readers, long purgePeriod, long purgeInterval) throws InterruptedException {
        TestReader testReader = TestReader.create(getServerUrl(), stream, readers, false);
        TestLoader testLoader = TestLoader.create(getServerUrl(), stream, loaders);
        TestPurge testPurge = TestPurge.create(getServerUrl(), stream, purgePeriod, purgeInterval);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(testPurge);
        Thread.sleep(5000);
        executorService.execute(testLoader);
        executorService.execute(testReader);
        executorService.awaitTermination(10, TimeUnit.MINUTES);
    }

}
