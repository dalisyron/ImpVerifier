package imp;

import java.util.ArrayList;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;

public class Composition {

    // arguments the AST for an ifelse statement are post condition Q
    // first element returned is Q, the remainder is the AVC of the ifelse statement

    public static ArrayList<BoolExpr> eval(Context ctx, BoolExpr Q, ArrayList<Expr> ast) {
        // ensure that ast is of type ifelse
        assert(ast.size() == 2);

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
            // get the variable and expr from the AST when its done
            ArrayList<Expr> statement1 = new ArrayList<>();
            ArrayList<Expr> statement2 = new ArrayList<>();
            BoolExpr A, B;

            switch(statement2.size()) {
                case 1:
                    A = Assign.awp(ctx, Q, statement2);
                    break;
                case 2:
                    A = awp(ctx, Q, statement2);
                case 3:
                    A = Ifelse.awp(ctx, Q, statement2);
                    break;
                default:
                    throw new RuntimeException("Unable to process composition statement2, unknown sub statement type");
            }
            switch(statement1.size()) {
                case 1:
                    B = Assign.awp(ctx, A, statement1);
                    break;
                case 2:
                    // call composition awp gen here
                    B = awp(ctx, A, statement1);
                    break;
                case 3:
                    B = Ifelse.awp(ctx, A, statement1);
                    break;
                default:
                    throw new RuntimeException("Unable to process composition statement1, unknown sub statement type");
            }
        return B;
    }

    public static BoolExpr avc(Context ctx, BoolExpr Q, ArrayList<Expr> ast) {
        return ctx.mkTrue();
    }

}
