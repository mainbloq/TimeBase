package deltix.qsrv.hf.tickdb.lang.pub;

import deltix.util.parsers.Location;
import deltix.qsrv.hf.tickdb.pub.*;

/**
 *
 */
public final class CreateStreamStatement extends Statement {
    public final Identifier             id;
    public final String                 title;
    public final String                 comment;
    public final StreamScope            scope;
    public final OptionElement []       options;
    public final ClassDef []            members;

    public CreateStreamStatement (
        long                        location,
        Identifier                  id, 
        String                      title,                                  
        String                      comment, 
        StreamScope                 scope, 
        OptionElement []            options,
        ClassDef []                 members 
    )
    {
        super (location);
        
        this.scope = scope;
        this.id = id;
        this.title = title;
        this.comment = comment;
        this.options = options;
        this.members = members;
    }
    
    public CreateStreamStatement (
        Identifier                  id, 
        String                      title,                                  
        String                      comment, 
        StreamScope                 scope, 
        OptionElement []            options,
        ClassDef []                 members
    )
    {
        this (
            Location.NONE, 
            id, title, comment, 
            scope, options, members
        );
    }
    
    @Override
    public void                     print (StringBuilder s) {
        s.append ("CREATE ");
        s.append (scope);
        s.append (" STREAM ");
        id.print (s);
        
        if (title != null) {
            s.append (" ");
            GrammarUtil.escapeStringLiteral (title, s);
        }
        
        s.append (" (\n");
        
        boolean     first = true;

        for (ClassDef cd : members) {
            if (first)
                first = false;
            else
                s.append (";\n");

            cd.print (s);
        }

        s.append (")");
        
        OptionElement.print (options, s);
        
        if (comment != null) {
            s.append ("\nCOMMENT ");
            GrammarUtil.escapeStringLiteral (comment, s);
        }
    }        
}
