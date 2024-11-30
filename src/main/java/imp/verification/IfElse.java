package imp.verification;

import java.util.Optional;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import imp.ast.expression.Expression;
import imp.ast.statement.BlockStatement;
import imp.ast.statement.IfStatement;
import imp.interpreter.Z3ImpInterpreter;

public class IfElse implements VerificationConditionProvider<IfStatement> {
    private static IfElse instance;

    private IfElse() {
    }

    // arguments the AST for an ifelse statement are post condition Q
    // first element returned is Q, the remainder is the AVC of the ifelse statement
    @Override
    public BoolExpr awp(Context ctx, IfStatement ast, BoolExpr Q) {
        BlockStatement thenBlock = ast.thenBlock();
        Optional<BlockStatement> elseBlock = ast.elseBlock();
        // get statement 1 and 2 and condition from AST when its done
        Expression conditionExpression = ast.condition();
        BoolExpr condition = Z3ImpInterpreter.convertConditional(ctx, conditionExpression);

        BoolExpr A = AWP.awp(ctx, thenBlock, Q);
        BoolExpr B;
        if (elseBlock.isPresent()) {
            B = AWP.awp(ctx, elseBlock.get(), Q);
        } else {
            B = Q;
        }

        A = ctx.mkImplies(condition, A);
        B = ctx.mkImplies(ctx.mkNot(condition), B);
        return ctx.mkAnd(A, B);
    }

    @Override
    public BoolExpr avc(Context ctx, IfStatement ast, BoolExpr Q) {
        // get statement 1 and 2 and condition from AST when its done
        BlockStatement statement1 = ast.thenBlock();
        Optional<BlockStatement> statement2 = ast.elseBlock();

        BoolExpr A, B;

        A = AVC.getInstance().avc(ctx, statement1, Q);
        if (statement2.isPresent()) {
            B = AVC.getInstance().avc(ctx, statement2.get(), Q);
        } else {
            B = ctx.mkTrue();
        }
        return ctx.mkAnd(A, B);
    }

    public static IfElse getInstance() {
        if (instance == null) {
            instance = new IfElse();
        }
        return instance;
    }
}