package imp.ast.typing.data.array;

import com.microsoft.z3.Context;
import com.microsoft.z3.Sort;
import imp.ast.ASTVisitor;
import imp.ast.expression.TypeVisitor;
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
    public void accept(TypeVisitor v) {
        v.visit(this);
    }
}
