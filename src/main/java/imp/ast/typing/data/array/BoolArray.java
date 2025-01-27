package imp.ast.typing.data.array;

import com.microsoft.z3.Context;
import com.microsoft.z3.Sort;
import imp.ast.ASTVisitor;
import imp.ast.typing.data.value.BoolType;

import java.util.Objects;

public final class BoolArray extends ArrayType {
    private static BoolArray instance = null;

    private BoolArray() {
        super(BoolType.getInstance());
        instance = this;
    }

    public static BoolArray getInstance() {
        return Objects.requireNonNullElseGet(instance, BoolArray::new);
    }

    @Override
    public Sort interpret(Context ctx) {
        return ctx.mkArraySort(ctx.getBoolSort(), ctx.getBoolSort());
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}
