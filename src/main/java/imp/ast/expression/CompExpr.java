package imp.ast.expression;

import imp.ast.expression.bool.Expr;

public abstract class CompExpr extends BinaryExpr {

    private final String operator;

    public CompExpr(Expr left, String operator, Expr right) {
        super(left, right);
        this.operator = operator;
    }

    @Override
    protected String operatorSymbol() {
        return operator;
    }
}
