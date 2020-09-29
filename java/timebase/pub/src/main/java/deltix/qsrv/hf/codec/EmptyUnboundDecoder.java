package deltix.qsrv.hf.codec;

import deltix.qsrv.hf.pub.NullValueException;
import deltix.qsrv.hf.pub.ReadableValue;
import deltix.qsrv.hf.pub.codec.NonStaticFieldInfo;
import deltix.qsrv.hf.pub.codec.RecordClassInfo;
import deltix.qsrv.hf.pub.codec.RecordLayout;
import deltix.qsrv.hf.pub.codec.UnboundDecoder;
import deltix.qsrv.hf.pub.codec.validerrors.ValidationError;
import deltix.util.memory.MemoryDataInput;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Date: Feb 18, 2010
 * @author BazylevD
 */
public final class EmptyUnboundDecoder implements UnboundDecoder {
    private final RecordLayout layout;

    public EmptyUnboundDecoder(RecordLayout layout) {
        this.layout = layout;
    }

    @Override
    public void beginRead(MemoryDataInput in) {
    }

    @Override
    public RecordClassInfo getClassInfo() {
        return layout;
    }

    @Override
    public boolean nextField() {
        return false;
    }

    @Override
    public NonStaticFieldInfo getField() {
        return null;
    }

    @Override
    public boolean previousField() {
        return false;
    }

    @Override
    public boolean seekField(int index) {
        return false;
    }

    @Override
    public int comparePrimaryKeys(MemoryDataInput in1, MemoryDataInput in2) {
        return 0;
    }

    @Override
    public int compareAll(MemoryDataInput in1, MemoryDataInput in2) {
        return 0;
    }

    @Override
    public ValidationError validate () {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean getBoolean() throws NullValueException {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getChar() throws NullValueException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getInt() throws NullValueException {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getLong() throws NullValueException {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getFloat() throws NullValueException {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getDouble() throws NullValueException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getString() throws NullValueException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getArrayLength() throws NullValueException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ReadableValue nextReadableElement() throws NullValueException {
        throw new UnsupportedOperationException();
    }

    @Override
    public UnboundDecoder getFieldDecoder() throws NullValueException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getBinaryLength() throws NullValueException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getBinary(int offset, int length, OutputStream out) throws NullValueException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getBinary(int srcOffset, int length, byte[] dest, int destOffset) throws NullValueException {
        throw new UnsupportedOperationException();
    }

    @Override
    public InputStream openBinary() throws NullValueException {
        throw new UnsupportedOperationException();
    }
}
