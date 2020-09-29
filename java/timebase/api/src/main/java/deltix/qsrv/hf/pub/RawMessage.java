package deltix.qsrv.hf.pub;

import deltix.qsrv.hf.pub.codec.*;
import deltix.qsrv.hf.pub.md.*;
import deltix.timebase.messages.InstrumentMessage;
import deltix.timebase.messages.RecordInfo;
import deltix.util.lang.Util;
import deltix.util.memory.*;

/**
 *  Special message class used by Tick DB server. Contains undecoded
 *  message bytes. 
 */
public final class RawMessage extends InstrumentMessage {
    public RecordClassDescriptor    type;
    public byte []                  data;
    public int                      offset;
    public int                      length;
    
    public RawMessage () {
    }
        
    public RawMessage (RecordClassDescriptor type) {
        this.type = type;
    }
        
    public final void                 setBytes (MemoryDataOutput out) {
        setBytes (out, 0);
    }

    public final void                 setBytes (MemoryDataOutput out, int offset) {
        data = out.getBuffer ();
        this.offset = offset;
        length = out.getSize () - offset;
    }

    public final void                 setBytes (byte [] data, int offset, int length) {
        this.data = data;
        this.offset = offset;
        this.length = length;
    }

    /**
     *  Copy bytes from the specified MemoryDataOutput into this message, 
     *  reusing its bytes field, unless it is null or too small.          
     */
    public final void                 copyBytes (MemoryDataOutput out, int offset) {
        this.length = out.getSize () - offset;
        this.offset = 0;
        if (this.data == null || this.data.length < length)
            this.data = new byte[length];

        System.arraycopy (out.getBuffer (), offset, this.data, 0, length);
    }

    public final void                 setUpMemoryDataInput (MemoryDataInput mdi) {
        mdi.setBytes (data, offset, length);
    }

    public final void                 writeTo (MemoryDataOutput out) {
        out.write (data, offset, length);
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
            
            MemoryDataInput         in = new MemoryDataInput (data, offset, length);
            UnboundDecoder          decoder =
                InterpretingCodecMetaFactory.INSTANCE.createFixedUnboundDecoderFactory (type).create ();
            
            decoder.beginRead (in);
            
            while (decoder.nextField ()) {
                NonStaticFieldInfo  df = decoder.getField ();
                sb.append (",");
                sb.append (df.getName ());
                sb.append (":");
                try {
                    sb.append (decoder.getString ());
                } catch (NullValueException e) {
                    sb.append ("<null>");
                }
            }
            
            return (sb.toString ());
        }
    }

    @Override
    public RawMessage copyFrom(RecordInfo template) {
        super.copyFrom(template);

        if (template instanceof RawMessage) {
            RawMessage      t = (RawMessage) template;

            this.length = t.length;
            this.type = t.type;

            this.offset = 0;
            if (this.data == null || this.data.length < t.length)
                this.data = new byte[t.length];

            if (t.data != null)
                System.arraycopy (t.data, t.offset, this.data, 0, t.length);
        }

        return this;
    }


    @Override
    protected RawMessage createInstance() {
        return new RawMessage();
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj)
            return (true);

        if (!(obj instanceof RawMessage))
            return (false);

        final RawMessage    other = (RawMessage) obj;

        return
            type.equals (other.type) &&
            getTimeStampMs() == other.getTimeStampMs() &&
            getNanoTime() == other.getNanoTime() &&
            Util.equals (getSymbol(), other.getSymbol()) &&
            Util.arrayequals (data, offset, length, other.data, other.offset, other.length);
    }

    @Override
    public int hashCode () {
        //  Skip instrumentType - it is rarely a deciding difference
        int             hash =
            Util.xhashCode (type) +
            Util.hashCode (getSymbol()) +
            Util.hashCode (getTimeStampMs());

        for (int ii = 0; ii < length; ii++)
            hash = hash * 31 + data [ii];

        return hash;
    }
    
    public byte [] getData () {
        return (data);
    }
}
