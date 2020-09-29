package deltix.qsrv.hf.pub.codec.intp;

import deltix.qsrv.hf.pub.codec.*;
import deltix.qsrv.hf.pub.md.IntegerDataType;

import java.lang.reflect.InvocationTargetException;

/**
 *
 */
class IntTimeIntervalFieldDecoder extends IntegerFieldDecoder {
    public IntTimeIntervalFieldDecoder (NonStaticFieldLayout f) {
        super (f);
    }

    @Override
    public long         getLong (DecodingContext ctxt) {
        return (deltix.qsrv.hf.pub.codec.TimeIntervalCodec.read(ctxt.in));
    }

    @Override
    public void         skip (DecodingContext ctxt) {
        deltix.qsrv.hf.pub.codec.TimeIntervalCodec.read(ctxt.in);
    }

    @Override
    public boolean isNull(long value) {
        return value == IntegerDataType.PINTERVAL_NULL;
    }

    @Override
    protected void setNull(Object obj) throws IllegalAccessException, InvocationTargetException {
        setValue(obj, IntegerDataType.PINTERVAL_NULL);
    }
}
