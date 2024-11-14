package imp.interpreter;

import com.microsoft.z3.*;
import imp.ast.*;
import imp.ast.conditional.*;
import imp.ast.expression.*;

public class Z3ImpInterpreter {

    /**
     * Converts a Conditional AST node to a Z3 BoolExpr.
     *
     * @param ctx  The Z3 context.
     * @param cond The Conditional AST node.
     * @return The equivalent Z3 BoolExpr.
     */
    public static BoolExpr convertConditional(Context ctx, Conditional cond) {
        if (cond instanceof TrueCond) {
            return ctx.mkTrue();
        } else if (cond instanceof FalseCond) {
            return ctx.mkFalse();
        } else if (cond instanceof EqualCond) {
            return convertEqualCond(ctx, (EqualCond) cond);
        } else if (cond instanceof LeqCond) {
            return convertLeqCond(ctx, (LeqCond) cond);
        } else {
            throw new UnsupportedOperationException("Unknown Conditional type: " + cond.getClass());
        }
    }

    // Private methods to handle specific Conditional types

    private static BoolExpr convertEqualCond(Context ctx, EqualCond cond) {
        ArithExpr leftExpr = convertExpression(ctx, cond.left());
        ArithExpr rightExpr = convertExpression(ctx, cond.right());
        return ctx.mkEq(leftExpr, rightExpr);
    }

    private static BoolExpr convertLeqCond(Context ctx, LeqCond cond) {
        ArithExpr leftExpr = convertExpression(ctx, cond.left());
        ArithExpr rightExpr = convertExpression(ctx, cond.right());
        return ctx.mkLe(leftExpr, rightExpr);
    }

    // Methods to convert Expressions

    private static ArithExpr convertExpression(Context ctx, Expression expr) {
        if (expr instanceof IntegerExpr) {
            return convertIntegerExpr(ctx, (IntegerExpr) expr);
        } else if (expr instanceof VariableExpr) {
            return convertVariableExpr(ctx, (VariableExpr) expr);
        } else if (expr instanceof AddExpr) {
            return convertAddExpr(ctx, (AddExpr) expr);
        } else if (expr instanceof MulExpr) {
            return convertMulExpr(ctx, (MulExpr) expr);
        } else if (expr instanceof ParenExpr) {
            return convertParenExpr(ctx, (ParenExpr) expr);
        } else {
            throw new UnsupportedOperationException("Unknown Expression type: " + expr.getClass());
        }
    }

    private static ArithExpr convertIntegerExpr(Context ctx, IntegerExpr expr) {
        return ctx.mkInt(expr.value());
    }

    private static ArithExpr convertVariableExpr(Context ctx, VariableExpr expr) {
        String varName = expr.name();
        // Note*: Creating multiple IntExprs with the same name is acceptable in Z3,
        // as Z3 treats variables with the same name as the same variable within the same context.

        return ctx.mkIntConst(varName);
    }

    private static ArithExpr convertAddExpr(Context ctx, AddExpr expr) {
        ArithExpr left = convertExpression(ctx, expr.left());
        ArithExpr right = convertExpression(ctx, expr.right());
        return ctx.mkAdd(left, right);
    }

    private static ArithExpr convertMulExpr(Context ctx, MulExpr expr) {
        ArithExpr left = convertExpression(ctx, expr.left());
        ArithExpr right = convertExpression(ctx, expr.right());
        return ctx.mkMul(left, right);
    }

    private static ArithExpr convertParenExpr(Context ctx, ParenExpr expr) {
        // Simply convert the inner expression
        return convertExpression(ctx, expr.inner());
    }
}
