package deltix.qsrv.hf.tickdb.comm;

import deltix.util.parsers.CompilationException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public interface ExceptionResolver {
    public Throwable create(Class<?> clazz, String message, Throwable cause);
}

