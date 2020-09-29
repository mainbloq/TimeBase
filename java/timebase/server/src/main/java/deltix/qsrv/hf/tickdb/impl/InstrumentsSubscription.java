package deltix.qsrv.hf.tickdb.impl;

import deltix.qsrv.hf.blocks.InstrumentToObjectMap;
import deltix.timebase.messages.IdentityKey;
import deltix.qsrv.hf.tickdb.pub.TickCursor;
import deltix.qsrv.hf.tickdb.pub.query.SubscriptionChangeListener;
import net.jcip.annotations.GuardedBy;

import java.util.*;

abstract class InstrumentsSubscription {

    private final ArrayList<IdentityKey>     added = new ArrayList<IdentityKey>();
    private final ArrayList<IdentityKey>     removed = new ArrayList<IdentityKey>();
    private final Reference                         all = new Reference();

    @GuardedBy("this")
    private final InstrumentToObjectMap<Reference> subscribed = new InstrumentToObjectMap<Reference>();

    static class Reference {
        final HashSet<TickCursor> owners = new HashSet<TickCursor>();
        boolean notified = false;

        boolean add(TickCursor cursor) {
            return owners.add(cursor) && owners.size() == 1;
        }

        boolean remove(TickCursor cursor) {
            if (owners.remove(cursor))
                return owners.isEmpty();

            return owners.isEmpty();
        }
    }

    public synchronized void    change(TickCursor cursor,
                                       Collection<IdentityKey> cAdded,
                                       Collection<IdentityKey> cRemoved) {
        added.clear();
        removed.clear();

        boolean notify = all.owners.isEmpty();

        if (cAdded != null && !cAdded.isEmpty()) {
            for (IdentityKey id : cAdded) {
                Reference ref = subscribed.get(id);
                if (ref == null) {
                    ref = new Reference();
                    subscribed.put(id, ref);
                }

                if (ref.add(cursor) || !ref.notified)
                    if (notify) {
                        added.add(id);
                        ref.notified = true;
                    }
            }
        }

        if (cRemoved != null && !cRemoved.isEmpty()) {
            for (IdentityKey id : cRemoved) {
                Reference ref = subscribed.get(id);

                if (ref == null) continue;

                if (ref.remove(cursor) || !ref.notified)
                    if (notify) {
                        removed.add(id);
                        ref.notified = true;
                    }
            }
        }

        if (notify)
            fireChanges(added, removed, getListeners());
    }

    public synchronized void         addAll(TickCursor cursor) {
        if (all.add(cursor))
            fireAllAdded(getListeners());

        // clear individual subscription owned by this cursor
        Iterator<Map.Entry<IdentityKey, Reference>> iterator = subscribed.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<IdentityKey, Reference> entry = iterator.next();

            Reference ref = entry.getValue();
            if (ref.remove(cursor))
                iterator.remove();
        }
    }

    public synchronized void         removeAll(TickCursor cursor) {
        added.clear();
        removed.clear();

        boolean notify = all.remove(cursor);

        Iterator<Map.Entry<IdentityKey, Reference>> iterator = subscribed.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<IdentityKey, Reference> entry = iterator.next();

            Reference ref = entry.getValue();
            boolean used = !ref.remove(cursor);

            if (notify) {
                if (!used && ref.notified) {
                    removed.add(entry.getKey());
                    ref.notified = false;
                } else if (used && !ref.notified) {
                    added.add(entry.getKey());
                    ref.notified = true;
                }
            }

            if (!used)
                iterator.remove();
        }

        if (notify) {
            if (subscribed.isEmpty())
                fireAllRemoved(getListeners());
            else
                fireChanges(added, removed, getListeners());
        }
    }

    void   fireCurrent(SubscriptionChangeListener... listeners) {
        if (all.owners.size() > 0) {
            fireAllAdded(listeners);
        } else if (!subscribed.isEmpty()) {
            ArrayList<IdentityKey> copy = new ArrayList<IdentityKey>();

            synchronized (this) {
                copy.addAll(subscribed.keySet());
            }

            fireChanges(copy, null, listeners);
        }
    }

    abstract void   fireChanges(Collection<IdentityKey> added,
                                Collection<IdentityKey> removed,
                                SubscriptionChangeListener[] listeners);

    abstract void   fireAllAdded(SubscriptionChangeListener[] listeners);

    abstract void   fireAllRemoved(SubscriptionChangeListener[] listeners);

    abstract SubscriptionChangeListener[]   getListeners();
}
