
package imp.ast.method;

import imp.ast.condition.ConditionList;
import imp.ast.statement.Statement;
import imp.ast.variable.Identifier;

import java.util.List;
import java.util.Optional;

public record MethodDeclaration(
        Identifier name,
        Optional<ParameterList> parameterList,
        Optional<ReturnValue> returnValue,
        Optional<ConditionList> conditionList,
        MethodBody methodBody
) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Append the method signature
        sb.append("method ").append(name);

        // Ensure brackets for the parameter list are always present
        if (parameterList.isPresent()) {
            sb.append(parameterList.get());
        } else {
            sb.append("()");
        }

        // Add return value if present
        returnValue.ifPresent(value -> sb.append(" returns (").append(value).append(")"));

        // Add conditions (e.g., requires/ensures) on new lines
        conditionList.ifPresent(conditions -> sb.append(System.lineSeparator()).append(conditions));

        // Append the method body
        sb.append(" {").append(System.lineSeparator());
        sb.append(methodBody).append(System.lineSeparator());
        sb.append("}");

        return sb.toString();
    }
}
