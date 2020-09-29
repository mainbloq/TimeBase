package deltix.util.vsocket;

import deltix.util.concurrent.QuickExecutor;
import org.junit.Test;

import java.io.*;

import deltix.util.time.TimeKeeper;

/**
 * Date: Mar 1, 2010
 */
public class VSockets_Test {

    private static TransportProperties      transportProperties = null;

    @Test
    public void Test() throws IOException, InterruptedException {        
        VSServer    server = new VSServer (8998, null, null, transportProperties);

        server.setConnectionListener (
            new VSConnectionListener() {
                @Override
                public void connectionAccepted (QuickExecutor executor, VSChannel serverChannel) {
                    new ServerThread (executor, serverChannel).submit ();
                }
            }
        );

        server.start ();

        VSClient    client = new VSClient ("localhost", server.getLocalPort());
        client.connect ();
        
        ClientThread th = new ClientThread(client);
        th.start();
        th.join();

        client.close();
        server.close();
    }
}

class ServerThread extends QuickExecutor.QuickTask {
    private final VSChannel     channel;

    public ServerThread (QuickExecutor executor, VSChannel serverChannel) {
        super (executor);
        this.channel = serverChannel;
    }

    @Override
    public void run () {
        long  bytes = 0;
        try {
            DataInputStream in = new DataInputStream(channel.getInputStream ());        

            for (;;) {
                double             b = in.readDouble();
                
                if (b < 0) {
                    System.out.println ("SERVER: End of " + channel + "; b = " + b);
                    channel.getOutputStream ().write(1);
                    channel.getOutputStream ().flush();
                    channel.getOutputStream ().close ();
                    break;
                }
                bytes += 8;
            }
        } catch (ChannelClosedException x) {
            System.out.println ("SERVER: Channel " + channel + " was closed by client.");
        } catch (IOException iox) {
            iox.printStackTrace ();
        } finally {
            System.out.println("Recieved bytes: " + bytes);
        }

        channel.close();
    }
}


class ClientThread extends Thread {
    public static final boolean PRINT_STATS = false;
    public static final int     NUM_MESSAGES = 100000000;

    private final VSChannel     channel;

    public ClientThread (VSClient client) throws IOException {
        super ("Client test thread for " + client);
        this.channel = client.openChannel ();
    }

    @Override
    public void run () {
        long            t1 = TimeKeeper.currentTime;
        
        try {
            OutputStream            os = channel.getOutputStream ();
            DataOutputStream out =  new DataOutputStream(os);

            for (int ii = 0; ii < NUM_MESSAGES; ii++) {
                out.writeDouble(ii + 0.1);
                out.writeDouble(ii + 1.1);
                out.writeDouble(ii + 2.1);
            }
            out.writeDouble(-1.0);
            out.flush();

            // wait for reply
            channel.getInputStream().read();

            os.close ();
        } catch (IOException iox) {
            iox.printStackTrace ();
        }

        long            t2 = TimeKeeper.currentTime;

         double                          s = (t2 - t1) * 0.001;
            System.out.printf (
                "%,d messages in %,.3fs; speed: %,.0f msg/s\n",
                NUM_MESSAGES,
                s,
                NUM_MESSAGES / s
            );

        channel.close ();
    }
}
