package imp;

import java.util.ArrayList;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;

import imp.ast.Statement;
import imp.ast.statement.AssignStmt;
import imp.ast.statement.IfStmt;
import imp.ast.statement.SequenceStmt;
import imp.ast.statement.WhileStmt;


public class Composition {

    // arguments the AST for an ifelse statement are post condition Q
    // first element returned is Q, the remainder is the AVC of the ifelse statement

    public static ArrayList<BoolExpr> eval(Context ctx, BoolExpr Q, Statement ast) {
        // ensure that ast is of type ifelse
        assert(ast.getClass() == SequenceStmt.class);

        ArrayList<BoolExpr> ret = new ArrayList<BoolExpr>();
        // get awp
        BoolExpr awp = awp(ctx, Q, (SequenceStmt)ast);
        //get avc
        BoolExpr avc = avc(ctx, Q, (SequenceStmt)ast);

        // add in order [Q, avc]
        ret.add(awp);
        ret.add(avc);
        return ret;
    }

    
    public static BoolExpr awp(Context ctx, BoolExpr Q, SequenceStmt ast) {
            // get the variable and expr from the AST when its done
            Statement statement1 = ast.getFirst();
            Statement statement2 = ast.getSecond();
            BoolExpr A, B;

            if (statement2.getClass() == AssignStmt.class) {
                B = Assign.awp(ctx, Q, statement2);
            } else if (statement2.getClass() == SequenceStmt.class) {
                B = awp(ctx, Q, (SequenceStmt)statement2);
            } else if (statement2.getClass() == IfStmt.class) {
                B = Ifelse.awp(ctx, Q, (IfStmt)statement2);

            } else if (statement2.getClass() == WhileStmt.class) {
                B = While.awp(ctx, Q, (WhileStmt)statement2);
            } else {
                throw new RuntimeException("Non-implemented AWP");
            }
            if (statement1.getClass() == AssignStmt.class) {
                A = Assign.awp(ctx, B, (AssignStmt)statement1);
            } else if (statement1.getClass() == SequenceStmt.class) {
                A = awp(ctx, B, (SequenceStmt)statement1);
            } else if (statement1.getClass() == IfStmt.class) {
                A = Ifelse.awp(ctx, B, (IfStmt)statement1);
            } else if (statement1.getClass() == WhileStmt.class) {
                A = While.awp(ctx, B, (WhileStmt)statement1);
            } else {
                throw new RuntimeException("Non-implemented AWP");
            }

        return A;
    }

    public static BoolExpr avc(Context ctx, BoolExpr Q, SequenceStmt ast) {
        Statement statement1 = ast.getFirst();
        Statement statement2 = ast.getSecond();
        BoolExpr A, B, awpS2;

        if (statement2.getClass() == AssignStmt.class) {
            B = Assign.avc(ctx, Q, statement2);
            awpS2 = Assign.awp(ctx, Q, (AssignStmt)statement2);
        } else if (statement2.getClass() == SequenceStmt.class) {
            B = avc(ctx, Q, (SequenceStmt)statement2);
            awpS2 = awp(ctx, Q, (SequenceStmt)statement2);
        } else if (statement2.getClass() == IfStmt.class) {
            B = Ifelse.avc(ctx, Q, (IfStmt)statement2);
            awpS2 = Ifelse.awp(ctx, Q, (IfStmt)statement2);
        } else if (statement2.getClass() == WhileStmt.class) {
            B = While.avc(ctx, Q, (WhileStmt)statement2);
            awpS2 = While.awp(ctx, Q, (WhileStmt)statement2);
        } else {
            throw new RuntimeException("Non-implemented AVC");
        }

        if (statement1.getClass() == AssignStmt.class) {
            A = Assign.avc(ctx, awpS2, (AssignStmt)statement1);
        } else if (statement1.getClass() == SequenceStmt.class) {
            A = avc(ctx, awpS2, (SequenceStmt)statement1);
        } else if (statement1.getClass() == IfStmt.class) {
            A = Ifelse.avc(ctx, awpS2, (IfStmt)statement1);
        }  else if (statement1.getClass() == WhileStmt.class) {
            A = While.avc(ctx, awpS2, (WhileStmt)statement1);
        } else {
            throw new RuntimeException("Non-implemented AVC");
        }

        return ctx.mkAnd(B, A);
    }

}
