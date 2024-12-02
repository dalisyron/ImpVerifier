package imp.ast.expression.binary.integer;

import imp.ast.expression.BinaryOpExpression;
import imp.ast.expression.Expression;

public class DivExpression extends BinaryOpExpression {

    public DivExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String operatorSymbol() {
        return "/";
    }
}
