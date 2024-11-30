package imp.ast.statement;

import imp.ast.expression.Expression;

public record ExpressionStatement(Expression expression) implements Statement {

    @Override
    public String toString() {
        return expression.toString() + ";";
    }
}
