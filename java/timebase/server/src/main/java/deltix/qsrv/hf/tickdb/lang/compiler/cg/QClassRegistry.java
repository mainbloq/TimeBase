package deltix.qsrv.hf.tickdb.lang.compiler.cg;

import deltix.qsrv.hf.pub.md.*;
import deltix.util.collections.IndexedArrayList;
import deltix.util.jcg.*;

/**
 *
 */
public class QClassRegistry {
    private final JExpr                                     typesVar;
    private final IndexedArrayList <RecordClassDescriptor>  registry =
        new IndexedArrayList <RecordClassDescriptor> ();

    public QClassRegistry (JExpr typesVar) {
        this.typesVar = typesVar;
    }

    public JExpr                    getTypeRef (RecordClassDescriptor type) {
        int     ret = registry.indexOf (type);

        if (ret < 0) {
            ret = registry.size ();
            registry.add (type);
        }

        return (typesVar.index (ret));
    }

    public RecordClassDescriptor [] getTypes () {
        return (registry.toArray (new RecordClassDescriptor [registry.size ()]));
    }
}
