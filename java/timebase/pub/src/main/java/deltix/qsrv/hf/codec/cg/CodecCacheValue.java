package deltix.qsrv.hf.codec.cg;

import deltix.qsrv.hf.codec.CompilationUnit;
import deltix.util.jcg.JClass;


public class CodecCacheValue {

    private JClass jClass;
    private CompilationUnit[] dependencies;

    public JClass getjClass() {
        return jClass;
    }

    public void setjClass(JClass jClass) {
        this.jClass = jClass;
    }

    public CompilationUnit[] getDependencies() {
        return dependencies;
    }

    public void setDependencies(CompilationUnit[] dependencies) {
        this.dependencies = dependencies;
    }

    public CodecCacheValue(JClass jClass, CompilationUnit[] dependencies) {
        this.jClass = jClass;
        this.dependencies = dependencies;
    }
}
