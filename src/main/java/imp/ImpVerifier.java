package imp;

import imp.ast.method.MethodDeclaration;
import imp.ast.Program;
import imp.parser.Parser;
import imp.typechecker.TypeChecker;
import imp.verification.Method;

import org.antlr.v4.runtime.tree.ParseTree;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;

import java.io.IOException;
import java.util.List;

public class ImpVerifier {

    public static void main(String[] args) throws IOException {
        // Read input file from args
        // Parse input file

        if (args.length != 1) {
            System.err.println("Usage: java -jar imp-verifier.jar <input-file>");
            System.exit(1);
        }

        // Parse input file
        String inputFile = args[0];

        Program program = Parser.parseFile(inputFile);

        // Type check throws error if P and Q have variables not declared
        // Cannot use the same structure as the tests since we have no access to the ast
        // TypeChecker.checkTypes(program);

        List<MethodDeclaration> methods = program.methods();
        Context ctx = new Context();

        for (int i = 0; i < methods.size(); i++) {
            boolean satisfiable = Method.getInstance().Verify(ctx, methods.get(i));
            if (satisfiable) {
                System.out.printf("Method %s conditions are satisfiable", methods.get(i).name());
            } else {
                System.out.printf("Method %s conditions are unsatisfiable", methods.get(i).name());
            }
        }

        // Print the AST
        //BoolExpr m = Method.getInstance().awp(ctx, (MethodDeclaration) tree);
    }
    }
