package deltix.qsrv.hf.tickdb.tests.procs;

import deltix.anvil.util.ShutdownSignal;
import deltix.streaming.MessageSource;
import deltix.gflog.Log;
import deltix.gflog.LogFactory;
import deltix.timebase.messages.InstrumentMessage;
import deltix.qsrv.hf.tickdb.pub.DXTickStream;
import deltix.qsrv.hf.tickdb.pub.LoadingOptions;
import deltix.qsrv.hf.tickdb.pub.TickDBFactory;
import deltix.qsrv.hf.tickdb.pub.TickLoader;
import deltix.qsrv.hf.tickdb.tests.RandomMessageSource;
import deltix.qsrv.hf.tickdb.tests.TestUtils.DBWrapper;
import deltix.qsrv.hf.tickdb.tests.TestUtils.MessagesMonitor;
import deltix.util.cmdline.DefaultApplication;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;

import java.time.Duration;

public class TestLoader extends DefaultApplication implements Runnable {

    private static final Log LOG = LogFactory.getLog(TestLoader.class);

    public TestLoader(String[] args) {
        super(args);
    }

    @Override
    public void run() {
        String streamKey = getArgValue("-stream", "testStream");
        String dbUrl = getArgValue("-db", "dxtick://localhost:8011");
        int maxRate = getIntArgValue("-rate", 100000);
        int symbols = getIntArgValue("-symbols", 100);
        LOG.info().append("Starting load task on stream ")
                .append(streamKey)
                .append(" with max rate ").append(maxRate)
                .append(".").commit();
        TickDBFactory.setApplicationName("test random loader");
        LoadTask task = new LoadTask("LoadTask", new RandomMessageSource(symbols), dbUrl, streamKey, maxRate);
        try {
            task.run();
        } finally {
            LOG.info().append("Finished load task.").commit();
        }
    }

    public static class LoadTask implements Runnable {

        private final MessageSource<InstrumentMessage> messageSource;
        private final String key;
        private final DBWrapper wrapper;
        private final RateLimiterRegistry registry;
        private final String id;
        private final ShutdownSignal shutdownSignal = new ShutdownSignal();

        LoadTask(String id, MessageSource<InstrumentMessage> messageSource, String url, String key, int maxRate) {
            this.id = id;
            this.messageSource = messageSource;
            this.key = key;
            this.wrapper = new DBWrapper(url);
            RateLimiterConfig config = RateLimiterConfig.custom()
                    .limitRefreshPeriod(Duration.ofSeconds(1))
                    .limitForPeriod(maxRate)
                    .build();
            this.registry = RateLimiterRegistry.of(config);
        }

        @Override
        public void run() {
            MessagesMonitor messagesMonitor = new MessagesMonitor(5000, id);
            try {
                messagesMonitor.start();
                while (!shutdownSignal.isSignaled()) {
                    try {
                        runUnchecked(messagesMonitor);
                    } catch (Exception exc) {
                        LOG.error().append(exc).commit();
                    }
                }
            } finally {
                messagesMonitor.stop();
            }
        }

        private void runUnchecked(MessagesMonitor messagesMonitor) {
            RateLimiter limiter = registry.rateLimiter("limiter");
            DXTickStream stream;
            long start = System.currentTimeMillis();
            do {
                stream = wrapper.getDB().getStream(key);
                if (System.currentTimeMillis() - start > 15000 && stream == null) {
                    throw new RuntimeException("Timeout while waiting for stream creation! Stopping loader task.");
                }
            } while (stream == null);
            try (TickLoader loader = stream.createLoader(new LoadingOptions(false))) {
                LOG.info().append("Opened loader to stream ").append(stream.getKey()).append(", starting loading messages.").commit();
                while (messageSource.next()) {
                    if (limiter.acquirePermission()) {
                        loader.send(messageSource.getMessage());
                        messagesMonitor.count();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new TestLoader(args).start();
    }

    public static TestLoader create(String dbUrl, String stream, int loaders) {
        return new TestLoader(new String[]{
                "-db", dbUrl,
                "-stream", stream,
                "-loaders", Integer.toString(loaders)
        });
    }
}
