package imp.ast.expression.bool;

import imp.ast.expression.Expression;

public class GreaterThanExpression extends Expression {
    private final Expression left;
    private final Expression right;

    public GreaterThanExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GreaterThanExpression greaterThanExpr) {
            return left.equals(greaterThanExpr.left) && right.equals(greaterThanExpr.right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * left.hashCode() + right.hashCode();
    }


    @Override
    public String toString() {
        return left + " > " + right;
    }
}
