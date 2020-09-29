package deltix.qsrv.hf.tickdb.comm.server.aeron.download.multicast;

import deltix.qsrv.hf.pub.ChannelPerformance;
import deltix.qsrv.hf.pub.RawMessage;
import deltix.qsrv.hf.tickdb.comm.TDBProtocol;
import deltix.qsrv.hf.tickdb.comm.server.DownloadHandler;
import deltix.qsrv.hf.tickdb.comm.server.aeron.AeronThreadTracker;
import deltix.qsrv.hf.tickdb.comm.server.aeron.DXServerAeronContext;
import deltix.qsrv.hf.tickdb.comm.server.aeron.download.unicast.AeronDownloadHandler;
import deltix.qsrv.hf.tickdb.impl.ServerStreamImpl;
import deltix.qsrv.hf.tickdb.pub.DXTickDB;
import deltix.qsrv.hf.tickdb.pub.DXTickStream;
import deltix.qsrv.hf.tickdb.pub.SelectionOptions;
import deltix.qsrv.hf.tickdb.pub.query.InstrumentMessageSource;
import deltix.util.concurrent.QuickExecutor;
import deltix.util.io.UncheckedIOException;
import deltix.util.security.DataFilter;
import deltix.util.security.TimebaseAccessController;
import deltix.util.time.TimeKeeper;
import deltix.util.vsocket.VSChannel;
import io.aeron.Aeron;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.Principal;

/**
 * Reads content of cursor creation request, decides on client type and creates corresponding client (socket-based or Aeron-based).
 *
 * @author Alexei Osipov
 */
public class DownloadHandlerFactoryMulticast {
    public static void start(Principal user, VSChannel ds, DXTickDB db, QuickExecutor executor, TimebaseAccessController ac, int clientVersion, AeronThreadTracker aeronThreadTracker, DXServerAeronContext aeronContext, boolean isLocal) throws IOException {

        String aeronChannel = aeronContext.getMulticastChannel();
        SelectionOptions options = new SelectionOptions();
        options.live = true;
        options.raw = true;

        String remoteAddress = ds.getRemoteAddress();
        DataFilter<RawMessage> filter = (ac != null ? ac.createFilter(user, remoteAddress) : null);

        DataOutputStream dout = ds.getDataOutputStream();
        DataInputStream din = ds.getDataInputStream ();
        boolean binary = din.readBoolean ();

        DXTickStream[]       streams;
        try {
            streams = DownloadHandler.readStreamList (ds, db);
        } catch (RuntimeException e) {
            dout.writeBoolean (false);
            AeronDownloadHandler.writeException(e, binary, dout);
            throw e;
        }

        if (streams.length != 1) {
            throw new IllegalArgumentException("Exactly one stream expected");
        }
        ServerStreamImpl stream = (ServerStreamImpl) streams[0];
        String streamKey = stream.getKey();

        AeronMulticastStreamContext multicastContext;
        try {
            multicastContext = aeronContext.subscribeToMulticast(streamKey, (__, existingValue) -> {
                AeronMulticastStreamContext result;
                if (existingValue != null) {
                    result = existingValue;
                } else {
                    // TODO: We can open cursor in the the handling thread
                    // TODO: Consider right start time
                    long startTime = TimeKeeper.currentTime;
                    InstrumentMessageSource cursor = stream.select(startTime, options);
                    int aeronDataStreamId = aeronContext.getNextStreamId();
                    int aeronCommandStreamId = aeronContext.getNextStreamId();

                    Aeron aeron = aeronContext.getAeron();
                    result = new AeronMulticastStreamContext(cursor, aeronDataStreamId, aeronChannel);
                    try {
                        createAndStart(options, cursor, filter, binary, aeronThreadTracker, aeron, aeronDataStreamId, aeronCommandStreamId, aeronChannel, result);
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }

                }
                result.addSubscriber(ds);
                AeronMulticastDownloadControlTask controlTask = new AeronMulticastDownloadControlTask(executor, streamKey, ds, aeronContext, result.getCursorMetadata());
                ds.setAvailabilityListener(controlTask.avlnr);
                return result;
            });
        } catch (Exception e) {
            dout.writeBoolean (false);
            AeronDownloadHandler.writeException(e, binary, dout);
            dout.flush();
            throw new RuntimeException(e);
        }

        dout.writeBoolean(true);  // OK
        DownloadHandler.writeSelectedTransport(clientVersion, dout, TDBProtocol.TRANSPORT_TYPE_AERON);

        // If client is local then give him direct path to aeron directory so client can use it as is.
        // Otherwise send empty string. In this case client should figure out location of Aeron on it's machine himself.
        String aeronDir = isLocal ? aeronContext.getAeronDir() : "";
        dout.writeUTF(aeronDir);

        dout.writeUTF(multicastContext.getAeronChannel());
        //dout.writeUTF(aeronDir);
        dout.writeInt(multicastContext.getAeronDataStreamId());
        dout.flush();

        ds.setAutoflush(true);
    }

    public static void createAndStart(SelectionOptions options, InstrumentMessageSource cursor, DataFilter<RawMessage> filter, boolean binary, AeronThreadTracker aeronThreadTracker, Aeron aeron, int aeronDataStreamId, int aeronCommandStreamId, String aeronChannel, AeronMulticastStreamContext streamContext) throws IOException {
        AeronMulticastDownloadTask downloadTask = new AeronMulticastDownloadTask(aeron, aeronDataStreamId, aeronCommandStreamId, cursor, filter, binary, options.channelPerformance, aeronChannel, streamContext);

        Thread downloaderThread = aeronThreadTracker.newMulticastDownloaderThread(downloadTask, options.channelPerformance == ChannelPerformance.LATENCY_CRITICAL);

        // Empty runnable to trigger NextResult.UNAVAILABLE
        cursor.setAvailabilityListener(() -> {});

        downloaderThread.start();
    }
}
