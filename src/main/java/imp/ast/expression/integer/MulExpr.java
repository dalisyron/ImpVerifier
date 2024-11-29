package imp.ast.expression.integer;

import imp.ast.expression.Expr;

public final class MulExpr extends Expr {
    private final Expr left;
    private final Expr right;

    public MulExpr(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    public Expr left() {
        return left;
    }

    public Expr right() {
        return right;
    }

    @Override
    public String toString() {
        return left.toString() + " * " + right.toString();
    }
}
