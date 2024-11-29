package imp.ast.expression.formula;

import imp.ast.expression.Expr;
import imp.ast.variable.Identifier;

// Define an abstract sealed class for quantified expressions
public abstract sealed class QuantifiedExpr extends Expr
        permits ExistsExpr, ForallExpr {

    protected final Identifier variable;
    protected final Expr expr;

    protected QuantifiedExpr(Identifier variable, Expr expr) {
        this.variable = variable;
        this.expr = expr;
    }

    public Identifier variable() {
        return variable;
    }

    public Expr expr() {
        return expr;
    }

    @Override
    public abstract String toString();
}

