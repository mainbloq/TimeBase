package deltix.qsrv.hf.tickdb.lang.compiler.cg;

import deltix.qsrv.hf.pub.codec.NestedObjectCodec;
import deltix.qsrv.hf.pub.md.ArrayDataType;
import deltix.qsrv.hf.tickdb.lang.runtime.Instance;
import deltix.util.jcg.JCompoundStatement;
import deltix.util.jcg.JExpr;
import deltix.util.jcg.JStatement;
import deltix.util.jcg.JVariable;

import static deltix.qsrv.hf.tickdb.lang.compiler.cg.QCGHelpers.CTXT;


public class QArrayType extends QType<ArrayDataType> {

    public QArrayType(ArrayDataType dt) {
        super(dt);
    }

    @Override
    public int getEncodedFixedSize() {
        return SIZE_VARIABLE;
    }

    @Override
    public Class<?> getJavaClass() {
        throw new UnsupportedOperationException(
                "Not implemented for " + getClass().getSimpleName()
        );
    }

    @Override
    public QValue               declareValue (
            String                      comment,
            QVariableContainer          container,
            QClassRegistry              registry,
            boolean                     setNull
    )
    {
        JExpr init = CTXT.newExpr (Instance.class);
        JVariable v = container.addVar (comment, true, Instance.class, init);

        return (new QArrayValue (this, container.access (v)));
    }

    @Override
    public JStatement       skip (JExpr input) {
        return (CTXT.staticCall (NestedObjectCodec.class, "skip", input).asStmt ());
    }

    @Override
    public void             moveNoNullCheck (
            QValue                  from,
            QValue                  to,
            JCompoundStatement      addTo
    )
    {
        addTo.add (to.read ().call ("set", from.read ()));
    }

    @Override
    public JExpr getNullLiteral() {
        return CTXT.nullLiteral();
    }

    @Override
    protected void          encodeNullImpl (JExpr output, JCompoundStatement addTo) {
        throw new UnsupportedOperationException ();
        //addTo.add (CTXT.staticCall (BinaryCodec.class, "writeNull", output));
    }

    @Override
    public JStatement       decode (JExpr input, QValue value) {
        //throw new UnsupportedOperationException ();
        return (value.read ().call ("decode", input).asStmt ());
    }

    @Override
    public void             encode (QValue value, JExpr output, JCompoundStatement addTo) {
        //throw new UnsupportedOperationException ();
        addTo.add (value.read ().call ("encode", output));
    }
}
