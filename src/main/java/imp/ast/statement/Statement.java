
package imp.ast.statement;

public sealed interface Statement permits AssignStatement, BlockStatement, ExpressionStatement, IfStatement, VariableDeclaration, imp.ast.statement.WhileStatement {

    @Override
    String toString();
}
