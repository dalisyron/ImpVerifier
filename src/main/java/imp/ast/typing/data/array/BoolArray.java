package imp.ast.typing.data.array;

import com.microsoft.z3.Context;
import com.microsoft.z3.Sort;
import imp.ast.ASTVisitor;
import imp.ast.expression.TypeVisitor;
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
    public void accept(TypeVisitor v) {
        v.visit(this);
    }
}
