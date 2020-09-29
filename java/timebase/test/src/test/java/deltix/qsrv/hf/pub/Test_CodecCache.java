package deltix.qsrv.hf.pub;

import deltix.qsrv.hf.tickdb.TDBRunner;
import deltix.qsrv.hf.tickdb.pub.*;
import deltix.qsrv.hf.tickdb.server.ServerRunner;
import deltix.qsrv.hf.tickdb.util.ZIPUtil;
import deltix.util.JUnitCategories;
import deltix.util.io.Home;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

/**
 * Test for CodecCache
 */
@Category (JUnitCategories.TickDBFast.class)
public class Test_CodecCache {


    @Test
    public void testMultiThreadingCodecs() throws Exception {
        File folder = new File(TDBRunner.getTemporaryLocation());

        FileInputStream is = new FileInputStream(Home.getFile("testdata/tickdb/misc/securities.zip"));
        ZIPUtil.extractZipStream(is, folder);
        is.close();

        TDBRunner runner = new ServerRunner(true, false, folder.getAbsolutePath());
        runner.startup();

        String URL = "dxtick://localhost:" + runner.getPort();

        final AtomicInteger exceptionCount = new AtomicInteger(0);

        Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread th, Throwable ex) {
                ex.printStackTrace(System.out);
                //System.out.println("Uncaught exception: " + ex);
                exceptionCount.incrementAndGet();
            }
        };

        final Thread[] threads = new Thread[50];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run () {
                    DXTickDB db = TickDBFactory.createFromUrl(URL);
                    db.open(false);
                    TickStream stream = db.getStream("securities");
                    try (TickCursor cursor = db.select(Long.MIN_VALUE, new SelectionOptions(), stream)) {
                        int count = 0;
                        while (cursor.next())
                            count++;
                        assertEquals(70, count);    // 70 messages in this stream
//                            System.out.println(count++);
                    } catch (RuntimeException e) {
                        throw e;
                    } finally {
                        db.close();
                    }
                }
            });
            threads[i].setUncaughtExceptionHandler(handler);
            threads[i].start();
        }

        for (Thread t : threads)
            t.join();

        assertEquals(0, exceptionCount.get());  // 0 exceptions when read stream in (threads.length) cursors

        runner.shutdown();
    }
}
