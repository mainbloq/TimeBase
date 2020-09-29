package deltix.qsrv.hf.tickdb.pub.task;

import deltix.qsrv.hf.tickdb.pub.BufferOptions;
import deltix.qsrv.hf.tickdb.pub.StreamOptions;
import deltix.util.time.Interval;
import deltix.util.time.Periodicity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="StreamChangeTask")
public class StreamChangeTask extends SchemaChangeTask {

    @XmlElement
    public BufferOptions    bufferOptions;

    @XmlElement
    public int              df = StreamOptions.MAX_DISTRIBUTION;

    @XmlElement
    public String           name;

    @XmlElement
    public String           description;

    @XmlElement
    public boolean          ha;

    /**
     *  Stream periodicity, if known.
     */
    @XmlElement
    public Periodicity      periodicity = Periodicity.mkIrregular();
}
