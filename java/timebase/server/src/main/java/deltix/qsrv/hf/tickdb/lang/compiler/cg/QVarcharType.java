package deltix.qsrv.hf.tickdb.lang.compiler.cg;

import deltix.qsrv.hf.pub.md.VarcharDataType;
import deltix.qsrv.hf.tickdb.lang.runtime.Varchar;
import deltix.qsrv.hf.pub.codec.AlphanumericCodec;
import deltix.util.jcg.*;
import static deltix.qsrv.hf.tickdb.lang.compiler.cg.QCGHelpers.*;

/**
 *
 */
public class QVarcharType extends QType <VarcharDataType> {
    public static final QVarcharType     NON_NULLABLE =
        new QVarcharType (new VarcharDataType(VarcharDataType.ENCODING_INLINE_VARSIZE, false, true));

    public static final QVarcharType     NULLABLE =
        new QVarcharType (new VarcharDataType(VarcharDataType.ENCODING_INLINE_VARSIZE, true, true));
    
    private final int           length;
    private final int           anSizeBits;
    
    protected QVarcharType (VarcharDataType dt) {
        super (dt);
        
        length = dt.getLength ();
        
        if (dt.getEncodingType () == VarcharDataType.ALPHANUMERIC)
            anSizeBits = AlphanumericCodec.getNumSizeBits (length);        
        else
            anSizeBits = 0;
    }
    
    @Override
    public QValue               declareValue (
        String                      comment,
        QVariableContainer          container, 
        QClassRegistry              registry,
        boolean                     setNull
    )
    {
        JVariable       v = 
            container.addVar (comment, true, Varchar.class, CTXT.newExpr (Varchar.class));
        
        return (new QVarcharValue (this, container.access (v)));
    }
    
    @Override
    public int                  getEncodedFixedSize () {
        return (SIZE_VARIABLE);
    }

    @Override
    public JStatement           skip (JExpr input) {
        switch (dt.getEncodingType ()) {
            case VarcharDataType.INLINE_VARSIZE:
                return (input.call ("skipCharSequence").asStmt ());
                
            case VarcharDataType.ALPHANUMERIC:
                return (
                    CTXT.staticCall (
                        AlphanumericCodec.class, 
                        "skip", 
                        input, 
                        CTXT.intLiteral (anSizeBits),
                        CTXT.intLiteral (length)
                    ).asStmt ()
                );                
               
            default:
                throw new UnsupportedOperationException (
                    "Unimplemented: " + dt.getEncodingType()
                );
        }
    }

    @Override
    public Class <?>            getJavaClass () {
        return Varchar.class;
    }

    @Override
    public boolean              instanceAllocatesMemory () {
        return (true);
    }
    
    @Override
    public JExpr                getNullLiteral() {
        return CTXT.nullLiteral ();
    }

    @Override
    public JExpr                makeConstantExpr (Object obj) {
        return CTXT.stringLiteral ((String) obj);
    }

    @Override
    public JStatement           decode (JExpr input, QValue value) {
        switch (dt.getEncodingType ()) {
            case VarcharDataType.INLINE_VARSIZE:
                return (value.write (input.call ("readCharSequence")));               
                
            case VarcharDataType.ALPHANUMERIC:
                return (
                    ((QVarcharValue) value).decode (
                        input, 
                        anSizeBits,
                        length
                    )
                );   
                
            default:
                throw new IllegalStateException ("unexpected encoding " + dt.getEncoding());
        }
    }

    public void                 encode (
        QValue                      value, 
        JExpr                       output,
        JCompoundStatement          addTo
    )
    {
        switch (dt.getEncodingType()) {
            case VarcharDataType.INLINE_VARSIZE:
                addTo.add (output.call ("writeString", value.read ()));
                break;
                
            case VarcharDataType.ALPHANUMERIC:
                addTo.add (                    
                    CTXT.staticCall (
                        AlphanumericCodec.class, 
                        "staticWrite", 
                        value.read (), 
                        CTXT.intLiteral (anSizeBits),
                        CTXT.intLiteral (length),
                        output
                    )                    
                );
                break;
                
            default:
                throw new IllegalStateException("unexpected encoding " + dt.getEncoding());
        }
    }

    @Override
    protected void              encodeNullImpl (JExpr output, JCompoundStatement addTo) {
        switch (dt.getEncodingType()) {
            case VarcharDataType.INLINE_VARSIZE:
                addTo.add (output.call ("writeNullString"));
                break;
                
            case VarcharDataType.ALPHANUMERIC:
                addTo.add (CTXT.staticCall (AlphanumericCodec.class, "writeNull", output, CTXT.intLiteral (dt.getLength())));
                break;
                
            default:
                throw new IllegalStateException ("unexpected encoding " + dt.getEncoding());
        }
    }
}
