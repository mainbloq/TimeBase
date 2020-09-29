package deltix.qsrv;

import java.io.File;

/**
 * Helper class to obtain QuantServer/UHF home directory.
 * It is usually defined by -home command line argument via "deltix.qsrv.home" System property.
 * 
 * See class {@link deltix.util.io.Home} to obtain installation root DELTIX_HOME.
 */
public class QSHome {
    public static final String          QSRV_HOME_SYS_PROP = "deltix.qsrv.home";

    public static void                  set (String home) {
        if (home != null) {
            if ( ! new File (home).exists())
                throw new IllegalArgumentException("Directory specified as Quant Server home does not exist: \"" + home + '"');
            
            System.setProperty (QSRV_HOME_SYS_PROP, home);
        }
    }

    public static String                get () {
        String      home = System.getProperty (QSRV_HOME_SYS_PROP);

        if (home == null)
            throw new RuntimeException (
                "The " + QSRV_HOME_SYS_PROP + " system property is not set"
            );

        return (home);
    }

    public static File                  getFile () {
        return (new File (get ()));
    }

    public static File                  getFile (String subPath) {
        return (new File (get (), subPath));
    }

    public static String                getPath (String subPath) {
        return (getFile (subPath).getPath ());
    }
}
