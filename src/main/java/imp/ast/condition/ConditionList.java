package imp.ast.condition;

import imp.ast.ASTNode;
import imp.ast.ASTVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public final class ConditionList extends ASTNode {
    @NotNull
    private final List<ConditionClause> conditions;


    public ConditionList(@NotNull List<ConditionClause> conditions) {
        // Ensure the list is not null and not empty
        Objects.requireNonNull(conditions, "conditions must not be null");
        this.conditions = conditions;
    }


    public List<EnsuresClause> ensuresClauses() {
        return conditions.stream()
                .filter(c -> c instanceof EnsuresClause)
                .map(c -> (EnsuresClause) c)
                .toList();
    }

    public List<RequiresClause> requiresClauses() {
        return conditions.stream()
                .filter(c -> c instanceof RequiresClause)
                .map(c -> (RequiresClause) c)
                .toList();
    }

    public boolean isEmpty() {
        return conditions.isEmpty();
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }

    public static ConditionList emptyList() {
        return new ConditionList(List.of());
    }

    @NotNull
    public List<ConditionClause> conditions() {
        return conditions;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ConditionList) obj;
        return Objects.equals(this.conditions, that.conditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conditions);
    }


}
