package imp.ast.expression;

import imp.ast.Expression;

public record VariableExpr(String name) implements Expression {

    @Override
    public String toString() {
        return name;
    }
}
