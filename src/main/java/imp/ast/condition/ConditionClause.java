package imp.ast.condition;

import imp.ast.expression.bool.Expr;

public sealed interface ConditionClause permits EnsuresClause, RequiresClause {
    Expr expr();

    @Override
    String toString();
}