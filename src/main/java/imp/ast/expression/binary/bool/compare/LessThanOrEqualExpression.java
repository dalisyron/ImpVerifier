package imp.ast.expression.binary.bool.compare;

import imp.ast.expression.BinaryOpExpression;
import imp.ast.expression.Expression;

public class LessThanOrEqualExpression extends BinaryOpExpression {

    public LessThanOrEqualExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String operatorSymbol() {
        return "<=";
    }
}
