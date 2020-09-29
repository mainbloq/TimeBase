package deltix.util.jcg.scg;

import deltix.util.jcg.*;

import java.io.IOException;
import java.util.*;

class VariableImpl 
    extends JExprImplBase
    implements JVariable, JMethodArgument 
{
    private final String                    name;
    private final String                    type;
    private final int                       modifiers;
    protected String                        comment;
    private final Collection <JAnnotation>  annotations = new ArrayList <> ();

    VariableImpl (JContextImpl context, int modifiers, String type, String name) {
        this(context, modifiers, type, name, null);
    }

    VariableImpl (JContextImpl context, int modifiers, String type, String name, String comment) {
        super (context);
        
        this.name = name;
        this.type = type;
        this.modifiers = modifiers;
        this.comment = comment;
    }

    @Override
    public void             addAnnotation (JAnnotation annotation) {
        annotations.add (annotation);
    }

    @Override
    public final int modifiers() {
        return (modifiers);
    }

    @Override
    public final String name() {
        return (name);
    }

    @Override
    public String type() {
        return (type);
    }

    protected final void printHead(SourceCodePrinter out) throws IOException {

        if (comment != null) {
            out.println("/**");
            String[] lines = comment.split("\n");
            for (String line : lines)
                out.println("* " + line.replace("\r", ""));
            out.println("*/");
        }

        if (annotations.size () > 0) {
            for (JAnnotation annotation : annotations) {
                out.print (annotation);
                out.println ();
            }
        }

        context.printModifiers (modifiers, out);
        context.printType (type, out);

        out.print(" ", name);
    }

    /**
     * Override to reference less-than-local variables.
     */
    public void print (int outerPriority, SourceCodePrinter out)
        throws IOException 
    {
        out.print(name);
    }
}
