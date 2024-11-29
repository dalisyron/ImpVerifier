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

    public Identifier arrayName() {
        return arrayName;
    }

    public Expr indexExpr() {
        return indexExpr;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ArrayRefExpr other = (ArrayRefExpr) obj;
        return arrayName.equals(other.arrayName) && indexExpr.equals(other.indexExpr);
    }

    @Override
    public String toString() {
        return arrayName + "[" + indexExpr + "]";
    }
}
