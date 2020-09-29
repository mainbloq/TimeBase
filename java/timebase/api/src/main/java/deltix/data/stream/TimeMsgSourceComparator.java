package deltix.data.stream;

import deltix.streaming.MessageSource;
import deltix.timebase.messages.TimeStampedMessage;
import deltix.util.lang.MathUtil;

import java.util.Comparator;

/**
 *
 */
public class TimeMsgSourceComparator 
    implements Comparator<MessageSource<? extends TimeStampedMessage>>
{
    private TimeMsgSourceComparator () { }
    
    public static final Comparator <MessageSource <? extends TimeStampedMessage>> INSTANCE =
        new TimeMsgSourceComparator ();
    
    public int      compare (
        MessageSource <? extends TimeStampedMessage> o1,
        MessageSource <? extends TimeStampedMessage> o2
    )
    {
        long            ts1 = o1.getMessage().getNanoTime();
        long            ts2 = o2.getMessage().getNanoTime();
        
        return (MathUtil.sign (ts1 - ts2));
    }
}
