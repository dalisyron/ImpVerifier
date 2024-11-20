package imp.ast.statement;

import imp.ast.Conditional;
import imp.ast.Statement;

public class WhileStmt implements Statement {
    private final Conditional condition;
    private final Conditional invariant;
    private final Statement body;

    public WhileStmt(Conditional condition, Conditional invariant, Statement body) {
        this.condition = condition;
        this.invariant = invariant;
        this.body = body;
    }

    public Conditional getCondition() {
        return condition;
    }

    public Statement getBody() {
        return body;
    }

    public Conditional getInvariant() {
        return invariant;
    }

    @Override
    public String toString() {
        return "while " + condition.toString() + "invariant " + invariant.toString() + " do " + body.toString();
    }
}
