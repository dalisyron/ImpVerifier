package imp.ast.statement;

import imp.ast.ASTVisitor;
import imp.ast.expression.Expression;
import imp.ast.InvariantList;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class WhileStatement extends Statement {
    @NotNull
    private final Expression condition;
    @NotNull
    private final InvariantList invariants;
    @NotNull
    private final BlockStatement body;


    public WhileStatement(@NotNull Expression condition, @NotNull InvariantList invariants, @NotNull BlockStatement body) {
        Objects.requireNonNull(condition, "Condition cannot be null");
        Objects.requireNonNull(invariants, "Invariants cannot be null");
        Objects.requireNonNull(body, "Body cannot be null");
        this.condition = condition;
        this.invariants = invariants;
        this.body = body;
    }


    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }

    @NotNull
    public Expression condition() {
        return condition;
    }

    @NotNull
    public InvariantList invariants() {
        return invariants;
    }

    @NotNull
    public BlockStatement body() {
        return body;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (WhileStatement) obj;
        return Objects.equals(this.condition, that.condition) &&
                Objects.equals(this.invariants, that.invariants) &&
                Objects.equals(this.body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition, invariants, body);
    }


}
