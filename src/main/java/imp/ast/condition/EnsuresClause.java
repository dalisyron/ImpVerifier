package imp.ast.condition;

import imp.ast.expression.Expression;

public record EnsuresClause(Expression expression) implements ConditionClause {

    @Override
    public Expression expr() {
        return expression;
    }

    @Override
    public String toString() {
        return "ensures " + expression.toString();
    }
}
