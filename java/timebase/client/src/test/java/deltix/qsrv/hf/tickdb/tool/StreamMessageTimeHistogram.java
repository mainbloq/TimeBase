package deltix.qsrv.hf.tickdb.tool;

import deltix.timebase.messages.InstrumentMessage;
import deltix.qsrv.hf.tickdb.pub.DXTickDB;
import deltix.qsrv.hf.tickdb.pub.DXTickStream;
import deltix.qsrv.hf.tickdb.pub.TickCursor;
import deltix.qsrv.hf.tickdb.pub.TickDBFactory;
import deltix.util.cmdline.DefaultApplication;
import deltix.util.collections.CharSeqToLongMap;
import deltix.util.collections.CharSeqToObjMap;
import org.HdrHistogram.Histogram;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

/**
 * Reads messages from stream and measures time interval between messages for each symbol.
 * Measurements are used to build a message frequency histogram for each symbol.
 *
 * TODO: Integrate into TickDB shell.
 *
 * @author Alexei Osipov
 */
public class StreamMessageTimeHistogram extends DefaultApplication {

    public StreamMessageTimeHistogram(String[] args) {
        super(args);
    }


    @Override
    protected void run() throws Throwable {
        String tb = getArgValue("-db", "dxtick://localhost:8011");
        //int size = getIntArgValue("-size", 100);
        String streamKey = getArgValue("-stream");
        if (streamKey == null) {
            System.out.println("Set stream name using -stream");
            System.exit(-1);
        }

        System.out.println(String.format("    timebase: %s", tb));
        System.out.println("    stream: " + streamKey);

        //System.out.println(String.format("    number of messages (per space): %,d", +total));



        Histogram allSymbols = createHistogram();

        CharSeqToLongMap<CharSequence> lastUpdateBySymbol = new CharSeqToLongMap<>();
        CharSeqToObjMap<CharSequence, Histogram> histogramBySymbol = new CharSeqToObjMap<>();

        long t0 = System.currentTimeMillis();
        try (DXTickDB db = TickDBFactory.createFromUrl(tb)) {
            db.open(false);

            DXTickStream stream = db.getStream(streamKey);

            TickCursor cursor = stream.select(Long.MIN_VALUE, null);
            while (cursor.next()) {
                InstrumentMessage msg = cursor.getMessage();
                CharSequence symbol = msg.getSymbol();
                long nanoTime = msg.getNanoTime();

                Histogram histogram = histogramBySymbol.get(symbol, null);
                if (histogram == null) {
                    // New symbol
                    histogram = createHistogram();
                    histogramBySymbol.put(symbol, histogram);
                    lastUpdateBySymbol.put(symbol, nanoTime);
                } else {
                    // Existing symbol
                    long prevNanoTime = lastUpdateBySymbol.get(symbol, Long.MIN_VALUE);
                    long diff = nanoTime - prevNanoTime;
                    if (diff < 0) {
                        System.out.println(String.format("Reverse time for symbol %s: diff: %d, at time: %d", symbol, diff, nanoTime));
                    }
                    lastUpdateBySymbol.put(symbol, nanoTime);
                    histogram.recordValue(diff);
                    allSymbols.recordValue(diff);
                }
            }

            //stream.delete();
            cursor.close();
        }

        long t1 = System.currentTimeMillis();
        double s = (t1 - t0) * 0.001;
        System.out.printf("Finished in %,.3fs\n", s);

        TimeUnit valueUnit = TimeUnit.NANOSECONDS;
        TimeUnit outputUnit = TimeUnit.MILLISECONDS;
        double outputValueUnitScalingRatio =  valueUnit.convert(1, outputUnit);

        System.out.println("Histogram unit: " + outputUnit);

        System.out.println();
        System.out.println();
        System.out.println("ALL SYMBOLS:");
        allSymbols.outputPercentileDistribution(System.out, 1, outputValueUnitScalingRatio);

        CharSequence[] keys = histogramBySymbol.keysToArray(new CharSequence[histogramBySymbol.size()]);
        for (CharSequence key : keys) {
            String symbol = key.toString();
            Histogram histogram = histogramBySymbol.get(symbol, null);
            System.out.println();
            System.out.println();
            System.out.println("Symbol: " + symbol);
            histogram.outputPercentileDistribution(System.out, 1, outputValueUnitScalingRatio);
        }
    }

    @NotNull
    private Histogram createHistogram() {
        return new Histogram(3);
    }



    public static void main(String[] args) {
        new StreamMessageTimeHistogram(args).start();
    }
}