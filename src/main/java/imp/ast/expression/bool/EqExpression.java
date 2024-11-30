package imp.ast.expression.bool;

import imp.ast.expression.Expression;

public final class EqExpression extends Expression {
    private final Expression left;
    private final Expression right;

    public EqExpression(Expression left, Expression right) {
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
    public boolean equals(Object obj) {
        if (obj instanceof EqExpression expr) {
            return left.equals(expr.left) && right.equals(expr.right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return left.hashCode() ^ right.hashCode();
    }

    @Override
    public String toString() {
        return left.toString() + " == " + right.toString();
    }
}
