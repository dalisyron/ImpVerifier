package imp.ast.expression.binary.integer;

import imp.ast.expression.BinaryOpExpression;
import imp.ast.expression.Expression;
import imp.ast.expression.ExpressionVisitor;

public final class MulExpression extends BinaryOpExpression {

    public MulExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String operatorSymbol() {
        return "*";
    }


    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
