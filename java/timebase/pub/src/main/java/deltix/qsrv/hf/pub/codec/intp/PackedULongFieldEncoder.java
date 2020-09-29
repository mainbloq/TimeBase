package deltix.qsrv.hf.pub.codec.intp;

import deltix.qsrv.hf.pub.codec.NonStaticFieldLayout;
import deltix.qsrv.hf.pub.md.IntegerDataType;
import deltix.qsrv.hf.codec.CodecUtils;
import deltix.util.text.CharSequenceParser;

/**
 *
 */
class PackedULongFieldEncoder extends IntegerFieldEncoder {
    public PackedULongFieldEncoder (NonStaticFieldLayout f) {
        super (f);
    }

    @Override
    void                    writeNull(EncodingContext ctxt) {
        setLong(IntegerDataType.PUINT61_NULL, ctxt);
    }

    @Override
    protected boolean isNull(long value) {
        return value == IntegerDataType.PUINT61_NULL; 
    }

    @Override
    void                    setString (CharSequence value, EncodingContext ctxt) {
        setLong (CharSequenceParser.parseLong (value), ctxt);
    }

    @Override
    void                    setLongImpl (long value, EncodingContext ctxt) {
        CodecUtils.writePackedUnsignedLong(value, ctxt.out);
    }    
}
