package imp.ast.expression.bool;

import imp.ast.expression.Expression;

public class LessThanOrEqualExpression extends Expression {
    private final Expression left;
    private final Expression right;

    public LessThanOrEqualExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LessThanOrEqualExpression lessThanOrEqualExpr) {
            return left.equals(lessThanOrEqualExpr.left) && right.equals(lessThanOrEqualExpr.right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * left.hashCode() + right.hashCode();
    }

    @Override
    public String toString() {
        return left + " <= " + right;
    }
}
