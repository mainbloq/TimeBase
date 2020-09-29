package deltix.qsrv.hf.tickdb.comm.server.aeron.upload;

import deltix.data.stream.MessageDecoder;
import deltix.qsrv.hf.pub.ChannelPerformance;
import deltix.qsrv.hf.pub.RawMessage;
import deltix.qsrv.hf.pub.md.RecordClassSet;
import deltix.qsrv.hf.tickdb.comm.TDBProtocol;
import deltix.qsrv.hf.tickdb.comm.server.RequestHandler;
import deltix.qsrv.hf.tickdb.comm.server.SimpleRawDecoder;
import deltix.qsrv.hf.tickdb.comm.server.TransportNegotiation;
import deltix.qsrv.hf.tickdb.comm.server.aeron.AeronThreadTracker;
import deltix.qsrv.hf.tickdb.comm.server.aeron.download.unicast.AeronDownloadHandler;
import deltix.qsrv.hf.tickdb.pub.*;
import deltix.qsrv.hf.tickdb.pub.lock.DBLock;
import deltix.qsrv.hf.tickdb.pub.lock.LockHandler;
import deltix.timebase.messages.InstrumentMessage;
import deltix.util.vsocket.VSChannel;
import io.aeron.Aeron;
import io.aeron.Subscription;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.Principal;

public class AeronUploadHandler {

    public static void start(Principal user, VSChannel ds, int clientVersion, Aeron aeron, int aeronServerMessageStreamId, int aeronLoaderDataStreamId, AeronThreadTracker aeronThreadTracker, String aeronDir, boolean binary, @Nonnull LoadingOptions options, @Nonnull DXTickStream stream, @Nullable DBLock lock)
        throws IOException
    {

        DataOutputStream out = ds.getDataOutputStream ();

        AeronUploadLockHolder lockHolder;
        // we should abort writer when 'write' lock added.
        if (lock == null && stream instanceof LockHandler) {
            // TODO: Impl

            LockHandler lockHandler = (LockHandler) stream;
            lockHolder = new AeronUploadLockHolder(lockHandler);
            lockHandler.addEventListener(lockHolder);
        } else {
            lockHolder = new AeronUploadLockHolder(null);
        }

        RecordClassSet      md = RequestHandler.getMetaData (stream);

        MessageDecoder decoder = new SimpleRawDecoder(md.getTopTypes ());
        TickLoader loader = stream.createLoader (options);


        Subscription subscription = aeron.addSubscription(AeronDownloadHandler.CHANNEL, aeronLoaderDataStreamId);
        AeronUploadTask uploadTask = new AeronUploadTask(user, subscription, loader, stream.getKey(), aeronServerMessageStreamId, decoder, binary, options.channelPerformance, lockHolder, ds);

        Thread uploaderThread;
        try {
            uploaderThread = aeronThreadTracker.newUploaderThread(uploadTask, options.channelPerformance == ChannelPerformance.LATENCY_CRITICAL);
        } catch (InsufficientCpuResourcesException e) {
            out.writeInt (TDBProtocol.RESP_ERROR);
            AeronDownloadHandler.writeException(e, binary, out); // TODO: Test
            throw e;
        }

        //ds.setAvailabilityListener (avlnr);
        out.writeInt (TDBProtocol.RESP_OK);
        TransportNegotiation.writeSelectedTransport(clientVersion, out, TDBProtocol.TRANSPORT_TYPE_AERON);
        out.writeUTF(aeronDir);
        out.writeInt(aeronServerMessageStreamId); // Direction: from server to client
        out.writeInt(aeronLoaderDataStreamId); // Direction: from client to server
        out.flush();

        uploadTask.installListeners();

        uploaderThread.start();
    }

}
