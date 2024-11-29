package imp.ast.expression.integer;

import imp.ast.expression.Expr;

public class AddExpr extends Expr {
    private final Expr left;
    private final Expr right;

    public AddExpr(Expr left, Expr right) {
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
        if (obj instanceof AddExpr addExpr) {
            return left.equals(addExpr.left) && right.equals(addExpr.right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * left.hashCode() + right.hashCode();
    }

    @Override
    public String toString() {
        return left + " + " + right;
    }
}