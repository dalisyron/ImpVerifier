package imp.ast.expression.integer;

import imp.ast.expression.Expr;

public final class IntExpr extends Expr {

    private final int value;

    public IntExpr(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IntExpr that) {
            return this.value == that.value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
