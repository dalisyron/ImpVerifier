package imp.ast.method;

import imp.ast.ASTNode;
import imp.ast.ASTVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public final class ParameterList extends ASTNode {
    @NotNull
    private final List<Parameter> parameters;


    public ParameterList(@NotNull List<Parameter> parameters) {
        // Ensure the list is not null and not empty
        Objects.requireNonNull(parameters, "parameters must not be null");
        this.parameters = parameters;
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

    @NotNull
    public List<Parameter> parameters() {
        return parameters;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ParameterList) obj;
        return Objects.equals(this.parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameters);
    }


}
