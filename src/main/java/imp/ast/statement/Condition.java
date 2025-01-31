package imp.ast.statement;

import imp.ast.ASTNode;
import imp.ast.ASTVisitor;
import imp.ast.expression.Expression;

public final class Condition extends ASTNode {
    private final Expression expression;

    public Condition(Expression expression) {
        this.expression = expression;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Condition that) {
            return this.expression.equals(that.expression);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return expression.hashCode();
    }

    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public Expression expression() {
        return expression;
    }
}
