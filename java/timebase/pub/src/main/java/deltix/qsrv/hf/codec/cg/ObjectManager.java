package deltix.qsrv.hf.codec.cg;

import deltix.util.collections.ElementsEnumeration;
import deltix.util.collections.generated.ObjectToObjectHashMap;
import deltix.util.memory.MemoryDataInput;

/**
 * Objects Collection Pool, not Thread-Safe
 */
public final class ObjectManager {

    private final CharSequencePool charSequencePool;
    private final ObjectToObjectHashMap<String, ManagerObjectPool> collections;
    private final ElementsEnumeration<ManagerObjectPool> ce;

    private final ObjectToObjectHashMap<String, ObjectPool> objects;
    private final ElementsEnumeration<ObjectPool> oe;

    private boolean isClean;

    public ObjectManager() {
        this(5);
    }

    public ObjectManager(int capacity) {
        collections = new ObjectToObjectHashMap<>(capacity);
        ce = collections.elements();

        objects = new ObjectToObjectHashMap<>(capacity);
        oe = objects.elements();

        charSequencePool = new CharSequencePool();
        isClean = true;
    }

    public Object               useObject(final Class clazz) {
        isClean = false;

        String name = clazz.getName();

        ObjectPool pool = objects.get(name, null);
        if (pool == null)
            objects.put(name, pool = new ObjectPool() {
                @Override
                public Object newItem() {
                    try {
                        return clazz.newInstance();
                    } catch (IllegalAccessException | InstantiationException  e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        return pool.borrow();
    }

    public Object               use(Class clazz) {
        return  this.use(clazz,5);
    }

    public Object               use(Class clazz, int capacity) {
        isClean = false;
        ManagerObjectPool pool = getManagerObjectPool(clazz.getName());
        return  pool.use(clazz, capacity);
    }

    private ManagerObjectPool   getManagerObjectPool(String className) {
        ManagerObjectPool pool = collections.get(className, null);
        if (pool == null)
            collections.put(className, pool = new ManagerObjectPool());

        return pool;
    }

    public void                 clean() {
        charSequencePool.reset();

        if (!isClean) {
            ce.reset();
            while (ce.hasMoreElements())
                ce.nextElement().clean();

            oe.reset();
            while (oe.hasMoreElements())
                oe.nextElement().reset();

            isClean = true;
        }
    }

    public CharSequence readCharSequence(MemoryDataInput input) {
        return charSequencePool.readCharSequence(input);
    }
}