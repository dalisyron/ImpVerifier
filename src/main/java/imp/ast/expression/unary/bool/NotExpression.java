package imp.ast.expression.unary.bool;

import imp.ast.ASTVisitor;
import imp.ast.expression.Expression;
import imp.ast.expression.UnaryExpression;

public final class NotExpression extends UnaryExpression {

    public NotExpression(Expression expression) {
        super(expression);
    }

    @Override
    public String operatorSymbol() {
        return "!";
    }
}
