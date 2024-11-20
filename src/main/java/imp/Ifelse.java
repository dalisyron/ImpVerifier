package imp;

import java.util.ArrayList;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;

import imp.ast.Statement;
import imp.ast.statement.AssignStmt;
import imp.ast.statement.IfStmt;
import imp.ast.statement.SequenceStmt;
import imp.ast.statement.WhileStmt;
import imp.interpreter.Z3ImpInterpreter;

public class Ifelse {

    // arguments the AST for an ifelse statement are post condition Q
    // first element returned is Q, the remainder is the AVC of the ifelse statement

    public static ArrayList<BoolExpr> eval(Context ctx, BoolExpr Q, Statement ast) {
        // ensure that ast is of type ifelse
        assert(ast.getClass() == IfStmt.class);

        ArrayList<BoolExpr> ret = new ArrayList<BoolExpr>();
        // get awp
        BoolExpr awp = awp(ctx, Q, (IfStmt)ast);
        //get avc
        BoolExpr avc = avc(ctx, Q, (IfStmt)ast);

        // add in order [Q, avc]
        ret.add(awp);
        ret.add(avc);
        return ret;
    }

    
    public static BoolExpr awp(Context ctx, BoolExpr Q, IfStmt ast) {
            // get statement 1 and 2 and condition from AST when its done
            Statement statement1 = ast.getThenBranch();
            Statement statement2 = ast.getElseBranch();
            BoolExpr condition = Z3ImpInterpreter.convertConditional(ctx, ast.getCondition());
            BoolExpr A, B;
            if (statement1.getClass() == AssignStmt.class) {
                A = Assign.awp(ctx, Q, statement1);
            } else if (statement1.getClass() == SequenceStmt.class) {
                A = Composition.awp(ctx, Q, (SequenceStmt)statement1);
            } else if (statement1.getClass() == IfStmt.class) {
                A = awp(ctx, Q, (IfStmt)statement1);
            } else if (statement1.getClass() == WhileStmt.class) {
                A = While.awp(ctx, Q, (WhileStmt)statement1);
            } else if (statement1.getClass() == IfStmt.class) {
                A = awp(ctx, Q, (IfStmt)statement1);
            } else {
                throw new RuntimeException("Non-implemented AWP");
            }

            if (statement2.getClass() == AssignStmt.class) {
                B = Assign.awp(ctx, Q, statement2);
            } else if (statement2.getClass() == SequenceStmt.class) {
                B = Composition.awp(ctx, Q, (SequenceStmt)statement2);
            } else if (statement2.getClass() == IfStmt.class) {
                B = awp(ctx, Q, (IfStmt)statement2);
            } else if (statement2.getClass() == WhileStmt.class) {
                B = While.awp(ctx, Q, (WhileStmt)statement2);
            } else if (statement2.getClass() == IfStmt.class) {
                B = awp(ctx, Q, (IfStmt)statement2);
            } else {
                throw new RuntimeException("Non-implemented AWP");
            }
            A = ctx.mkImplies(condition, A);
            B = ctx.mkImplies(ctx.mkNot(condition), B);
        return ctx.mkAnd(A, B);
    }

    public static BoolExpr avc(Context ctx, BoolExpr Q, IfStmt ast) {
        // get statement 1 and 2 and condition from AST when its done
        Statement statement1 = ast.getThenBranch();
        Statement statement2 = ast.getElseBranch();
        BoolExpr A, B;
        if (statement1.getClass() == AssignStmt.class) {
            A = Assign.avc(ctx, Q, statement1);
        } else if (statement1.getClass() == SequenceStmt.class) {
            A = Composition.avc(ctx, Q, (SequenceStmt)statement1);
            A = ctx.mkTrue();
        } else if (statement1.getClass() == IfStmt.class) {
            A = avc(ctx, Q, (IfStmt)statement1);
        }  else if (statement1.getClass() == WhileStmt.class) {
            A = While.avc(ctx, Q, (WhileStmt)statement1);
        } else if (statement1.getClass() == IfStmt.class) {
            A = avc(ctx, Q, (IfStmt)statement1);
        } else {
            throw new RuntimeException("Non-implemented AVC");
        }

        if (statement2.getClass() == AssignStmt.class) {
            B = Assign.avc(ctx, Q, statement2);
        } else if (statement2.getClass() == SequenceStmt.class) {
            B = Composition.avc(ctx, Q, (SequenceStmt)statement2);
        } else if (statement2.getClass() == IfStmt.class) {
            B = avc(ctx, Q, (IfStmt)statement2);
        } else if (statement2.getClass() == WhileStmt.class) {
            B = While.avc(ctx, Q, (WhileStmt)statement2);
        } else if (statement2.getClass() == IfStmt.class) {
            B = avc(ctx, Q, (IfStmt)statement2);
        } else {
            throw new RuntimeException("Non-implemented AVC");
        }
    return ctx.mkAnd(A, B);
    }

}
