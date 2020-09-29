package deltix.qsrv.hf.tickdb.pub.mon;

import deltix.qsrv.hf.tickdb.pub.SelectionOptions;
import java.util.Date;

/**
 *
 */
public interface TBCursor extends TBChannel, TBObject {
    public SelectionOptions     getOptions ();

    public String []            getSourceStreamKeys ();

    public long                 getLastResetTime ();

    public Date                 getLastResetDate ();
}
