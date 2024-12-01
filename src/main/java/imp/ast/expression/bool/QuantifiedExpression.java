package imp.ast.expression.bool;

import imp.ast.ASTNode;
import imp.ast.expression.Expression;
import imp.ast.variable.Identifier;

import java.util.List;

// Define an abstract sealed class for quantified expressions
public abstract sealed class QuantifiedExpression extends Expression
        permits ExistsExpression, ForallExpression {

    protected final String quantifier;
    protected final Identifier variable;
    protected final Expression body;

    protected QuantifiedExpression(String quantifier, Identifier variable, Expression body) {
        this.variable = variable;
        this.body = body;
        this.quantifier = quantifier;
    }

    public Identifier variable() {
        return variable;
    }

    public Expression body() {
        return body;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QuantifiedExpression other)) {
            return false;
        }
        return quantifier.equals(other.quantifier) && variable.equals(other.variable) && body.equals(other.body);
    }

    @Override
    public int hashCode() {
        return quantifier.hashCode() ^ variable.hashCode() ^ body.hashCode();
    }

    @Override
    public String toString() {
        return quantifier + " " + variable + " :: " + body;
    }

    
}
