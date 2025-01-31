package imp.ast.method;

import imp.ast.ASTNode;
import imp.ast.ASTVisitor;
import imp.ast.condition.ConditionList;
import imp.ast.expression.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class MethodDeclaration extends ASTNode {
    @NotNull
    private final Identifier name;
    @NotNull
    private final ParameterList parameterList;
    @Nullable
    private final ReturnValue returnValue;
    @NotNull
    private final ConditionList conditionList;
    @NotNull
    private final MethodBody methodBody;

    public MethodDeclaration(@NotNull Identifier name,
                             @NotNull ParameterList parameterList,
                             @Nullable ReturnValue returnValue,
                             @NotNull ConditionList conditionList,
                             @NotNull MethodBody methodBody) {
        this.name = name;
        this.parameterList = parameterList;
        this.returnValue = returnValue;
        this.conditionList = conditionList;
        this.methodBody = methodBody;
    }


    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }

    @NotNull
    public Identifier name() {
        return name;
    }

    @NotNull
    public ParameterList parameterList() {
        return parameterList;
    }

    @Nullable
    public ReturnValue returnValue() {
        return returnValue;
    }

    @NotNull
    public ConditionList conditionList() {
        return conditionList;
    }

    @NotNull
    public MethodBody methodBody() {
        return methodBody;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (MethodDeclaration) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.parameterList, that.parameterList) &&
                Objects.equals(this.returnValue, that.returnValue) &&
                Objects.equals(this.conditionList, that.conditionList) &&
                Objects.equals(this.methodBody, that.methodBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parameterList, returnValue, conditionList, methodBody);
    }


}
