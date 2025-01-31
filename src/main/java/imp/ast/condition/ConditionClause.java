package imp.ast.condition;

import imp.ast.ASTNode;
import imp.ast.expression.Expression;

public sealed abstract class ConditionClause extends ASTNode permits EnsuresClause, RequiresClause {
    abstract Expression expr();
}