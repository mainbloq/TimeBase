package deltix.qsrv.hf.tickdb.lang.compiler.cg;

import deltix.qsrv.hf.pub.md.NonStaticDataField;
import deltix.qsrv.hf.tickdb.lang.compiler.sx.FieldSelector;

class FieldSelectorInfo {
    final NonStaticDataField        field;
    FieldSelector                   fieldSelector = null;
    boolean                         usedAsBase = false;
    FieldSelectorInfo               relativeTo = null;
    QType                           qtype = null;
    QValue                          cache = null;

    public FieldSelectorInfo (NonStaticDataField field) {
        this.field = field;
        this.qtype = QType.forDataType (field.getType ());               
    }
}
