package deltix.qsrv.hf.tickdb.lang.compiler.cg;

import deltix.qsrv.hf.tickdb.lang.runtime.QRT;
import deltix.qsrv.hf.pub.md.*;
import deltix.qsrv.hf.tickdb.lang.compiler.sem.StdEnvironment;
import deltix.qsrv.hf.tickdb.lang.compiler.sx.CompiledExpression;
import deltix.util.jcg.*;

import static deltix.qsrv.hf.tickdb.lang.compiler.cg.QCGHelpers.CTXT;

/**
 *  The superclass of any data value type. QTypes are used to create QValues
 *  and to generate code for operations that do not involve QValues.
 */
public abstract class QType <T extends DataType> {
    public static final int     SIZE_VARIABLE = -1;

//    public static final QType   INSTRUMENT_TYPE =
//        new QEnumType (StdEnvironment.INSTR_TYPE_ENUM);
    
    public static QType     forDataType (DataType type) {
        Class <?>               tc = type.getClass ();

        if (tc == IntegerDataType.class)
            return (new QIntegerType ((IntegerDataType) type));

        if (tc == BooleanDataType.class)
            return (new QBooleanType ((BooleanDataType) type));

        if (tc == VarcharDataType.class)
            return (new QVarcharType ((VarcharDataType) type));

        if (tc == FloatDataType.class)
            return (new QFloatType ((FloatDataType) type));

        if (tc == DateTimeDataType.class)
            return (new QDateTimeType ((DateTimeDataType) type));

        if (tc == EnumDataType.class)
            return (new QEnumType ((EnumDataType) type));

        if (tc == CharDataType.class)
            return (new QCharType ((CharDataType) type));

        if (tc == TimeOfDayDataType.class)
            return (new QTimeOfDayType ((TimeOfDayDataType) type));

        if (tc == BinaryDataType.class)
            return (new QBinaryType ((BinaryDataType) type));

        if (tc == QueryDataType.class)
            return (new QQueryType ((QueryDataType) type));
        
        if (tc == ClassDataType.class)
            return (new QObjectType ((ClassDataType) type));

        if (tc == ArrayDataType.class)
            return (new QArrayType((ArrayDataType) type));
                
        throw new UnsupportedOperationException (tc.getSimpleName ());
    }

    public static QType     forExpr (CompiledExpression e) {
        return (forDataType (e.type));
    }

    public final T      dt;

    protected QType (T dt) {
        this.dt = dt;
    }

    public void                 moveNoNullCheck (
        QValue                      from,
        QValue                      to,
        JCompoundStatement          addTo
    )
    {
        addTo.add (to.write (from.read ()));
    }
    
    public void                 move (
        QValue                      from,
        QValue                      to,
        JCompoundStatement          addTo
    )
    {
        if (from.type.isNullable () && !to.type.isNullable ())
            addTo.add (
                CTXT.ifStmt (
                    from.readIsNull (true),
                    QCGHelpers.throwNVX ()
                )
            );

        moveNoNullCheck (from, to, addTo);
    }

    public JStatement           skip (JExpr input) {
        throw new UnsupportedOperationException (
            getEncodedFixedSize () == SIZE_VARIABLE ?
                "Unimplemented for " + getClass().getSimpleName() :
                "Fixed size - should have been skipped by #bytes"            
        );
    }

    /**
     *  Returns whether the underlying value is nullable.
     */
    public final boolean        isNullable () {
        return dt.isNullable ();
    }

    /**
     *  Returns the size, if fixed, or SIZE_VARIABLE if the size is variable.
     */
    public abstract int         getEncodedFixedSize ();

    /**
     *  Generates decoding code.
     * 
     *  @param input    MemoryDataInput instance
     *  @param value    Output value.
     *  @return         A single or compound statement.
     */
    public abstract JStatement  decode (
        JExpr                       input,
        QValue                      value
    );

    public abstract void        encode (
        QValue                      value, 
        JExpr                       output,
        JCompoundStatement          addTo
    );
    
    public abstract Class <?>   getJavaClass ();
    
    public boolean              instanceAllocatesMemory () {
        return (false);
    }
    
    public JExpr                checkNull (JExpr e, boolean eq) {
        return (CTXT.binExpr (e, eq ? "==" : "!=", getNullLiteral ()));
    }
    
    /**
     *  Declares a variable in the supplied container.
     * 
     *  @param container    Place to declare a variable.
     *  @param registry     Class registry, in case it's needed.
     *  @param setNull      If the variable should be initialized to NULL.
     * 
     *  @return     A QValue instance representing the variable
     */
    public QValue               declareValue (
        String                      comment,
        QVariableContainer          container, 
        QClassRegistry              registry,
        boolean                     setNull
    )
    {
        JVariable       v = 
            container.addVar (
                comment,
                false, 
                getJavaClass (), 
                setNull ? getNullLiteral () : null
            );
        
        return (new QExprValue (this, container.access (v)));
    }

    /**
     *  Creates a constant value, if supported.
     * 
     *  @param obj  The actual value of the constant. Exact type required
     *              is implementation-dependent.
     *  @return     A QValue instance representing the constant.
     */
    public final QValue         makeConstant (Object obj) {
        return (new QExprValue (this, obj == null ? getNullLiteral () : makeConstantExpr (obj)));
    }

    /**
     *  Creates a constant expression, if supported.
     * 
     *  @param obj  The actual value of the constant. Exact type required
     *              is implementation-dependent. Never null.
     *  @return     A JExpr that can be assigned.
     */
    protected JExpr             makeConstantExpr (Object obj) {
        throw notImplemented ();
    }

    /**
     *  Generate code for writing a null into the data output. This method
     *  asserts that the type is nullable and then calls {@link #encodeNullImpl}.
     * 
     *  @param output       MemoryDataOutput instance.
     *  @param addTo        Compound statement to add code to.
     */
    public final void           encodeNull (
        JExpr                       output,
        JCompoundStatement          addTo
    )
    {
        if (isNullable ())
            encodeNullImpl(output, addTo);
        else
            throw new IllegalStateException ("type is not Nullable " + dt.getBaseName());
    }

    /**
     *  Override this method to do useful work.
     * 
     *  @param output       MemoryDataOutput instance.
     *  @param addTo        Compound statement to add code to.
     */
    protected void              encodeNullImpl (
        JExpr                       output,
        JCompoundStatement          addTo
    ) 
    {
        throw notImplemented ();
    }

    protected UnsupportedOperationException notImplemented () {
        throw new UnsupportedOperationException (
            "Not implemented for " + getClass ().getSimpleName ()
        );
    }
    
    public abstract JExpr       getNullLiteral ();
    
    /**
     *  Compute s, unless arg is null, in which case if out is nullable, set out
     *  to null, otherwise throw NVX.
     * 
     *  @param s        Statement to compute if arg value is not null.
     *  @param arg      The arg value to check for null.
     *  @param out      Set it to null if it's nullable
     * 
     *  @return     Expression with the result.
     */
    public static JStatement    wrapWithNullCheck (
        JStatement                  s,
        QValue                      arg,
        QValue                      out
    )
    {
        if (arg.type.isNullable ()) 
            return (
                CTXT.ifStmt (
                    arg.readIsNull (true),
                    out.type.isNullable () ? 
                        out.write (out.type.getNullLiteral ()) : 
                        QCGHelpers.throwNVX (),
                    s
                )
            );                    

        return (s);
    }

    public static void          genUnOp (
        String                      op,
        QValue                      arg,
        QValue                      out,
        JCompoundStatement          addTo
    )
    {
        JExpr               argExpr = arg.read ();
        JExpr               e;
        
        if (op.startsWith ("QRT."))
            e = CTXT.staticCall (QRT.class, op.substring (4), argExpr);
        else
            throw new RuntimeException (op);
        
        addTo.add (wrapWithNullCheck (out.write (e), arg, out));
    }
    
    public static void          genBinOp (
        QValue                      left,
        String                      op,
        QValue                      right,
        QValue                      out,
        JCompoundStatement          addTo
    )
    {
        JExpr               leftArg = left.read ();
        JExpr               rightArg = right.read ();

        JExpr               e;
        
        if (op.startsWith ("QRT."))
            e = CTXT.staticCall (QRT.class, op.substring (4), leftArg, rightArg);
        else {
            e = CTXT.binExpr (leftArg, op, rightArg);
            
            if (op.equals ("==") || op.equals ("!=") || op.equals (">") || 
                op.equals ("<") || op.equals (">=") || op.equals ("<="))
                e = CTXT.staticCall (QRT.class, "bpos", e);
        }
        
        JStatement          s = out.write (e);
        
        s = wrapWithNullCheck (s, left, out);
        s = wrapWithNullCheck (s, right, out);

        addTo.add (s);
    }
    
    public static void          genEqOp (
        QValue                      left,
        String                      op,
        boolean                     positive,
        QValue                      right,
        QValue                      out,
        JCompoundStatement          addTo
    )
    {
        if (!(out.type instanceof QBooleanType))
            throw new IllegalArgumentException (
                "output type is not BOOLEAN: " + out.type
            );
        
        boolean             leftIsNullable = left.type.isNullable ();
        boolean             rightIsNullable = right.type.isNullable ();
                        
        JExpr               leftArg = left.read ();
        JExpr               rightArg = right.read ();

        JExpr               e;
        
        if (op.startsWith ("QRT."))
            e = CTXT.staticCall (QRT.class, op.substring (4), leftArg, rightArg);
        else {
            e = CTXT.binExpr (leftArg, op, rightArg);
            
            if (op.equals ("==") || op.equals ("!=") || op.equals (">") || 
                op.equals ("<") || op.equals (">=") || op.equals ("<="))
                e = CTXT.staticCall (QRT.class, "bpos", e);
        }
        
        JStatement          s = out.write (e);
        JExpr               negative = QBooleanType.getLiteral (!positive);
        
        if (leftIsNullable) {
            if (rightIsNullable) {
                s = 
                    CTXT.ifStmt (
                        left.readIsNull (true),
                        out.write (QBooleanType.cleanToNullable (right.readIsNull (positive))),
                        CTXT.ifStmt (
                            right.readIsNull (true), // but left is not null
                            out.write (negative),
                            s
                        )
                    );
            }
            else {
                s = 
                    CTXT.ifStmt (
                        left.readIsNull (true),
                        out.write (negative),
                        s
                    );
            }
        }
        else if (rightIsNullable) {
            s = 
                CTXT.ifStmt (
                    right.readIsNull (true),
                    out.write (negative),
                    s
                );
        }                

        addTo.add (s);
    }
}
