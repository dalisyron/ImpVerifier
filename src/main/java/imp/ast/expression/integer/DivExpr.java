package imp.ast.expression.integer;

import imp.ast.expression.BinaryExpr;
import imp.ast.expression.bool.Expr;

public final class DivExpr extends BinaryExpr {

    public DivExpr(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    protected String operatorSymbol() {
        return "/";
    }
}
