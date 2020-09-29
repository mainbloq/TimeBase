package deltix.qsrv.hf.tickdb.lang.compiler.cg;

import deltix.qsrv.hf.pub.codec.*;
import deltix.qsrv.hf.pub.md.*;
import deltix.qsrv.hf.tickdb.lang.compiler.sx.FieldSelector;
import java.util.*;

/**
 *
 */
class ClassSelectorInfo {
    private static final FieldSelectorInfo []       NO_FIELDS = { };
    
    final ClassSelectorInfo                         parent;
    final RecordClassDescriptor                     type;
    int                                             ordinal;
    final FieldSelectorInfo []                      fields;
    int                                             highestUsedIdx = -1;    
    final Set <ClassSelectorInfo>                   directChildren = 
        new HashSet <ClassSelectorInfo> ();
    private final Map <String, FieldSelectorInfo>   nameToFieldMap =
        new HashMap <String, FieldSelectorInfo> ();

    ClassSelectorInfo (ClassSelectorInfo parent, RecordClassDescriptor type) {
        this.parent = parent;
        this.type = type;

        RecordLayout                rl = new RecordLayout (type);
        NonStaticFieldLayout []     nsfs = rl.getNonStaticFields ();
        
        if (nsfs == null)
            fields = NO_FIELDS;
        else {
            //
            //  The layout of a class is not strictly guaranteed to 
            //  begin with the exact layout of its parent class.            
            //
            NonStaticFieldLayout [] nsfls = rl.getNonStaticFields ();
            int                     n = nsfls.length;
            
            fields = new FieldSelectorInfo [n];
            
            for (int ii = 0; ii < n; ii++) { 
                NonStaticFieldLayout    nsfl = nsfls [ii];
                FieldSelectorInfo       fsi;
                
                if (nsfl.getOwner () == type)
                    fsi = new FieldSelectorInfo (nsfl.getField ());
                else {
                    fsi = parent.getFieldSelectorInfo (nsfl.getName ());
                    
                    if (fsi == null)
                        throw new RuntimeException ("Field not found in parent: " + nsfl);
                }
                
                fields [ii] = fsi;
                
                FieldSelectorInfo       dup = 
                    nameToFieldMap.put (fsi.field.getName (), fsi);
                
                if (dup != null)
                    throw new IllegalStateException (
                        "At least two fields in a RecordLayout have the same name: " +
                        dup + " and " + fsi
                    );
            }
        }
        
        if (parent != null)
            parent.directChildren.add (this);                    
    }
    
    private FieldSelectorInfo   getFieldSelectorInfo (String name) {
        return (nameToFieldMap.get (name));
    }
    
    boolean                     hasUsedFields () {
        return (highestUsedIdx >= 0);
    }

    boolean                     nonStaticFieldUsedFrom (FieldSelector fs) {
        NonStaticDataField      field = (NonStaticDataField) fs.fieldRef.field;
        int                     idx = -1;
        FieldSelectorInfo       fsi = null;
        
        for (int ii = 0; ii < fields.length; ii++) {
            fsi = fields [ii];

            if (fsi.field == field) {
                idx = ii;
                break;
            }
        }

        if (idx < 0)
            return (false);

        if (highestUsedIdx < idx) 
            highestUsedIdx = idx; 
        //
        // If first use detected:
        //
        if (fsi.fieldSelector == null) {
            fsi.fieldSelector = fs;
                       
            String              relName = field.getRelativeTo ();

            if (relName != null) {
                FieldSelectorInfo       relFSI = getFieldSelectorInfo (relName);
        
                if (relFSI == null)
                    throw new RuntimeException (
                        "Field " + field.getName () +
                        " references unknown field " +
                        relName + " as relative."
                    );

                relFSI.usedAsBase = true;
                fsi.relativeTo = relFSI;
            }
        }
        else if (!fs.equals (fsi.fieldSelector))
            throw new RuntimeException (
                fs + " and " + fsi.fieldSelector +
                " reference the same field but are not equal"
            );

        return (true);
    }

    @Override
    public String       toString () {
        return "ClassSelectorInfo { " + "type=" + type + "; highestUsedIdx=" + highestUsedIdx + " }";
    }
}
