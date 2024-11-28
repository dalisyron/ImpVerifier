package imp.ast.expression;

public final class AddExpr extends BinaryExpr {

    public AddExpr(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    protected String operatorSymbol() {
        return "+";
    }
}
