package imp.ast.expression;

public abstract sealed class BinaryExpr extends Expr permits AddExpr, SubExpr, MulExpr, DivExpr, CompExpr,
        AndExpr, OrExpr, EqExpr, ImpliesExpr {

    private final Expr left;
    private final Expr right;

    public BinaryExpr(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    public Expr left() {
        return left;
    }

    public Expr right() {
        return right;
    }

    protected abstract String operatorSymbol();

    @Override
    public String toString() {
        return left.toString() + " " + operatorSymbol() + " " + right.toString();
    }
}
