package deltix.qsrv.hf.tickdb;

import deltix.timebase.messages.ConstantIdentityKey;
import deltix.timebase.messages.InstrumentMessage;

import deltix.qsrv.hf.pub.md.FloatDataType;
import deltix.qsrv.hf.pub.md.RecordClassDescriptor;
import deltix.qsrv.hf.pub.md.RecordClassSet;
import deltix.qsrv.hf.tickdb.pub.*;
import deltix.qsrv.hf.tickdb.pub.task.SchemaChangeTask;
import deltix.qsrv.hf.tickdb.schema.MetaDataChange;
import deltix.qsrv.hf.tickdb.schema.SchemaAnalyzer;
import deltix.qsrv.hf.tickdb.schema.StreamMetaDataChange;
import deltix.qsrv.testsetup.TickDBCreator;
import deltix.timebase.api.messages.BarMessage;
import deltix.timebase.messages.service.StreamTruncatedMessage;
import deltix.timebase.messages.service.SystemMessage;
import deltix.util.JUnitCategories;
import deltix.util.lang.Util;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
@Category(JUnitCategories.TickDBFast.class)
public class Test_Versioning extends TDBTestBase {

    public Test_Versioning() {
        super(false, true);
    }

    private void loadData(DXTickStream stream, Date time, String[] symbols) {
        GregorianCalendar c = new GregorianCalendar(2010, 0, 1);
        if (time != null)
            c.setTime(time);

        BarsGenerator g = new BarsGenerator(time != null ? c : null, (int) BarMessage.BAR_MINUTE, 5000, symbols);

        TickLoader loader = stream.createLoader();
        while (g.next())
            loader.send(g.getMessage());

        loader.close();
    }

    @Test
    public void                     appendTest() {
        DXTickStream stream = getTickDb().createStream("appendTest",
                StreamOptions.fixedType(StreamScope.DURABLE, "appendTest", null, 0,
                        StreamConfigurationHelper.mkUniversalBarMessageDescriptor()));

        Date start = null;
        loadData(stream, start, new String[] {"AA1", "AA2", "AA3", "AA4"});
        long[] range = stream.getTimeRange();

        stream.enableVersioning();

        SelectionOptions options = new SelectionOptions();
        options.versionTracking = true;
        options.allowLateOutOfOrder = true;
        //options.live = true;

        loadData(stream, start, new String[] {"AA1", "AA2", "AA3", "AA4"});

        TickCursor cursor = stream.select(Long.MIN_VALUE, options);

        //boolean getMessage = false;

        while (cursor.next()) {
            InstrumentMessage message = cursor.getMessage();

            if (message instanceof StreamTruncatedMessage) {
                Assert.fail("Got unexpected message: " + message);
                break;
            }


        }
        cursor.close();
    }

    @Test(timeout=10_000)
    public void                     truncateTest() {
        DXTickStream stream = getTickDb().createStream("truncateTest",
            StreamOptions.fixedType(StreamScope.DURABLE, "truncateTest", null, 0,
                    StreamConfigurationHelper.mkUniversalBarMessageDescriptor()));

        Date start = null;
        loadData(stream, start, new String[] {"AA1", "AA2", "AA3", "AA4"});
        long[] range = stream.getTimeRange();

        stream.enableVersioning();

        SelectionOptions options = new SelectionOptions();
        options.versionTracking = true;
        options.allowLateOutOfOrder = true;
        options.live = true;

        TickCursor cursor = stream.select(Long.MIN_VALUE, options);

        int count = 0;
        boolean getMessage = false;
        long ts  = (range[0] + range[1]) / 5;
        while (count < 10_001 && cursor.next()) {
            InstrumentMessage message = cursor.getMessage();
            if (count == 1500)
                loadData(stream, new Date(ts), new String[] {"AA1"});

            //System.out.println(count + " - " + message);

            count++;
            if (message instanceof StreamTruncatedMessage) {
                assertEquals("AA1:EQUITY", ((StreamTruncatedMessage)message).getInstruments().toString());
                getMessage = true;
                break;
            }


        }
        cursor.close();

        assertTrue("StreamTruncatedMessage is not recieved!", getMessage);
    }

    @Test
    public void                     clearTest() {
        DXTickStream stream = getTickDb().createStream("clearTest",
            StreamOptions.fixedType(StreamScope.DURABLE, "clearTest", null, 0,
                    StreamConfigurationHelper.mkUniversalBarMessageDescriptor()));

        Date start = null;
        loadData(stream, start, new String[] {"AA1", "AA2", "AA3", "AA4"});

        stream.enableVersioning();

        SelectionOptions options = new SelectionOptions();
        options.versionTracking = true;
        options.allowLateOutOfOrder = true;
        //options.live = true;

        TickCursor cursor = stream.select(Long.MIN_VALUE, options);

        int count = 0;
        while (cursor.next()) {
            InstrumentMessage message = cursor.getMessage();
            if (count == 1500)
                stream.clear(new ConstantIdentityKey("AA1"));

            count++;
            //System.out.println(message);

        }
        cursor.close();
        //System.out.println("Recieved " + count);

        //assertTrue("StreamTruncatedMessage is not recieved!", getMessage);
    }

    @Test
    public void versioningTest() throws InterruptedException {

        String location = TDBRunner.getTemporaryLocation();
        try (DXTickDB db = TickDBCreator.createTickDB(location, true)) {
            db.getStream(TickDBCreator.BARS_STREAM_KEY).enableVersioning();

            RecordClassDescriptor rcd = StreamConfigurationHelper.mkBarMessageDescriptor(null,
                    "",
                    0,
                    FloatDataType.ENCODING_FIXED_DOUBLE,
                    FloatDataType.ENCODING_FIXED_DOUBLE);

            DXTickStream stream = db.createStream("stream", StreamOptions.fixedType(StreamScope.DURABLE, "stream", null, 0, rcd));
            stream.enableVersioning();
            stream.truncate(0);
        }

        try (DXTickDB db = TickDBFactory.create(location)) {
            db.open(false);

            SelectionOptions options = new SelectionOptions();
            options.versionTracking = true;
            options.allowLateOutOfOrder = true;

            DXTickStream stream = db.getStream("stream");
            assertEquals(0, stream.getDataVersion());
        }
    }

    @Test
    public void convertTest() throws InterruptedException {

        DXTickStream stream = getTickDb().createStream("convertTest",
                StreamOptions.fixedType(StreamScope.DURABLE, "clearTest", null, 0,
                        StreamConfigurationHelper.mkUniversalBarMessageDescriptor()));

        loadData(stream, null, new String[] {"AA1", "AA2", "AA3", "AA4"});
        stream.enableVersioning();

        RecordClassDescriptor rcd = StreamConfigurationHelper.mkBarMessageDescriptor(null,
                        "",
                        0,
                        FloatDataType.ENCODING_FIXED_DOUBLE,
                        FloatDataType.ENCODING_FIXED_DOUBLE);

        SelectionOptions options = new SelectionOptions();
        options.versionTracking = true;
        options.allowLateOutOfOrder = true;
        //options.live = true;

        TickCursor cursor = stream.select(Long.MIN_VALUE, options);

        int count = 0;
        while (true) {
            try {
                if (!cursor.next())
                    break;

                InstrumentMessage message = cursor.getMessage();
                if (count == 1500)
                    convert(stream, rcd);

                if (message instanceof SystemMessage)
                    System.out.println(message);

                count++;
                //System.out.println(message);
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                break;
            }
        }
        Util.close(cursor);
    }

    public static void convert(DXTickStream source, RecordClassDescriptor rcd) throws InterruptedException {

        RecordClassSet in = new RecordClassSet ();
        in.addContentClasses(source.getFixedType());
        RecordClassSet out = new RecordClassSet ();
        out.addContentClasses(rcd);

        StreamMetaDataChange change = SchemaAnalyzer.DEFAULT.getChanges
                (in, MetaDataChange.ContentType.Fixed,  out, MetaDataChange.ContentType.Fixed);
        source.execute(new SchemaChangeTask(change));

        BackgroundProcessInfo process;
        boolean complete = false;
        while (!complete) {
            process = source.getBackgroundProcess();
            complete = process != null && process.isFinished();
            Thread.sleep(100);
        }
    }

}
