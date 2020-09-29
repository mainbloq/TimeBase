package deltix.qsrv.hf.tickdb.lang.compiler.cg;

import deltix.qsrv.hf.tickdb.lang.runtime.QRT;
import deltix.qsrv.hf.pub.md.BooleanDataType;
import deltix.util.jcg.*;
import static deltix.qsrv.hf.tickdb.lang.compiler.cg.QCGHelpers.*;

/**
 *
 */
public class QBooleanType extends QType <BooleanDataType> {
    public static final QBooleanType     NON_NULLABLE =
        new QBooleanType (new BooleanDataType (false));

    public static final QBooleanType     NULLABLE =
        new QBooleanType (new BooleanDataType (true));

    public static final JExpr      N_TRUE = CTXT.intLiteral (1).cast (byte.class);
    public static final JExpr      N_FALSE = CTXT.intLiteral (0).cast (byte.class);
    public static final JExpr      NULL = CTXT.intLiteral (-1).cast (byte.class);

    public static boolean isNullableBoolean (QType type) {
        return ((type instanceof QBooleanType) && type.isNullable ());
    }

    protected QBooleanType (BooleanDataType dt) {
        super (dt);
    }

    @Override
    public Class <?>            getJavaClass () {
        return (byte.class);
    }

    public static JExpr         getLiteral (boolean test) {
        return (test ? N_TRUE : N_FALSE);
    }

    @Override
    public JExpr                getNullLiteral () {
        return (NULL);
    }

    @Override
    public int                  getEncodedFixedSize () {
        return (1);
    }

    @Override
    protected void              encodeNullImpl (JExpr output, JCompoundStatement addTo) {
        addTo.add (output.call ("writeByte", NULL));
    }

    @Override
    public JStatement           decode (JExpr input, QValue value) {
        return (value.write (input.call ("readByte")));
    }

    @Override
    public void                 encode (QValue value, JExpr output, JCompoundStatement addTo) {
        addTo.add (output.call ("writeByte", value.read ()));
    }
   
    @Override
    public JExpr                makeConstantExpr (Object obj) {
        return (CTXT.intLiteral (((Boolean) obj) ? 1 : 0).cast (byte.class));
    }

    /*
     * Macro-methods
     */
    public static JExpr         cleanToNullable (JExpr boolExpr) {
        return (CTXT.condExpr(boolExpr, N_TRUE, N_FALSE));
    }

    /**
     *  Converts a nullable boolean (byte) to clean (boolean), given that the
     *  byte is definitely not NULL (-1).
     * 
     * @param nbExpr nullable boolean expression
     * @return boolean expression
     */
    public static JExpr         nullableToClean (JExpr nbExpr) {
        return (CTXT.binExpr (nbExpr, "==", N_TRUE));
    }

    public static void          genNotOp (
        QValue                      arg,
        QValue                      out,
        JCompoundStatement          addTo
    )
    {
        JExpr               argExpr = arg.read ();
        
        addTo.add (out.write (CTXT.staticCall (QRT.class, "bnot", argExpr)));
    }
    
    public static void          genBooleanOp (
        QValue                      left,
        String                      op,
        QValue                      right,
        QValue                      out,
        JCompoundStatement          addTo
    )
    {
        JExpr               leftArg = left.read ();
        JExpr               rightArg = right.read ();

        addTo.add (out.write (CTXT.staticCall (QRT.class, op, leftArg, rightArg)));
    }        
}
