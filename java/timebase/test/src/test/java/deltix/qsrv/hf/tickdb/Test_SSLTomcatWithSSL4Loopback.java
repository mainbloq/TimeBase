package deltix.qsrv.hf.tickdb;

import deltix.qsrv.QSHome;
import deltix.qsrv.SSLProperties;
import deltix.qsrv.comm.cat.StartConfiguration;
import deltix.qsrv.hf.tickdb.comm.client.TickDBClient;
import deltix.qsrv.hf.tickdb.comm.server.TomcatServer;
import deltix.qsrv.hf.tickdb.pub.DXTickDB;
import deltix.qsrv.hf.tickdb.pub.TickDBFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

import org.junit.experimental.categories.Category;
import deltix.util.JUnitCategories.TickDBFast;

import java.io.File;

/**
 *
 */
@Category(TickDBFast.class)
public class Test_SSLTomcatWithSSL4Loopback {

    private static TDBRunner runner;

    @BeforeClass
    public static void start() throws Throwable {
        File timebase = new File(TDBRunner.getTemporaryLocation());
        QSHome.set(timebase.getParent());

        StartConfiguration configuration = StartConfiguration.create(true, false, false);
        SSLProperties ssl = new SSLProperties(true, true);
        configuration.tb.setSSLConfig(ssl);
        runner = new TDBRunner(true, true, QSHome.get(), new TomcatServer(configuration));
        runner.startup();
    }

    @AfterClass
    public static void stop() throws Throwable {
        runner.shutdown();
        runner = null;
    }

    @Test
    public void testConnectionToSSLTomcat() throws Throwable {
        DXTickDB client = TickDBFactory.connect("localhost", runner.getPort(), false);
        client.open(false);
        assertEquals(true, ((TickDBClient) client).isSSLEnabled()); //becomes SSL for this test
        client.close();

        //connect with ssl
        DXTickDB sslClient = TickDBFactory.connect("localhost", runner.getPort(), true);
        sslClient.open(false);
        assertEquals(true, ((TickDBClient) sslClient).isSSLEnabled());
        sslClient.close();
    }
}
