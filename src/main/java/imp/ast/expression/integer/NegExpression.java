package imp.ast.expression.integer;

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
    public String toString() {
        return "-" + expression.toString();
    }
}
