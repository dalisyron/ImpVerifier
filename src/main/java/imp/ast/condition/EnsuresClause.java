package imp.ast.condition;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import imp.ast.ASTVisitor;
import imp.ast.expression.Expression;

import java.util.Objects;

public final class EnsuresClause extends ConditionClause {

    private final Expression expression;

    public EnsuresClause(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Expression expr() {
        return expression;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnsuresClause that = (EnsuresClause) o;

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
