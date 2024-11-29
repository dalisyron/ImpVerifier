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
    public String toString() {
        return left + " > " + right;
    }
}
