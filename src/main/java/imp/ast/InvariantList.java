package imp.ast;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import imp.ast.ASTNode;
import imp.ast.Invariant;
import imp.ast.expression.Expression;
import imp.interpreter.expr.Z3BoolExprInterpreter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public record InvariantList(@NotNull List<Invariant> invariants) implements ASTNode, Z3BoolExprInterpreter {

    public InvariantList {
        Objects.requireNonNull(invariants, "Invariants cannot be null");
    }

    @Override
    public List<Invariant> invariants() {
        return invariants;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Invariant invariant : invariants) {
            sb.append(invariant).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvariantList that = (InvariantList) o;

        return Objects.equals(invariants, that.invariants);
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }

    @Override
    public BoolExpr interpret(Context ctx) {
        BoolExpr I = ctx.mkTrue();
        for (Invariant invariant : invariants) {
            Expression invariantExpression = invariant.expression();
            I = ctx.mkAnd(I, (BoolExpr) invariantExpression.interpret(ctx));
        }
        return I;
    }
}
