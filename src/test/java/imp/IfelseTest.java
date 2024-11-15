package imp;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.IntExpr;

import imp.ast.Statement;
import imp.parser.Parser;

import org.junit.Assert;
import org.junit.Test;

public class IfelseTest {
        @Test
    public void testParseIfStatement() {
        String program = "if x <= 5 then y := 1 else y := y + 2";
        Statement ast = Parser.parseString(program);

        // Compare ASTs
        Context ctx = new Context();
        IntExpr y = ctx.mkIntConst("y");
        BoolExpr Q = ctx.mkGt(y, ctx.mkInt(0));
        // Q = {y > 0}
        // expected output for awp {(x <= 5 ==> 1 > 0) AND (x < 5 ==> y + 2 > 0)}
        String expectedout = "(and (=> (<= x 5) (> 1 0)) (=> (not (<= x 5)) (> (+ y 2) 0)))";
        BoolExpr awpResult = Composition.eval(ctx, Q, ast).get(0);
        Assert.assertEquals(expectedout, awpResult.toString());
    }
}
