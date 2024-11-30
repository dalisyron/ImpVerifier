package imp.ast.statement;

import imp.ast.expression.Expression;
import imp.ast.expression.ReferenceExpression;

public record AssignStatement(ReferenceExpression lhs, Expression expression) implements Statement {

    @Override
    public String toString() {
        return lhs.toString() + " = " + expression.toString() + ";";
    }
}
