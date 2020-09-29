package deltix.qsrv.hf.pub;

import deltix.qsrv.hf.pub.codec.NonStaticFieldLayout;

import deltix.qsrv.hf.pub.codec.StaticFieldLayout;
import deltix.qsrv.hf.pub.md.*;
import deltix.timebase.api.messages.InstrumentType;
import deltix.timebase.messages.InstrumentMessage;
import deltix.util.JUnitCategories;
import org.junit.experimental.categories.Category;

import static deltix.qsrv.hf.pub.md.FloatDataType.*;

@Category(JUnitCategories.TickDBCodecs.class)
public final class Test_RecordCodecs5 extends deltix.qsrv.hf.pub.Test_RecordCodecsBase {
    private java.lang.String getFieldString (java.lang.Object msg,
        java.lang.String name)
    {
        try {
            return (((msg).getClass ()).getField (name)).toString ();
        }catch (java.lang.NoSuchFieldException e) {
            throw new java.lang.RuntimeException (e);
        }
    }
    public static final class AllBooleanPublic extends InstrumentMessage {
        public boolean boolean1;
        public byte byte2;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return (((this).boolean1) + (",")) + ((this).byte2);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPublic))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPublic other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPublic) (o);
            return (((this).boolean1) == ((other).boolean1)) && (((this).byte2) == ((other).byte2));
        }}
    private static final RecordClassDescriptor rcdAllBooleanPublicNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPublic.class).getName (), null, false, null, new NonStaticDataField ("boolean1", null, new BooleanDataType (false)), new NonStaticDataField ("byte2", null, new BooleanDataType (true)));
    private static final RecordClassDescriptor rcdAllBooleanPublicNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPublic.class).getName (), null, false, null, new NonStaticDataField ("boolean1", null, new BooleanDataType (false)), new NonStaticDataField ("byte2", null, new BooleanDataType (false)));
    private static final RecordClassDescriptor rcdAllBooleanPublicNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPublic.class).getName (), null, false, null, new StaticDataField ("boolean1", null, new BooleanDataType (false), "true"), new StaticDataField ("byte2", null, new BooleanDataType (true), "true"));
    private static final RecordClassDescriptor rcdAllBooleanPublicNullableStaticF = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPublic.class).getName (), null, false, null, new StaticDataField ("boolean1", null, new BooleanDataType (false), "false"), new StaticDataField ("byte2", null, new BooleanDataType (true), "false"));
    private static final RecordClassDescriptor rcdAllBooleanPublicNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPublic.class).getName (), null, false, null, new StaticDataField ("boolean1", null, new BooleanDataType (false), "true"), new StaticDataField ("byte2", null, new BooleanDataType (false), "true"));
    private static final RecordClassDescriptor rcdAllBooleanPublicNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPublic.class).getName (), null, false, null, new StaticDataField ("boolean1", null, new BooleanDataType (false), "false"), new StaticDataField ("byte2", null, new BooleanDataType (true), null));
    public static final class AllBooleanPrivate extends InstrumentMessage {
        public boolean boolean1;
        public byte byte2;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return (((this).boolean1) + (",")) + ((this).byte2);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPrivate))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPrivate other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPrivate) (o);
            return (((this).boolean1) == ((other).boolean1)) && (((this).byte2) == ((other).byte2));
        }}
    private static final RecordClassDescriptor rcdAllBooleanPrivateNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPrivate.class).getName (), null, false, null, new NonStaticDataField ("boolean1", null, new BooleanDataType (false)), new NonStaticDataField ("byte2", null, new BooleanDataType (true)));
    private static final RecordClassDescriptor rcdAllBooleanPrivateNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPrivate.class).getName (), null, false, null, new NonStaticDataField ("boolean1", null, new BooleanDataType (false)), new NonStaticDataField ("byte2", null, new BooleanDataType (false)));
    private static final RecordClassDescriptor rcdAllBooleanPrivateNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPrivate.class).getName (), null, false, null, new StaticDataField ("boolean1", null, new BooleanDataType (false), "true"), new StaticDataField ("byte2", null, new BooleanDataType (true), "true"));
    private static final RecordClassDescriptor rcdAllBooleanPrivateNullableStaticF = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPrivate.class).getName (), null, false, null, new StaticDataField ("boolean1", null, new BooleanDataType (false), "false"), new StaticDataField ("byte2", null, new BooleanDataType (true), "false"));
    private static final RecordClassDescriptor rcdAllBooleanPrivateNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPrivate.class).getName (), null, false, null, new StaticDataField ("boolean1", null, new BooleanDataType (false), "true"), new StaticDataField ("byte2", null, new BooleanDataType (false), "true"));
    private static final RecordClassDescriptor rcdAllBooleanPrivateNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPrivate.class).getName (), null, false, null, new StaticDataField ("boolean1", null, new BooleanDataType (false), "false"), new StaticDataField ("byte2", null, new BooleanDataType (true), null));
    private void testBooleanDataType ()
    {
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPublic ();
            (msg).boolean1 = true;
            (msg).byte2 = 1;
            super.testRcdBound ("private nullable - true values", msg, rcdAllBooleanPublicNullable);
            super.testRcdBound ("private non-nullable - true values", msg, rcdAllBooleanPublicNotNullable);
            super.testRcdBound ("private nullable static - true values", msg, rcdAllBooleanPublicNullableStatic);
            super.testRcdBound ("private non-nullable static - true values", msg, rcdAllBooleanPublicNotNullableStatic);
            (msg).boolean1 = false;
            (msg).byte2 = 0;
            super.testRcdBound ("private nullable - false values", msg, rcdAllBooleanPublicNullable);
            super.testRcdBound ("private non-nullable - false values", msg, rcdAllBooleanPublicNotNullable);
            super.testRcdBound ("private nullable static - false values", msg, rcdAllBooleanPublicNullableStaticF);
            (msg).boolean1 = false;
            (msg).byte2 = -1;
            super.testRcdBound ("private nullable - null values", msg, rcdAllBooleanPublicNullable);
            super.testRcdBound ("private nullable static - null values", msg, rcdAllBooleanPublicNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPrivate ();
            (msg).boolean1 = true;
            (msg).byte2 = 1;
            super.testRcdBound ("public nullable - true values", msg, rcdAllBooleanPrivateNullable);
            super.testRcdBound ("public non-nullable - true values", msg, rcdAllBooleanPrivateNotNullable);
            super.testRcdBound ("public nullable static - true values", msg, rcdAllBooleanPrivateNullableStatic);
            super.testRcdBound ("public non-nullable static - true values", msg, rcdAllBooleanPrivateNotNullableStatic);
            (msg).boolean1 = false;
            (msg).byte2 = 0;
            super.testRcdBound ("public nullable - false values", msg, rcdAllBooleanPrivateNullable);
            super.testRcdBound ("public non-nullable - false values", msg, rcdAllBooleanPrivateNotNullable);
            super.testRcdBound ("public nullable static - false values", msg, rcdAllBooleanPrivateNullableStaticF);
            (msg).boolean1 = false;
            (msg).byte2 = -1;
            super.testRcdBound ("public nullable - null values", msg, rcdAllBooleanPrivateNullable);
            super.testRcdBound ("public nullable static - null values", msg, rcdAllBooleanPrivateNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPublic ();
            (msg).boolean1 = false;
            (msg).byte2 = -1;
            (msg).boolean1 = true;
            try {
                super.boundEncode (msg, rcdAllBooleanPublicNotNullable);
                org.junit.Assert.fail ("null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("null violation - public", "java.lang.IllegalArgumentException: \'byte2\' field is not nullable", (e).toString ());
            }
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBooleanPrivate ();
            (msg).boolean1 = false;
            (msg).byte2 = -1;
            (msg).boolean1 = true;
            try {
                super.boundEncode (msg, rcdAllBooleanPrivateNotNullable);
                org.junit.Assert.fail ("null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("null violation - private", "java.lang.IllegalArgumentException: \'byte2\' field is not nullable", (e).toString ());
            }
        }
    }
    @org.junit.Test
    public void testBooleanDataTypeComp ()
    {
        super.setUpComp ();
        this.testBooleanDataType ();
    }
    @org.junit.Test
    public void testBooleanDataTypeIntp ()
    {
        super.setUpIntp ();
        this.testBooleanDataType ();
    }
    private void testIntegerDataType ()
    {
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPublic ();
            (msg).byte1 = 85;
            (msg).short2 = -30001;
            (msg).int3 = 2000000001;
            (msg).long4 = 2000000000001L;
            (msg).long5 = 140737488355082L;
            (msg).int6 = 1073741822;
            (msg).long7 = 2305843009213693950L;
            (msg).int8 = java.lang.Integer.MAX_VALUE;
            super.testRcdBound ("public nullable - normal values", msg, rcdAllIntegerPublicNullable);
            super.testRcdBound ("public non-nullable - normal values", msg, rcdAllIntegerPublicNotNullable);
            super.testRcdBound ("public nullable static - normal values", msg, rcdAllIntegerPublicNullableStatic);
            super.testRcdBound ("public non-nullable static - normal values", msg, rcdAllIntegerPublicNotNullableStatic);
            (msg).byte1 = IntegerDataType.INT8_NULL;
            (msg).short2 = IntegerDataType.INT16_NULL;
            (msg).int3 = IntegerDataType.INT32_NULL;
            (msg).long4 = IntegerDataType.INT64_NULL;
            (msg).long5 = IntegerDataType.INT48_NULL;
            (msg).int6 = IntegerDataType.PUINT30_NULL;
            (msg).long7 = IntegerDataType.PUINT61_NULL;
            (msg).int8 = IntegerDataType.PINTERVAL_NULL;
            super.testRcdBound ("public nullable - null values", msg, rcdAllIntegerPublicNullable);
            super.testRcdBound ("public nullable static - null values", msg, rcdAllIntegerPublicNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPublic ();
            (msg).byte1 = IntegerDataType.INT8_NULL;
            (msg).short2 = IntegerDataType.INT16_NULL;
            (msg).int3 = IntegerDataType.INT32_NULL;
            (msg).long4 = IntegerDataType.INT64_NULL;
            (msg).long5 = IntegerDataType.INT48_NULL;
            (msg).int6 = IntegerDataType.PUINT30_NULL;
            (msg).long7 = IntegerDataType.PUINT61_NULL;
            (msg).int8 = IntegerDataType.PINTERVAL_NULL;
            try {
                super.boundEncode (msg, rcdAllIntegerPublicNotNullable);
                org.junit.Assert.fail ("INT8 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - null violation - public", "java.lang.IllegalArgumentException: \'byte1\' field is not nullable", (e).toString ());
            }
            (msg).byte1 = 85;
            try {
                super.boundEncode (msg, rcdAllIntegerPublicNotNullable);
                org.junit.Assert.fail ("INT16 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT16 - null violation - public", "java.lang.IllegalArgumentException: \'short2\' field is not nullable", (e).toString ());
            }
            (msg).short2 = -30001;
            try {
                super.boundEncode (msg, rcdAllIntegerPublicNotNullable);
                org.junit.Assert.fail ("INT32 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT32 - null violation - public", "java.lang.IllegalArgumentException: \'int3\' field is not nullable", (e).toString ());
            }
            (msg).int3 = 2000000001;
            try {
                super.boundEncode (msg, rcdAllIntegerPublicNotNullable);
                org.junit.Assert.fail ("INT64 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT64 - null violation - public", "java.lang.IllegalArgumentException: \'long4\' field is not nullable", (e).toString ());
            }
            (msg).long4 = 2000000000001L;
            try {
                super.boundEncode (msg, rcdAllIntegerPublicNotNullable);
                org.junit.Assert.fail ("INT48 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT48 - null violation - public", "java.lang.IllegalArgumentException: \'long5\' field is not nullable", (e).toString ());
            }
            (msg).long5 = 140737488355082L;
            try {
                super.boundEncode (msg, rcdAllIntegerPublicNotNullable);
                org.junit.Assert.fail ("PUINT30 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - null violation - public", "java.lang.IllegalArgumentException: \'int6\' field is not nullable", (e).toString ());
            }
            (msg).int6 = 1073741822;
            try {
                super.boundEncode (msg, rcdAllIntegerPublicNotNullable);
                org.junit.Assert.fail ("PUINT61 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - null violation - public", "java.lang.IllegalArgumentException: \'long7\' field is not nullable", (e).toString ());
            }
            (msg).long7 = 2305843009213693950L;
            try {
                super.boundEncode (msg, rcdAllIntegerPublicNotNullable);
                org.junit.Assert.fail ("PINTERVAL - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - null violation - public", "java.lang.IllegalArgumentException: \'int8\' field is not nullable", (e).toString ());
            }
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPrivate ();
            (msg).byte1 = 85;
            (msg).short2 = -30001;
            (msg).int3 = 2000000001;
            (msg).long4 = 2000000000001L;
            (msg).long5 = 140737488355082L;
            (msg).int6 = 1073741822;
            (msg).long7 = 2305843009213693950L;
            (msg).int8 = java.lang.Integer.MAX_VALUE;
            super.testRcdBound ("private nullable - normal values", msg, rcdAllIntegerPrivateNullable);
            super.testRcdBound ("private non-nullable - normal values", msg, rcdAllIntegerPrivateNotNullable);
            super.testRcdBound ("private nullable static - normal values", msg, rcdAllIntegerPrivateNullableStatic);
            super.testRcdBound ("private non-nullable static - normal values", msg, rcdAllIntegerPrivateNotNullableStatic);
            (msg).byte1 = IntegerDataType.INT8_NULL;
            (msg).short2 = IntegerDataType.INT16_NULL;
            (msg).int3 = IntegerDataType.INT32_NULL;
            (msg).long4 = IntegerDataType.INT64_NULL;
            (msg).long5 = IntegerDataType.INT48_NULL;
            (msg).int6 = IntegerDataType.PUINT30_NULL;
            (msg).long7 = IntegerDataType.PUINT61_NULL;
            (msg).int8 = IntegerDataType.PINTERVAL_NULL;
            super.testRcdBound ("private nullable - null values", msg, rcdAllIntegerPrivateNullable);
            super.testRcdBound ("private nullable static - null values", msg, rcdAllIntegerPrivateNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPrivate ();
            (msg).byte1 = IntegerDataType.INT8_NULL;
            (msg).short2 = IntegerDataType.INT16_NULL;
            (msg).int3 = IntegerDataType.INT32_NULL;
            (msg).long4 = IntegerDataType.INT64_NULL;
            (msg).long5 = IntegerDataType.INT48_NULL;
            (msg).int6 = IntegerDataType.PUINT30_NULL;
            (msg).long7 = IntegerDataType.PUINT61_NULL;
            (msg).int8 = IntegerDataType.PINTERVAL_NULL;
            try {
                super.boundEncode (msg, rcdAllIntegerPrivateNotNullable);
                org.junit.Assert.fail ("INT8 - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - null violation - private", "java.lang.IllegalArgumentException: \'byte1\' field is not nullable", (e).toString ());
            }
            (msg).byte1 = 85;
            try {
                super.boundEncode (msg, rcdAllIntegerPrivateNotNullable);
                org.junit.Assert.fail ("INT16 - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT16 - null violation - private", "java.lang.IllegalArgumentException: \'short2\' field is not nullable", (e).toString ());
            }
            (msg).short2 = -30001;
            try {
                super.boundEncode (msg, rcdAllIntegerPrivateNotNullable);
                org.junit.Assert.fail ("INT32 - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT32 - null violation - private", "java.lang.IllegalArgumentException: \'int3\' field is not nullable", (e).toString ());
            }
            (msg).int3 = 2000000001;
            try {
                super.boundEncode (msg, rcdAllIntegerPrivateNotNullable);
                org.junit.Assert.fail ("INT64 - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT64 - null violation - private", "java.lang.IllegalArgumentException: \'long4\' field is not nullable", (e).toString ());
            }
            (msg).long4 = 2000000000001L;
            try {
                super.boundEncode (msg, rcdAllIntegerPrivateNotNullable);
                org.junit.Assert.fail ("INT48 - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT48 - null violation - private", "java.lang.IllegalArgumentException: \'long5\' field is not nullable", (e).toString ());
            }
            (msg).long5 = 140737488355082L;
            try {
                super.boundEncode (msg, rcdAllIntegerPrivateNotNullable);
                org.junit.Assert.fail ("PUINT30 - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - null violation - private", "java.lang.IllegalArgumentException: \'int6\' field is not nullable", (e).toString ());
            }
            (msg).int6 = 1073741822;
            try {
                super.boundEncode (msg, rcdAllIntegerPrivateNotNullable);
                org.junit.Assert.fail ("PUINT61 - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - null violation - private", "java.lang.IllegalArgumentException: \'long7\' field is not nullable", (e).toString ());
            }
            (msg).long7 = 2305843009213693950L;
            try {
                super.boundEncode (msg, rcdAllIntegerPrivateNotNullable);
                org.junit.Assert.fail ("PINTERVAL - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - null violation - private", "java.lang.IllegalArgumentException: \'int8\' field is not nullable", (e).toString ());
            }
        }
    }
    public static final class AllIntegerPublic extends InstrumentMessage {
        public byte byte1;
        public short short2;
        public int int3;
        public long long4;
        public long long5;
        public int int6;
        public long long7;
        public int int8;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return (((((((((((((((this).byte1) + (",")) + ((this).short2)) + (",")) + ((this).int3)) + (",")) + ((this).long4)) + (",")) + ((this).long5)) + (",")) + ((this).int6)) + (",")) + ((this).long7)) + (",")) + ((this).int8);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPublic))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPublic other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPublic) (o);
            return (((((((((this).byte1) == ((other).byte1)) && (((this).short2) == ((other).short2))) && (((this).int3) == ((other).int3))) && (((this).long4) == ((other).long4))) && (((this).long5) == ((other).long5))) && (((this).int6) == ((other).int6))) && (((this).long7) == ((other).long7))) && (((this).int8) == ((other).int8));
        }}
    private static final RecordClassDescriptor rcdAllIntegerPublicNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPublic.class).getName (), null, false, null, new NonStaticDataField ("byte1", null, new IntegerDataType ("INT8", true)), new NonStaticDataField ("short2", null, new IntegerDataType ("INT16", true)), new NonStaticDataField ("int3", null, new IntegerDataType ("INT32", true)), new NonStaticDataField ("long4", null, new IntegerDataType ("INT64", true)), new NonStaticDataField ("long5", null, new IntegerDataType ("INT48", true)), new NonStaticDataField ("int6", null, new IntegerDataType ("PUINT30", true)), new NonStaticDataField ("long7", null, new IntegerDataType ("PUINT61", true)), new NonStaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", true)));
    private static final RecordClassDescriptor rcdAllIntegerPublicNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPublic.class).getName (), null, false, null, new NonStaticDataField ("byte1", null, new IntegerDataType ("INT8", false)), new NonStaticDataField ("short2", null, new IntegerDataType ("INT16", false)), new NonStaticDataField ("int3", null, new IntegerDataType ("INT32", false)), new NonStaticDataField ("long4", null, new IntegerDataType ("INT64", false)), new NonStaticDataField ("long5", null, new IntegerDataType ("INT48", false)), new NonStaticDataField ("int6", null, new IntegerDataType ("PUINT30", false)), new NonStaticDataField ("long7", null, new IntegerDataType ("PUINT61", false)), new NonStaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", false)));
    private static final RecordClassDescriptor rcdAllIntegerPublicNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPublic.class).getName (), null, false, null, new StaticDataField ("byte1", null, new IntegerDataType ("INT8", true), "85"), new StaticDataField ("short2", null, new IntegerDataType ("INT16", true), "-30001"), new StaticDataField ("int3", null, new IntegerDataType ("INT32", true), "2000000001"), new StaticDataField ("long4", null, new IntegerDataType ("INT64", true), "2000000000001"), new StaticDataField ("long5", null, new IntegerDataType ("INT48", true), "140737488355082"), new StaticDataField ("int6", null, new IntegerDataType ("PUINT30", true), "1073741822"), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", true), "2305843009213693950"), new StaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", true), "2147483647"));
    private static final RecordClassDescriptor rcdAllIntegerPublicNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPublic.class).getName (), null, false, null, new StaticDataField ("byte1", null, new IntegerDataType ("INT8", false), "85"), new StaticDataField ("short2", null, new IntegerDataType ("INT16", false), "-30001"), new StaticDataField ("int3", null, new IntegerDataType ("INT32", false), "2000000001"), new StaticDataField ("long4", null, new IntegerDataType ("INT64", false), "2000000000001"), new StaticDataField ("long5", null, new IntegerDataType ("INT48", false), "140737488355082"), new StaticDataField ("int6", null, new IntegerDataType ("PUINT30", false), "1073741822"), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", false), "2305843009213693950"), new StaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", false), "2147483647"));
    private static final RecordClassDescriptor rcdAllIntegerPublicNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPublic.class).getName (), null, false, null, new StaticDataField ("byte1", null, new IntegerDataType ("INT8", true), null), new StaticDataField ("short2", null, new IntegerDataType ("INT16", true), null), new StaticDataField ("int3", null, new IntegerDataType ("INT32", true), null), new StaticDataField ("long4", null, new IntegerDataType ("INT64", true), null), new StaticDataField ("long5", null, new IntegerDataType ("INT48", true), null), new StaticDataField ("int6", null, new IntegerDataType ("PUINT30", true), null), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", true), null), new StaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", true), null));
    public static final class AllIntegerPrivate extends InstrumentMessage {
        public byte byte1;
        public short short2;
        public int int3;
        public long long4;
        public long long5;
        public int int6;
        public long long7;
        public int int8;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return (((((((((((((((this).byte1) + (",")) + ((this).short2)) + (",")) + ((this).int3)) + (",")) + ((this).long4)) + (",")) + ((this).long5)) + (",")) + ((this).int6)) + (",")) + ((this).long7)) + (",")) + ((this).int8);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPrivate))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPrivate other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPrivate) (o);
            return (((((((((this).byte1) == ((other).byte1)) && (((this).short2) == ((other).short2))) && (((this).int3) == ((other).int3))) && (((this).long4) == ((other).long4))) && (((this).long5) == ((other).long5))) && (((this).int6) == ((other).int6))) && (((this).long7) == ((other).long7))) && (((this).int8) == ((other).int8));
        }}
    private static final RecordClassDescriptor rcdAllIntegerPrivateNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPrivate.class).getName (), null, false, null, new NonStaticDataField ("byte1", null, new IntegerDataType ("INT8", true)), new NonStaticDataField ("short2", null, new IntegerDataType ("INT16", true)), new NonStaticDataField ("int3", null, new IntegerDataType ("INT32", true)), new NonStaticDataField ("long4", null, new IntegerDataType ("INT64", true)), new NonStaticDataField ("long5", null, new IntegerDataType ("INT48", true)), new NonStaticDataField ("int6", null, new IntegerDataType ("PUINT30", true)), new NonStaticDataField ("long7", null, new IntegerDataType ("PUINT61", true)), new NonStaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", true)));
    private static final RecordClassDescriptor rcdAllIntegerPrivateNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPrivate.class).getName (), null, false, null, new NonStaticDataField ("byte1", null, new IntegerDataType ("INT8", false)), new NonStaticDataField ("short2", null, new IntegerDataType ("INT16", false)), new NonStaticDataField ("int3", null, new IntegerDataType ("INT32", false)), new NonStaticDataField ("long4", null, new IntegerDataType ("INT64", false)), new NonStaticDataField ("long5", null, new IntegerDataType ("INT48", false)), new NonStaticDataField ("int6", null, new IntegerDataType ("PUINT30", false)), new NonStaticDataField ("long7", null, new IntegerDataType ("PUINT61", false)), new NonStaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", false)));
    private static final RecordClassDescriptor rcdAllIntegerPrivateNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPrivate.class).getName (), null, false, null, new StaticDataField ("byte1", null, new IntegerDataType ("INT8", true), "85"), new StaticDataField ("short2", null, new IntegerDataType ("INT16", true), "-30001"), new StaticDataField ("int3", null, new IntegerDataType ("INT32", true), "2000000001"), new StaticDataField ("long4", null, new IntegerDataType ("INT64", true), "2000000000001"), new StaticDataField ("long5", null, new IntegerDataType ("INT48", true), "140737488355082"), new StaticDataField ("int6", null, new IntegerDataType ("PUINT30", true), "1073741822"), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", true), "2305843009213693950"), new StaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", true), "2147483647"));
    private static final RecordClassDescriptor rcdAllIntegerPrivateNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPrivate.class).getName (), null, false, null, new StaticDataField ("byte1", null, new IntegerDataType ("INT8", false), "85"), new StaticDataField ("short2", null, new IntegerDataType ("INT16", false), "-30001"), new StaticDataField ("int3", null, new IntegerDataType ("INT32", false), "2000000001"), new StaticDataField ("long4", null, new IntegerDataType ("INT64", false), "2000000000001"), new StaticDataField ("long5", null, new IntegerDataType ("INT48", false), "140737488355082"), new StaticDataField ("int6", null, new IntegerDataType ("PUINT30", false), "1073741822"), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", false), "2305843009213693950"), new StaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", false), "2147483647"));
    private static final RecordClassDescriptor rcdAllIntegerPrivateNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllIntegerPrivate.class).getName (), null, false, null, new StaticDataField ("byte1", null, new IntegerDataType ("INT8", true), null), new StaticDataField ("short2", null, new IntegerDataType ("INT16", true), null), new StaticDataField ("int3", null, new IntegerDataType ("INT32", true), null), new StaticDataField ("long4", null, new IntegerDataType ("INT64", true), null), new StaticDataField ("long5", null, new IntegerDataType ("INT48", true), null), new StaticDataField ("int6", null, new IntegerDataType ("PUINT30", true), null), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", true), null), new StaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", true), null));
    @org.junit.Test
    public void testIntegerDataTypeComp ()
    {
        super.setUpComp ();
        this.testIntegerDataType ();
    }
    @org.junit.Test
    public void testIntegerDataTypeIntp ()
    {
        super.setUpIntp ();
        this.testIntegerDataType ();
    }
    private void testIntegerDataTypeBinding ()
    {
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPublic ();
            (msg).long1 = 85;
            (msg).long2 = -30001;
            (msg).long3 = 2000000001;
            (msg).long4 = 2000000000001L;
            (msg).long5 = 140737488355082L;
            (msg).long6 = 1073741822;
            (msg).long7 = 2305843009213693950L;
            (msg).long8 = java.lang.Integer.MAX_VALUE;
            super.testRcdBound ("public nullable - normal values", msg, rcdALongPublicNullable);
            super.testRcdBound ("public non-nullable - normal values", msg, rcdALongPublicNotNullable);
            super.testRcdBound ("public nullable static - normal values", msg, rcdALongPublicNullableStatic);
            super.testRcdBound ("public non-nullable static - normal values", msg, rcdALongPublicNotNullableStatic);
            (msg).long1 = IntegerDataType.INT8_NULL;
            (msg).long2 = IntegerDataType.INT16_NULL;
            (msg).long3 = IntegerDataType.INT32_NULL;
            (msg).long4 = IntegerDataType.INT64_NULL;
            (msg).long5 = IntegerDataType.INT48_NULL;
            (msg).long6 = IntegerDataType.PUINT30_NULL;
            (msg).long7 = IntegerDataType.PUINT61_NULL;
            (msg).long8 = IntegerDataType.PINTERVAL_NULL;
            super.testRcdBound ("public nullable - null values", msg, rcdALongPublicNullable);
            super.testRcdBound ("public nullable static - null values", msg, rcdALongPublicNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPrivate ();
            (msg).long1 = 85;
            (msg).long2 = -30001;
            (msg).long3 = 2000000001;
            (msg).long4 = 2000000000001L;
            (msg).long5 = 140737488355082L;
            (msg).long6 = 1073741822;
            (msg).long7 = 2305843009213693950L;
            (msg).long8 = java.lang.Integer.MAX_VALUE;
            super.testRcdBound ("private nullable - normal values", msg, rcdALongPrivateNullable);
            super.testRcdBound ("private non-nullable - normal values", msg, rcdALongPrivateNotNullable);
            super.testRcdBound ("private nullable static - normal values", msg, rcdALongPrivateNullableStatic);
            super.testRcdBound ("private non-nullable static - normal values", msg, rcdALongPrivateNotNullableStatic);
            (msg).long1 = IntegerDataType.INT8_NULL;
            (msg).long2 = IntegerDataType.INT16_NULL;
            (msg).long3 = IntegerDataType.INT32_NULL;
            (msg).long4 = IntegerDataType.INT64_NULL;
            (msg).long5 = IntegerDataType.INT48_NULL;
            (msg).long6 = IntegerDataType.PUINT30_NULL;
            (msg).long7 = IntegerDataType.PUINT61_NULL;
            (msg).long8 = IntegerDataType.PINTERVAL_NULL;
            super.testRcdBound ("private nullable - null values", msg, rcdALongPrivateNullable);
            super.testRcdBound ("private nullable static - null values", msg, rcdALongPrivateNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPublic ();
            (msg).long1 = IntegerDataType.INT8_NULL;
            (msg).long2 = IntegerDataType.INT16_NULL;
            (msg).long3 = IntegerDataType.INT32_NULL;
            (msg).long4 = IntegerDataType.INT64_NULL;
            (msg).long5 = IntegerDataType.INT48_NULL;
            (msg).long6 = IntegerDataType.PUINT30_NULL;
            (msg).long7 = IntegerDataType.PUINT61_NULL;
            (msg).long8 = IntegerDataType.PINTERVAL_NULL;
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("INT8 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - null violation - public", "java.lang.IllegalArgumentException: \'long1\' field is not nullable", (e).toString ());
            }
            (msg).long1 = 85;
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("INT16 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT16 - null violation - public", "java.lang.IllegalArgumentException: \'long2\' field is not nullable", (e).toString ());
            }
            (msg).long2 = -30001;
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("INT32 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT32 - null violation - public", "java.lang.IllegalArgumentException: \'long3\' field is not nullable", (e).toString ());
            }
            (msg).long3 = 2000000001;
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("INT64 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT64 - null violation - public", "java.lang.IllegalArgumentException: \'long4\' field is not nullable", (e).toString ());
            }
            (msg).long4 = 2000000000001L;
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("INT48 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT48 - null violation - public", "java.lang.IllegalArgumentException: \'long5\' field is not nullable", (e).toString ());
            }
            (msg).long5 = 140737488355082L;
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("PUINT30 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - null violation - public", "java.lang.IllegalArgumentException: \'long6\' field is not nullable", (e).toString ());
            }
            (msg).long6 = 1073741822;
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("PUINT61 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - null violation - public", "java.lang.IllegalArgumentException: \'long7\' field is not nullable", (e).toString ());
            }
            (msg).long7 = 2305843009213693950L;
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("PINTERVAL - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - null violation - public", "java.lang.IllegalArgumentException: \'long8\' field is not nullable", (e).toString ());
            }
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPrivate ();
            (msg).long1 = IntegerDataType.INT8_NULL;
            (msg).long2 = IntegerDataType.INT16_NULL;
            (msg).long3 = IntegerDataType.INT32_NULL;
            (msg).long4 = IntegerDataType.INT64_NULL;
            (msg).long5 = IntegerDataType.INT48_NULL;
            (msg).long6 = IntegerDataType.PUINT30_NULL;
            (msg).long7 = IntegerDataType.PUINT61_NULL;
            (msg).long8 = IntegerDataType.PINTERVAL_NULL;
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("INT8 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - null violation - public", "java.lang.IllegalArgumentException: \'long1\' field is not nullable", (e).toString ());
            }
            (msg).long1 = 85;
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("INT16 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT16 - null violation - public", "java.lang.IllegalArgumentException: \'long2\' field is not nullable", (e).toString ());
            }
            (msg).long2 = -30001;
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("INT32 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT32 - null violation - public", "java.lang.IllegalArgumentException: \'long3\' field is not nullable", (e).toString ());
            }
            (msg).long3 = 2000000001;
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("INT64 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT64 - null violation - public", "java.lang.IllegalArgumentException: \'long4\' field is not nullable", (e).toString ());
            }
            (msg).long4 = 2000000000001L;
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("INT48 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT48 - null violation - public", "java.lang.IllegalArgumentException: \'long5\' field is not nullable", (e).toString ());
            }
            (msg).long5 = 140737488355082L;
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("PUINT30 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - null violation - public", "java.lang.IllegalArgumentException: \'long6\' field is not nullable", (e).toString ());
            }
            (msg).long6 = 1073741822;
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("PUINT61 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - null violation - public", "java.lang.IllegalArgumentException: \'long7\' field is not nullable", (e).toString ());
            }
            (msg).long7 = 2305843009213693950L;
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("PINTERVAL - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - null violation - public", "java.lang.IllegalArgumentException: \'long8\' field is not nullable", (e).toString ());
            }
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPublic ();
            (msg).int1 = 85;
            (msg).int2 = -30001;
            (msg).int3 = 2000000001;
            (msg).long4 = 2000000000001L;
            (msg).long5 = 140737488355082L;
            (msg).int6 = 1073741822;
            (msg).long7 = 2305843009213693950L;
            (msg).int8 = java.lang.Integer.MAX_VALUE;
            super.testRcdBound ("public nullable - normal values", msg, rcdAIntegerPublicNullable);
            super.testRcdBound ("public non-nullable - normal values", msg, rcdAIntegerPublicNotNullable);
            super.testRcdBound ("public nullable static - normal values", msg, rcdAIntegerPublicNullableStatic);
            super.testRcdBound ("public non-nullable static - normal values", msg, rcdAIntegerPublicNotNullableStatic);
            (msg).int1 = IntegerDataType.INT8_NULL;
            (msg).int2 = IntegerDataType.INT16_NULL;
            (msg).int3 = IntegerDataType.INT32_NULL;
            (msg).long4 = IntegerDataType.INT64_NULL;
            (msg).long5 = IntegerDataType.INT48_NULL;
            (msg).int6 = IntegerDataType.PUINT30_NULL;
            (msg).long7 = IntegerDataType.PUINT61_NULL;
            (msg).int8 = IntegerDataType.PINTERVAL_NULL;
            super.testRcdBound ("public nullable - null values", msg, rcdAIntegerPublicNullable);
            super.testRcdBound ("public nullable static - null values", msg, rcdAIntegerPublicNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPrivate ();
            (msg).int1 = 85;
            (msg).int2 = -30001;
            (msg).int3 = 2000000001;
            (msg).long4 = 2000000000001L;
            (msg).long5 = 140737488355082L;
            (msg).int6 = 1073741822;
            (msg).long7 = 2305843009213693950L;
            (msg).int8 = java.lang.Integer.MAX_VALUE;
            super.testRcdBound ("private nullable - normal values", msg, rcdAIntegerPrivateNullable);
            super.testRcdBound ("private non-nullable - normal values", msg, rcdAIntegerPrivateNotNullable);
            super.testRcdBound ("private nullable static - normal values", msg, rcdAIntegerPrivateNullableStatic);
            super.testRcdBound ("private non-nullable static - normal values", msg, rcdAIntegerPrivateNotNullableStatic);
            (msg).int1 = IntegerDataType.INT8_NULL;
            (msg).int2 = IntegerDataType.INT16_NULL;
            (msg).int3 = IntegerDataType.INT32_NULL;
            (msg).long4 = IntegerDataType.INT64_NULL;
            (msg).long5 = IntegerDataType.INT48_NULL;
            (msg).int6 = IntegerDataType.PUINT30_NULL;
            (msg).long7 = IntegerDataType.PUINT61_NULL;
            (msg).int8 = IntegerDataType.PINTERVAL_NULL;
            super.testRcdBound ("private nullable - null values", msg, rcdAIntegerPrivateNullable);
            super.testRcdBound ("private nullable static - null values", msg, rcdAIntegerPrivateNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPublic ();
            (msg).int1 = IntegerDataType.INT8_NULL;
            (msg).int2 = IntegerDataType.INT16_NULL;
            (msg).int3 = IntegerDataType.INT32_NULL;
            (msg).long4 = IntegerDataType.INT64_NULL;
            (msg).long5 = IntegerDataType.INT48_NULL;
            (msg).int6 = IntegerDataType.PUINT30_NULL;
            (msg).long7 = IntegerDataType.PUINT61_NULL;
            (msg).int8 = IntegerDataType.PINTERVAL_NULL;
            try {
                super.boundEncode (msg, rcdAIntegerPublicNotNullable);
                org.junit.Assert.fail ("INT8 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - null violation - public", "java.lang.IllegalArgumentException: \'int1\' field is not nullable", (e).toString ());
            }
            (msg).int1 = 85;
            try {
                super.boundEncode (msg, rcdAIntegerPublicNotNullable);
                org.junit.Assert.fail ("INT16 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT16 - null violation - public", "java.lang.IllegalArgumentException: \'int2\' field is not nullable", (e).toString ());
            }
            (msg).int2 = -30001;
            try {
                super.boundEncode (msg, rcdAIntegerPublicNotNullable);
                org.junit.Assert.fail ("INT32 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT32 - null violation - public", "java.lang.IllegalArgumentException: \'int3\' field is not nullable", (e).toString ());
            }
            (msg).int3 = 2000000001;
            try {
                super.boundEncode (msg, rcdAIntegerPublicNotNullable);
                org.junit.Assert.fail ("INT64 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT64 - null violation - public", "java.lang.IllegalArgumentException: \'long4\' field is not nullable", (e).toString ());
            }
            (msg).long4 = 2000000000001L;
            try {
                super.boundEncode (msg, rcdAIntegerPublicNotNullable);
                org.junit.Assert.fail ("INT48 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT48 - null violation - public", "java.lang.IllegalArgumentException: \'long5\' field is not nullable", (e).toString ());
            }
            (msg).long5 = 140737488355082L;
            try {
                super.boundEncode (msg, rcdAIntegerPublicNotNullable);
                org.junit.Assert.fail ("PUINT30 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - null violation - public", "java.lang.IllegalArgumentException: \'int6\' field is not nullable", (e).toString ());
            }
            (msg).int6 = 1073741822;
            try {
                super.boundEncode (msg, rcdAIntegerPublicNotNullable);
                org.junit.Assert.fail ("PUINT61 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - null violation - public", "java.lang.IllegalArgumentException: \'long7\' field is not nullable", (e).toString ());
            }
            (msg).long7 = 2305843009213693950L;
            try {
                super.boundEncode (msg, rcdAIntegerPublicNotNullable);
                org.junit.Assert.fail ("PINTERVAL - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - null violation - public", "java.lang.IllegalArgumentException: \'int8\' field is not nullable", (e).toString ());
            }
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPrivate ();
            (msg).int1 = IntegerDataType.INT8_NULL;
            (msg).int2 = IntegerDataType.INT16_NULL;
            (msg).int3 = IntegerDataType.INT32_NULL;
            (msg).long4 = IntegerDataType.INT64_NULL;
            (msg).long5 = IntegerDataType.INT48_NULL;
            (msg).int6 = IntegerDataType.PUINT30_NULL;
            (msg).long7 = IntegerDataType.PUINT61_NULL;
            (msg).int8 = IntegerDataType.PINTERVAL_NULL;
            try {
                super.boundEncode (msg, rcdAIntegerPrivateNotNullable);
                org.junit.Assert.fail ("INT8 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - null violation - public", "java.lang.IllegalArgumentException: \'int1\' field is not nullable", (e).toString ());
            }
            (msg).int1 = 85;
            try {
                super.boundEncode (msg, rcdAIntegerPrivateNotNullable);
                org.junit.Assert.fail ("INT16 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT16 - null violation - public", "java.lang.IllegalArgumentException: \'int2\' field is not nullable", (e).toString ());
            }
            (msg).int2 = -30001;
            try {
                super.boundEncode (msg, rcdAIntegerPrivateNotNullable);
                org.junit.Assert.fail ("INT32 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT32 - null violation - public", "java.lang.IllegalArgumentException: \'int3\' field is not nullable", (e).toString ());
            }
            (msg).int3 = 2000000001;
            try {
                super.boundEncode (msg, rcdAIntegerPrivateNotNullable);
                org.junit.Assert.fail ("INT64 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT64 - null violation - public", "java.lang.IllegalArgumentException: \'long4\' field is not nullable", (e).toString ());
            }
            (msg).long4 = 2000000000001L;
            try {
                super.boundEncode (msg, rcdAIntegerPrivateNotNullable);
                org.junit.Assert.fail ("INT48 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT48 - null violation - public", "java.lang.IllegalArgumentException: \'long5\' field is not nullable", (e).toString ());
            }
            (msg).long5 = 140737488355082L;
            try {
                super.boundEncode (msg, rcdAIntegerPrivateNotNullable);
                org.junit.Assert.fail ("PUINT30 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - null violation - public", "java.lang.IllegalArgumentException: \'int6\' field is not nullable", (e).toString ());
            }
            (msg).int6 = 1073741822;
            try {
                super.boundEncode (msg, rcdAIntegerPrivateNotNullable);
                org.junit.Assert.fail ("PUINT61 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - null violation - public", "java.lang.IllegalArgumentException: \'long7\' field is not nullable", (e).toString ());
            }
            (msg).long7 = 2305843009213693950L;
            try {
                super.boundEncode (msg, rcdAIntegerPrivateNotNullable);
                org.junit.Assert.fail ("PINTERVAL - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - null violation - public", "java.lang.IllegalArgumentException: \'int8\' field is not nullable", (e).toString ());
            }
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPublic ();
            (msg).short1 = 85;
            (msg).short2 = -30001;
            (msg).int3 = 2000000001;
            (msg).long4 = 2000000000001L;
            (msg).long5 = 140737488355082L;
            (msg).int6 = 1073741822;
            (msg).long7 = 2305843009213693950L;
            (msg).int8 = java.lang.Integer.MAX_VALUE;
            super.testRcdBound ("public nullable - normal values", msg, rcdAShortPublicNullable);
            super.testRcdBound ("public non-nullable - normal values", msg, rcdAShortPublicNotNullable);
            super.testRcdBound ("public nullable static - normal values", msg, rcdAShortPublicNullableStatic);
            super.testRcdBound ("public non-nullable static - normal values", msg, rcdAShortPublicNotNullableStatic);
            (msg).short1 = IntegerDataType.INT8_NULL;
            (msg).short2 = IntegerDataType.INT16_NULL;
            (msg).int3 = IntegerDataType.INT32_NULL;
            (msg).long4 = IntegerDataType.INT64_NULL;
            (msg).long5 = IntegerDataType.INT48_NULL;
            (msg).int6 = IntegerDataType.PUINT30_NULL;
            (msg).long7 = IntegerDataType.PUINT61_NULL;
            (msg).int8 = IntegerDataType.PINTERVAL_NULL;
            super.testRcdBound ("public nullable - null values", msg, rcdAShortPublicNullable);
            super.testRcdBound ("public nullable static - null values", msg, rcdAShortPublicNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPrivate ();
            (msg).short1 = 85;
            (msg).short2 = -30001;
            (msg).int3 = 2000000001;
            (msg).long4 = 2000000000001L;
            (msg).long5 = 140737488355082L;
            (msg).int6 = 1073741822;
            (msg).long7 = 2305843009213693950L;
            (msg).int8 = java.lang.Integer.MAX_VALUE;
            super.testRcdBound ("private nullable - normal values", msg, rcdAShortPrivateNullable);
            super.testRcdBound ("private non-nullable - normal values", msg, rcdAShortPrivateNotNullable);
            super.testRcdBound ("private nullable static - normal values", msg, rcdAShortPrivateNullableStatic);
            super.testRcdBound ("private non-nullable static - normal values", msg, rcdAShortPrivateNotNullableStatic);
            (msg).short1 = IntegerDataType.INT8_NULL;
            (msg).short2 = IntegerDataType.INT16_NULL;
            (msg).int3 = IntegerDataType.INT32_NULL;
            (msg).long4 = IntegerDataType.INT64_NULL;
            (msg).long5 = IntegerDataType.INT48_NULL;
            (msg).int6 = IntegerDataType.PUINT30_NULL;
            (msg).long7 = IntegerDataType.PUINT61_NULL;
            (msg).int8 = IntegerDataType.PINTERVAL_NULL;
            super.testRcdBound ("private nullable - null values", msg, rcdAShortPrivateNullable);
            super.testRcdBound ("private nullable static - null values", msg, rcdAShortPrivateNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPublic ();
            (msg).short1 = IntegerDataType.INT8_NULL;
            (msg).short2 = IntegerDataType.INT16_NULL;
            (msg).int3 = IntegerDataType.INT32_NULL;
            (msg).long4 = IntegerDataType.INT64_NULL;
            (msg).long5 = IntegerDataType.INT48_NULL;
            (msg).int6 = IntegerDataType.PUINT30_NULL;
            (msg).long7 = IntegerDataType.PUINT61_NULL;
            (msg).int8 = IntegerDataType.PINTERVAL_NULL;
            try {
                super.boundEncode (msg, rcdAShortPublicNotNullable);
                org.junit.Assert.fail ("INT8 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - null violation - public", "java.lang.IllegalArgumentException: \'short1\' field is not nullable", (e).toString ());
            }
            (msg).short1 = 85;
            try {
                super.boundEncode (msg, rcdAShortPublicNotNullable);
                org.junit.Assert.fail ("INT16 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT16 - null violation - public", "java.lang.IllegalArgumentException: \'short2\' field is not nullable", (e).toString ());
            }
            (msg).short2 = -30001;
            try {
                super.boundEncode (msg, rcdAShortPublicNotNullable);
                org.junit.Assert.fail ("INT32 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT32 - null violation - public", "java.lang.IllegalArgumentException: \'int3\' field is not nullable", (e).toString ());
            }
            (msg).int3 = 2000000001;
            try {
                super.boundEncode (msg, rcdAShortPublicNotNullable);
                org.junit.Assert.fail ("INT64 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT64 - null violation - public", "java.lang.IllegalArgumentException: \'long4\' field is not nullable", (e).toString ());
            }
            (msg).long4 = 2000000000001L;
            try {
                super.boundEncode (msg, rcdAShortPublicNotNullable);
                org.junit.Assert.fail ("INT48 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT48 - null violation - public", "java.lang.IllegalArgumentException: \'long5\' field is not nullable", (e).toString ());
            }
            (msg).long5 = 140737488355082L;
            try {
                super.boundEncode (msg, rcdAShortPublicNotNullable);
                org.junit.Assert.fail ("PUINT30 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - null violation - public", "java.lang.IllegalArgumentException: \'int6\' field is not nullable", (e).toString ());
            }
            (msg).int6 = 1073741822;
            try {
                super.boundEncode (msg, rcdAShortPublicNotNullable);
                org.junit.Assert.fail ("PUINT61 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - null violation - public", "java.lang.IllegalArgumentException: \'long7\' field is not nullable", (e).toString ());
            }
            (msg).long7 = 2305843009213693950L;
            try {
                super.boundEncode (msg, rcdAShortPublicNotNullable);
                org.junit.Assert.fail ("PINTERVAL - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - null violation - public", "java.lang.IllegalArgumentException: \'int8\' field is not nullable", (e).toString ());
            }
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPrivate ();
            (msg).short1 = IntegerDataType.INT8_NULL;
            (msg).short2 = IntegerDataType.INT16_NULL;
            (msg).int3 = IntegerDataType.INT32_NULL;
            (msg).long4 = IntegerDataType.INT64_NULL;
            (msg).long5 = IntegerDataType.INT48_NULL;
            (msg).int6 = IntegerDataType.PUINT30_NULL;
            (msg).long7 = IntegerDataType.PUINT61_NULL;
            (msg).int8 = IntegerDataType.PINTERVAL_NULL;
            try {
                super.boundEncode (msg, rcdAShortPrivateNotNullable);
                org.junit.Assert.fail ("INT8 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - null violation - public", "java.lang.IllegalArgumentException: \'short1\' field is not nullable", (e).toString ());
            }
            (msg).short1 = 85;
            try {
                super.boundEncode (msg, rcdAShortPrivateNotNullable);
                org.junit.Assert.fail ("INT16 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT16 - null violation - public", "java.lang.IllegalArgumentException: \'short2\' field is not nullable", (e).toString ());
            }
            (msg).short2 = -30001;
            try {
                super.boundEncode (msg, rcdAShortPrivateNotNullable);
                org.junit.Assert.fail ("INT32 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT32 - null violation - public", "java.lang.IllegalArgumentException: \'int3\' field is not nullable", (e).toString ());
            }
            (msg).int3 = 2000000001;
            try {
                super.boundEncode (msg, rcdAShortPrivateNotNullable);
                org.junit.Assert.fail ("INT64 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT64 - null violation - public", "java.lang.IllegalArgumentException: \'long4\' field is not nullable", (e).toString ());
            }
            (msg).long4 = 2000000000001L;
            try {
                super.boundEncode (msg, rcdAShortPrivateNotNullable);
                org.junit.Assert.fail ("INT48 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT48 - null violation - public", "java.lang.IllegalArgumentException: \'long5\' field is not nullable", (e).toString ());
            }
            (msg).long5 = 140737488355082L;
            try {
                super.boundEncode (msg, rcdAShortPrivateNotNullable);
                org.junit.Assert.fail ("PUINT30 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - null violation - public", "java.lang.IllegalArgumentException: \'int6\' field is not nullable", (e).toString ());
            }
            (msg).int6 = 1073741822;
            try {
                super.boundEncode (msg, rcdAShortPrivateNotNullable);
                org.junit.Assert.fail ("PUINT61 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - null violation - public", "java.lang.IllegalArgumentException: \'long7\' field is not nullable", (e).toString ());
            }
            (msg).long7 = 2305843009213693950L;
            try {
                super.boundEncode (msg, rcdAShortPrivateNotNullable);
                org.junit.Assert.fail ("PINTERVAL - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - null violation - public", "java.lang.IllegalArgumentException: \'int8\' field is not nullable", (e).toString ());
            }
        }
    }
    public static final class ALongPublic extends InstrumentMessage {
        public long long1;
        public long long2;
        public long long3;
        public long long4;
        public long long5;
        public long long6;
        public long long7;
        public long long8;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return (((((((((((((((this).long1) + (",")) + ((this).long2)) + (",")) + ((this).long3)) + (",")) + ((this).long4)) + (",")) + ((this).long5)) + (",")) + ((this).long6)) + (",")) + ((this).long7)) + (",")) + ((this).long8);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPublic))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPublic other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPublic) (o);
            return (((((((((this).long1) == ((other).long1)) && (((this).long2) == ((other).long2))) && (((this).long3) == ((other).long3))) && (((this).long4) == ((other).long4))) && (((this).long5) == ((other).long5))) && (((this).long6) == ((other).long6))) && (((this).long7) == ((other).long7))) && (((this).long8) == ((other).long8));
        }}
    private static final RecordClassDescriptor rcdALongPublicNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPublic.class).getName (), null, false, null, new NonStaticDataField ("long1", null, new IntegerDataType ("INT8", true)), new NonStaticDataField ("long2", null, new IntegerDataType ("INT16", true)), new NonStaticDataField ("long3", null, new IntegerDataType ("INT32", true)), new NonStaticDataField ("long4", null, new IntegerDataType ("INT64", true)), new NonStaticDataField ("long5", null, new IntegerDataType ("INT48", true)), new NonStaticDataField ("long6", null, new IntegerDataType ("PUINT30", true)), new NonStaticDataField ("long7", null, new IntegerDataType ("PUINT61", true)), new NonStaticDataField ("long8", null, new IntegerDataType ("PINTERVAL", true)));
    private static final RecordClassDescriptor rcdALongPublicNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPublic.class).getName (), null, false, null, new NonStaticDataField ("long1", null, new IntegerDataType ("INT8", false)), new NonStaticDataField ("long2", null, new IntegerDataType ("INT16", false)), new NonStaticDataField ("long3", null, new IntegerDataType ("INT32", false)), new NonStaticDataField ("long4", null, new IntegerDataType ("INT64", false)), new NonStaticDataField ("long5", null, new IntegerDataType ("INT48", false)), new NonStaticDataField ("long6", null, new IntegerDataType ("PUINT30", false)), new NonStaticDataField ("long7", null, new IntegerDataType ("PUINT61", false)), new NonStaticDataField ("long8", null, new IntegerDataType ("PINTERVAL", false)));
    private static final RecordClassDescriptor rcdALongPublicNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPublic.class).getName (), null, false, null, new StaticDataField ("long1", null, new IntegerDataType ("INT8", true), "85"), new StaticDataField ("long2", null, new IntegerDataType ("INT16", true), "-30001"), new StaticDataField ("long3", null, new IntegerDataType ("INT32", true), "2000000001"), new StaticDataField ("long4", null, new IntegerDataType ("INT64", true), "2000000000001"), new StaticDataField ("long5", null, new IntegerDataType ("INT48", true), "140737488355082"), new StaticDataField ("long6", null, new IntegerDataType ("PUINT30", true), "1073741822"), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", true), "2305843009213693950"), new StaticDataField ("long8", null, new IntegerDataType ("PINTERVAL", true), "2147483647"));
    private static final RecordClassDescriptor rcdALongPublicNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPublic.class).getName (), null, false, null, new StaticDataField ("long1", null, new IntegerDataType ("INT8", false), "85"), new StaticDataField ("long2", null, new IntegerDataType ("INT16", false), "-30001"), new StaticDataField ("long3", null, new IntegerDataType ("INT32", false), "2000000001"), new StaticDataField ("long4", null, new IntegerDataType ("INT64", false), "2000000000001"), new StaticDataField ("long5", null, new IntegerDataType ("INT48", false), "140737488355082"), new StaticDataField ("long6", null, new IntegerDataType ("PUINT30", false), "1073741822"), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", false), "2305843009213693950"), new StaticDataField ("long8", null, new IntegerDataType ("PINTERVAL", false), "2147483647"));
    private static final RecordClassDescriptor rcdALongPublicNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPublic.class).getName (), null, false, null, new StaticDataField ("long1", null, new IntegerDataType ("INT8", true), null), new StaticDataField ("long2", null, new IntegerDataType ("INT16", true), null), new StaticDataField ("long3", null, new IntegerDataType ("INT32", true), null), new StaticDataField ("long4", null, new IntegerDataType ("INT64", true), null), new StaticDataField ("long5", null, new IntegerDataType ("INT48", true), null), new StaticDataField ("long6", null, new IntegerDataType ("PUINT30", true), null), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", true), null), new StaticDataField ("long8", null, new IntegerDataType ("PINTERVAL", true), null));
    public static final class ALongPrivate extends InstrumentMessage {
        public long long1;
        public long long2;
        public long long3;
        public long long4;
        public long long5;
        public long long6;
        public long long7;
        public long long8;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return (((((((((((((((this).long1) + (",")) + ((this).long2)) + (",")) + ((this).long3)) + (",")) + ((this).long4)) + (",")) + ((this).long5)) + (",")) + ((this).long6)) + (",")) + ((this).long7)) + (",")) + ((this).long8);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPrivate))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPrivate other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPrivate) (o);
            return (((((((((this).long1) == ((other).long1)) && (((this).long2) == ((other).long2))) && (((this).long3) == ((other).long3))) && (((this).long4) == ((other).long4))) && (((this).long5) == ((other).long5))) && (((this).long6) == ((other).long6))) && (((this).long7) == ((other).long7))) && (((this).long8) == ((other).long8));
        }}
    private static final RecordClassDescriptor rcdALongPrivateNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPrivate.class).getName (), null, false, null, new NonStaticDataField ("long1", null, new IntegerDataType ("INT8", true)), new NonStaticDataField ("long2", null, new IntegerDataType ("INT16", true)), new NonStaticDataField ("long3", null, new IntegerDataType ("INT32", true)), new NonStaticDataField ("long4", null, new IntegerDataType ("INT64", true)), new NonStaticDataField ("long5", null, new IntegerDataType ("INT48", true)), new NonStaticDataField ("long6", null, new IntegerDataType ("PUINT30", true)), new NonStaticDataField ("long7", null, new IntegerDataType ("PUINT61", true)), new NonStaticDataField ("long8", null, new IntegerDataType ("PINTERVAL", true)));
    private static final RecordClassDescriptor rcdALongPrivateNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPrivate.class).getName (), null, false, null, new NonStaticDataField ("long1", null, new IntegerDataType ("INT8", false)), new NonStaticDataField ("long2", null, new IntegerDataType ("INT16", false)), new NonStaticDataField ("long3", null, new IntegerDataType ("INT32", false)), new NonStaticDataField ("long4", null, new IntegerDataType ("INT64", false)), new NonStaticDataField ("long5", null, new IntegerDataType ("INT48", false)), new NonStaticDataField ("long6", null, new IntegerDataType ("PUINT30", false)), new NonStaticDataField ("long7", null, new IntegerDataType ("PUINT61", false)), new NonStaticDataField ("long8", null, new IntegerDataType ("PINTERVAL", false)));
    private static final RecordClassDescriptor rcdALongPrivateNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPrivate.class).getName (), null, false, null, new StaticDataField ("long1", null, new IntegerDataType ("INT8", true), "85"), new StaticDataField ("long2", null, new IntegerDataType ("INT16", true), "-30001"), new StaticDataField ("long3", null, new IntegerDataType ("INT32", true), "2000000001"), new StaticDataField ("long4", null, new IntegerDataType ("INT64", true), "2000000000001"), new StaticDataField ("long5", null, new IntegerDataType ("INT48", true), "140737488355082"), new StaticDataField ("long6", null, new IntegerDataType ("PUINT30", true), "1073741822"), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", true), "2305843009213693950"), new StaticDataField ("long8", null, new IntegerDataType ("PINTERVAL", true), "2147483647"));
    private static final RecordClassDescriptor rcdALongPrivateNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPrivate.class).getName (), null, false, null, new StaticDataField ("long1", null, new IntegerDataType ("INT8", false), "85"), new StaticDataField ("long2", null, new IntegerDataType ("INT16", false), "-30001"), new StaticDataField ("long3", null, new IntegerDataType ("INT32", false), "2000000001"), new StaticDataField ("long4", null, new IntegerDataType ("INT64", false), "2000000000001"), new StaticDataField ("long5", null, new IntegerDataType ("INT48", false), "140737488355082"), new StaticDataField ("long6", null, new IntegerDataType ("PUINT30", false), "1073741822"), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", false), "2305843009213693950"), new StaticDataField ("long8", null, new IntegerDataType ("PINTERVAL", false), "2147483647"));
    private static final RecordClassDescriptor rcdALongPrivateNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPrivate.class).getName (), null, false, null, new StaticDataField ("long1", null, new IntegerDataType ("INT8", true), null), new StaticDataField ("long2", null, new IntegerDataType ("INT16", true), null), new StaticDataField ("long3", null, new IntegerDataType ("INT32", true), null), new StaticDataField ("long4", null, new IntegerDataType ("INT64", true), null), new StaticDataField ("long5", null, new IntegerDataType ("INT48", true), null), new StaticDataField ("long6", null, new IntegerDataType ("PUINT30", true), null), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", true), null), new StaticDataField ("long8", null, new IntegerDataType ("PINTERVAL", true), null));
    public static final class AIntegerPublic extends InstrumentMessage {
        public int int1;
        public int int2;
        public int int3;
        public long long4;
        public long long5;
        public int int6;
        public long long7;
        public int int8;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return (((((((((((((((this).int1) + (",")) + ((this).int2)) + (",")) + ((this).int3)) + (",")) + ((this).long4)) + (",")) + ((this).long5)) + (",")) + ((this).int6)) + (",")) + ((this).long7)) + (",")) + ((this).int8);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPublic))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPublic other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPublic) (o);
            return (((((((((this).int1) == ((other).int1)) && (((this).int2) == ((other).int2))) && (((this).int3) == ((other).int3))) && (((this).long4) == ((other).long4))) && (((this).long5) == ((other).long5))) && (((this).int6) == ((other).int6))) && (((this).long7) == ((other).long7))) && (((this).int8) == ((other).int8));
        }}
    private static final RecordClassDescriptor rcdAIntegerPublicNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPublic.class).getName (), null, false, null, new NonStaticDataField ("int1", null, new IntegerDataType ("INT8", true)), new NonStaticDataField ("int2", null, new IntegerDataType ("INT16", true)), new NonStaticDataField ("int3", null, new IntegerDataType ("INT32", true)), new NonStaticDataField ("long4", null, new IntegerDataType ("INT64", true)), new NonStaticDataField ("long5", null, new IntegerDataType ("INT48", true)), new NonStaticDataField ("int6", null, new IntegerDataType ("PUINT30", true)), new NonStaticDataField ("long7", null, new IntegerDataType ("PUINT61", true)), new NonStaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", true)));
    private static final RecordClassDescriptor rcdAIntegerPublicNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPublic.class).getName (), null, false, null, new NonStaticDataField ("int1", null, new IntegerDataType ("INT8", false)), new NonStaticDataField ("int2", null, new IntegerDataType ("INT16", false)), new NonStaticDataField ("int3", null, new IntegerDataType ("INT32", false)), new NonStaticDataField ("long4", null, new IntegerDataType ("INT64", false)), new NonStaticDataField ("long5", null, new IntegerDataType ("INT48", false)), new NonStaticDataField ("int6", null, new IntegerDataType ("PUINT30", false)), new NonStaticDataField ("long7", null, new IntegerDataType ("PUINT61", false)), new NonStaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", false)));
    private static final RecordClassDescriptor rcdAIntegerPublicNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPublic.class).getName (), null, false, null, new StaticDataField ("int1", null, new IntegerDataType ("INT8", true), "85"), new StaticDataField ("int2", null, new IntegerDataType ("INT16", true), "-30001"), new StaticDataField ("int3", null, new IntegerDataType ("INT32", true), "2000000001"), new StaticDataField ("long4", null, new IntegerDataType ("INT64", true), "2000000000001"), new StaticDataField ("long5", null, new IntegerDataType ("INT48", true), "140737488355082"), new StaticDataField ("int6", null, new IntegerDataType ("PUINT30", true), "1073741822"), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", true), "2305843009213693950"), new StaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", true), "2147483647"));
    private static final RecordClassDescriptor rcdAIntegerPublicNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPublic.class).getName (), null, false, null, new StaticDataField ("int1", null, new IntegerDataType ("INT8", false), "85"), new StaticDataField ("int2", null, new IntegerDataType ("INT16", false), "-30001"), new StaticDataField ("int3", null, new IntegerDataType ("INT32", false), "2000000001"), new StaticDataField ("long4", null, new IntegerDataType ("INT64", false), "2000000000001"), new StaticDataField ("long5", null, new IntegerDataType ("INT48", false), "140737488355082"), new StaticDataField ("int6", null, new IntegerDataType ("PUINT30", false), "1073741822"), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", false), "2305843009213693950"), new StaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", false), "2147483647"));
    private static final RecordClassDescriptor rcdAIntegerPublicNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPublic.class).getName (), null, false, null, new StaticDataField ("int1", null, new IntegerDataType ("INT8", true), null), new StaticDataField ("int2", null, new IntegerDataType ("INT16", true), null), new StaticDataField ("int3", null, new IntegerDataType ("INT32", true), null), new StaticDataField ("long4", null, new IntegerDataType ("INT64", true), null), new StaticDataField ("long5", null, new IntegerDataType ("INT48", true), null), new StaticDataField ("int6", null, new IntegerDataType ("PUINT30", true), null), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", true), null), new StaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", true), null));
    public static final class AIntegerPrivate extends InstrumentMessage {
        public int int1;
        public int int2;
        public int int3;
        public long long4;
        public long long5;
        public int int6;
        public long long7;
        public int int8;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return (((((((((((((((this).int1) + (",")) + ((this).int2)) + (",")) + ((this).int3)) + (",")) + ((this).long4)) + (",")) + ((this).long5)) + (",")) + ((this).int6)) + (",")) + ((this).long7)) + (",")) + ((this).int8);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPrivate))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPrivate other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPrivate) (o);
            return (((((((((this).int1) == ((other).int1)) && (((this).int2) == ((other).int2))) && (((this).int3) == ((other).int3))) && (((this).long4) == ((other).long4))) && (((this).long5) == ((other).long5))) && (((this).int6) == ((other).int6))) && (((this).long7) == ((other).long7))) && (((this).int8) == ((other).int8));
        }}
    private static final RecordClassDescriptor rcdAIntegerPrivateNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPrivate.class).getName (), null, false, null, new NonStaticDataField ("int1", null, new IntegerDataType ("INT8", true)), new NonStaticDataField ("int2", null, new IntegerDataType ("INT16", true)), new NonStaticDataField ("int3", null, new IntegerDataType ("INT32", true)), new NonStaticDataField ("long4", null, new IntegerDataType ("INT64", true)), new NonStaticDataField ("long5", null, new IntegerDataType ("INT48", true)), new NonStaticDataField ("int6", null, new IntegerDataType ("PUINT30", true)), new NonStaticDataField ("long7", null, new IntegerDataType ("PUINT61", true)), new NonStaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", true)));
    private static final RecordClassDescriptor rcdAIntegerPrivateNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPrivate.class).getName (), null, false, null, new NonStaticDataField ("int1", null, new IntegerDataType ("INT8", false)), new NonStaticDataField ("int2", null, new IntegerDataType ("INT16", false)), new NonStaticDataField ("int3", null, new IntegerDataType ("INT32", false)), new NonStaticDataField ("long4", null, new IntegerDataType ("INT64", false)), new NonStaticDataField ("long5", null, new IntegerDataType ("INT48", false)), new NonStaticDataField ("int6", null, new IntegerDataType ("PUINT30", false)), new NonStaticDataField ("long7", null, new IntegerDataType ("PUINT61", false)), new NonStaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", false)));
    private static final RecordClassDescriptor rcdAIntegerPrivateNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPrivate.class).getName (), null, false, null, new StaticDataField ("int1", null, new IntegerDataType ("INT8", true), "85"), new StaticDataField ("int2", null, new IntegerDataType ("INT16", true), "-30001"), new StaticDataField ("int3", null, new IntegerDataType ("INT32", true), "2000000001"), new StaticDataField ("long4", null, new IntegerDataType ("INT64", true), "2000000000001"), new StaticDataField ("long5", null, new IntegerDataType ("INT48", true), "140737488355082"), new StaticDataField ("int6", null, new IntegerDataType ("PUINT30", true), "1073741822"), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", true), "2305843009213693950"), new StaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", true), "2147483647"));
    private static final RecordClassDescriptor rcdAIntegerPrivateNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPrivate.class).getName (), null, false, null, new StaticDataField ("int1", null, new IntegerDataType ("INT8", false), "85"), new StaticDataField ("int2", null, new IntegerDataType ("INT16", false), "-30001"), new StaticDataField ("int3", null, new IntegerDataType ("INT32", false), "2000000001"), new StaticDataField ("long4", null, new IntegerDataType ("INT64", false), "2000000000001"), new StaticDataField ("long5", null, new IntegerDataType ("INT48", false), "140737488355082"), new StaticDataField ("int6", null, new IntegerDataType ("PUINT30", false), "1073741822"), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", false), "2305843009213693950"), new StaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", false), "2147483647"));
    private static final RecordClassDescriptor rcdAIntegerPrivateNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPrivate.class).getName (), null, false, null, new StaticDataField ("int1", null, new IntegerDataType ("INT8", true), null), new StaticDataField ("int2", null, new IntegerDataType ("INT16", true), null), new StaticDataField ("int3", null, new IntegerDataType ("INT32", true), null), new StaticDataField ("long4", null, new IntegerDataType ("INT64", true), null), new StaticDataField ("long5", null, new IntegerDataType ("INT48", true), null), new StaticDataField ("int6", null, new IntegerDataType ("PUINT30", true), null), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", true), null), new StaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", true), null));
    public static final class AShortPublic extends InstrumentMessage {
        public short short1;
        public short short2;
        public int int3;
        public long long4;
        public long long5;
        public int int6;
        public long long7;
        public int int8;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return (((((((((((((((this).short1) + (",")) + ((this).short2)) + (",")) + ((this).int3)) + (",")) + ((this).long4)) + (",")) + ((this).long5)) + (",")) + ((this).int6)) + (",")) + ((this).long7)) + (",")) + ((this).int8);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPublic))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPublic other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPublic) (o);
            return (((((((((this).short1) == ((other).short1)) && (((this).short2) == ((other).short2))) && (((this).int3) == ((other).int3))) && (((this).long4) == ((other).long4))) && (((this).long5) == ((other).long5))) && (((this).int6) == ((other).int6))) && (((this).long7) == ((other).long7))) && (((this).int8) == ((other).int8));
        }}
    private static final RecordClassDescriptor rcdAShortPublicNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPublic.class).getName (), null, false, null, new NonStaticDataField ("short1", null, new IntegerDataType ("INT8", true)), new NonStaticDataField ("short2", null, new IntegerDataType ("INT16", true)), new NonStaticDataField ("int3", null, new IntegerDataType ("INT32", true)), new NonStaticDataField ("long4", null, new IntegerDataType ("INT64", true)), new NonStaticDataField ("long5", null, new IntegerDataType ("INT48", true)), new NonStaticDataField ("int6", null, new IntegerDataType ("PUINT30", true)), new NonStaticDataField ("long7", null, new IntegerDataType ("PUINT61", true)), new NonStaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", true)));
    private static final RecordClassDescriptor rcdAShortPublicNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPublic.class).getName (), null, false, null, new NonStaticDataField ("short1", null, new IntegerDataType ("INT8", false)), new NonStaticDataField ("short2", null, new IntegerDataType ("INT16", false)), new NonStaticDataField ("int3", null, new IntegerDataType ("INT32", false)), new NonStaticDataField ("long4", null, new IntegerDataType ("INT64", false)), new NonStaticDataField ("long5", null, new IntegerDataType ("INT48", false)), new NonStaticDataField ("int6", null, new IntegerDataType ("PUINT30", false)), new NonStaticDataField ("long7", null, new IntegerDataType ("PUINT61", false)), new NonStaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", false)));
    private static final RecordClassDescriptor rcdAShortPublicNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPublic.class).getName (), null, false, null, new StaticDataField ("short1", null, new IntegerDataType ("INT8", true), "85"), new StaticDataField ("short2", null, new IntegerDataType ("INT16", true), "-30001"), new StaticDataField ("int3", null, new IntegerDataType ("INT32", true), "2000000001"), new StaticDataField ("long4", null, new IntegerDataType ("INT64", true), "2000000000001"), new StaticDataField ("long5", null, new IntegerDataType ("INT48", true), "140737488355082"), new StaticDataField ("int6", null, new IntegerDataType ("PUINT30", true), "1073741822"), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", true), "2305843009213693950"), new StaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", true), "2147483647"));
    private static final RecordClassDescriptor rcdAShortPublicNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPublic.class).getName (), null, false, null, new StaticDataField ("short1", null, new IntegerDataType ("INT8", false), "85"), new StaticDataField ("short2", null, new IntegerDataType ("INT16", false), "-30001"), new StaticDataField ("int3", null, new IntegerDataType ("INT32", false), "2000000001"), new StaticDataField ("long4", null, new IntegerDataType ("INT64", false), "2000000000001"), new StaticDataField ("long5", null, new IntegerDataType ("INT48", false), "140737488355082"), new StaticDataField ("int6", null, new IntegerDataType ("PUINT30", false), "1073741822"), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", false), "2305843009213693950"), new StaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", false), "2147483647"));
    private static final RecordClassDescriptor rcdAShortPublicNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPublic.class).getName (), null, false, null, new StaticDataField ("short1", null, new IntegerDataType ("INT8", true), null), new StaticDataField ("short2", null, new IntegerDataType ("INT16", true), null), new StaticDataField ("int3", null, new IntegerDataType ("INT32", true), null), new StaticDataField ("long4", null, new IntegerDataType ("INT64", true), null), new StaticDataField ("long5", null, new IntegerDataType ("INT48", true), null), new StaticDataField ("int6", null, new IntegerDataType ("PUINT30", true), null), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", true), null), new StaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", true), null));
    public static final class AShortPrivate extends InstrumentMessage {
        public short short1;
        public short short2;
        public int int3;
        public long long4;
        public long long5;
        public int int6;
        public long long7;
        public int int8;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return (((((((((((((((this).short1) + (",")) + ((this).short2)) + (",")) + ((this).int3)) + (",")) + ((this).long4)) + (",")) + ((this).long5)) + (",")) + ((this).int6)) + (",")) + ((this).long7)) + (",")) + ((this).int8);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPrivate))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPrivate other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPrivate) (o);
            return (((((((((this).short1) == ((other).short1)) && (((this).short2) == ((other).short2))) && (((this).int3) == ((other).int3))) && (((this).long4) == ((other).long4))) && (((this).long5) == ((other).long5))) && (((this).int6) == ((other).int6))) && (((this).long7) == ((other).long7))) && (((this).int8) == ((other).int8));
        }}
    private static final RecordClassDescriptor rcdAShortPrivateNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPrivate.class).getName (), null, false, null, new NonStaticDataField ("short1", null, new IntegerDataType ("INT8", true)), new NonStaticDataField ("short2", null, new IntegerDataType ("INT16", true)), new NonStaticDataField ("int3", null, new IntegerDataType ("INT32", true)), new NonStaticDataField ("long4", null, new IntegerDataType ("INT64", true)), new NonStaticDataField ("long5", null, new IntegerDataType ("INT48", true)), new NonStaticDataField ("int6", null, new IntegerDataType ("PUINT30", true)), new NonStaticDataField ("long7", null, new IntegerDataType ("PUINT61", true)), new NonStaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", true)));
    private static final RecordClassDescriptor rcdAShortPrivateNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPrivate.class).getName (), null, false, null, new NonStaticDataField ("short1", null, new IntegerDataType ("INT8", false)), new NonStaticDataField ("short2", null, new IntegerDataType ("INT16", false)), new NonStaticDataField ("int3", null, new IntegerDataType ("INT32", false)), new NonStaticDataField ("long4", null, new IntegerDataType ("INT64", false)), new NonStaticDataField ("long5", null, new IntegerDataType ("INT48", false)), new NonStaticDataField ("int6", null, new IntegerDataType ("PUINT30", false)), new NonStaticDataField ("long7", null, new IntegerDataType ("PUINT61", false)), new NonStaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", false)));
    private static final RecordClassDescriptor rcdAShortPrivateNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPrivate.class).getName (), null, false, null, new StaticDataField ("short1", null, new IntegerDataType ("INT8", true), "85"), new StaticDataField ("short2", null, new IntegerDataType ("INT16", true), "-30001"), new StaticDataField ("int3", null, new IntegerDataType ("INT32", true), "2000000001"), new StaticDataField ("long4", null, new IntegerDataType ("INT64", true), "2000000000001"), new StaticDataField ("long5", null, new IntegerDataType ("INT48", true), "140737488355082"), new StaticDataField ("int6", null, new IntegerDataType ("PUINT30", true), "1073741822"), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", true), "2305843009213693950"), new StaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", true), "2147483647"));
    private static final RecordClassDescriptor rcdAShortPrivateNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPrivate.class).getName (), null, false, null, new StaticDataField ("short1", null, new IntegerDataType ("INT8", false), "85"), new StaticDataField ("short2", null, new IntegerDataType ("INT16", false), "-30001"), new StaticDataField ("int3", null, new IntegerDataType ("INT32", false), "2000000001"), new StaticDataField ("long4", null, new IntegerDataType ("INT64", false), "2000000000001"), new StaticDataField ("long5", null, new IntegerDataType ("INT48", false), "140737488355082"), new StaticDataField ("int6", null, new IntegerDataType ("PUINT30", false), "1073741822"), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", false), "2305843009213693950"), new StaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", false), "2147483647"));
    private static final RecordClassDescriptor rcdAShortPrivateNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPrivate.class).getName (), null, false, null, new StaticDataField ("short1", null, new IntegerDataType ("INT8", true), null), new StaticDataField ("short2", null, new IntegerDataType ("INT16", true), null), new StaticDataField ("int3", null, new IntegerDataType ("INT32", true), null), new StaticDataField ("long4", null, new IntegerDataType ("INT64", true), null), new StaticDataField ("long5", null, new IntegerDataType ("INT48", true), null), new StaticDataField ("int6", null, new IntegerDataType ("PUINT30", true), null), new StaticDataField ("long7", null, new IntegerDataType ("PUINT61", true), null), new StaticDataField ("int8", null, new IntegerDataType ("PINTERVAL", true), null));
    @org.junit.Test
    public void testIntegerDataTypeBindingComp ()
    {
        super.setUpComp ();
        this.testIntegerDataTypeBinding ();
    }
    @org.junit.Test
    public void testIntegerDataTypeBindingIntp ()
    {
        super.setUpIntp ();
        this.testIntegerDataTypeBinding ();
    }
    private void testIntegerDataTypeRange ()
    {
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPublic ();
            (msg).long1 = -129;
            (msg).long2 = -32769;
            (msg).long3 = -2147483649L;
            (msg).long5 = -140737488355329L;
            (msg).long6 = -1;
            (msg).long7 = -1;
            (msg).long8 = -1;
            try {
                super.boundEncode (msg, rcdALongPublicNullable);
                org.junit.Assert.fail ("INT8 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -129", (this).getFieldString (msg, "long1"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("INT8 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -129", (this).getFieldString (msg, "long1"))).toString (), (e).toString ());
            }
            (msg).long1 = 85;
            try {
                super.boundEncode (msg, rcdALongPublicNullable);
                org.junit.Assert.fail ("INT16 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT16 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -32769", (this).getFieldString (msg, "long2"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("INT16 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT16 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -32769", (this).getFieldString (msg, "long2"))).toString (), (e).toString ());
            }
            (msg).long2 = -30001;
            try {
                super.boundEncode (msg, rcdALongPublicNullable);
                org.junit.Assert.fail ("INT32 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT32 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -2147483649", (this).getFieldString (msg, "long3"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("INT32 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT32 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -2147483649", (this).getFieldString (msg, "long3"))).toString (), (e).toString ());
            }
            (msg).long3 = 2000000001;
            (msg).long4 = 2000000000001L;
            try {
                super.boundEncode (msg, rcdALongPublicNullable);
                org.junit.Assert.fail ("INT48 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT48 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -140737488355329", (this).getFieldString (msg, "long5"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("INT48 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT48 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -140737488355329", (this).getFieldString (msg, "long5"))).toString (), (e).toString ());
            }
            (msg).long5 = 140737488355082L;
            try {
                super.boundEncode (msg, rcdALongPublicNullable);
                org.junit.Assert.fail ("PUINT30 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "long6"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("PUINT30 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "long6"))).toString (), (e).toString ());
            }
            (msg).long6 = 1073741822;
            try {
                super.boundEncode (msg, rcdALongPublicNullable);
                org.junit.Assert.fail ("PUINT61 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "long7"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("PUINT61 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "long7"))).toString (), (e).toString ());
            }
            (msg).long7 = 2305843009213693950L;
            try {
                super.boundEncode (msg, rcdALongPublicNullable);
                org.junit.Assert.fail ("PINTERVAL - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "long8"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("PINTERVAL - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "long8"))).toString (), (e).toString ());
            }
            (msg).long1 = 128;
            (msg).long2 = 32768;
            (msg).long3 = 2147483648L;
            (msg).long5 = 140737488355328L;
            (msg).long6 = 1073741823;
            (msg).long7 = 2305843009213693951L;
            (msg).long8 = 2147483648L;
            try {
                super.boundEncode (msg, rcdALongPublicNullable);
                org.junit.Assert.fail ("INT8 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 128", (this).getFieldString (msg, "long1"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("INT8 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 128", (this).getFieldString (msg, "long1"))).toString (), (e).toString ());
            }
            (msg).long1 = 85;
            try {
                super.boundEncode (msg, rcdALongPublicNullable);
                org.junit.Assert.fail ("INT16 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT16 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 32768", (this).getFieldString (msg, "long2"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("INT16 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT16 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 32768", (this).getFieldString (msg, "long2"))).toString (), (e).toString ());
            }
            (msg).long2 = -30001;
            try {
                super.boundEncode (msg, rcdALongPublicNullable);
                org.junit.Assert.fail ("INT32 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT32 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 2147483648", (this).getFieldString (msg, "long3"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("INT32 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT32 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 2147483648", (this).getFieldString (msg, "long3"))).toString (), (e).toString ());
            }
            (msg).long3 = 2000000001;
            (msg).long4 = 2000000000001L;
            try {
                super.boundEncode (msg, rcdALongPublicNullable);
                org.junit.Assert.fail ("INT48 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT48 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 140737488355328", (this).getFieldString (msg, "long5"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("INT48 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT48 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 140737488355328", (this).getFieldString (msg, "long5"))).toString (), (e).toString ());
            }
            (msg).long5 = 140737488355082L;
            try {
                super.boundEncode (msg, rcdALongPublicNullable);
                org.junit.Assert.fail ("PUINT30 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 1073741823", (this).getFieldString (msg, "long6"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("PUINT30 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 1073741823", (this).getFieldString (msg, "long6"))).toString (), (e).toString ());
            }
            (msg).long6 = 1073741822;
            try {
                super.boundEncode (msg, rcdALongPublicNullable);
                org.junit.Assert.fail ("PUINT61 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 2305843009213693951", (this).getFieldString (msg, "long7"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("PUINT61 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 2305843009213693951", (this).getFieldString (msg, "long7"))).toString (), (e).toString ());
            }
            (msg).long7 = 2305843009213693950L;
            try {
                super.boundEncode (msg, rcdALongPublicNullable);
                org.junit.Assert.fail ("PINTERVAL - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 2147483648", (this).getFieldString (msg, "long8"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPublicNotNullable);
                org.junit.Assert.fail ("PINTERVAL - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 2147483648", (this).getFieldString (msg, "long8"))).toString (), (e).toString ());
            }
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.ALongPrivate ();
            (msg).long1 = -129;
            (msg).long2 = -32769;
            (msg).long3 = -2147483649L;
            (msg).long5 = -140737488355329L;
            (msg).long6 = -1;
            (msg).long7 = -1;
            (msg).long8 = -1;
            try {
                super.boundEncode (msg, rcdALongPrivateNullable);
                org.junit.Assert.fail ("INT8 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -129", (this).getFieldString (msg, "long1"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("INT8 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -129", (this).getFieldString (msg, "long1"))).toString (), (e).toString ());
            }
            (msg).long1 = 85;
            try {
                super.boundEncode (msg, rcdALongPrivateNullable);
                org.junit.Assert.fail ("INT16 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT16 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -32769", (this).getFieldString (msg, "long2"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("INT16 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT16 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -32769", (this).getFieldString (msg, "long2"))).toString (), (e).toString ());
            }
            (msg).long2 = -30001;
            try {
                super.boundEncode (msg, rcdALongPrivateNullable);
                org.junit.Assert.fail ("INT32 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT32 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -2147483649", (this).getFieldString (msg, "long3"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("INT32 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT32 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -2147483649", (this).getFieldString (msg, "long3"))).toString (), (e).toString ());
            }
            (msg).long3 = 2000000001;
            (msg).long4 = 2000000000001L;
            try {
                super.boundEncode (msg, rcdALongPrivateNullable);
                org.junit.Assert.fail ("INT48 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT48 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -140737488355329", (this).getFieldString (msg, "long5"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("INT48 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT48 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -140737488355329", (this).getFieldString (msg, "long5"))).toString (), (e).toString ());
            }
            (msg).long5 = 140737488355082L;
            try {
                super.boundEncode (msg, rcdALongPrivateNullable);
                org.junit.Assert.fail ("PUINT30 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "long6"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("PUINT30 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "long6"))).toString (), (e).toString ());
            }
            (msg).long6 = 1073741822;
            try {
                super.boundEncode (msg, rcdALongPrivateNullable);
                org.junit.Assert.fail ("PUINT61 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "long7"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("PUINT61 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "long7"))).toString (), (e).toString ());
            }
            (msg).long7 = 2305843009213693950L;
            try {
                super.boundEncode (msg, rcdALongPrivateNullable);
                org.junit.Assert.fail ("PINTERVAL - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "long8"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("PINTERVAL - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "long8"))).toString (), (e).toString ());
            }
            (msg).long1 = 128;
            (msg).long2 = 32768;
            (msg).long3 = 2147483648L;
            (msg).long5 = 140737488355328L;
            (msg).long6 = 1073741823;
            (msg).long7 = 2305843009213693951L;
            (msg).long8 = 2147483648L;
            try {
                super.boundEncode (msg, rcdALongPrivateNullable);
                org.junit.Assert.fail ("INT8 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 128", (this).getFieldString (msg, "long1"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("INT8 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 128", (this).getFieldString (msg, "long1"))).toString (), (e).toString ());
            }
            (msg).long1 = 85;
            try {
                super.boundEncode (msg, rcdALongPrivateNullable);
                org.junit.Assert.fail ("INT16 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT16 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 32768", (this).getFieldString (msg, "long2"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("INT16 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT16 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 32768", (this).getFieldString (msg, "long2"))).toString (), (e).toString ());
            }
            (msg).long2 = -30001;
            try {
                super.boundEncode (msg, rcdALongPrivateNullable);
                org.junit.Assert.fail ("INT32 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT32 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 2147483648", (this).getFieldString (msg, "long3"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("INT32 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT32 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 2147483648", (this).getFieldString (msg, "long3"))).toString (), (e).toString ());
            }
            (msg).long3 = 2000000001;
            (msg).long4 = 2000000000001L;
            try {
                super.boundEncode (msg, rcdALongPrivateNullable);
                org.junit.Assert.fail ("INT48 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT48 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 140737488355328", (this).getFieldString (msg, "long5"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("INT48 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT48 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 140737488355328", (this).getFieldString (msg, "long5"))).toString (), (e).toString ());
            }
            (msg).long5 = 140737488355082L;
            try {
                super.boundEncode (msg, rcdALongPrivateNullable);
                org.junit.Assert.fail ("PUINT30 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 1073741823", (this).getFieldString (msg, "long6"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("PUINT30 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 1073741823", (this).getFieldString (msg, "long6"))).toString (), (e).toString ());
            }
            (msg).long6 = 1073741822;
            try {
                super.boundEncode (msg, rcdALongPrivateNullable);
                org.junit.Assert.fail ("PUINT61 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 2305843009213693951", (this).getFieldString (msg, "long7"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("PUINT61 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 2305843009213693951", (this).getFieldString (msg, "long7"))).toString (), (e).toString ());
            }
            (msg).long7 = 2305843009213693950L;
            try {
                super.boundEncode (msg, rcdALongPrivateNullable);
                org.junit.Assert.fail ("PINTERVAL - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 2147483648", (this).getFieldString (msg, "long8"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdALongPrivateNotNullable);
                org.junit.Assert.fail ("PINTERVAL - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 2147483648", (this).getFieldString (msg, "long8"))).toString (), (e).toString ());
            }
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPublic ();
            (msg).int1 = -129;
            (msg).int2 = -32769;
            (msg).int6 = -1;
            (msg).long7 = -1;
            (msg).int8 = -1;
            try {
                super.boundEncode (msg, rcdAIntegerPublicNullable);
                org.junit.Assert.fail ("INT8 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -129", (this).getFieldString (msg, "int1"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAIntegerPublicNotNullable);
                org.junit.Assert.fail ("INT8 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -129", (this).getFieldString (msg, "int1"))).toString (), (e).toString ());
            }
            (msg).int1 = 85;
            try {
                super.boundEncode (msg, rcdAIntegerPublicNullable);
                org.junit.Assert.fail ("INT16 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT16 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -32769", (this).getFieldString (msg, "int2"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAIntegerPublicNotNullable);
                org.junit.Assert.fail ("INT16 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT16 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -32769", (this).getFieldString (msg, "int2"))).toString (), (e).toString ());
            }
            (msg).int2 = -30001;
            (msg).int3 = 2000000001;
            (msg).long4 = 2000000000001L;
            (msg).long5 = 140737488355082L;
            try {
                super.boundEncode (msg, rcdAIntegerPublicNullable);
                org.junit.Assert.fail ("PUINT30 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "int6"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAIntegerPublicNotNullable);
                org.junit.Assert.fail ("PUINT30 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "int6"))).toString (), (e).toString ());
            }
            (msg).int6 = 1073741822;
            try {
                super.boundEncode (msg, rcdAIntegerPublicNullable);
                org.junit.Assert.fail ("PUINT61 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "long7"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAIntegerPublicNotNullable);
                org.junit.Assert.fail ("PUINT61 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "long7"))).toString (), (e).toString ());
            }
            (msg).long7 = 2305843009213693950L;
            try {
                super.boundEncode (msg, rcdAIntegerPublicNullable);
                org.junit.Assert.fail ("PINTERVAL - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "int8"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAIntegerPublicNotNullable);
                org.junit.Assert.fail ("PINTERVAL - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "int8"))).toString (), (e).toString ());
            }
            (msg).int1 = 128;
            try {
                super.boundEncode (msg, rcdAIntegerPublicNullable);
                org.junit.Assert.fail ("INT8 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 128", (this).getFieldString (msg, "int1"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAIntegerPublicNotNullable);
                org.junit.Assert.fail ("INT8 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 128", (this).getFieldString (msg, "int1"))).toString (), (e).toString ());
            }
            (msg).int1 = 85;
            (msg).int2 = -30001;
            (msg).int3 = 2000000001;
            (msg).long4 = 2000000000001L;
            (msg).long5 = 140737488355082L;
            (msg).int6 = 1073741822;
            (msg).long7 = 2305843009213693950L;
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AIntegerPrivate ();
            (msg).int1 = -129;
            (msg).int2 = -32769;
            (msg).int6 = -1;
            (msg).long7 = -1;
            (msg).int8 = -1;
            try {
                super.boundEncode (msg, rcdAIntegerPrivateNullable);
                org.junit.Assert.fail ("INT8 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -129", (this).getFieldString (msg, "int1"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAIntegerPrivateNotNullable);
                org.junit.Assert.fail ("INT8 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -129", (this).getFieldString (msg, "int1"))).toString (), (e).toString ());
            }
            (msg).int1 = 85;
            try {
                super.boundEncode (msg, rcdAIntegerPrivateNullable);
                org.junit.Assert.fail ("INT16 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT16 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -32769", (this).getFieldString (msg, "int2"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAIntegerPrivateNotNullable);
                org.junit.Assert.fail ("INT16 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT16 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -32769", (this).getFieldString (msg, "int2"))).toString (), (e).toString ());
            }
            (msg).int2 = -30001;
            (msg).int3 = 2000000001;
            (msg).long4 = 2000000000001L;
            (msg).long5 = 140737488355082L;
            try {
                super.boundEncode (msg, rcdAIntegerPrivateNullable);
                org.junit.Assert.fail ("PUINT30 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "int6"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAIntegerPrivateNotNullable);
                org.junit.Assert.fail ("PUINT30 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "int6"))).toString (), (e).toString ());
            }
            (msg).int6 = 1073741822;
            try {
                super.boundEncode (msg, rcdAIntegerPrivateNullable);
                org.junit.Assert.fail ("PUINT61 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "long7"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAIntegerPrivateNotNullable);
                org.junit.Assert.fail ("PUINT61 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "long7"))).toString (), (e).toString ());
            }
            (msg).long7 = 2305843009213693950L;
            try {
                super.boundEncode (msg, rcdAIntegerPrivateNullable);
                org.junit.Assert.fail ("PINTERVAL - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "int8"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAIntegerPrivateNotNullable);
                org.junit.Assert.fail ("PINTERVAL - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "int8"))).toString (), (e).toString ());
            }
            (msg).int1 = 128;
            try {
                super.boundEncode (msg, rcdAIntegerPrivateNullable);
                org.junit.Assert.fail ("INT8 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 128", (this).getFieldString (msg, "int1"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAIntegerPrivateNotNullable);
                org.junit.Assert.fail ("INT8 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 128", (this).getFieldString (msg, "int1"))).toString (), (e).toString ());
            }
            (msg).int1 = 85;
            (msg).int2 = -30001;
            (msg).int3 = 2000000001;
            (msg).long4 = 2000000000001L;
            (msg).long5 = 140737488355082L;
            (msg).int6 = 1073741822;
            (msg).long7 = 2305843009213693950L;
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPublic ();
            (msg).short1 = -129;
            (msg).int6 = -1;
            (msg).long7 = -1;
            (msg).int8 = -1;
            try {
                super.boundEncode (msg, rcdAShortPublicNullable);
                org.junit.Assert.fail ("INT8 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -129", (this).getFieldString (msg, "short1"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAShortPublicNotNullable);
                org.junit.Assert.fail ("INT8 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -129", (this).getFieldString (msg, "short1"))).toString (), (e).toString ());
            }
            (msg).short1 = 85;
            (msg).short2 = -30001;
            (msg).int3 = 2000000001;
            (msg).long4 = 2000000000001L;
            (msg).long5 = 140737488355082L;
            try {
                super.boundEncode (msg, rcdAShortPublicNullable);
                org.junit.Assert.fail ("PUINT30 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "int6"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAShortPublicNotNullable);
                org.junit.Assert.fail ("PUINT30 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "int6"))).toString (), (e).toString ());
            }
            (msg).int6 = 1073741822;
            try {
                super.boundEncode (msg, rcdAShortPublicNullable);
                org.junit.Assert.fail ("PUINT61 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "long7"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAShortPublicNotNullable);
                org.junit.Assert.fail ("PUINT61 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "long7"))).toString (), (e).toString ());
            }
            (msg).long7 = 2305843009213693950L;
            try {
                super.boundEncode (msg, rcdAShortPublicNullable);
                org.junit.Assert.fail ("PINTERVAL - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "int8"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAShortPublicNotNullable);
                org.junit.Assert.fail ("PINTERVAL - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "int8"))).toString (), (e).toString ());
            }
            (msg).short1 = 128;
            (msg).int6 = 1073741823;
            try {
                super.boundEncode (msg, rcdAShortPublicNullable);
                org.junit.Assert.fail ("INT8 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 128", (this).getFieldString (msg, "short1"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAShortPublicNotNullable);
                org.junit.Assert.fail ("INT8 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 128", (this).getFieldString (msg, "short1"))).toString (), (e).toString ());
            }
            (msg).short1 = 85;
            (msg).short2 = -30001;
            (msg).int3 = 2000000001;
            (msg).long4 = 2000000000001L;
            (msg).long5 = 140737488355082L;
            try {
                super.boundEncode (msg, rcdAShortPublicNullable);
                org.junit.Assert.fail ("PUINT30 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 1073741823", (this).getFieldString (msg, "int6"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAShortPublicNotNullable);
                org.junit.Assert.fail ("PUINT30 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 1073741823", (this).getFieldString (msg, "int6"))).toString (), (e).toString ());
            }
            (msg).int6 = 1073741822;
            (msg).long7 = 2305843009213693950L;
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AShortPrivate ();
            (msg).short1 = -129;
            (msg).int6 = -1;
            (msg).long7 = -1;
            (msg).int8 = -1;
            try {
                super.boundEncode (msg, rcdAShortPrivateNullable);
                org.junit.Assert.fail ("INT8 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -129", (this).getFieldString (msg, "short1"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAShortPrivateNotNullable);
                org.junit.Assert.fail ("INT8 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -129", (this).getFieldString (msg, "short1"))).toString (), (e).toString ());
            }
            (msg).short1 = 85;
            (msg).short2 = -30001;
            (msg).int3 = 2000000001;
            (msg).long4 = 2000000000001L;
            (msg).long5 = 140737488355082L;
            try {
                super.boundEncode (msg, rcdAShortPrivateNullable);
                org.junit.Assert.fail ("PUINT30 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "int6"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAShortPrivateNotNullable);
                org.junit.Assert.fail ("PUINT30 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "int6"))).toString (), (e).toString ());
            }
            (msg).int6 = 1073741822;
            try {
                super.boundEncode (msg, rcdAShortPrivateNullable);
                org.junit.Assert.fail ("PUINT61 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "long7"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAShortPrivateNotNullable);
                org.junit.Assert.fail ("PUINT61 - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT61 - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "long7"))).toString (), (e).toString ());
            }
            (msg).long7 = 2305843009213693950L;
            try {
                super.boundEncode (msg, rcdAShortPrivateNullable);
                org.junit.Assert.fail ("PINTERVAL - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "int8"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAShortPrivateNotNullable);
                org.junit.Assert.fail ("PINTERVAL - low");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PINTERVAL - low", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == -1", (this).getFieldString (msg, "int8"))).toString (), (e).toString ());
            }
            (msg).short1 = 128;
            (msg).int6 = 1073741823;
            try {
                super.boundEncode (msg, rcdAShortPrivateNullable);
                org.junit.Assert.fail ("INT8 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 128", (this).getFieldString (msg, "short1"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAShortPrivateNotNullable);
                org.junit.Assert.fail ("INT8 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("INT8 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 128", (this).getFieldString (msg, "short1"))).toString (), (e).toString ());
            }
            (msg).short1 = 85;
            (msg).short2 = -30001;
            (msg).int3 = 2000000001;
            (msg).long4 = 2000000000001L;
            (msg).long5 = 140737488355082L;
            try {
                super.boundEncode (msg, rcdAShortPrivateNullable);
                org.junit.Assert.fail ("PUINT30 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 1073741823", (this).getFieldString (msg, "int6"))).toString (), (e).toString ());
            }
            try {
                super.boundEncode (msg, rcdAShortPrivateNotNullable);
                org.junit.Assert.fail ("PUINT30 - up");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("PUINT30 - up", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 1073741823", (this).getFieldString (msg, "int6"))).toString (), (e).toString ());
            }
            (msg).int6 = 1073741822;
            (msg).long7 = 2305843009213693950L;
        }
    }
    @org.junit.Test
    public void testIntegerDataTypeRangeComp ()
    {
        super.setUpComp ();
        this.testIntegerDataTypeRange ();
    }
    @org.junit.Test
    public void testIntegerDataTypeRangeIntp ()
    {
        super.setUpIntp ();
        this.testIntegerDataTypeRange ();
    }
    private void testFloatDataType ()
    {
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPublic ();
            (msg).float1 = 23456.34F;
            (msg).double2 = 7.64565777632E7;
            (msg).double3 = 7.64565777633E7;
            (msg).double4 = 7.645657737E7;
            (msg).double5 = 7.6456577769E7;
            super.testRcdBound ("public nullable - normal values", msg, rcdAllFloatPublicNullable);
            super.testRcdBound ("public non-nullable - normal values", msg, rcdAllFloatPublicNotNullable);
            super.testRcdBound ("public nullable static - normal values", msg, rcdAllFloatPublicNullableStatic);
            super.testRcdBound ("public non-nullable static - normal values", msg, rcdAllFloatPublicNotNullableStatic);
            (msg).float1 = java.lang.Float.NaN;
            (msg).double2 = java.lang.Double.NaN;
            (msg).double3 = java.lang.Double.NaN;
            (msg).double4 = java.lang.Double.NaN;
            (msg).double5 = java.lang.Double.NaN;
            super.testRcdBound ("public nullable - null values", msg, rcdAllFloatPublicNullable);
            super.testRcdBound ("public nullable static - null values", msg, rcdAllFloatPublicNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPublic ();
            (msg).float1 = java.lang.Float.NaN;
            (msg).double2 = java.lang.Double.NaN;
            (msg).double3 = java.lang.Double.NaN;
            (msg).double4 = java.lang.Double.NaN;
            (msg).double5 = java.lang.Double.NaN;
            try {
                super.boundEncode (msg, rcdAllFloatPublicNotNullable);
                org.junit.Assert.fail ("IEEE32 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("IEEE32 - null violation - public", "java.lang.IllegalArgumentException: \'float1\' field is not nullable", (e).toString ());
            }
            (msg).float1 = 23456.34F;
            try {
                super.boundEncode (msg, rcdAllFloatPublicNotNullable);
                org.junit.Assert.fail ("IEEE64 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("IEEE64 - null violation - public", "java.lang.IllegalArgumentException: \'double2\' field is not nullable", (e).toString ());
            }
            (msg).double2 = 7.64565777632E7;
            try {
                super.boundEncode (msg, rcdAllFloatPublicNotNullable);
                org.junit.Assert.fail ("DECIMAL - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("DECIMAL - null violation - public", "java.lang.IllegalArgumentException: \'double3\' field is not nullable", (e).toString ());
            }
            (msg).double3 = 7.64565777633E7;
            try {
                super.boundEncode (msg, rcdAllFloatPublicNotNullable);
                org.junit.Assert.fail ("DECIMAL(2) - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("DECIMAL(2) - null violation - public", "java.lang.IllegalArgumentException: \'double4\' field is not nullable", (e).toString ());
            }
            (msg).double4 = 7.645657737E7;
            try {
                super.boundEncode (msg, rcdAllFloatPublicNotNullable);
                org.junit.Assert.fail ("DECIMAL(4) - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("DECIMAL(4) - null violation - public", "java.lang.IllegalArgumentException: \'double5\' field is not nullable", (e).toString ());
            }
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPrivate ();
            (msg).float1 = 23456.34F;
            (msg).double2 = 7.64565777632E7;
            (msg).double3 = 7.64565777633E7;
            (msg).double4 = 7.645657737E7;
            (msg).double5 = 7.6456577769E7;
            super.testRcdBound ("private nullable - normal values", msg, rcdAllFloatPrivateNullable);
            super.testRcdBound ("private non-nullable - normal values", msg, rcdAllFloatPrivateNotNullable);
            super.testRcdBound ("private nullable static - normal values", msg, rcdAllFloatPrivateNullableStatic);
            super.testRcdBound ("private non-nullable static - normal values", msg, rcdAllFloatPrivateNotNullableStatic);
            (msg).float1 = java.lang.Float.NaN;
            (msg).double2 = java.lang.Double.NaN;
            (msg).double3 = java.lang.Double.NaN;
            (msg).double4 = java.lang.Double.NaN;
            (msg).double5 = java.lang.Double.NaN;
            super.testRcdBound ("private nullable - null values", msg, rcdAllFloatPrivateNullable);
            super.testRcdBound ("private nullable static - null values", msg, rcdAllFloatPrivateNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPrivate ();
            (msg).float1 = java.lang.Float.NaN;
            (msg).double2 = java.lang.Double.NaN;
            (msg).double3 = java.lang.Double.NaN;
            (msg).double4 = java.lang.Double.NaN;
            (msg).double5 = java.lang.Double.NaN;
            try {
                super.boundEncode (msg, rcdAllFloatPrivateNotNullable);
                org.junit.Assert.fail ("IEEE32 - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("IEEE32 - null violation - private", "java.lang.IllegalArgumentException: \'float1\' field is not nullable", (e).toString ());
            }
            (msg).float1 = 23456.34F;
            try {
                super.boundEncode (msg, rcdAllFloatPrivateNotNullable);
                org.junit.Assert.fail ("IEEE64 - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("IEEE64 - null violation - private", "java.lang.IllegalArgumentException: \'double2\' field is not nullable", (e).toString ());
            }
            (msg).double2 = 7.64565777632E7;
            try {
                super.boundEncode (msg, rcdAllFloatPrivateNotNullable);
                org.junit.Assert.fail ("DECIMAL - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("DECIMAL - null violation - private", "java.lang.IllegalArgumentException: \'double3\' field is not nullable", (e).toString ());
            }
            (msg).double3 = 7.64565777633E7;
            try {
                super.boundEncode (msg, rcdAllFloatPrivateNotNullable);
                org.junit.Assert.fail ("DECIMAL(2) - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("DECIMAL(2) - null violation - private", "java.lang.IllegalArgumentException: \'double4\' field is not nullable", (e).toString ());
            }
            (msg).double4 = 7.645657737E7;
            try {
                super.boundEncode (msg, rcdAllFloatPrivateNotNullable);
                org.junit.Assert.fail ("DECIMAL(4) - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("DECIMAL(4) - null violation - private", "java.lang.IllegalArgumentException: \'double5\' field is not nullable", (e).toString ());
            }
        }
    }
    public static final class AllFloatPublic extends InstrumentMessage {
        public float float1;
        public double double2;
        public double double3;
        public double double4;
        public double double5;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return (((((((((this).float1) + (",")) + ((this).double2)) + (",")) + ((this).double3)) + (",")) + ((this).double4)) + (",")) + ((this).double5);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPublic))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPublic other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPublic) (o);
            return (((((java.lang.Float.compare ((this).float1, (other).float1)) == (0)) && ((java.lang.Double.compare ((this).double2, (other).double2)) == (0))) && ((java.lang.Double.compare ((this).double3, (other).double3)) == (0))) && ((java.lang.Double.compare ((this).double4, (other).double4)) == (0))) && ((java.lang.Double.compare ((this).double5, (other).double5)) == (0));
        }}
    private static final RecordClassDescriptor rcdAllFloatPublicNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPublic.class).getName (), null, false, null, new NonStaticDataField ("float1", null, new FloatDataType ("IEEE32", true)), new NonStaticDataField ("double2", null, new FloatDataType ("IEEE64", true)), new NonStaticDataField ("double3", null, new FloatDataType ("DECIMAL", true)), new NonStaticDataField ("double4", null, new FloatDataType ("DECIMAL(2)", true)), new NonStaticDataField ("double5", null, new FloatDataType ("DECIMAL(4)", true)));
    private static final RecordClassDescriptor rcdAllFloatPublicNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPublic.class).getName (), null, false, null, new NonStaticDataField ("float1", null, new FloatDataType ("IEEE32", false)), new NonStaticDataField ("double2", null, new FloatDataType ("IEEE64", false)), new NonStaticDataField ("double3", null, new FloatDataType ("DECIMAL", false)), new NonStaticDataField ("double4", null, new FloatDataType ("DECIMAL(2)", false)), new NonStaticDataField ("double5", null, new FloatDataType ("DECIMAL(4)", false)));
    private static final RecordClassDescriptor rcdAllFloatPublicNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPublic.class).getName (), null, false, null, new StaticDataField ("float1", null, new FloatDataType ("IEEE32", true), "23456.34"), new StaticDataField ("double2", null, new FloatDataType ("IEEE64", true), "7.64565777632E7"), new StaticDataField ("double3", null, new FloatDataType ("DECIMAL", true), "7.64565777633E7"), new StaticDataField ("double4", null, new FloatDataType ("DECIMAL(2)", true), "7.645657737E7"), new StaticDataField ("double5", null, new FloatDataType ("DECIMAL(4)", true), "7.6456577769E7"));
    private static final RecordClassDescriptor rcdAllFloatPublicNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPublic.class).getName (), null, false, null, new StaticDataField ("float1", null, new FloatDataType ("IEEE32", false), "23456.34"), new StaticDataField ("double2", null, new FloatDataType ("IEEE64", false), "7.64565777632E7"), new StaticDataField ("double3", null, new FloatDataType ("DECIMAL", false), "7.64565777633E7"), new StaticDataField ("double4", null, new FloatDataType ("DECIMAL(2)", false), "7.645657737E7"), new StaticDataField ("double5", null, new FloatDataType ("DECIMAL(4)", false), "7.6456577769E7"));
    private static final RecordClassDescriptor rcdAllFloatPublicNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPublic.class).getName (), null, false, null, new StaticDataField ("float1", null, new FloatDataType ("IEEE32", true), "NaN"), new StaticDataField ("double2", null, new FloatDataType ("IEEE64", true), "NaN"), new StaticDataField ("double3", null, new FloatDataType ("DECIMAL", true), "NaN"), new StaticDataField ("double4", null, new FloatDataType ("DECIMAL(2)", true), "NaN"), new StaticDataField ("double5", null, new FloatDataType ("DECIMAL(4)", true), "NaN"));
    public static final class AllFloatPrivate extends InstrumentMessage {
        public float float1;
        public double double2;
        public double double3;
        public double double4;
        public double double5;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return (((((((((this).float1) + (",")) + ((this).double2)) + (",")) + ((this).double3)) + (",")) + ((this).double4)) + (",")) + ((this).double5);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPrivate))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPrivate other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPrivate) (o);
            return (((((java.lang.Float.compare ((this).float1, (other).float1)) == (0)) && ((java.lang.Double.compare ((this).double2, (other).double2)) == (0))) && ((java.lang.Double.compare ((this).double3, (other).double3)) == (0))) && ((java.lang.Double.compare ((this).double4, (other).double4)) == (0))) && ((java.lang.Double.compare ((this).double5, (other).double5)) == (0));
        }}
    private static final RecordClassDescriptor rcdAllFloatPrivateNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPrivate.class).getName (), null, false, null, new NonStaticDataField ("float1", null, new FloatDataType ("IEEE32", true)), new NonStaticDataField ("double2", null, new FloatDataType ("IEEE64", true)), new NonStaticDataField ("double3", null, new FloatDataType ("DECIMAL", true)), new NonStaticDataField ("double4", null, new FloatDataType ("DECIMAL(2)", true)), new NonStaticDataField ("double5", null, new FloatDataType ("DECIMAL(4)", true)));
    private static final RecordClassDescriptor rcdAllFloatPrivateNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPrivate.class).getName (), null, false, null, new NonStaticDataField ("float1", null, new FloatDataType ("IEEE32", false)), new NonStaticDataField ("double2", null, new FloatDataType ("IEEE64", false)), new NonStaticDataField ("double3", null, new FloatDataType ("DECIMAL", false)), new NonStaticDataField ("double4", null, new FloatDataType ("DECIMAL(2)", false)), new NonStaticDataField ("double5", null, new FloatDataType ("DECIMAL(4)", false)));
    private static final RecordClassDescriptor rcdAllFloatPrivateNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPrivate.class).getName (), null, false, null, new StaticDataField ("float1", null, new FloatDataType ("IEEE32", true), "23456.34"), new StaticDataField ("double2", null, new FloatDataType ("IEEE64", true), "7.64565777632E7"), new StaticDataField ("double3", null, new FloatDataType ("DECIMAL", true), "7.64565777633E7"), new StaticDataField ("double4", null, new FloatDataType ("DECIMAL(2)", true), "7.645657737E7"), new StaticDataField ("double5", null, new FloatDataType ("DECIMAL(4)", true), "7.6456577769E7"));
    private static final RecordClassDescriptor rcdAllFloatPrivateNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPrivate.class).getName (), null, false, null, new StaticDataField ("float1", null, new FloatDataType ("IEEE32", false), "23456.34"), new StaticDataField ("double2", null, new FloatDataType ("IEEE64", false), "7.64565777632E7"), new StaticDataField ("double3", null, new FloatDataType ("DECIMAL", false), "7.64565777633E7"), new StaticDataField ("double4", null, new FloatDataType ("DECIMAL(2)", false), "7.645657737E7"), new StaticDataField ("double5", null, new FloatDataType ("DECIMAL(4)", false), "7.6456577769E7"));
    private static final RecordClassDescriptor rcdAllFloatPrivateNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllFloatPrivate.class).getName (), null, false, null, new StaticDataField ("float1", null, new FloatDataType ("IEEE32", true), "NaN"), new StaticDataField ("double2", null, new FloatDataType ("IEEE64", true), "NaN"), new StaticDataField ("double3", null, new FloatDataType ("DECIMAL", true), "NaN"), new StaticDataField ("double4", null, new FloatDataType ("DECIMAL(2)", true), "NaN"), new StaticDataField ("double5", null, new FloatDataType ("DECIMAL(4)", true), "NaN"));
    @org.junit.Test
    public void testFloatDataTypeComp ()
    {
        super.setUpComp ();
        this.testFloatDataType ();
    }
    @org.junit.Test
    public void testFloatDataTypeIntp ()
    {
        super.setUpIntp ();
        this.testFloatDataType ();
    }
    private void testVarcharDataType ()
    {
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPublic ();
            (msg).String1 = "Hi Kolia !!!";
            (msg).CharSequence2 = "How are you Gene ???";
            (msg).String3 = "VERY VERY, LONG STRING !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_ ABCDEFGHIJKLM";
            (msg).CharSequence4 = "ANOTHER    LONG STRING !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_ ABCDEFGHIJKLM";
            (msg).long5 = -5709125537083949056L;
            (msg).long6 = -6313327282072505067L;
            super.testRcdBound ("public nullable - normal values", msg, rcdAllVarcharPublicNullable);
            super.testRcdBound ("public non-nullable - normal values", msg, rcdAllVarcharPublicNotNullable);
            super.testRcdBound ("public nullable static - normal values", msg, rcdAllVarcharPublicNullableStatic);
            super.testRcdBound ("public non-nullable static - normal values", msg, rcdAllVarcharPublicNotNullableStatic);
            (msg).String1 = null;
            (msg).CharSequence2 = null;
            (msg).String3 = null;
            (msg).CharSequence4 = null;
            (msg).long5 = deltix.qsrv.hf.pub.ExchangeCodec.NULL;
            (msg).long6 = deltix.qsrv.hf.pub.ExchangeCodec.NULL;
            super.testRcdBound ("public nullable - null values", msg, rcdAllVarcharPublicNullable);
            super.testRcdBound ("public nullable static - null values", msg, rcdAllVarcharPublicNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPublic ();
            (msg).String1 = null;
            (msg).CharSequence2 = null;
            (msg).String3 = null;
            (msg).CharSequence4 = null;
            (msg).long5 = deltix.qsrv.hf.pub.ExchangeCodec.NULL;
            (msg).long6 = deltix.qsrv.hf.pub.ExchangeCodec.NULL;
            try {
                super.boundEncode (msg, rcdAllVarcharPublicNotNullable);
                org.junit.Assert.fail ("UTF8 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("UTF8 - null violation - public", "java.lang.IllegalArgumentException: \'String1\' field is not nullable", (e).toString ());
            }
            (msg).String1 = "Hi Kolia !!!";
            try {
                super.boundEncode (msg, rcdAllVarcharPublicNotNullable);
                org.junit.Assert.fail ("UTF8 - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("UTF8 - null violation - public", "java.lang.IllegalArgumentException: \'CharSequence2\' field is not nullable", (e).toString ());
            }
            (msg).CharSequence2 = "How are you Gene ???";
            try {
                super.boundEncode (msg, rcdAllVarcharPublicNotNullable);
                org.junit.Assert.fail ("ALPHANUMERIC(100) - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("ALPHANUMERIC(100) - null violation - public", "java.lang.IllegalArgumentException: \'String3\' field is not nullable", (e).toString ());
            }
            (msg).String3 = "VERY VERY, LONG STRING !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_ ABCDEFGHIJKLM";
            try {
                super.boundEncode (msg, rcdAllVarcharPublicNotNullable);
                org.junit.Assert.fail ("ALPHANUMERIC(100) - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("ALPHANUMERIC(100) - null violation - public", "java.lang.IllegalArgumentException: \'CharSequence4\' field is not nullable", (e).toString ());
            }
            (msg).CharSequence4 = "ANOTHER    LONG STRING !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_ ABCDEFGHIJKLM";
            try {
                super.boundEncode (msg, rcdAllVarcharPublicNotNullable);
                org.junit.Assert.fail ("ALPHANUMERIC(5) - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("ALPHANUMERIC(5) - null violation - public", "java.lang.IllegalArgumentException: \'long5\' field is not nullable", (e).toString ());
            }
            (msg).long5 = -5709125537083949056L;
            try {
                super.boundEncode (msg, rcdAllVarcharPublicNotNullable);
                org.junit.Assert.fail ("ALPHANUMERIC(10) - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("ALPHANUMERIC(10) - null violation - public", "java.lang.IllegalArgumentException: \'long6\' field is not nullable", (e).toString ());
            }
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPrivate ();
            (msg).String1 = "Hi Kolia !!!";
            (msg).CharSequence2 = "How are you Gene ???";
            (msg).String3 = "VERY VERY, LONG STRING !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_ ABCDEFGHIJKLM";
            (msg).CharSequence4 = "ANOTHER    LONG STRING !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_ ABCDEFGHIJKLM";
            (msg).long5 = -5709125537083949056L;
            (msg).long6 = -6313327282072505067L;
            super.testRcdBound ("private nullable - normal values", msg, rcdAllVarcharPrivateNullable);
            super.testRcdBound ("private non-nullable - normal values", msg, rcdAllVarcharPrivateNotNullable);
            super.testRcdBound ("private nullable static - normal values", msg, rcdAllVarcharPrivateNullableStatic);
            super.testRcdBound ("private non-nullable static - normal values", msg, rcdAllVarcharPrivateNotNullableStatic);
            (msg).String1 = null;
            (msg).CharSequence2 = null;
            (msg).String3 = null;
            (msg).CharSequence4 = null;
            (msg).long5 = deltix.qsrv.hf.pub.ExchangeCodec.NULL;
            (msg).long6 = deltix.qsrv.hf.pub.ExchangeCodec.NULL;
            super.testRcdBound ("private nullable - null values", msg, rcdAllVarcharPrivateNullable);
            super.testRcdBound ("private nullable static - null values", msg, rcdAllVarcharPrivateNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPrivate ();
            (msg).String1 = null;
            (msg).CharSequence2 = null;
            (msg).String3 = null;
            (msg).CharSequence4 = null;
            (msg).long5 = deltix.qsrv.hf.pub.ExchangeCodec.NULL;
            (msg).long6 = deltix.qsrv.hf.pub.ExchangeCodec.NULL;
            try {
                super.boundEncode (msg, rcdAllVarcharPrivateNotNullable);
                org.junit.Assert.fail ("UTF8 - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("UTF8 - null violation - private", "java.lang.IllegalArgumentException: \'String1\' field is not nullable", (e).toString ());
            }
            (msg).String1 = "Hi Kolia !!!";
            try {
                super.boundEncode (msg, rcdAllVarcharPrivateNotNullable);
                org.junit.Assert.fail ("UTF8 - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("UTF8 - null violation - private", "java.lang.IllegalArgumentException: \'CharSequence2\' field is not nullable", (e).toString ());
            }
            (msg).CharSequence2 = "How are you Gene ???";
            try {
                super.boundEncode (msg, rcdAllVarcharPrivateNotNullable);
                org.junit.Assert.fail ("ALPHANUMERIC(100) - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("ALPHANUMERIC(100) - null violation - private", "java.lang.IllegalArgumentException: \'String3\' field is not nullable", (e).toString ());
            }
            (msg).String3 = "VERY VERY, LONG STRING !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_ ABCDEFGHIJKLM";
            try {
                super.boundEncode (msg, rcdAllVarcharPrivateNotNullable);
                org.junit.Assert.fail ("ALPHANUMERIC(100) - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("ALPHANUMERIC(100) - null violation - private", "java.lang.IllegalArgumentException: \'CharSequence4\' field is not nullable", (e).toString ());
            }
            (msg).CharSequence4 = "ANOTHER    LONG STRING !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_ ABCDEFGHIJKLM";
            try {
                super.boundEncode (msg, rcdAllVarcharPrivateNotNullable);
                org.junit.Assert.fail ("ALPHANUMERIC(5) - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("ALPHANUMERIC(5) - null violation - private", "java.lang.IllegalArgumentException: \'long5\' field is not nullable", (e).toString ());
            }
            (msg).long5 = -5709125537083949056L;
            try {
                super.boundEncode (msg, rcdAllVarcharPrivateNotNullable);
                org.junit.Assert.fail ("ALPHANUMERIC(10) - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("ALPHANUMERIC(10) - null violation - private", "java.lang.IllegalArgumentException: \'long6\' field is not nullable", (e).toString ());
            }
        }
    }
    public static final class AllVarcharPublic extends InstrumentMessage {
        public java.lang.String String1;
        public java.lang.CharSequence CharSequence2;
        public java.lang.String String3;
        public java.lang.CharSequence CharSequence4;
        public long long5;
        public long long6;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return (((((((((((this).String1) + (",")) + ((this).CharSequence2)) + (",")) + ((this).String3)) + (",")) + ((this).CharSequence4)) + (",")) + ((this).long5)) + (",")) + ((this).long6);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPublic))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPublic other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPublic) (o);
            return (((((deltix.util.lang.Util.equals ((this).String1, (other).String1)) && (deltix.util.lang.Util.equals ((this).CharSequence2, (other).CharSequence2))) && (deltix.util.lang.Util.equals ((this).String3, (other).String3))) && (deltix.util.lang.Util.equals ((this).CharSequence4, (other).CharSequence4))) && (((this).long5) == ((other).long5))) && (((this).long6) == ((other).long6));
        }}
    private static final RecordClassDescriptor rcdAllVarcharPublicNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPublic.class).getName (), null, false, null, new NonStaticDataField ("String1", null, new VarcharDataType ("UTF8", true, false)), new NonStaticDataField ("CharSequence2", null, new VarcharDataType ("UTF8", true, false)), new NonStaticDataField ("String3", null, new VarcharDataType ("ALPHANUMERIC(100)", true, false)), new NonStaticDataField ("CharSequence4", null, new VarcharDataType ("ALPHANUMERIC(100)", true, false)), new NonStaticDataField ("long5", null, new VarcharDataType ("ALPHANUMERIC(5)", true, false)), new NonStaticDataField ("long6", null, new VarcharDataType ("ALPHANUMERIC(10)", true, false)));
    private static final RecordClassDescriptor rcdAllVarcharPublicNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPublic.class).getName (), null, false, null, new NonStaticDataField ("String1", null, new VarcharDataType ("UTF8", false, false)), new NonStaticDataField ("CharSequence2", null, new VarcharDataType ("UTF8", false, false)), new NonStaticDataField ("String3", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)), new NonStaticDataField ("CharSequence4", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)), new NonStaticDataField ("long5", null, new VarcharDataType ("ALPHANUMERIC(5)", false, false)), new NonStaticDataField ("long6", null, new VarcharDataType ("ALPHANUMERIC(10)", false, false)));
    private static final RecordClassDescriptor rcdAllVarcharPublicNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPublic.class).getName (), null, false, null, new StaticDataField ("String1", null, new VarcharDataType ("UTF8", true, false), "Hi Kolia !!!"), new StaticDataField ("CharSequence2", null, new VarcharDataType ("UTF8", true, false), "How are you Gene ???"), new StaticDataField ("String3", null, new VarcharDataType ("ALPHANUMERIC(100)", true, false), "VERY VERY, LONG STRING !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_ ABCDEFGHIJKLM"), new StaticDataField ("CharSequence4", null, new VarcharDataType ("ALPHANUMERIC(100)", true, false), "ANOTHER    LONG STRING !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_ ABCDEFGHIJKLM"), new StaticDataField ("long5", null, new VarcharDataType ("ALPHANUMERIC(5)", true, false), "ABCDE"), new StaticDataField ("long6", null, new VarcharDataType ("ALPHANUMERIC(10)", true, false), "ABCDE12345"));
    private static final RecordClassDescriptor rcdAllVarcharPublicNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPublic.class).getName (), null, false, null, new StaticDataField ("String1", null, new VarcharDataType ("UTF8", false, false), "Hi Kolia !!!"), new StaticDataField ("CharSequence2", null, new VarcharDataType ("UTF8", false, false), "How are you Gene ???"), new StaticDataField ("String3", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false), "VERY VERY, LONG STRING !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_ ABCDEFGHIJKLM"), new StaticDataField ("CharSequence4", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false), "ANOTHER    LONG STRING !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_ ABCDEFGHIJKLM"), new StaticDataField ("long5", null, new VarcharDataType ("ALPHANUMERIC(5)", false, false), "ABCDE"), new StaticDataField ("long6", null, new VarcharDataType ("ALPHANUMERIC(10)", false, false), "ABCDE12345"));
    private static final RecordClassDescriptor rcdAllVarcharPublicNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPublic.class).getName (), null, false, null, new StaticDataField ("String1", null, new VarcharDataType ("UTF8", true, false), null), new StaticDataField ("CharSequence2", null, new VarcharDataType ("UTF8", true, false), null), new StaticDataField ("String3", null, new VarcharDataType ("ALPHANUMERIC(100)", true, false), null), new StaticDataField ("CharSequence4", null, new VarcharDataType ("ALPHANUMERIC(100)", true, false), null), new StaticDataField ("long5", null, new VarcharDataType ("ALPHANUMERIC(5)", true, false), null), new StaticDataField ("long6", null, new VarcharDataType ("ALPHANUMERIC(10)", true, false), null));
    public static final class AllVarcharPrivate extends InstrumentMessage {
        public java.lang.String String1;
        public java.lang.CharSequence CharSequence2;
        public java.lang.String String3;
        public java.lang.CharSequence CharSequence4;
        public long long5;
        public long long6;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return (((((((((((this).String1) + (",")) + ((this).CharSequence2)) + (",")) + ((this).String3)) + (",")) + ((this).CharSequence4)) + (",")) + ((this).long5)) + (",")) + ((this).long6);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPrivate))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPrivate other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPrivate) (o);
            return (((((deltix.util.lang.Util.equals ((this).String1, (other).String1)) && (deltix.util.lang.Util.equals ((this).CharSequence2, (other).CharSequence2))) && (deltix.util.lang.Util.equals ((this).String3, (other).String3))) && (deltix.util.lang.Util.equals ((this).CharSequence4, (other).CharSequence4))) && (((this).long5) == ((other).long5))) && (((this).long6) == ((other).long6));
        }}
    private static final RecordClassDescriptor rcdAllVarcharPrivateNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPrivate.class).getName (), null, false, null, new NonStaticDataField ("String1", null, new VarcharDataType ("UTF8", true, false)), new NonStaticDataField ("CharSequence2", null, new VarcharDataType ("UTF8", true, false)), new NonStaticDataField ("String3", null, new VarcharDataType ("ALPHANUMERIC(100)", true, false)), new NonStaticDataField ("CharSequence4", null, new VarcharDataType ("ALPHANUMERIC(100)", true, false)), new NonStaticDataField ("long5", null, new VarcharDataType ("ALPHANUMERIC(5)", true, false)), new NonStaticDataField ("long6", null, new VarcharDataType ("ALPHANUMERIC(10)", true, false)));
    private static final RecordClassDescriptor rcdAllVarcharPrivateNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPrivate.class).getName (), null, false, null, new NonStaticDataField ("String1", null, new VarcharDataType ("UTF8", false, false)), new NonStaticDataField ("CharSequence2", null, new VarcharDataType ("UTF8", false, false)), new NonStaticDataField ("String3", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)), new NonStaticDataField ("CharSequence4", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)), new NonStaticDataField ("long5", null, new VarcharDataType ("ALPHANUMERIC(5)", false, false)), new NonStaticDataField ("long6", null, new VarcharDataType ("ALPHANUMERIC(10)", false, false)));
    private static final RecordClassDescriptor rcdAllVarcharPrivateNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPrivate.class).getName (), null, false, null, new StaticDataField ("String1", null, new VarcharDataType ("UTF8", true, false), "Hi Kolia !!!"), new StaticDataField ("CharSequence2", null, new VarcharDataType ("UTF8", true, false), "How are you Gene ???"), new StaticDataField ("String3", null, new VarcharDataType ("ALPHANUMERIC(100)", true, false), "VERY VERY, LONG STRING !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_ ABCDEFGHIJKLM"), new StaticDataField ("CharSequence4", null, new VarcharDataType ("ALPHANUMERIC(100)", true, false), "ANOTHER    LONG STRING !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_ ABCDEFGHIJKLM"), new StaticDataField ("long5", null, new VarcharDataType ("ALPHANUMERIC(5)", true, false), "ABCDE"), new StaticDataField ("long6", null, new VarcharDataType ("ALPHANUMERIC(10)", true, false), "ABCDE12345"));
    private static final RecordClassDescriptor rcdAllVarcharPrivateNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPrivate.class).getName (), null, false, null, new StaticDataField ("String1", null, new VarcharDataType ("UTF8", false, false), "Hi Kolia !!!"), new StaticDataField ("CharSequence2", null, new VarcharDataType ("UTF8", false, false), "How are you Gene ???"), new StaticDataField ("String3", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false), "VERY VERY, LONG STRING !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_ ABCDEFGHIJKLM"), new StaticDataField ("CharSequence4", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false), "ANOTHER    LONG STRING !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_ ABCDEFGHIJKLM"), new StaticDataField ("long5", null, new VarcharDataType ("ALPHANUMERIC(5)", false, false), "ABCDE"), new StaticDataField ("long6", null, new VarcharDataType ("ALPHANUMERIC(10)", false, false), "ABCDE12345"));
    private static final RecordClassDescriptor rcdAllVarcharPrivateNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllVarcharPrivate.class).getName (), null, false, null, new StaticDataField ("String1", null, new VarcharDataType ("UTF8", true, false), null), new StaticDataField ("CharSequence2", null, new VarcharDataType ("UTF8", true, false), null), new StaticDataField ("String3", null, new VarcharDataType ("ALPHANUMERIC(100)", true, false), null), new StaticDataField ("CharSequence4", null, new VarcharDataType ("ALPHANUMERIC(100)", true, false), null), new StaticDataField ("long5", null, new VarcharDataType ("ALPHANUMERIC(5)", true, false), null), new StaticDataField ("long6", null, new VarcharDataType ("ALPHANUMERIC(10)", true, false), null));
    @org.junit.Test
    public void testVarcharDataTypeComp ()
    {
        super.setUpComp ();
        this.testVarcharDataType ();
    }
    @org.junit.Test
    public void testVarcharDataTypeIntp ()
    {
        super.setUpIntp ();
        this.testVarcharDataType ();
    }
    private static final EnumClassDescriptor INSTRUMENT_TYPE = new EnumClassDescriptor (deltix.timebase.api.messages.InstrumentType.class.getName (), null, false, "EQUITY", "OPTION", "FUTURE", "BOND", "FX", "INDEX", "ETF", "CUSTOM", "SIMPLE_OPTION", "EXCHANGE", "TRADING_SESSION", "STREAM", "DATA_CONNECTOR", "SYSTEM");
    private void testEnumDataType ()
    {
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPublic ();
            (msg).InstrumentType1 = InstrumentType.OPTION;
            (msg).CharSequence2 = "FUTURE";
            (msg).byte3 = (byte) (3L);
            (msg).short4 = (short) (2L);
            (msg).int5 = (int) (1L);
            (msg).long6 = (long) (4L);
            super.testRcdBound ("public nullable - normal values", msg, rcdAllEnumPublicNullable);
            super.testRcdBound ("public non-nullable - normal values", msg, rcdAllEnumPublicNotNullable);
            super.testRcdBound ("public nullable static - normal values", msg, rcdAllEnumPublicNullableStatic);
            super.testRcdBound ("public non-nullable static - normal values", msg, rcdAllEnumPublicNotNullableStatic);
            (msg).InstrumentType1 = null;
            (msg).CharSequence2 = null;
            (msg).byte3 = (byte) (-1L);
            (msg).short4 = (short) (-1L);
            (msg).int5 = (int) (-1L);
            (msg).long6 = (long) (-1L);
            super.testRcdBound ("public nullable - null values", msg, rcdAllEnumPublicNullable);
            super.testRcdBound ("public nullable static - null values", msg, rcdAllEnumPublicNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPublic ();
            (msg).InstrumentType1 = null;
            (msg).CharSequence2 = null;
            (msg).byte3 = (byte) (-1L);
            (msg).short4 = (short) (-1L);
            (msg).int5 = (int) (-1L);
            (msg).long6 = (long) (-1L);
            try {
                super.boundEncode (msg, rcdAllEnumPublicNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - null violation - public", "java.lang.IllegalArgumentException: \'InstrumentType1\' field is not nullable", (e).toString ());
            }
            (msg).InstrumentType1 = InstrumentType.OPTION;
            try {
                super.boundEncode (msg, rcdAllEnumPublicNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - null violation - public", "java.lang.IllegalArgumentException: \'CharSequence2\' field is not nullable", (e).toString ());
            }
            (msg).CharSequence2 = "FUTURE";
            try {
                super.boundEncode (msg, rcdAllEnumPublicNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - null violation - public", "java.lang.IllegalArgumentException: \'byte3\' field is not nullable", (e).toString ());
            }
            (msg).byte3 = (byte) (3L);
            try {
                super.boundEncode (msg, rcdAllEnumPublicNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - null violation - public", "java.lang.IllegalArgumentException: \'short4\' field is not nullable", (e).toString ());
            }
            (msg).short4 = (short) (2L);
            try {
                super.boundEncode (msg, rcdAllEnumPublicNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - null violation - public", "java.lang.IllegalArgumentException: \'int5\' field is not nullable", (e).toString ());
            }
            (msg).int5 = (int) (1L);
            try {
                super.boundEncode (msg, rcdAllEnumPublicNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - null violation - public", "java.lang.IllegalArgumentException: \'long6\' field is not nullable", (e).toString ());
            }
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPublic ();
            (msg).InstrumentType1 = null;
            (msg).CharSequence2 = "BAD_VALUE";
            (msg).byte3 = 20;
            (msg).short4 = 21;
            (msg).int5 = 22;
            (msg).long6 = 8197L;
            (msg).InstrumentType1 = InstrumentType.OPTION;
            try {
                super.boundEncode (msg, rcdAllEnumPublicNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - contraint violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                if (!(("java.lang.IllegalArgumentException: CharSequence2 == BAD_VALUE").equals ((e).toString ())))
                    org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - contraint violation - public", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == BAD_VALUE", (this).getFieldString (msg, "CharSequence2"))).toString (), (e).toString ());
            }
            (msg).CharSequence2 = "FUTURE";
            try {
                super.boundEncode (msg, rcdAllEnumPublicNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - contraint violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                if (!(("java.lang.IllegalArgumentException: byte3 == 20").equals ((e).toString ())))
                    org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - contraint violation - public", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 20", (this).getFieldString (msg, "byte3"))).toString (), (e).toString ());
            }
            (msg).byte3 = (byte) (3L);
            try {
                super.boundEncode (msg, rcdAllEnumPublicNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - contraint violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                if (!(("java.lang.IllegalArgumentException: short4 == 21").equals ((e).toString ())))
                    org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - contraint violation - public", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 21", (this).getFieldString (msg, "short4"))).toString (), (e).toString ());
            }
            (msg).short4 = (short) (2L);
            try {
                super.boundEncode (msg, rcdAllEnumPublicNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - contraint violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                if (!(("java.lang.IllegalArgumentException: int5 == 22").equals ((e).toString ())))
                    org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - contraint violation - public", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 22", (this).getFieldString (msg, "int5"))).toString (), (e).toString ());
            }
            (msg).int5 = (int) (1L);
            try {
                super.boundEncode (msg, rcdAllEnumPublicNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - contraint violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                if (!(("java.lang.IllegalArgumentException: long6 == 8197").equals ((e).toString ())))
                    org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - contraint violation - public", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 8197", (this).getFieldString (msg, "long6"))).toString (), (e).toString ());
            }
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPrivate ();
            (msg).InstrumentType1 = InstrumentType.OPTION;
            (msg).CharSequence2 = "FUTURE";
            (msg).byte3 = (byte) (3L);
            (msg).short4 = (short) (2L);
            (msg).int5 = (int) (1L);
            (msg).long6 = (long) (4L);
            super.testRcdBound ("private nullable - normal values", msg, rcdAllEnumPrivateNullable);
            super.testRcdBound ("private non-nullable - normal values", msg, rcdAllEnumPrivateNotNullable);
            super.testRcdBound ("private nullable static - normal values", msg, rcdAllEnumPrivateNullableStatic);
            super.testRcdBound ("private non-nullable static - normal values", msg, rcdAllEnumPrivateNotNullableStatic);
            (msg).InstrumentType1 = null;
            (msg).CharSequence2 = null;
            (msg).byte3 = (byte) (-1L);
            (msg).short4 = (short) (-1L);
            (msg).int5 = (int) (-1L);
            (msg).long6 = (long) (-1L);
            super.testRcdBound ("private nullable - null values", msg, rcdAllEnumPrivateNullable);
            super.testRcdBound ("private nullable static - null values", msg, rcdAllEnumPrivateNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPrivate ();
            (msg).InstrumentType1 = null;
            (msg).CharSequence2 = null;
            (msg).byte3 = (byte) (-1L);
            (msg).short4 = (short) (-1L);
            (msg).int5 = (int) (-1L);
            (msg).long6 = (long) (-1L);
            try {
                super.boundEncode (msg, rcdAllEnumPrivateNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - null violation - private", "java.lang.IllegalArgumentException: \'InstrumentType1\' field is not nullable", (e).toString ());
            }
            (msg).InstrumentType1 = InstrumentType.OPTION;
            try {
                super.boundEncode (msg, rcdAllEnumPrivateNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - null violation - private", "java.lang.IllegalArgumentException: \'CharSequence2\' field is not nullable", (e).toString ());
            }
            (msg).CharSequence2 = "FUTURE";
            try {
                super.boundEncode (msg, rcdAllEnumPrivateNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - null violation - private", "java.lang.IllegalArgumentException: \'byte3\' field is not nullable", (e).toString ());
            }
            (msg).byte3 = (byte) (3L);
            try {
                super.boundEncode (msg, rcdAllEnumPrivateNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - null violation - private", "java.lang.IllegalArgumentException: \'short4\' field is not nullable", (e).toString ());
            }
            (msg).short4 = (short) (2L);
            try {
                super.boundEncode (msg, rcdAllEnumPrivateNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - null violation - private", "java.lang.IllegalArgumentException: \'int5\' field is not nullable", (e).toString ());
            }
            (msg).int5 = (int) (1L);
            try {
                super.boundEncode (msg, rcdAllEnumPrivateNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - null violation - private", "java.lang.IllegalArgumentException: \'long6\' field is not nullable", (e).toString ());
            }
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPrivate ();
            (msg).InstrumentType1 = null;
            (msg).CharSequence2 = "BAD_VALUE";
            (msg).byte3 = 20;
            (msg).short4 = 21;
            (msg).int5 = 22;
            (msg).long6 = 8197L;
            (msg).InstrumentType1 = InstrumentType.OPTION;
            try {
                super.boundEncode (msg, rcdAllEnumPrivateNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - contraint violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                if (!(("java.lang.IllegalArgumentException: CharSequence2 == BAD_VALUE").equals ((e).toString ())))
                    org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - contraint violation - private", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == BAD_VALUE", (this).getFieldString (msg, "CharSequence2"))).toString (), (e).toString ());
            }
            (msg).CharSequence2 = "FUTURE";
            try {
                super.boundEncode (msg, rcdAllEnumPrivateNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - contraint violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                if (!(("java.lang.IllegalArgumentException: byte3 == 20").equals ((e).toString ())))
                    org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - contraint violation - private", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 20", (this).getFieldString (msg, "byte3"))).toString (), (e).toString ());
            }
            (msg).byte3 = (byte) (3L);
            try {
                super.boundEncode (msg, rcdAllEnumPrivateNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - contraint violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                if (!(("java.lang.IllegalArgumentException: short4 == 21").equals ((e).toString ())))
                    org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - contraint violation - private", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 21", (this).getFieldString (msg, "short4"))).toString (), (e).toString ());
            }
            (msg).short4 = (short) (2L);
            try {
                super.boundEncode (msg, rcdAllEnumPrivateNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - contraint violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                if (!(("java.lang.IllegalArgumentException: int5 == 22").equals ((e).toString ())))
                    org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - contraint violation - private", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 22", (this).getFieldString (msg, "int5"))).toString (), (e).toString ());
            }
            (msg).int5 = (int) (1L);
            try {
                super.boundEncode (msg, rcdAllEnumPrivateNotNullable);
                org.junit.Assert.fail ("deltix.timebase.api.messages.InstrumentType - contraint violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                if (!(("java.lang.IllegalArgumentException: long6 == 8197").equals ((e).toString ())))
                    org.junit.Assert.assertEquals ("deltix.timebase.api.messages.InstrumentType - contraint violation - private", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 8197", (this).getFieldString (msg, "long6"))).toString (), (e).toString ());
            }
        }
    }
    public static final class AllEnumPublic extends InstrumentMessage {
        public InstrumentType InstrumentType1;
        public java.lang.CharSequence CharSequence2;
        public byte byte3;
        public short short4;
        public int int5;
        public long long6;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return (((((((((((this).InstrumentType1) + (",")) + ((this).CharSequence2)) + (",")) + ((this).byte3)) + (",")) + ((this).short4)) + (",")) + ((this).int5)) + (",")) + ((this).long6);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPublic))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPublic other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPublic) (o);
            return (((((((this).InstrumentType1) == ((other).InstrumentType1)) && (deltix.util.lang.Util.equals ((this).CharSequence2, (other).CharSequence2))) && (((this).byte3) == ((other).byte3))) && (((this).short4) == ((other).short4))) && (((this).int5) == ((other).int5))) && (((this).long6) == ((other).long6));
        }}
    private static final RecordClassDescriptor rcdAllEnumPublicNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPublic.class).getName (), null, false, null, 
            new NonStaticDataField ("InstrumentType1", null, new EnumDataType (true, INSTRUMENT_TYPE)), 
            new NonStaticDataField ("CharSequence2", null, new EnumDataType (true, INSTRUMENT_TYPE)), 
            new NonStaticDataField ("byte3", null, new EnumDataType (true, INSTRUMENT_TYPE)), 
            new NonStaticDataField ("short4", null, new EnumDataType (true, INSTRUMENT_TYPE)), 
            new NonStaticDataField ("int5", null, new EnumDataType (true, INSTRUMENT_TYPE)), 
            new NonStaticDataField ("long6", null, new EnumDataType (true, INSTRUMENT_TYPE)));
    private static final RecordClassDescriptor rcdAllEnumPublicNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPublic.class).getName (), null, false, null,
            new NonStaticDataField ("InstrumentType1", null, new EnumDataType (false, INSTRUMENT_TYPE)), new NonStaticDataField ("CharSequence2", null, new EnumDataType (false, INSTRUMENT_TYPE)), new NonStaticDataField ("byte3", null, new EnumDataType (false, INSTRUMENT_TYPE)), new NonStaticDataField ("short4", null, new EnumDataType (false, INSTRUMENT_TYPE)), new NonStaticDataField ("int5", null, new EnumDataType (false, INSTRUMENT_TYPE)), new NonStaticDataField ("long6", null, new EnumDataType (false, INSTRUMENT_TYPE)));
    private static final RecordClassDescriptor rcdAllEnumPublicNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPublic.class).getName (), null, false, null, new StaticDataField ("InstrumentType1", null, new EnumDataType (true, INSTRUMENT_TYPE), "OPTION"), new StaticDataField ("CharSequence2", null, new EnumDataType (true, INSTRUMENT_TYPE), "FUTURE"), new StaticDataField ("byte3", null, new EnumDataType (true, INSTRUMENT_TYPE), "BOND"), new StaticDataField ("short4", null, new EnumDataType (true, INSTRUMENT_TYPE), "FUTURE"), new StaticDataField ("int5", null, new EnumDataType (true, INSTRUMENT_TYPE), "OPTION"), new StaticDataField ("long6", null, new EnumDataType (true, INSTRUMENT_TYPE), "FX"));
    private static final RecordClassDescriptor rcdAllEnumPublicNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPublic.class).getName (), null, false, null, new StaticDataField ("InstrumentType1", null, new EnumDataType (false, INSTRUMENT_TYPE), "OPTION"), new StaticDataField ("CharSequence2", null, new EnumDataType (false, INSTRUMENT_TYPE), "FUTURE"), new StaticDataField ("byte3", null, new EnumDataType (false, INSTRUMENT_TYPE), "BOND"), new StaticDataField ("short4", null, new EnumDataType (false, INSTRUMENT_TYPE), "FUTURE"), new StaticDataField ("int5", null, new EnumDataType (false, INSTRUMENT_TYPE), "OPTION"), new StaticDataField ("long6", null, new EnumDataType (false, INSTRUMENT_TYPE), "FX"));
    private static final RecordClassDescriptor rcdAllEnumPublicNullableStaticNV = 
            new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPublic.class).getName (), null, false, null, 
                    new StaticDataField ("InstrumentType1", null,new EnumDataType (true, INSTRUMENT_TYPE), null), 
                    new StaticDataField ("CharSequence2", null, new EnumDataType (true, INSTRUMENT_TYPE), null), 
                    new StaticDataField ("byte3", null, new EnumDataType (true, INSTRUMENT_TYPE), null), 
                    new StaticDataField ("short4", null, new EnumDataType (true, INSTRUMENT_TYPE), null), 
                    new StaticDataField ("int5", null, new EnumDataType (true, INSTRUMENT_TYPE), null), 
                    new StaticDataField ("long6", null, new EnumDataType (true, INSTRUMENT_TYPE), null));
    public static final class AllEnumPrivate extends InstrumentMessage {
        public InstrumentType InstrumentType1;
        public java.lang.CharSequence CharSequence2;
        public byte byte3;
        public short short4;
        public int int5;
        public long long6;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return (((((((((((this).InstrumentType1) + (",")) + ((this).CharSequence2)) + (",")) + ((this).byte3)) + (",")) + ((this).short4)) + (",")) + ((this).int5)) + (",")) + ((this).long6);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPrivate))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPrivate other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPrivate) (o);
            return (((((((this).InstrumentType1) == ((other).InstrumentType1)) && (deltix.util.lang.Util.equals ((this).CharSequence2, (other).CharSequence2))) && (((this).byte3) == ((other).byte3))) && (((this).short4) == ((other).short4))) && (((this).int5) == ((other).int5))) && (((this).long6) == ((other).long6));
        }}
    private static final RecordClassDescriptor rcdAllEnumPrivateNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPrivate.class).getName (), null, false, null, new NonStaticDataField ("InstrumentType1", null, new EnumDataType (true, INSTRUMENT_TYPE)), new NonStaticDataField ("CharSequence2", null, new EnumDataType (true, INSTRUMENT_TYPE)), new NonStaticDataField ("byte3", null, new EnumDataType (true, INSTRUMENT_TYPE)), new NonStaticDataField ("short4", null, new EnumDataType (true, INSTRUMENT_TYPE)), new NonStaticDataField ("int5", null, new EnumDataType (true, INSTRUMENT_TYPE)), new NonStaticDataField ("long6", null, new EnumDataType (true, INSTRUMENT_TYPE)));
    private static final RecordClassDescriptor rcdAllEnumPrivateNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPrivate.class).getName (), null, false, null, new NonStaticDataField ("InstrumentType1", null, new EnumDataType (false, INSTRUMENT_TYPE)), new NonStaticDataField ("CharSequence2", null, new EnumDataType (false, INSTRUMENT_TYPE)), new NonStaticDataField ("byte3", null, new EnumDataType (false, INSTRUMENT_TYPE)), new NonStaticDataField ("short4", null, new EnumDataType (false, INSTRUMENT_TYPE)), new NonStaticDataField ("int5", null, new EnumDataType (false, INSTRUMENT_TYPE)), new NonStaticDataField ("long6", null, new EnumDataType (false, INSTRUMENT_TYPE)));
    private static final RecordClassDescriptor rcdAllEnumPrivateNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPrivate.class).getName (), null, false, null, new StaticDataField ("InstrumentType1", null, new EnumDataType (true, INSTRUMENT_TYPE), "OPTION"), new StaticDataField ("CharSequence2", null, new EnumDataType (true, INSTRUMENT_TYPE), "FUTURE"), new StaticDataField ("byte3", null, new EnumDataType (true, INSTRUMENT_TYPE), "BOND"), new StaticDataField ("short4", null, new EnumDataType (true, INSTRUMENT_TYPE), "FUTURE"), new StaticDataField ("int5", null, new EnumDataType (true, INSTRUMENT_TYPE), "OPTION"), new StaticDataField ("long6", null, new EnumDataType (true, INSTRUMENT_TYPE), "FX"));
    private static final RecordClassDescriptor rcdAllEnumPrivateNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPrivate.class).getName (), null, false, null, new StaticDataField ("InstrumentType1", null, new EnumDataType (false, INSTRUMENT_TYPE), "OPTION"), new StaticDataField ("CharSequence2", null, new EnumDataType (false, INSTRUMENT_TYPE), "FUTURE"), new StaticDataField ("byte3", null, new EnumDataType (false, INSTRUMENT_TYPE), "BOND"), new StaticDataField ("short4", null, new EnumDataType (false, INSTRUMENT_TYPE), "FUTURE"), new StaticDataField ("int5", null, new EnumDataType (false, INSTRUMENT_TYPE), "OPTION"), new StaticDataField ("long6", null, new EnumDataType (false, INSTRUMENT_TYPE), "FX"));
    private static final RecordClassDescriptor rcdAllEnumPrivateNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllEnumPrivate.class).getName (), null, false, null, new StaticDataField ("InstrumentType1", null, new EnumDataType (true, INSTRUMENT_TYPE), null), new StaticDataField ("CharSequence2", null, new EnumDataType (true, INSTRUMENT_TYPE), null), new StaticDataField ("byte3", null, new EnumDataType (true, INSTRUMENT_TYPE), null), new StaticDataField ("short4", null, new EnumDataType (true, INSTRUMENT_TYPE), null), new StaticDataField ("int5", null, new EnumDataType (true, INSTRUMENT_TYPE), null), new StaticDataField ("long6", null, new EnumDataType (true, INSTRUMENT_TYPE), null));
    @org.junit.Test
    public void testEnumDataTypeComp ()
    {
        super.setUpComp ();
        this.testEnumDataType ();
    }
    @org.junit.Test
    public void testEnumDataTypeIntp ()
    {
        super.setUpIntp ();
        this.testEnumDataType ();
    }
    private static final EnumClassDescriptor JavaFieldsBitmask = new EnumClassDescriptor ("JavaFieldsBitmask", null, true, new EnumValue ("field1", 1L), new EnumValue ("field2", 2L), new EnumValue ("field3", 4L), new EnumValue ("field4", 8L), new EnumValue ("field5", 16L));
    public void testBitmaskDataType ()
    {
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPublic ();
            (msg).int1 = (int) (1L);
            (msg).long2 = (long) (2L);
            super.testRcdBound ("public nullable - normal values", msg, rcdAllBitmaskPublicNullable);
            super.testRcdBound ("public non-nullable - normal values", msg, rcdAllBitmaskPublicNotNullable);
            super.testRcdBound ("public nullable static - normal values", msg, rcdAllBitmaskPublicNullableStatic);
            super.testRcdBound ("public non-nullable static - normal values", msg, rcdAllBitmaskPublicNotNullableStatic);
            (msg).int1 = (int) (1L);
            (msg).long2 = (long) (2L);
            super.testRcdBound ("public nullable - null values", msg, rcdAllBitmaskPublicNullable);
            super.testRcdBound ("public nullable static - null values", msg, rcdAllBitmaskPublicNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPublic ();
            (msg).int1 = (int) (1L);
            (msg).long2 = (long) (2L);
            (msg).int1 = (int) (1L);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPublic ();
            (msg).int1 = 33;
            (msg).long2 = 8197L;
            try {
                super.boundEncode (msg, rcdAllBitmaskPublicNotNullable);
                org.junit.Assert.fail ("JavaFieldsBitmask - contraint violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                if (!(("java.lang.IllegalArgumentException: int1 == 33").equals ((e).toString ())))
                    org.junit.Assert.assertEquals ("JavaFieldsBitmask - contraint violation - public", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 33", (this).getFieldString (msg, "int1"))).toString (), (e).toString ());
            }
            (msg).int1 = (int) (1L);
            try {
                super.boundEncode (msg, rcdAllBitmaskPublicNotNullable);
                org.junit.Assert.fail ("JavaFieldsBitmask - contraint violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                if (!(("java.lang.IllegalArgumentException: long2 == 8197").equals ((e).toString ())))
                    org.junit.Assert.assertEquals ("JavaFieldsBitmask - contraint violation - public", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 8197", (this).getFieldString (msg, "long2"))).toString (), (e).toString ());
            }
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPrivate ();
            (msg).int1 = (int) (1L);
            (msg).long2 = (long) (2L);
            super.testRcdBound ("private nullable - normal values", msg, rcdAllBitmaskPrivateNullable);
            super.testRcdBound ("private non-nullable - normal values", msg, rcdAllBitmaskPrivateNotNullable);
            super.testRcdBound ("private nullable static - normal values", msg, rcdAllBitmaskPrivateNullableStatic);
            super.testRcdBound ("private non-nullable static - normal values", msg, rcdAllBitmaskPrivateNotNullableStatic);
            (msg).int1 = (int) (1L);
            (msg).long2 = (long) (2L);
            super.testRcdBound ("private nullable - null values", msg, rcdAllBitmaskPrivateNullable);
            super.testRcdBound ("private nullable static - null values", msg, rcdAllBitmaskPrivateNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPrivate ();
            (msg).int1 = (int) (1L);
            (msg).long2 = (long) (2L);
            (msg).int1 = (int) (1L);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPrivate ();
            (msg).int1 = 33;
            (msg).long2 = 8197L;
            try {
                super.boundEncode (msg, rcdAllBitmaskPrivateNotNullable);
                org.junit.Assert.fail ("JavaFieldsBitmask - contraint violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                if (!(("java.lang.IllegalArgumentException: int1 == 33").equals ((e).toString ())))
                    org.junit.Assert.assertEquals ("JavaFieldsBitmask - contraint violation - private", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 33", (this).getFieldString (msg, "int1"))).toString (), (e).toString ());
            }
            (msg).int1 = (int) (1L);
            try {
                super.boundEncode (msg, rcdAllBitmaskPrivateNotNullable);
                org.junit.Assert.fail ("JavaFieldsBitmask - contraint violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                if (!(("java.lang.IllegalArgumentException: long2 == 8197").equals ((e).toString ())))
                    org.junit.Assert.assertEquals ("JavaFieldsBitmask - contraint violation - private", (java.lang.String.format ("java.lang.IllegalArgumentException: %s == 8197", (this).getFieldString (msg, "long2"))).toString (), (e).toString ());
            }
        }
    }
    public static final class AllBitmaskPublic extends InstrumentMessage {
        public int int1;
        public long long2;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return (((this).int1) + (",")) + ((this).long2);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPublic))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPublic other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPublic) (o);
            return (((this).int1) == ((other).int1)) && (((this).long2) == ((other).long2));
        }}
    private static final RecordClassDescriptor rcdAllBitmaskPublicNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPublic.class).getName (), null, false, null, new NonStaticDataField ("int1", null, new EnumDataType (true, JavaFieldsBitmask)), new NonStaticDataField ("long2", null, new EnumDataType (true, JavaFieldsBitmask)));
    private static final RecordClassDescriptor rcdAllBitmaskPublicNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPublic.class).getName (), null, false, null, new NonStaticDataField ("int1", null, new EnumDataType (false, JavaFieldsBitmask)), new NonStaticDataField ("long2", null, new EnumDataType (false, JavaFieldsBitmask)));
    private static final RecordClassDescriptor rcdAllBitmaskPublicNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPublic.class).getName (), null, false, null, new StaticDataField ("int1", null, new EnumDataType (true, JavaFieldsBitmask), "field1"), new StaticDataField ("long2", null, new EnumDataType (true, JavaFieldsBitmask), "field2"));
    private static final RecordClassDescriptor rcdAllBitmaskPublicNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPublic.class).getName (), null, false, null, new StaticDataField ("int1", null, new EnumDataType (false, JavaFieldsBitmask), "field1"), new StaticDataField ("long2", null, new EnumDataType (false, JavaFieldsBitmask), "field2"));
    private static final RecordClassDescriptor rcdAllBitmaskPublicNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPublic.class).getName (), null, false, null, new StaticDataField ("int1", null, new EnumDataType (true, JavaFieldsBitmask), "field1"), new StaticDataField ("long2", null, new EnumDataType (true, JavaFieldsBitmask), "field2"));
    public static final class AllBitmaskPrivate extends InstrumentMessage {
        public int int1;
        public long long2;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return (((this).int1) + (",")) + ((this).long2);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPrivate))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPrivate other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPrivate) (o);
            return (((this).int1) == ((other).int1)) && (((this).long2) == ((other).long2));
        }}
    private static final RecordClassDescriptor rcdAllBitmaskPrivateNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPrivate.class).getName (), null, false, null, new NonStaticDataField ("int1", null, new EnumDataType (true, JavaFieldsBitmask)), new NonStaticDataField ("long2", null, new EnumDataType (true, JavaFieldsBitmask)));
    private static final RecordClassDescriptor rcdAllBitmaskPrivateNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPrivate.class).getName (), null, false, null, new NonStaticDataField ("int1", null, new EnumDataType (false, JavaFieldsBitmask)), new NonStaticDataField ("long2", null, new EnumDataType (false, JavaFieldsBitmask)));
    private static final RecordClassDescriptor rcdAllBitmaskPrivateNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPrivate.class).getName (), null, false, null, new StaticDataField ("int1", null, new EnumDataType (true, JavaFieldsBitmask), "field1"), new StaticDataField ("long2", null, new EnumDataType (true, JavaFieldsBitmask), "field2"));
    private static final RecordClassDescriptor rcdAllBitmaskPrivateNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPrivate.class).getName (), null, false, null, new StaticDataField ("int1", null, new EnumDataType (false, JavaFieldsBitmask), "field1"), new StaticDataField ("long2", null, new EnumDataType (false, JavaFieldsBitmask), "field2"));
    private static final RecordClassDescriptor rcdAllBitmaskPrivateNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllBitmaskPrivate.class).getName (), null, false, null, new StaticDataField ("int1", null, new EnumDataType (true, JavaFieldsBitmask), "field1"), new StaticDataField ("long2", null, new EnumDataType (true, JavaFieldsBitmask), "field2"));
    @org.junit.Test
    public void testBitmaskDataTypeComp ()
    {
        super.setUpComp ();
        this.testBitmaskDataType ();
    }
    @org.junit.Test
    public void testBitmaskDataTypeIntp ()
    {
        super.setUpIntp ();
        this.testBitmaskDataType ();
    }
    private void testDateTimeDataType ()
    {
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePublic ();
            (msg).long1 = 1314899629573L;
            super.testRcdBound ("public nullable - normal values", msg, rcdAllDateTimePublicNullable);
            super.testRcdBound ("public non-nullable - normal values", msg, rcdAllDateTimePublicNotNullable);
            super.testRcdBound ("public nullable static - normal values", msg, rcdAllDateTimePublicNullableStatic);
            super.testRcdBound ("public non-nullable static - normal values", msg, rcdAllDateTimePublicNotNullableStatic);
            (msg).long1 = 1314899629573L;
            super.testRcdBound ("public nullable - null values", msg, rcdAllDateTimePublicNullable);
            super.testRcdBound ("public nullable static - null values", msg, rcdAllDateTimePublicNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePublic ();
            (msg).long1 = 1314899629573L;
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePrivate ();
            (msg).long1 = 1314899629573L;
            super.testRcdBound ("private nullable - normal values", msg, rcdAllDateTimePrivateNullable);
            super.testRcdBound ("private non-nullable - normal values", msg, rcdAllDateTimePrivateNotNullable);
            super.testRcdBound ("private nullable static - normal values", msg, rcdAllDateTimePrivateNullableStatic);
            super.testRcdBound ("private non-nullable static - normal values", msg, rcdAllDateTimePrivateNotNullableStatic);
            (msg).long1 = 1314899629573L;
            super.testRcdBound ("private nullable - null values", msg, rcdAllDateTimePrivateNullable);
            super.testRcdBound ("private nullable static - null values", msg, rcdAllDateTimePrivateNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePrivate ();
            (msg).long1 = 1314899629573L;
        }
    }
    public static final class AllDateTimePublic extends InstrumentMessage {
        public long long1;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return java.lang.String.valueOf ((this).long1);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePublic))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePublic other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePublic) (o);
            return ((this).long1) == ((other).long1);
        }}
    private static final RecordClassDescriptor rcdAllDateTimePublicNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePublic.class).getName (), null, false, null, new NonStaticDataField ("long1", null, new DateTimeDataType (true)));
    private static final RecordClassDescriptor rcdAllDateTimePublicNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePublic.class).getName (), null, false, null, new NonStaticDataField ("long1", null, new DateTimeDataType (false)));
    private static final RecordClassDescriptor rcdAllDateTimePublicNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePublic.class).getName (), null, false, null, new StaticDataField ("long1", null, new DateTimeDataType (true), "2011-09-01 17:53:49.573"));
    private static final RecordClassDescriptor rcdAllDateTimePublicNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePublic.class).getName (), null, false, null, new StaticDataField ("long1", null, new DateTimeDataType (false), "2011-09-01 17:53:49.573"));
    private static final RecordClassDescriptor rcdAllDateTimePublicNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePublic.class).getName (), null, false, null, new StaticDataField ("long1", null, new DateTimeDataType (true), "2011-09-01 17:53:49.573"));
    public static final class AllDateTimePrivate extends InstrumentMessage {
        public long long1;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return java.lang.String.valueOf ((this).long1);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePrivate))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePrivate other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePrivate) (o);
            return ((this).long1) == ((other).long1);
        }}
    private static final RecordClassDescriptor rcdAllDateTimePrivateNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePrivate.class).getName (), null, false, null, new NonStaticDataField ("long1", null, new DateTimeDataType (true)));
    private static final RecordClassDescriptor rcdAllDateTimePrivateNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePrivate.class).getName (), null, false, null, new NonStaticDataField ("long1", null, new DateTimeDataType (false)));
    private static final RecordClassDescriptor rcdAllDateTimePrivateNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePrivate.class).getName (), null, false, null, new StaticDataField ("long1", null, new DateTimeDataType (true), "2011-09-01 17:53:49.573"));
    private static final RecordClassDescriptor rcdAllDateTimePrivateNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePrivate.class).getName (), null, false, null, new StaticDataField ("long1", null, new DateTimeDataType (false), "2011-09-01 17:53:49.573"));
    private static final RecordClassDescriptor rcdAllDateTimePrivateNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllDateTimePrivate.class).getName (), null, false, null, new StaticDataField ("long1", null, new DateTimeDataType (true), "2011-09-01 17:53:49.573"));
    @org.junit.Test
    public void testDateTimeDataTypeComp ()
    {
        super.setUpComp ();
        this.testDateTimeDataType ();
    }
    @org.junit.Test
    public void testDateTimeDataTypeIntp ()
    {
        super.setUpIntp ();
        this.testDateTimeDataType ();
    }
    private void testTimeOfDayDataType ()
    {
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPublic ();
            (msg).int1 = 81597955;
            super.testRcdBound ("public nullable - normal values", msg, rcdAllTimeOfDayPublicNullable);
            super.testRcdBound ("public non-nullable - normal values", msg, rcdAllTimeOfDayPublicNotNullable);
            super.testRcdBound ("public nullable static - normal values", msg, rcdAllTimeOfDayPublicNullableStatic);
            super.testRcdBound ("public non-nullable static - normal values", msg, rcdAllTimeOfDayPublicNotNullableStatic);
            (msg).int1 = TimeOfDayDataType.NULL;
            super.testRcdBound ("public nullable - null values", msg, rcdAllTimeOfDayPublicNullable);
            super.testRcdBound ("public nullable static - null values", msg, rcdAllTimeOfDayPublicNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPublic ();
            (msg).int1 = TimeOfDayDataType.NULL;
            try {
                super.boundEncode (msg, rcdAllTimeOfDayPublicNotNullable);
                org.junit.Assert.fail ("null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("null violation - public", "java.lang.IllegalArgumentException: \'int1\' field is not nullable", (e).toString ());
            }
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPrivate ();
            (msg).int1 = 81597955;
            super.testRcdBound ("private nullable - normal values", msg, rcdAllTimeOfDayPrivateNullable);
            super.testRcdBound ("private non-nullable - normal values", msg, rcdAllTimeOfDayPrivateNotNullable);
            super.testRcdBound ("private nullable static - normal values", msg, rcdAllTimeOfDayPrivateNullableStatic);
            super.testRcdBound ("private non-nullable static - normal values", msg, rcdAllTimeOfDayPrivateNotNullableStatic);
            (msg).int1 = TimeOfDayDataType.NULL;
            super.testRcdBound ("private nullable - null values", msg, rcdAllTimeOfDayPrivateNullable);
            super.testRcdBound ("private nullable static - null values", msg, rcdAllTimeOfDayPrivateNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPrivate ();
            (msg).int1 = TimeOfDayDataType.NULL;
            try {
                super.boundEncode (msg, rcdAllTimeOfDayPrivateNotNullable);
                org.junit.Assert.fail ("null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("null violation - private", "java.lang.IllegalArgumentException: \'int1\' field is not nullable", (e).toString ());
            }
        }
    }
    public static final class AllTimeOfDayPublic extends InstrumentMessage {
        public int int1;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return java.lang.String.valueOf ((this).int1);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPublic))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPublic other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPublic) (o);
            return ((this).int1) == ((other).int1);
        }}
    private static final RecordClassDescriptor rcdAllTimeOfDayPublicNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPublic.class).getName (), null, false, null, new NonStaticDataField ("int1", null, new TimeOfDayDataType (true)));
    private static final RecordClassDescriptor rcdAllTimeOfDayPublicNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPublic.class).getName (), null, false, null, new NonStaticDataField ("int1", null, new TimeOfDayDataType (false)));
    private static final RecordClassDescriptor rcdAllTimeOfDayPublicNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPublic.class).getName (), null, false, null, new StaticDataField ("int1", null, new TimeOfDayDataType (true), "22:39:57.955"));
    private static final RecordClassDescriptor rcdAllTimeOfDayPublicNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPublic.class).getName (), null, false, null, new StaticDataField ("int1", null, new TimeOfDayDataType (false), "22:39:57.955"));
    private static final RecordClassDescriptor rcdAllTimeOfDayPublicNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPublic.class).getName (), null, false, null, new StaticDataField ("int1", null, new TimeOfDayDataType (true), null));
    public static final class AllTimeOfDayPrivate extends InstrumentMessage {
        public int int1;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return java.lang.String.valueOf ((this).int1);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPrivate))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPrivate other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPrivate) (o);
            return ((this).int1) == ((other).int1);
        }}
    private static final RecordClassDescriptor rcdAllTimeOfDayPrivateNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPrivate.class).getName (), null, false, null, new NonStaticDataField ("int1", null, new TimeOfDayDataType (true)));
    private static final RecordClassDescriptor rcdAllTimeOfDayPrivateNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPrivate.class).getName (), null, false, null, new NonStaticDataField ("int1", null, new TimeOfDayDataType (false)));
    private static final RecordClassDescriptor rcdAllTimeOfDayPrivateNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPrivate.class).getName (), null, false, null, new StaticDataField ("int1", null, new TimeOfDayDataType (true), "22:39:57.955"));
    private static final RecordClassDescriptor rcdAllTimeOfDayPrivateNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPrivate.class).getName (), null, false, null, new StaticDataField ("int1", null, new TimeOfDayDataType (false), "22:39:57.955"));
    private static final RecordClassDescriptor rcdAllTimeOfDayPrivateNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTimeOfDayPrivate.class).getName (), null, false, null, new StaticDataField ("int1", null, new TimeOfDayDataType (true), null));
    @org.junit.Test
    public void testTimeOfDayDataTypeComp ()
    {
        super.setUpComp ();
        this.testTimeOfDayDataType ();
    }
    @org.junit.Test
    public void testTimeOfDayDataTypeIntp ()
    {
        super.setUpIntp ();
        this.testTimeOfDayDataType ();
    }
    private void testCharacterDataType ()
    {
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPublic ();
            (msg).char1 = 'Z';
            super.testRcdBound ("public nullable - normal values", msg, rcdAllCharacterPublicNullable);
            super.testRcdBound ("public non-nullable - normal values", msg, rcdAllCharacterPublicNotNullable);
            super.testRcdBound ("public nullable static - normal values", msg, rcdAllCharacterPublicNullableStatic);
            super.testRcdBound ("public non-nullable static - normal values", msg, rcdAllCharacterPublicNotNullableStatic);
            (msg).char1 = CharDataType.NULL;
            super.testRcdBound ("public nullable - null values", msg, rcdAllCharacterPublicNullable);
            super.testRcdBound ("public nullable static - null values", msg, rcdAllCharacterPublicNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPublic ();
            (msg).char1 = CharDataType.NULL;
            try {
                super.boundEncode (msg, rcdAllCharacterPublicNotNullable);
                org.junit.Assert.fail ("null violation - public");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("null violation - public", "java.lang.IllegalArgumentException: \'char1\' field is not nullable", (e).toString ());
            }
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPrivate ();
            (msg).char1 = 'Z';
            super.testRcdBound ("private nullable - normal values", msg, rcdAllCharacterPrivateNullable);
            super.testRcdBound ("private non-nullable - normal values", msg, rcdAllCharacterPrivateNotNullable);
            super.testRcdBound ("private nullable static - normal values", msg, rcdAllCharacterPrivateNullableStatic);
            super.testRcdBound ("private non-nullable static - normal values", msg, rcdAllCharacterPrivateNotNullableStatic);
            (msg).char1 = CharDataType.NULL;
            super.testRcdBound ("private nullable - null values", msg, rcdAllCharacterPrivateNullable);
            super.testRcdBound ("private nullable static - null values", msg, rcdAllCharacterPrivateNullableStaticNV);
        }
        {
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPrivate msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPrivate ();
            (msg).char1 = CharDataType.NULL;
            try {
                super.boundEncode (msg, rcdAllCharacterPrivateNotNullable);
                org.junit.Assert.fail ("null violation - private");
            }catch (java.lang.IllegalArgumentException e) {
                org.junit.Assert.assertEquals ("null violation - private", "java.lang.IllegalArgumentException: \'char1\' field is not nullable", (e).toString ());
            }
        }
    }
    public static final class AllCharacterPublic extends InstrumentMessage {
        public char char1;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return java.lang.String.valueOf ((this).char1);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPublic))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPublic other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPublic) (o);
            return ((this).char1) == ((other).char1);
        }}
    private static final RecordClassDescriptor rcdAllCharacterPublicNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPublic.class).getName (), null, false, null, new NonStaticDataField ("char1", null, new CharDataType (true)));
    private static final RecordClassDescriptor rcdAllCharacterPublicNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPublic.class).getName (), null, false, null, new NonStaticDataField ("char1", null, new CharDataType (false)));
    private static final RecordClassDescriptor rcdAllCharacterPublicNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPublic.class).getName (), null, false, null, new StaticDataField ("char1", null, new CharDataType (true), "Z"));
    private static final RecordClassDescriptor rcdAllCharacterPublicNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPublic.class).getName (), null, false, null, new StaticDataField ("char1", null, new CharDataType (false), "Z"));
    private static final RecordClassDescriptor rcdAllCharacterPublicNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPublic.class).getName (), null, false, null, new StaticDataField ("char1", null, new CharDataType (true), null));
    public static final class AllCharacterPrivate extends InstrumentMessage {
        public char char1;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return java.lang.String.valueOf ((this).char1);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPrivate))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPrivate other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPrivate) (o);
            return ((this).char1) == ((other).char1);
        }}
    private static final RecordClassDescriptor rcdAllCharacterPrivateNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPrivate.class).getName (), null, false, null, new NonStaticDataField ("char1", null, new CharDataType (true)));
    private static final RecordClassDescriptor rcdAllCharacterPrivateNotNullable = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPrivate.class).getName (), null, false, null, new NonStaticDataField ("char1", null, new CharDataType (false)));
    private static final RecordClassDescriptor rcdAllCharacterPrivateNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPrivate.class).getName (), null, false, null, new StaticDataField ("char1", null, new CharDataType (true), "Z"));
    private static final RecordClassDescriptor rcdAllCharacterPrivateNotNullableStatic = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPrivate.class).getName (), null, false, null, new StaticDataField ("char1", null, new CharDataType (false), "Z"));
    private static final RecordClassDescriptor rcdAllCharacterPrivateNullableStaticNV = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.AllCharacterPrivate.class).getName (), null, false, null, new StaticDataField ("char1", null, new CharDataType (true), null));
    @org.junit.Test
    public void testCharacterDataTypeComp ()
    {
        super.setUpComp ();
        this.testCharacterDataType ();
    }
    @org.junit.Test
    public void testCharacterDataTypeIntp ()
    {
        super.setUpIntp ();
        this.testCharacterDataType ();
    }
    public static final class AllTypesPublic extends InstrumentMessage {
        public boolean boolean1;
        public char char2;
        public byte byte3;
        public short short4;
        public int int5;
        public long long6;
        public float float7;
        public double double8;
        public java.lang.String String9;
        public InstrumentType InstrumentType10;
        public java.lang.CharSequence CharSequence11;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return (((((((((((((((((((((this).boolean1) + (",")) + ((this).char2)) + (",")) + ((this).byte3)) + (",")) + ((this).short4)) + (",")) + ((this).int5)) + (",")) + ((this).long6)) + (",")) + ((this).float7)) + (",")) + ((this).double8)) + (",")) + ((this).String9)) + (",")) + ((this).InstrumentType10)) + (",")) + ((this).CharSequence11);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic) (o);
            return ((((((((((((this).boolean1) == ((other).boolean1)) && (((this).char2) == ((other).char2))) && (((this).byte3) == ((other).byte3))) && (((this).short4) == ((other).short4))) && (((this).int5) == ((other).int5))) && (((this).long6) == ((other).long6))) && ((java.lang.Float.compare ((this).float7, (other).float7)) == (0))) && ((java.lang.Double.compare ((this).double8, (other).double8)) == (0))) && (deltix.util.lang.Util.equals ((this).String9, (other).String9))) && (((this).InstrumentType10) == ((other).InstrumentType10))) && (deltix.util.lang.Util.equals ((this).CharSequence11, (other).CharSequence11));
        }}

    @org.junit.Test
    public void testEarlyBinding ()
    {
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new IntegerDataType ("INT8", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new IntegerDataType ("INT16", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new IntegerDataType ("INT32", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new IntegerDataType ("INT64", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new IntegerDataType ("INT48", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new IntegerDataType ("PUINT30", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new IntegerDataType ("PUINT61", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new IntegerDataType ("PINTERVAL", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new IntegerDataType ("INT8", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new IntegerDataType ("INT16", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new IntegerDataType ("INT32", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new IntegerDataType ("INT64", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new IntegerDataType ("INT48", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new IntegerDataType ("PUINT30", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new IntegerDataType ("PUINT61", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new IntegerDataType ("PINTERVAL", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to char field", (e).toString ());
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new IntegerDataType ("INT8", false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new IntegerDataType ("INT16", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("byte3 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INT16 cannot be bound to byte field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new IntegerDataType ("INT32", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("byte3 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INT32 cannot be bound to byte field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new IntegerDataType ("INT64", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("byte3 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INT64 cannot be bound to byte field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new IntegerDataType ("INT48", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("byte3 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INT48 cannot be bound to byte field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new IntegerDataType ("PUINT30", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("byte3 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: PUINT30 cannot be bound to byte field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new IntegerDataType ("PUINT61", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("byte3 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: PUINT61 cannot be bound to byte field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new IntegerDataType ("PINTERVAL", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("byte3 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: PINTERVAL cannot be bound to byte field", (e).toString ());
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new IntegerDataType ("INT8", false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new IntegerDataType ("INT16", false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new IntegerDataType ("INT32", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("short4 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INT32 cannot be bound to short field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new IntegerDataType ("INT64", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("short4 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INT64 cannot be bound to short field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new IntegerDataType ("INT48", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("short4 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INT48 cannot be bound to short field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new IntegerDataType ("PUINT30", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("short4 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: PUINT30 cannot be bound to short field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new IntegerDataType ("PUINT61", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("short4 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: PUINT61 cannot be bound to short field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new IntegerDataType ("PINTERVAL", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("short4 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: PINTERVAL cannot be bound to short field", (e).toString ());
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new IntegerDataType ("INT8", false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new IntegerDataType ("INT16", false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new IntegerDataType ("INT32", false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new IntegerDataType ("INT64", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("int5 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INT64 cannot be bound to int field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new IntegerDataType ("INT48", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("int5 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INT48 cannot be bound to int field", (e).toString ());
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new IntegerDataType ("PUINT30", false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new IntegerDataType ("PUINT61", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("int5 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: PUINT61 cannot be bound to int field", (e).toString ());
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new IntegerDataType ("PINTERVAL", false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null, new IntegerDataType ("INT8", false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null, new IntegerDataType ("INT16", false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null, new IntegerDataType ("INT32", false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null, new IntegerDataType ("INT64", false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null, new IntegerDataType ("INT48", false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null, new IntegerDataType ("PUINT30", false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null, new IntegerDataType ("PUINT61", false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null, new IntegerDataType ("PINTERVAL", false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new IntegerDataType ("INT8", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new IntegerDataType ("INT16", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new IntegerDataType ("INT32", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new IntegerDataType ("INT64", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new IntegerDataType ("INT48", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new IntegerDataType ("PUINT30", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new IntegerDataType ("PUINT61", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new IntegerDataType ("PINTERVAL", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new IntegerDataType ("INT8", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("double8 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to double field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new IntegerDataType ("INT16", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("double8 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to double field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new IntegerDataType ("INT32", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("double8 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to double field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new IntegerDataType ("INT64", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("double8 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to double field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new IntegerDataType ("INT48", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("double8 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to double field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new IntegerDataType ("PUINT30", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("double8 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to double field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new IntegerDataType ("PUINT61", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("double8 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to double field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new IntegerDataType ("PINTERVAL", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("double8 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to double field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new IntegerDataType ("INT8", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("String9 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to java.lang.String field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new IntegerDataType ("INT16", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("String9 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to java.lang.String field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new IntegerDataType ("INT32", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("String9 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to java.lang.String field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new IntegerDataType ("INT64", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("String9 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to java.lang.String field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new IntegerDataType ("INT48", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("String9 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to java.lang.String field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new IntegerDataType ("PUINT30", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("String9 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to java.lang.String field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new IntegerDataType ("PUINT61", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("String9 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to java.lang.String field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new IntegerDataType ("PINTERVAL", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("String9 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to java.lang.String field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new IntegerDataType ("INT8", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("InstrumentType10 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to deltix.timebase.api.messages.InstrumentType field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new IntegerDataType ("INT16", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("InstrumentType10 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to deltix.timebase.api.messages.InstrumentType field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new IntegerDataType ("INT32", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("InstrumentType10 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to deltix.timebase.api.messages.InstrumentType field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new IntegerDataType ("INT64", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("InstrumentType10 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to deltix.timebase.api.messages.InstrumentType field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new IntegerDataType ("INT48", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("InstrumentType10 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to deltix.timebase.api.messages.InstrumentType field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new IntegerDataType ("PUINT30", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("InstrumentType10 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to deltix.timebase.api.messages.InstrumentType field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new IntegerDataType ("PUINT61", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("InstrumentType10 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to deltix.timebase.api.messages.InstrumentType field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new IntegerDataType ("PINTERVAL", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("InstrumentType10 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to deltix.timebase.api.messages.InstrumentType field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new IntegerDataType ("INT8", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("CharSequence11 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to java.lang.CharSequence field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new IntegerDataType ("INT16", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("CharSequence11 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to java.lang.CharSequence field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new IntegerDataType ("INT32", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("CharSequence11 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to java.lang.CharSequence field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new IntegerDataType ("INT64", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("CharSequence11 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to java.lang.CharSequence field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new IntegerDataType ("INT48", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("CharSequence11 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to java.lang.CharSequence field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new IntegerDataType ("PUINT30", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("CharSequence11 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to java.lang.CharSequence field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new IntegerDataType ("PUINT61", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("CharSequence11 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to java.lang.CharSequence field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new IntegerDataType ("PINTERVAL", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("CharSequence11 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: INTEGER cannot be bound to java.lang.CharSequence field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new FloatDataType ("IEEE32", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new FloatDataType ("IEEE64", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new FloatDataType ("DECIMAL", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new FloatDataType ("DECIMAL(2)", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new FloatDataType ("DECIMAL(4)", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new FloatDataType ("IEEE32", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new FloatDataType ("IEEE64", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new FloatDataType ("DECIMAL", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new FloatDataType ("DECIMAL(2)", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new FloatDataType ("DECIMAL(4)", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new FloatDataType ("IEEE32", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("byte3 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to byte field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new FloatDataType ("IEEE64", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("byte3 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to byte field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new FloatDataType ("DECIMAL", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("byte3 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to byte field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new FloatDataType ("DECIMAL(2)", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("byte3 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to byte field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new FloatDataType ("DECIMAL(4)", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("byte3 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to byte field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new FloatDataType ("IEEE32", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("short4 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to short field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new FloatDataType ("IEEE64", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("short4 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to short field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new FloatDataType ("DECIMAL", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("short4 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to short field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new FloatDataType ("DECIMAL(2)", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("short4 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to short field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new FloatDataType ("DECIMAL(4)", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("short4 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to short field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new FloatDataType ("IEEE32", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("int5 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to int field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new FloatDataType ("IEEE64", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("int5 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to int field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout( new NonStaticDataField ("int5", null, new FloatDataType ("DECIMAL", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("int5 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to int field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new FloatDataType ("DECIMAL(2)", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("int5 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to int field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new FloatDataType ("DECIMAL(4)", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("int5 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to int field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout (
                        new NonStaticDataField("long6", null, new FloatDataType(ENCODING_FIXED_FLOAT, false)));
                (layout).bind (Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("long6 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: IEEE32 cannot be bound to long field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout (
                        new NonStaticDataField ("long6", null, new FloatDataType (ENCODING_FIXED_DOUBLE, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("long6 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: IEEE64 cannot be bound to long field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null,
                        new FloatDataType (ENCODING_SCALE_AUTO, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("long6 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: DECIMAL cannot be bound to long field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null,
                        new FloatDataType ("DECIMAL(2)", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("long6 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: DECIMAL(2) cannot be bound to long field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null, new FloatDataType ("DECIMAL(4)", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("long6 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: DECIMAL(4) cannot be bound to long field", (e).toString ());
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new FloatDataType ("IEEE32", false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new FloatDataType ("IEEE64", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: IEEE64 cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new FloatDataType ("DECIMAL", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: DECIMAL cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new FloatDataType ("DECIMAL(2)", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: DECIMAL(2) cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new FloatDataType ("DECIMAL(4)", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: DECIMAL(4) cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new FloatDataType ("IEEE32", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("double8 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: IEEE32 cannot be bound to double field", (e).toString ());
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new FloatDataType ("IEEE64", false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new FloatDataType ("DECIMAL", false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new FloatDataType ("DECIMAL(2)", false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new FloatDataType ("DECIMAL(4)", false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new FloatDataType ("IEEE32", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("String9 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to java.lang.String field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new FloatDataType ("IEEE64", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("String9 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to java.lang.String field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new FloatDataType ("DECIMAL", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("String9 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to java.lang.String field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new FloatDataType ("DECIMAL(2)", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("String9 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to java.lang.String field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new FloatDataType ("DECIMAL(4)", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("String9 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to java.lang.String field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new FloatDataType ("IEEE32", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("InstrumentType10 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to deltix.timebase.api.messages.InstrumentType field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new FloatDataType ("IEEE64", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("InstrumentType10 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to deltix.timebase.api.messages.InstrumentType field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new FloatDataType ("DECIMAL", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("InstrumentType10 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to deltix.timebase.api.messages.InstrumentType field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new FloatDataType ("DECIMAL(2)", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("InstrumentType10 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to deltix.timebase.api.messages.InstrumentType field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new FloatDataType ("DECIMAL(4)", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("InstrumentType10 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to deltix.timebase.api.messages.InstrumentType field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new FloatDataType ("IEEE32", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("CharSequence11 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to java.lang.CharSequence field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new FloatDataType ("IEEE64", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("CharSequence11 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to java.lang.CharSequence field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new FloatDataType ("DECIMAL", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("CharSequence11 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to java.lang.CharSequence field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new FloatDataType ("DECIMAL(2)", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("CharSequence11 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to java.lang.CharSequence field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new FloatDataType ("DECIMAL(4)", false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("CharSequence11 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: FLOAT cannot be bound to java.lang.CharSequence field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new VarcharDataType ("UTF8", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new VarcharDataType ("UTF8", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new VarcharDataType ("ALPHANUMERIC(5)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new VarcharDataType ("ALPHANUMERIC(10)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new VarcharDataType ("UTF8", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new VarcharDataType ("UTF8", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new VarcharDataType ("ALPHANUMERIC(5)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new VarcharDataType ("ALPHANUMERIC(10)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new VarcharDataType ("UTF8", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("byte3 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to byte field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new VarcharDataType ("UTF8", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("byte3 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to byte field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("byte3 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to byte field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("byte3 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to byte field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new VarcharDataType ("ALPHANUMERIC(5)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("byte3 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to byte field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new VarcharDataType ("ALPHANUMERIC(10)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("byte3 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to byte field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new VarcharDataType ("UTF8", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("short4 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to short field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new VarcharDataType ("UTF8", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("short4 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to short field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("short4 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to short field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("short4 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to short field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new VarcharDataType ("ALPHANUMERIC(5)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("short4 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to short field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new VarcharDataType ("ALPHANUMERIC(10)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("short4 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to short field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new VarcharDataType ("UTF8", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("int5 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to int field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new VarcharDataType ("UTF8", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("int5 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to int field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("int5 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to int field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("int5 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to int field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new VarcharDataType ("ALPHANUMERIC(5)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("int5 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to int field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new VarcharDataType ("ALPHANUMERIC(10)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("int5 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to int field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null, new VarcharDataType ("UTF8", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("long6 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: UTF8 cannot be bound to long field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null, new VarcharDataType ("UTF8", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("long6 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: UTF8 cannot be bound to long field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("long6 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: ALPHANUMERIC(100) cannot be bound to long field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("long6 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: ALPHANUMERIC(100) cannot be bound to long field", (e).toString ());
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null, new VarcharDataType ("ALPHANUMERIC(5)", false, false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null, new VarcharDataType ("ALPHANUMERIC(10)", false, false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new VarcharDataType ("UTF8", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new VarcharDataType ("UTF8", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new VarcharDataType ("ALPHANUMERIC(5)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new VarcharDataType ("ALPHANUMERIC(10)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new VarcharDataType ("UTF8", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("double8 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to double field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new VarcharDataType ("UTF8", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("double8 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to double field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("double8 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to double field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("double8 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to double field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new VarcharDataType ("ALPHANUMERIC(5)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("double8 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to double field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new VarcharDataType ("ALPHANUMERIC(10)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("double8 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to double field", (e).toString ());
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new VarcharDataType ("UTF8", false, false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new VarcharDataType ("UTF8", false, false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new VarcharDataType ("ALPHANUMERIC(5)", false, false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new VarcharDataType ("ALPHANUMERIC(10)", false, false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new VarcharDataType ("UTF8", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("InstrumentType10 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to deltix.timebase.api.messages.InstrumentType field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new VarcharDataType ("UTF8", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("InstrumentType10 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to deltix.timebase.api.messages.InstrumentType field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("InstrumentType10 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to deltix.timebase.api.messages.InstrumentType field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("InstrumentType10 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to deltix.timebase.api.messages.InstrumentType field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new VarcharDataType ("ALPHANUMERIC(5)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("InstrumentType10 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to deltix.timebase.api.messages.InstrumentType field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new VarcharDataType ("ALPHANUMERIC(10)", false, false)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("InstrumentType10 to VARCHAR");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: VARCHAR cannot be bound to deltix.timebase.api.messages.InstrumentType field", (e).toString ());
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new VarcharDataType ("UTF8", false, false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new VarcharDataType ("UTF8", false, false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new VarcharDataType ("ALPHANUMERIC(100)", false, false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new VarcharDataType ("ALPHANUMERIC(5)", false, false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new VarcharDataType ("ALPHANUMERIC(10)", false, false)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("boolean1", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("boolean1 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to boolean field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to char field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("char2", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("char2 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to char field", (e).toString ());
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("byte3", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("short4", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("int5", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("long6", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("float7", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to float field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("double8 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to double field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("double8 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to double field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("double8 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to double field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("double8 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to double field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("double8 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to double field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("double8", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("double8 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to double field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("String9 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to java.lang.String field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("String9 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to java.lang.String field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("String9 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to java.lang.String field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("String9 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to java.lang.String field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("String9 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to java.lang.String field", (e).toString ());
        }
        try {
            {
                final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("String9", null, new EnumDataType (false, INSTRUMENT_TYPE)));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("String9 to deltix.timebase.api.messages.InstrumentType");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: deltix.timebase.api.messages.InstrumentType cannot be bound to java.lang.String field", (e).toString ());
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("InstrumentType10", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final NonStaticFieldLayout layout = new NonStaticFieldLayout ( new NonStaticDataField ("CharSequence11", null, new EnumDataType (false, INSTRUMENT_TYPE)));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
    }
    @org.junit.Test
    public void testEarlyBindingStatic ()
    {
        {
            final StaticFieldLayout layout = new StaticFieldLayout (null, new StaticDataField ("byte3", null, new IntegerDataType ("INT64", true), "85"));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        try {
            {
                final StaticFieldLayout layout = new StaticFieldLayout (null, new StaticDataField ("byte3", null, new IntegerDataType ("INT64", true), "128"));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("byte3 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: Cannot store static value 128 to byte", (e).toString ());
        }
        try {
            {
                final StaticFieldLayout layout = new StaticFieldLayout (null, new StaticDataField ("byte3", null, new IntegerDataType ("INT64", true), "-129"));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("byte3 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: Cannot store static value -129 to byte", (e).toString ());
        }
        {
            final StaticFieldLayout layout = new StaticFieldLayout (null, new StaticDataField ("short4", null, new IntegerDataType ("INT64", true), "-30001"));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        try {
            {
                final StaticFieldLayout layout = new StaticFieldLayout (null, new StaticDataField ("short4", null, new IntegerDataType ("INT64", true), "32768"));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("short4 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: Cannot store static value 32768 to short", (e).toString ());
        }
        try {
            {
                final StaticFieldLayout layout = new StaticFieldLayout (null, new StaticDataField ("short4", null, new IntegerDataType ("INT64", true), "-32769"));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("short4 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: Cannot store static value -32769 to short", (e).toString ());
        }
        {
            final StaticFieldLayout layout = new StaticFieldLayout (null, new StaticDataField ("int5", null, new IntegerDataType ("INT64", true), "2000000001"));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        try {
            {
                final StaticFieldLayout layout = new StaticFieldLayout (null, new StaticDataField ("int5", null, new IntegerDataType ("INT64", true), "2147483648"));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("int5 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: Cannot store static value 2147483648 to int", (e).toString ());
        }
        try {
            {
                final StaticFieldLayout layout = new StaticFieldLayout (null, new StaticDataField ("int5", null, new IntegerDataType ("INT64", true), "-2147483649"));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("int5 to INTEGER");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: Cannot store static value -2147483649 to int", (e).toString ());
        }
        {
            final StaticFieldLayout layout = new StaticFieldLayout (null, new StaticDataField ("long6", null, new IntegerDataType ("INT64", true), "2000000000001"));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        {
            final StaticFieldLayout layout = new StaticFieldLayout(null, new StaticDataField ("float7", null, new FloatDataType ("IEEE64", true), "1.12"));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
        try {
            {
                final StaticFieldLayout layout = new StaticFieldLayout (null, new StaticDataField ("float7", null, new FloatDataType ("IEEE64", true), "3.4028236E38"));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: Cannot store static value 3.4028236E38 to float", (e).toString ());
        }
        try {
            {
                final StaticFieldLayout layout = new StaticFieldLayout (null, new StaticDataField ("float7", null, new FloatDataType ("IEEE64", true), "-3.4028236E38"));
                (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
                org.junit.Assert.fail ("float7 to FLOAT");
            }
        }catch (java.lang.IllegalArgumentException e) {
            org.junit.Assert.assertEquals (null, "java.lang.IllegalArgumentException: Cannot store static value -3.4028236E38 to float", (e).toString ());
        }
        {
            final StaticFieldLayout layout = new StaticFieldLayout (null, new StaticDataField ("double8", null, new FloatDataType ("IEEE64", true), "1324.345"));
            (layout).bind (deltix.qsrv.hf.pub.Test_RecordCodecs5.AllTypesPublic.class);
            assert ((layout).getJavaField ()) != (null);
        }
    }

    public static final class ArrayOtherBindingsPublic extends InstrumentMessage {
        public deltix.util.collections.generated.ByteArrayList ByteArrayList1;
        public deltix.util.collections.generated.ShortArrayList ShortArrayList2;
        public deltix.util.collections.generated.IntegerArrayList IntegerArrayList3;
        public deltix.util.collections.generated.LongArrayList LongArrayList4;
        public deltix.util.collections.generated.LongArrayList LongArrayList5;
        public deltix.util.collections.generated.DoubleArrayList DoubleArrayList6;
        @java.lang.Override
        public java.lang.String toString ()
        {
            return (((((((((((this).ByteArrayList1) + (",")) + ((this).ShortArrayList2)) + (",")) + ((this).IntegerArrayList3)) + (",")) + ((this).LongArrayList4)) + (",")) + ((this).LongArrayList5)) + (",")) + ((this).DoubleArrayList6);
        }
        @java.lang.Override
        public boolean equals (java.lang.Object o)
        {
            if (!((o) instanceof deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic))
                return false;
            deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic other = (deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic) (o);
            return (((((deltix.util.lang.Util.xequals ((this).ByteArrayList1, (other).ByteArrayList1)) && (deltix.util.lang.Util.xequals ((this).ShortArrayList2, (other).ShortArrayList2))) && (deltix.util.lang.Util.xequals ((this).IntegerArrayList3, (other).IntegerArrayList3))) && (deltix.util.lang.Util.xequals ((this).LongArrayList4, (other).LongArrayList4))) && (deltix.util.lang.Util.xequals ((this).LongArrayList5, (other).LongArrayList5))) && (deltix.util.lang.Util.xequals ((this).DoubleArrayList6, (other).DoubleArrayList6));
        }
        private void initEmpty ()
        {
            (this).ByteArrayList1 = new deltix.util.collections.generated.ByteArrayList ();
            (this).ShortArrayList2 = new deltix.util.collections.generated.ShortArrayList ();
            (this).IntegerArrayList3 = new deltix.util.collections.generated.IntegerArrayList ();
            (this).LongArrayList4 = new deltix.util.collections.generated.LongArrayList ();
            (this).LongArrayList5 = new deltix.util.collections.generated.LongArrayList ();
            (this).DoubleArrayList6 = new deltix.util.collections.generated.DoubleArrayList ();
        }
        private void initNulls ()
        {
            (this).ByteArrayList1 = null;
            (this).ShortArrayList2 = null;
            (this).IntegerArrayList3 = null;
            (this).LongArrayList4 = null;
            (this).LongArrayList5 = null;
            (this).DoubleArrayList6 = null;
        }}
    private void testArrayOtherBindings ()
    {
        {
            final deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic ();
            (msg).initEmpty ();
            ((msg).ByteArrayList1).add ((byte) (80));
            ((msg).ShortArrayList2).add ((short) (81));
            ((msg).IntegerArrayList3).add (82);
            ((msg).LongArrayList4).add (83);
            ((msg).LongArrayList5).add (140737488355322L);
            ((msg).DoubleArrayList6).add (127.459);
            super.testRcdBound ("public INT8_INT48_IEEE64 one-value", msg, rcdArrayOtherBindingsPublicNullableINT8_INT48_IEEE64);
            (msg).initEmpty ();
            super.testRcdBound ("public INT8_INT48_IEEE64 empty", msg, rcdArrayOtherBindingsPublicNullableINT8_INT48_IEEE64);
            (msg).initNulls ();
            super.testRcdBound ("public INT8_INT48_IEEE64 null", msg, rcdArrayOtherBindingsPublicNullableINT8_INT48_IEEE64);
        }
        {
            final deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic ();
            (msg).initEmpty ();
            ((msg).ShortArrayList2).add ((short) (24401));
            ((msg).IntegerArrayList3).add (24402);
            ((msg).LongArrayList4).add (24403);
            ((msg).LongArrayList5).add (9223372036854447679L);
            ((msg).DoubleArrayList6).add (127.459);
            super.testRcdBound ("public INT16_INT64_DECIMAL one-value", msg, rcdArrayOtherBindingsPublicNullableINT16_INT64_DECIMAL);
            (msg).initEmpty ();
            super.testRcdBound ("public INT16_INT64_DECIMAL empty", msg, rcdArrayOtherBindingsPublicNullableINT16_INT64_DECIMAL);
            (msg).initNulls ();
            super.testRcdBound ("public INT16_INT64_DECIMAL null", msg, rcdArrayOtherBindingsPublicNullableINT16_INT64_DECIMAL);
        }
        {
            final deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic ();
            (msg).initEmpty ();
            ((msg).IntegerArrayList3).add (1599215897);
            ((msg).LongArrayList4).add (1599215898);
            ((msg).LongArrayList5).add (2305843009213693946L);
            ((msg).DoubleArrayList6).add (127.45);
            super.testRcdBound ("public INT32_PUINT61_DECIMAL2 one-value", msg, rcdArrayOtherBindingsPublicNullableINT32_PUINT61_DECIMAL2);
            (msg).initEmpty ();
            super.testRcdBound ("public INT32_PUINT61_DECIMAL2 empty", msg, rcdArrayOtherBindingsPublicNullableINT32_PUINT61_DECIMAL2);
            (msg).initNulls ();
            super.testRcdBound ("public INT32_PUINT61_DECIMAL2 null", msg, rcdArrayOtherBindingsPublicNullableINT32_PUINT61_DECIMAL2);
        }
        {
            final deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic ();
            (msg).initEmpty ();
            ((msg).IntegerArrayList3).add (1073741818);
            ((msg).LongArrayList4).add (1073741819);
            ((msg).LongArrayList5).add (-6313327282072505067L);
            super.testRcdBound ("public PUINT30_VARCHAR one-value", msg, rcdArrayOtherBindingsPublicNullablePUINT30_VARCHAR);
            (msg).initEmpty ();
            super.testRcdBound ("public PUINT30_VARCHAR empty", msg, rcdArrayOtherBindingsPublicNullablePUINT30_VARCHAR);
            (msg).initNulls ();
            super.testRcdBound ("public PUINT30_VARCHAR null", msg, rcdArrayOtherBindingsPublicNullablePUINT30_VARCHAR);
        }
        {
            final deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic ();
            (msg).initEmpty ();
            ((msg).IntegerArrayList3).add (2147483642);
            ((msg).LongArrayList4).add (2147483643);
            ((msg).LongArrayList5).add (1314899629573L);
            super.testRcdBound ("public PINTERVAL_TIMESTAMP one-value", msg, rcdArrayOtherBindingsPublicNullablePINTERVAL_TIMESTAMP);
            (msg).initEmpty ();
            super.testRcdBound ("public PINTERVAL_TIMESTAMP empty", msg, rcdArrayOtherBindingsPublicNullablePINTERVAL_TIMESTAMP);
            (msg).initNulls ();
            super.testRcdBound ("public PINTERVAL_TIMESTAMP null", msg, rcdArrayOtherBindingsPublicNullablePINTERVAL_TIMESTAMP);
        }
        {
            final deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic ();
            (msg).initEmpty ();
            ((msg).IntegerArrayList3).add (81597956);
            super.testRcdBound ("public TIMEOFDAY one-value", msg, rcdArrayOtherBindingsPublicNullableTIMEOFDAY);
            (msg).initEmpty ();
            super.testRcdBound ("public TIMEOFDAY empty", msg, rcdArrayOtherBindingsPublicNullableTIMEOFDAY);
            (msg).initNulls ();
            super.testRcdBound ("public TIMEOFDAY null", msg, rcdArrayOtherBindingsPublicNullableTIMEOFDAY);
        }
        {
            final deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic ();
            (msg).initEmpty ();
            ((msg).ByteArrayList1).add ((byte) ((byte) (1L)));
            ((msg).ShortArrayList2).add ((short) ((byte) (2L)));
            ((msg).IntegerArrayList3).add ((byte) (3L));
            ((msg).LongArrayList4).add ((byte) (4L));
            super.testRcdBound ("public ENUM one-value", msg, rcdArrayOtherBindingsPublicNullableENUM);
            (msg).initEmpty ();
            super.testRcdBound ("public ENUM empty", msg, rcdArrayOtherBindingsPublicNullableENUM);
            (msg).initNulls ();
            super.testRcdBound ("public ENUM null", msg, rcdArrayOtherBindingsPublicNullableENUM);
        }
        {
            final deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic msg = new deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic ();
            (msg).initEmpty ();
            ((msg).ByteArrayList1).add ((byte) ((int) (1L)));
            ((msg).ShortArrayList2).add ((short) ((int) (2L)));
            ((msg).IntegerArrayList3).add ((int) (4L));
            ((msg).LongArrayList4).add ((int) (8L));
            super.testRcdBound ("public BITMASK one-value", msg, rcdArrayOtherBindingsPublicNullableBITMASK);
            (msg).initEmpty ();
            super.testRcdBound ("public BITMASK empty", msg, rcdArrayOtherBindingsPublicNullableBITMASK);
            (msg).initNulls ();
            super.testRcdBound ("public BITMASK null", msg, rcdArrayOtherBindingsPublicNullableBITMASK);
        }
    }
    private static final RecordClassDescriptor rcdArrayOtherBindingsPublicNullableINT8_INT48_IEEE64 = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic.class).getName (), null, false, null, new NonStaticDataField ("ByteArrayList1", null, new ArrayDataType (true, new IntegerDataType ("INT8", true))), new NonStaticDataField ("ShortArrayList2", null, new ArrayDataType (true, new IntegerDataType ("INT8", true))), new NonStaticDataField ("IntegerArrayList3", null, new ArrayDataType (true, new IntegerDataType ("INT8", true))), new NonStaticDataField ("LongArrayList4", null, new ArrayDataType (true, new IntegerDataType ("INT8", true))), new NonStaticDataField ("LongArrayList5", null, new ArrayDataType (true, new IntegerDataType ("INT48", true))), new NonStaticDataField ("DoubleArrayList6", null, new ArrayDataType (true, new FloatDataType ("IEEE64", true))));
    private static final RecordClassDescriptor rcdArrayOtherBindingsPublicNullableINT16_INT64_DECIMAL = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic.class).getName (), null, false, null, new NonStaticDataField ("ByteArrayList1", null, new ArrayDataType (true, new IntegerDataType ("INT8", true))), new NonStaticDataField ("ShortArrayList2", null, new ArrayDataType (true, new IntegerDataType ("INT16", true))), new NonStaticDataField ("IntegerArrayList3", null, new ArrayDataType (true, new IntegerDataType ("INT16", true))), new NonStaticDataField ("LongArrayList4", null, new ArrayDataType (true, new IntegerDataType ("INT16", true))), new NonStaticDataField ("LongArrayList5", null, new ArrayDataType (true, new IntegerDataType ("INT64", true))), new NonStaticDataField ("DoubleArrayList6", null, new ArrayDataType (true, new FloatDataType ("DECIMAL", true))));
    private static final RecordClassDescriptor rcdArrayOtherBindingsPublicNullableINT32_PUINT61_DECIMAL2 = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic.class).getName (), null, false, null, new NonStaticDataField ("ByteArrayList1", null, new ArrayDataType (true, new IntegerDataType ("INT8", true))), new NonStaticDataField ("ShortArrayList2", null, new ArrayDataType (true, new IntegerDataType ("INT16", true))), new NonStaticDataField ("IntegerArrayList3", null, new ArrayDataType (true, new IntegerDataType ("INT32", true))), new NonStaticDataField ("LongArrayList4", null, new ArrayDataType (true, new IntegerDataType ("INT32", true))), new NonStaticDataField ("LongArrayList5", null, new ArrayDataType (true, new IntegerDataType ("PUINT61", true))), new NonStaticDataField ("DoubleArrayList6", null, new ArrayDataType (true, new FloatDataType ("DECIMAL(2)", true))));
    private static final RecordClassDescriptor rcdArrayOtherBindingsPublicNullablePUINT30_VARCHAR = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic.class).getName (), null, false, null, new NonStaticDataField ("ByteArrayList1", null, new ArrayDataType (true, new IntegerDataType ("INT8", true))), new NonStaticDataField ("ShortArrayList2", null, new ArrayDataType (true, new IntegerDataType ("INT16", true))), new NonStaticDataField ("IntegerArrayList3", null, new ArrayDataType (true, new IntegerDataType ("PUINT30", true))), new NonStaticDataField ("LongArrayList4", null, new ArrayDataType (true, new IntegerDataType ("PUINT30", true))), new NonStaticDataField ("LongArrayList5", null, new ArrayDataType (true, new VarcharDataType ("ALPHANUMERIC(10)", true, false))), new NonStaticDataField ("DoubleArrayList6", null, new ArrayDataType (true, new FloatDataType ("IEEE64", true))));
    private static final RecordClassDescriptor rcdArrayOtherBindingsPublicNullablePINTERVAL_TIMESTAMP = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic.class).getName (), null, false, null, new NonStaticDataField ("ByteArrayList1", null, new ArrayDataType (true, new IntegerDataType ("INT8", true))), new NonStaticDataField ("ShortArrayList2", null, new ArrayDataType (true, new IntegerDataType ("INT16", true))), new NonStaticDataField ("IntegerArrayList3", null, new ArrayDataType (true, new IntegerDataType ("PINTERVAL", true))), new NonStaticDataField ("LongArrayList4", null, new ArrayDataType (true, new IntegerDataType ("PINTERVAL", true))), new NonStaticDataField ("LongArrayList5", null, new ArrayDataType (true, new DateTimeDataType (true))), new NonStaticDataField ("DoubleArrayList6", null, new ArrayDataType (true, new FloatDataType ("DECIMAL(2)", true))));
    private static final RecordClassDescriptor rcdArrayOtherBindingsPublicNullableTIMEOFDAY = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic.class).getName (), null, false, null, new NonStaticDataField ("ByteArrayList1", null, new ArrayDataType (true, new IntegerDataType ("INT8", true))), new NonStaticDataField ("ShortArrayList2", null, new ArrayDataType (true, new IntegerDataType ("INT16", true))), new NonStaticDataField ("IntegerArrayList3", null, new ArrayDataType (true, new TimeOfDayDataType (true))), new NonStaticDataField ("LongArrayList4", null, new ArrayDataType (true, new IntegerDataType ("INT64", true))), new NonStaticDataField ("LongArrayList5", null, new ArrayDataType (true, new IntegerDataType ("INT64", true))), new NonStaticDataField ("DoubleArrayList6", null, new ArrayDataType (true, new FloatDataType ("DECIMAL(2)", true))));
    private static final RecordClassDescriptor rcdArrayOtherBindingsPublicNullableENUM = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic.class).getName (), null, false, null, new NonStaticDataField ("ByteArrayList1", null, new ArrayDataType (true, new EnumDataType (true, INSTRUMENT_TYPE))), new NonStaticDataField ("ShortArrayList2", null, new ArrayDataType (true, new EnumDataType (true, INSTRUMENT_TYPE))), new NonStaticDataField ("IntegerArrayList3", null, new ArrayDataType (true, new EnumDataType (true, INSTRUMENT_TYPE))), new NonStaticDataField ("LongArrayList4", null, new ArrayDataType (true, new EnumDataType (true, INSTRUMENT_TYPE))), new NonStaticDataField ("LongArrayList5", null, new ArrayDataType (true, new IntegerDataType ("INT64", true))), new NonStaticDataField ("DoubleArrayList6", null, new ArrayDataType (true, new FloatDataType ("DECIMAL(2)", true))));
    private static final RecordClassDescriptor rcdArrayOtherBindingsPublicNullableBITMASK = new RecordClassDescriptor ((deltix.qsrv.hf.pub.Test_RecordCodecs5.ArrayOtherBindingsPublic.class).getName (), null, false, null, new NonStaticDataField ("ByteArrayList1", null, new ArrayDataType (true, new EnumDataType (true, JavaFieldsBitmask))), new NonStaticDataField ("ShortArrayList2", null, new ArrayDataType (true, new EnumDataType (true, JavaFieldsBitmask))), new NonStaticDataField ("IntegerArrayList3", null, new ArrayDataType (true, new EnumDataType (true, JavaFieldsBitmask))), new NonStaticDataField ("LongArrayList4", null, new ArrayDataType (true, new EnumDataType (true, JavaFieldsBitmask))), new NonStaticDataField ("LongArrayList5", null, new ArrayDataType (true, new IntegerDataType ("INT64", true))), new NonStaticDataField ("DoubleArrayList6", null, new ArrayDataType (true, new FloatDataType ("DECIMAL(2)", true))));
    @org.junit.Test
    public void testArrayOtherBindingsComp ()
    {
        super.setUpComp ();
        this.testArrayOtherBindings ();
    }
    @org.junit.Test
    public void testArrayOtherBindingsIntp ()
    {
        super.setUpIntp ();
        this.testArrayOtherBindings ();
    }
}