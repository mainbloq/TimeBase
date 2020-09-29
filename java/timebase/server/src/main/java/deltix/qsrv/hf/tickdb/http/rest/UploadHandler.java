package deltix.qsrv.hf.tickdb.http.rest;

import deltix.timebase.messages.ConstantIdentityKey;
import deltix.timebase.messages.IdentityKey;
import deltix.qsrv.hf.pub.RawMessage;
import deltix.qsrv.hf.pub.md.RecordClassDescriptor;
import deltix.qsrv.hf.pub.util.SerializationUtils;
import deltix.qsrv.hf.tickdb.comm.UnknownStreamException;
import deltix.qsrv.hf.tickdb.http.HTTPProtocol;
import deltix.qsrv.hf.tickdb.http.ValidationException;
import deltix.qsrv.hf.tickdb.pub.*;
import deltix.qsrv.hf.tickdb.pub.query.SubscriptionChangeListener;
import deltix.util.ContextContainer;
import deltix.util.collections.generated.ObjectArrayList;
import deltix.util.io.LittleEndianDataInputStream;
import deltix.util.lang.Util;

import java.io.*;
import java.net.Socket;
import java.util.Collection;
import java.util.logging.Level;
import java.util.zip.GZIPInputStream;

import static deltix.qsrv.hf.pub.util.SerializationUtils.readIdentityKey;

/**
 *
 */
public class UploadHandler extends RestHandler implements Runnable {
    private final DXTickDB              db;
    private final Socket                socket;
    private final DataInput             din;
    private final DataOutput            dout;

    private volatile boolean            closed = false;

    private RecordClassDescriptor[]     concreteTypes;
    private final ObjectArrayList<ConstantIdentityKey> entities = new ObjectArrayList<>();
    private byte[]                      streamMsgBuffer = new byte[256];
    private final RawMessage            raw = new RawMessage();

    private final ErrorListener         listener = new ErrorListener();

    public UploadHandler(DXTickDB db, Socket socket, InputStream plainIs, OutputStream os, ContextContainer contextContainer) throws IOException {
        super(contextContainer.getQuickExecutor());
        this.db = db;
        this.socket = socket;
        this.dout = new DataOutputStream(os);

        boolean useCompression = (plainIs.read() == 1);
        InputStream is = useCompression ? new GZIPInputStream(plainIs) : plainIs;

        final int endianness = is.read();
        switch (endianness) {
            case 0: // Little-endian
                this.din = new LittleEndianDataInputStream(is);
                break;
            case 1: // Big-endian
                this.din = new DataInputStream(is);
                break;
            default:
                throw new ValidationException(String.format("invalid endianness field %d", endianness));
        }
    }

    private void                        process() throws IOException {
        final String streamKey = din.readUTF();

        final LoadingOptions.WriteMode writeMode = LoadingOptions.WriteMode.values()[din.readByte()];

        final DXTickStream stream = db.getStream(streamKey);
        if (stream == null)
            throw new UnknownStreamException(String.format("stream \"%s\" doesn't exist", streamKey));

        LoadingOptions options = new LoadingOptions(true);
        options.writeMode = writeMode;

        concreteTypes = stream.getStreamOptions().getMetaData().getTopTypes();

        TickLoader loader = null;
        try {
            loader = stream.createLoader(options);

            log(streamKey, "Upload started");

            loader.addEventListener(listener);
            loader.addSubscriptionListener(listener);
            int code;

            while (!closed) {
                code = din.readByte();

                switch (code) {
                    case HTTPProtocol.MESSAGE_BLOCK_ID:

                        log(streamKey, "recieved MESSAGE_BLOCK_ID");
                        while (readMessageRecord(loader))
                            ;
                        break;
                    case HTTPProtocol.INSTRUMENT_BLOCK_ID:

                        log(streamKey, "recieved INSTRUMENT_BLOCK_ID");
                        entities.add(readIdentityKey(din));
                        break;
                    case HTTPProtocol.TERMINATOR_BLOCK_ID:
                        log(streamKey, "recieved TERMINATOR_BLOCK_ID");

                        loader.removeEventListener(listener);
                        loader.removeSubscriptionListener(listener);
                        completeUpload();
                        break;
                    default:
                        throw new IllegalStateException(String.format("UploadHandler@%d [%s] recieved unexpected code: %d", hashCode(), stream, code));
                }
            }
        } finally {
            closeLoader(loader);
        }

        log(streamKey, "Upload finished.");
    }

    private void                        log(String stream, String message) {
        log(Level.FINE, stream, message);
    }

    private void                        log(Level level, String stream, String message) {
        if (HTTPProtocol.LOGGER.isLoggable(level))
            HTTPProtocol.LOGGER.log(level, String.format("UploadHandler@%d [%s]:  %s", hashCode(), stream, message));
    }

    private boolean                     readMessageRecord(final TickLoader loader) throws IOException {
        int size = din.readInt();

        if (size == HTTPProtocol.TERMINATOR_RECORD) {
            log(loader.getTargetStream().getKey(), "received TERMINATOR_RECORD.");
            return false;
        }

        size -= HTTPProtocol.LOADER_MESSAGE_HEADER_SIZE; // readLong + readShort + readByte
        if (size < 0)
            throw new IllegalStateException("size=" + size);

        // read: timestamp instrument_index type_index body
        raw.setNanoTime(din.readLong());
        final ConstantIdentityKey id = entities.get(din.readShort());
        raw.setSymbol(id.symbol);
        byte typeIndex = din.readByte();
        raw.type = concreteTypes[typeIndex];

        if (streamMsgBuffer.length < size)
            streamMsgBuffer = new byte[Util.doubleUntilAtLeast(streamMsgBuffer.length, size)];

        din.readFully(streamMsgBuffer, 0, size);
        raw.setBytes(streamMsgBuffer, 0, size);
        loader.send(raw);

        return true;
    }

    private void                        closeLoader(TickLoader loader) {
        if (loader != null) {
            loader.removeEventListener(listener);
            loader.removeSubscriptionListener(listener);
            loader.close();
        }
    }

    private void                        completeUpload() throws IOException {
        closed = true;
        sendResponse(null);
    }

    @Override
    public void                         run() {
        try {
            process();
        } catch (Throwable t) {
            try {
                HTTPProtocol.LOGGER.log(Level.WARNING, "Error while loading message: ", t);
                sendResponse(t);
            } catch (IOException ioe) {
                throw new deltix.util.io.UncheckedIOException(ioe);
            }
        } finally {
            try {
                socket.close();
            } catch (IOException ioe) {
                throw new deltix.util.io.UncheckedIOException(ioe);
            }
        }
    }

    private void                        sendResponse(Throwable t) throws IOException {
        synchronized (dout) {
            sendResponse(dout, t);
        }
    }

    @Override
    public void                         sendKeepAlive() throws IOException {
        synchronized (dout) {
            dout.write(HTTPProtocol.KEEP_ALIVE_ID);
        }
    }

    private class ErrorListener implements LoadingErrorListener, SubscriptionChangeListener {
        @Override
        public void entitiesAdded(Collection<IdentityKey> entities) {
            try {
                synchronized (dout) {
                    dout.write(HTTPProtocol.INSTRUMENT_BLOCK_ID);
                    dout.writeBoolean(false); // all entities
                    dout.writeBoolean(true); // entities added
                    dout.writeInt(entities.size()); // entities size
                    for (IdentityKey id : entities)
                        SerializationUtils.writeIdentityKey(id, dout);
                }
            } catch (IOException ioe) {
                onException(ioe);
            }
        }

        @Override
        public void entitiesRemoved(Collection<IdentityKey> entities) {
            try {
                synchronized (dout) {
                    dout.write(HTTPProtocol.INSTRUMENT_BLOCK_ID);
                    dout.writeBoolean(false); // all entities
                    dout.writeBoolean(false); // entities added
                    dout.writeInt(entities.size()); // entities size
                    for (IdentityKey id : entities)
                        SerializationUtils.writeIdentityKey(id, dout);
                }
            } catch (IOException ioe) {
                onException(ioe);
            }
        }

        @Override
        public void allEntitiesAdded() {
            try {
                synchronized (dout) {
                    dout.write(HTTPProtocol.INSTRUMENT_BLOCK_ID);
                    dout.writeBoolean(true); // all entities
                    dout.writeBoolean(true); // added
                }
            } catch (IOException ioe) {
                onException(ioe);
            }
        }

        @Override
        public void allEntitiesRemoved() {
            try {
                synchronized (dout) {
                    dout.write(HTTPProtocol.INSTRUMENT_BLOCK_ID);
                    dout.writeBoolean(true); // all entities
                    dout.writeBoolean(false); // added
                }
            } catch (IOException ioe) {
                onException(ioe);
            }
        }

        @Override
        public void typesAdded(Collection<String> types) {
            try {
                synchronized (dout) {
                    dout.write(HTTPProtocol.TYPE_BLOCK_ID);
                    dout.writeBoolean(false); // all entities
                    dout.writeBoolean(true); // entities added
                    dout.writeInt(types.size()); // entities size
                    for (String type : types)
                        dout.writeUTF(type);
                }
            } catch (IOException ioe) {
                onException(ioe);
            }
        }

        @Override
        public void typesRemoved(Collection<String> types) {
            try {
                synchronized (dout) {
                    dout.write(HTTPProtocol.TYPE_BLOCK_ID);
                    dout.writeBoolean(false); // all entities
                    dout.writeBoolean(false); // entities added
                    dout.writeInt(types.size()); // entities size
                    for (String type : types)
                        dout.writeUTF(type);
                }
            } catch (IOException ioe) {
                onException(ioe);
            }
        }

        @Override
        public void allTypesAdded() {
            try {
                synchronized (dout) {
                    dout.write(HTTPProtocol.TYPE_BLOCK_ID);
                    dout.writeBoolean(true); // all entities
                    dout.writeBoolean(true); // added
                }
            } catch (IOException ioe) {
                onException(ioe);
            }
        }

        @Override
        public void allTypesRemoved() {
            try {
                synchronized (dout) {
                    dout.write(HTTPProtocol.TYPE_BLOCK_ID);
                    dout.writeBoolean(true); // all entities
                    dout.writeBoolean(false); // added
                }
            } catch (IOException ioe) {
                onException(ioe);
            }
        }

        @Override
        public void                         onError(LoadingError e) {
            HTTPProtocol.LOGGER.log(Level.WARNING, "Error while loading message: ", e);

            try {
                synchronized (dout) {
                    dout.write(HTTPProtocol.ERROR_BLOCK_ID);
                    dout.writeInt(-1);
                    String msg = e.getMessage();
                    dout.writeUTF(msg != null ? msg : "");
                }
            } catch (IOException ioe) {
                onException(ioe);
            }
        }

        private void                        onException(IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}
