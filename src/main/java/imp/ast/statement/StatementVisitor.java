package imp.ast.statement;

import imp.ast.expression.ExpressionVisitor;

public interface StatementVisitor extends ExpressionVisitor {

    void visit(AssignStatement assignStatement);

    void visit(BlockStatement blockStatement);

    void visit(IfStatement ifStatement);

    void visit(VariableDeclaration variableDeclaration);

    void visit(WhileStatement whileStatement);

    void visit(FuncCallStatement funcCallStatement);
}
