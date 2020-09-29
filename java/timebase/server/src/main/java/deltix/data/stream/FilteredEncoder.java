package deltix.data.stream;

import deltix.timebase.messages.InstrumentMessage;
import deltix.qsrv.hf.tickdb.lang.compiler.sem.PredicateCompiler;
import deltix.qsrv.hf.tickdb.lang.pub.CompilerUtil;
import deltix.qsrv.hf.tickdb.lang.runtime.MessagePredicate;
import deltix.qsrv.hf.tickdb.pub.TickStream;
import deltix.util.memory.MemoryDataOutput;

/**
 *
 */
public class FilteredEncoder<T extends InstrumentMessage> implements MessageEncoder<T> {
    private MessagePredicate    filter;
    private MessageEncoder<T>   encoder;

    public FilteredEncoder(TickStream stream, String filterExpression, MessageEncoder<T> encoder) {
        if (filterExpression != null) {
            PredicateCompiler pc =
                    stream.isPolymorphic () ?
                            new PredicateCompiler (stream.getPolymorphicDescriptors ()) :
                            new PredicateCompiler (stream.getFixedType ());

            this.filter = pc.compile(CompilerUtil.parseExpression(filterExpression));
        }
        this.encoder = encoder;
    }

    @Override
    public boolean encode(T message, MemoryDataOutput out) {
        encoder.encode(message, out);

        if (filter != null)
            return filter.accept(message, getTypeIndex(), out.getBuffer(),
                    encoder.getContentOffset(), out.getSize() - getContentOffset());

        return true;
    }

    @Override
    public int getContentOffset() {
        return encoder.getContentOffset();
    }

    @Override
    public int getTypeIndex() {
        return encoder.getTypeIndex();
    }
}
