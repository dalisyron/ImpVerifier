package imp.ast.expression.integer;

import imp.ast.expression.Expr;

public final class ArrayLengthExpr extends Expr {

    private final Expr arrayExpr;

    public ArrayLengthExpr(Expr arrayExpr) {
        this.arrayExpr = arrayExpr;
    }

    public Expr arrayExpr() {
        return arrayExpr;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ArrayLengthExpr arrayLengthExpr) {
            return arrayExpr.equals(arrayLengthExpr.arrayExpr);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * arrayExpr.hashCode();
    }

    @Override
    public String toString() {
        return arrayExpr.toString() + ".length";
    }
}
