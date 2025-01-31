package imp.ast.expression.constant.bool;

import imp.ast.expression.Expression;
import imp.ast.expression.ExpressionVisitor;

public final class FalseExpression extends Expression {

    private static final FalseExpression INSTANCE = new FalseExpression();

    private FalseExpression() {}

    public static FalseExpression getInstance() {
        return INSTANCE;
    }



    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
