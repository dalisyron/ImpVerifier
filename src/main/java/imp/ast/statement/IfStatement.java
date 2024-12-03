package imp.ast.statement;

import imp.ast.ASTVisitor;
import imp.ast.expression.Expression;

import java.util.Objects;
import java.util.Optional;

public final class IfStatement implements Statement {

    private final Condition condition;
    private final BlockStatement thenBlock;
    private final Optional<BlockStatement> elseBlock;

    public IfStatement(Condition condition, BlockStatement thenBlock, Optional<BlockStatement> elseBlock) {
        this.condition = condition;
        this.thenBlock = thenBlock;
        this.elseBlock = elseBlock;
    }

    public Condition condition() {
        return condition;
    }

    public BlockStatement thenBlock() {
        return thenBlock;
    }

    public Optional<BlockStatement> elseBlock() {
        return elseBlock;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("if (").append(condition.toString()).append(") ");
        sb.append(thenBlock.toString());
        elseBlock.ifPresent(block -> sb.append(" else ").append(block));
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IfStatement that = (IfStatement) o;

        if (!Objects.equals(condition, that.condition)) return false;
        if (!Objects.equals(thenBlock, that.thenBlock)) return false;
        return Objects.equals(elseBlock, that.elseBlock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition, thenBlock, elseBlock);
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}
