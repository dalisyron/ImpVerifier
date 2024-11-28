package imp.ast.expression;

import imp.ast.expression.bool.Expr;

public final class ParenExpr extends Expr {

    private final Expr expr;

    public ParenExpr(Expr expr) {
        this.expr = expr;
    }

    public Expr expr() {
        return expr;
    }

    @Override
    public String toString() {
        return "(" + expr.toString() + ")";
    }
}
