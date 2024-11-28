package imp.ast.condition;

import imp.ast.expression.bool.Expr;

public record RequiresClause(Expr expr) implements ConditionClause {

    @Override
    public String toString() {
        return "requires " + expr.toString();
    }
}