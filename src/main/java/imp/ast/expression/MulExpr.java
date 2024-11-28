package imp.ast.expression;

public final class MulExpr extends BinaryExpr {

    public MulExpr(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    protected String operatorSymbol() {
        return "*";
    }
}
