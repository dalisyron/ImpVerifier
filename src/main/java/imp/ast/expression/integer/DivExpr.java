package imp.ast.expression.integer;

import imp.ast.expression.Expr;

public class DivExpr extends Expr {
    private final Expr left;
    private final Expr right;

    public DivExpr(Expr left, Expr right) {
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
        if (obj instanceof DivExpr that) {
            return this.left.equals(that.left) && this.right.equals(that.right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * left.hashCode() + right.hashCode();
    }

    @Override
    public String toString() {
        return left.toString() + " / " + right.toString();
    }
}