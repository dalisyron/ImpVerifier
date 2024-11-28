package imp.ast.expression.array;

import imp.ast.expression.ReferenceExpr;
import imp.ast.expression.bool.Expr;
import imp.ast.variable.Identifier;

public record ArrayRefExpr(Identifier arrayName, Expr indexExpr) implements ReferenceExpr {

    @Override
    public String toString() {
        return arrayName + "[" + indexExpr + "]";
    }
}
