package deltix.qsrv.hf.codec.cg;

import deltix.qsrv.hf.pub.md.IntegerDataType;
import deltix.qsrv.hf.pub.md.MdUtil;
import deltix.util.jcg.JCompoundStatement;
import deltix.util.jcg.JExpr;

import static deltix.qsrv.hf.tickdb.lang.compiler.cg.QCGHelpers.CTXT;

/**
 *
 */
public class QBIntegerType extends QBNumericType<QIntegerType> {

    public QBIntegerType(QIntegerType qType, Class<?> javaType, QAccessor accessor) {
        super(qType, javaType, accessor);
    }

    @Override
    public void decode(JExpr input, JCompoundStatement addTo) {
        super.decode(input, addTo);
    }

    @Override
    public void encode(JExpr output, JCompoundStatement addTo) {
        if (javaBaseType == qType.getJavaClass())
            super.encode(output, addTo);
        else {
            qType.encodeExpr(output, getEncodeValue(qType.getNullLiteral()).cast(qType.getJavaClass()), addTo);
        }
    }

    @Override
    public JExpr getNullLiteral() {
        return qType.getNullLiteral();
    }

    @Override
    protected JExpr makeConstantExpr(Object obj) {
        if (obj == null)
            return getNullLiteral();

        if (javaBaseType == qType.getJavaClass())
            return qType.getLiteral((Number) obj);
        else {
            final long l = (((Number) obj).longValue());
            // take into account bound type
            if (MdUtil.isIntegerType(javaBaseType)) {
                if (javaBaseType != long.class ) {
                    // check native type boundaries
                    if (javaBaseType == byte.class ) {
                        if (l < Byte.MIN_VALUE || l > Byte.MAX_VALUE)
                            throw new IllegalArgumentException("provided value exceeded Byte boundaries: " + obj);
                    } else if (javaBaseType == short.class ) {
                        if (l < Short.MIN_VALUE || l > Short.MAX_VALUE)
                            throw new IllegalArgumentException("provided value exceeded Short boundaries: " + obj);
                    } else if (javaBaseType == int.class) {
                        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE)
                            throw new IllegalArgumentException("provided value exceeded Integer boundaries: " + obj);
                    }

                    return CTXT.intLiteral((int) l);
                } else
                    return CTXT.longLiteral(l);
            }
            throw new IllegalStateException("unexpected type " + javaBaseType.getName());
        }
    }

    @Override
    public boolean hasConstraint() {
        return (javaBaseType != qType.getJavaClass() &&  javaBaseType != byte.class)
                || hasTypeRange(qType.dt) || super.hasConstraint();
    }

    @Override
    public JExpr readIsConstraintViolated() {
        if (javaBaseType == qType.getJavaClass() && !hasTypeRange(qType.dt))
            return super.readIsConstraintViolated();
        else
            return getConstraintExpression(this, qType.min, qType.max);
    }

    // whether type has value range, which differs from base type range
    private static boolean hasTypeRange(IntegerDataType dt) {
        return dt.getSize() == 6 ||  // INT48
                dt.getSize() > 8;    // PUINT30, PUINT61, PINTERVAL
    }

    private static String getNullLiteralName(int size, Class<?> type) {
        switch (size) {
            case 1:
                return  "INT8_NULL";
            case 2:
                return  "INT16_NULL";
            case 4:
                return "INT32_NULL";
            case 6:
                return  "INT48_NULL";
            case 8:
                return  "INT64_NULL";
            default:
                throw new IllegalArgumentException("unexpected size " + size);
        }
    }
}

