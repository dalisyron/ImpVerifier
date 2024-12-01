package imp.ast.expression.constant.bool;

import imp.ast.ASTVisitor;
import imp.ast.expression.Expression;

public final class TrueExpression extends Expression {

    private static final TrueExpression INSTANCE = new TrueExpression();

    private TrueExpression() {}

    // Public method to access the single instance
    public static TrueExpression getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "true";
    }


    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}
