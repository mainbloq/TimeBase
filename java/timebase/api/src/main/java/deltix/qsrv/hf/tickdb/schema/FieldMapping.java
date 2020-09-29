package deltix.qsrv.hf.tickdb.schema;

import deltix.qsrv.hf.pub.codec.NonStaticFieldLayout;
import deltix.qsrv.hf.pub.codec.UnboundEncoder;
import deltix.qsrv.hf.pub.md.*;
import deltix.qsrv.hf.tickdb.schema.encoders.IgnoreWriter;
import deltix.qsrv.hf.tickdb.schema.encoders.WritableValueDelegate;
import deltix.qsrv.hf.tickdb.schema.encoders.DefaultValueEncoder;
import deltix.qsrv.hf.tickdb.schema.encoders.MixedWritableValue;

public class FieldMapping {

    public DataType                         sourceType;
    public NonStaticFieldLayout             source;
    public int                              sourceIndex;
    public SchemaConverter.DataTypeIndex    sourceTypeIndex;

    public DataType                         targetType;
    public NonStaticFieldLayout             target;
    public int                              targetIndex;
    public SchemaConverter.DataTypeIndex    targetTypeIndex;

    public ErrorResolution                  resolution;

    FieldMapping(NonStaticFieldLayout target, NonStaticFieldLayout source) {
        this.target = target;
        this.targetType = target.getField().getType();
        this.targetTypeIndex = getTypeIndex(targetType);

        if (source != null) {
            this.source = source;
            this.sourceType = source.getField().getType();
            this.sourceTypeIndex = getTypeIndex(sourceType);
        }
    }

    public MixedWritableValue       getWritable(UnboundEncoder encoder) {

        if (resolution != null)
            return resolution.result == ErrorResolution.Result.Resolved ?
                            new DefaultValueEncoder(encoder, resolution.defaultValue, targetType) :
                            new IgnoreWriter(encoder, targetType);
        else
            return new WritableValueDelegate(encoder);
            //throw new IllegalStateException("Resolution is null for " + this);
    }

    public static SchemaConverter.DataTypeIndex getTypeIndex(DataType type) {
        
        if (type instanceof BooleanDataType)
            return SchemaConverter.DataTypeIndex.Boolean;

        else if (type instanceof VarcharDataType)
            return SchemaConverter.DataTypeIndex.String;

        else if (type instanceof CharDataType)
            return SchemaConverter.DataTypeIndex.String;

        else if (type instanceof DateTimeDataType)
            return SchemaConverter.DataTypeIndex.Long;

        else if (type instanceof TimeOfDayDataType)
            return SchemaConverter.DataTypeIndex.Int;

        else if (type instanceof IntegerDataType) {
            if (((IntegerDataType)type).getNativeTypeSize() < 8)
                return SchemaConverter.DataTypeIndex.Int;
            return SchemaConverter.DataTypeIndex.Long;

        } else if (type instanceof FloatDataType) {
            if (((FloatDataType) type).isDecimal64())
                return SchemaConverter.DataTypeIndex.Long;

            if (((FloatDataType) type).isFloat())
                return SchemaConverter.DataTypeIndex.Float;

            return SchemaConverter.DataTypeIndex.Double;

        } else if (type instanceof EnumDataType)
           return SchemaConverter.DataTypeIndex.Enum;

        else if (type instanceof ArrayDataType)
            return SchemaConverter.DataTypeIndex.Array;

        else if (type instanceof ClassDataType)
            return SchemaConverter.DataTypeIndex.Object;

        if (type instanceof BinaryDataType)
            return SchemaConverter.DataTypeIndex.Binary;

        throw new IllegalStateException("DataType " + type + "is not supported.");
    }
}
