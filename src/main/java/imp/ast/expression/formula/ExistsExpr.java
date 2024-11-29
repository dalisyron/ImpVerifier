package imp.ast.expression.formula;

import imp.ast.expression.Expr;
import imp.ast.variable.Identifier;

// Define the ExistsExpr subclass for "exists" quantifier
public final class ExistsExpr extends QuantifiedExpr {

    public ExistsExpr(Identifier variable, Expr expr) {
        super(variable, expr);
    }

    @Override
    public String toString() {
        return "exists " + variable + " :: " + expr;
    }
}
