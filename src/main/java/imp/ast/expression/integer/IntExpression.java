package imp.ast.expression.integer;

import imp.ast.ASTNode;
import imp.ast.expression.Expression;

import java.util.List;

public final class IntExpression extends Expression implements IntExpectedType {

    private final int value;

    public IntExpression(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IntExpression that) {
            return this.value == that.value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of();
    }
}
