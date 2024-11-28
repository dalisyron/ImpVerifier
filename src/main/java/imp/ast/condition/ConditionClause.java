package imp.ast.condition;

import imp.ast.expression.Expr;

public sealed interface ConditionClause permits EnsuresClause, RequiresClause {
    Expr expr();

    @Override
    String toString();
}