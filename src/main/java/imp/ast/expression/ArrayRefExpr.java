package imp.ast.expression;

import imp.ast.expression.ReferenceExpr;
import imp.ast.expression.Expr;
import imp.ast.variable.Identifier;

public final class ArrayRefExpr extends ReferenceExpr {
    private final Identifier arrayName;
    private final Expr indexExpr;

    public ArrayRefExpr(Identifier arrayName, Expr indexExpr) {
        this.arrayName = arrayName;
        this.indexExpr = indexExpr;
    }

    @Override
    public String toString() {
        return arrayName + "[" + indexExpr + "]";
    }
}
