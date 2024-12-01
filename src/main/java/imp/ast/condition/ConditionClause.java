package imp.ast.condition;

import imp.ast.ASTNode;
import imp.ast.expression.Expression;

public sealed interface ConditionClause extends ASTNode permits EnsuresClause, RequiresClause {
    Expression expr();

    @Override
    String toString();
}