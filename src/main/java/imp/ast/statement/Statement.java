
package imp.ast.statement;

import imp.ast.ASTNode;

public sealed interface Statement extends ASTNode permits AssignStatement, BlockStatement, ExpressionStatement, IfStatement, VariableDeclaration, WhileStatement {

    @Override
    String toString();
}
