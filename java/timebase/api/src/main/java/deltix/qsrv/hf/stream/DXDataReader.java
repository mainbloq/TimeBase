package deltix.qsrv.hf.stream;

import deltix.data.stream.ConsumableMessageSource;
import deltix.timebase.messages.InstrumentMessage;
import deltix.qsrv.hf.pub.md.RecordClassDescriptor;
import deltix.qsrv.util.archive.DXDataEntry;
import deltix.qsrv.util.archive.DXDataInputStream;
import deltix.qsrv.util.archive.DXHeaderEntry;
import deltix.util.lang.Disposable;
import deltix.util.lang.Util;
import deltix.util.memory.DataExchangeUtils;


import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class DXDataReader implements Disposable{

    private final DXDataInputStream     in;
    private ClassSet                    classes;
    private DXDataEntry                 current;
    
    private final long[]                range = new long[2];

    public DXDataReader(File file) throws IOException {
        this.in = new DXDataInputStream(file);
        DXHeaderEntry header = (DXHeaderEntry) in.getNextEntry();
        
        range[0] = DataExchangeUtils.readLong(header.data, 0);
        range[1] = DataExchangeUtils.readLong(header.data, 8);
    }

    public ConsumableMessageSource<InstrumentMessage> readBlock() throws IOException {
        DXDataEntry entry = (DXDataEntry) in.getNextEntry();
        if (entry != null) {
            if (entry.getName().endsWith("xml"))
                classes = Protocol.readTypes(new DataInputStream(in));

            current = (DXDataEntry) in.getNextEntry();
            GZIPInputStream gzip = new GZIPInputStream(new BufferedInputStream(in, 1 << 14), 1 << 14);
            return new MessageReader2(gzip, current.getSize(), classes.getContentClasses());
        }

        return null;
    }

    public boolean nextBlock() throws IOException {
        DXDataEntry entry = (DXDataEntry) in.getNextEntry();

        if (entry != null) {
            if (entry.getName().endsWith("xml"))
                Protocol.readTypes(new DataInputStream(in));

            current = (DXDataEntry) in.getNextEntry();
        }

        return entry != null;
    }

	public RecordClassDescriptor[] 	   getBlockTypes() {
        return classes.getContentClasses();
    }

	public String 	                    getBlockName() {
        return current.getName();
    }

    public long[] 						getTimeRange() {
        return range;
    }

    @Override
    public void                         close() {
        Util.close(in);
    }
}
