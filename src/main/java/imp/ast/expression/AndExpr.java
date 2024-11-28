package imp.ast.expression;

public final class AndExpr extends BinaryExpr {

    public AndExpr(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    protected String operatorSymbol() {
        return "&&";
    }
}
