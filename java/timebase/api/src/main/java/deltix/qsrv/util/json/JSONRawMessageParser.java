package deltix.qsrv.util.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import deltix.qsrv.hf.pub.RawMessage;
import deltix.qsrv.hf.pub.UnboundWriter;
import deltix.qsrv.hf.pub.WritableValue;
import deltix.qsrv.hf.pub.codec.CodecMetaFactory;
import deltix.qsrv.hf.pub.codec.CompiledCodecMetaFactory;
import deltix.qsrv.hf.pub.codec.FixedUnboundEncoder;
import deltix.qsrv.hf.pub.codec.UnboundEncoder;
import deltix.qsrv.hf.pub.md.*;
import deltix.util.collections.generated.ByteArrayList;
import deltix.util.collections.generated.ObjectToObjectHashMap;
import deltix.util.memory.MemoryDataOutput;

import java.text.ParseException;

/**
 * Created by Alex Karpovich on 10/3/2018.
 */
public class JSONRawMessageParser extends UnboundWriter<JsonElement> {

    private final ObjectToObjectHashMap<String, FixedUnboundEncoder> encoders = new ObjectToObjectHashMap<> ();
    private final CodecMetaFactory factory = CompiledCodecMetaFactory.INSTANCE;

    private final RecordClassDescriptor[]   descriptors;
    private final RawMessage                raw = new RawMessage();
    private final MemoryDataOutput          out = new MemoryDataOutput();
    private final DateFormatter             dateFormatter = new DateFormatter();

    public JSONRawMessageParser(RecordClassDescriptor[] descriptors) {
        this(descriptors, "type");
    }

    public JSONRawMessageParser(RecordClassDescriptor[] descriptors, String typePropertyName) {
        super(typePropertyName);
        this.descriptors = descriptors;
    }

    private FixedUnboundEncoder getEncoder (final RecordClassDescriptor type) {
        String guid = type.getGuid ();
        FixedUnboundEncoder encoder = encoders.get (guid, null);

        if (encoder == null) {
            encoder = factory.createFixedUnboundEncoderFactory (type).create ();
            encoders.put (guid, encoder);
        }
        return encoder;
    }

    @Override
    public void             writeField(JsonElement value, DataType type, WritableValue w) {
        if (value == null || value.isJsonNull())
            w.writeNull();
        else if (type instanceof IntegerDataType)
            w.writeLong(value.getAsLong());
        else if (type instanceof FloatDataType) {
            if (type.getEncoding().equals(FloatDataType.ENCODING_FIXED_FLOAT))
                w.writeFloat(value.getAsFloat());
            else
                w.writeDouble(value.getAsDouble());
        }
        else if (type instanceof VarcharDataType || type instanceof CharDataType) {
            w.writeString(value.getAsString());
        }
        else if (type instanceof EnumDataType) {

            if (value.isJsonPrimitive()) {
                JsonPrimitive p = (JsonPrimitive) value;
                if (p.isNumber())
                    w.writeLong(p.getAsLong());
                if (p.isString())
                    w.writeString(p.getAsString());
            }
            else
                throw new IllegalArgumentException("Expected primitive value: " + value.getClass().getName());
        }
        else if (type instanceof BooleanDataType) {
            w.writeBoolean(value.getAsBoolean());
        }
        else if (type instanceof DateTimeDataType) {
            long time = parseDateTime(value.getAsString());
            w.writeLong(time);
        }
        else if (type instanceof TimeOfDayDataType) {
            int time = value.getAsInt();
            w.writeInt(time);
        } else if (type instanceof ArrayDataType) {
            writeArray(value, (ArrayDataType) type, w);
        } else if (type instanceof ClassDataType) {
            RecordClassDescriptor rcd = matchObjectType(value, (ClassDataType) type);
            writeObject(value, w.getFieldEncoder(rcd));
        } else if (type instanceof BinaryDataType) {
            ByteArrayList list = new ByteArrayList();
            for (JsonElement jsonElement : value.getAsJsonArray()) {
                list.add(jsonElement.getAsByte());
            }

            w.writeBinary(list.getInternalBuffer(), 0, list.size());
        } else
            throw new RuntimeException("Unrecognized DataType: " + type);
    }

    protected long parseDateTime(String value) {
        try {
            return dateFormatter.fromDateString(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Object getObjectType(JsonElement value) {
        JsonElement element = ((JsonObject) value).get(typePropertyName);
        return element != null ? element.getAsString() : null;
    }

    @Override
    public void writeArray(JsonElement value, ArrayDataType type, WritableValue w) {

        if (value instanceof JsonArray) {
            JsonArray array = ((JsonArray)value);
            writeArray(array, array.size(), type, w);
        } else {
            super.writeArray(value, type, w);
        }
    }

    public void writeObject(JsonElement value, UnboundEncoder encoder) {

        if (value instanceof JsonObject) {
            JsonObject obj = (JsonObject)value;
            while (encoder.nextField()) {
                JsonElement v = obj.get(encoder.getField().getName());
                if (v != null)
                    writeField(v, encoder.getField().getType(), encoder);
            }
        } else {
            throw new RuntimeException("Unrecognized DataType: " + value);
        }
    }

    public RawMessage parse(JsonObject object) {
        RecordClassDescriptor msgType = matchObjectType(object, descriptors);

        FixedUnboundEncoder encoder = getEncoder(msgType);
        out.reset();
        encoder.beginWrite(out);

        writeObject(object, encoder);

        encoder.endWrite();

        raw.type = msgType;
        raw.setBytes(out);

        if (object.has("symbol"))
            raw.setSymbol(object.get("symbol").getAsString());
        else
            raw.setSymbol("");

        if (object.has("timestamp"))
            raw.setTimeStampMs(parseDateTime(object.get("timestamp").getAsString()));
        else
            raw.setTimeStampMs(Long.MIN_VALUE);

        return raw;
    }
}
