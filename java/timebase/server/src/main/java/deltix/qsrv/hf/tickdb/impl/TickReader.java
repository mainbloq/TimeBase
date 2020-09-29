package deltix.qsrv.hf.tickdb.impl;

import deltix.qsrv.hf.tickdb.impl.queue.QueueMessageReader;
import deltix.qsrv.hf.tickdb.pub.DXTickStream;
import deltix.qsrv.hf.tickdb.pub.Messages;
import deltix.qsrv.hf.tickdb.pub.SelectionOptions;
import deltix.timebase.messages.InstrumentMessage;
import deltix.util.lang.*;
import deltix.qsrv.hf.pub.*;
import deltix.qsrv.hf.pub.md.RecordClassDescriptor;
import deltix.util.concurrent.*;

import java.io.*;

/**
 *  Adapts RawReaderBase to MessageSource &lt;InstrumentMessage&gt; by decoding.
 */
class TickReader implements TickStreamReader {
    final QueueMessageReader                rawReader;
    protected Filter                        filter;
    protected final RecordDecoder           decoder;
    protected long                          startTime;
    protected boolean                       isAtEnd = false;
    protected InstrumentMessage             curMsg = null;
    protected RecordClassDescriptor         currentType;

    private InstrumentMessage               liveMessage;
    private boolean                         isRealtime = false;
    private boolean                         realTimeNotification = false;

    TickReader (
            Filter                              filter,
            RecordDecoder                       decoder,
            QueueMessageReader                  rawReader,
            long                                startTime,
            SelectionOptions                    options
    )
    {
        this.filter = filter;
        this.rawReader = rawReader;
        this.decoder = decoder;
        this.startTime = startTime;
        this.realTimeNotification = options.realTimeNotification;

        liveMessage = realTimeNotification ? TickStreamImpl.createRealTimeStartMessage(options.raw, startTime) : null;
    }

    public void                 setAvailabilityListener (Runnable lnr) {
        rawReader.setAvailabilityListener (lnr);
    }

    public DXTickStream getStream () {
        return (rawReader.getStream ());
    }

    public InstrumentMessage    getMessage () {
        return (curMsg);
    }

    public boolean              isAtEnd () {
        return (isAtEnd);
    }

    @Override
    public boolean              isRealTime() {
        return  isRealtime;
    }

    @Override
    public boolean              realTimeAvailable() {
        return rawReader.isLive();
    }

    @SuppressWarnings ("unchecked")
    public boolean              next () {
        try {
            for (;;) {
                if (!rawReader.read ()) {
                        isAtEnd = true;
                        return (false);
                    }

                    long            ts = rawReader.getTimestamp ();

                    if (ts < startTime) {
                        if (DebugFlags.DEBUG_MSG_DISCARD)
                            DebugFlags.discard (
                                "Discarding message " + rawReader + " because timestamp " + ts +
                                    " is earlier than required " + startTime
                            );

                    continue;
                }

                curMsg = (InstrumentMessage) decoder.decode (filter, rawReader.getInput ());
                currentType = decoder.getCurrentType();

                if (curMsg != null) {
                    curMsg.setNanoTime(rawReader.getNanoTime());
                    return (true);
                }
            }
        } catch (IOException iox) {
            throw new deltix.util.io.UncheckedIOException (iox);
        } catch (UnavailableResourceException r) {
            if (realTimeNotification && !isRealtime && (rawReader.isTransient() || rawReader.available() == 0)) {
                isRealtime = true;
                liveMessage.setNanoTime(curMsg != null ? curMsg.getNanoTime() : Long.MIN_VALUE);
                curMsg = liveMessage;
                currentType = Messages.REAL_TIME_START_MESSAGE_DESCRIPTOR;
                return true;
            }

            throw r;
        }
    }
   
    public void                     reset(long time) {
        startTime = time;
    }
  
    public int                      getCurrentTypeIndex () {
        return decoder.getCurrentTypeIndex();
    }

    public RecordClassDescriptor    getCurrentType () {
        return currentType;
    }

//    public RecordClassDescriptor [] getMessageTypes() {
//        return decoder.getMessageTypes();
//    }

    public void                     close () {
        Util.close (rawReader);
    }

    @Override
    public String                   toString () {
        return ("TickReader (" + rawReader + ")");
    }
}
