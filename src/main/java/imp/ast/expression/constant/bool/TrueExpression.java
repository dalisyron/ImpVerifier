package imp.ast.expression.constant.bool;

import imp.ast.expression.Expression;
import imp.ast.expression.ExpressionVisitor;

public final class TrueExpression extends Expression {

    private static final TrueExpression INSTANCE = new TrueExpression();

    private TrueExpression() {}

    // Public method to access the single instance
    public static TrueExpression getInstance() {
        return INSTANCE;
    }



    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
