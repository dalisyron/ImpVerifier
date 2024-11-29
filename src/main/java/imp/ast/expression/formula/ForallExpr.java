package imp.ast.expression.formula;

import imp.ast.expression.Expr;
import imp.ast.variable.Identifier;

// Define the ForallExpr subclass for "forall" quantifier
public final class ForallExpr extends QuantifiedExpr {

    public ForallExpr(Identifier variable, Expr expr) {
        super("forall", variable, expr);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
