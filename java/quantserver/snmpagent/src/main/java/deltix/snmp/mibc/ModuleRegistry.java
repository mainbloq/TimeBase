package deltix.snmp.mibc;

import deltix.snmp.mibc.errors.*;
import deltix.util.parsers.*;

import java.util.*;

/**
 *
 */
public class ModuleRegistry {
    private Map <String, CompiledModule>        env = 
        new HashMap <String, CompiledModule> ();    
    
    ModuleRegistry () {
    }
    
    public static ModuleRegistry    create () {
        ModuleRegistry      mreg = new ModuleRegistry ();

        mreg.register (StandardModules.SNMPv2_SMI);
        mreg.register (StandardModules.SNMPv2_TC);
        mreg.register (StandardModules.SNMPv2_CONF);
        mreg.register (StandardModules.SNMPv2_MIB);
        mreg.register (StandardModules.IANAifType_MIB);
        mreg.register (StandardModules.IF_MIB);

        return (mreg);
    }
    
    public final void               register (CompiledModule module) {
        String          id = module.getId ();
        
        if (env.containsKey (id))
            throw new DuplicateIdException (Location.NONE, id);
        
        env.put (id, module);
    }
    
    public final CompiledModule     getModule (String id) {
        return (getModule (Location.NONE, id));
    }
    
    public final CompiledModule     getModule (long refLocation, String id) {
        if (!Character.isUpperCase (id.charAt (0)))
            throw new IllegalArgumentException (id);
        
        CompiledModule              cmod = env.get (id);
        
        if (cmod == null)
            throw new UnresolvedIdException (refLocation, id);
        
        return (cmod);
    }
}
