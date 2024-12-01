package imp.ast.typing;

public class ArrayType extends Type {
    private final Type elementType;

    public ArrayType(Type elementType) {
        this.elementType = elementType;
    }

    public Type getElementType() {
        return elementType;
    }
}