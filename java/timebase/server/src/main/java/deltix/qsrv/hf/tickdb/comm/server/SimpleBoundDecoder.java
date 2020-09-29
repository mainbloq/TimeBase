package deltix.qsrv.hf.tickdb.comm.server;

import deltix.qsrv.hf.pub.TypeLoader;
import deltix.qsrv.hf.pub.codec.CodecFactory;
import deltix.qsrv.hf.pub.codec.FixedExternalDecoder;
import deltix.qsrv.hf.pub.md.RecordClassDescriptor;
import deltix.qsrv.hf.tickdb.impl.RecordDecoder;
import deltix.timebase.messages.InstrumentMessage;
import deltix.util.lang.Filter;
import deltix.util.memory.MemoryDataInput;

public class SimpleBoundDecoder implements RecordDecoder<InstrumentMessage>  {

    private final FixedExternalDecoder      decoder;
    private final RecordClassDescriptor     type;
    private final StringBuilder             symBuilder = new StringBuilder();
    private final InstrumentMessage         instance;


    public SimpleBoundDecoder(RecordClassDescriptor type, TypeLoader loader, CodecFactory factory) {
        this.type = type;
        this.decoder = factory.createFixedExternalDecoder(loader, type);

        this.instance = (InstrumentMessage) type.newInstanceNoX (loader);
        decoder.setStaticFields(instance);
        instance.setSymbol(symBuilder);
    }

    @Override
    public RecordClassDescriptor getCurrentType() {
        return type;
    }

    @Override
    public int getCurrentTypeIndex() {
        return 0;
    }

    @Override
    public InstrumentMessage decode(Filter<? super InstrumentMessage> filter, MemoryDataInput in) {

        in.readStringBuilder(symBuilder);
        decoder.decode(in, instance);

        return filter.accept(instance) ? instance : null;
    }
}
