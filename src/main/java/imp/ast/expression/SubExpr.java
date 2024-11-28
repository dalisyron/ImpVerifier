package imp.ast.expression;

public final class SubExpr extends BinaryExpr {

    public SubExpr(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    protected String operatorSymbol() {
        return "-";
    }
}
