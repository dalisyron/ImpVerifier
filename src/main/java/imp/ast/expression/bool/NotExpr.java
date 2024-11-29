package imp.ast.expression.bool;

import imp.ast.expression.Expr;

public final class NotExpr extends Expr {

    private final Expr expr;

    public NotExpr(Expr expr) {
        this.expr = expr;
    }

    public Expr expr() {
        return expr;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NotExpr that) {
            return this.expr.equals(that.expr);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * expr.hashCode();
    }

    @Override
    public String toString() {
        return "!" + expr.toString();
    }
}
