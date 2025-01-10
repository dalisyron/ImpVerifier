package imp.ast.method;

import imp.ast.ASTNode;
import imp.ast.ASTVisitor;
import imp.ast.condition.ConditionList;
import imp.ast.expression.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Objects;

public record MethodDeclaration(@NotNull Identifier name,
                                @NotNull ParameterList parameterList,
                                @Nullable ReturnValue returnValue,
                                @NotNull ConditionList conditionList,
                                @NotNull MethodBody methodBody) implements ASTNode {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Append the method signature
        sb.append("method ").append(name);

        // Ensure brackets for the parameter list are always present
        if (!parameterList.isEmpty()) {
            sb.append(parameterList);
        } else {
            sb.append("()");
        }

        // Add return value if present
        if (returnValue != null) {
            sb.append(" returns (").append(returnValue).append(")");
        }

        // Add conditions (e.g., requires/ensures) on new lines
        if (!conditionList.isEmpty()) {
            sb.append(System.lineSeparator()).append(conditionList);
        }

        // Append the method body
        sb.append(" {").append(System.lineSeparator());
        sb.append(methodBody).append(System.lineSeparator());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}
