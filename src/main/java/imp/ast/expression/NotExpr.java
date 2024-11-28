package imp.ast.expression;

public final class NotExpr extends Expr {

    private final Expr expr;

    public NotExpr(Expr expr) {
        this.expr = expr;
    }

    public Expr expr() {
        return expr;
    }

    @Override
    public String toString() {
        return "!" + expr.toString();
    }
}
