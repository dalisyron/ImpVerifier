package imp;

import imp.ast.Statement;
import imp.ast.statement.AssignStmt;
import imp.ast.statement.IfStmt;
import imp.ast.statement.PostcondStmt;
import imp.ast.statement.PrecondStmt;
import imp.ast.statement.SequenceStmt;
import imp.interpreter.Z3ImpInterpreter;
import imp.parser.Parser;

import java.io.IOException;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Solver;
import com.microsoft.z3.Status;

public class ImpVerifier {

    public static void main(String[] args) {
        try {
            Statement ast, conditions;

            if (args.length > 1) {
                // If a file path is provided as a command-line argument
                String filePath = args[0];
                ast = Parser.parseFile(filePath);
                String conditionsPath = args[1];
                conditions = Parser.parseFile(conditionsPath);
            } else {
                // No file path provided, default to "myprog.imp"
                ast = Parser.parseFile("myprog.imp");
                conditions = Parser.parseFile("conditions.imp");
            }

            // Now, process the AST as needed
            // For example, print the AST nodes
            Context ctx = new Context();

            //first 2 statements have to be pre and post cond
            //System.out.println(((SequenceStmt)ast).getFirst());
            PostcondStmt postcond = (PostcondStmt)((SequenceStmt)conditions).getSecond();
            PrecondStmt precond = (PrecondStmt)((SequenceStmt)conditions).getFirst();

            BoolExpr P, Q;
            P = Z3ImpInterpreter.convertConditional(ctx, precond.getCondition());
            Q = Z3ImpInterpreter.convertConditional(ctx, postcond.getCondition());

            BoolExpr awp, avc;

            if (ast.getClass() == AssignStmt.class) {
                awp = Assign.eval(ctx, Q, ast).get(0);
            } else if (ast.getClass() == SequenceStmt.class) {
                awp = Composition.eval(ctx, Q, ast).get(0);
            } else if (ast.getClass() == IfStmt.class) {
                awp = Ifelse.eval(ctx, Q, ast).get(0);
            } else {
                throw new RuntimeException("Non-implemented Statement type");
            }

            if (ast.getClass() == AssignStmt.class) {
                avc = Assign.eval(ctx, Q, ast).get(1);
            } else if (ast.getClass() == SequenceStmt.class) {
                avc = Composition.eval(ctx, Q, ast).get(1);
            } else if (ast.getClass() == IfStmt.class) {
                avc = Ifelse.eval(ctx, Q, ast).get(1);
            } else {
                throw new RuntimeException("Non-implemented Statement type");
            }

            Solver solver = ctx.mkSolver();
            // This logic needs to be checked
            solver.add(ctx.mkAnd(ctx.mkImplies(P, awp), P));
            // solver adds are implicitly and statements
            solver.add(avc);
            if (solver.check() == Status.SATISFIABLE) {
                System.out.println("Hoares triple is valid");
            }


        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Parsing error: " + e.getMessage());
        }
    }
}