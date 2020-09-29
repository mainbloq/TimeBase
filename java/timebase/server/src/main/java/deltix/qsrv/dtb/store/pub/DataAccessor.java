package deltix.qsrv.dtb.store.pub;

import deltix.util.lang.Disposable;

/**
 *  A reusable object for accessing TimeBase messages.
 */
public interface DataAccessor extends Disposable {
    /**
     *  Associates this accessor with the specified root.
     */
    public void                 associate (TSRoot root);

}
