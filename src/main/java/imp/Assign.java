package imp;

import java.util.ArrayList;

import com.microsoft.z3.ArithExpr;
import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.IntExpr;

public class Assign {

    // arguments the AST for an ifelse statement are post condition Q
    // first element returned is Q, the remainder is the AVC of the ifelse statement

    public static ArrayList<BoolExpr> eval(Context ctx, BoolExpr Q, ArrayList<String> ast) {
        // ensure that ast is of type ifelse
        assert(ast.size() == 1);

        ArrayList<BoolExpr> ret = new ArrayList<BoolExpr>();
        // get awp
        BoolExpr awp = awp(ctx, Q, ast);
        //get avc
        BoolExpr avc = avc(ctx, Q, ast);

        // add in order [Q, avc]
        ret.add(awp);
        ret.add(avc);
        return ret;
    }

    
    public static BoolExpr awp(Context ctx, BoolExpr Q, ArrayList<String> ast) {
            // get the variable and expr from the AST when its done
            IntExpr variable = ctx.mkIntConst("test");
            ArithExpr expr = ctx.mkAdd();
            Q.substitute(variable, expr);
        return (BoolExpr) Q.substitute(variable, expr);
    }

    public static BoolExpr avc(Context ctx, BoolExpr Q, ArrayList<String> ast) {
        return ctx.mkTrue();
    }

}
