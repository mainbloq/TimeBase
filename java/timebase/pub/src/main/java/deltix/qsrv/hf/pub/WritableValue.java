package deltix.qsrv.hf.pub;

import deltix.qsrv.hf.pub.codec.UnboundEncoder;
import deltix.qsrv.hf.pub.md.RecordClassDescriptor;

/**
 *  Write access to a value.
 */
public interface WritableValue {
    public void                 writeBoolean (boolean value);

    public void                 writeChar (char value);

    public void                 writeInt (int value);

    public void                 writeLong (long value);

    public void                 writeFloat (float value);

    public void                 writeDouble (double value);

    public void                 writeString (CharSequence value);

    /**
     * Set length of the array. It must be called prior to nextWritableElement
     * @param len length of the array
     */
    public void                 setArrayLength (int len);

    /**
     * <p>
     * Returns a transient (reused by interface implementation) WritableValue object for the next element of the array.
     * </p>
     * <p>
     * {@link #setArrayLength} method must be called before this method.<br>
     * Client code must not cache the reference for further usage.<br>
     * Usage sample:</p><pre>
     *  encoder.setArrayLength(10);
     *  for(int i=0; i&lt;10; i++) {
     *      WritableValue v = encoder.nextWritableElement (i);
     *      v.setInt(i);
     *  }
     * </pre>
     * @return reference to WritableValue object
     * @throws java.util.NoSuchElementException when when the next element lays beyond array boundary
     */
    public WritableValue        nextWritableElement ();

    //public UnboundEncoder       getFieldEncoder();

    public UnboundEncoder       getFieldEncoder(RecordClassDescriptor rcd);

    public void                 writeBinary (byte [] data, int offset, int length);

    public void                 writeNull ();
}
