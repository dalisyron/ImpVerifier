package imp.parser;

import imp.parser.antlr.ImpLexer;
import imp.parser.antlr.ImpParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.File;
import java.io.IOException;

public class Parser {

    /**
     * Parses an IMP program from a file and returns its AST representation.
     *
     * @param filePath The path to the IMP program file.
     * @return The AST (Abstract Syntax Tree) of the parsed program.
     * @throws IOException              If the file does not exist or cannot be read.
     * @throws IllegalArgumentException If the file does not have a .imp extension.
     * @throws RuntimeException         If a syntax error is encountered during parsing.
     */
    public static ParseTree parseFile(String filePath) throws IOException {
        // Check if file exists
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File " + filePath + " does not exist.");
        }

        // Check if file has .imp extension
        if (!filePath.endsWith(".imp")) {
            throw new IllegalArgumentException("File " + filePath + " does not have .imp extension.");
        }

        // Load input from the file
        CharStream input = CharStreams.fromFileName(filePath);

        // Use the common parsing code
        return parseInput(input);
    }

    /**
     * Parses an IMP program from a string and returns its AST representation.
     *
     * @param program The IMP program as a string.
     * @return The AST (Abstract Syntax Tree) of the parsed program.
     * @throws RuntimeException If a syntax error is encountered during parsing.
     */
    public static ParseTree parseString(String program) {
        // Load input from the string
        CharStream input = CharStreams.fromString(program);

        // Use the common parsing code
        return parseInput(input);
    }

    // Common parsing code used by both parseFile and parseString methods
    private static ParseTree parseInput(CharStream input) {
        ImpLexer lexer = new ImpLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ImpParser parser = new ImpParser(tokens);

        // Add a custom error listener to detect and report errors
        parser.removeErrorListeners(); // Remove default console error listener
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(
                    Recognizer<?, ?> recognizer, Object offendingSymbol,
                    int line, int charPositionInLine, String msg,
                    RecognitionException e
            ) {
                throw new RuntimeException(
                        "Syntax error at line " + line + ":" + charPositionInLine + " - " + msg
                );
            }
        });

        // Attempt to parse the input program
        ParseTree tree = parser.parse();

        // Build the AST using the listener
        return tree;
    }
}
