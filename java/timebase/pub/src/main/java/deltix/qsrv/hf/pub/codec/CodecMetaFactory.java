package deltix.qsrv.hf.pub.codec;

import deltix.qsrv.hf.pub.codec.intp.*;
import deltix.qsrv.hf.pub.md.*;
import deltix.qsrv.hf.pub.TypeLoader;
import deltix.util.lang.Factory;

/**
 *
 */
public abstract class CodecMetaFactory {
    public Factory <FixedBoundEncoder>      createFixedBoundEncoderFactory (
        TypeLoader              loader,
        RecordClassDescriptor   cd
    )
    {
        return (
            new IntpCodecFactoryBase<FixedBoundEncoder>(loader, cd) {
                public FixedBoundEncoder create() {
                    return (new FixedBoundEncoderImpl(layout));
                }
            }
        );
    }

    public Factory <FixedExternalDecoder>   createFixedExternalDecoderFactory (
        TypeLoader                              loader,
        RecordClassDescriptor                   cd
    )
    {
        return (
            new IntpCodecFactoryBase<FixedExternalDecoder>(loader, cd) {
                public FixedExternalDecoder create() {
                    return (new FixedBoundExternalDecoderImpl(layout));
                }
            }
        );
    }

    public Factory <BoundDecoder>           createFixedBoundDecoderFactory (
        TypeLoader                              loader,
        RecordClassDescriptor                   cd
    )
    {
        return (
            new IntpCodecFactoryBase<BoundDecoder>(loader, cd) {
                public BoundDecoder create() {
                    return (new FixedBoundDecoderImpl(layout));
                }
            }
        );
    }

    public Factory <UnboundDecoder>         createFixedUnboundDecoderFactory (
        RecordClassDescriptor                   cd
    )
    {
        return (
            new IntpCodecFactoryBase <UnboundDecoder> (cd) {
                public UnboundDecoder    create () {
                    return (new FixedUnboundDecoderImpl (layout));
                }
            }
        );
    }

    public Factory <FixedUnboundEncoder>    createFixedUnboundEncoderFactory (
        RecordClassDescriptor                   cd
    )
    {
        return (
            new IntpCodecFactoryBase <FixedUnboundEncoder> (cd) {
                public FixedUnboundEncoder    create () {
                    return (new FixedUnboundEncoderImpl (layout));
                }
            }
        );
    }

    @SuppressWarnings ("unchecked")
    public final Factory <UnboundDecoder>      createPolyUnboundDecoderFactory (
        RecordClassDescriptor ...                       cds
    )
    {
        int                                     num = cds.length;
        Factory <UnboundDecoder> []        fa =
            (Factory <UnboundDecoder> []) new Factory [num];

        for (int ii = 0; ii < num; ii++)
            fa [ii] = createFixedUnboundDecoderFactory (cds [ii]);

        return (
            new PolyCodecFactoryBase <UnboundDecoder, UnboundDecoder> (fa) {
                public UnboundDecoder    create () {
                    int                 num = fixedFactories.length;
                    UnboundDecoder []     decoders = new UnboundDecoder[num];

                    for (int ii = 0; ii < num; ii++)
                        decoders [ii] = fixedFactories [ii].create ();

                    return (new PolyUnboundDecoderImpl (decoders));
                }
            }
        );
    }

    @SuppressWarnings ("unchecked")
    public final Factory <PolyUnboundEncoder>       createPolyUnboundEncoderFactory (
        RecordClassDescriptor ...                       cds
    )
    {
        int                             num = cds.length;
        Factory <FixedUnboundEncoder> []  fa =
            (Factory <FixedUnboundEncoder> []) new Factory [num];

        for (int ii = 0; ii < num; ii++)
            fa [ii] = createFixedUnboundEncoderFactory (cds [ii]);

        return (
            new PolyCodecFactoryBase <PolyUnboundEncoder, FixedUnboundEncoder> (fa) {
                public PolyUnboundEncoder  create () {
                    int                     num = fixedFactories.length;
                    FixedUnboundEncoder []    encoders = new FixedUnboundEncoder [num];

                    for (int ii = 0; ii < num; ii++)
                        encoders [ii] = fixedFactories [ii].create ();

                    return (new PolyUnboundEncoderImpl (encoders));
                }
            }
        );
    }

    @SuppressWarnings ("unchecked")
    public final Factory <BoundDecoder>             createPolyBoundDecoderFactory (
        TypeLoader                                      loader,
        boolean                                         ignoreUnloadableClasses,
        RecordClassDescriptor ...                       cds
    )
    {
        int                             num = cds.length;
        Factory <BoundDecoder> []       fa =
            (Factory <BoundDecoder> []) new Factory [num];

        for (int ii = 0; ii < num; ii++)
            try {
                fa [ii] = createFixedBoundDecoderFactory (loader, cds [ii]);
            } catch (MetaDataBindException x) {
                if (ignoreUnloadableClasses)
                    fa [ii] = null;
                else
                    throw x;
            }

        return (
            new PolyCodecFactoryBase <BoundDecoder, BoundDecoder> (fa) {
                public BoundDecoder    create () {
                    int                 num = fixedFactories.length;
                    BoundDecoder []     decoders = new BoundDecoder [num];

                    for (int ii = 0; ii < num; ii++) {
                        Factory <BoundDecoder>  f = fixedFactories [ii];

                        decoders [ii] = f == null ? null : f.create ();
                    }

                    return (new PolyBoundDecoderImpl (decoders));
                }
            }
        );
    }

    @SuppressWarnings ("unchecked")
    public final Factory <PolyBoundEncoder>         createPolyBoundEncoderFactory (
        TypeLoader                                      loader,
        boolean                                         ignoreUnloadableClasses,
        RecordClassDescriptor ...                       cds
    )
    {
        int                             num = cds.length;
        Factory <FixedBoundEncoder> []  fa =
            (Factory <FixedBoundEncoder> []) new Factory [num];

        for (int ii = 0; ii < num; ii++)
            try {
                fa [ii] = createFixedBoundEncoderFactory (loader, cds [ii]);
            } catch (MetaDataBindException x) {
                if (ignoreUnloadableClasses)
                    fa [ii] = null;
                else
                    throw x;
            }
            
        return (
            new PolyCodecFactoryBase <PolyBoundEncoder, FixedBoundEncoder> (fa) {
                public PolyBoundEncoder  create () {
                    int                     num = fixedFactories.length;
                    FixedBoundEncoder []    encoders = new FixedBoundEncoder [num];

                    for (int ii = 0; ii < num; ii++) {
                        Factory <FixedBoundEncoder> f = fixedFactories [ii];

                        encoders [ii] = f == null ? null : f.create ();
                    }

                    return (new PolyBoundEncoderImpl (encoders));
                }
            }
        );
    }
}
