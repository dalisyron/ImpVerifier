package imp.ast.typing;

public class ArrayType extends Type {
    private final Type elementType;

    public ArrayType(Type elementType) {
        this.elementType = elementType;
    }

    public Type getElementType() {
        return elementType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ArrayType other) {
            return elementType.equals(other.elementType);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return elementType.hashCode();
    }

    @Override
    public String toString() {
        return elementType + "[]";
    }
}