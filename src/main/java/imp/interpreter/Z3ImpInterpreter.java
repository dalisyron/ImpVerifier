package imp.interpreter;

import com.microsoft.z3.*;
import imp.ast.expression.Expression;
import imp.ast.expression.VarRefExpression;
import imp.ast.expression.binary.bool.compare.*;
import imp.ast.expression.binary.bool.logic.AndExpression;
import imp.ast.expression.binary.bool.logic.OrExpression;
import imp.ast.expression.binary.integer.AddExpression;
import imp.ast.expression.binary.integer.DivExpression;
import imp.ast.expression.binary.integer.MulExpression;
import imp.ast.expression.binary.integer.SubExpression;
import imp.ast.expression.bool.ForallExpression;
import imp.ast.expression.binary.bool.logic.ImpliesExpression;
import imp.ast.expression.constant.bool.FalseExpression;
import imp.ast.expression.constant.bool.TrueExpression;
import imp.ast.expression.constant.integer.IntExpression;
import imp.ast.expression.unary.integer.NegExpression;

public class Z3ImpInterpreter {

    /**
     * Converts a Conditional AST node to a Z3 BoolExpr.
     *
     * @param ctx  The Z3 context.
     * @param cond The Conditional AST node.
     * @return The equivalent Z3 BoolExpr.
     */
    public static BoolExpr convertConditional(Context ctx, Expression cond) {
        if (cond instanceof TrueExpression) {
            return ctx.mkTrue();
        } else if (cond instanceof FalseExpression) {
            return ctx.mkFalse();
        } else if (cond instanceof EqExpression) {
            return convertEqualCond(ctx, (EqExpression) cond);
        } else if (cond instanceof LessThanOrEqualExpression) {
            return convertLeqCond(ctx, (LessThanOrEqualExpression) cond);
        } else if (cond instanceof LessThanExpression) {
            return convertLtCond(ctx, (LessThanExpression) cond);
        } else if (cond instanceof GreaterThanOrEqualExpression) {
            return convertGeqCond(ctx, (GreaterThanOrEqualExpression) cond);
        } else if (cond instanceof GreaterThanExpression) {
            return convertGtCond(ctx, (GreaterThanExpression) cond);
        } else if (cond instanceof AndExpression) {
            return convertAndCond(ctx, (AndExpression) cond);
        } else if (cond instanceof OrExpression) {
            return convertOrCond(ctx, (OrExpression) cond);
        } else if (cond instanceof ImpliesExpression) {
            return convertImpliesCond(ctx, (ImpliesExpression) cond);
        } else if (cond instanceof ForallExpression) {
            return convertForallCond(ctx, (ForallExpression) cond);
        } else {
            throw new UnsupportedOperationException("Unknown Conditional type: " + cond.getClass());
        }
    }

    private static BoolExpr convertForallCond(Context ctx, ForallExpression cond) {
//        BoolExpr body = convertConditional(ctx, cond.body());
//
//        BoolExpr quantified = ctx.mkForall(new Expr[] {
//                ctx.mkConst(cond.variable().name(), ctx.getIntSort())
//        }, body, 1, null, null, null, null);
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static BoolExpr convertImpliesCond(Context ctx, ImpliesExpression cond) {
        return ctx.mkImplies(convertConditional(ctx, cond.left()), convertConditional(ctx, cond.right()));
    }

    private static BoolExpr convertOrCond(Context ctx, OrExpression cond) {
        return ctx.mkOr(convertConditional(ctx, cond.left()), convertConditional(ctx, cond.right()));
    }

    private static BoolExpr convertAndCond(Context ctx, AndExpression cond) {
        return ctx.mkAnd(convertConditional(ctx, cond.left()), convertConditional(ctx, cond.right()));
    }

    private static BoolExpr convertGtCond(Context ctx, GreaterThanExpression cond) {
        ArithExpr leftExpr = convertExpression(ctx, cond.left());
        ArithExpr rightExpr = convertExpression(ctx, cond.right());
        return ctx.mkGt(leftExpr, rightExpr);
    }

    private static BoolExpr convertGeqCond(Context ctx, GreaterThanOrEqualExpression cond) {
        ArithExpr leftExpr = convertExpression(ctx, cond.left());
        ArithExpr rightExpr = convertExpression(ctx, cond.right());
        return ctx.mkGe(leftExpr, rightExpr);
    }

    private static BoolExpr convertLtCond(Context ctx, LessThanExpression cond) {
        ArithExpr leftExpr = convertExpression(ctx, cond.left());
        ArithExpr rightExpr = convertExpression(ctx, cond.right());
        return ctx.mkLt(leftExpr, rightExpr);
    }

    private static BoolExpr convertLeqCond(Context ctx, LessThanOrEqualExpression cond) {
        ArithExpr leftExpr = convertExpression(ctx, cond.left());
        ArithExpr rightExpr = convertExpression(ctx, cond.right());
        return ctx.mkLe(leftExpr, rightExpr);
    }

    private static BoolExpr convertEqualCond(Context ctx, EqExpression cond) {
        ArithExpr leftExpr = convertExpression(ctx, cond.left());
        ArithExpr rightExpr = convertExpression(ctx, cond.right());
        return ctx.mkEq(leftExpr, rightExpr);
    }
    // Private methods to handle specific Conditional types

    public static ArithExpr convertExpression(Context ctx, Expression expr) {
        if (expr instanceof IntExpression) {
            return convertIntExpr(ctx, (IntExpression) expr);
        }
        if (expr instanceof NegExpression) {
            return convertNegExpr(ctx, (NegExpression) expr);
        }
        if (expr instanceof VarRefExpression) {
            return convertVarRefExpr(ctx, (VarRefExpression) expr);
        }
        if (expr instanceof AddExpression) {
            return convertAddExpr(ctx, (AddExpression) expr);
        }
        if (expr instanceof DivExpression) {
            return ConvertDivExpr(ctx, (DivExpression) expr);
        }
        if (expr instanceof MulExpression) {
            return convertMulExpr(ctx, (MulExpression) expr);
        }
        if (expr instanceof SubExpression) {
            return convertSubExpr(ctx, (SubExpression) expr);
        }
        System.out.println(expr.getClass());
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private static ArithExpr convertIntExpr(Context ctx, IntExpression expr) {
        return ctx.mkInt(expr.value());
    }

    private static ArithExpr convertNegExpr(Context ctx, NegExpression expr) {
        ArithExpr expression = convertExpression(ctx, expr.expression());
        return ctx.mkUnaryMinus(expression);
    }

    private static ArithExpr convertVarRefExpr (Context ctx, VarRefExpression expr) {
        return ctx.mkIntConst(expr.toString());
    }

    private static ArithExpr convertAddExpr(Context ctx, AddExpression expr) {
        ArithExpr leftExpr = convertExpression(ctx, expr.left());
        ArithExpr rightExpr = convertExpression(ctx, expr.right());
        return ctx.mkAdd(leftExpr, rightExpr);
    }

    private static ArithExpr convertMulExpr(Context ctx, MulExpression expr) {
        ArithExpr leftExpr = convertExpression(ctx, expr.left());
        ArithExpr rightExpr = convertExpression(ctx, expr.right());
        return ctx.mkMul(leftExpr, rightExpr);
    }

    private static ArithExpr convertSubExpr(Context ctx, SubExpression expr) {
        ArithExpr leftExpr = convertExpression(ctx, expr.left());
        ArithExpr rightExpr = convertExpression(ctx, expr.right());
        return ctx.mkSub(leftExpr, rightExpr);
    }

    private static ArithExpr ConvertDivExpr(Context ctx, DivExpression expr) {
        ArithExpr leftExpr = convertExpression(ctx, expr.left());
        ArithExpr rightExpr = convertExpression(ctx, expr.right());
        return ctx.mkDiv(leftExpr, rightExpr);
    }


}