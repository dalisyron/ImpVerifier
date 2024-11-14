package imp.ast.statement;

import imp.ast.Conditional;
import imp.ast.Statement;

public class WhileStmt implements Statement {
    private final Conditional condition;
    private final Statement body;

    public WhileStmt(Conditional condition, Statement body) {
        this.condition = condition;
        this.body = body;
    }

    public Conditional getCondition() {
        return condition;
    }

    public Statement getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "while " + condition.toString() + " do " + body.toString();
    }
}
