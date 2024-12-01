package imp.ast.method;

import imp.ast.ASTNode;
import imp.ast.ASTVisitor;
import imp.ast.statement.Statement;

import java.util.List;
import java.util.Objects;

/**
 * Syntactically similar to {@link imp.ast.statement.BlockStatement} but not implementing {@link imp.ast.statement.Statement}.
 */
public final class MethodBody implements ASTNode {

    private final List<Statement> statements;

    public MethodBody(List<Statement> statements) {
        this.statements = statements;
    }

    public List<Statement> statements() {
        return statements;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodBody that = (MethodBody) o;

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
