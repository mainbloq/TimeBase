package deltix.qsrv.hf.tickdb.comm.client;

import deltix.qsrv.hf.blocks.InstrumentToObjectMap;
import deltix.qsrv.hf.pub.ChannelPerformance;
import deltix.timebase.messages.IdentityKey;
import deltix.qsrv.hf.tickdb.comm.SelectionOptionsCodec;
import deltix.qsrv.hf.tickdb.comm.TDBProtocol;
import deltix.qsrv.hf.tickdb.pub.SelectionOptions;
import deltix.qsrv.hf.tickdb.pub.TickCursor;
import deltix.qsrv.hf.tickdb.pub.TickStream;
import deltix.qsrv.hf.tickdb.pub.query.Parameter;
import deltix.util.concurrent.UncheckedInterruptedException;
import deltix.util.lang.Util;
import deltix.util.vsocket.VSChannel;
import io.aeron.Aeron;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.util.Arrays;
import java.util.HashSet;

import static deltix.qsrv.hf.pub.util.SerializationUtils.writeInstrumentIdentities;
import static deltix.qsrv.hf.tickdb.comm.TDBProtocol.*;

/**
 * @author Alexei Osipov
 */
public class TickCursorClientFactory {

    public static TickCursor create(
                                     DXRemoteDB                 db,
                                     SelectionOptions           inOptions,
                                     long                       time,
                                     String                     query,
                                     Parameter[]                parameters,
                                     IdentityKey[]       ids,
                                     String[]                   types,
                                     DXClientAeronContext       aeronContext,
                                     TickStream...              streams
    ) {
        SelectionOptions options = inOptions == null ? new SelectionOptions () : inOptions;
        assert !options.restrictStreamType || query == null : "restrictStreamType and query are not compatible";

        boolean     ok = false;
        VSChannel tmpds = null;

        try {
            tmpds = db.connect (ChannelType.Input, false, false, options.compression, options.channelBufferSize);

            DataOutputStream out = tmpds.getDataOutputStream ();
            out.writeInt (REQ_CREATE_CURSOR);

            boolean aeronSupported = db.getServerProtocolVersion() >= TDBProtocol.AERON_SUPPORT_VERSION;
            if (aeronSupported) {
                byte preferredTransport = options.channelPerformance == ChannelPerformance.HIGH_THROUGHPUT ||
                                options.channelPerformance == ChannelPerformance.LATENCY_CRITICAL
                        ? TRANSPORT_TYPE_AERON : TRANSPORT_TYPE_SOCKET;
                out.write(preferredTransport);
            }

            out.writeBoolean(true); // binary serialization
            SelectionOptionsCodec.write (out, options, db.getServerProtocolVersion());
            out.writeLong (time);

            writeInstrumentIdentities (ids, out);

            boolean allEntitiesSubscribed = (ids == null);
            InstrumentToObjectMap<Long> subscribedEntities = new InstrumentToObjectMap<>();
            if (!allEntitiesSubscribed) {
                for (IdentityKey id : ids) {
                    subscribedEntities.put(id, 0L);
                }
            }

            out.writeBoolean(types != null);
            if (types != null) {
                writeNonNullableStrings(out, types);
            }

            boolean allTypesSubscribed = (types == null);
            HashSet<String> subscribedTypes = new HashSet<>();
            if (!allTypesSubscribed) {
                subscribedTypes.addAll(Arrays.asList(types));
            }

            TickCursorClient.writeStreamKeys(out, streams);

            HashSet<TickStream> subscribedStreams = new HashSet<>();
            if (streams != null) {
                subscribedStreams.addAll(Arrays.asList(streams));
            }

            out.writeBoolean (query != null);
            if (query != null) {
                out.writeUTF (query);
                writeParameters (parameters, out);
            }

            out.flush ();

            final DataInputStream in = tmpds.getDataInputStream();
            boolean success = in.readBoolean ();

            if (TickCursorClient.DEBUG_COMM) {
                TickDBClient.LOGGER.info (TickCursorClientFactory.class + ": CREATE {" +
                        Arrays.toString(streams) + ";" + Arrays.toString(ids) + ";" + Arrays.toString(types) + "}");
            }

            //conn.onReconnected ();

            if (!success) {
                TickCursorClient.processError(in);
                throw new AssertionError("Unreachable");
            } else {

                int transportType = TRANSPORT_TYPE_SOCKET;
                if (aeronSupported) {
                    transportType = in.read();
                }

                TickCursor result;
                if (transportType == TRANSPORT_TYPE_SOCKET) {
                    result = new TickCursorClient(db, tmpds, options, time, allEntitiesSubscribed, allTypesSubscribed, subscribedEntities, subscribedTypes, subscribedStreams);
                } else if (transportType == TRANSPORT_TYPE_AERON) {
                    String aeronDir = in.readUTF();
                    Aeron aeron = aeronContext.getServerSharedAeronInstance(aeronDir);
                    int aeronDataStreamId = in.readInt();
                    int aeronCommandStreamId = in.readInt();
                    result = new TickCursorClientAeron(db, tmpds, options, time, allEntitiesSubscribed, allTypesSubscribed, subscribedEntities, subscribedTypes, subscribedStreams, aeron, aeronDataStreamId, aeronCommandStreamId, aeronContext.getSubscriptionChecker());
                } else {
                    throw new IllegalStateException("Unknown transport type code: " + transportType);
                }
                ok = true;
                return result;
            }
        } catch (IOException x) {
            if (x instanceof SocketException) {
                if (TickCursorClient.isChannelClosed(tmpds)) {
                    throw new IllegalStateException (
                            "Cursor is closed either by a client or upon a disconnection event."
                    );
                }
            }

            if (x instanceof InterruptedIOException) {
                throw new UncheckedInterruptedException(x);
            }

            throw new deltix.util.io.UncheckedIOException (x);
        } finally {
            if (!ok) {
                Util.close(tmpds);
            }
        }
    }

    public static TickCursor create(
            DXRemoteDB                 db,
            SelectionOptions           inOptions,
            long                       time,
            String                     query,
            Parameter[]                parameters,
            String[]                   types,
            DXClientAeronContext       aeronContext,
            TickStream...              streams
    ) {
        IdentityKey[] ids = null;
        return create(db, inOptions, time, query, parameters, ids, types, aeronContext, streams);
    }

    public static TickCursor create(
            DXRemoteDB                 db,
            SelectionOptions           inOptions,
            long                       time,
            String                     query,
            Parameter[]                parameters,
            DXClientAeronContext       aeronContext,
            TickStream...              streams
    ) {
        String[] types = null;
        return create(db, inOptions, time, query, parameters, types, aeronContext, streams);
    }

    public static TickCursor create(
            DXRemoteDB                 db,
            SelectionOptions           inOptions,
            long                       time,
            String                     query,
            Parameter[]                parameters,
            CharSequence[]             symbols,
            String[]                   types,
            DXClientAeronContext       aeronContext,
            TickStream...              streams
    ) {
        SelectionOptions options = inOptions == null ? new SelectionOptions () : inOptions;
        assert !options.restrictStreamType || query == null : "restrictStreamType and query are not compatible";

        boolean     ok = false;
        VSChannel tmpds = null;

        try {
            tmpds = db.connect (ChannelType.Input, false, false, options.compression, options.channelBufferSize);

            DataOutputStream out = tmpds.getDataOutputStream ();
            out.writeInt (REQ_CREATE_CURSOR);

            boolean aeronSupported = db.getServerProtocolVersion() >= TDBProtocol.AERON_SUPPORT_VERSION;
            if (aeronSupported) {
                byte preferredTransport = options.channelPerformance == ChannelPerformance.HIGH_THROUGHPUT ||
                        options.channelPerformance == ChannelPerformance.LATENCY_CRITICAL
                        ? TRANSPORT_TYPE_AERON : TRANSPORT_TYPE_SOCKET;
                out.write(preferredTransport);
            }

            out.writeBoolean(true); // binary serialization
            SelectionOptionsCodec.write (out, options, db.getServerProtocolVersion());
            out.writeLong (time);

            IdentityKey[] ids = writeSymbols(symbols, out);

            boolean allEntitiesSubscribed = (symbols == null);
            InstrumentToObjectMap<Long> subscribedEntities = new InstrumentToObjectMap<>();
            if (!allEntitiesSubscribed) {
                for (IdentityKey id : ids) {
                    subscribedEntities.put(id, 0L);
                }
            }

            out.writeBoolean(types != null);
            if (types != null) {
                writeNonNullableStrings(out, types);
            }

            boolean allTypesSubscribed = (types == null);
            HashSet<String> subscribedTypes = new HashSet<>();
            if (!allTypesSubscribed) {
                subscribedTypes.addAll(Arrays.asList(types));
            }

            TickCursorClient.writeStreamKeys(out, streams);

            HashSet<TickStream> subscribedStreams = new HashSet<>();
            if (streams != null) {
                subscribedStreams.addAll(Arrays.asList(streams));
            }

            out.writeBoolean (query != null);
            if (query != null) {
                out.writeUTF (query);
                writeParameters (parameters, out);
            }

            out.flush ();

            final DataInputStream in = tmpds.getDataInputStream();
            boolean success = in.readBoolean ();

            if (TickCursorClient.DEBUG_COMM) {
                TickDBClient.LOGGER.info (TickCursorClientFactory.class + ": CREATE {" +
                        Arrays.toString(streams) + ";" + Arrays.toString(ids) + ";" + Arrays.toString(types) + "}");
            }

            //conn.onReconnected ();

            if (!success) {
                TickCursorClient.processError(in);
                throw new AssertionError("Unreachable");
            } else {

                int transportType = TRANSPORT_TYPE_SOCKET;
                if (aeronSupported) {
                    transportType = in.read();
                }

                TickCursor result;
                if (transportType == TRANSPORT_TYPE_SOCKET) {
                    result = new TickCursorClient(db, tmpds, options, time, allEntitiesSubscribed, allTypesSubscribed, subscribedEntities, subscribedTypes, subscribedStreams);
                } else if (transportType == TRANSPORT_TYPE_AERON) {
                    String aeronDir = in.readUTF();
                    Aeron aeron = aeronContext.getServerSharedAeronInstance(aeronDir);
                    int aeronDataStreamId = in.readInt();
                    int aeronCommandStreamId = in.readInt();
                    result = new TickCursorClientAeron(db, tmpds, options, time, allEntitiesSubscribed, allTypesSubscribed, subscribedEntities, subscribedTypes, subscribedStreams, aeron, aeronDataStreamId, aeronCommandStreamId, aeronContext.getSubscriptionChecker());
                } else {
                    throw new IllegalStateException("Unknown transport type code: " + transportType);
                }
                ok = true;
                return result;
            }
        } catch (IOException x) {
            if (x instanceof SocketException) {
                if (TickCursorClient.isChannelClosed(tmpds)) {
                    throw new IllegalStateException (
                            "Cursor is closed either by a client or upon a disconnection event."
                    );
                }
            }

            if (x instanceof InterruptedIOException) {
                throw new UncheckedInterruptedException(x);
            }

            throw new deltix.util.io.UncheckedIOException (x);
        } finally {
            if (!ok) {
                Util.close(tmpds);
            }
        }
    }
}
