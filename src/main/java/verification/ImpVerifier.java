package verification;

import imp.ast.method.MethodDeclaration;
import imp.ast.Program;
import imp.parser.Parser;
import verification.generator.Method;

import com.microsoft.z3.Context;

import java.io.IOException;
import java.util.List;

public class ImpVerifier {

    public static void main(String[] args) throws IOException {
        // Read input file from args
        // Parse input file

        if (args.length != 1) {
            System.err.println("Usage: java -cp lib/com.microsoft.z3.jar:lib/antlr4-runtime-4.13.2.jar:target/imp-verifier-1.0.jar verification.ImpVerifier <input-file>");
            System.exit(1);
        }

        // Parse input file
        String inputFile = args[0];

        Program program = Parser.parseFileCheckingTypes(inputFile);


        // Type check throws error if P and Q have variables not declared
        // Cannot use the same structure as the tests since we have no access to the ast
        // TypeChecker.checkTypes(program);

        List<MethodDeclaration> methods = program.methods();
        Context ctx = new Context();

        for (int i = 0; i < methods.size(); i++) {
            boolean valid = Method.getInstance().Verify(ctx, methods.get(i));
            if (valid) {
                System.out.printf("Method %s is valid\n\r", methods.get(i).name());
            } else {
                System.out.printf("Method %s is invalid\n\r", methods.get(i).name());
            }
        }

        // Print the AST
        //BoolExpr m = Method.getInstance().awp(ctx, (MethodDeclaration) tree);
    }
    }
