package imp.ast.expression.bool;

import imp.ast.expression.Expr;

public class GreaterThanOrEqualExpr extends Expr {
    private final Expr left;
    private final Expr right;

    public GreaterThanOrEqualExpr(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GreaterThanOrEqualExpr greaterThanOrEqualExpr) {
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
