package imp.ast.statement;

import imp.ast.expression.Expr;
import imp.ast.expression.InvariantList;
import imp.ast.statement.BlockStatement;
import imp.ast.statement.Statement;

import java.util.Optional;

public record WhileStatement(Expr condition, Optional<InvariantList> invariants, BlockStatement body) implements Statement {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("while (").append(condition).append(")");
        invariants.ifPresent(invList -> sb.append("\n").append(invList));
        sb.append(" ").append(body);
        return sb.toString();
    }
}