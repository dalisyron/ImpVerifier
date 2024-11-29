package imp.ast.expression.bool;

import imp.ast.expression.Expr;

public class LessThanExpr extends Expr {
    private final Expr left;
    private final Expr right;

    public LessThanExpr(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LessThanExpr lessThanExpr) {
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
