package deltix.qsrv.hf.pub.codec.intp;

import deltix.qsrv.hf.pub.codec.NonStaticFieldLayout;
import deltix.qsrv.hf.pub.codec.validerrors.*;
import deltix.qsrv.hf.pub.md.*;
import deltix.util.lang.MathUtil;

import java.lang.reflect.InvocationTargetException;

/**
 *
 */
abstract class IntegerFieldDecoder extends FieldDecoder {
    private final long        min;
    private final long        max;
    
    IntegerFieldDecoder (NonStaticFieldLayout f) {
        super (f);
        
        DataType            type = f.getType ();
        
        if (type instanceof IntegerDataType) {
            IntegerDataType idt = (IntegerDataType) type;
            
            min = idt.getMinNotNull ().longValue ();
            max = idt.getMaxNotNull ().longValue ();
        }      
        else {
            min = Long.MIN_VALUE;
            max = Long.MAX_VALUE;
        }
    }

    @Override
    public abstract long    getLong (DecodingContext ctxt);

    @Override
    public abstract void    skip (DecodingContext ctxt);

    @Override
    public int              compare (DecodingContext ctxt1, DecodingContext ctxt2) {
        final long v1 = getLong(ctxt1);
        assert isNullable || !isNull(v1) : getNotNullableMsg();
        final long v2 = getLong(ctxt2);
        assert isNullable || !isNull(v2) : getNotNullableMsg();
        return (MathUtil.compare(v1, v2));
    }

    @Override
    final protected void copy (DecodingContext ctxt, Object obj)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        long        value = getLong (ctxt);
        assert isNullable || !isNull(value) : getNotNullableMsg();
        setValue(obj, value);
    }

    @Override
    public boolean isNull(DecodingContext ctxt) {
        return isNull(getLong(ctxt));
    }

    @Override
    public byte             getByte (DecodingContext ctxt) {
        return (byte) getLong (ctxt);
    }

    @Override
    public int              getInt (DecodingContext ctxt) {
        return (int) getLong (ctxt);
    }

    @Override
    public short            getShort (DecodingContext ctxt) {
        return (short) getLong (ctxt);
    }

    @Override
    public String           getString (DecodingContext ctxt) {
        final long v = getLong(ctxt);
        return isNull(v) ? null : String.valueOf(v);
    }

    protected void setValue(Object obj, long value) throws IllegalAccessException, InvocationTargetException {
        if (fieldType == int.class)
            setter.setInt(obj, (int) value);
        else if (fieldType == long.class)
            setter.setLong(obj, value);
        else if (fieldType == short.class)
            setter.setShort(obj, (short) value);
        else if (fieldType == byte.class)
            setter.setByte(obj, (byte) value);
        else
            throw new RuntimeException(fieldType.getName());
    }
    
    @Override
    public ValidationError      validate (DecodingContext ctxt) {
        final int           offset = ctxt.in.getCurrentOffset ();        
        final long          v = getLong(ctxt);
        
        if (isNull (v)) {
            if (!isNullable)
                return (new IllegalNullValue (offset, fieldInfo));        
        }
        else if (v < min || v > max)
            return (new IntegerFieldOutOfRange (offset, fieldInfo, v, min, max));
        
        return (null);
    }
}
