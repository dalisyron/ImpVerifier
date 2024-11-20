package imp.ast.statement;

import imp.ast.Conditional;
import imp.ast.Statement;

public class PrecondStmt implements Statement {
    private final Conditional condition;

    public PrecondStmt(Conditional condition) {
        this.condition = condition;
    }

    public Conditional getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "precond " + condition.toString();
    }
}
