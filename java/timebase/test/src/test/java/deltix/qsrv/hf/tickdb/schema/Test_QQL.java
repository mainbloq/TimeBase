package deltix.qsrv.hf.tickdb.schema;

/*  ##TICKDB.QQL## */

import deltix.timebase.messages.InstrumentMessage;
import deltix.qsrv.hf.tickdb.TDBRunner;
import deltix.qsrv.hf.tickdb.TDBRunnerBase;
import deltix.qsrv.hf.tickdb.comm.server.TomcatServer;
import deltix.qsrv.hf.tickdb.pub.BackgroundProcessInfo;
import deltix.qsrv.hf.tickdb.pub.DXTickDB;
import deltix.qsrv.hf.tickdb.pub.DXTickStream;
import deltix.qsrv.hf.tickdb.pub.SelectionOptions;
import deltix.qsrv.hf.tickdb.pub.query.InstrumentMessageSource;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import org.junit.experimental.categories.Category;
import deltix.util.JUnitCategories.TickDBQQL;

@Category(TickDBQQL.class)
public class Test_QQL extends TDBRunnerBase {

    final static String CREATE_STATEMENT = "CREATE DURABLE STREAM \"bbo\" 'bbo' (\n" +
            "    CLASS \"deltix.qsrv.hf.pub.MarketMessage\" (\n" +
            "        STATIC \"originalTimestamp\" 'Original Time' TIMESTAMP = NULL,\n" +
            "        \"currencyCode\" 'Currency Code' INTEGER SIGNED (16),\n" +
            "        \"sequenceNumber\" 'Sequence Number' INTEGER\n" +
            "    )\n" +
            "        NOT INSTANTIABLE;\n" +
            "    CLASS \"deltix.qsrv.hf.pub.BestBidOfferMessage\" UNDER \"deltix.qsrv.hf.pub.MarketMessage\" (\n" +
            "        STATIC \"isNational\" 'National BBO' BOOLEAN = true,\n" +
            "        \"bidPrice\" 'Bid Price' FLOAT DECIMAL,\n" +
            "        \"bidSize\" 'Bid Size' FLOAT DECIMAL,\n" +
            "        \"bidExchange\" 'Bid Exchange' VARCHAR ALPHANUMERIC (10),\n" +
            "        \"offerPrice\" 'Offer Price' FLOAT DECIMAL,\n" +
            "        \"offerSize\" 'Offer Size' FLOAT DECIMAL,\n" +
            "        \"offerExchange\" 'Offer Exchange' VARCHAR ALPHANUMERIC (10)\n" +
            "    );\n" +
            ")\n" +
            "OPTIONS (FIXEDTYPE; DF = 12; HIGHAVAILABILITY = FALSE)\n" +
            "COMMENT 'bbo'";

    final static String MODIFY_STATEMENT =  "MODIFY STREAM \"bbo\" (\n" +
            "    CLASS \"deltix.qsrv.hf.pub.MarketMessage\" (\n" +
            "        STATIC \"originalTimestamp\" 'Original Time' TIMESTAMP = NULL,\n" +
            "        \"currencyCode\" 'Currency Code' INTEGER SIGNED (16),\n" +
            "        \"sequenceNumber\" 'Sequence Number' INTEGER\n" +
            "    )\n" +
            "        NOT INSTANTIABLE;\n" +
            "    CLASS \"deltix.qsrv.hf.pub.BestBidOfferMessage\" UNDER \"deltix.qsrv.hf.pub.MarketMessage\" (\n" +
            "        STATIC \"isNational\" 'National BBO' BOOLEAN = true,\n" +
            "        \"bidPrice\" 'Bid Price' FLOAT DECIMAL,\n" +
            "        \"bidSize\" 'Bid Size' FLOAT DECIMAL,\n" +
            "        \"bidExchange\" 'Bid Exchange' VARCHAR ALPHANUMERIC (10),\n" +
            "        \"offerPrice\" 'Offer Price' FLOAT DECIMAL,\n" +
            "        \"offerSize\" 'Offer Size' FLOAT DECIMAL,\n" +
            "        \"offerExchange\" 'Offer Exchange' VARCHAR ALPHANUMERIC (10)\n" +
            "    );\n" +
            ")\n" +
            "OPTIONS (FIXEDTYPE; DF = 132; HIGHAVAILABILITY = FALSE)\n" +
            "COMMENT 'bbo' CONFIRM DROP_DATA";


    @BeforeClass
    public static void start() throws Throwable {
        runner = new TDBRunner(true, true, new TomcatServer());
        runner.startup();
    }

    @Test
    public void testLocal() throws InterruptedException {
        test(getServerDb());
    }

    @Test
    public void testRemote() throws InterruptedException {
        test(getTickDb());
    }

    private BackgroundProcessInfo waitForExecution(DXTickStream stream) throws InterruptedException {

        boolean complete = false;
        while (!complete) {
            BackgroundProcessInfo process = stream.getBackgroundProcess();
            complete = process != null && process.isFinished();
            Thread.sleep(100);
        }

        return stream.getBackgroundProcess();
    }

    public void test(DXTickDB db) throws InterruptedException {

        try( InstrumentMessageSource source = db.executeQuery(CREATE_STATEMENT, new SelectionOptions(true, false)) ) {
            assertTrue(source.next());

            InstrumentMessage message = source.getMessage();
            assertTrue(message.toString(), message.toString().contains("SUCCESS"));
        }

        DXTickStream stream = db.getStream("bbo");

        assertEquals("bbo", stream.getName());

        try ( InstrumentMessageSource source = db.executeQuery(MODIFY_STATEMENT, new SelectionOptions(true, false)) ) {
            assertTrue(source.next());

            InstrumentMessage message = source.getMessage();
            assertTrue(message.toString(), message.toString().contains("SUCCESS"));
        }

        // execution runs in background - wait for it
        waitForExecution(stream);

        assertEquals(null, stream.getName());
        stream.delete();
    }
}
