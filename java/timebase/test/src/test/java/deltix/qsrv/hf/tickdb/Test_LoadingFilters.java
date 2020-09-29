package deltix.qsrv.hf.tickdb;


import deltix.timebase.api.messages.*;
import deltix.qsrv.hf.pub.md.RecordClassDescriptor;
import deltix.qsrv.hf.tickdb.pub.*;
import deltix.util.lang.Util;
import org.junit.Ignore;
import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.experimental.categories.Category;
import deltix.util.JUnitCategories.TickDBFast;

/**
 * Test for Filters implementation on the client side. Since QQL engine moved to the server side, this functionality is
 * currently disabled.
 */
@Category(TickDBFast.class)
public class Test_LoadingFilters extends TDBRunnerBase {

    @Ignore
    public void testSingleLocal() {
        testSingle(runner.getServerDb());
    }

    @Ignore
    public void testSingleRemote() {
        testSingle(runner.getTickDb());
    }

    @Ignore
    public void testSeveralLocal() {
        testSeveral(runner.getServerDb());
    }

    @Ignore
    public void testSeveralRemote() {
        testSeveral(runner.getTickDb());
    }

    public void testSingle(DXTickDB tickdb) {

        RecordClassDescriptor classDescriptor = StreamConfigurationHelper.mkBarMessageDescriptor(
                null, "", null,
                "DECIMAL(4)",
                "DECIMAL(0)"
        );

        DXTickStream stream = tickdb.createStream("bars",
                StreamOptions.fixedType(StreamScope.DURABLE, "bars", null, 0, classDescriptor));

        final int[] errors = new int[1];

        final LoadingErrorListener listener = new LoadingErrorListener() {
            public void onError(LoadingError e) {
                errors[0]++;
            }
        };

        LoadingOptions options = new LoadingOptions();
        options.filterExpression = "volume > 2000";

        TickLoader      loader = stream.createLoader (options);
        loader.addEventListener(listener);
        GregorianCalendar calendar = new GregorianCalendar(2011, 1, 1);

        try {
            BarMessage message = new BarMessage();
            message.setSymbol("ES1");
            message.setTimeStampMs(calendar.getTimeInMillis());
            message.setCurrencyCode((short)840);

            Random rnd = new Random();
            for (int i = 0; i < 5; i++)
            {
                message.setHigh(rnd.nextDouble() * 100);
                message.setOpen(message.getHigh() - rnd.nextDouble()*10);
                message.setClose(message.getHigh() - rnd.nextDouble()*10);
                message.setLow(Math.min(message.getOpen(), message.getClose()) - rnd.nextDouble()*10);
                message.setVolume(1000 * i);

                loader.send(message);
            }
        } finally {
            Util.close(loader);
        }

        try (TickCursor cursor = stream.select(0, null)) {
            int count = 0;
            while (cursor.next()) {
                count++;

                assertEquals(cursor.getCurrentType().getName(), BarMessage.CLASS_NAME);
                assertTrue (((BarMessage)cursor.getMessage()).getVolume() > 2000);
            }

            assertEquals(2, count);

        } finally {
            stream.delete();
        }
    }

    public void testSeveral(DXTickDB tickdb) {

        RecordClassDescriptor descriptor = StreamConfigurationHelper.mkMarketMessageDescriptor(null);
        RecordClassDescriptor barsDescriptor = StreamConfigurationHelper.mkBarMessageDescriptor(
                descriptor, "", null,
                "DECIMAL(4)",
                "DECIMAL(0)"
        );

        RecordClassDescriptor tradeDescriptor = StreamConfigurationHelper.mkTradeMessageDescriptor(
                descriptor, "", null,
                "DECIMAL(4)",
                "DECIMAL(0)"
        );

        DXTickStream stream = tickdb.createStream("mixed",
                StreamOptions.polymorphic(StreamScope.DURABLE, "mixed", null, 0, barsDescriptor, tradeDescriptor));

        final int[] errors = new int[1];

        final LoadingErrorListener listener = new LoadingErrorListener() {
            public void onError(LoadingError e) {
                errors[0]++;
            }
        };

        LoadingOptions options = new LoadingOptions();
        options.filterExpression = "size > 1 and this is deltix.timebase.api.messages.TradeMessage";

        TickLoader      loader = stream.createLoader (options);
        loader.addEventListener(listener);
        GregorianCalendar calendar = new GregorianCalendar(2011, 1, 1);

        try {
            TradeMessage trade = new TradeMessage();
            trade.setSymbol("ES1");
            trade.setTimeStampMs(calendar.getTimeInMillis());
            trade.setCurrencyCode((short)840);

            for (int i = 0; i < 5; i++)
            {
                trade.setSize(i);
                trade.setPrice(i * 100);
                loader.send(trade);
            }

            BarMessage bar = new BarMessage();
            bar.setSymbol("ORCL");
            bar.setTimeStampMs(calendar.getTimeInMillis());
            bar.setCurrencyCode((short)840);

            for (int i = 0; i < 5; i++)
            {
                bar.setVolume(1000 * i);
                loader.send(bar);
            }
        } finally {
            Util.close(loader);
        }

        try (TickCursor cursor = stream.select(0, null)) {
            int count = 0;
            while (cursor.next()) {
                count++;

                assertEquals(cursor.getCurrentType().getName(), TradeMessage.CLASS_NAME);

                assertTrue (((TradeMessage)cursor.getMessage()).getSize() > 1);
            }

            assertEquals(3, count);

        } finally {
            stream.delete();
        }
    }

}
