package deltix.qsrv.hf.tickdb.lang.compiler.sx;

import deltix.qsrv.hf.tickdb.lang.runtime.SelectionMode;
import deltix.qsrv.hf.pub.md.*;
import deltix.qsrv.hf.tickdb.lang.pub.GrammarUtil;
import deltix.qsrv.hf.tickdb.pub.Messages;
import deltix.qsrv.hf.tickdb.pub.Streams;
import deltix.qsrv.hf.tickdb.pub.TickStream;
import java.util.Set;

/**
 *
 */
public class StreamSelector extends CompiledQuery {
    
    public final TickStream []          streams;
    public final SelectionMode                   mode;
    
    public StreamSelector (TickStream ... streams) {
        super (
            new QueryDataType (
                false, 
                new ClassDataType (false, Streams.catTypes (streams))
            )
        );
        
        this.streams = streams;
        this.mode = SelectionMode.NORMAL;
    }

    private static QueryDataType    addRTM (QueryDataType base) {
        ClassDataType                   baseOutput = base.getOutputType ();
        RecordClassDescriptor []        baseRCDs = baseOutput.getDescriptors ();
        int                             n = baseRCDs.length;
        
        for (int ii = 0; ii < n; ii++)
            if (baseRCDs [ii].getGuid ().equals (Messages.REAL_TIME_START_MESSAGE_DESCRIPTOR.getGuid()))
                return (base);
        
        RecordClassDescriptor []    outRCDs = new RecordClassDescriptor [n + 1];
        
        System.arraycopy (baseRCDs, 0, outRCDs, 0, n);
        outRCDs [n] = Messages.REAL_TIME_START_MESSAGE_DESCRIPTOR;
        
        return (new QueryDataType (false, new ClassDataType (false, outRCDs)));
    }
    
    public StreamSelector (StreamSelector template, SelectionMode newMode) {
        super (
            newMode == SelectionMode.HYBRID ?
                addRTM (template.type) :
                template.type
        );
        
        this.streams = template.streams;
        this.mode = newMode;
    }
    
    @Override
    public boolean              isForward () {
        return (mode != SelectionMode.REVERSE);
    }
    
    @Override
    public void                 getAllTypes (Set <ClassDescriptor> out) {
        for (TickStream s : streams)
            for (ClassDescriptor cd : s.getAllDescriptors ())
                out.add (cd);
        
        if (mode == SelectionMode.HYBRID)
            out.add (Messages.REAL_TIME_START_MESSAGE_DESCRIPTOR);
    }
        
    @Override
    protected void              print (StringBuilder out) {
        int         n = streams.length;

        if (mode != SelectionMode.NORMAL) {
            out.append (mode.name ());
            out.append (' ');        
        }
        
        if (n > 1)
            out.append ("(");

        GrammarUtil.escapeVarId (streams [0].getKey (), out);

        for (int ii = 1; ii < streams.length; ii++) {
            out.append (" union ");
            GrammarUtil.escapeVarId (streams [ii].getKey (), out);
        }

        if (n > 1)
            out.append (")");
    }
}
