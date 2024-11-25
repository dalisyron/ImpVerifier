package imp;

import java.util.ArrayList;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;

import imp.ast.Conditional;
import imp.ast.Statement;
import imp.ast.statement.AssignStmt;
import imp.ast.statement.IfStmt;
import imp.ast.statement.SequenceStmt;
import imp.ast.statement.WhileStmt;
import imp.interpreter.Z3ImpInterpreter;

public class While {

    // AST input is an ast of z3 expressions
    // AST for an assign statement has 2 nodes, 1 for the variable and 1 for the expression
    // returns a list where a[0] = Q[E/x] and a[1] = avc

    public static ArrayList<BoolExpr> eval(Context ctx, BoolExpr Q, Statement ast) {
        // ensure that ast is of type assign
        assert(ast.getClass() == WhileStmt.class);

        ArrayList<BoolExpr> ret = new ArrayList<>();
        // get awp
        BoolExpr awp = awp(ctx, Q, (WhileStmt)ast);
        //get avc
        BoolExpr avc = avc(ctx, Q, (WhileStmt)ast);

        // add in order [awp, avc]
        ret.add(awp);
        ret.add(avc);
        return ret;
    }
    
    public static BoolExpr awp(Context ctx, BoolExpr Q, WhileStmt ast) {
        // return invariant for while awp
        return Z3ImpInterpreter.convertConditional(ctx, ast.getInvariant());
    }

    public static BoolExpr avc(Context ctx, BoolExpr Q, WhileStmt statement) {

        Statement body = statement.getBody();
        BoolExpr invariant = Z3ImpInterpreter.convertConditional(ctx, statement.getInvariant());
        BoolExpr condition = Z3ImpInterpreter.convertConditional(ctx, statement.getCondition());

        BoolExpr awpSI;

        if (body.getClass() == AssignStmt.class) {
            awpSI = Assign.awp(ctx, invariant, body);
        } else if (body.getClass() == SequenceStmt.class) {
            awpSI = Composition.awp(ctx, invariant, (SequenceStmt)body);
        } else if (body.getClass() == IfStmt.class) {
            awpSI = Ifelse.awp(ctx, invariant, (IfStmt)body);
        } else if (body.getClass() == WhileStmt.class) {
            awpSI = While.awp(ctx, invariant, (WhileStmt)body);
        } else {
            throw new RuntimeException("Non-implemented AWP");
        }

        BoolExpr clause1;

        if (body.getClass() == AssignStmt.class) {
            clause1 = Assign.avc(ctx, invariant, body);
        } else if (body.getClass() == SequenceStmt.class) {
            clause1 = Composition.avc(ctx, invariant, (SequenceStmt)body);
        } else if (body.getClass() == IfStmt.class) {
            clause1 = Ifelse.avc(ctx, invariant, (IfStmt)body);
        } else if (body.getClass() == WhileStmt.class) {
            clause1 = While.avc(ctx, invariant, (WhileStmt)body);
        } else {
            throw new RuntimeException("Non-implemented AVC");
        }

        BoolExpr clause2 = ctx.mkImplies(ctx.mkAnd(invariant, condition), awpSI);
        BoolExpr clause3 = ctx.mkImplies(ctx.mkAnd(invariant, ctx.mkNot(condition)), Q);
        return ctx.mkAnd(clause1, clause2, clause3);
    }

}
