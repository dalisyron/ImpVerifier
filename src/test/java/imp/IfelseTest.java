package imp;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.IntExpr;

import imp.ast.Statement;
import imp.parser.Parser;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class IfelseTest {
        @Test
    public void testParseIfStatement() {
        String program = "if x <= 5 then y := 1 else y := y + 2 done";
        Statement ast = Parser.parseString(program);

        // Compare ASTs
        Context ctx = new Context();
        IntExpr y = ctx.mkIntConst("y");
        BoolExpr Q = ctx.mkGt(y, ctx.mkInt(0));
        // Q = {y > 0}
        // expected output for awp {(x <= 5 ==> 1 > 0) AND (x < 5 ==> y + 2 > 0)}
        String expectedout = "(and (=> (<= x 5) (> 1 0)) (=> (not (<= x 5)) (> (+ y 2) 0)))";
        ArrayList<BoolExpr> ret = Ifelse.eval(ctx, Q, ast);
        BoolExpr awpResult = ret.get(0);
        Assert.assertEquals(expectedout, awpResult.toString());
        BoolExpr avcResult = ret.get(1);
        expectedout = "(and true true)";
        Assert.assertEquals(expectedout, avcResult.toString());
    }
}
