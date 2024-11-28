package imp.ast.statement;

import java.util.List;

/**
 *  Represents a block of statements enclosed in curly braces.
 *  For example:
 *  {
 *    x = 1;
 *    y = 2;
 *    z = x + y;
 *  }
 */
public record BlockStatement(List<Statement> statements) implements Statement {

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