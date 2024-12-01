package imp.ast.statement;

import imp.ast.expression.Expression;

import java.util.Objects;

public final class ExpressionStatement implements Statement {

    private final Expression expression;

    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }

    public Expression expression() {
        return expression;
    }

    @Override
    public String toString() {
        return expression.toString() + ";";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpressionStatement that = (ExpressionStatement) o;

        return Objects.equals(expression, that.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression);
    }
}
