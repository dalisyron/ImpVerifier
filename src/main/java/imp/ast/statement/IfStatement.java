
package imp.ast.statement;

import imp.ast.expression.Expression;

import java.util.Optional;

public record IfStatement(Expression condition, BlockStatement thenBlock, Optional<BlockStatement> elseBlock) implements Statement {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("if (").append(condition.toString()).append(") ");
        sb.append(thenBlock.toString());
        elseBlock.ifPresent(block -> sb.append(" else ").append(block));
        return sb.toString();
    }
}
