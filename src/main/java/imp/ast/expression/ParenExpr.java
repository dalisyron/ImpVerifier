package imp.ast.expression;

import imp.ast.Expression;

public record ParenExpr(Expression inner) implements Expression {

    @Override
    public String toString() {
        return "(" + inner.toString() + ")";
    }
}
