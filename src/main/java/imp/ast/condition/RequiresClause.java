package imp.ast.condition;

import imp.ast.expression.Expression;

public record RequiresClause(Expression expression) implements ConditionClause {

    @Override
    public Expression expr() {
        return expression;
    }

    @Override
    public String toString() {
        return "requires " + expression.toString();
    }
}