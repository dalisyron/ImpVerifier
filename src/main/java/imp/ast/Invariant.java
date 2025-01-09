package imp.ast;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import imp.ast.ASTNode;
import imp.ast.expression.Expression;
import imp.interpreter.expr.Z3BoolExprInterpreter;

import java.util.Objects;

public record Invariant(Expression expression) implements ASTNode {

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
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}
