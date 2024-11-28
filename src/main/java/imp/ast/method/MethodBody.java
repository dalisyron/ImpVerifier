package imp.ast.method;

import imp.ast.statement.Statement;

import java.util.List;

/**
 *  Syntactically similar to {@link imp.ast.statement.BlockStatement} but not implementing {@link imp.ast.statement.Statement}.
 */
public record MethodBody(List<Statement> statements) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (Statement statement : statements) {
            sb.append(statement.toString());
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
