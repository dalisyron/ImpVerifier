package imp.ast.expression;

public final class IntExpr extends Expr {

    private final int value;

    public IntExpr(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
