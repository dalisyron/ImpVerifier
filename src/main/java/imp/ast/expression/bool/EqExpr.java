package imp.ast.expression.bool;

import imp.ast.expression.BinaryExpr;
import imp.ast.expression.bool.Expr;

public final class EqExpr extends BinaryExpr {

    public EqExpr(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    protected String operatorSymbol() {
        return "==";
    }
}
