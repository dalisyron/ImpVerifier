package imp.ast.expression.formula;

import imp.ast.expression.Expression;
import imp.ast.variable.Identifier;

// Define an abstract sealed class for quantified expressions
public abstract sealed class QuantifiedExpression extends Expression
        permits ExistsExpression, ForallExpression {

    protected final String quantifier;
    protected final Identifier variable;
    protected final Expression expression;

    protected QuantifiedExpression(String quantifier, Identifier variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
        this.quantifier = quantifier;
    }

    public Identifier variable() {
        return variable;
    }

    public Expression expr() {
        return expression;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QuantifiedExpression other)) {
            return false;
        }
        return quantifier.equals(other.quantifier) && variable.equals(other.variable) && expression.equals(other.expression);
    }

    @Override
    public int hashCode() {
        return quantifier.hashCode() ^ variable.hashCode() ^ expression.hashCode();
    }

    @Override
    public String toString() {
        return quantifier + " " + variable + " :: " + expression;
    }
}

