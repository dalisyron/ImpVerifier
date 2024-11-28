package imp.ast.expression;

public final class ImpliesExpr extends BinaryExpr {

    public ImpliesExpr(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    protected String operatorSymbol() {
        return "==>";
    }
}
