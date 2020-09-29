package deltix.qsrv.comm.cat;

import deltix.gflog.Log;
import deltix.gflog.LogFactory;
import deltix.qsrv.MemoryMonitorConfigurer;
import deltix.qsrv.QSHome;
import deltix.qsrv.config.QuantServiceConfig;
import deltix.qsrv.hf.tickdb.server.Version;
import deltix.qsrv.util.log.ServerLoggingConfigurer;
import deltix.util.cmdline.DefaultApplication;
import deltix.util.lang.Util;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Level;

public class TBServerCmd extends DefaultApplication {

    private static final String TB_WEBAPP_RESOURCE_FILE = "webapp/deltix-timebase-web.war";
    private static final String QS_WEBAPP_RESOURCE_FILE = "webapp/deltix-quantserver-web.war";

    private final long startTime = System.currentTimeMillis();
    //protected static final Logger LOGGER = Logger.getLogger("deltix.util.tomcat");

    // SEVERE level serves as trigger and failure identifier in many notification frameworks, so set STARTUP level below it
    public static final Level LEVEL_STARTUP = new Level("STARTUP", Level.SEVERE.intValue() - 10) { };

    public final TomcatRunner runner;

    public TBServerCmd(String[] args) throws Exception {
        super(args);

        QSHome.set(getArgValue("-home"));

        StartConfiguration config = StartConfiguration.create(true, false, false);

        if (isArgSpecified("-port"))
            config.port = getIntArgValue("-port", -1);
        else
            config.port = getPort(config);

        // configure logging and memory monitoring
        configure(config);

        if (config.port == -1)
            exit("No port specified");

        runner = new TomcatRunner(config);

        LogKeeper.LOG.info().append(Util.NATIVE_LINE_BREAK).append(Util.NATIVE_LINE_BREAK).commit();
        LogKeeper.LOG.info("QuantServer Version:  %s").with(Version.getVersion());
        LogKeeper.LOG.info("QuantServer Home:     %s").with(QSHome.get());
        LogKeeper.LOG.info("QuantServer Port:     %s").with(config.port);
    }

    private class QuantServerShutdownHook extends Thread {
        @Override
        public void run() {
            LogKeeper.LOG.info("Shutting QuantServer...");
            runner.close();
            ServerLoggingConfigurer.unconfigure();
        }
    }

    protected void run() throws Throwable {
        Runtime.getRuntime().addShutdownHook(new QuantServerShutdownHook());

        try {
            runner.run();
            onStarted();

            runner.waitForStop(); // consume additional thread
        } catch (Throwable e) {
            LogKeeper.LOG.fatal("Fatal error initializing web components: %s").with(e);
            System.exit(-1);
        }
    }

    void    onStarted() {
        LogKeeper.LOG.info("QuantServer started (%s seconds).").with((System.currentTimeMillis() - startTime) / 1000);
    }

    public static int getPort(StartConfiguration config) {
        if (config.tb != null)    // TB Overrides all
            return (config.tb.getPort(8011));

        return (-1);
    }

    private static void configure(StartConfiguration config) throws Exception {

        QuantServiceConfig targetConfig = config.tb;
        // configure logging
        ServerLoggingConfigurer.configure(targetConfig);

        System.setProperty(QuantServiceConfig.QSRV_TYPE_SYS_PROP, targetConfig.getType().toString());

        MemoryMonitorConfigurer.configure(config.quantServer.getProps(), getMaxMemory(config, targetConfig));
        config.tb.setProperty(QuantServiceConfig.WEBAPP_PATH,
                extractResource(TB_WEBAPP_RESOURCE_FILE, QSHome.getFile("temp"))
        );
        config.quantServer.setProperty(QuantServiceConfig.WEBAPP_PATH,
                extractResource(QS_WEBAPP_RESOURCE_FILE, QSHome.getFile("temp"))
        );
    }

    private static long getMaxMemory(StartConfiguration config, QuantServiceConfig targetConfig) {
        long defaultMaxMemory = Runtime.getRuntime().maxMemory();

        assert targetConfig != null;

        if (targetConfig == config.quantServer) { // embedded mode

            if (config.tb != null)
                targetConfig = config.tb;
            else if (config.agg != null)
                targetConfig = config.agg;
            else if (config.es != null)
                targetConfig = config.es;
        }

        if (config.tb == targetConfig)
            return config.tb.getLong("memorySize.3", defaultMaxMemory);
        if (config.agg == targetConfig || config.es == targetConfig)
            return targetConfig.getLong("memorySize.2", defaultMaxMemory);

        return targetConfig.getLong("maxMemory", defaultMaxMemory);
    }

    private static String extractResource(String resource, File extractDirectory) {
        try {
            InputStream is = TBServerCmd.class.getClassLoader().getResourceAsStream(resource);
            if (is != null) {
                File extractedFile = new File(extractDirectory, resource);
                prepareDirectory(extractedFile);
                Files.copy(is, extractedFile.toPath());

                return extractedFile.getAbsolutePath();
            }
        } catch(Exception e) {
            LogKeeper.LOG.info("Can't extract web application %s").with(e);
        }

        return null;
    }

    private static void prepareDirectory(File extractFile) {
        // delete old war file
        if (extractFile.exists()) {
            if (!extractFile.delete()) {
                throw new IllegalStateException("Error deleting file: " + extractFile);
            }
        }

        // create subdirs
        File extractedDir = extractFile.getParentFile();
        if (!extractedDir.exists()) {
            if (!extractedDir.mkdirs()) {
                throw new IllegalStateException("Error creating directory: " + extractFile.getParentFile());
            }
        }
    }

    private static void exit(String message) {
        LogKeeper.LOG.error(message);
        System.exit(-1);
    }

    public static void main(String... args) throws Throwable {
        new TBServerCmd(args).start();
    }

    public static class LogKeeper {
        public static final Log        LOG = LogFactory.getLog ("deltix.util.tomcat");
        //public static final Logger     LOGGER = Logger.getLogger ("deltix.util.tomcat");
    }
}