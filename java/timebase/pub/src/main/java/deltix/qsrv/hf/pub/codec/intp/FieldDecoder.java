package deltix.qsrv.hf.pub.codec.intp;

import deltix.qsrv.hf.pub.NullValueException;
import deltix.qsrv.hf.pub.ReadableValue;
import deltix.qsrv.hf.pub.codec.NonStaticFieldInfo;
import deltix.qsrv.hf.pub.codec.NonStaticFieldLayout;
import deltix.qsrv.hf.pub.codec.UnboundDecoder;
import deltix.qsrv.hf.pub.codec.validerrors.IllegalNullValue;
import deltix.qsrv.hf.pub.codec.validerrors.ValidationError;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 *
 */
public abstract class FieldDecoder {
    protected final ValueSetter setter;
    protected final Class <?>   fieldType;
    protected final boolean     isNullable;
    private final String        fieldName;
    private final String        fieldDescription;
    protected final NonStaticFieldInfo fieldInfo;

    protected FieldDecoder (NonStaticFieldLayout f) {
        this.fieldInfo = f;

        final Field field = f.getJavaField ();
        if (f.isBound()) {
            if (f.hasAccessMethods()) {
                fieldName = f.getName();
                fieldDescription = f.toString();
                fieldType = f.getSetterType();
            } else {
                fieldName = field.getName();
                fieldDescription = field.toGenericString();
                fieldType = field.getType();
            }
        } else {
            fieldName = f.getName();
            fieldDescription = f.toString();
            fieldType = null;
        }

        isNullable = f.getType().isNullable();

        if (f.hasAccessMethods())
            setter = new JavaValueSetterMethod(f);
        else
            setter = field != null ? new JavaValueSetter(field) : null;
    }

    public boolean      isNullable() {
        return isNullable;
    }

    abstract void    skip (DecodingContext ctxt);

    protected abstract void    copy (DecodingContext ctxt, Object obj)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException;

    protected abstract void    setNull (Object obj)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException;

    protected  void            setNull (Object obj, int idx) {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    boolean          getBoolean (DecodingContext ctxt) {
        throw new UnsupportedOperationException ("Not supported for " + getClass().getSimpleName());
    }

    char             getChar (DecodingContext ctxt) {
        throw new UnsupportedOperationException ("Not supported for " + getClass().getSimpleName());
    }

    byte             getByte (DecodingContext ctxt) {
        return ctxt.in.readByte();
    }

    short            getShort (DecodingContext ctxt) {
        throw new UnsupportedOperationException ("Not supported for " + getClass().getSimpleName());
    }

    int              getInt (DecodingContext ctxt) {
        throw new UnsupportedOperationException ("Not supported for " + getClass().getSimpleName());
    }

    long             getLong (DecodingContext ctxt) {
        throw new UnsupportedOperationException ("Not supported for " + getClass().getSimpleName());
    }

    float            getFloat (DecodingContext ctxt) {
        throw new UnsupportedOperationException ("Not supported for " + getClass().getSimpleName());
    }

    double           getDouble (DecodingContext ctxt) {
        throw new UnsupportedOperationException ("Not supported for " + getClass().getSimpleName());
    }

    int              getArrayLength() {
        throw new UnsupportedOperationException("Not supported for " + getClass().getSimpleName());
    }

    ReadableValue    nextReadableElement() {
        throw new UnsupportedOperationException("Not supported for " + getClass().getSimpleName());
    }

    UnboundDecoder getFieldDecoder() throws NullValueException {
        throw new UnsupportedOperationException("Not supported for " + getClass().getSimpleName());
    }

    int              getBinaryLength (DecodingContext ctxt) throws NullValueException {
        throw new UnsupportedOperationException ("Not supported for " + getClass().getSimpleName());
    }

    void             getBinary (int offset, int length, OutputStream out, DecodingContext ctxt) throws NullValueException {
        throw new UnsupportedOperationException ("Not supported for " + getClass().getSimpleName());
    }

    void             getBinary (byte[] data, int srcOffset, int destOffset, int length, DecodingContext ctxt) throws NullValueException {
        throw new UnsupportedOperationException ("Not supported for " + getClass().getSimpleName());
    }

    InputStream      openBinary (DecodingContext ctxt) {
        throw new UnsupportedOperationException ("Not supported for " + getClass().getSimpleName());
    }

    abstract String  getString (DecodingContext ctxt);

    abstract int     compare (
        DecodingContext         ctxt1,
        DecodingContext         ctxt2
    );

    public abstract boolean isNull(DecodingContext ctxt);

    public boolean isNull(long value) {
        throw new UnsupportedOperationException ("Not supported for " + getClass().getSimpleName());
    }

    public boolean isNull(double value) {
        throw new UnsupportedOperationException ("Not supported for " + getClass().getSimpleName());
    }

    public boolean isNull(String value) {
        return value == null;
    }

    protected String getNotNullableMsg() {
        return "'" + fieldName + "' field is not nullable";
    }

    @Override
    public String   toString () {
        return (getClass ().getSimpleName () + " for " + fieldDescription);
    }

    boolean isBound() {
        return setter != null;
    }

    public ValidationError      validate (DecodingContext ctxt) {
        if (isNullable)
            skip (ctxt);
        else {
            final int         offset = ctxt.in.getCurrentOffset ();
            
            if (isNull (ctxt))
                return (new IllegalNullValue (offset, fieldInfo));
        }
        
        return (null);
    }
}
