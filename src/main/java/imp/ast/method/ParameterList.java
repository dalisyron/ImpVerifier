package imp.ast.method;

import java.util.List;
import java.util.Objects;

public record ParameterList(List<Parameter> parameters) {

    public ParameterList {
        // Ensure the list is not null and not empty
        Objects.requireNonNull(parameters, "parameters must not be null");
        if (parameters.isEmpty()) {
            throw new IllegalArgumentException("parameters must not be empty");
        }
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
}