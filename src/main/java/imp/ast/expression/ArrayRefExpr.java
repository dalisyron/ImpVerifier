package imp.ast.expression;

import imp.ast.variable.Identifier;

public final class ArrayRefExpr extends ReferenceExpr {

    private final Identifier arrayName;
    private final Expr indexExpr;

    public ArrayRefExpr(Identifier arrayName, Expr indexExpr) {
        this.arrayName = arrayName;
        this.indexExpr = indexExpr;
    }

    public Identifier arrayName() {
        return arrayName;
    }

    public Expr indexExpr() {
        return indexExpr;
    }

    @Override
    public String toString() {
        return arrayName + "[" + indexExpr + "]";
    }
}
