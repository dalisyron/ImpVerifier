package imp.ast.expression.unary.bool;

import imp.ast.expression.Expression;

public final class NotExpression extends Expression {

    private final Expression expression;

    public NotExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression expr() {
        return expression;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NotExpression that) {
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
        return "!" + expression.toString();
    }

    
}
