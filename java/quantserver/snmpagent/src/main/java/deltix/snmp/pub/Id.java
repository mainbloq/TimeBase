package deltix.snmp.pub;

import java.lang.annotation.*;

/**
 *  User-defined id
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@Target ( { ElementType.METHOD, ElementType.TYPE } )
public @interface Id {
    public int value ();
}
