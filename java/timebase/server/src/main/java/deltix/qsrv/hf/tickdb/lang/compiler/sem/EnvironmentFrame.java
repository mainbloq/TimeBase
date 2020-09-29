package deltix.qsrv.hf.tickdb.lang.compiler.sem;

import deltix.util.parsers.Location;
import deltix.qsrv.hf.tickdb.lang.pub.*;
import deltix.qsrv.hf.tickdb.lang.errors.*;
import java.util.*;

/**
 *
 */
public class EnvironmentFrame implements Environment {    
    private static final Object             AMBIGUOUS = new Object ();

    private static final class Key {
        NamedObjectType     type;
        String              name;

        Key () {            
        }
        
        Key (NamedObjectType type, String name) {
            this.type = type;
            this.name = name;
        }
        
        @Override
        @SuppressWarnings ("EqualsWhichDoesntCheckParameterClass")
        public boolean      equals (Object obj) {
            if (obj == null)
                return false;

            final Key           other = (Key) obj;
            return (this.type == other.type && this.name.equals (other.name));
        }

        @Override
        public int          hashCode () {
            int                 hash = 5;
            hash = 97 * hash + this.type.hashCode ();
            hash = 97 * hash + this.name.hashCode ();
            return hash;
        }                
    }
    
    private final Environment               parent;
    private final Map <Key, Object>         local = new HashMap <Key, Object> ();
    private final Map <Key, Object>         cilocal = new HashMap <Key, Object> ();
    private Key                             buffer = new Key ();
    
    public EnvironmentFrame () {
        this (null);
    }

    public EnvironmentFrame (Environment parent) {
        this.parent = parent;
    }

    public Object               lookUpExactLocal (NamedObjectType type, String id) {
        buffer.type = type;
        buffer.name = id;
        
        return (local.get (buffer));
    }
    
    public Object               lookUp (NamedObjectType type, String id, long location) {
        buffer.type = type;
        buffer.name = id;
        
        Object          obj = local.get (buffer);

        if (obj == AMBIGUOUS)
            throw new AmbiguousIdentifierException (id, location);

        if (obj != null)
            return (obj);

        buffer.name = id.toUpperCase ();
        obj = cilocal.get (buffer);

        if (obj == AMBIGUOUS)
            throw new AmbiguousIdentifierException (id, location);

        if (obj != null)
            return (obj);

        if (parent == null)
            throw new UnknownIdentifierException (id, location);
        
        return (parent.lookUp (type, id, location));
    }

    public void              bindNoDup (
        TypeIdentifier          id, 
        Object                  value
    )
    {        
        bind (NamedObjectType.TYPE, id.typeName, id.location, value, false);
    }
    
    public void              bindNoDup (
        Identifier              id, 
        Object                  value
    )
    {        
        bind (NamedObjectType.VARIABLE, id.id, id.location, value, false);
    }
    
    public void              bindNoDup (
        NamedObjectType         type, 
        String                  id, 
        long                    location,
        Object                  value
    )
    {        
        bind (type, id, location, value, false);
    }
    
    public void              bind (
        NamedObjectType         type,
        String                  id,
        Object                  value
    )
    {
        bind (type, id, Location.NONE, value, true);
    }
    
    private void            bind (
        NamedObjectType         type, 
        String                  id, 
        long                    location,
        Object                  value,
        boolean                 dupOk
    )
    {
        Key         key = new Key (type, id);
        
        if (!dupOk && local.containsKey (key))
            throw new DuplicateIdentifierException (id, location);
        
        Object      prev = local.put (key, value);

        if (prev != null && prev != AMBIGUOUS)
            local.put (key, AMBIGUOUS);

        Key         ukey = new Key (type, id.toUpperCase ());

        prev = cilocal.put (ukey, value);

        if (prev != null && prev != AMBIGUOUS)
            cilocal.put (ukey, AMBIGUOUS);
    }
}
