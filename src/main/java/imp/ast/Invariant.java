package imp.ast;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import imp.ast.ASTNode;
import imp.ast.expression.Expression;
import imp.interpreter.expr.Z3BoolExprInterpreter;

import java.util.Objects;

public final class Invariant implements ASTNode {

    private final Expression expression;

    public Invariant(Expression expression) {
        this.expression = expression;
    }

    public Expression expression() {
        return expression;
    }

    @Override
    public String toString() {
        return "invariant " + expression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invariant invariant = (Invariant) o;

        return Objects.equals(expression, invariant.expression);
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
