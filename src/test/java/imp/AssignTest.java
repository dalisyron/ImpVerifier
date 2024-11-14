package imp;

import com.microsoft.z3.ArithExpr;
import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.IntExpr;
import org.junit.Assert;
import org.junit.Test;

public class AssignTest {
    @Test
    public void testSubstituteFunction() {
        // Create a new context
        try (Context ctx = new Context()) {
            // Create a postcondition x > 0
            IntExpr x = ctx.mkIntConst("x");
            BoolExpr Q = ctx.mkGt(x, ctx.mkInt(0));

            // Create RHS expression
            IntExpr y = ctx.mkIntConst("y");
            ArithExpr constantOne = ctx.mkInt(1);
            ArithExpr rhsExpression = ctx.mkAdd(y, constantOne);

            // Apply substitution
            BoolExpr substitutedQ = (BoolExpr) Q.substitute(x, rhsExpression);

            // Expected result: (y + 1) > 0
            BoolExpr expectedQ = ctx.mkGt(rhsExpression, ctx.mkInt(0));

            System.out.println("Original postcondition Q: " + Q);
            System.out.println("Substituted postcondition Q: " + substitutedQ);
            System.out.println("Expected postcondition Q: " + expectedQ);

            Assert.assertEquals(expectedQ, substitutedQ);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
