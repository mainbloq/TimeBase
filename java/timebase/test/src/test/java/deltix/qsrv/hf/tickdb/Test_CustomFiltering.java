package deltix.qsrv.hf.tickdb;

/*  ##TICKDB.FAST## */

import deltix.qsrv.hf.pub.*;
import deltix.qsrv.hf.pub.md.ClassDescriptor;
import deltix.qsrv.hf.tickdb.pub.*;
import deltix.qsrv.testsetup.TickDBCreator;
import deltix.timebase.messages.*;


import org.junit.Test;

import static deltix.util.time.TimeConstants.TIMESTAMP_UNKNOWN;
import static org.junit.Assert.assertTrue;

import org.junit.experimental.categories.Category;
import deltix.util.JUnitCategories.TickDBFast;

/**
 * Date: May 28, 2010
 *
 * @author alex
 */
@Category(TickDBFast.class)
public class Test_CustomFiltering extends TDBTestBase {

    public static class MyBarMessage extends InstrumentMessage {
        public MyBarMessage() {
        }

        @Override
        public String toString() {
            return "MyMessage";
        }

        @SchemaElement(
                title = "Original Time"
        )
        @SchemaType(
                dataType = SchemaDataType.TIMESTAMP
        )
        public long                 originalTimestamp = TIMESTAMP_UNKNOWN;


        @SchemaElement(
                title = "Currency Code"
        )
        public short                currencyCode = 999;

        @SchemaElement(
                title = "Exchange Code"
        )
        @SchemaType(
                encoding = "ALPHANUMERIC(10)",
                dataType = SchemaDataType.VARCHAR
        )
        public long                 exchangeCode = ExchangeCodec.NULL;

        @SchemaElement(
                title = "Close"
        )
        public double               close;

        @RelativeTo("close")
        @SchemaElement(
                title = "Open"
        )
        public double               open;

        @RelativeTo ("close")
        @SchemaElement(
                title = "High"
        )
        public double               high;

        @RelativeTo ("close")
        @SchemaElement(
                title = "Low"
        )
        public double               low;

        @SchemaElement(
                title = "Volume"
        )
        public double               volume;
    }


    public Test_CustomFiltering() {
        super(true);
    }

    @Test
    public void testCustomFiltering() {
        DXTickDB tdb = getTickDb();
        DXTickStream stream = TickDBCreator.createBarsStream (tdb, "test");
        
        SelectionOptions options = new SelectionOptions();
        options.typeLoader = new TypeLoaderImpl(MyBarMessage.class.getClassLoader()) {
            @Override
            public Class load(ClassDescriptor cd) throws ClassNotFoundException {
                if (cd.getName().contains("BarMessage"))
                    return MyBarMessage.class;
                
                return super.load(cd);
            }
        };

        try (TickCursor cursor = TickCursorFactory.create(stream, 0, options, "AAPL")) {

            for (int i = 0; i < 100; i++)
                cursor.next();

            cursor.setTypes(deltix.timebase.api.messages.BarMessage.class.getName());
            cursor.addEntity(new ConstantIdentityKey("ORCL"));
            assertTrue(cursor.next());
        }
    }
}
