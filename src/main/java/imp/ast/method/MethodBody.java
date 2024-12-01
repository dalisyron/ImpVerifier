package imp.ast.method;

import imp.ast.ASTNode;
import imp.ast.statement.Statement;

import java.util.List;

/**
 *  Syntactically similar to {@link imp.ast.statement.BlockStatement} but not implementing {@link imp.ast.statement.Statement}.
 */
public record MethodBody(List<Statement> statements) implements ASTNode {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Statement statement : statements) {
            sb.append(statement.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.copyOf(statements);
    }
}
