package imp.ast.statement;

import imp.ast.expression.Expression;
import imp.ast.InvariantList;

import java.util.Objects;
import java.util.Optional;

public final class WhileStatement implements Statement {

    private final Expression condition;
    private final Optional<InvariantList> invariants;
    private final BlockStatement body;

    public WhileStatement(Expression condition, Optional<InvariantList> invariants, BlockStatement body) {
        this.condition = condition;
        this.invariants = invariants;
        this.body = body;
    }

    public Expression condition() {
        return condition;
    }

    public Optional<InvariantList> invariants() {
        return invariants;
    }

    public BlockStatement body() {
        return body;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("while (").append(condition).append(")");
        invariants.ifPresent(invList -> sb.append("\n").append(invList));
        sb.append(" ").append(body);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WhileStatement that = (WhileStatement) o;

        if (!Objects.equals(condition, that.condition)) return false;
        if (!Objects.equals(invariants, that.invariants)) return false;
        return Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition, invariants, body);
    }
}
