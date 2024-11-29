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
    public String toString() {
        return left + " >= " + right;
    }
}
