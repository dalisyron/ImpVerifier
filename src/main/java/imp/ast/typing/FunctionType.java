package imp.ast.typing;

import imp.ast.typing.data.DataType;

import java.util.List;

public final class FunctionType extends Type {

    private final List<DataType> parameterTypes;
    private final DataType returnType;

    public FunctionType(List<DataType> parameterTypes, DataType returnType) {
        this.parameterTypes = parameterTypes;
        this.returnType = returnType;
    }

    public List<DataType> getParameterTypes() {
        return parameterTypes;
    }

    public DataType getReturnType() {
        return returnType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FunctionType) {
            FunctionType other = (FunctionType) obj;
            return this.parameterTypes.equals(other.parameterTypes) && this.returnType.equals(other.returnType);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return parameterTypes.hashCode() + returnType.hashCode();
    }

    @Override
    public String toString() {
        String parameterTypes = this.parameterTypes.stream().map(Type::toString).reduce("", (a, b) -> a + ", " + b);

        return "(" + parameterTypes + ") -> " + returnType;
    }

}
