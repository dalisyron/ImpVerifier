package imp.ast.expression.constant.integer;

import imp.ast.ASTVisitor;
import imp.ast.expression.Expression;

public final class IntExpression extends Expression {

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
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}
