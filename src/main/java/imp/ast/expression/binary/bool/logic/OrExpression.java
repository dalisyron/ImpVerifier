package imp.ast.expression.binary.bool.logic;

import imp.ast.expression.BinaryOpExpression;
import imp.ast.expression.Expression;

public final class OrExpression extends BinaryOpExpression {

    public OrExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String operatorSymbol() {
        return "||";
    }
}
