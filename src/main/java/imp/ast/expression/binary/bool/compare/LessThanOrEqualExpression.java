package imp.ast.expression.binary.bool.compare;

import imp.ast.expression.BinaryOpExpression;
import imp.ast.expression.Expression;
import imp.ast.expression.ExpressionVisitor;

public class LessThanOrEqualExpression extends BinaryOpExpression {

    public LessThanOrEqualExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String operatorSymbol() {
        return "<=";
    }


    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
