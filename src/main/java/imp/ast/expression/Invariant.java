package imp.ast.expression;

import imp.ast.expression.bool.Expr;

public record Invariant(Expr expr) {

    @Override
    public String toString() {
        return "invariant " + expr + ";";
    }
}
