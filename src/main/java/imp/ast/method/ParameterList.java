package imp.ast.method;

import imp.ast.ASTNode;
import imp.ast.ASTVisitor;

import java.util.List;
import java.util.Objects;

public final class ParameterList implements ASTNode {

    private final List<Parameter> parameters;

    public ParameterList(List<Parameter> parameters) {
        // Ensure the list is not null and not empty
        Objects.requireNonNull(parameters, "parameters must not be null");
        if (parameters.isEmpty()) {
            throw new IllegalArgumentException("parameters must not be empty");
        }
        this.parameters = parameters;
    }

    public List<Parameter> parameters() {
        return parameters;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParameterList that = (ParameterList) o;

        return Objects.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameters);
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}
