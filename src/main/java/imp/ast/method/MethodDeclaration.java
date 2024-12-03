package imp.ast.method;

import imp.ast.ASTNode;
import imp.ast.ASTVisitor;
import imp.ast.condition.ConditionList;
import imp.ast.expression.Identifier;

import java.util.Optional;
import java.util.Objects;

public final class MethodDeclaration implements ASTNode {

    private final Identifier name;
    private final Optional<ParameterList> parameterList;
    private final Optional<ReturnValue> returnValue;
    private final Optional<ConditionList> conditionList;
    private final MethodBody methodBody;

    public MethodDeclaration(
            Identifier name,
            Optional<ParameterList> parameterList,
            Optional<ReturnValue> returnValue,
            Optional<ConditionList> conditionList,
            MethodBody methodBody
    ) {
        this.name = name;
        this.parameterList = parameterList;
        this.returnValue = returnValue;
        this.conditionList = conditionList;
        this.methodBody = methodBody;
    }

    public Identifier name() {
        return name;
    }

    public Optional<ParameterList> parameterList() {
        return parameterList;
    }

    public Optional<ReturnValue> returnValue() {
        return returnValue;
    }

    public Optional<ConditionList> conditionList() {
        return conditionList;
    }

    public MethodBody methodBody() {
        return methodBody;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodDeclaration that = (MethodDeclaration) o;

        return Objects.equals(name, that.name) &&
                Objects.equals(parameterList, that.parameterList) &&
                Objects.equals(returnValue, that.returnValue) &&
                Objects.equals(conditionList, that.conditionList) &&
                Objects.equals(methodBody, that.methodBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parameterList, returnValue, conditionList, methodBody);
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}
