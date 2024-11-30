package imp.ast.expression.integer;

import imp.ast.expression.Expression;

public final class MulExpression extends Expression {
    private final Expression left;
    private final Expression right;

    public MulExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression left() {
        return left;
    }

    public Expression right() {
        return right;
    }

    @Override
    public String toString() {
        return left.toString() + " * " + right.toString();
    }
}
