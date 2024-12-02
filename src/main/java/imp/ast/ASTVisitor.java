package imp.ast;

import imp.ast.condition.ConditionList;
import imp.ast.condition.EnsuresClause;
import imp.ast.condition.RequiresClause;
import imp.ast.expression.*;
import imp.ast.expression.array.NewArrayExpression;
import imp.ast.expression.bool.ExistsExpression;
import imp.ast.expression.bool.ForallExpression;
import imp.ast.expression.constant.bool.FalseExpression;
import imp.ast.expression.constant.bool.TrueExpression;
import imp.ast.expression.constant.integer.IntExpression;
import imp.ast.method.*;
import imp.ast.statement.*;
import imp.ast.variable.Identifier;

public abstract class ASTVisitor {

    public abstract void visit(BinaryOpExpression binaryOpExpression);

    public abstract void visit(ArrayRefExpression arrayRefExpression);

    public abstract void visit(VarRefExpression varRefExpression);

    public abstract void visit(ForallExpression forallExpression);

    public abstract void visit(ExistsExpression existsExpression);

    public abstract void visit(Identifier identifier);

    public abstract void visit(Program program);

    public abstract void visit(MethodDeclaration methodDeclaration);

    public abstract void visit(ParameterList parameterList);

    public abstract void visit(Parameter parameter);

    public abstract void visit(ReturnValue returnValue);

    public abstract void visit(ConditionList conditionList);

    public abstract void visit(EnsuresClause ensuresClause);

    public abstract void visit(UnaryExpression unaryExpression);

    public abstract void visit(ArrayLengthExpression arrayLengthExpression);

    public abstract void visit(FuncCallExpression funcCallExpression);

    public abstract void visit(MethodBody methodBody);

    public abstract void visit(AssignStatement assignStatement);

    public abstract void visit(BlockStatement blockStatement);

    public abstract void visit(ExpressionStatement expressionStatement);

    public abstract void visit(IfStatement ifStatement);

    public abstract void visit(VariableDeclaration variableDeclaration);

    public abstract void visit(WhileStatement whileStatement);

    public abstract void visit(Invariant invariant);

    public abstract void visit(InvariantList invariantList);

    public abstract void visit(FalseExpression falseExpression);

    public abstract void visit(IntExpression intExpression);

    public abstract void visit(RequiresClause requiresClause);

    public abstract void visit(NewArrayExpression newArrayExpression);

    public abstract void visit(TrueExpression trueExpression);
}
