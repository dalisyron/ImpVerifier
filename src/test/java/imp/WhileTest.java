package imp;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.IntExpr;

import imp.ast.Statement;
import imp.parser.Parser;

public class WhileTest {
    @Test
    public void testParseWhileStatement() {
        String program = "while x <= 5 invariant x <= 6 do begin x := x+1 end";
        Statement ast = Parser.parseString(program);

        // Compare ASTs
        Context ctx = new Context();
        IntExpr x = ctx.mkIntConst("x");
        BoolExpr Q = ctx.mkGt(x, ctx.mkInt(5));
        String expectedawp = "(<= x 6)";
        String expectedavc1 = "(=> (and (<= x 6) (<= x 5)) (<= (+ x 1) 6))";
        String expectedavc2 = "(=> (and (<= x 6) (not (<= x 5))) (> x 5))";
        ArrayList<BoolExpr> result = While.eval(ctx, Q, ast);
        BoolExpr awpResult = result.get(0);
        String avc = result.get(1).toString();

        Assert.assertEquals(expectedawp, awpResult.toString());
        Assert.assertTrue(avc.contains("true"));
        Assert.assertTrue(avc.contains(expectedavc1));
        Assert.assertTrue(avc.contains(expectedavc2));
    }
}