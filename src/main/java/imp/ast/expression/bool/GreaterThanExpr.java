package imp.ast.expression.bool;

import imp.ast.expression.Expr;

public class GreaterThanExpr extends Expr {
    private final Expr left;
    private final Expr right;

    public GreaterThanExpr(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GreaterThanExpr greaterThanExpr) {
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
