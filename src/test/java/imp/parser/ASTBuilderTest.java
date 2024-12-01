package imp.parser;

import imp.ast.Program;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

class ASTBuilderTest {

    @Test
    void test() throws IOException {
        // Directory containing test data files
        String testDataDir = "TestData/";

        // Get all *.imp files in the TestData directory
        try (Stream<Path> paths = Files.walk(Paths.get(testDataDir))) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".imp"))
                    .forEach(path -> {
                        try {
                            // Test each .imp file
                            testAstEqualsReparsedAst(path.toString());
                        } catch (IOException e) {
                            Assertions.fail("Failed to read or test file: " + path, e);
                        }
                    });
        }
    }

    @Test
    void testTmp() throws IOException {
        String filePath = "TestData/Test1.imp";

        testAstEqualsReparsedAst(filePath);
    }

    @Test
    void testWithProgramString() {
        String program = """
                method Bar()
                    requires y > 10 && true
                {
                    x = (x || y) && z;
                    if (x < 10) {
                        y = z ==> z;
                    }
                    int[] a;
                    if (x == 10) {
                        while (true)
                            invariant y > 10
                            invariant forall x :: x == y + 20
                        {
                            if (x == 10) {
                            }
                        }
                    }
                }
                        
                        
        """;

        testAstEqualsReparsedAstFromString(program);
    }

    private void testAstEqualsReparsedAst(String filePath) throws IOException {
        String program = readProgramFromFile(filePath);
        testAstEqualsReparsedAstFromString(program);
    }

    private void testAstEqualsReparsedAstFromString(String program) {
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

            // Assert that the original and reparsed ASTs are equal
            Assertions.assertEquals(ast, reparsedAst);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Exception occurred during AST parsing and comparison");
        }
    }

    private String readProgramFromFile(String filePath) throws IOException {
        Path path = Path.of(filePath);

        // Check for ".imp" extension
        if (!filePath.endsWith(".imp")) {
            throw new IllegalArgumentException("File must have a .imp extension: " + filePath);
        }

        // Read and return the file contents as a string
        return Files.readString(path);
    }
}
