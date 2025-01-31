package imp.ast.expression.bool;

import imp.ast.expression.Expression;
import imp.ast.typing.data.DataType;
import imp.ast.expression.Identifier;

// Define an abstract sealed class for quantified expressions
public abstract sealed class QuantifiedExpression extends Expression
        permits ExistsExpression, ForallExpression {

    protected final String quantifier;
    protected final Identifier variable;
    protected final Expression body;
    private final DataType type;

    protected QuantifiedExpression(String quantifier, Identifier variable, DataType type, Expression body) {
        this.variable = variable;
        this.body = body;
        this.quantifier = quantifier;
        this.type = type;
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



    public DataType getType() {
        return type;
    }
}
