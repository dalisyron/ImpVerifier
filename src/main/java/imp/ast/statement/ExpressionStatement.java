package imp.ast.statement;

import imp.ast.expression.Expr;
import imp.ast.statement.Statement;

public record ExpressionStatement(Expr expr) implements Statement {

    @Override
    public String toString() {
        return expr.toString() + ";";
    }
}
