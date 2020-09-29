package deltix.qsrv.hf.tickdb.lang.runtime.std;

import deltix.qsrv.hf.pub.md.*;
import deltix.qsrv.hf.tickdb.lang.pub.*;

/**
 *  Aggregate MAX (CHAR)
 */
@Aggregate @FunctionInfo (id = "MAX", returns = "CHAR?", args = { "CHAR?" })
public final class MaxChar {
    private char        maxValue = CharDataType.NULL;

    public char         get () {
        return (maxValue);
    }

    public void         set1 (char v) {
        if (v > maxValue)
            maxValue = v;
    }        
}
