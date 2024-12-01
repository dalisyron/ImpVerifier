package imp.ast.statement;

import imp.ast.ASTNode;
import imp.ast.ASTVisitor;

import java.util.List;
import java.util.Objects;

/**
 * Represents a block of statements enclosed in curly braces.
 * For example:
 * {
 *   x = 1;
 *   y = 2;
 *   z = x + y;
 * }
 */
public final class BlockStatement implements Statement {

    private final List<Statement> statements;

    public BlockStatement(List<Statement> statements) {
        this.statements = statements;
    }

    public List<Statement> statements() {
        return statements;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockStatement that = (BlockStatement) o;

        return Objects.equals(statements, that.statements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statements);
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}
