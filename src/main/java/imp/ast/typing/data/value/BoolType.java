package imp.ast.typing.data.value;

import com.microsoft.z3.Context;
import com.microsoft.z3.Sort;
import imp.ast.typing.data.DataType;

public final class BoolType extends PrimitiveType {
    private static final BoolType instance = new BoolType();

    private BoolType() {
    }

    public static BoolType getInstance() {
        return instance;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BoolType;
    }

    @Override
    public int hashCode() {
        return getClass().getName().hashCode();
    }

    @Override
    public Sort interpret(Context ctx) {
        return ctx.mkBoolSort();
    }

    @Override
    public String toString() {
        return "bool";
    }
}
