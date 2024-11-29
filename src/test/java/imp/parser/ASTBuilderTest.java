package imp.parser;

import imp.ast.Program;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ASTBuilderTest {

    @Test
    void test() {
        String program = """
                method Bar() returns (int x)
                    ensures y == 10
                {
                    // Hello world
                }
                """;
        try {
            // Parse the input program string
            ParseTree tree = Parser.parseString(program);

            // Create an ASTBuilder and walk the parse tree
            ASTBuilder astBuilder = new ASTBuilder();
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(astBuilder, tree);

            // Get the AST from the ASTBuilder
            Program ast = astBuilder.getProgram();

            // Print the AST
            String programRepresentation = ast.toString();

            // Reparse the program representation
            ParseTree reparsedTree = Parser.parseString(programRepresentation);

            // Ensure the original and reparsed trees are equal
            ASTBuilder reparsedAstBuilder = new ASTBuilder();
            walker.walk(reparsedAstBuilder, reparsedTree);
            Program reparsedAst = reparsedAstBuilder.getProgram();

            Assertions.assertEquals(ast, reparsedAst);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}