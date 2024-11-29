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
    public String toString() {
        return arrayExpr.toString() + ".length";
    }
}
