package imp.ast.expression.bool;

import imp.ast.expression.BinaryExpr;

public final class OrExpr extends BinaryExpr {

    public OrExpr(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    protected String operatorSymbol() {
        return "||";
    }
}
