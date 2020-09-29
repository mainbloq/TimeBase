package deltix.qsrv.hf.tickdb.lang.runtime;

import deltix.qsrv.hf.pub.RawMessage;

/**
 *
 */
public abstract class FilterState {
    boolean                 accepted = false;
    
    public boolean          isAccepted () {
        return (accepted);
    }
    
    protected RawMessage    getLastMessage () {
        throw new UnsupportedOperationException ();
    }
}
