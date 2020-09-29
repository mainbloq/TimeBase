package deltix.qsrv.hf.pub.codec.intp;


import deltix.qsrv.hf.pub.WritableValue;
import deltix.qsrv.hf.pub.codec.UnboundEncoder;
import deltix.qsrv.hf.pub.md.RecordClassDescriptor;
import deltix.util.memory.MemoryDataOutput;

public abstract class WritableValueImpl implements WritableValue {

    FieldEncoder                encoder;
    private EncodingContext     ctx;

    protected WritableValueImpl(FieldEncoder encoder, EncodingContext ctx) {
        this.encoder = encoder;
        this.ctx = ctx;
    }

    @Override
    public void writeBoolean(boolean value) {
        encoder.setBoolean(value, ctx);
    }

    @Override
    public void writeChar(char value) {
        encoder.setChar(value, ctx);
    }

    @Override
    public void writeInt(int value) {
        encoder.setInt(value, ctx);
    }

    @Override
    public void writeLong(long value) {
        encoder.setLong(value, ctx);
    }

    @Override
    public void writeFloat(float value) {
        encoder.setFloat(value, ctx);
    }

    @Override
    public void writeDouble(double value) {
        encoder.setDouble(value, ctx);
    }

    @Override
    public void writeString(CharSequence value) {
        encoder.setString(value, ctx);
    }

    @Override
    public void setArrayLength(int len) {
        encoder.setArrayLength(len, ctx);
    }

    @Override
    public WritableValue nextWritableElement() {
        return encoder.nextWritableElement();
    }

    @Override
    public UnboundEncoder getFieldEncoder(RecordClassDescriptor rcd) {
        return encoder.getFieldEncoder(rcd);
    }

    @Override
    public void writeBinary(byte[] data, int offset, int length) {
        throw new UnsupportedOperationException();
    }

    public void             beginWrite(MemoryDataOutput out) {
        if (encoder instanceof ContainerEncoder)
            ((ContainerEncoder)encoder).beginWrite(out);
    }

    public void             endWrite() {
        if (encoder instanceof ContainerEncoder)
            ((ContainerEncoder)encoder).endWrite();
    }

    @Override
    public void writeNull() {
        encoder.writeNull(ctx);
    }
}
