package deltix.qsrv.hf.tickdb.impl;

import deltix.qsrv.dtb.store.pub.TSMessageProducer;
import deltix.qsrv.hf.codec.CodecUtils;
import deltix.timebase.messages.InstrumentMessage;
import deltix.qsrv.hf.pub.RawMessage;
import deltix.qsrv.hf.pub.TypeLoader;
import deltix.qsrv.hf.pub.codec.CodecFactory;
import deltix.qsrv.hf.pub.codec.FixedBoundEncoder;
import deltix.qsrv.hf.pub.codec.RecordTypeMap;
import deltix.qsrv.hf.pub.md.RecordClassDescriptor;
import deltix.util.memory.MemoryDataOutput;

public abstract class MessageProducer<T extends InstrumentMessage> implements TSMessageProducer {
    protected T         message;
    protected int       code;

    public abstract int beginWrite(T msg);
}

class RawProducer extends MessageProducer<RawMessage> {

    private final RecordTypeMap<RecordClassDescriptor>      typeMap;

    protected RawProducer(RecordClassDescriptor[] types) {
        typeMap = new RecordTypeMap<RecordClassDescriptor>(types);
    }

    public int beginWrite(RawMessage msg) {
        message = msg;
        return code = typeMap.getCode(message.type);
    }

    @Override
    public void writeBody(MemoryDataOutput out) {
        message.writeTo(out);
    }
}

class Producer extends MessageProducer<InstrumentMessage> {

    private final RecordTypeMap<Class>      typeMap;
    private final FixedBoundEncoder[]       encoders;

    protected Producer(RecordClassDescriptor[] types, TypeLoader loader, CodecFactory factory) {
        this.encoders = new FixedBoundEncoder[types.length];

        final Class <?> []              classes = new Class <?> [types.length];

        for (int i = 0; i < types.length; i++) {
            FixedBoundEncoder encoder = encoders[i] = factory.createFixedBoundEncoder(loader, types[i]);
            CodecUtils.validateBoundClass(encoder.getClassInfo());
            classes [i] = encoder.getClassInfo().getTargetClass ();
        }

        typeMap = new RecordTypeMap<Class> (classes);
    }

    public int beginWrite(InstrumentMessage msg) {
        message = msg;
        return code = typeMap.getCode (message.getClass ());
    }

    @Override
    public void writeBody(MemoryDataOutput out) {
        encoders[code].encode(message, out);
    }
}


