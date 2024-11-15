package imp;

import java.util.ArrayList;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;

import imp.ast.Statement;
import imp.ast.statement.AssignStmt;
import imp.interpreter.Z3ImpInterpreter;

public class Assign {

    // AST input is an ast of z3 expressions
    // AST for an assign statement has 2 nodes, 1 for the variable and 1 for the expression
    // returns a list where a[0] = Q[E/x] and a[1] = avc

    public static ArrayList<BoolExpr> eval(Context ctx, BoolExpr Q, Statement ast) {
        // ensure that ast is of type assign
        assert(ast.getClass() == AssignStmt.class);

        ArrayList<BoolExpr> ret = new ArrayList<>();
        // get awp
        BoolExpr awp = awp(ctx, Q, ast);
        //get avc
        BoolExpr avc = avc(ctx, Q, ast);

        // add in order [awp, avc]
        ret.add(awp);
        ret.add(avc);
        return ret;
    }
    
    public static BoolExpr awp(Context ctx, BoolExpr Q, Statement statement) {
        // get the variable and expr from the AST
        AssignStmt assignstatement = (AssignStmt)statement;
        Expr variable = ctx.mkIntConst(assignstatement.getVariable());
        Expr expr = Z3ImpInterpreter.convertExpression(ctx, assignstatement.getExpression());
        Q.substitute(variable, expr);
        return (BoolExpr) Q.substitute(variable, expr);
    }

    public static BoolExpr avc(Context ctx, BoolExpr Q, Statement statement) {
        return ctx.mkTrue();
    }

}
