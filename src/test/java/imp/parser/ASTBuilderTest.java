package imp.parser;

import imp.ast.Program;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Test;

class ASTBuilderTest {

    @Test
    void test() {
        String program = """
                method Foo() returns (int x)
                    ensures y == 10
                {
                    if (true) {
                    }
                    int x;
                    int y = x;
                    if (x > 10) {
                        y = y + 1;
                    }
                    
                    int z;
                    bool zzz;
                    while (z > x) {
                        z = x - y;
                    }
                }
                            
                method Bar() returns (int x)
                    ensures y == 10
                {
                    t1 = Foo(2);
                    // Hello world
                    int x = 10;
                    
                    while (x > 10) {
                        x = x + 10;
                        
                        if (x > (y + z)) {
                        }
                    }
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
            System.out.println(ast);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}