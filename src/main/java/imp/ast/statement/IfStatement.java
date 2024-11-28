
package imp.ast.statement;

import imp.ast.expression.Expr;

import java.util.Optional;

public record IfStatement(Expr condition, BlockStatement thenBlock, Optional<BlockStatement> elseBlock) implements Statement {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("if (").append(condition.toString()).append(") ");
        sb.append(thenBlock.toString());
        elseBlock.ifPresent(block -> sb.append(" else ").append(block));
        return sb.toString();
    }
}
