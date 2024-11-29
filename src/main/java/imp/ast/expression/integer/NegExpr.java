package imp.ast.expression.integer;

import imp.ast.expression.Expr;

public final class NegExpr extends Expr {

    private final Expr expr;

    public NegExpr(Expr expr) {
        this.expr = expr;
    }

    public Expr expr() {
        return expr;
    }

    @Override
    public String toString() {
        return "-" + expr.toString();
    }
}
