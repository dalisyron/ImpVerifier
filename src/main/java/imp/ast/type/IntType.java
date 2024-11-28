package imp.ast.type;

public record IntType() implements Type {
    @Override
    public String toString() {
        return "int";
    }
}
