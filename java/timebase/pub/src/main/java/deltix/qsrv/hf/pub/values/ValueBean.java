package deltix.qsrv.hf.pub.values;

import deltix.qsrv.hf.pub.*;
import deltix.qsrv.hf.pub.codec.UnboundDecoder;
import deltix.qsrv.hf.pub.codec.UnboundEncoder;
import deltix.qsrv.hf.pub.md.*;
import java.io.*;

/**
 *  Default implementation for value beans.
 */
public abstract class ValueBean
    implements ReadableValue, WritableValue, Serializable
{
    private static final long serialVersionUID = 1L;
    
    public static ValueBean forType (DataType type) {
        Class <?>   tc = type.getClass ();

        if (tc == IntegerDataType.class)
            return (new IntegerValueBean ((IntegerDataType) type));

        if (tc == FloatDataType.class) {
            FloatDataType   fdt = (FloatDataType) type;

            return (fdt.isFloat () ? new FloatValueBean (fdt) : new DoubleValueBean (fdt));
        }

        if (tc == VarcharDataType.class)
            return (new StringValueBean ((VarcharDataType) type));

        throw new UnsupportedOperationException ("Not yet implemented for " + tc.getSimpleName ());
    }

    public final DataType   type;

    protected ValueBean (DataType type) {
        this.type = type;
    }

    public void             checkNotNull () {
        if (isNull ())
            throw NullValueException.INSTANCE;
    }

    public void             getBinary (
        int                     offset,
        int                     length,
        OutputStream            out
    )
        throws NullValueException
    {
        throw new UnsupportedOperationException ("Illegal");
    }

    public void             getBinary (
        int                     srcOffset,
        int                     length,
        byte []                 dest,
        int                     destOffset
    )
        throws NullValueException
    {
        throw new UnsupportedOperationException ("Illegal");
    }

    public int              getBinaryLength () throws NullValueException {
        throw new UnsupportedOperationException ("Illegal");
    }

    public boolean          getBoolean () throws NullValueException {
        throw new UnsupportedOperationException ("Illegal");
    }

    public char             getChar() throws NullValueException {
        throw new UnsupportedOperationException ("Illegal");
    }

    public double           getDouble () throws NullValueException {
        throw new UnsupportedOperationException ("Illegal");
    }

    public float            getFloat () throws NullValueException {
        throw new UnsupportedOperationException ("Illegal");
    }

    public int              getInt () throws NullValueException {
        throw new UnsupportedOperationException ("Illegal");
    }

    public long             getLong () throws NullValueException {
        throw new UnsupportedOperationException ("Illegal");
    }

    @Override
    public int getArrayLength() throws NullValueException {
        throw new UnsupportedOperationException ("Illegal");
    }

    @Override
    public ReadableValue nextReadableElement() throws NullValueException {
        throw new UnsupportedOperationException ("Illegal");
    }

    @Override
    public UnboundDecoder getFieldDecoder() throws NullValueException {
        throw new UnsupportedOperationException ("Illegal");
    }

    public InputStream      openBinary () throws NullValueException {
        throw new UnsupportedOperationException ("Illegal");
    }

    public void             writeBinary (byte[] data, int offset, int length) {
        throw new UnsupportedOperationException ("Illegal");
    }

    public void             writeBoolean (boolean value) {
        throw new UnsupportedOperationException ("Illegal");
    }

    @Override
    public void writeChar(char value) {
        throw new UnsupportedOperationException ("Illegal");
    }

    public void             writeDouble (double value) {
        throw new UnsupportedOperationException ("Illegal");
    }

    public void             writeFloat (float value) {
        throw new UnsupportedOperationException ("Illegal");
    }

    public void             writeInt (int value) {
        throw new UnsupportedOperationException ("Illegal");
    }

    public void             writeLong (long value) {
        throw new UnsupportedOperationException ("Illegal");
    }

    @Override
    public void setArrayLength(int len) {
        throw new UnsupportedOperationException ("Illegal");
    }

    @Override
    public WritableValue nextWritableElement() {
        throw new UnsupportedOperationException ("Illegal");
    }

    @Override
    public UnboundEncoder getFieldEncoder(RecordClassDescriptor rcd) {
        throw new UnsupportedOperationException ("Illegal");
    }

    protected abstract Object getBoxedValue();
    
    @Override
    public String           toString () {
        return (type.toString (getBoxedValue()));
    }
}
