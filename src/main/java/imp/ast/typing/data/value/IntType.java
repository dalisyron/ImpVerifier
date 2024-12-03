package imp.ast.typing.data.value;

import com.microsoft.z3.Context;
import com.microsoft.z3.Sort;

public class IntType extends PrimitiveType {
    private static final IntType instance = new IntType();

    private IntType() {
    }

    public static IntType getInstance() {
        return instance;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntType;
    }

    @Override
    public int hashCode() {
        return getClass().getName().hashCode();
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public Sort interpret(Context ctx) {
        return ctx.getIntSort();
    }
}
