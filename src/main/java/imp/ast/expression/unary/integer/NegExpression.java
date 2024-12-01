package imp.ast.expression.unary.integer;

import imp.ast.expression.Expression;
import imp.ast.expression.UnaryExpression;

public final class NegExpression extends UnaryExpression {

    public NegExpression(Expression expression) {
        super(expression);
    }

    @Override
    public String operatorSymbol() {
        return "-";
    }
}
