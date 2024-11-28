package imp.ast.expression.integer;

import imp.ast.expression.BinaryExpr;
import imp.ast.expression.bool.Expr;

public final class MulExpr extends BinaryExpr {

    public MulExpr(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    protected String operatorSymbol() {
        return "*";
    }
}
