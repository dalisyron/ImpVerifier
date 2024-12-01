package imp.ast.typing;

import imp.ast.expression.type.Type;

import java.util.List;

public final class FunctionType extends imp.ast.typing.Type {

    private final List<Type> parameterTypes;
    private final Type returnType;

    public FunctionType(List<Type> parameterTypes, Type returnType) {
        this.parameterTypes = parameterTypes;
        this.returnType = returnType;
    }

    public List<Type> getParameterTypes() {
        return parameterTypes;
    }

    public Type getReturnType() {
        return returnType;
    }
}
