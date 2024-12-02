package imp.ast.expression.constant.bool;

import imp.ast.ASTVisitor;
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


    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}
