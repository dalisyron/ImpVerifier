package imp.ast.expression.binary.integer;

import imp.ast.expression.BinaryOpExpression;
import imp.ast.expression.Expression;

public final class SubExpression extends BinaryOpExpression {

    public SubExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String operatorSymbol() {
        return "-";
    }
}
