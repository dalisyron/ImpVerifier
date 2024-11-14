package imp;

import imp.ast.Statement;
import imp.parser.Parser;

import java.io.IOException;

public class ImpVerifier {

    public static void main(String[] args) {
        try {
            Statement ast;

            if (args.length > 0) {
                // If a file path is provided as a command-line argument
                String filePath = args[0];
                ast = Parser.parseFile(filePath);
            } else {
                // No file path provided, default to "myprog.imp"
                ast = Parser.parseFile("myprog.imp");
            }

            // Now, process the AST as needed
            // For example, print the AST nodes
            System.out.println(ast);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Parsing error: " + e.getMessage());
        }
    }
}