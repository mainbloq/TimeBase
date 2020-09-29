package deltix.qsrv.hf.codec.cg;

import deltix.qsrv.hf.pub.codec.AlphanumericCodec;
import deltix.qsrv.hf.pub.md.VarcharDataType;
import deltix.util.jcg.JCompoundStatement;
import deltix.util.jcg.JExpr;

import static deltix.qsrv.hf.tickdb.lang.compiler.cg.QCGHelpers.CTXT;

public class QAlphanumericType extends QPrimitiveType <VarcharDataType> {

    public QAlphanumericType(VarcharDataType dt) {
        super(dt);
        if (dt.getEncodingType() != VarcharDataType.ALPHANUMERIC)
            throw new IllegalArgumentException("invalid encoding " + dt.getEncoding());
    }

    @Override
    public int getEncodedFixedSize() {
        return SIZE_VARIABLE;
    }

    @Override
    public void skip(JExpr input, JCompoundStatement addTo) {
        addTo.add(CTXT.staticCall(AlphanumericCodec.class, "skip", input, CTXT.intLiteral(dt.getLength())));
    }

    @Override
    protected void encodeNullImpl(JExpr output, JCompoundStatement addTo) {
        addTo.add(CTXT.staticCall(AlphanumericCodec.class, "writeNull", output, CTXT.intLiteral(dt.getLength())));
    }

    @Override
    public Class<?> getJavaClass() {
        throw new UnsupportedOperationException("Not implemented for QAlphanumericType");
    }

    @Override
    protected JExpr getNullLiteral() {
        throw new UnsupportedOperationException("Not implemented for QAlphanumericType");
    }
}
