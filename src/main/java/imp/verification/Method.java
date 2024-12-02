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

            BoolExpr QPrime = Q;
            BoolExpr avc = ctx.mkTrue();
            //reverse order avc generation
            for (int i = method.methodBody().statements().size() - 1; i >= 0 ; i--) {
                Statement statement = method.methodBody().statements().get(i);
                avc = ctx.mkAnd(avc, AVC.getInstance().avc(ctx, statement, QPrime));
                QPrime = AWP.getInstance().awp(ctx, statement, QPrime);
                String s1 = avc.toString();
                String qp = QPrime.toString();
            }

        Solver solver = ctx.mkSolver();
        solver.add(ctx.mkNot(ctx.mkAnd(ctx.mkImplies(P, QPrime), avc)));
        String s = solver.toString();
        System.err.println(P);
        System.err.println(Q);
        System.err.println(QPrime);
        System.err.println(avc);
        return solver.check() == Status.UNSATISFIABLE;
    }

    public static Method getInstance() {
        if (instance == null) {
            instance = new Method();
        }
        return instance;
    }
}