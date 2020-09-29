package deltix.data.stream;

import deltix.streaming.MessageSource;
import deltix.timebase.messages.InstrumentMessage;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author Alexei Osipov
 */
public class MessageSourceMultiplexerFixedTest {
    @Test
    public void closeFeed() throws Exception {
        ArrayList<MessageSource<InstrumentMessage>> sources = new ArrayList<>();
        MessageSourceMultiplexerFixed<InstrumentMessage> fmx = new MessageSourceMultiplexerFixed<InstrumentMessage>(null, sources, true, Long.MIN_VALUE, new Object());
        fmx.syncNext();
    }

}