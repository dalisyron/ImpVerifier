package imp.verification;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import imp.ast.Invariant;
import imp.ast.expression.Expression;
import imp.ast.InvariantList;
import imp.ast.statement.BlockStatement;
import imp.ast.statement.WhileStatement;

import java.util.Optional;

public class While implements VerificationConditionProvider<WhileStatement> {
    private static While instance;

    private While() {
    }

    @Override
    public BoolExpr awp(Context ctx, WhileStatement whileStatement, BoolExpr Q) {
        Optional<InvariantList> invariants = whileStatement.invariants();

        return invariants.get().interpret(ctx);
    }

    @Override
    public BoolExpr avc(Context ctx, WhileStatement whileStatement, BoolExpr Q) {
        // $A V C\left(S^{\prime}, Q\right)=A V C(S, I) \wedge(I \wedge C \rightarrow a w p(S, I)) \wedge(I \wedge \neg C \rightarrow Q)$

        Optional<InvariantList> invariants = whileStatement.invariants();

        if (invariants.isEmpty()) {
            throw new RuntimeException("While statement does not have invariants");
        }

        BoolExpr I = invariants.orElseThrow().interpret(ctx);

        BlockStatement body = whileStatement.body();
        Expression condition = whileStatement.condition();
        BoolExpr condExpr = (BoolExpr) condition.interpret(ctx);

        BoolExpr A = AVC.getInstance().avc(ctx, body, I);
        BoolExpr B = ctx.mkImplies(ctx.mkAnd(I, condExpr), AWP.getInstance().awp(ctx, body, I));
        BoolExpr C = ctx.mkImplies(ctx.mkAnd(I, ctx.mkNot(condExpr)), Q);

        return ctx.mkAnd(A, B, C);
    }

    public static While getInstance() {
        if (instance == null) {
            instance = new While();
        }
        return instance;
    }
}