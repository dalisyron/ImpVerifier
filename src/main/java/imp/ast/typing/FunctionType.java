package imp.ast.typing;

import java.util.List;

public final class FunctionType extends Type {

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

    @Override
    public String toString() {
        String parameterTypes = this.parameterTypes.stream().map(Type::toString).reduce("", (a, b) -> a + ", " + b);

        return "(" + parameterTypes + ") -> " + returnType;
    }
}
