package imp;

import java.util.ArrayList;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;

public class Ifelse {

    // arguments the AST for an ifelse statement are post condition Q
    // first element returned is Q, the remainder is the AVC of the ifelse statement

    public static ArrayList<BoolExpr> eval(Context ctx, BoolExpr Q, ArrayList<Expr> ast) {
        // ensure that ast is of type ifelse
        assert(ast.size() == 3);

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

    
    public static BoolExpr awp(Context ctx, BoolExpr Q, ArrayList<Expr> ast) {
            // get statement 1 and 2 and condition from AST when its done
            ArrayList<Expr> statement1 = new ArrayList<>();
            ArrayList<Expr> statement2 = new ArrayList<>();
            BoolExpr condition = ctx.mkTrue();
            BoolExpr A, B;
            switch(statement1.size()) {
                case 1:
                    A = Assign.awp(ctx, Q, statement1);
                    break;
                case 2:
                    // call composition awp gen here
                    A = Q;
                    break;
                case 3:
                    A = ctx.mkImplies(condition, awp(ctx, Q, statement1));
                    break;
                default:
                    throw new RuntimeException("Unable to process if else statement, unknown sub statement type");
            }
            switch(statement2.size()) {
                case 1:
                    B = Assign.awp(ctx, Q, statement2);
                    break;
                case 2:
                    // call composition awp gen here
                    B = Q;
                case 3:
                    B = ctx.mkImplies(condition, awp(ctx, Q, statement1));
                    break;
                default:
                    throw new RuntimeException("Unable to process if else statement, unknown sub statement type");
            }
            A = ctx.mkImplies(condition, awp(ctx, Q, statement1));
            B = ctx.mkImplies(ctx.mkNot(condition), awp(ctx, Q, statement2));
        return ctx.mkAnd(A, B);
    }

    public static BoolExpr avc(Context ctx, BoolExpr Q, ArrayList<Expr> ast) {
        throw new RuntimeException("To be implemented");
    }

}
