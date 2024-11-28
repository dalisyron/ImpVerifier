package imp.ast.statement;

import imp.ast.expression.bool.Expr;

public record ExpressionStatement(Expr expr) implements Statement {

    @Override
    public String toString() {
        return expr.toString() + ";";
    }
}
