package deltix.qsrv.hf.tickdb.ui.tbshell.virtualplayer;

import deltix.timebase.messages.SchemaElement;
import deltix.timebase.messages.RecordInfo;
import deltix.timebase.messages.RecordInterface;
import deltix.timebase.messages.TypeConstants;

/**
 */
@SchemaElement(
    name = "deltix.timebase.api.messages.playback.PlaybackFrequencyChangedEvent",
    title = "Event"
)
public class PlaybackFrequencyChangedEvent extends PlaybackEvent implements RecordInterface {
  public static final String CLASS_NAME = PlaybackFrequencyChangedEvent.class.getName();

  /**
   * This field contains new virtual time slice duration (virtual clock step size) in milliseconds.
   * Value 500 means virtual clock will be updated 2 times during 1 virtual second.
   */
  protected double frequency = TypeConstants.IEEE64_NULL;

  public PlaybackFrequencyChangedEvent() {
    super();
    eventType = PlaybackEventType.SPEED_CHANGED;
  }

  /**
   * @return Event Type
   */
  @Override
  public PlaybackEventType getEventType() {
    return eventType;
  }

  /**
   * @param value - Event Type
   */
  @Override
  public void setEventType(PlaybackEventType value) {
    this.eventType = value;
  }

  /**
   * @return true if Event Type is not null
   */
  @Override
  public boolean hasEventType() {
    return eventType != null;
  }

  /**
   */
  @Override
  public void nullifyEventType() {
    this.eventType = null;
  }

  /**
   * This field contains new virtual time slice duration (virtual clock step size) in milliseconds.
   * Value 500 means virtual clock will be updated 2 times during 1 virtual second.
   * @return Frequency
   */
  @SchemaElement
  public double getFrequency() {
    return frequency;
  }

  /**
   * This field contains new virtual time slice duration (virtual clock step size) in milliseconds.
   * Value 500 means virtual clock will be updated 2 times during 1 virtual second.
   * @param value - Frequency
   */
  public void setFrequency(double value) {
    this.frequency = value;
  }

  /**
   * This field contains new virtual time slice duration (virtual clock step size) in milliseconds.
   * Value 500 means virtual clock will be updated 2 times during 1 virtual second.
   * @return true if Frequency is not null
   */
  public boolean hasFrequency() {
    return !Double.isNaN(frequency);
  }

  /**
   * This field contains new virtual time slice duration (virtual clock step size) in milliseconds.
   * Value 500 means virtual clock will be updated 2 times during 1 virtual second.
   */
  public void nullifyFrequency() {
    this.frequency = TypeConstants.IEEE64_NULL;
  }

  /**
   * Creates new instance of this class.
   * @return new instance of this class.
   */
  @Override
  protected PlaybackFrequencyChangedEvent createInstance() {
    return new PlaybackFrequencyChangedEvent();
  }

  /**
   * Method nullifies all instance properties
   */
  @Override
  public PlaybackFrequencyChangedEvent nullify() {
    super.nullify();
    nullifyFrequency();
    return this;
  }

  /**
   * Resets all instance properties to their default values
   */
  @Override
  public PlaybackFrequencyChangedEvent reset() {
    super.reset();
    frequency = TypeConstants.IEEE64_NULL;
    return this;
  }

  /**
   * Method copies state to a given instance
   */
  @Override
  public PlaybackFrequencyChangedEvent clone() {
    PlaybackFrequencyChangedEvent t = createInstance();
    t.copyFrom(this);
    return t;
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    boolean superEquals = super.equals(obj);
    if (!superEquals) return false;
    if (!(obj instanceof PlaybackFrequencyChangedEvent)) return false;
    PlaybackFrequencyChangedEvent other =(PlaybackFrequencyChangedEvent)obj;
    if (hasFrequency() != other.hasFrequency()) return false;
    if (hasFrequency() && getFrequency() != other.getFrequency()) return false;
    return true;
  }

  /**
   * Returns a hash code value for the object. This method is * supported for the benefit of hash tables such as those provided by.
   */
  @Override
  public int hashCode() {
    int hash = super.hashCode();
    if (hasFrequency()) {
      hash = hash * 31 + ((int)(Double.doubleToLongBits(getFrequency()) ^ (Double.doubleToLongBits(getFrequency()) >>> 32)));
    }
    return hash;
  }

  /**
   * Method copies state to a given instance
   * @param template class instance that should be used as a copy source
   */
  @Override
  public PlaybackFrequencyChangedEvent copyFrom(RecordInfo template) {
    super.copyFrom(template);
    if (template instanceof PlaybackFrequencyChangedEvent) {
      PlaybackFrequencyChangedEvent t = (PlaybackFrequencyChangedEvent)template;
      if (t.hasFrequency()) {
        setFrequency(t.getFrequency());
      } else {
        nullifyFrequency();
      }
    }
    return this;
  }

  /**
   * @return a string representation of this class object.
   */
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    return toString(str).toString();
  }

  /**
   * @return a string representation of this class object.
   */
  @Override
  public StringBuilder toString(StringBuilder str) {
    str.append("{ \"$type\":  \"PlaybackFrequencyChangedEvent\"");
    if (hasFrequency()) {
      str.append(", \"frequency\": ").append(getFrequency());
    }
    if (hasEventType()) {
      str.append(", \"eventType\": \"").append(getEventType()).append("\"");
    }
    if (hasPlaybackTime()) {
      str.append(", \"playbackTime\": \"").append(getPlaybackTime()).append("\"");
    }
    if (hasTimeStampMs()) {
      str.append(", \"timestamp\": \"").append(formatNanos(getTimeStampMs(), (int)getNanoTime())).append("\"");
    }
    if (hasSymbol()) {
      str.append(", \"symbol\": \"").append(getSymbol()).append("\"");
    }
    str.append("}");
    return str;
  }
}
