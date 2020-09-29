package deltix.qsrv.hf.tickdb.impl.mon;

import deltix.qsrv.hf.tickdb.pub.mon.*;
import deltix.util.collections.CharSequenceToObjectMap;
import deltix.util.collections.generated.*;
import deltix.util.lang.Util;
import net.jcip.annotations.GuardedBy;
import java.util.*;

/**
 *
 */
public abstract class TBMonitorImpl implements TBMonitor {
    private final Set<TBObjectMonitor>      monitors = new HashSet<TBObjectMonitor>();
    private TBObjectMonitor[]               monitorsSnapshot = {};

    private final Object                    dbLock = new Object ();

    public static final long                UNKNOWN_ID = -1;

    @GuardedBy ("dbLock")
    private long                            idSequence = 1;
    
    @GuardedBy ("dbLock")
    private final LongToObjectHashMap <TBObject>    monObjects =
        new LongToObjectHashMap <TBObject> ();

    @GuardedBy ("dbLock")
    private final ObjectHashSet <TBLoader>      loaders =
        new ObjectHashSet<TBLoader>();

    @GuardedBy ("dbLock")
    private final ObjectHashSet <TBCursor>      cursors =
        new ObjectHashSet <TBCursor> ();

    @GuardedBy("dbLock")
    private final CharSequenceToObjectMap<TBLock>   locks =
        new CharSequenceToObjectMap<>();

    private volatile boolean                trackMessagesByInstrument = false;

    public final boolean                          getTrackMessages () {
        return trackMessagesByInstrument;
    }

    public final void                             setTrackMessages (boolean value) {
        this.trackMessagesByInstrument = value;
    }

    public final TBObject                         getObjectById (long id) {
        synchronized (dbLock) {
            return (monObjects.get (id, null));
        }
    }

    public final TBCursor []                      getOpenCursors () {
        TBCursor[] active = null;

        synchronized (dbLock) {
            active = cursors.toArray(new TBCursor[cursors.size()]);
        }

        Arrays.sort(active, new Comparator<TBCursor>() {
            @Override
            public int compare(TBCursor c1, TBCursor c2) {
                return Util.compare(c2.getId(), c1.getId()); // descending
            }
        });

        return active;

    }

    public final TBLoader []                      getOpenLoaders () {
        TBLoader[] active = null;

        synchronized (dbLock) {
            active = loaders.toArray(new TBLoader[loaders.size()]);
        }

        Arrays.sort(active, new Comparator<TBLoader>() {
            @Override
            public int compare(TBLoader left, TBLoader right) {
                return Util.compare(left.getId(), right.getId()); // descending
            }
        });

        return active;
    }

    public final TBLock[]                         getLocks() {
        TBLock[] active;

        synchronized (dbLock) {
            active = locks.values().toArray(new TBLock[locks.size()]);
        }

        return active;
    }

    public final long                             registerLoader (TBLoader loader) {
        long index;
        synchronized (dbLock) {
            loaders.add (loader);
            index = idSequence++;
            monObjects.put (index, loader);
        }

        fireObjectCreated(loader, index);
        return (index);
    }

    public final long                             registerCursor (TBCursor cursor) {
        long index;
        synchronized (dbLock) {
            cursors.add (cursor);
            index = idSequence++;
            monObjects.put (index, cursor);
        }

        fireObjectCreated(cursor, index);
        return (index);
    }

    public final void                             unregisterLoader (TBLoader loader) {
        synchronized (dbLock) {
            if (!loaders.remove (loader))
                throw new RuntimeException ();

            if (loader != monObjects.remove (loader.getId (), null))
                throw new RuntimeException ();
        }

        fireObjectRemoved(loader, loader.getId());
    }

    public final void                             unregisterCursor (TBCursor cursor) {
        synchronized (dbLock) {
            if (!cursors.remove (cursor))
                throw new RuntimeException ();

            if (cursor != monObjects.remove (cursor.getId (), null))
                throw new RuntimeException ();
        }

        fireObjectRemoved(cursor, cursor.getId());
    }

    @Override
    public void addObjectMonitor(TBObjectMonitor monitor) {
        synchronized (monitors) {
            monitors.add(monitor);
            monitorsSnapshot = monitors.toArray(new TBObjectMonitor[monitors.size()]);
        }
    }

    @Override
    public void removeObjectMonitor(TBObjectMonitor monitor) {
        synchronized (monitors) {
            monitors.remove(monitor);
            monitorsSnapshot = monitors.toArray(new TBObjectMonitor[monitors.size()]);
        }
    }

    private void fireObjectCreated(TBObject obj, long id) {
        TBObjectMonitor[] snapshot = monitorsSnapshot;
        for (TBObjectMonitor monitor : snapshot)
            monitor.objectCreated(obj, (int)id);
    }

    private void fireObjectRemoved(TBObject obj, long id) {
        TBObjectMonitor[] snapshot = monitorsSnapshot;
        for (TBObjectMonitor monitor : snapshot)
            monitor.objectDeleted(obj, (int) id);
    }

    public long getLockId(TBLock lock) {
        synchronized (dbLock) {
            TBLock savedLock = locks.get(lock.getGuid());
            if (savedLock != null) {
                return savedLock.getId();
            } else {
                return idSequence++;
            }
        }
    }

    public void registerLock(TBLock lock) {
        long index = lock.getId();
        if (index == UNKNOWN_ID)
            return;

        boolean isNew = false;
        synchronized (dbLock) {
            if (!locks.containsKey(lock.getGuid())) {
                locks.put(lock.getGuid(), lock);
                monObjects.put(index, lock);
                isNew = true;
            }
        }

        if (isNew) {
            fireObjectCreated(lock, index);
        }
    }

    public void unregisterLock(TBLock lock) {
        if (lock.getId() == UNKNOWN_ID)
            return;

        synchronized (dbLock) {
            TBLock removed = locks.remove(lock.getGuid());
            if (removed == null) {
                return;
            }

            monObjects.remove(removed.getId(), null);
        }

        fireObjectRemoved(lock, lock.getId());
    }

}
