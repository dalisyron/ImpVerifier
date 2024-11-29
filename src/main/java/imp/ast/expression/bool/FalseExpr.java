package imp.ast.expression.bool;

import imp.ast.expression.Expr;

public final class FalseExpr extends Expr {

    private static final FalseExpr INSTANCE = new FalseExpr();

    private FalseExpr() {}

    public static FalseExpr getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "false";
    }
}
