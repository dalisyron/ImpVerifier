package imp.ast.expression.bool;

import imp.ast.expression.BinaryExpr;

public final class AndExpr extends BinaryExpr {

    public AndExpr(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    protected String operatorSymbol() {
        return "&&";
    }
}
