package deltix.qsrv.hf.tickdb;

import deltix.qsrv.hf.tickdb.comm.client.ServerException;
import deltix.qsrv.hf.tickdb.pub.DXTickDB;
import deltix.qsrv.hf.tickdb.pub.DXTickStream;
import deltix.qsrv.hf.tickdb.pub.SelectionOptions;
import deltix.qsrv.hf.tickdb.StreamConfigurationHelper;
import deltix.qsrv.hf.tickdb.pub.StreamOptions;
import deltix.qsrv.hf.tickdb.pub.StreamScope;
import deltix.qsrv.hf.tickdb.pub.TickCursor;
import deltix.util.JUnitCategories.TickDBFast;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Runs 3 threads.
 * Two treads attempt to create a stream and then read a message from it.
 * Third thread polls TB for this stream and then tries to read message.
 * <p>
 * Time must guarantee that:
 * <ul>
 *     <li>
 *         Exactly one thread successfully creates stream.
 *     </li>
 *     <li>
 *         Thread that failed to create stream must get "Duplicate stream key" message.
 *     </li>
 *     <li>
 *         Polling thread must successfully open cursor as soon as stream created.
 *         Client may not observer partially created streams.
 *     </li>
 * </ul>
 */
@Category(TickDBFast.class)
public class Test_ConcurrentCreateThenRead extends TDBTestBase {

    public Test_ConcurrentCreateThenRead() {
        super(true);
    }

    @Test
    public void runTest() throws InterruptedException {
        DXTickDB db = getTickDb();

        for (int i = 0; i < 20; i++) {
            testIteration(db);
        }
    }

    private void testIteration(DXTickDB db) throws InterruptedException {
        CreateStreamTask t1 = new CreateStreamTask(db);
        CreateStreamTask t2 = new CreateStreamTask(db);
        PollStreamTask t3 = new PollStreamTask(db);

        Thread[] executors = new Thread[50];
        for (int i = 0; i < executors.length; i++) {
            executors[i] = new Thread(i % 3 == 0 ? t1 : (i % 3 == 1 ? t2 : t3));
        }

        for (int i = 0; i < executors.length; i++) {
            executors[i].start();
        }

        for (int i = 0; i < executors.length; i++) {
            executors[i].join();
        }


//        Thread thread1 = new Thread(t1);
//        Thread thread2 = new Thread(t2);
//        Thread thread3 = new Thread(t3);
//
//        thread3.start();
//        thread1.start();
//        thread2.start();
//
//        thread1.join();
//        thread2.join();
//        thread3.join();

        if (!t1.success.get() || !t2.success.get() || !t3.success.get()) {
            throw new AssertionError("One of threads failed");
        }

        db.getStream("test").delete();
    }


    private static class CreateStreamTask implements Runnable {

        private final DXTickDB db;
        private final AtomicBoolean success = new AtomicBoolean(false);

        public CreateStreamTask(DXTickDB db) {
            this.db = db;
        }

        @Override
        public void run() {
            String name = "test";
            StreamOptions streamOptions = StreamOptions.fixedType(StreamScope.DURABLE, name, name, 0,
                    StreamConfigurationHelper.mkBarMessageDescriptor(null, null, null,
                            "DECIMAL(4)", "DECIMAL(0)"));

            try {
                DXTickStream stream = db.getStream(name);;
                try {
                    if (stream == null)
                        stream = db.createStream("test", streamOptions);
                } catch (ServerException | IllegalArgumentException e) {
                    if (!e.getMessage().endsWith("Duplicate stream key: test")) {
                        throw new AssertionError("Unexpected message", e);
                    }
                    //stream = db.getStream("test");
                }

                SelectionOptions options = new SelectionOptions();
                TickCursor cursor = stream.createCursor(options);
                cursor.reset(Long.MIN_VALUE);
                cursor.next();
                cursor.close();

                success.set(true);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }


    private static class PollStreamTask implements Runnable {

        private final DXTickDB db;
        private final AtomicBoolean success = new AtomicBoolean(false);

        public PollStreamTask(DXTickDB db) {
            this.db = db;
        }

        @Override
        public void run() {
            try {
                DXTickStream stream = null;
                while (stream == null) {
                    stream = db.getStream("test");
                }

                SelectionOptions options = new SelectionOptions();
                TickCursor cursor = stream.createCursor(options);
                cursor.reset(Long.MIN_VALUE);
                cursor.next();
                cursor.close();

                success.set(true);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}