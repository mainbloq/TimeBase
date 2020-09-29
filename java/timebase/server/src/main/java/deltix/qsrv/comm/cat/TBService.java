package deltix.qsrv.comm.cat;

import deltix.util.runtime.Shutdown;
import deltix.util.service.BaseService;

import java.io.IOException;
import java.util.logging.Level;

public class TBService extends BaseService {

     private class KeepAliveThread extends Thread {
        public KeepAliveThread () {
            super ("KeepAliveThread for " + TBService.this);
        }

        @Override
        public void         run () {
            try {
                for (;;) {
                    getLogger().info("TomcatService - request additional time 5000.");
                    requestAdditionalTime(5000);
                    Thread.sleep (4000);
                }
            } catch (InterruptedException ix) {
                // Done.
            }
        }
    }

    protected volatile TomcatRunner      runner;
    private String[]                     args;

    public TBService(String[] args) {
        super(getSid(args));
        this.args = args;
    }

    class ShutdownHook extends Thread {
        @Override
        public void run() {
            int shutdownCode = 0;
            if (Shutdown.isTerminated())
                shutdownCode = Shutdown.getCode();

            TBService.this.control(SERVICE_CONTROL_STOP, shutdownCode);

            if (runner != null) {
                getLogger().info("Shutting QuantServer...");
                runner.close();
            }
        }
    }

    public void onStart() throws Throwable {

        KeepAliveThread keeper = new KeepAliveThread();
        keeper.start();

        try {
            TBServerCmd cmd = new TBServerCmd(this.args);
            (runner = cmd.runner).run();
            cmd.onStarted();

//            if (runner.isWatchdogUsed()) {
//                if (serviceName.isEmpty()) {
//                    getLogger().warning(
//                        "Parameter -sid is not specified in service launcher command. " +
//                        "DXWatchdog failover will not work for this service.");
//                } else if (!WatchdogService.INSTANCE.running()) {
//                    getLogger().warning(
//                        "DXWatchdog service is not running. " +
//                        "DXWatchdog failover will not work for this service.");
//                } else {
//                    WatchdogService.INSTANCE.register(
//                        serviceName,
//                        runner.getServiceRestartDelay(),
//                        runner.getServiceRestartAttempts());
//                }
//            }
        } finally {
            keeper.interrupt();
        }
    }

    @Override
    protected int getMask() {
        return SERVICE_ACCEPT_STOP | SERVICE_ACCEPT_SHUTDOWN;
    }

    @Override
    public void run() {
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());
        super.run();
    }

    @Override
    public void onStop() throws Throwable {

        // TODO: shutdown hooks doesn't invoked when JVM is destroyed with DestroyJavaVM() method.
        // TODO: find solution how FIX JNI service library

        if (runner != null) {
            getLogger().info("Shutting QuantServer...");
            runner.close();
        }
    }

    public void onPause() throws Throwable {
        // do nothing - it's not supported
    }

    @Override
    public void onError(Throwable error, String function) {
        getLogger().log(Level.SEVERE, "Failed to execute " + function + ": ", error);
    }

    //todo: logger
//    @Override
//    public Logger       getLogger() {
//        return TBServerCmd.LogKeeper.LOGGER;
//    }

    private static String getSid(String[] args) {
        for (int i = 0; i < args.length; ++i)
            if ("-sid".equalsIgnoreCase(args[i]))
                if (i + 1 < args.length)
                    return args[i + 1];
        return "";
    }

    // JAVA entry point
    public static void main (String[] args) throws IOException {
        try {
            new TBService(args).run();
        } catch (Throwable x) {
            TBServerCmd.LogKeeper.LOG.error()
                .append("Service initialization failed: ").append(x)
                .commit();
        }
    }
}

