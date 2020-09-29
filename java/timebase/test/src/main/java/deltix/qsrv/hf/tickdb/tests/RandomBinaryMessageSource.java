package deltix.qsrv.hf.tickdb.tests;

import deltix.streaming.MessageSource;
import deltix.timebase.messages.InstrumentMessage;
import deltix.timebase.messages.service.BinaryMessage;
import rtmath.containers.BinaryArray;

import java.util.ArrayList;
import java.util.Random;

public class RandomBinaryMessageSource implements MessageSource<BinaryMessage> {

    private final byte[] bytes;
    private final Random random = new Random(System.currentTimeMillis());
    private final BinaryMessage message;
    private final BinaryArray array;
    private final ArrayList<String> symbols;

    public RandomBinaryMessageSource(int size, int symbols) {
        this.bytes = new byte[size];
        this.symbols = TestUtils.getRandomStringsList(symbols - 1);
        this.symbols.add("DELETE_SYMBOL");
        this.message = new BinaryMessage();
        this.array = new BinaryArray(size);
        this.message.setData(this.array);
    }

    private String getSymbol() {
        return symbols.get(random.nextInt(symbols.size()));
    }

    private void setInstrumentMessage(InstrumentMessage instrumentMessage) {
        instrumentMessage.setSymbol(getSymbol());
        //instrumentMessage.setInstrumentType(InstrumentType.FX);
    }

    private void setBinaryMessage(BinaryMessage binaryMessage) {
        setInstrumentMessage(binaryMessage);
        array.clear();
        random.nextBytes(bytes);
        array.append(bytes);
    }

    public String removeSymbol() {
        synchronized (symbols) {
            return symbols.remove(symbols.size() - 1);
        }
    }

    @Override
    public BinaryMessage getMessage() {
        setBinaryMessage(message);
        return message;
    }

    @Override
    public boolean next() {
        return true;
    }

    @Override
    public boolean isAtEnd() {
        return false;
    }

    @Override
    public void close() {
        array.clear();
    }
}
