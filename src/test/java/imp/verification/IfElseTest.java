package imp.verification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import imp.ast.typing.data.value.IntType;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.IntExpr;
import com.microsoft.z3.Solver;
import com.microsoft.z3.Status;

import imp.ast.expression.VarRefExpression;
import imp.ast.expression.binary.bool.compare.LessThanExpression;
import imp.ast.expression.constant.integer.IntExpression;
import imp.ast.statement.*;
import imp.ast.expression.Identifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class IfElseTest {
    @Test
    public void test() {

        VarRefExpression var_x = new VarRefExpression(new Identifier("x"));
        var_x.setType(IntType.getInstance());
        LessThanExpression condition = new LessThanExpression(var_x, new IntExpression(1));
        VarRefExpression var_y = new VarRefExpression(new Identifier("y"));
        var_y.setType(IntType.getInstance());
        List<Statement> thenList = new ArrayList<Statement>();
        List<Statement> elseList = new ArrayList<Statement>();
        thenList.add(new AssignStatement(var_y, new IntExpression(1)));
        elseList.add(new AssignStatement(var_y, new IntExpression(2)));
        BlockStatement thenBlock = new BlockStatement(thenList);
        BlockStatement elseBlock = new BlockStatement(elseList);
        IfStatement statement = new IfStatement(new Condition(condition), thenBlock, elseBlock);

        Context ctx = new Context();
        IntExpr x = ctx.mkIntConst("x");
        IntExpr y = ctx.mkIntConst("y");
        BoolExpr P1 = ctx.mkEq(x, ctx.mkInt(0));
        BoolExpr P2 = ctx.mkEq(x, ctx.mkInt(2));
        BoolExpr Q1 = ctx.mkEq(y, ctx.mkInt(1));
        BoolExpr Q2 = ctx.mkEq(y, ctx.mkInt(2));
        BoolExpr awp1 = IfElse.getInstance().awp(ctx, statement, Q1);
        BoolExpr avc1 = IfElse.getInstance().avc(ctx, statement, Q1);
        BoolExpr awp2 = IfElse.getInstance().awp(ctx, statement, Q2);
        BoolExpr avc2 = IfElse.getInstance().avc(ctx, statement, Q2);

        BoolExpr vc1 = ctx.mkAnd(avc1, ctx.mkImplies(P1, awp1));
        BoolExpr vc2 = ctx.mkAnd(avc2, ctx.mkImplies(P2, awp2));

        Solver solver = ctx.mkSolver();
        solver.add(ctx.mkNot(ctx.mkAnd(vc1, vc2)));
        Status status = solver.check();
        Assertions.assertEquals(Status.UNSATISFIABLE, status);
    }
}
