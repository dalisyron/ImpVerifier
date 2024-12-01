package imp.ast.expression.unary.integer;

import imp.ast.expression.Expression;

public final class NegExpression extends Expression {

    private final Expression expression;

    public NegExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression expr() {
        return expression;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NegExpression that) {
            return this.expression.equals(that.expression);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * expression.hashCode();
    }

    @Override
    public String toString() {
        return "-" + expression.toString();
    }

    
}
