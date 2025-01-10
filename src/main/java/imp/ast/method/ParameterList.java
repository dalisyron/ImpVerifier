package imp.ast.method;

import imp.ast.ASTNode;
import imp.ast.ASTVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public record ParameterList(@NotNull List<Parameter> parameters) implements ASTNode {

    public ParameterList {
        // Ensure the list is not null and not empty
        Objects.requireNonNull(parameters, "parameters must not be null");
    }

    @Override
    public String toString() {
        // (p1, p2, p3, ..., pn)
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < parameters.size(); i++) {
            sb.append(parameters.get(i));
            if (i < parameters.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");

        return sb.toString();
    }

    public boolean isEmpty() {
        return parameters.isEmpty();
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }

    public static ParameterList emptyList() {
        return new ParameterList(List.of());
    }
}
