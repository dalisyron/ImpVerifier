package imp.ast.statement;

import imp.ast.expression.Expr;
import imp.ast.expression.ReferenceExpr;

public record AssignStatement(ReferenceExpr lhs, Expr expr) implements Statement {

    @Override
    public String toString() {
        return lhs.toString() + " = " + expr.toString() + ";";
    }
}
