package imp.ast.expression.bool;

import imp.ast.expression.Expr;

public final class AndExpr extends Expr {
    private final Expr left;
    private final Expr right;

    public AndExpr(Expr left, Expr right) {
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
        return left.toString() + " && " + right.toString();
    }
}
