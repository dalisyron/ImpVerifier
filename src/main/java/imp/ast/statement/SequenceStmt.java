package imp.ast.statement;

import imp.ast.Statement;

public class SequenceStmt implements Statement {
    private final Statement first;
    private final Statement second;

    public SequenceStmt(Statement first, Statement second) {
        this.first = first;
        this.second = second;
    }

    public Statement getFirst() {
        return first;
    }

    public Statement getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return first.toString() + ";\n" + second.toString();
    }
}
