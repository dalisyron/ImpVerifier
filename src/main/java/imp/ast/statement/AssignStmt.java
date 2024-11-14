package imp.ast.statement;

import imp.ast.Expression;
import imp.ast.Statement;

public class AssignStmt implements Statement {
    private final String variable;
    private final Expression expression;

    public AssignStmt(String variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    public String getVariable() {
        return variable;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return variable + " := " + expression.toString();
    }
}
