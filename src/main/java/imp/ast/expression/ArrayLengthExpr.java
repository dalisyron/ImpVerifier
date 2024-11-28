package imp.ast.expression;

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
