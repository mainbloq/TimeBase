package deltix.qsrv.hf.pub;

import deltix.qsrv.hf.pub.md.ClassDescriptor;
import deltix.util.lang.ExceptionHandler;

public interface RegularTypeLoader extends TypeLoader {

    /**
     * @return Class for type specified by given descriptor (never null).
     * Implementation of this function must return the same class for identical class descriptors (i.e. must be "pure function").
     * @throws ClassNotFoundException
     */
    public Class<?> load(ClassDescriptor cd, ExceptionHandler handler) throws ClassNotFoundException;
}
