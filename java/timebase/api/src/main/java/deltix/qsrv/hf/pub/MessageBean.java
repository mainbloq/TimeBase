package deltix.qsrv.hf.pub;

import deltix.qsrv.hf.pub.md.DateTimeDataType;
import deltix.qsrv.hf.pub.md.RecordClassDescriptor;
import deltix.timebase.messages.InstrumentMessage;
import deltix.timebase.messages.RecordInfo;
import deltix.util.lang.Util;

import java.util.HashMap;
import java.util.Map;

/**
 *  A representation of arbitrary message that holds a map from field name to
 *  field value.
 */
public class MessageBean extends InstrumentMessage {
    public static final Object      NULL_VALUE = 
        new Object () {
            @Override
            public String       toString () {
                return ("MessageBean.NULL_VALUE");
            }
        };

    public RecordClassDescriptor    type;
    public Map <String, Object>     fields;

    public MessageBean () {
    }

    public MessageBean (RecordClassDescriptor type) {
        this.type = type;
    }

    public final void           initFieldMap () {
        fields = new HashMap <String, Object> ();
    }

    @Override
    public MessageBean copyFrom(RecordInfo template) {
        super.copyFrom(template);

        if (template instanceof MessageBean) {
            MessageBean      t = (MessageBean) template;

            this.type = t.type;

            fields = new HashMap <String, Object> (t.fields);
        }
        return this;
    }

    @Override
    protected MessageBean createInstance() {
        return new MessageBean();
    }

    /**
     *  This method is not very efficient, but will
     *  work for console debug output, etc.
     */
    @Override
    public String               toString () {
        if (type == null)
            return (super.toString ());
        else {

            StringBuilder   sb = new StringBuilder ();

            sb.append (type.getName ());
            sb.append (",");
            sb.append (getSymbol());
            sb.append (",");
            if (getTimeStampMs() == DateTimeDataType.NULL)
                sb.append ("<null>");
            else
                sb.append (getTimeString());

            if (fields != null) {
                for (Map.Entry <String, Object> e : fields.entrySet ()) {
                    sb.append (",");
                    sb.append (e.getKey ());
                    sb.append (":");

                    Object  value = e.getValue ();

                    if (value == NULL_VALUE)
                        sb.append ("<null>");
                    else
                        sb.append (value);
                }
            }

            return (sb.toString ());
        }
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj)
            return (true);

        if (!(obj instanceof MessageBean))
            return (false);

        final MessageBean    other = (MessageBean) obj;

        if (!type.equals (other.type) ||
            getTimeStampMs() != other.getTimeStampMs() ||
            !Util.equals (getSymbol(), other.getSymbol()))
            return (false);

        Map <String, Object>    of = other.fields;

        if (fields == null)
            if (of == null)
                return (true);
            else
                return (false);

        if (of == null)
            return (false);

        int     n = fields.size ();

        if (n != of.size ())
            return (false);

        for (Map.Entry <String, Object> e : fields.entrySet ()) {
            if (!e.getValue ().equals (of.get (e.getKey ())))
                return (false);
        }

        return (true);
    }

    @Override
    public int hashCode () {
        //  Skip instrumentType - it is rarely a deciding difference
        int             hash =
            Util.xhashCode (type) +
            Util.hashCode (getSymbol()) +
            Util.hashCode (getTimeStampMs());

        for (Object v : fields.values ())
            hash = hash * 31 + v.hashCode (); // skip keys

        return hash;
    }
}