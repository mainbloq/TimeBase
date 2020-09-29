package deltix.qsrv.hf.tickdb.impl;

import deltix.streaming.MessageSource;
import deltix.qsrv.hf.pub.*;
import deltix.qsrv.hf.pub.codec.CodecFactory;
import deltix.qsrv.hf.pub.codec.FixedExternalDecoder;
import deltix.qsrv.hf.stream.AbstractMessageReader;
import deltix.qsrv.hf.tickdb.pub.SelectionOptions;
import deltix.qsrv.hf.tickdb.pub.TickStream;
import deltix.timebase.messages.InstrumentMessage;
import deltix.util.concurrent.IntermittentlyAvailableResource;
import deltix.util.concurrent.QuickExecutor;
import deltix.util.concurrent.UnavailableResourceException;

import java.io.IOException;

public class StreamVersionsReader extends AbstractMessageReader
        implements
        MessageSource<InstrumentMessage>,
        TickStreamRelated,
        IntermittentlyAvailableResource {

    private volatile Runnable           availabilityListener = null;
    StreamVersionsContainer             container;

    private QuickExecutor.QuickTask     notifier;
    private InstrumentMessage           message = null;
    private long                        time;
    volatile boolean                    waiting = false;
    final boolean                       live;
    private RawReader                   reader;

    public StreamVersionsReader(StreamVersionsContainer container,
                                RawReader reader,
                                long time,
                                SelectionOptions options)
    {
        this.container = container;
        this.reader = reader;
        this.live = options.live;
        this.time = time;

        this.notifier = new QuickExecutor.QuickTask (container.getStream().getQuickExecutor()) {
                @Override
                public void run () {
                    final Runnable          lnr = availabilityListener;

                    if (lnr == null)
                        synchronized (this) {
                            this.notifyAll ();
                        }
                    else
                        lnr.run ();
                }
        };

        this.types = container.getTypes();

        TypeLoader loader = options.getTypeLoader();

        if (!options.raw) {
            final int       numTypes = this.types.length;

            decoders = new FixedExternalDecoder[numTypes];
            messages = new InstrumentMessage [numTypes];

            for (int i = 0; i < numTypes; i++) {
                FixedExternalDecoder d =
                        decoders[i] = CodecFactory.COMPILED.createFixedExternalDecoder(loader, types[i]);
                InstrumentMessage msg =
                        messages[i] = (InstrumentMessage) types[i].newInstanceNoX(loader);
                d.setStaticFields (msg);
            }
        } else {
            rawMsg = new RawMessage();
            rawMsg.setSymbol(symbol);
        }
    }

    public final void                       submitNotifier () {
        waiting = false;
        notifier.submit ();
    }

    @Override
    public InstrumentMessage                getMessage() {
        return message;
    }
    
    @Override
    public synchronized boolean              next() {
        try {
            boolean hasNext;

            while (hasNext = reader.read()) {
                message = decode(reader.getInput());
                if (message.getTimeStampMs() >= time)
                    break;
            }
            return hasNext;

        } catch (IOException e) {
            throw new deltix.util.io.UncheckedIOException(e);
        } catch (UnavailableResourceException x) {
            waiting = true;
            throw x;
        }
    }

//    void                 readMessage(int ind) {
//        byte[] bytes = container.getData(ind);
//
//        buffer.setBytes(bytes, 0, bytes.length);
//
//        try {
//            message = decode(buffer);
//        } catch (IOException e) {
//            throw new deltix.util.io.UncheckedIOException(e);
//        }
//    }

    @Override
    public boolean          isAtEnd() {
        return false;
    }

    @Override
    public void             close() {
        reader.close();
        container.onClose(this);
    }

    @Override
    public void             setAvailabilityListener(Runnable maybeAvailable) {
        availabilityListener = maybeAvailable;
    }

    @Override
    public TickStream           getStream() {
        return container.getStream();
    }
}
