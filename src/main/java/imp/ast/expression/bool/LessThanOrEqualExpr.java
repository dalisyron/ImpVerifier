package imp.ast.expression.bool;

import imp.ast.expression.Expr;

public class LessThanOrEqualExpr extends Expr {
    private final Expr left;
    private final Expr right;

    public LessThanOrEqualExpr(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LessThanOrEqualExpr lessThanOrEqualExpr) {
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
