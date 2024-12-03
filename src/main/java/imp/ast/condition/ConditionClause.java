package imp.ast.condition;

import imp.ast.ASTNode;
import imp.ast.expression.Expression;
import imp.interpreter.expr.Z3BoolExprInterpreter;

public sealed interface ConditionClause extends ASTNode, Z3BoolExprInterpreter permits EnsuresClause, RequiresClause {
    Expression expr();

    @Override
    String toString();
}