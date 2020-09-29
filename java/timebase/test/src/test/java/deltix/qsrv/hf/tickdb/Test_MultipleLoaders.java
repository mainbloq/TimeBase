package deltix.qsrv.hf.tickdb;

import deltix.timebase.api.messages.BarMessage;
import deltix.qsrv.hf.tickdb.pub.*;
import deltix.util.lang.Util;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.experimental.categories.Category;
import deltix.util.JUnitCategories.TickDBFast;

/**
 * Time: 3:43:05 PM
  */
@Category(TickDBFast.class)
public class Test_MultipleLoaders extends TDBTestBase {

    public Test_MultipleLoaders() {
        super(true);
    }

    @Test
    public void testLocal() {
        runTest(getServerDb());
    }

    @Test
    public void testRemote() {
        runTest(getTickDb());
    }

    @Test
    public void test2Local() throws InterruptedException {
        runTest2(getServerDb());
    }

    @Test
    public void test2Remote() throws InterruptedException {
        runTest2(getTickDb());
    }

    public void runTest(DXTickDB db) {

        DXTickStream stream = db.createStream("test",
                StreamOptions.fixedType(StreamScope.DURABLE, "test", "test", 0, 
                        StreamConfigurationHelper.mkBarMessageDescriptor(null, null, null,
                                "DECIMAL(4)", "DECIMAL(0)")));

        BarsGenerator generator =
                new BarsGenerator(null,(int) BarMessage.BAR_MINUTE, 100, "MSFT", "ORCL");

        final int[] errors = new int[] {0};
        
        TickLoader loader1 = stream.createLoader(new LoadingOptions(false));

        loader1.addEventListener(new LoadingErrorListener() {            
             public void onError(LoadingError e) {
                 errors[0]++;
                 e.printStackTrace(System.out);
             }
        });
        TickLoader loader2 = stream.createLoader(new LoadingOptions(false));

         loader2.addEventListener(new LoadingErrorListener() {
            
             public void onError(LoadingError e) {
                 errors[0]++;
                 e.printStackTrace(System.out);
             }
        });

        TickLoader loader = loader1;
        while (generator.next()) {
            if (loader.equals(loader1))
                loader = loader2;
            else
                loader = loader1;

            loader.send(generator.getMessage());
        }

        loader1.close();
        loader2.close();

        TickCursor cursor = null;
        try {
            cursor = stream.select(0, null);
            assertTrue(cursor.next());

            int count = 0;
            while (cursor.next()) {
                count++;
            }

            assertEquals(99, count);
            cursor.close();
        } finally {
            Util.close(cursor);
        }
    }

    public void runTest2(DXTickDB db) throws InterruptedException {
        final AtomicInteger     expected = new AtomicInteger (199);

        DXTickStream stream = db.createStream("test",
                StreamOptions.fixedType(StreamScope.DURABLE, "test", "test", 0,
                        StreamConfigurationHelper.mkBarMessageDescriptor(null, null, null, 
                                "DECIMAL(4)", "DECIMAL(0)")));

        LoadingOptions options = new LoadingOptions(false);
        options.writeMode = LoadingOptions.WriteMode.REPLACE;

         final LoadingErrorListener listener = new LoadingErrorListener() {

            public void onError(LoadingError e) {
                e.printStackTrace(System.out);
            }
        };

        final TickLoader loader1 = stream.createLoader(options);
        loader1.addEventListener(listener);
        final TickLoader loader2 = stream.createLoader(options);
        loader2.addEventListener(listener);

        final CountDownLatch counter = new CountDownLatch(2);
        new TestLoader(loader1, counter).start();
        new TestLoader(loader2, counter).start();
        counter.await();
        
        TickCursor cursor = null;
        try {
            cursor = stream.select(0, null);
            assertTrue(cursor.next());

            int count = 0;
            while (cursor.next()) {
                count++;
            }
            cursor.close();
            cursor = null;
            assertEquals(expected.get (), count);

        } finally {
            Util.close(cursor);
        }
    }

    public static class TestLoader extends Thread {
        private TickLoader loader;
        private CountDownLatch counter;

        public TestLoader(TickLoader loader, CountDownLatch counter) {
            this.loader = loader;
            this.counter = counter;
        }

        @Override
        public void run() {
            try {
                BarsGenerator generator =
                        new BarsGenerator(null, (int) BarMessage.BAR_MINUTE, 100, "MSFT", "ORCL");

                while (generator.next())
                    loader.send(generator.getMessage());

                loader.close();
            } finally {
                counter.countDown();   
            }
        }
    }
}