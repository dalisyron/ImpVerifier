package imp.ast.expression.bool;

import imp.ast.expression.Expression;

public final class FalseExpression extends Expression {

    private static final FalseExpression INSTANCE = new FalseExpression();

    private FalseExpression() {}

    public static FalseExpression getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "false";
    }
}
