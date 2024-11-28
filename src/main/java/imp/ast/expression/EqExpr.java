package imp.ast.expression;

public final class EqExpr extends BinaryExpr {

    public EqExpr(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    protected String operatorSymbol() {
        return "==";
    }
}
