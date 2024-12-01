package imp.ast.condition;

import imp.ast.ASTNode;
import imp.ast.ASTVisitor;

import java.util.List;
import java.util.Objects;

public final class ConditionList implements ASTNode {

    private final List<ConditionClause> conditions;

    public ConditionList(List<ConditionClause> conditions) {
        // Ensure the list is not null and not empty
        Objects.requireNonNull(conditions, "conditions must not be null");
        if (conditions.isEmpty()) {
            throw new IllegalArgumentException("conditions must not be empty");
        }
        this.conditions = conditions;
    }

    public List<ConditionClause> conditions() {
        return conditions;
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

    public boolean hasEnsuresClause() {
        return conditions.stream().anyMatch(c -> c instanceof EnsuresClause);
    }

    public boolean hasRequiresClause() {
        return conditions.stream().anyMatch(c -> c instanceof RequiresClause);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConditionList that = (ConditionList) o;

        return Objects.equals(conditions, that.conditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conditions);
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}
