package imp.ast.statement;

import imp.ast.ASTVisitor;
import imp.ast.expression.Expression;
import imp.ast.InvariantList;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record WhileStatement(@NotNull Expression condition, @NotNull InvariantList invariants, @NotNull BlockStatement body) implements Statement {

    public WhileStatement {
        Objects.requireNonNull(condition, "Condition cannot be null");
        Objects.requireNonNull(invariants, "Invariants cannot be null");
        Objects.requireNonNull(body, "Body cannot be null");
    }

    @Override
    public String toString() {
        return "while (" + condition + ")" +
                invariants +
                " " + body;
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}
