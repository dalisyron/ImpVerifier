package imp.verification;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import imp.ast.expression.Expression;
import imp.ast.expression.InvariantList;
import imp.ast.statement.BlockStatement;
import imp.ast.statement.WhileStatement;
import imp.interpreter.Z3ImpInterpreter;

import java.util.Optional;

public class While implements VerificationConditionProvider<WhileStatement> {

    @Override
    public BoolExpr awp(Context ctx, WhileStatement whileStatement, BoolExpr Q) {
        Optional<InvariantList> invariants = whileStatement.invariants();

        if (invariants.isEmpty()) {
            // TODO: Figure out how to handle empty invariants (wp / avc formula not available in slides)
            throw new RuntimeException("While statement does not have invariants");
        }

        InvariantList invariantList = invariants.get();

        if (invariantList.invariants().size() > 1) {
            throw new RuntimeException("While statement has only one invariant");
        }

        Expression invariant = invariantList.invariants().get(0).expression();

        BoolExpr I = Z3ImpInterpreter.convertConditional(ctx, invariant);

        return I;
    }

    @Override
    public BoolExpr avc(Context ctx, WhileStatement whileStatement, BoolExpr Q) {
        // $A V C\left(S^{\prime}, Q\right)=A V C(S, I) \wedge(I \wedge C \rightarrow a w p(S, I)) \wedge(I \wedge \neg C \rightarrow Q)$

        Optional<InvariantList> invariants = whileStatement.invariants();

        if (invariants.isEmpty()) {
            throw new RuntimeException("While statement does not have invariants");
        }

        InvariantList invariantList = invariants.get();

        if (invariantList.invariants().size() > 1) {
            throw new RuntimeException("While statement has only one invariant");
        }

        Expression invariant = invariantList.invariants().get(0).expression();
        BlockStatement body = whileStatement.body();
        Expression condition = whileStatement.condition();

        BoolExpr I = Z3ImpInterpreter.convertConditional(ctx, invariant);
        BoolExpr condExpr = Z3ImpInterpreter.convertConditional(ctx, condition);

        BoolExpr A = AVC.getInstance().avc(ctx, body, I);
        BoolExpr B = ctx.mkImplies(ctx.mkAnd(I, condExpr), AWP.awp(ctx, body, I));
        BoolExpr C = ctx.mkImplies(ctx.mkAnd(I, ctx.mkNot(condExpr)), Q);

        return ctx.mkAnd(A, B, C);
    }
}