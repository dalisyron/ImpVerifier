package imp.ast.expression;

public record Invariant(Expression expression) {

    @Override
    public String toString() {
        return "invariant " + expression;
    }
}
