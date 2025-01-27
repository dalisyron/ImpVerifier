package imp.ast.condition;

import imp.ast.ASTNode;
import imp.ast.expression.Expression;
import imp.interpreter.expr.Z3BoolExprInterpreter;

public sealed abstract class ConditionClause extends ASTNode implements Z3BoolExprInterpreter permits EnsuresClause, RequiresClause {
    abstract Expression expr();
}