package imp.ast.expression.bool;

import imp.ast.expression.Expr;

public final class EqExpr extends Expr {
    private final Expr left;
    private final Expr right;

    public EqExpr(Expr left, Expr right) {
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
    public boolean equals(Object obj) {
        if (obj instanceof EqExpr expr) {
            return left.equals(expr.left) && right.equals(expr.right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return left.hashCode() ^ right.hashCode();
    }

    @Override
    public String toString() {
        return left.toString() + " == " + right.toString();
    }
}
