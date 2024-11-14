package imp.ast.expression;

import imp.ast.Expression;

public record IntegerExpr(int value) implements Expression {

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
