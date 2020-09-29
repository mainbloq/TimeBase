package deltix.qsrv.hf.tickdb.schema;

import deltix.qsrv.hf.pub.*;
import deltix.qsrv.hf.pub.md.*;
import deltix.qsrv.hf.pub.codec.*;
import deltix.streaming.MessageSource;
import deltix.streaming.MessageChannel;
import deltix.qsrv.hf.tickdb.schema.encoders.MixedWritableValue;
import deltix.timebase.messages.InstrumentMessage;
import deltix.util.collections.SmallArrays;
import deltix.util.memory.MemoryDataOutput;
import deltix.util.memory.MemoryDataInput;

import java.util.*;

public class SchemaConverter {
    private final MemoryDataOutput  buffer = new MemoryDataOutput();
    private final MemoryDataInput   input = new MemoryDataInput();

    private final RawMessage        result = new RawMessage();

    private final MetaDataChange    change;

    private byte[] tmp;

    private HashMap<RecordClassDescriptor, ClassDescriptorMapping> typeMappings = new
            HashMap<RecordClassDescriptor, ClassDescriptorMapping>();

    public SchemaConverter(MetaDataChange change) {
        this.change = change;        
        
        // create mappings
        RecordClassDescriptor[] input = change.source.getContentClasses();

        // create mapping for each source type
        for (RecordClassDescriptor source : input) {
            ClassDescriptorChange typeChange = change.getChange(source, null);
            if (typeChange != null) {
                if (typeChange.getSource() != null && typeChange.getTarget() != null) {
                    typeMappings.put(source, new ClassDescriptorMapping(typeChange, change.mapping));
                }
            } else {
                RecordClassDescriptor target = (RecordClassDescriptor)
                    change.mapping.findClassDescriptor(source, change.target);

                if (target != null)
                    typeMappings.put(source, new ClassDescriptorMapping(source, target, change.mapping));
            }
        }

        // make sure that we do not miss additional changes
        for (ClassDescriptorChange typeChange : change.changes) {

            if (typeChange.getSource() instanceof RecordClassDescriptor) {
                RecordClassDescriptor source = (RecordClassDescriptor)typeChange.getSource();

                if (!typeMappings.containsKey(source) && typeChange.getTarget() != null)
                    typeMappings.put(source, new ClassDescriptorMapping(typeChange, change.mapping));
            }

        }
    }

    public boolean canConvert() {
        return canConvert(change);
    }
    
    public static boolean canConvert(MetaDataChange change) {
        ArrayList input =
                new ArrayList<RecordClassDescriptor>(Arrays.asList(change.source.getContentClasses()));

        for (ClassDescriptorChange c : change.changes) {
            if (c.getSource() != null && c.getTarget() != null)
                return true;
            else if (c.getSource() != null)
                input.remove(c.getSource());
        }

        return input.size() != 0;
    }

    public final RawMessage convert(RawMessage msg) {
        ClassDescriptorMapping mapping = typeMappings.get(msg.type);
        
        if (mapping != null && mapping.mappings != null) {

            if (mapping.decoder == null)
                throw new IllegalStateException("Decoder for " + msg.type + " is not defined.");

            buffer.reset(0);
            mapping.encoder.beginWrite(buffer);

            msg.setUpMemoryDataInput(input);
            mapping.decoder.beginRead(input);

            for (FieldMapping fieldMapping : mapping.mappings) {
                mapping.encoder.nextField();

                convertField(fieldMapping, mapping.decoder, mapping.encoder);
            }

            mapping.encoder.endWrite();

            result.type = mapping.target;
            result.setSymbol(msg.getSymbol());
            result.setNanoTime(msg.getNanoTime());
            result.setBytes(buffer, 0);

            return result;
        } else if (mapping != null && mapping.decoder != null) {
            result.copyFrom(msg);
            result.type = mapping.target;

            return result;
        }

        return null;
    }

    private boolean convertObject(ReadableValue reader, ClassDataType inType, MixedWritableValue writable, ClassDataType outType) {

        UnboundDecoder decoder = reader.getFieldDecoder();

        RecordClassDescriptor descriptor = decoder.getClassInfo().getDescriptor();

        ClassDescriptorMapping mapping = typeMappings.get(descriptor);

        if (mapping != null) {

            UnboundEncoder encoder = writable.getFieldEncoder(mapping.target);

            for (FieldMapping fieldMapping : mapping.mappings) {
                encoder.nextField();

                convertField(fieldMapping, decoder, encoder);
            }
            encoder.endWrite();

            return true;
        } else { // type is not changed - copy "as is"

            int index = SmallArrays.indexOf(descriptor, inType.getDescriptors());

            UnboundEncoder encoder = writable.getFieldEncoder(outType.getDescriptors()[index]);
            MixedWritableValue out = writable.clone(encoder);

            while (decoder.nextField()) {
                encoder.nextField();

                convertField(decoder, decoder.getField().getType(), null, out, encoder.getField().getType(), null);
            }
            encoder.endWrite();

            return true;
        }
    }

    public void convert(MessageSource<InstrumentMessage> in, MessageChannel<InstrumentMessage> out) {
        while (in.next()) {
            RawMessage msg = (RawMessage) in.getMessage();
            RawMessage result = convert(msg);
            if (result != null)
                out.send(result);
        }
    }

    private void convertField(FieldMapping mapping, UnboundDecoder decoder, UnboundEncoder encoder) {
        MixedWritableValue writable = mapping.getWritable(encoder);

        if (mapping.source == null) {
            // field may be ignored
            writable.writeDefault();
        } else {
            decoder.seekField(mapping.sourceIndex);
            convertField(decoder, mapping.sourceType, mapping.sourceTypeIndex, writable, mapping.targetType, mapping.targetTypeIndex);
        }
    }

    private boolean convertField(ReadableValue in, DataType inType, DataTypeIndex inTypeIndex,
                              MixedWritableValue out, DataType outType, DataTypeIndex outTypeIndex) {

        DataTypeIndex from = inTypeIndex == null ? FieldMapping.getTypeIndex(inType) : inTypeIndex;
        DataTypeIndex to = outTypeIndex == null ? FieldMapping.getTypeIndex(outType) : outTypeIndex;

        try {
            switch (from) {
                case Boolean:
                    boolean bValue = in.getBoolean();
                    switch (to) {
                        case Boolean: out.writeBoolean(bValue); break;
                        case Int: out.writeInt(bValue ? 1 : 0); break;
                        case Long: out.writeLong(bValue ? 1 : 0); break;
                        case Float: out.writeFloat(bValue ? 1.0f : 0.0f); break;
                        case Double: out.writeDouble(bValue ? 1.0 : 0.0); break;
                        case String: out.writeString(String.valueOf(bValue)); break;
                        case Enum: out.writeEnum(String.valueOf(bValue)); break;
                    }
                    break;

                case Int:
                    int iValue = in.getInt();
                    switch (to) {
                        case Boolean: out.writeBoolean(iValue != 0); break;
                        case Int: out.writeInt(iValue); break;
                        case Long: out.writeLong(iValue); break;
                        case Float: out.writeFloat(iValue); break;
                        case Double: out.writeDouble(iValue); break;
                        case String: out.writeString(String.valueOf(iValue)); break;
                        case Enum: out.writeEnum(String.valueOf(iValue)); break;
                    }
                    break;

                case Long:
                    long lValue = in.getLong();
                    switch (to) {
                        case Boolean: out.writeBoolean(lValue != 0); break;
                        case Int: out.writeInt(lValue); break;
                        case Long: out.writeLong(lValue); break;
                        case Float: out.writeFloat(lValue); break;
                        case Double: out.writeDouble(lValue); break;
                        case String: out.writeString(String.valueOf(lValue)); break;
                        case Enum: out.writeEnum(String.valueOf(lValue)); break;
                    }
                    break;

                case Float:
                    float fValue = in.getFloat();
                    switch (to) {
                        case Boolean: out.writeBoolean(fValue != 0); break;
                        case Int: out.writeInt(fValue); break;
                        case Long: out.writeLong(fValue); break;
                        case Float: out.writeFloat(fValue); break;
                        case Double: out.writeDouble(fValue); break;
                        case String: out.writeString(String.valueOf(fValue)); break;
                        case Enum: out.writeEnum(String.valueOf(fValue)); break;
                    }
                    break;

                case Double:
                    double dValue = in.getDouble();
                    switch (to) {
                        case Boolean: out.writeBoolean(dValue); break;
                        case Int: out.writeInt(dValue); break;
                        case Long: out.writeLong(dValue); break;
                        case Float: out.writeFloat(dValue); break;
                        case Double: out.writeDouble(dValue); break;
                        case String: out.writeString(String.valueOf(dValue)); break;
                        case Enum: out.writeEnum(String.valueOf(dValue)); break;
                    }
                    break;

                case String:
                    String sValue = in.getString();
                    out.writeString(sValue);
                    break;

                case Enum:
                    long enumValue = in.getLong();
                    out.writeLong(change.enumMapping.getMapped(((EnumDataType) inType).getDescriptor(), enumValue, enumValue));
                    break;

                case Array:
                    if (to == DataTypeIndex.Array) {

                        DataType inArrayType = ((ArrayDataType)inType).getElementDataType();
                        DataTypeIndex inArrayTypeIndex = FieldMapping.getTypeIndex(inArrayType);

                        DataType outArrayType = ((ArrayDataType)outType).getElementDataType();
                        DataTypeIndex outArrayTypeIndex = FieldMapping.getTypeIndex(outArrayType);

                        int length = in.getArrayLength();
                        out.setArrayLength(length);

                        for (int i = 0; i < length; i++)
                            convertField(
                                    in.nextReadableElement(), inArrayType, inArrayTypeIndex,
                                    out.clone(out.nextWritableElement()), outArrayType, outArrayTypeIndex
                            );

                    } else {
                        out.writeDefault();
                    }
                    break;

                case Object:
                    if (to == DataTypeIndex.Object)
                        convertObject(in, (ClassDataType) inType, out, (ClassDataType) outType);
                    else
                        out.writeDefault();
                    break;

                case Binary:
                    if (to == DataTypeIndex.Binary) {
                        int length = in.getBinaryLength();
                        if (tmp == null || tmp.length < length)
                            tmp = new byte[length];

                        in.getBinary(0, length, tmp, 0);
                        out.writeBinary(tmp, 0, length);
                    } else {
                        out.writeDefault();
                    }
                    break;
            }
        } catch (NullValueException e) {
            if (outType.isNullable())
                out.writeNull();
            else
                out.writeDefault();
        }

        return true;
    }

    public enum DataTypeIndex {
        Boolean, Int, Long, Float, Double, Enum, String, Array, Object, Binary
    }
}