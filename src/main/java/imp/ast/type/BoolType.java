package imp.ast.type;

public record BoolType() implements Type {
    @Override
    public String toString() {
        return "bool";
    }
}
