package imp.ast.condition;

import imp.ast.ASTVisitor;
import imp.ast.expression.Expression;

import java.util.Objects;

public final class RequiresClause implements ConditionClause {

    private final Expression expression;

    public RequiresClause(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Expression expr() {
        return expression;
    }

    @Override
    public String toString() {
        return "requires " + expression.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequiresClause that = (RequiresClause) o;

        return Objects.equals(expression, that.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression);
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}
