package imp.interpreter;

import com.microsoft.z3.*;
import imp.ast.*;
import imp.ast.conditional.*;
import imp.ast.expression.*;
import org.junit.Assert;
import org.junit.Test;


public class Z3ImpInterpreterTest {

    @Test
    public void testConvertTrueCond() {
        Context ctx = new Context();
        Conditional cond = TrueCond.INSTANCE;
        BoolExpr expr = Z3ImpInterpreter.convertConditional(ctx, cond);
        Assert.assertTrue(expr.isTrue());
    }

    @Test
    public void testConvertFalseCond() {
        Context ctx = new Context();
        Conditional cond = FalseCond.INSTANCE;
        BoolExpr expr = Z3ImpInterpreter.convertConditional(ctx, cond);
        Assert.assertTrue(expr.isFalse());
    }

    @Test
    public void testConvertEqualCond() {
        Context ctx = new Context();
        Expression left = new VariableExpr("x");
        Expression right = new IntegerExpr(5);
        Conditional cond = new EqualCond(left, right);
        BoolExpr expr = Z3ImpInterpreter.convertConditional(ctx, cond);

        // Check that expr is equivalent to x == 5
        BoolExpr xEq5 = ctx.mkEq(ctx.mkIntConst("x"), ctx.mkInt(5));
        BoolExpr equivalence = ctx.mkIff(expr, xEq5);
        Solver solver = ctx.mkSolver();
        solver.add(ctx.mkNot(equivalence)); // Negate the equivalence
        Status status = solver.check();
        Assert.assertEquals(Status.UNSATISFIABLE, status); // Should be UNSAT because expr <-> x == 5 is valid
    }

    @Test
    public void testConvertLeqCond() {
        Context ctx = new Context();
        Expression left = new VariableExpr("x");
        Expression right = new IntegerExpr(5);
        Conditional cond = new LeqCond(left, right);
        BoolExpr expr = Z3ImpInterpreter.convertConditional(ctx, cond);

        // Check that expr is equivalent to x <= 5
        BoolExpr xLeq5 = ctx.mkLe(ctx.mkIntConst("x"), ctx.mkInt(5));
        BoolExpr equivalence = ctx.mkIff(expr, xLeq5);
        Solver solver = ctx.mkSolver();
        solver.add(ctx.mkNot(equivalence)); // Negate the equivalence
        Status status = solver.check();
        Assert.assertEquals(Status.UNSATISFIABLE, status); // Should be UNSAT because expr <-> x <= 5 is valid
    }

    @Test
    public void testConvertConditionalWithExpressions() {
        Context ctx = new Context();
        // Conditional: (x + 3) * y <= 20
        Conditional cond = new LeqCond(
                new MulExpr(
                        new ParenExpr(
                                new AddExpr(
                                        new VariableExpr("x"),
                                        new IntegerExpr(3)
                                )
                        ),
                        new VariableExpr("y")
                ),
                new IntegerExpr(20)
        );
        BoolExpr expr = Z3ImpInterpreter.convertConditional(ctx, cond);

        // Build expected Z3 expression: (x + 3) * y <= 20
        ArithExpr x = ctx.mkIntConst("x");
        ArithExpr y = ctx.mkIntConst("y");
        ArithExpr lhs = ctx.mkMul(ctx.mkAdd(x, ctx.mkInt(3)), y);
        BoolExpr expectedExpr = ctx.mkLe(lhs, ctx.mkInt(20));

        // Check that expr is equivalent to expectedExpr
        BoolExpr equivalence = ctx.mkIff(expr, expectedExpr);
        Solver solver = ctx.mkSolver();
        solver.add(ctx.mkNot(equivalence)); // Negate the equivalence
        Status status = solver.check();
        Assert.assertEquals(Status.UNSATISFIABLE, status); // UNSAT because expressions are equivalent
    }
}