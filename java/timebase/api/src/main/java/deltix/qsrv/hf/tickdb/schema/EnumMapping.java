package deltix.qsrv.hf.tickdb.schema;

import deltix.qsrv.hf.pub.md.EnumClassDescriptor;
import deltix.qsrv.hf.pub.md.EnumDataType;
import deltix.qsrv.hf.pub.md.EnumValue;
import deltix.qsrv.hf.tickdb.schema.xml.LongToLongMapAccessor;
import deltix.util.collections.generated.LongToLongHashMap;
import deltix.util.collections.generated.ObjectArrayList;

import javax.xml.bind.annotation.XmlElement;
import java.util.HashMap;

public class EnumMapping {

    public EnumMapping() { } // for jaxb

    @XmlElement
    public final HashMap<String, LongToLongMapAccessor> map = new HashMap<>();

    public void addMapping(EnumClassDescriptor from, EnumClassDescriptor to, SchemaMapping mapping) {
        ObjectArrayList<EnumValue> fromValues = new ObjectArrayList<>(from.getValues());
        ObjectArrayList<String> fromSymbols = new ObjectArrayList<>(from.getSymbols());
        ObjectArrayList<EnumValue> toValues = new ObjectArrayList<>(to.getValues());
        ObjectArrayList<String> toSymbols = new ObjectArrayList<>(to.getSymbols());

        LongToLongHashMap longMap = new LongToLongHashMap(fromSymbols.size());
        map.put(from.getGuid(), new LongToLongMapAccessor(longMap));

        for (int i = 0; i < fromValues.size(); i++) {
            EnumValue enumValue = mapping.enumValues.get(fromValues.get(i));
            if (enumValue != null && toValues.indexOf(enumValue) != -1) {
                longMap.put(fromValues.get(i).value, enumValue.value);
            } else {
                int index = toSymbols.indexOf(fromSymbols.get(i));
                if (index != -1) {
                    longMap.put(fromValues.get(i).value, toValues.get(i).value);
                } else {
                    longMap.put(fromValues.get(i).value, EnumDataType.NULL);
                }
            }
        }
    }

    public long getMapped(EnumClassDescriptor ecd, long value, long defaultValue) {
        LongToLongMapAccessor longMapAccessor = map.get(ecd.getGuid());
        return longMapAccessor == null ? defaultValue: longMapAccessor.map.get(value, defaultValue);
    }
}
