package deltix.qsrv.hf.tickdb.impl;

import deltix.streaming.MessageChannel;
import deltix.qsrv.dtb.store.pub.DataWriter;
import deltix.qsrv.dtb.store.pub.IllegalMessageAppend;
import deltix.qsrv.dtb.store.pub.TSRoot;
import deltix.timebase.messages.InstrumentMessage;
import deltix.qsrv.hf.tickdb.lang.compiler.sem.PredicateCompiler;
import deltix.qsrv.hf.tickdb.lang.pub.CompilerUtil;
import deltix.qsrv.hf.tickdb.lang.runtime.MessagePredicate;
import deltix.qsrv.hf.tickdb.pub.LoadingOptions;
import deltix.qsrv.hf.tickdb.pub.OutOfSequenceMessageException;
import deltix.qsrv.hf.tickdb.pub.WriterClosedException;
import deltix.timebase.messages.TimeStamp;
import deltix.util.lang.StringUtils;
import deltix.util.lang.Util;
import deltix.util.time.TimeKeeper;
import org.apache.commons.lang.mutable.MutableBoolean;

import java.io.Flushable;
import java.io.IOException;

/**
 *
 */
class PDStreamChannel implements MessageChannel<InstrumentMessage>, Flushable {

    private DataWriter                                  writer;
    private PDStream                                    stream;

    private final MessageProducer                       producer;
    private boolean                                     truncate;
    private LoadingOptions.WriteMode                    mode;
    private final RegistryCache                         cache;
    private boolean                                     opened = false;
    private final MutableBoolean                        exists = new MutableBoolean(false);
    private MessagePredicate                            filter;

    PDStreamChannel(PDStream stream, TSRoot root, DataWriter writer,
                    MessageProducer<? extends InstrumentMessage> producer,
                    LoadingOptions options) {
        this(stream, writer, producer, options, new RegistryCache(root.getSymbolRegistry()));
    }

    PDStreamChannel(PDStream stream, DataWriter writer,
                    MessageProducer<? extends InstrumentMessage> producer,
                    LoadingOptions options, RegistryCache cache)
    {
        this.writer = writer;
        this.stream = stream;
        this.mode = options.writeMode;
        this.producer = producer;
        this.truncate = mode != LoadingOptions.WriteMode.APPEND &&
                mode != LoadingOptions.WriteMode.INSERT;

        this.cache = cache;

        if (!StringUtils.isEmpty(options.filterExpression)) {
            PredicateCompiler pc =
                    stream.isPolymorphic () ?
                            new PredicateCompiler (stream.getPolymorphicDescriptors ()) :
                            new PredicateCompiler (stream.getFixedType ());

            this.filter = pc.compile(CompilerUtil.parseExpression(options.filterExpression));
        }

        stream.writerCreated(writer);
    }

    @Override
    @SuppressWarnings("unchecked")
    public synchronized void            send(InstrumentMessage msg) {
        if (writer == null) // closed state
            throw new WriterClosedException(this + " is closed");

        if (!stream.accumulateIfRequired (msg))
            return;

        boolean undefined = msg.getTimeStampMs() == TimeStamp.TIMESTAMP_UNKNOWN;

        long nstime = undefined ? TimeKeeper.currentTimeNanos : msg.getNanoTime();

        assert nstime != Long.MAX_VALUE; // temporary

        if (!opened) {
            writer.open(nstime, null);
            opened = true;
            stream.firePropertyChanged(TickStreamProperties.TIME_RANGE);
        }

        int index = cache.encode(msg, exists);
        int type = producer.beginWrite(msg);

        if (truncate) {
            // truncation should be done for the first message

            if (!undefined && !exists.booleanValue()) {
                stream.truncateInternal(nstime, msg);
                writer.truncate(nstime, index);
            }
        }

        if (!exists.booleanValue())
            stream.firePropertyChanged(TickStreamProperties.ENTITIES);

        try {
            if (undefined)
                writer.insertMessage(index, nstime, type, producer);
            else if (mode == LoadingOptions.WriteMode.INSERT)
                writer.insertMessage(index, nstime, type, producer);
            else
                writer.appendMessage(index, nstime, type, producer, false);
        } catch (IllegalMessageAppend e) {
            throw new OutOfSequenceMessageException(msg, nstime, stream.getKey(), e.getLastWrittenNanos());
        }
    }

    @Override
    public synchronized void            close() {
        Util.close(writer);
        stream.writerClosed(writer);
        writer = null;

        if (opened) {
            stream.firePropertyChanged(TickStreamProperties.TIME_RANGE);
            stream.firePropertyChanged(TickStreamProperties.ENTITIES);
        }
        opened = false;
    }

    @Override
    public synchronized void flush() throws IOException {
        // we need just close writer, because it will be reopened on next send()
        Util.close(writer);
        opened = false;
    }
}