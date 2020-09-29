package deltix.qsrv.hf.tickdb.impl;

import deltix.qsrv.hf.codec.MessageSizeCodec;
import deltix.timebase.messages.InstrumentMessage;
import deltix.qsrv.hf.pub.TypeLoaderImpl;
import deltix.qsrv.hf.pub.codec.CodecFactory;
import deltix.qsrv.hf.pub.codec.FixedBoundEncoder;
import deltix.qsrv.hf.pub.md.RecordClassDescriptor;
import deltix.qsrv.hf.stream.AbstractMessageReader;
import deltix.qsrv.hf.stream.AbstractMessageWriter;
import deltix.util.collections.generated.ByteArrayList;
import deltix.util.collections.generated.ObjectArrayList;
import deltix.util.io.RandomAccessFileToInputStreamAdapter;
import deltix.util.io.RandomAccessFileToOutputStreamAdapter;
import deltix.util.lang.Util;
import deltix.util.memory.DataExchangeUtils;
import deltix.util.memory.MemoryDataOutput;

import java.io.*;

class MessageContainer extends AbstractMessageWriter {

    final static class DataContainer {
        final ByteArrayList data;

        public DataContainer(int capacity) {
            data = new ByteArrayList(capacity);
        }

        public byte[]   getBytes() {
            return data.getInternalBuffer();
        }

        public void     writeTo(OutputStream out) throws IOException {
            out.write(data.getInternalBuffer(), 0, data.size());
        }

        public boolean  readFrom(InputStream in, int length) throws IOException {
            int count = in.read(data.getInternalBuffer(), 0, length);
            data.setSize(count);
            return count == size();
        }

        public static DataContainer createFrom(MemoryDataOutput out) {
            DataContainer c = new DataContainer(out.getSize());
            c.data.addAll(out.getBuffer(), 0, out.getSize());
            return c;
        }

        public void     put(byte[] buffer, int offset, int length) {
            data.clear();
            data.addAll(buffer, offset, length);
        }

        public int      size() {
            return data.size();
        }
    }


    final ObjectArrayList<DataContainer> data = new ObjectArrayList<DataContainer>();
    final MemoryDataOutput buffer = new MemoryDataOutput(8192);
    final CodecFactory factory;
    final RecordClassDescriptor[] descriptors;
    boolean dirty = false;

    public MessageContainer(CodecFactory factory, Class[] classes, RecordClassDescriptor[] descriptors) {
        this.factory = factory;
        this.descriptors = descriptors;

        for (int i = 0; i < classes.length; i++) {
            FixedBoundEncoder encoder = factory.createFixedBoundEncoder(TypeLoaderImpl.DEFAULT_INSTANCE, descriptors[i]);
            addNew(descriptors[i], classes[i], encoder);
        }

        for (RecordClassDescriptor rcd : descriptors)
            addNew(rcd, null, null);
    }

    public MessageContainer(CodecFactory factory, RecordClassDescriptor ... descriptors) {
        this.factory = factory;
        this.descriptors = descriptors;

        for (RecordClassDescriptor rcd : descriptors)
            addNew(rcd, null, null);
    }

    public synchronized boolean add(InstrumentMessage msg) {
        try {
            encode(msg, buffer);

            DataContainer container = DataContainer.createFrom(buffer);
            synchronized (data) {
               data.add(container);
            }

            return (dirty = true);
        } catch (IOException e) {
            throw new deltix.util.io.UncheckedIOException(e);
        }
    }

    @Override
    protected int           getTypeIndex(RecordClassDescriptor type) {
        int index = super.getTypeIndex(type);
        if (index == -1)
            return (addNew(type, null, null));

        return index;
    }

    @Override
    protected int           getTypeIndex(Class<?> cls) {
        int index = super.getTypeIndex(cls);

        if (index == -1) {
            TypeLoaderImpl loader = new TypeLoaderImpl(cls.getClassLoader());

            // search for class
            for (int i = 0; i < descriptors.length; i++) {
                RecordClassDescriptor cd = descriptors[i];

                try {
                    if (cls.equals(loader.load(cd))) {
                        FixedBoundEncoder encoder = factory.createFixedBoundEncoder(loader, cd);
                        set(i, cd, cls, encoder);
                        return i;
                    }
                } catch (ClassNotFoundException e) {
                }
            }
        }

        return index;
    }

    public synchronized boolean     isDirty() {
        return dirty;
    }

    protected synchronized void     setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public void write(File f) throws IOException {
        byte[] tmp = new byte[8];

        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(f, "rw");
            raf.write(tmp);
            OutputStream out = new RandomAccessFileToOutputStreamAdapter(raf);
            long size = write(out);
            DataExchangeUtils.writeLong(tmp, 0, size);
            raf.seek(0);
            raf.write(tmp);
            raf.close();
        } finally {
            setDirty(false);
            Util.close(raf);
        }
    }

    public void read(File f) throws IOException {
        byte[] tmp = new byte[8];

        if (!f.exists())
            return;

        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(f, "r");
            raf.read(tmp);
            read(new RandomAccessFileToInputStreamAdapter(raf),
                    DataExchangeUtils.readLong(tmp, 0));
            raf.close();
        } catch (IOException e) {
            throw new deltix.util.io.UncheckedIOException(e);
        } finally {
            Util.close(raf);
        }
    }

    protected long write(OutputStream out) throws IOException {
        UniqueMessageContainer.writeHeader(out, null, descriptors);

        int total = 0;

        synchronized (data) {
            for (DataContainer bytes : data) {
                final int size = bytes.size();

                MessageSizeCodec.write(size, out);
                bytes.writeTo(out);
                total += size;
            }
        }

        setDirty(false);

        return total;
    }

    protected void read(InputStream in, long size) throws IOException {

        synchronized (data) {
            data.clear();

            AbstractMessageReader.readHeader(in);
            int total = 0;
            while (total < size) {
                int length = MessageSizeCodec.read(in);

                DataContainer c = new DataContainer(length);
                if (c.readFrom(in, length)) {
                    data.add(c);
                    total += length;
                }
            }
        }

    }
}