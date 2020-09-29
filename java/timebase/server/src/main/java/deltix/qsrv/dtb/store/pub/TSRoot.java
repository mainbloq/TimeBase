package deltix.qsrv.dtb.store.pub;

import deltix.qsrv.dtb.fs.pub.AbstractFileSystem;
import deltix.qsrv.dtb.fs.pub.AbstractPath;
import deltix.qsrv.dtb.store.dataacc.DAPrivate;
import deltix.qsrv.dtb.store.dataacc.DataAccessorBase;
import deltix.qsrv.hf.pub.TimeInterval;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Collection;

/**
 *
 */
public interface TSRoot {
    public PersistentDataStore      getStore ();
    
    public void                     open (boolean readOnly);
    
    public void                     format ();

    public void                     delete ();
    
    public SymbolRegistry           getSymbolRegistry ();
    
    public void                     setMaxFolderSize (int numTimeSlices);
    
    public int                      getMaxFolderSize ();

    public void                     setMaxFileSize (int numBytes);
    
    public int                      getMaxFileSize (); 
    
    public String                   getCompression();

    public void                     setCompression(String compression);
    
    public void                     getTimeRange (int id, TimeRange out);

    public void                     getTimeRange (TimeRange out);

    public AbstractFileSystem       getFileSystem();

    public TimeInterval[]           getTimeRanges (int[] ids);
    
    /**
     *  Close this root gracefully. It must not be in active state.
     */
    public void                     close ();

    public AbstractPath             getPath ();
    
    /**
     *  Force-close this root. Any unsaved data will be lost.
     */
    public void                     forceClose ();   
    
    public void                     selectTimeSlices (
        TimeRange                       timeRange,
        EntityFilter                    filter,
        Collection <TSRef>              addTo
    );

    public String                   getPathString();

    public void                     drop (TimeRange range);

    public  void                    iterate(TimeRange range, EntityFilter filter, TimeSliceIterator it);

    public TSRef                    associate(String path);

    int MAX_FILE_SIZE_DEF          =  1 << 23;
    int MAX_FILE_SIZE_LOW          =  100;
    int MAX_FILE_SIZE_HIGH         =  100 << 20;

    int MAX_FOLDER_SIZE_DEF        =  100;
    int MAX_FOLDER_SIZE_LOW        =  10;
    int MAX_FOLDER_SIZE_HIGH       =  0xFFFF;

    int COMPRESSION_LEVEL_LOCAL_FS =  0;
    String COMPRESSION_DEF         =  System.getProperty("TimeBase.fileSystem.compression", "LZ4(5)");

    /**
     * @return name of space, may be {@code null} if space name was not explicitly assigned
     */
    @Nullable
    String getSpace();

    /**
     * Assigns index for this {@link TSRoot}. See {@link #getSpaceIndex()}
     */
    void setSpaceIndex(int index);

    /**
     * Index of this space for sorting operations.
     *
     * <pre> signum(String.compare(r1.getSpace(), r2.getSpace()) == signum(Integer.compare(r1.getSpaceIndex(), r2.getSpaceIndex()) </pre>
     * This means that sorting by value of getSpaceIndex() produces same order as sorting by {@link TSRoot#getSpace()}
     *
     * NOTE: This statement is valid only if the index was assigned by {@link deltix.qsrv.hf.tickdb.impl.PDStreamSpaceIndexManager}.
     */
    int getSpaceIndex();
}
