package imp;

import java.util.ArrayList;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;

public class Assign {

    // AST input is an ast of z3 expressions
    // AST for an assign statement has 2 nodes, 1 for the variable and 1 for the expression
    // returns a list where a[0] = Q[E/x] and a[1] = avc

    public static ArrayList<BoolExpr> eval(Context ctx, BoolExpr Q, ArrayList<Expr> ast) {
        // ensure that ast is of type assign
        assert(ast.size() == 2);

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

    
    public static BoolExpr awp(Context ctx, BoolExpr Q, ArrayList<Expr> ast) {
        // get the variable and expr from the AST
        Expr variable = ast.get(0);
        Expr expr = ast.get(1);
        Q.substitute(variable, expr);
        return (BoolExpr) Q.substitute(variable, expr);
    }

    public static BoolExpr avc(Context ctx, BoolExpr Q, ArrayList<Expr> ast) {
        return ctx.mkTrue();
    }

}
