package imp.ast.expression;

import imp.ast.variable.Identifier;

public final class QuantifiedExpr extends Expr {

    private final String quantifier; // "forall" or "exists"
    private final Identifier variable;
    private final Expr expr;

    public QuantifiedExpr(String quantifier, Identifier variable, Expr expr) {
        this.quantifier = quantifier;
        this.variable = variable;
        this.expr = expr;
    }

    public String quantifier() {
        return quantifier;
    }

    public Identifier variable() {
        return variable;
    }

    public Expr expr() {
        return expr;
    }

    @Override
    public String toString() {
        return quantifier + " " + variable + " :: " + expr;
    }
}
