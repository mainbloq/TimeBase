package deltix.qsrv.hf.codec.cg;

import deltix.qsrv.hf.pub.md.VarcharDataType;
import deltix.qsrv.hf.pub.FwdStringCodec;
import deltix.util.jcg.*;
import static deltix.qsrv.hf.tickdb.lang.compiler.cg.QCGHelpers.*;

/**
 *
 */
public class QStringType extends QPrimitiveType <VarcharDataType> {
    public static final QStringType     NON_NULLABLE =
        new QStringType (new VarcharDataType(VarcharDataType.ENCODING_INLINE_VARSIZE, false, true));

    public static final QStringType     NULLABLE =
        new QStringType (new VarcharDataType(VarcharDataType.ENCODING_INLINE_VARSIZE, true, true));
    
    protected QStringType (VarcharDataType dt) {
        super (dt);
    }

    @Override
    public int                  getEncodedFixedSize () {
        return (SIZE_VARIABLE);
    }

    @Override
    public void                 skip (JExpr input, JCompoundStatement addTo) {
        switch (dt.getEncodingType ()) {
            case VarcharDataType.INLINE_VARSIZE:
                addTo.add (input.call ("skipCharSequence"));
                break;

            case VarcharDataType.FORWARD_VARSIZE:    //TODO:fix (do we have to?)
            case VarcharDataType.ALPHANUMERIC:       //TODO:fix
            default:
                throw new UnsupportedOperationException (
                    "Unimplemented: " + dt.getEncodingType()
                );
        }
    }

    @Override
    public Class<?> getJavaClass() {
        return String.class;
    }

    @Override
    protected JExpr getNullLiteral() {
        return CTXT.nullLiteral();
    }

    @Override
    public JExpr makeConstantExpr(Object obj) {
        return CTXT.stringLiteral((String) obj);
    }

    @Override
    protected JExpr decodeExpr(JExpr input) {
        switch (dt.getEncodingType()) {
            case VarcharDataType.INLINE_VARSIZE:
                return input.call("readString");
            case VarcharDataType.FORWARD_VARSIZE:
                return CTXT.staticCall(FwdStringCodec.class, "readString", input);
            default:
                throw new IllegalStateException("unexpected encoding " + dt.getEncoding());
        }
    }

    @Override
    protected void encodeExpr(JExpr output, JExpr value, JCompoundStatement addTo) {
        switch (dt.getEncodingType()) {
            case VarcharDataType.INLINE_VARSIZE:
                addTo.add(output.call("writeString", value));
                break;
            case VarcharDataType.FORWARD_VARSIZE:
                addTo.add(CTXT.staticCall(FwdStringCodec.class, "write", value, output));
                break;
            default:
                throw new IllegalStateException("unexpected encoding " + dt.getEncoding());
        }
    }

    @Override
    protected void encodeNullImpl(JExpr output, JCompoundStatement addTo) {
        switch (dt.getEncodingType()) {
            case VarcharDataType.INLINE_VARSIZE:
                addTo.add(output.call("writeNullString"));
                break;
            case VarcharDataType.FORWARD_VARSIZE:
                addTo.add(CTXT.staticCall(FwdStringCodec.class, "write", CTXT.nullLiteral(), output));
                break;
            default:
                throw new IllegalStateException("unexpected encoding " + dt.getEncoding());
        }
    }
}
