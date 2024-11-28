package imp.ast.type;

public record ArrayType(Type elementType) implements Type {
    @Override
    public String toString() {
        return elementType + "[]";
    }
}