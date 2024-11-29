package imp.ast.expression;

public record Invariant(Expr expr) {

    @Override
    public String toString() {
        return "invariant " + expr + ";";
    }
}
