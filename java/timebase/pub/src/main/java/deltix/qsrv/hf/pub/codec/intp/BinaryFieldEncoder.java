package deltix.qsrv.hf.pub.codec.intp;

import deltix.qsrv.hf.codec.BinaryCodec;
import deltix.qsrv.hf.pub.codec.NonStaticFieldLayout;
import deltix.qsrv.hf.pub.md.BinaryDataType;
import deltix.util.collections.generated.ByteArrayList;
import rtmath.containers.interfaces.BinaryArrayReadOnly;

import java.lang.reflect.InvocationTargetException;

/**
 * User: BazylevD
 * Date: Dec 1, 2009
 */
class BinaryFieldEncoder extends FieldEncoder {
    private final int compressionLevel;
    private final int maxSize;

    BinaryFieldEncoder(NonStaticFieldLayout f, int maxSize, int compressionLevel) {
        super(f);
        this.maxSize = maxSize;
        this.compressionLevel = compressionLevel;
    }

    @Override
    final protected void copy(Object obj, EncodingContext ctxt) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        final Class<?> fc = this.fieldType;
        if (fc == ByteArrayList.class) {
            ByteArrayList data = (ByteArrayList) getter.get(obj);
            if (data == null)
                writeNull(ctxt);
            else {
                setBinary(data.getInternalBuffer(), 0, data.size(), ctxt);
            }
        } else if (fc == byte[].class) {
            final byte[] data = (byte[]) getter.get(obj);
            if (data == null)
                writeNull(ctxt);
            else {
                setBinary(data, 0, data.length, ctxt);
            }
        } else if (fc.isAssignableFrom(BinaryArrayReadOnly.class)) {
            BinaryArrayReadOnly data = (BinaryArrayReadOnly) getter.get(obj);
            if (data == null)
                writeNull(ctxt);
            else {
                checkLimit(data.size());
                BinaryCodec.write(data, 0, data.size(), ctxt.out, compressionLevel);
            }
        }
        else
            throw new IllegalArgumentException("Type " + fc + " is not supported.");
    }

    @Override
    void writeNull(EncodingContext ctxt) {
        if (!isNullable)
            throwNotNullableException();
        else
            BinaryCodec.writeNull(ctxt.out);
    }

    @Override
    void setString(CharSequence value, EncodingContext ctxt) {
        throw new UnsupportedOperationException();
    }

    private void        checkLimit(int length) {
        if (length > maxSize && maxSize != BinaryDataType.UNLIMITED_SIZE)
            throw new IllegalArgumentException(String.format("An attempt to write more data then specified by maxSize: %d > %d", length, maxSize));
    }

    @Override
    void setBinary(byte[] data, int offset, int length, EncodingContext ctxt) {
        checkLimit(length);

        BinaryCodec.write(data, offset, length, ctxt.out, compressionLevel);
    }

    @Override
    protected boolean isNullValue(Object message) throws IllegalAccessException, InvocationTargetException {
        return getter.get(message) == null;
    }
}
