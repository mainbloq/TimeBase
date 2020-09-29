package deltix.qsrv.dtb.store.impl;

import deltix.gflog.Log;
import deltix.qsrv.dtb.store.codecs.BlockCompressor;
import deltix.util.collections.generated.ByteArrayList;
import deltix.util.lang.StringUtils;

/**
 *
 */
class TSFWriterThread extends Thread {
    private static final Log LOGGER = PDSImpl.LOGGER;
    private final PDSImpl       pds;

    // compression related
    private BlockCompressor     compressor = null;
    private String              compression   = null;

    private final ByteArrayList buffer = new ByteArrayList();

    TSFWriterThread (PDSImpl pds, int idx) {
        super ("TSF Writer Thread #" + idx);
        this.pds = pds;

        // Should not be a daemon thread because it executes IO operations
    }

    @Override
    public void                 run () {

        try {
            for (;;) {
                boolean logThisOne = LOGGER.isDebugEnabled();

                final TSFile          tsf = pds.getTSFToWrite ();
                final TSRootFolder    root = tsf.root;

                if (!StringUtils.equals(root.getCompression(), compression)) {
                    compression = root.getCompression();
                    compressor = root.createCompressor(buffer);
                }

                // file destroyed
                if (tsf.getState() == null) {
                    LOGGER.warn().append("File was closed: ").append(tsf).commit();
                    pds.fileHasFailed(tsf, null);
                    continue;
                }

                boolean lockAcquired = false;

                try {
                    // should be under try/catch to not crash WriterThread
                    root.acquireSharedLock ();
                    lockAcquired = true;

                    if (tsf.getState() == TSFState.DIRTY_CHECKED_OUT) {
                        pds.fileWasStored(tsf);
                        if (logThisOne)
                            LOGGER.debug().append("Skip storing ").append(tsf).append(" ...").commit();
                        continue;
                    } else if (tsf.getState() != TSFState.DIRTY_QUEUED_FOR_WRITE) {
                        LOGGER.warn().append("Storing ").append(tsf).append(" with wrong state: ").append(tsf.getState()).commit();
                    }

                    root.storeAdditionalDirtyData ();
                    TreeOps.storeIndexFile(tsf.getParent());

                    if (tsf.isDropped()) {
                        if (logThisOne)
                            LOGGER.debug().append("Dropping ").append(tsf.getPath().getPathString()).append(" ...").commit();

                        // finalize index before deleting file, while folder is still "used"
                        TreeOps.finalizeIndex(tsf.getParent());

                        // delete file, because we can have last usage here
                        TreeOps.tryDrop(tsf);

                        pds.fileWasDropped (tsf);
                    } else {
                        if (logThisOne)
                            LOGGER.debug().append("Storing ").append(tsf).append(" ...").commit();

                        if (tsf.store(compressor)) {
                            TreeOps.finalizeIndex(tsf.getParent());

                            if (logThisOne)
                                LOGGER.debug().append(tsf).append(" was stored [").append(tsf.getState()).append("]").commit();
                        } else {
                            if (logThisOne)
                                LOGGER.debug().append(tsf).append(" wasn't stored [").append(tsf.getState()).append("]").commit();
                        }

                        pds.fileWasStored(tsf);
                    }
                } catch (Throwable x) {
                    pds.fileHasFailed (tsf, x);
                } finally {
                    if (lockAcquired)
                        root.releaseSharedLock ();
                }
            }
        } catch (InterruptedException x) {
            LOGGER.debug().append(getName()).append(" is interrupted. Terminating.").commit();
        } catch (Throwable x) {
            LOGGER.error().append(getName()).append(" is crashed.").append(x).commit();
            pds.writerFailed(this);
        }
    }
}
