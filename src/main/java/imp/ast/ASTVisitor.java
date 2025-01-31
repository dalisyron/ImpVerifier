package imp.ast;

import imp.ast.condition.ConditionList;
import imp.ast.condition.EnsuresClause;
import imp.ast.condition.RequiresClause;
import imp.ast.expression.*;
import imp.ast.method.*;
import imp.ast.statement.*;

public abstract class ASTVisitor implements StatementVisitor {

    public abstract void visit(Program program);

    public abstract void visit(MethodDeclaration methodDeclaration);

    public abstract void visit(ParameterList parameterList);

    public abstract void visit(Parameter parameter);

    public abstract void visit(ReturnValue returnValue);

    public abstract void visit(ConditionList conditionList);

    public abstract void visit(EnsuresClause ensuresClause);

    public abstract void visit(MethodBody methodBody);

    public abstract void visit(Invariant invariant);

    public abstract void visit(InvariantList invariantList);

    public abstract void visit(RequiresClause requiresClause);

    public abstract void visit(Condition condition);
}
