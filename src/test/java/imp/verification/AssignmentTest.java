package imp.verification;

import imp.ast.expression.Identifier;
import imp.ast.typing.data.value.IntType;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.IntExpr;
import com.microsoft.z3.Solver;
import com.microsoft.z3.Status;

import imp.ast.expression.VarRefExpression;
import imp.ast.expression.constant.integer.IntExpression;
import imp.ast.statement.AssignStatement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import verification.generator.Assignment;


public class AssignmentTest {

    @Test
    public void AssignIntTest() {
        Context ctx = new Context();
        VarRefExpression lhs = new VarRefExpression(new Identifier("x"));
        lhs.setType(IntType.getInstance());
        AssignStatement statement = new AssignStatement(lhs, new IntExpression(1));
        IntExpr x = ctx.mkIntConst("x");
        BoolExpr Q = ctx.mkEq(x, ctx.mkInt(1));
        BoolExpr awp = Assignment.getInstance().awp(ctx, statement, Q);
        BoolExpr avc = Assignment.getInstance().avc(ctx, statement, Q);
        Solver solver = ctx.mkSolver();
        solver.add(ctx.mkNot(ctx.mkAnd(awp, avc)));
        Status status = solver.check();
        Assertions.assertEquals(Status.UNSATISFIABLE, status);
    }

}
