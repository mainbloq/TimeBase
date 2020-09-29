package deltix.util.collections.latency;

import deltix.qsrv.hf.tickdb.comm.server.aeron.DXAeronHelper;
import deltix.util.io.Home;
import deltix.util.io.aeron.DXAeron;
import deltix.util.concurrent.QuickExecutor;
import deltix.util.io.offheap.OffHeap;
import deltix.util.vsocket.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 */
public class VSLatencyTestServer implements TestSettings {
    private volatile boolean        serverStopped = false;

    public VSLatencyTestServer(TransportType transportType) throws IOException {
        if (USE_VSOCKET) {
            runWithVSocket(transportType);
        } else if (TRANSPORT_TYPE == TransportType.OFFHEAP_IPC) {
            runWithOffHeap();
        } else if (TRANSPORT_TYPE == TransportType.AERON_IPC) {
            runWithAeron();
        } else {
            runWithSocket();
        }
    }

    private void            runWithVSocket(TransportType type) throws IOException {
        System.out.println (
                "Server listening on port " + PORT + " and transport: " + type
        );

        VSServer server = new VSServer(PORT, null, null, new TransportProperties(type, Home.getPath("tmp/dxipc") ));
        server.setConnectionListener (
                new VSConnectionListener() {
                    @Override
                    public void connectionAccepted(QuickExecutor executor, VSChannel serverChannel) {
                        new LatencyServerThread(executor, serverChannel).submit();
                    }
                }
        );

        server.start();

        while (!serverStopped)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        server.close();
    }

    private void            runWithOffHeap() throws IOException {
        OffHeap.start(Home.getPath("tmp/dxipc"), true);

        ServerSocket srv = new ServerSocket(PORT);
        System.out.println("Java ServerSocket listening on " + srv.getLocalPort());
        Socket s = srv.accept();
        srv.close();
        s.setTcpNoDelay(true);

        File serverFile = new File(SERVER_FILE);
        File clientFile = new File(CLIENT_FILE);

        if (serverFile.exists())
            serverFile.delete();
        if (clientFile.exists())
            clientFile.exists();

        System.out.println("Created files: \n" +
            "Servers file " + serverFile.getName() + "\n" +
            "Clients file " + clientFile.getName());

        InputStream rin = OffHeap.createInputStream(new RandomAccessFile(serverFile, "rw"));
        OutputStream rout = OffHeap.createOutputStream(new RandomAccessFile(clientFile, "rw"));

        doRun(new DataInputStream(rin), new DataOutputStream(rout), WARMUP);
        doRun(new DataInputStream(rin), new DataOutputStream(rout), REPS);

        rin.close();
        rout.close();
    }

    private void            runWithAeron() throws IOException {
        DXAeronHelper.start(true);

        ServerSocket srv = new ServerSocket(PORT);
        System.out.println("Java ServerSocket listening on " + srv.getLocalPort());
        Socket s = srv.accept();
        srv.close();
        s.setTcpNoDelay(true);

        InputStream in = DXAeron.createInputStream(42);
        OutputStream out = DXAeron.createOutputStream(43);

        doRun(new DataInputStream(in), new DataOutputStream(out), WARMUP);
        doRun(new DataInputStream(in), new DataOutputStream(out), REPS);

        in.close();
        out.close();
        DXAeron.shutdown();
    }

    private void            runWithSocket() throws IOException {
        ServerSocket srv = new ServerSocket(PORT);
        System.out.println("Java ServerSocket listening on " + srv.getLocalPort());
        Socket s = srv.accept();
        srv.close();
        s.setTcpNoDelay(true);

        doRun(new DataInputStream(s.getInputStream()),
              new DataOutputStream(s.getOutputStream()), WARMUP);

        doRun(new DataInputStream(s.getInputStream()),
              new DataOutputStream(s.getOutputStream()), REPS);

        s.close();
    }

    private void            doRun(DataInputStream is, DataOutputStream os, int count) throws IOException {
        for (int i = 0; i < count; ++i) {
            os.writeLong(is.readLong());
            os.flush();
        }
    }

    private class LatencyServerThread extends QuickExecutor.QuickTask {
        private final VSChannel channel;

        public LatencyServerThread(QuickExecutor executor, VSChannel serverChannel) {
            super(executor);
            this.channel = serverChannel;
        }

        @Override
        public void run() {
            try (DataInputStream is = new DataInputStream(channel.getInputStream());
                 DataOutputStream os = new DataOutputStream(channel.getOutputStream())) {
                doRun(is, os, WARMUP);
                doRun(is, os, REPS);
            } catch (IOException e) {
                e.printStackTrace();
            }

            channel.setNoDelay(true);
            channel.close(true);
            serverStopped = true;
        }
    }

    public static void main(String[] args) throws IOException {
        final TransportType transportType;
        if (args.length > 0 && args[0].equals("-ipc")) {
            transportType = TransportType.OFFHEAP_IPC;
        } else if (args.length > 0 && args[0].equals("-aeron")) {
            transportType = TransportType.AERON_IPC;
        } else {
            transportType = TransportType.SOCKET_TCP;
        }

        new VSLatencyTestServer(transportType);
    }
}
