package imp.ast.condition;

import imp.ast.expression.Expression;

public sealed interface ConditionClause permits EnsuresClause, RequiresClause {
    Expression expr();

    @Override
    String toString();
}