package imp.ast.condition;

import imp.ast.ASTNode;
import imp.ast.ASTVisitor;
import imp.ast.statement.Condition;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public record ConditionList(@NotNull List<ConditionClause> conditions) implements ASTNode {

    public ConditionList {
        // Ensure the list is not null and not empty
        Objects.requireNonNull(conditions, "conditions must not be null");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < conditions.size(); i++) {
            sb.append(conditions.get(i));
            if (i < conditions.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
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
}
