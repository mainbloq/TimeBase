package deltix.qsrv.hf.tickdb.http.rest;

import deltix.qsrv.hf.tickdb.StreamConfigurationHelper;
import deltix.streaming.MessageChannel;
import deltix.timebase.api.messages.BarMessage;

import deltix.qsrv.hf.pub.RawMessage;
import deltix.qsrv.hf.pub.TypeLoaderImpl;
import deltix.qsrv.hf.pub.codec.CodecFactory;
import deltix.qsrv.hf.pub.codec.FixedBoundEncoder;
import deltix.qsrv.hf.pub.md.RecordClassDescriptor;
import deltix.qsrv.hf.pub.md.RecordClassSet;
import deltix.qsrv.hf.tickdb.http.SecuredBaseTest;
import deltix.qsrv.hf.tickdb.pub.StreamOptions;
import deltix.qsrv.hf.tickdb.pub.StreamScope;
import deltix.util.JUnitCategories;
import deltix.util.memory.MemoryDataOutput;
import deltix.util.time.GMT;
import deltix.util.time.TimeConstants;
import org.junit.Test;
import org.junit.experimental.categories.Category;


/**
 *
 */
@Category(JUnitCategories.TickDBFast.class)
public class TestSecuredRESTLoad extends SecuredBaseTest {

    private static final String[] SYMBOLS = {"AAPL-HTTP"};

    @Test
    public void loadRestBars() throws Exception {
        loadRestBars(true, false, ADMIN_USER, ADMIN_PASS);
    }

    private static void loadRestBars(boolean useCompression, boolean isBigEndian, String user, String password) throws Exception {
        String streamName = "1min";

        StreamOptions options = StreamOptions.fixedType(StreamScope.DURABLE, streamName, null, 0, StreamConfigurationHelper.mkUniversalBarMessageDescriptor());
        createStream(streamName, options);

        final BarMessage bar = new BarMessage();
        bar.setTimeStampMs(GMT.parseDateTime("2013-10-08 09:00:00").getTime());
        bar.setOpen(1.0);
        bar.setHigh(bar.getOpen());
        bar.setLow(bar.getOpen());
        bar.setClose(bar.getOpen());
        bar.setVolume(bar.getOpen());

        final RecordClassSet rcs = Utils.requestSchema(getPath("tb/xml"), streamName, user, password);
        final RecordClassDescriptor type = rcs.getTopType(0);
        final FixedBoundEncoder encoder = CodecFactory.COMPILED.createFixedBoundEncoder(TypeLoaderImpl.DEFAULT_INSTANCE, type);
        final MemoryDataOutput mdo = new MemoryDataOutput();

        try {
            Utils.getRestLoader(
                TB_HOST, runner.getPort(), useCompression, isBigEndian, streamName, rcs.getTopTypes(), null, null);
        } catch (Throwable t) {
            //ok
        }

        try {
            Utils.getRestLoader(
                TB_HOST, runner.getPort(), useCompression, isBigEndian, streamName, rcs.getTopTypes(), ADMIN_USER, "wrong_pass");
        } catch (Throwable t) {
            //ok
        }

        try {
            Utils.getRestLoader(
                TB_HOST, runner.getPort(), useCompression, isBigEndian, streamName, rcs.getTopTypes(), "wrong_user", ADMIN_PASS);
        } catch (Throwable t) {
            //ok
        }

        final MessageChannel<RawMessage> loader = Utils.getRestLoader(
            TB_HOST, runner.getPort(), useCompression, isBigEndian, streamName, rcs.getTopTypes(), user, password);
        RawMessage raw = new RawMessage();
        raw.type = type;

        for (int i = 0; i < 1000; i++) {
            for (String symbol : SYMBOLS) {
                bar.setSymbol(symbol);

                mdo.reset();
                encoder.encode(bar, mdo);
                raw.setSymbol(bar.getSymbol());
                raw.setTimeStampMs(bar.getTimeStampMs());
                raw.setBytes(mdo);
                loader.send(raw);

                bar.setOpen(bar.getOpen() + 1);
            }

            bar.setTimeStampMs(bar.getTimeStampMs() + TimeConstants.MINUTE);
            bar.setHigh(bar.getHigh() + 1);
            bar.setLow(bar.getLow() + 0.1);
        }

        loader.close();
    }
}
