package imp.ast.expression.formula;

import imp.ast.expression.Expr;
import imp.ast.variable.Identifier;

// Define an abstract sealed class for quantified expressions
public abstract sealed class QuantifiedExpr extends Expr
        permits ExistsExpr, ForallExpr {

    protected final String quantifier;
    protected final Identifier variable;
    protected final Expr expr;

    protected QuantifiedExpr(String quantifier, Identifier variable, Expr expr) {
        this.variable = variable;
        this.expr = expr;
        this.quantifier = quantifier;
    }

    public Identifier variable() {
        return variable;
    }

    public Expr expr() {
        return expr;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QuantifiedExpr other)) {
            return false;
        }
        return quantifier.equals(other.quantifier) && variable.equals(other.variable) && expr.equals(other.expr);
    }

    @Override
    public int hashCode() {
        return quantifier.hashCode() ^ variable.hashCode() ^ expr.hashCode();
    }

    @Override
    public String toString() {
        return quantifier + " " + variable + " :: " + expr;
    }
}

