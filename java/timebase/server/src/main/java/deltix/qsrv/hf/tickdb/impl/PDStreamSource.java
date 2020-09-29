package deltix.qsrv.hf.tickdb.impl;

import deltix.streaming.MessageSource;
import deltix.qsrv.hf.tickdb.impl.multiplexer.PrioritizedMessageSourceMultiplexer;
import deltix.qsrv.dtb.store.pub.EntityFilter;
import deltix.qsrv.dtb.store.pub.SymbolRegistry;
import deltix.qsrv.dtb.store.pub.TSRoot;
import deltix.timebase.messages.IdentityKey;
import deltix.timebase.messages.InstrumentMessage;
import deltix.timebase.messages.TimeStamp;
import deltix.qsrv.hf.tickdb.pub.SelectionOptions;
import deltix.util.collections.ElementsEnumeration;
import deltix.util.collections.KeyEntry;
import deltix.util.collections.generated.*;
import deltix.util.lang.DisposableListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

class PDStreamSource extends AbstractStreamSource implements DisposableListener<PDStreamReader> {

    private static class Filter implements EntityFilter {

        //private final StringBuilder sb = new StringBuilder();

        protected final IntegerToLongHashMap    subscribed = new IntegerToLongHashMap();

        private ObjectToLongHashMap<String>     missing;
        //private final PDStream                  stream;
        private final SymbolRegistry            symbols;

        Filter(SymbolRegistry symbols, ObjectHashSet<IdentityKey> ids, ObjectToLongHashMap<IdentityKey> times) {
            this.symbols = symbols;

            Iterator<IdentityKey> it = ids.keyIterator();
            while (it.hasNext()) {
                IdentityKey id = it.next();
                long time = times.get(id, Long.MIN_VALUE);

                //sb.append(id).append(" (").append(time).append("),");

                CharSequence symbol = id.getSymbol();

                int key = symbols.symbolToId(symbol);
                if (key != -1) {
                    subscribed.put(key, time);
                } else {
                    if (missing == null)
                        missing = new ObjectToLongHashMap<String>();

                    missing.put(symbol.toString(), time);
                }
            }
        }

//        @Override
//        public String toString() {
//            return sb.toString();
//        }

        @Override
        public long         acceptFrom (int entity) {
            return subscribed.get(entity, Long.MIN_VALUE);
        }

        @Override
        public boolean      accept(int entity) {

            if (subscribed.containsKey(entity))
                return true;

            if (missing != null && !missing.isEmpty()) {
                String symbol = symbols.idToSymbol(entity);

                if (symbol == null)
                    return false;

                long time = missing.remove(symbol, Long.MAX_VALUE);

                if (time != Long.MAX_VALUE) {
                    subscribed.put(entity, time);
                    return true;
                }
            }

            return false;
        }

        @Override
        public boolean      restrictAll() {
            return subscribed.size() == 0 && (missing == null || missing.size() == 0);
        }

        @Override
        public boolean      acceptAll() {
            return false;
        }
    }

    public static class SourceSubscription {

        ObjectHashSet<IdentityKey>       subscribed;
        long                                    timestamp = Long.MIN_VALUE;

        public SourceSubscription(long timestamp) {
            this.timestamp = timestamp;
        }

        public SourceSubscription(Iterable<IdentityKey> subscribed) {
            add(subscribed.iterator());
        }

        public SourceSubscription(Iterator<IdentityKey> subscribed) {
            add(subscribed);
        }

        public void                         add(Iterator<IdentityKey> ids) {
            if (subscribed == null)
                subscribed = new ObjectHashSet<>();

            while (ids.hasNext())
                subscribed.add(ids.next());
        }

        public void                         add(Iterable<IdentityKey> ids) {
            add(ids.iterator());
        }

        public void                         remove(Iterator<IdentityKey> ids) {
            if (subscribed == null)
                subscribed = new ObjectHashSet<>();

                while (ids.hasNext())
                    subscribed.add(ids.next());
        }

        public void                         remove(Iterable<IdentityKey> ids) {
            remove(ids.iterator());
        }

        public void                         clear() {
            if (subscribed == null)
                subscribed = new ObjectHashSet<>();
            else
                subscribed.clear();
        }

        public void                         addAll(long timestamp) {
            this.timestamp = timestamp;
            this.subscribed = null;
        }

        public boolean                      isEmpty() {
            return subscribed != null && subscribed.isEmpty();
        }
    }

    private final PDStream          stream;

    // subscribed instrument identities according to their subscription time
    private final ObjectToLongHashMap<IdentityKey> subscription = new ObjectToLongHashMap<IdentityKey>();

    private boolean                 isSubscribedToAllEntities = false;

    private final ObjectToObjectHashMap<PDStreamReader, SourceSubscription> sources = new ObjectToObjectHashMap<>();

    // tmp for new subscriptions
    private final ArrayList<IdentityKey> temp = new ArrayList<IdentityKey>();

    PDStreamSource(PDStream stream, PrioritizedMessageSourceMultiplexer<InstrumentMessage> mx, SelectionOptions options) {
        super(mx, options);

        this.stream = stream;
    }

    @Override
    public boolean              subscribeToAllEntities(final long timestamp) {
        assert Thread.holdsLock(mx);

        isSubscribedToAllEntities = true;
        subscription.clear();

        final long nstime = TimeStamp.getNanoTime(timestamp);

        for (SourceSubscription source : sources)
            source.addAll(timestamp);

        applyFilter(timestamp, nstime, new SourceSubscription(timestamp));

        return true;
    }

    public EntityFilter         createFilter(SymbolRegistry symbols, SourceSubscription sub) {

        if (sub.subscribed == null) {
            final long nstime = TimeStamp.getNanoTime(sub.timestamp);
            return new EntityFilter() {
                @Override
                public boolean acceptAll() {
                    return true;
                }

                @Override
                public boolean accept(int entity) {
                    return true;
                }

                @Override
                public long acceptFrom(int entity) {
                    return nstime;
                }

                @Override
                public boolean restrictAll() {
                    return false;
                }
            };
        } else {
            return new Filter(symbols, sub.subscribed, subscription);
        }

    }

    /*
     *  Returns true if new source should be created
     */
    @SuppressWarnings("unchecked")
    private void applyFilter(long timestamp, long nstime, SourceSubscription sub) {

        long limit = 0;

        boolean changed = false;

        if (!options.reversed) {
            limit = Long.MAX_VALUE;

            ElementsEnumeration<SourceSubscription> e = sources.elements();
            KeyEntry<PDStreamReader> keys = (KeyEntry<PDStreamReader>) e;

            while (e.hasMoreElements()) {
                PDStreamReader next = keys.key();
                // filter was changed, so we need to re-apply current
                next.setFilter(createFilter(next.getSymbols(), e.nextElement()));
                limit = Math.min(limit, next.getStartTimestamp());
            }

            // create reader which will get data from range [nstime, limit]
            if (!sub.isEmpty() && nstime < limit) {
                TSRoot[] roots = stream.getRoots(sub, options.live, options.space);

                for (TSRoot root : roots) {
                    EntityFilter filter = createFilter(root.getSymbolRegistry(), sub);
                    if (filter.restrictAll())
                        continue;

                    PDStreamReader reader = stream.createReader(root, nstime, options, filter);
                    reader.setDisposableListener(this);
                    reader.setLimitTimestamp(limit);
                    sources.put(reader, sub);

                    mx.add(reader, timestamp, root.getSpaceIndex());
                    changed = true;
                }
            }

        } else {
            limit = Long.MIN_VALUE;

            ElementsEnumeration<SourceSubscription> e = sources.elements();
            KeyEntry<PDStreamReader> keys = (KeyEntry<PDStreamReader>) e;

            while (e.hasMoreElements()) {
                PDStreamReader next = keys.key();
                next.setFilter(createFilter(next.getSymbols(), e.nextElement()));
                limit = Math.max(limit, next.getEndTimestamp());
            }

            // create reader which will get data from range [limit, nstime] reverse
            if (!sub.isEmpty() && nstime > limit) {
                TSRoot[] roots = stream.getRoots(sub, options.live, options.space);

                for (TSRoot root : roots) {
                    EntityFilter filter = createFilter(root.getSymbolRegistry(), sub);
                    if (filter.restrictAll())
                        continue;

                    PDStreamReader reader = stream.createReader(root, nstime, options, filter);
                    reader.setDisposableListener(this);
                    reader.setLimitTimestamp(limit);
                    sources.put(reader, sub);

                    mx.add(reader, timestamp, root.getSpaceIndex());
                    changed = true;
                }
            }
        }

        if (changed || !sources.isEmpty())
            addSpecialReaders(stream, timestamp);
        else
            addSpecialReader(stream, timestamp, VERSIONS_READER);
    }


    @Override
    @SuppressWarnings("unchecked")
    public boolean          clearAllEntities() {
        assert Thread.holdsLock(mx);

        isSubscribedToAllEntities = false;
        subscription.clear();

        ElementsEnumeration<SourceSubscription> e = sources.elements();
        KeyEntry<PDStreamReader> keys = (KeyEntry<PDStreamReader>) e;

        while (e.hasMoreElements()) {
            PDStreamReader reader = keys.key();
            SourceSubscription sub = e.nextElement();
            sub.clear();
            reader.setFilter(EntityFilter.NONE);
        }

        return true;
    }


    @Override
    public boolean      addEntities(long timestamp, Collection<IdentityKey> ids) {
        assert Thread.holdsLock(mx);

        isSubscribedToAllEntities = false;

        long nstime = TimeStamp.getNanoTime(timestamp);

        temp.clear();
        for (IdentityKey id : ids) {
            if (subscription.put(id, nstime))
                temp.add(id);
        }

        for (SourceSubscription source : sources)
            source.add(temp);

        if (!temp.isEmpty()) {
            applyFilter(timestamp, nstime, new SourceSubscription(temp));
            return true;
        }

        return false;
    }

    @Override
    public boolean      addEntities(long timestamp, IdentityKey[] ids) {
        assert Thread.holdsLock(mx);

        isSubscribedToAllEntities = false;

        long nstime = TimeStamp.getNanoTime(timestamp);

        temp.clear();
        for (IdentityKey id : ids) {
            if (subscription.put(id, nstime))
                temp.add(id);
        }

        for (SourceSubscription source : sources)
            source.add(temp);

        if (!temp.isEmpty()) {
            applyFilter(timestamp, nstime, new SourceSubscription(temp));
            return true;
        }

        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean      removeEntities(IdentityKey[] ids) {
        assert Thread.holdsLock(mx);

        isSubscribedToAllEntities = false;

        temp.clear();
        for (IdentityKey id : ids) {
            if (subscription.remove(id))
                temp.add(id);
        }

        for (SourceSubscription source : sources)
            source.remove(temp);

        ElementsEnumeration<SourceSubscription> e = sources.elements();
        KeyEntry<PDStreamReader> keys = (KeyEntry<PDStreamReader>) e;

        while (e.hasMoreElements()) {
            PDStreamReader reader = keys.key();
            SourceSubscription sub = e.nextElement();
            sub.remove(temp);
            reader.setFilter(createFilter(reader.getSymbols(), sub));
        }

        return true;
    }

    @Override
    public boolean  reset(long timestamp) {

        assert Thread.holdsLock(mx);

        clearInternal();

        long nstime = TimeStamp.getNanoTime(timestamp);

        if (isSubscribedToAllEntities) {
            subscribeToAllEntities(timestamp);
        } else {
            // change subscriptions times
            IdentityKey[] keys = subscription.keysToArray(new IdentityKey[subscription.size()]);
            for (int i = 0; i < keys.length; i++)
                subscription.putAndGet(keys[i], nstime, nstime);

            applyFilter(timestamp, nstime, new SourceSubscription(subscription.keyIterator()));
        }

        return true;
    }

    @Override
    public boolean          entityCreated(IdentityKey id) {
        return false;
    }

    @Override
    public boolean          handle(MessageSource<?> feed, RuntimeException ex) {
        return false;
    }

    @Override
    public void             disposed(PDStreamReader resource) {
        assert Thread.holdsLock(mx);

        sources.remove(resource);
    }

    private void    clearInternal() {

        removeSpecialReaders();

        for (Iterator<PDStreamReader> it = sources.keyIterator(); it.hasNext(); ) {
            PDStreamReader reader = it.next();
            mx.closeAndRemove(reader);
            reader.setDisposableListener(null);
        }

        sources.clear();
    }

    @Override
    public void close() {
        assert Thread.holdsLock(mx);

        clearInternal();
    }
}
