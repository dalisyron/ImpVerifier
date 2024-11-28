package imp.ast.expression;

import imp.ast.expression.bool.Expr;

public final class ImpliesExpr extends BinaryExpr {

    public ImpliesExpr(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    protected String operatorSymbol() {
        return "==>";
    }
}
