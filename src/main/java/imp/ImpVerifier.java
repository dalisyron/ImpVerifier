package imp;

import imp.parser.Parser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

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

        ParseTree tree = Parser.parseFile(inputFile);

        // Print the AST
        System.out.println(tree.toStringTree());
    }
}