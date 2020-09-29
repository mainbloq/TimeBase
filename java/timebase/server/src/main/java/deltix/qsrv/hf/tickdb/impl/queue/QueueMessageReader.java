package deltix.qsrv.hf.tickdb.impl.queue;

import deltix.qsrv.hf.tickdb.pub.DXTickStream;
import deltix.util.concurrent.IntermittentlyAvailableResource;
import deltix.util.memory.MemoryDataInput;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Alexei Osipov
 */
public interface QueueMessageReader extends Closeable, IntermittentlyAvailableResource {
    /**
     * Attempts to read a message. In case of success message is stored in this reader.
     *
     * Return {@code true} if message was received.
     * If there is no message then:
     * <pre>
     * {@literal
     * if !live => returns false
     * if live && async => throws UnavailableResourceException
     * if live && !async => awaits for message (eventually returning true)
     * }
     * </pre>
     * @return true if message was received, false is there is no data and this is not live reader.
     */
    boolean read() throws IOException;

    /**
     * @return number of bytes already loaded into reader buffer
     */
    // TODO: Consider removing this method from the interface.
    long available();

    DXTickStream        getStream();

    boolean             isLive();

    boolean             isTransient();

    long                getTimestamp();

    long                getNanoTime();

    MemoryDataInput     getInput();
}
