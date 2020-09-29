package deltix.qsrv.hf.tickdb.lang.compiler.sem;

import deltix.qsrv.hf.tickdb.lang.pub.NamedObjectType;
import deltix.qsrv.hf.pub.*;
import deltix.qsrv.hf.pub.md.*;
import deltix.qsrv.hf.tickdb.lang.runtime.std.*;

/**
 *
 */
public class StdEnvironment extends EnvironmentFrame {
//    public static final EnumClassDescriptor INSTR_TYPE_ECD;
//    public static final EnumDataType        INSTR_TYPE_ENUM;
//
//    static {
//        Introspector    ix = Introspector.createEmptyMessageIntrospector ();
//
//        try {
//            INSTR_TYPE_ECD = ix.introspectEnumClass (InstrumentType.class);
//        } catch (Introspector.IntrospectionException x) {
//            throw new RuntimeException ("Error introspecting built-in types", x);
//        }
//
//        INSTR_TYPE_ENUM = new EnumDataType (false, INSTR_TYPE_ECD);
//    }
//
    public StdEnvironment (Environment parent) {
        super (parent);
        
        register (StandardTypes.CLEAN_BOOLEAN);
        register (StandardTypes.CLEAN_BINARY);
        register (StandardTypes.CLEAN_CHAR);
        register (StandardTypes.CLEAN_FLOAT);
        register (StandardTypes.CLEAN_INTEGER);
        register (StandardTypes.CLEAN_TIMEOFDAY);
        register (StandardTypes.CLEAN_TIMESTAMP);
        register (StandardTypes.CLEAN_VARCHAR);
                        
//        register (new ClassMap.EnumClassInfo (INSTR_TYPE_ECD));
//        QQLCompiler.setUpEnv (this, INSTR_TYPE_ECD);
        
        registerFunction (MaxBoolean.class);
        registerFunction (MaxInteger.class);
        registerFunction (MaxFloat.class);
        registerFunction (MaxChar.class);
        registerFunction (MaxVarchar.class);
        registerFunction (MaxTimeOfDay.class);
        registerFunction (MaxTimestamp.class);
        
        registerFunction (MinBoolean.class);
        registerFunction (MinInteger.class);
        registerFunction (MinFloat.class);
        registerFunction (MinChar.class);
        registerFunction (MinVarchar.class);
        registerFunction (MinTimeOfDay.class);
        registerFunction (MinTimestamp.class);
        
        registerFunction (Count.class);      
        
        bindPseudoFunction (QQLCompiler.KEYWORD_LAST);
        bindPseudoFunction (QQLCompiler.KEYWORD_FIRST);
        bindPseudoFunction (QQLCompiler.KEYWORD_REVERSE);
        bindPseudoFunction (QQLCompiler.KEYWORD_LIVE);
        bindPseudoFunction (QQLCompiler.KEYWORD_HYBRID);        
    }
    
    private void            bindPseudoFunction (String name) {
        bind (NamedObjectType.FUNCTION, name, name);
    }
    
    public final void       register (DataType type) {
        bind (NamedObjectType.TYPE, type.getBaseName (), type);
    }
    
    public final void       register (ClassMap.ClassInfo <?> ci) {
        bind (NamedObjectType.TYPE, ci.cd.getName (), ci);
    }
    
    public final void       registerFunction (Class <?> cls) {
        FunctionDescriptor      fd = new FunctionDescriptor (cls);
        String                  id = fd.info.id ();
        OverloadedFunctionSet   ofs = (OverloadedFunctionSet) 
            lookUpExactLocal (NamedObjectType.FUNCTION, id);
        
        if (ofs == null) {
            ofs = new OverloadedFunctionSet (id);
            
            bind (NamedObjectType.FUNCTION, id, ofs);
        }
            
        ofs.add (fd);                
    }
}
