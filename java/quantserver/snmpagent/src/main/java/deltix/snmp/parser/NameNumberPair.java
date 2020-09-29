package deltix.snmp.parser;

import deltix.util.parsers.Element;

/**
 *
 */
public final class NameNumberPair extends Element {
    public final String                 name;
    public final Number                 numericId;

    public NameNumberPair (long location, String name, Number numericId) {
        super (location);
        
        this.name = name;
        this.numericId = numericId;
    }
    
    
}
