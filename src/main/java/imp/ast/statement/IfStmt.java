package imp.ast.statement;

import imp.ast.Conditional;
import imp.ast.Statement;

public class IfStmt implements Statement {
    private final Conditional condition;
    private final Statement thenBranch;
    private final Statement elseBranch;

    public IfStmt(Conditional condition, Statement thenBranch, Statement elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    public Conditional getCondition() {
        return condition;
    }

    public Statement getThenBranch() {
        return thenBranch;
    }

    public Statement getElseBranch() {
        return elseBranch;
    }

    @Override
    public String toString() {
        return "if " + condition.toString() + " then " + thenBranch.toString() + " else " + elseBranch.toString();
    }
}
