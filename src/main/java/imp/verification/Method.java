package imp.verification;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Solver;
import com.microsoft.z3.Status;

import imp.ast.method.MethodDeclaration;
import imp.ast.condition.ConditionClause;
import imp.ast.condition.ConditionList;
import imp.ast.condition.EnsuresClause;
import imp.ast.condition.RequiresClause;
import imp.ast.statement.BlockStatement;
import imp.ast.statement.Statement;
import imp.interpreter.Z3ImpInterpreter;

import java.util.Optional;

public class Method {
    private static Method instance;
    
    private Method() {
        }
    public boolean Verify(Context ctx, MethodDeclaration method) {
        Optional<ConditionList> conditionals = method.conditionList();
        BoolExpr Q = ctx.mkTrue();
        BoolExpr P = ctx.mkTrue();

        if (conditionals.isEmpty()) {
            // TODO: Figure out how to handle empty invariants (wp / avc formula not available in slides)
            throw new RuntimeException("Method does not have invariants");
        }

        // Format conditions list to P and Q
        ConditionList conditionalsList = conditionals.get();

        for (int i = 0; i < conditionalsList.conditions().size(); i++ ) {
            ConditionClause condition = conditionalsList.conditions().get(i);
            if (condition instanceof EnsuresClause) {
                Q = ctx.mkAnd(Z3ImpInterpreter.convertConditional(ctx, condition.expr()));
            }
            else if (condition instanceof RequiresClause) {
                P = ctx.mkAnd(Z3ImpInterpreter.convertConditional(ctx, condition.expr()));
            }
        }
        
        BlockStatement statements = new BlockStatement(method.methodBody().statements());
        BoolExpr  avc = AVC.getInstance().avc(ctx, statements, Q);
        BoolExpr awp = AWP.getInstance().awp(ctx, statements, Q);

        Solver solver = ctx.mkSolver();
        solver.add(ctx.mkNot(ctx.mkAnd(ctx.mkImplies(P, awp), avc)));
        
        return solver.check() == Status.UNSATISFIABLE;
    }

    public static Method getInstance() {
        if (instance == null) {
            instance = new Method();
        }
        return instance;
    }
}