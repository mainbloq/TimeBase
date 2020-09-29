package deltix.qsrv.hf.tickdb.pub;

import deltix.streaming.MessageChannel;
import deltix.streaming.MessageSource;
import deltix.timebase.messages.InstrumentMessage;
import deltix.util.progress.ProgressIndicator;

public class TickTools {
    public static long []   getTimeRange (
        TickStream ...          streams
    )
    {
        long []         tr = { Long.MAX_VALUE, Long.MIN_VALUE };

        for (TickStream stream : streams) {
            long []     str = stream.getTimeRange ();

            if (str != null) {
                if (str [0] < tr [0])
                    tr [0] = str [0];

                if (str [1] > tr [1])
                    tr [1] = str [1];
            }
        }

        if (tr [0] == Long.MAX_VALUE)
            return (null);

        return (tr);
    }
    
    public static void                  copy (
        MessageSource<InstrumentMessage>    ac,
        long []                             timeRange,
        MessageChannel<InstrumentMessage> channel,
        ProgressIndicator                   pi
    )
    {
        long    nextReport = 0;
        long    reportInterval = 0;
        long    width = timeRange [1] - timeRange [0];

        if (pi != null) {
            reportInterval = width / 1000;
            nextReport = reportInterval;

            pi.setTotalWork (width);
        }

        while (ac.next ()) {
            InstrumentMessage   msg = (InstrumentMessage) ac.getMessage ();

            final long        t = msg.getTimeStampMs();

            if (t > timeRange [1])
                break;

            channel.send (msg);

            if (pi != null) {
                if (t >= nextReport) {
                    pi.setWorkDone (t - timeRange [0]);
                    nextReport = t + reportInterval;
                }
            }
        }

        if (pi != null)
            pi.setWorkDone (width);
    }
}
