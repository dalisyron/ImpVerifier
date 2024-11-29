package imp.ast.expression.bool;

import imp.ast.expression.Expr;

public final class TrueExpr extends Expr {

    private static final TrueExpr INSTANCE = new TrueExpr();

    private TrueExpr() {}

    // Public method to access the single instance
    public static TrueExpr getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "true";
    }
}
