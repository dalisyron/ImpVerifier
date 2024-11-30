package imp.ast.expression.bool;

import imp.ast.expression.Expression;

public class LessThanExpression extends Expression {
    private final Expression left;
    private final Expression right;

    public LessThanExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LessThanExpression lessThanExpr) {
            return left.equals(lessThanExpr.left) && right.equals(lessThanExpr.right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * left.hashCode() + right.hashCode();
    }

    @Override
    public String toString() {
        return left + " < " + right;
    }
}
