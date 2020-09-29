package deltix.qsrv.hf.tickdb;

import deltix.qsrv.hf.tickdb.pub.*;
import deltix.qsrv.hf.pub.md.*;
import deltix.util.JUnitCategories;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

@Category(JUnitCategories.TickDBFast.class)
public class Test_StreamMetaData extends TDBTestBase {

    public Test_StreamMetaData() {
        super(true);
    }

    @Test
    public void Test1() {
        DXTickDB tdb = getTickDb();

        IntegerDataType type1 = new IntegerDataType(IntegerDataType.ENCODING_INT32, true, 1, 10);
        FloatDataType type2 = new FloatDataType(FloatDataType.ENCODING_FIXED_FLOAT, true, 1f, 100f);

        final String            name = "Test";
        NonStaticDataField close = new NonStaticDataField("close", "Close", type1);
        close.setDescription("<HTML></HTML>");

        RecordClassDescriptor d2 =  new RecordClassDescriptor (
            name, name, false,
            null,
                close,
            new NonStaticDataField("open", "Open", type2)
        );

        tdb.createStream("Test1", StreamOptions.fixedType(StreamScope.DURABLE, "Test1", null, 0, d2));
        tdb.close();

        tdb.open(false);
        
        DXTickStream stream = tdb.getStream("Test1");
        DataField closeField = stream.getFixedType().getField("close");
        assertEquals(close.getDescription(), closeField.getDescription());
        
        IntegerDataType closeType = (IntegerDataType) closeField.getType();
        FloatDataType openType = (FloatDataType) stream.getFixedType().getField("open").getType();
        
        assertTrue(closeType.getMin().longValue() == 1);
        assertTrue(openType.getMin() instanceof Float);
        assertTrue(openType.getMin().floatValue() == 1);

    }
}
