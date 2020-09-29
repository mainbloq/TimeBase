package deltix.qsrv.hf.tickdb.tb5;

import deltix.qsrv.hf.tickdb.StreamConfigurationHelper;
import deltix.qsrv.hf.tickdb.TDBRunner;
import deltix.qsrv.hf.tickdb.comm.server.TomcatServer;
import deltix.qsrv.hf.tickdb.pub.*;
import deltix.qsrv.testsetup.TickDBCreator;
import deltix.timebase.messages.ConstantIdentityKey;
import deltix.timebase.messages.IdentityKey;

import deltix.timebase.messages.TimeStamp;
import deltix.util.JUnitCategories;
import deltix.util.time.GMT;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@Category(JUnitCategories.TickDBFast.class)
public class Test_Delete {

    private static TDBRunner runner;

    @BeforeClass
    public static void start() throws Throwable {
        System.setProperty(TickDBFactory.VERSION_PROPERTY, "5.0");

        runner = new TDBRunner(true, true, new TomcatServer());
        runner.startup();
    }

    @AfterClass
    public static void stop() throws Throwable {
        runner.shutdown();
        runner = null;
    }

    @Test
    @Ignore("for 4.3")
    public void testLarge() {

        StreamOptions options = new StreamOptions (StreamScope.DURABLE, "large", null, 0);
        options.setFixedType(StreamConfigurationHelper.mkUniversalBarMessageDescriptor());

        DXTickStream stream = runner.getServerDb().createStream(options.name, options);

        TDBRunner.BarsGenerator gn =
                new TDBRunner.BarsGenerator(new GregorianCalendar(), (int) deltix.timebase.api.messages.BarMessage.BAR_SECOND, 5000000, "ORCL", "WWWW");

        try (TickLoader loader = stream.createLoader()) {
            while (gn.next())
                loader.send(gn.getMessage());
        }

        stream = runner.getTickDb().getStream("large");

        long[] range = stream.getTimeRange();
        TimeStamp start = TimeStamp.fromMilliseconds((range[1] + range[0]) / 2);
        TimeStamp end = TimeStamp.fromMilliseconds(range[0] + (range[1] - range[0]) * 2 / 3);

        //System.out.println("dropping range: " + GMT.formatNanos(start.getNanoTime()) + " to " + GMT.formatNanos(end.getNanoTime()));

        try( TickCursor cursor = stream.select(0, null)) {
            int total = 0;
            while (cursor.next()) {
                cursor.getMessage();
                total ++;
            }

            assertEquals(total, 5000000);
        }

        stream.delete(start, end);

        try( TickCursor cursor = stream.select(0, null)) {
            int total = 0;
            while (cursor.next()) {
                long time = cursor.getMessage().getNanoTime();
                assert time < start.getNanoTime() || time > end.getNanoTime() : GMT.formatNanos(time);
                total ++;
            }

            assertTrue(String.valueOf(total), total > 2000000);
        }
    }

    @Test
    @Ignore("for 4.3")
    public void test1() {
        DXTickStream bars = TickDBCreator.createBarsStream(runner.getServerDb(), "test1");

        long[] range = bars.getTimeRange();
        long start = TimeStamp.getNanoTime((range[1] + range[0]) / 2);
        long end = TimeStamp.getNanoTime(range[0] + (range[1] - range[0]) * 2 / 3);

        try (TickCursor cursor = bars.select(0, null)) {
            int total = 0;
            while (cursor.next()) {
                cursor.getMessage();
                total ++;
            }

            assert total > 0;

            //System.out.println(total);
        }

        deleteAndVerify(bars, start, end);
    }

    @Test
    @Ignore("for 4.3")
    public void test11() {
        DXTickStream bars = TickDBCreator.createBarsStream(runner.getServerDb(), "test11");

        long[] range = bars.getTimeRange();
        long start = TimeStamp.getNanoTime((range[1] + range[0]) / 2);
        long end = TimeStamp.getNanoTime(range[0] + (range[1] - range[0]) * 2 / 3);

        IdentityKey[] ids = {
                new ConstantIdentityKey("ORCL")
        };

        try (TickCursor cursor = bars.select(0, null, null, ids)) {
            int total = 0;
            while (cursor.next()) {
                cursor.getMessage();
                total ++;
            }

            assert total > 0;
            //System.out.println(total);
        }

        deleteAndVerify(bars, start, end, ids);
    }

    @Test
    @Ignore("for 4.3")
    public void test3() {
        DXTickStream bars = TickDBCreator.createBarsStream(runner.getServerDb(), "test3");

        long time = TimeStamp.getNanoTime(bars.getTimeRange()[0]);
        long endtime = time + TimeStamp.getNanoTime(60 * 10 * 1000);

        deleteAndVerify(bars, 0, endtime);

        long[] range = bars.getTimeRange();

        assertTrue("Delete(" + GMT.formatNanos(endtime) + ") but stream start time = " +
                GMT.formatDateTimeMillis(range[0]), TimeStamp.getNanoTime(range[0]) > endtime);
    }

    @Test
    @Ignore("for 4.3")
    public void test4() {
        DXTickStream bars = TickDBCreator.createBarsStream(runner.getServerDb(), "test4");

        long time = TimeStamp.getNanoTime(bars.getTimeRange()[0]);
        long endtime = time + TimeStamp.getNanoTime(60 * 10 * 1000);

        deleteAndVerify(bars, endtime, Long.MAX_VALUE);

        long[] range = bars.getTimeRange();

//        assertTrue("Delete(" + GMT.formatNanos(endtime) + ") but stream start time = " +
//                GMT.formatDateTimeMillis(range[0]), TimeStamp.getNanoTime(range[0]) > endtime);
    }

    private void deleteAndVerify(DXTickStream stream, long start, long end) {

        System.out.println("dropping range: " + GMT.formatNanos(start) + " to " + GMT.formatNanos(end));

        stream.delete(TimeStamp.fromNanoseconds(start), TimeStamp.fromNanoseconds(end));

        try( TickCursor cursor = stream.select(0, null)) {
            int total = 0;
            while (cursor.next()) {
                long time = cursor.getMessage().getNanoTime();
                assert time < start || time > end : GMT.formatNanos(time);
                total ++;
            }

            assert total > 0;

            System.out.println(total);
        }
    }

    private void deleteAndVerify(DXTickStream stream, long start, long end, IdentityKey[] ids) {

        System.out.println("Dropping range: " + GMT.formatNanos(start) + " to " + GMT.formatNanos(end));

        stream.delete(TimeStamp.fromNanoseconds(start), TimeStamp.fromNanoseconds(end), ids);

        try( TickCursor cursor = stream.select(0, null, null, ids)) {
            int total = 0;

            while (cursor.next()) {
                long time = cursor.getMessage().getNanoTime();
                assert time < start || time > end : GMT.formatNanos(time);
                total ++;
            }

            assert total > 0;

            //System.out.println(total);
        }
    }

    @Test
    @Ignore("for 4.3")
    public void test2() {
        DXTickStream bars = TickDBCreator.createBarsStream(runner.getServerDb(), "test2");

        long time = TimeStamp.getNanoTime(bars.getTimeRange()[0]);

        deleteAndVerify(bars, time, time);
    }
}
