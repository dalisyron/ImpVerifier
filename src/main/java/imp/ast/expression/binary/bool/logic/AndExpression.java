package imp.ast.expression.binary.bool.logic;

import imp.ast.expression.BinaryOpExpression;
import imp.ast.expression.Expression;

public final class AndExpression extends BinaryOpExpression {

    public AndExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String operatorSymbol() {
        return "&&";
    }
}
