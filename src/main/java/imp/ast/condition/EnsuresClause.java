package imp.ast.condition;

import imp.ast.expression.Expr;

public record EnsuresClause(Expr expr) implements ConditionClause {

    @Override
    public String toString() {
        return "ensures " + expr.toString();
    }
}
