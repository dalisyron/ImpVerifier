package imp.ast.typing.data.array;

import com.microsoft.z3.Context;
import com.microsoft.z3.Sort;
import imp.ast.typing.data.DataType;
import imp.ast.typing.data.value.IntType;

import java.util.Objects;

public final class IntArray extends ArrayType {
    private static IntArray instance = null;

    private IntArray() {
        super(IntType.getInstance());
        instance = this;
    }

    public static IntArray getInstance() {
        return Objects.requireNonNullElseGet(instance, IntArray::new);
    }

    @Override
    public Sort interpret(Context ctx) {
        return ctx.mkArraySort(ctx.getIntSort(), ctx.getIntSort());
    }
}
