package imp.ast.expression.bool;

import imp.ast.expression.Expression;

public class GreaterThanOrEqualExpression extends Expression {
    private final Expression left;
    private final Expression right;

    public GreaterThanOrEqualExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GreaterThanOrEqualExpression greaterThanOrEqualExpr) {
            return left.equals(greaterThanOrEqualExpr.left) && right.equals(greaterThanOrEqualExpr.right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * left.hashCode() + right.hashCode();
    }

    @Override
    public String toString() {
        return left + " >= " + right;
    }
}
