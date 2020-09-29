package deltix.qsrv.hf.tickdb.lang.runtime.std;

import deltix.qsrv.hf.pub.md.DateTimeDataType;
import deltix.qsrv.hf.tickdb.lang.pub.*;

/**
 *  Aggregate MAX (TIMESTAMP)
 */
@Aggregate @FunctionInfo (id = "MAX", returns = "TIMESTAMP?", args = { "TIMESTAMP?" })
public final class MaxTimestamp {
    private long        maxValue = DateTimeDataType.NULL;

    public long         get () {
        return (maxValue);
    }

    public void         set1 (long v) {
        if (v > maxValue)
            maxValue = v;
    }        
}
