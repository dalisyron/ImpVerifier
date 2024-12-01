package imp.ast.statement;

import imp.ast.expression.Expression;
import imp.ast.InvariantList;

import java.util.Optional;

public record WhileStatement(Expression condition, Optional<InvariantList> invariants, BlockStatement body) implements Statement {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("while (").append(condition).append(")");
        invariants.ifPresent(invList -> sb.append("\n").append(invList));
        sb.append(" ").append(body);
        return sb.toString();
    }
}