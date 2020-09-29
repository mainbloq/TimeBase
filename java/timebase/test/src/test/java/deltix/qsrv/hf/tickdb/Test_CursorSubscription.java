package deltix.qsrv.hf.tickdb;

/*  ##TICKDB.FAST## */

import deltix.data.stream.MessageSourceMultiplexer;
import deltix.qsrv.hf.pub.*;
import deltix.timebase.messages.ConstantIdentityKey;
import deltix.timebase.messages.IdentityKey;
import deltix.timebase.messages.InstrumentKey;
import deltix.timebase.messages.InstrumentMessage;

import deltix.qsrv.hf.tickdb.server.ServerRunner;
import deltix.timebase.api.messages.BarMessage;
import deltix.util.JUnitCategories;
import org.junit.Assert;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import deltix.qsrv.hf.tickdb.pub.*;
import deltix.qsrv.testsetup.TickDBCreator;
import deltix.util.lang.Util;
import org.junit.experimental.categories.Category;

import java.util.GregorianCalendar;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

@Category(JUnitCategories.TickDBFast.class)
public class Test_CursorSubscription {

    private static TDBRunner runner;

    @BeforeClass
    public static void start() throws Throwable {
        runner = new ServerRunner(true, true);
        runner.startup();

        DXTickStream stream1 = TickDBCreator.createBarsStream(runner.getServerDb(), "bars");
        stream1.clear(new InstrumentKey( "ORCL"),
                new InstrumentKey("AAPL"));

        DXTickStream stream2 = TickDBCreator.createBarsStream(runner.getServerDb(), "bars1");
        stream2.clear(new InstrumentKey("IBM"),
                new InstrumentKey("GOOG"));
    }

    @AfterClass
    public static void stop() throws Throwable {
        runner.shutdown();
        runner = null;
    }

    @Test
    public void Test_Subscribe() {
        RunTest_Subscribe(runner.getServerDb());
    }

    @Test
    public void Test_Unsubscribe() {
        RunTest_Unsubscribe(runner.getServerDb());
    }

    @Test
    public void Test_UnsubscribeAll() {
        RunTest_UnsubscribeAll(runner.getServerDb());
    }

    @Test
    public void Test_SubscribeRemote() {
        for (int i = 0; i < 10; i++)
            RunTest_Subscribe(runner.getTickDb());
    }

    @Test
    public void Test_UnsubscribeRemote() {
        for (int i = 0; i < 10; i++)
            RunTest_Unsubscribe(runner.getTickDb());
    }

    @Test
    public void Test_UnsubscribeALLRemote() {
        RunTest_UnsubscribeAll(runner.getTickDb());
    }

    private SelectionOptions createOptions () {
        SelectionOptions    o = new SelectionOptions ();
        o.raw = true;
        return (o);
    }

    @Test
    public void Test_SubscribeRemote1() {
        for (int i = 0; i < 10; i++)
            RunTest_Subscribe1(runner.getTickDb());
    }

    @Test
    public void Test_ResetWithInstruments() {
        RunTest_ResetWithInstruments(runner.getTickDb());
    }

    public void RunTest_Subscribe1(DXTickDB db) {

        DXTickStream stream = db.getStream("bars");
        long[] range = stream.getTimeRange();

        IdentityKey[] keys = new IdentityKey[] {
                new ConstantIdentityKey("IBM"),
                new ConstantIdentityKey("AAPL")
        };

        TickCursor cursor = null;
        try {
            cursor = stream.select(0, createOptions (), null, keys);

            for (int i = 0; i < 100; i++)
                cursor.next();

            cursor.removeStream(stream);
            cursor.addStream(stream);

            assertTrue(cursor.next());

            RawMessage message = (RawMessage) cursor.getMessage();

            while (cursor.next()) {
                message = (RawMessage) cursor.getMessage();
            }

            assertEquals(range[1], message.getTimeStampMs());

            cursor.close();
            cursor = null;
        } finally {
            Util.close(cursor);
        }
    }

    public void RunTest_Subscribe(DXTickDB db) {

        DXTickStream stream1 = db.getStream("bars");
        DXTickStream stream2 = db.getStream("bars1");

        IdentityKey[] keys = new IdentityKey[] {
                new ConstantIdentityKey("IBM"),
                new ConstantIdentityKey("AAPL")
        };

        TickCursor cursor = null;
        try {
            cursor = db.select(0, createOptions (), null, keys, stream1);

            for (int i = 0; i < 100; i++) {
                cursor.next();
            }

            cursor.addStream(stream2, stream2);
            cursor.addStream(stream2);
            assertTrue(cursor.next());
            String symbol = cursor.getMessage().getSymbol().toString();
            assertTrue(symbol.equals("AAPL") || symbol.equals("IBM"));

            assertTrue(cursor.next());

            if (symbol.equals("AAPL")) {
                symbol = cursor.getMessage().getSymbol().toString();
                assertEquals("IBM", symbol);
                assertEquals("bars", cursor.getCurrentStreamKey());
            } else {
                symbol = cursor.getMessage().getSymbol().toString();
                assertEquals("AAPL", symbol);
                assertEquals("bars1", cursor.getCurrentStreamKey());
            }

            while (cursor.next()) {
                RawMessage message = (RawMessage) cursor.getMessage();
                assertEquals(message.type, cursor.getCurrentType());
            }

            cursor.close();
            cursor = null;
        } finally {
            Util.close(cursor);
        }
    }

    @Test
    public void testEarlier() {
        runTestEarlier(runner.getServerDb());
        runTestEarlier(runner.getTickDb());

        runEOF(runner.getTickDb());
        runEOF(runner.getServerDb());
    }

    @Test
    public void testEarlier1() {
        ///runTestEarlier1(runner.getServerDb());
        runTestEarlier1(runner.getTickDb());
    }

    public void runTestEarlier1(DXTickDB db) {

        IdentityKey[] keys = new IdentityKey[] {
                new ConstantIdentityKey("GOOG")
        };

        StreamOptions options = new StreamOptions (StreamScope.DURABLE, "large", null, 0);
        options.setFixedType(StreamConfigurationHelper.mkUniversalBarMessageDescriptor());

        DXTickStream stream = db.createStream(options.name, options);

        GregorianCalendar calendar = new GregorianCalendar(2016, 1, 1);
        long start = calendar.getTimeInMillis();

        int total = 5000000;

        TDBRunner.BarsGenerator gn =
                new TDBRunner.BarsGenerator(calendar, (int) BarMessage.BAR_SECOND, total, "ORCL", "GOOG");

        try (TickLoader loader = stream.createLoader()) {
            while (gn.next())
                loader.send(gn.getMessage());
        }

        int count = 0;

        TickCursor cursor = null;
        try {

            cursor = db.select(start + total * BarMessage.BAR_SECOND / 4, createOptions (), null, keys, stream);

            for (int i = 0; i < 100; i++) {
                if (cursor.next())
                    count++;
            }

            cursor.setTimeForNewSubscriptions(start);
            cursor.addEntity(new ConstantIdentityKey("ORCL"));

            while (cursor.next()) {
                count++;
            }

        } finally {
            Util.close(cursor);
        }

        //stream.delete();

        Assert.assertEquals(total * 3/4, count);
    }

    public void runTestEarlier(DXTickDB db) {

        IdentityKey[] keys = new IdentityKey[] {
                new ConstantIdentityKey("GOOG")
        };

        DXTickStream bars = db.getStream("bars");
        long[] range = bars.getTimeRange();

        int count = 0;

        TickCursor cursor = null;
        try {

            cursor = db.select((range[0] + range[1]) / 2, createOptions (), null, keys, bars);

            for (int i = 0; i < 100; i++) {
                if (cursor.next())
                    count++;
            }

            cursor.setTimeForNewSubscriptions(range[0]);
            cursor.addEntity(new ConstantIdentityKey("IBM"));

            while (cursor.next()) {
                count++;
            }

        } finally {
            Util.close(cursor);
        }

        Assert.assertEquals(35349, count);
    }

    public void runEOF(DXTickDB db) {

        IdentityKey[] keys = new IdentityKey[] {
                new ConstantIdentityKey( "GOOG")
        };

        DXTickStream bars = db.getStream("bars");
        long[] range = bars.getTimeRange();

        int count = 0;

        TickCursor cursor = null;
        try {

            cursor = db.select((range[0] + range[1]) / 2, createOptions (), null, keys, bars);

            while (cursor.next()) {
                count++;
            }

            cursor.setTimeForNewSubscriptions(range[0]);
            cursor.addEntity(new ConstantIdentityKey( "IBM"));

            while (cursor.next()) {
                count++;
            }

        } finally {
            Util.close(cursor);
        }

        Assert.assertEquals(35349, count);
    }

    public void RunTest_Unsubscribe(DXTickDB db) {

        DXTickStream stream1 = db.getStream("bars");
//        if (stream1 == null) {
//            stream1 = TickDBCreator.createBarsStream(db);
//            stream1.clear(new InstrumentKey("ORCL"),
//                    new InstrumentKey("AAPL"));
//        }

        DXTickStream stream2 = db.getStream("bars1");
//        if (stream2 == null) {
//            stream2 = TickDBCreator.createBarsStream(db, "bars1");
//            stream2.clear(new InstrumentKey("IBM"),
//                    new InstrumentKey("GOOG"));
//        }

        IdentityKey[] keys = new IdentityKey[] {
                new ConstantIdentityKey("IBM"),
                new ConstantIdentityKey("AAPL")
        };

        TickCursor cursor = null;
        try {
            cursor = db.select(0, createOptions (), null, keys, stream1, stream2);
            for (int i = 0; i < 100; i++)
                cursor.next();

            cursor.removeStream(stream2);
            assertTrue(cursor.next());
            while (cursor.next()) {
                String symbol = cursor.getMessage().getSymbol().toString();
                assertTrue(symbol.equals("IBM"));
                assertEquals("bars", cursor.getCurrentStreamKey());
            }

        } finally {
            Util.close(cursor);
        }
    }

    public void RunTest_UnsubscribeAll(DXTickDB db) {

        DXTickStream stream1 = db.getStream("bars");
        DXTickStream stream2 = db.getStream("bars1");

        IdentityKey[] keys = new IdentityKey[] {
                new ConstantIdentityKey("IBM"),
                new ConstantIdentityKey("AAPL")
        };

        TickCursor cursor = null;
        try {
            cursor = db.select(0, new SelectionOptions(), null, keys, stream1, stream2);
            for (int i = 0; i < 100; i++)
                cursor.next();

            cursor.removeStream(stream2);
            cursor.removeStream(stream1);
            assertTrue(!cursor.next());
        } finally {
            Util.close(cursor);
        }
    }

    @Test
    public void         testResubscribe() {
        testResubscribe(runner.getServerDb());
        testResubscribe(runner.getTickDb());
    }

    public void         testResubscribe(DXTickDB db) {
        DXTickStream stream = db.getStream("bars");

        SelectionOptions options = new SelectionOptions();

        try (TickCursor cursor = stream.select(Long.MIN_VALUE, options) ) {

            long time = Long.MIN_VALUE;
            int count = 0;

            while (cursor.next()) {
                InstrumentMessage message = cursor.getMessage();

                if (time == Long.MIN_VALUE)
                    time = message.getTimeStampMs();

                long timestamp = message.getTimeStampMs();

                if (count++ == 1) {
                    cursor.addEntity(new ConstantIdentityKey("GOOG"));
                    assertTrue("gap detected: " + (timestamp - time), timestamp == time || timestamp - time == 1000); // 1 sec
                }

                time = timestamp;

//                if (count < 3 || count > 23660)
//                    System.out.println(message);
            }

            assertEquals(23662, count);
        }

    }

    public void RunTest_ResetWithInstruments(DXTickDB db) {
        DXTickStream stream = db.getStream("bars");

        IdentityKey[] instruments = new IdentityKey[] {
                new InstrumentKey("IBM"),
                new InstrumentKey("GOOG")
        };

        MessageSourceMultiplexer<InstrumentMessage> msm = new
                MessageSourceMultiplexer<InstrumentMessage>(true, false);

        SelectionOptions options = new SelectionOptions();

        try (TickCursor cursor = stream.createCursor(options)) {
            cursor.reset(Long.MIN_VALUE);
            cursor.addEntities(instruments, 0, instruments.length);

            msm.add(cursor);

            InstrumentMessage message = null;
            int count = 0;
            while (msm.next()) {
                InstrumentMessage newMessage = msm.getMessage().clone();

                if (message != null) {
                    if (message.getSymbol().equals(newMessage.getSymbol()) &&
                            message.getNanoTime() == newMessage.getNanoTime())
                        assert false;
                }

                message = newMessage;

                //System.out.println("MSG " + count + ": " + message);

                if (++count == 100) {
                    msm.remove(cursor);
                    cursor.reset(Long.MIN_VALUE);
                    msm.add(cursor);
                    msm.remove(cursor);
                    cursor.reset(Long.MIN_VALUE);
                    msm.add(cursor);
                }

                if (count > 150)
                    break;
            }

            System.out.println("Count = " + count);
        }

    }

}
