package deltix.qsrv.hf.tickdb;

import deltix.qsrv.SSLProperties;
import deltix.qsrv.comm.cat.StartConfiguration;
import deltix.qsrv.hf.tickdb.comm.server.TomcatServer;
import deltix.qsrv.servlet.HomeServlet;
import org.junit.*;

import java.io.File;
import java.io.IOException;

import static junit.framework.Assert.assertEquals;

import org.junit.experimental.categories.Category;
import deltix.util.JUnitCategories.TickDBFast;

@Category(TickDBFast.class)
public class Test_TomcatServer {

    @Test
    public void testHomeServlet() throws Throwable {
        TDBRunner runner = new TDBRunner(true, true, new TomcatServer(null));
        runner.startup();

        testHome("localhost", runner.getPort(), new File(runner.getLocation()).getParent());

        runner.shutdown();
    }

    @Test
    public void testHomeServletSSL() throws Throwable {

        StartConfiguration configuration = StartConfiguration.create(true, false, false);
        configuration.tb.setSSLConfig(new SSLProperties(true, false));

        TDBRunner runner = new TDBRunner(true, true, new TomcatServer(configuration));
        runner.startup();

        testHome("localhost", runner.getPort(), new File(runner.getLocation()).getParent());

        runner.shutdown();
    }

    public static void testHome(String host, int port, String home) throws IOException {
        String value = HomeServlet.get(host, port);
        assertEquals(home, value);
    }

}
