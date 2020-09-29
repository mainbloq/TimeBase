package deltix.qsrv.hf.pub.codec.intp;

import deltix.qsrv.hf.codec.cg.CharSequencePool;
import deltix.qsrv.hf.pub.codec.*;
import deltix.util.memory.MemoryDataInput;

import java.lang.reflect.InvocationTargetException;

/**
 *  Interpreting BoundExternalDecoder
 */
public class FixedBoundExternalDecoderImpl implements FixedExternalDecoder {
    protected final RecordLayout        layout;
    private final FieldDecoder []       nonStaticFields;
    private final DecodingContext       ctxt;

    public FixedBoundExternalDecoderImpl (RecordLayout layout) {
        if (!layout.isBound ())
            throw new IllegalArgumentException (layout + " is not bound");
        
        this.layout = layout;
        
        nonStaticFields = FieldCodecFactory.createDecoders (layout);
        ctxt = new DecodingContext (layout);
    }

    public RecordClassInfo      getClassInfo () {
        return (layout);
    }

    public Class<?>             getTargetClass () {
        return (layout.getTargetClass ());
    }

    public void                 setStaticFields (Object message) {
        layout.setStaticFields (message);
    }

    public void                 decode (DecodingContext external, Object msgObject) {

        if (layout.getTargetClass () != msgObject.getClass ())
            throw new IllegalArgumentException (
                    "Object class " + msgObject.getClass () +
                            " does not match " + layout.getTargetClass () +
                            ", to which this decoder is bound."
            );

        if (ctxt != external)
            ctxt.setInput(external);

        boolean truncated = false;
        for (FieldDecoder f : nonStaticFields) {
            try {
                if (truncated || (truncated = ctxt.in.getAvail() <= 0)) {
                    if (f.isBound()) {
                        assert f.isNullable : f.getNotNullableMsg();
                        f.setNull(msgObject);
                    }
                } else {
                    // skip dummy field
                    if (!f.isBound())
                        f.skip(ctxt);
                    else
                        f.copy(ctxt, msgObject);
                }
            } catch (IllegalArgumentException |  InvocationTargetException | IllegalAccessException ex) {
                throw new RuntimeException (f.toString (), ex);
            }
        }
    }
    
    public void                 decode (MemoryDataInput in, Object msgObject) {
        ctxt.setInput(in);
        decode(ctxt, msgObject);
    }
}
