package imp.verification;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import imp.ast.statement.BlockStatement;
import imp.ast.statement.Statement;

public class Composition implements VerificationConditionProvider<BlockStatement> {

    public BoolExpr awp(Context ctx, BlockStatement block, BoolExpr Q) {
        if (block.statements().isEmpty()) {
            return Q;
        }

        if (block.statements().size() == 1) {
            Statement statement = block.getHead();
            return AWP.awp(ctx, statement, Q);
        }

        Statement headStatement = block.getHead();
        BlockStatement tailStatements = block.getTail();

        BoolExpr restAWP = awp(ctx, tailStatements, Q);

        return AWP.awp(ctx, headStatement, restAWP);
    }

    public BoolExpr avc(Context ctx, BlockStatement block, BoolExpr Q) {
        if (block.statements().isEmpty()) {
            return ctx.mkTrue();
        }

        if (block.statements().size() == 1) {
            Statement statement = block.getHead();
            return AVC.getInstance().avc(ctx, statement, Q);
        }

        Statement headStatement = block.getHead();
        BlockStatement tailStatements = block.getTail();

        BoolExpr A, B;

        A = avc(ctx, tailStatements, Q);
        B = AVC.getInstance().avc(ctx, headStatement, awp(ctx, tailStatements, Q));

        return ctx.mkAnd(A, B);
    }
}