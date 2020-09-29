package deltix.qsrv.hf.stream;

import deltix.streaming.MessageChannel;
import deltix.qsrv.hf.pub.*;
import deltix.qsrv.hf.pub.codec.*;
import deltix.qsrv.hf.pub.md.*;
import deltix.qsrv.hf.codec.MessageSizeCodec;
import deltix.timebase.messages.InstrumentMessage;
import deltix.util.lang.Util;
import deltix.util.memory.*;
import deltix.util.time.Interval;

import java.io.*;
import java.util.zip.*;

/**
 *  This class is thread-unsafe.
 */
public class MessageWriter2 extends AbstractMessageWriter implements MessageChannel <InstrumentMessage> /*, Closeable, Flushable */{
    private final OutputStream              out;
    protected final MemoryDataOutput        buffer = new MemoryDataOutput (4096);

//    public MessageWriter2 (OutputStream out, Interval periodicity, Class ... classes)
//            throws IOException
//    {
//        this.out = out;
//        this.codecFactory = CodecFactory.COMPILED;
//
//        for (Class clazz : classes) {
//            RecordClassDescriptor   type =
//                StreamConfigurationHelper.mkUniversalDescriptor (clazz);
//            FixedBoundEncoder       encoder =
//                codecFactory.createFixedBoundEncoder(new TypeLoaderImpl(clazz.getClassLoader()), type);
//
//            addNew (type, clazz, encoder);
//        }
//
//        writeHeader(out, periodicity, getTypes());
//    }

    public MessageWriter2 (OutputStream out,
                           Interval periodicity,
                           TypeLoader loader,
                           RecordClassDescriptor ... descriptors)
       throws IOException, ClassNotFoundException
    {
       this(out,
               periodicity,
               loader,
               CodecFactory.COMPILED,
               descriptors);
    }

    public MessageWriter2 (OutputStream out,
                            Interval periodicity,
                            TypeLoader loader,
                            CodecFactory codecFactory,
                            RecordClassDescriptor ... descriptors)
        throws IOException, ClassNotFoundException
    {
        this.out = out;

        for (RecordClassDescriptor rcd : descriptors) {
            if (loader != null) {
                Class<?> clazz = loader.load(rcd);
                FixedBoundEncoder       encoder =
                    codecFactory.createFixedBoundEncoder (loader, rcd);
                addNew (rcd, clazz, encoder);
            } else {
                addNew (rcd, null, null);
            }
        }

        writeHeader(out, periodicity, getTypes());
    }   

//    public static MessageWriter2    create (File f)
//        throws Exception
//    {
//        return create (f, 1 << 16, null);
//    }

//    public static MessageWriter2    create (File f, Class ... cds)
//        throws Exception
//    {
//        return create (f, 1 << 16, null, cds);
//    }

    public static OutputStream createOutputStream(File f, int bufferSize)
        throws IOException
    {
       OutputStream    os = new FileOutputStream (f);

        try {
            if (Protocol.isDeflatedMessageStream (f)) {
                bufferSize /= 2;
                os = new GZIPOutputStream (os, bufferSize);
            }

            return new BufferedOutputStream (os, bufferSize);
        }
        finally {
            Util.close (os);
        }
    }

//    public static MessageWriter2    create (File f, int bufferSize, Interval periodicity, Class ... cds)
//        throws Exception
//    {
//        OutputStream    os = new FileOutputStream (f);
//
//        try {
//            if (Protocol.isDeflatedMessageStream (f)) {
//                bufferSize /= 2;
//                os = new GZIPOutputStream (os, bufferSize);
//            }
//            if (bufferSize > 0)
//                os = new BufferedOutputStream (os, bufferSize);
//
//            MessageWriter2  wr = new MessageWriter2(os, periodicity, cds);
//            os = null;  // Signal ok
//            return (wr);
//        } finally {
//            Util.close (os);
//        }
//    }

    public static MessageWriter2    create (File f,
                                            Interval periodicity,
                                            TypeLoader loader,
                                            RecordClassDescriptor ... cds)
        throws IOException, ClassNotFoundException
    {
        return create (f, 1 << 16, periodicity, loader, cds);
    }

    public static MessageWriter2    create (
             File f,
             int bufferSize,
             Interval periodicity,
             TypeLoader loader,
             RecordClassDescriptor ... cds)
        throws IOException, ClassNotFoundException
    {
        OutputStream    os = new FileOutputStream (f);

        try {
            if (Protocol.isDeflatedMessageStream (f)) {
                bufferSize /= 2;
                os = new GZIPOutputStream (os, bufferSize);
            }
            if (bufferSize > 0)
                os = new BufferedOutputStream (os, bufferSize);

            MessageWriter2  wr = new MessageWriter2(os, periodicity, loader, cds);
            os = null;  // Signal ok
            return (wr);
        } finally {
            Util.close (os);
        }
    }

//    public static MessageWriter2 open(File f, int bufferSize, TypeLoader loader)
//        throws Exception
//    {
//        InputStream in = null;
//        try {
//            in = new FileInputStream(f);
//            if (Protocol.isDeflatedMessageStream (f)) {
//                    bufferSize /= 2;
//                    in = new GZIPInputStream (in, bufferSize);
//                }
//
//            if (bufferSize > 0)
//                in = new BufferedInputStream (in, bufferSize);
//
//            return create(f, bufferSize, loader, MessageReader2.readHeader(in));
//
//        } finally {
//            if (in != null)
//                in.close();
//        }
//    }

    public void                     close () {
        try {
            out.close ();
        } catch (IOException e) {
            throw new deltix.util.io.UncheckedIOException(e);
        }
    }

    public void                     flush () throws IOException {
        out.flush ();
    }

    public void                     send (InstrumentMessage msg) {
        try {
            encode(msg, buffer);
            final int   size = buffer.getSize ();

            if (out != null) {
                MessageSizeCodec.write (size, out);
                out.write (buffer.getBuffer (), 0, size);
            }
        } catch (IOException e) {
            throw new deltix.util.io.UncheckedIOException(e);
        }
    }    
}
