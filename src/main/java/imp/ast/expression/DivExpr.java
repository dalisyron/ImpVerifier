package imp.ast.expression;

public final class DivExpr extends BinaryExpr {

    public DivExpr(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    protected String operatorSymbol() {
        return "/";
    }
}
