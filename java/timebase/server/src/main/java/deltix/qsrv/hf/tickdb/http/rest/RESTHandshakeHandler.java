package deltix.qsrv.hf.tickdb.http.rest;

import deltix.qsrv.hf.tickdb.http.HTTPProtocol;
import deltix.qsrv.hf.tickdb.impl.TickDBWrapper;
import deltix.qsrv.hf.tickdb.pub.DXTickDB;
import deltix.util.codec.Base64DecoderEx;
import deltix.util.concurrent.QuickExecutor;
import deltix.util.lang.Disposable;
import deltix.util.security.SecurityController;
import deltix.util.tomcat.ConnectionHandshakeHandler;
import deltix.util.ContextContainer;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.AccessControlException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;

/**
 *
 */
public class RESTHandshakeHandler implements ConnectionHandshakeHandler, Closeable {

    private static final int        KEEP_ALIVE_INTERVAL = 5000;

    private SecurityController      securityController;
    private KeepAlive               keepAlive = null;
    private DXTickDB                tickdb;
    private final Map<String, DXTickDB> userNameToDb = new HashMap<>();
    private final ContextContainer contextContainer;

    public RESTHandshakeHandler(DXTickDB tickdb, SecurityController securityController, ContextContainer contextContainer) {
        this.tickdb = tickdb;
        this.contextContainer = contextContainer;
        this.contextContainer.getQuickExecutor().reuseInstance();
        this.securityController = securityController;
    }

    public boolean handleHandshake(Socket socket, BufferedInputStream bis, OutputStream os) throws IOException {

        synchronized (this) {
            if (keepAlive == null) {
                keepAlive = new KeepAlive(contextContainer.getQuickExecutor());
                keepAlive.submit();
            }
        }

        setUpSocket(socket);

        final DataInputStream dis = new DataInputStream(bis);
        final DataOutputStream dos = new DataOutputStream(os);

        final int init = dis.read();
        assert HTTPProtocol.PROTOCOL_INIT == init;

        if (!handshakeVersion(dis, dos))
            return false;

        DXTickDB db = readCredentialsAndAuthenticate(dis, dos);
        dos.writeInt(HTTPProtocol.RESP_OK);

        final RestHandler handler;
        final int request = dis.readByte();

        switch (request) {
            case HTTPProtocol.REQ_UPLOAD_DATA:
                handler = new UploadHandler(db, socket, bis, os, contextContainer);
                break;

            case HTTPProtocol.REQ_CREATE_SESSION:
                handler = new SessionHandler(db, socket, bis, os, contextContainer);
                break;

            default:
                HTTPProtocol.LOGGER.severe("Unrecognized request code: " + request);
                return false;
        }

        keepAlive.handlers.add(handler);
        handler.submit();

        return true;
    }

    private void                    setUpSocket(Socket socket) {
        try {
            socket.setTcpNoDelay(true);
            socket.setSoTimeout(0);
            socket.setKeepAlive(true);
        } catch (IOException x) {
            HTTPProtocol.LOGGER.log(Level.WARNING, null, x);
        }
    }

    private boolean                  handshakeVersion(DataInputStream dis, DataOutputStream dos) throws IOException {
        dos.writeShort(HTTPProtocol.VERSION);
        final short version = dis.readShort();
        if (version != HTTPProtocol.VERSION) {
            HTTPProtocol.LOGGER.severe(
                String.format("Incompatible REST-TB protocol version %d. Expected version is %d.", version, HTTPProtocol.VERSION));
            return false;
        }

        return true;
    }

    private DXTickDB                readCredentialsAndAuthenticate(DataInputStream dis, DataOutputStream dos) throws IOException {
        String user = null;
        String password = null;

        final boolean readPrincipal = dis.readBoolean();
        if (readPrincipal) {
            user = dis.readUTF();
            password = new String(Base64DecoderEx.decodeBuffer(dis.readUTF()), StandardCharsets.UTF_8);
        }

        DXTickDB db = tickdb;

        if (securityController != null) {
            try {
                Principal principal = securityController.authenticate(user, password);
                if (principal != null) {
                    synchronized (userNameToDb) {
                        db = userNameToDb.get(principal.getName());
                        if (db == null)
                            userNameToDb.put(principal.getName(), (db = new TickDBWrapper(tickdb, securityController, principal)));
                    }
                } else
                    throw new AccessControlException("User is not specified.");
            } catch (Throwable t) {
                HTTPProtocol.LOGGER.log(Level.SEVERE, "Authentication error: ", t);
                responseError(dos, t);
                throw t;
            }
        }

        return db;
    }

    private void                    responseError(DataOutputStream dos, Throwable t) throws IOException {
        dos.writeInt(HTTPProtocol.RESP_ERROR);

        dos.writeUTF(t.getClass().getName());
        String msg = t.getMessage();
        dos.writeUTF(msg == null ? "" : msg);
    }

    public void                     close() {
        if (keepAlive != null)
            keepAlive.close();
        this.contextContainer.getQuickExecutor().shutdownInstance();
    }

    private class KeepAlive extends QuickExecutor.QuickTask implements Disposable {
        List<RestHandler>           handlers = new CopyOnWriteArrayList<>();
        private volatile boolean    closed = false;

        public KeepAlive(QuickExecutor executor) {
            super(executor);
        }

        @Override
        public void run() throws InterruptedException {
            List<RestHandler> toRemove = new ArrayList<>();

            while (!closed) {
                for (int i = 0; i < handlers.size(); ++i) {
                    RestHandler handler = handlers.get(i);
                    try {
                        handler.sendKeepAlive();
                    } catch (IOException e) {
                        toRemove.add(handler);
                    }
                }

                handlers.removeAll(toRemove);
                toRemove.clear();
                Thread.sleep(KEEP_ALIVE_INTERVAL);
            }
            handlers.clear();
        }

        public void close() {
            closed = true;
        }
    }

}
