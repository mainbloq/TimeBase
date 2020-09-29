package deltix.qsrv.hf.tickdb;

import deltix.timebase.api.messages.BarMessage;
import deltix.qsrv.hf.pub.md.NonStaticDataField;
import deltix.qsrv.hf.pub.md.RecordClassDescriptor;
import deltix.qsrv.hf.pub.md.VarcharDataType;
import deltix.qsrv.hf.tickdb.pub.*;
import deltix.util.time.TimeKeeper;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import org.junit.experimental.categories.Category;
import deltix.util.JUnitCategories.TickDBFast;

/**
 *
 */
@Category(TickDBFast.class)
public class Test_TypeSet extends TDBTestBase {

    public Test_TypeSet() {
        super(true, true);
    }

    public static class MyMessage extends BarMessage {
        public MyMessage() {
        }

        public String ratio;
    }

    @Test
    public void testSelect() {

        DXTickDB tickDb = getTickDb();

        StreamOptions options = new StreamOptions (StreamScope.DURABLE, "bars", null, 0);
        RecordClassDescriptor bar = StreamConfigurationHelper.mkUniversalBarMessageDescriptor();
        RecordClassDescriptor extened = new RecordClassDescriptor(MyMessage.class.getName(), "extended" , false, bar,
                new NonStaticDataField("ratio", "ratio",
                        new VarcharDataType(VarcharDataType.ENCODING_INLINE_VARSIZE, true, true)));

        options.setPolymorphic(bar, extened);

        DXTickStream stream = tickDb.createStream("extended", options);

        TickLoader loader = stream.createLoader();

        BarMessage msg = new MyMessage();
        msg.setTimeStampMs(TimeKeeper.currentTime);
        ((MyMessage)msg).ratio = "1.0";
        msg.setOpen(10.1);
        msg.setClose(10.1);
        loader.send(msg);

        msg = new BarMessage();
        msg.setTimeStampMs(TimeKeeper.currentTime + 1);
        msg.setOpen(10.1);
        msg.setClose(0.11);
        loader.send(msg);

        loader.close();
        
        TickCursor cursor = stream.select(Long.MIN_VALUE, null);

        try {
            assertTrue(cursor.next());
            assertTrue(cursor.getMessage() instanceof MyMessage);
            assertTrue(cursor.next());
            assertTrue(cursor.getMessage() instanceof BarMessage);
        } finally {
            cursor.close();
        }


    }
}
