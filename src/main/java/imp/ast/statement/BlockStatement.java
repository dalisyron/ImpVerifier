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

    public Statement getHead() {
        return statements.get(0);
    }

    public BlockStatement getTail() {
        if (statements.isEmpty()) {
            throw new IllegalStateException("Cannot get tail of empty block");
        }

        if (statements.size() == 1) {
            return new BlockStatement(List.of());
        }
        return new BlockStatement(statements.subList(1, statements.size()));
    }
}