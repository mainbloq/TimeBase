package deltix.qsrv.hf.pub.codec;

import deltix.qsrv.hf.codec.ClassCodecFactory;
import deltix.qsrv.hf.codec.cg.CodecCache;
import deltix.qsrv.hf.codec.cg.ObjectManager;
import deltix.qsrv.hf.pub.md.*;
import deltix.qsrv.hf.pub.TypeLoader;
import deltix.util.collections.generated.ObjectHashSet;
import deltix.util.collections.generated.ObjectToObjectHashMap;
import deltix.util.lang.*;
import deltix.util.lang.JavaCompilerHelper.SpecialClassLoader;

/**
 *
 */
public class CompiledCodecMetaFactory extends CodecMetaFactory {
    public static final int                         MAX_COMPILED_FIELDS = 200;
    public static final CompiledCodecMetaFactory    INSTANCE =
        new CompiledCodecMetaFactory ();

    public boolean                          canCompile (RecordClassDescriptor cd) {
        return (cd.getFields().length <= MAX_COMPILED_FIELDS);
    }

    private static boolean hasObjectField(RecordClassDescriptor cd) {
        for (DataField field : cd.getFields()) {
            final DataType dt = field.getType();
            if (dt instanceof ClassDataType || ((dt instanceof ArrayDataType) && ((ArrayDataType) dt).getElementDataType() instanceof ClassDataType))
                return true;
        }

        return cd.getParent() != null && hasObjectField(cd.getParent());
    }

    private final ObjectToObjectHashMap<ClassLoader, SpecialClassLoader> loaders = new ObjectToObjectHashMap<>(10);

    @Override
    public Factory <FixedBoundEncoder>      createFixedBoundEncoderFactory (
        TypeLoader loader,
        RecordClassDescriptor                   cd
    )
    {
        if (!canCompile (cd))
            return (super.createFixedBoundEncoderFactory (loader, cd));

        final RecordLayout layout = new RecordLayout(loader, cd);

        final SpecialClassLoader classLoader = hasObjectField(cd) ?
                new SpecialClassLoader(layout.getTargetClass().getClassLoader() != null ? layout.getTargetClass().getClassLoader() : ClassCodecFactory.class.getClassLoader()) :
                null;
        try {
            return (
                new ClassAsFactory <FixedBoundEncoder> (
                    ClassCodecFactory.createFixedBoundEncoder (loader, layout, classLoader),
                    layout
                )
            );
        } catch (NoSuchMethodException x) {
            throw new RuntimeException (x);
        }
    }

    @Override
    public Factory <FixedExternalDecoder>   createFixedExternalDecoderFactory (
        TypeLoader                              loader,
        RecordClassDescriptor                   cd
    )
    {
        if (!canCompile (cd))
            return (super.createFixedExternalDecoderFactory (loader, cd));

        final RecordLayout layout = new RecordLayout(loader, cd);

        final SpecialClassLoader classLoader = hasObjectField(cd) ?
                new SpecialClassLoader(layout.getTargetClass().getClassLoader() != null ? layout.getTargetClass().getClassLoader() : ClassCodecFactory.class.getClassLoader()) :
                null;

        try {
            return (
                new ClassAsFactory <FixedExternalDecoder> (
                    ClassCodecFactory.createFixedExternalDecoder (loader, layout, classLoader),
                    layout
                )
            );
        } catch (NoSuchMethodException x) {
            throw new RuntimeException (x);
        }
    }

    @Override
    public Factory <BoundDecoder>           createFixedBoundDecoderFactory (
        TypeLoader                             loader,
        RecordClassDescriptor                   cd
    )
    {
        if (!canCompile (cd))
            return (super.createFixedBoundDecoderFactory (loader, cd));

        final RecordLayout layout = new RecordLayout(loader, cd);

//        if(classLoader == null)
//            classLoader  =  new SpecialClassLoader(ClassCodecFactory.class.getClassLoader());
//        if(codecCache == null)
//            codecCache = new CodecCache();

        final SpecialClassLoader classLoader = hasObjectField(cd) ?
                new SpecialClassLoader(layout.getTargetClass().getClassLoader() != null ? layout.getTargetClass().getClassLoader() : ClassCodecFactory.class.getClassLoader()) :
                null;

        try {
            return (
                new ClassAsFactory <BoundDecoder>(
                        ClassCodecFactory.createFixedBoundDecoder(loader, layout, classLoader),
                    layout
                )
            );
        } catch (NoSuchMethodException x) {
            throw new RuntimeException (x);
        }
    }

    @Override
    public Factory <UnboundDecoder>         createFixedUnboundDecoderFactory (
        RecordClassDescriptor                   cd
    )
    {
        if (!canCompile (cd))
            return (super.createFixedUnboundDecoderFactory (cd));

        RecordLayout            layout = new RecordLayout (cd);
        // #7360 temporary disable compiled decoder under Java
        // 8 Feb 2012: completely disable compiled unbound codec
        return
                InterpretingCodecMetaFactory.INSTANCE.createFixedUnboundDecoderFactory(layout.getDescriptor());

    }

    @Override
    public Factory <FixedUnboundEncoder>    createFixedUnboundEncoderFactory (
        RecordClassDescriptor                   cd
    )
    {
        if (!canCompile (cd))
            return (super.createFixedUnboundEncoderFactory (cd));

        RecordLayout            layout = new RecordLayout (cd);

        // 8 Feb 2012: completely disable compiled unbound codec
        return
                InterpretingCodecMetaFactory.INSTANCE.createFixedUnboundEncoderFactory(layout.getDescriptor());
    }
}
