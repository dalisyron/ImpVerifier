package imp;

import com.microsoft.z3.*;

import imp.ast.Program;
import imp.ast.method.MethodDeclaration;
import imp.parser.*;
import imp.verification.Method;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;


public class FunctionExampleTest {
    @Test
    public void test() {
        Context ctx = new Context();
        IntExpr a = ctx.mkIntConst("a");
        FuncDecl f = ctx.mkFuncDecl("f", new Sort[] {ctx.getIntSort()}, ctx.getIntSort());
        // Build formula: f(f(f(a))) == a
        BoolExpr p1 = ctx.mkEq(ctx.mkApp(f, ctx.mkApp(f, ctx.mkApp(f, a))), a);
        // Build formula: f(f(f(f(f(a))))) == a
        BoolExpr p2 = ctx.mkEq(ctx.mkApp(f, ctx.mkApp(f, ctx.mkApp(f, ctx.mkApp(f, ctx.mkApp(f, a))))), a);

        // Build formula: f(a) != a
        BoolExpr p3 = ctx.mkNot(ctx.mkEq(ctx.mkApp(f, a), a));

        Solver solver = ctx.mkSolver();
        solver.add(p1, p2, p3);
        // Check satisfiability
        Status status = solver.check();
        Assert.assertEquals(Status.UNSATISFIABLE, status);
        // System.out.println(status);
    }

    @Test
    public void testExamples() throws IOException {
        // Directory containing test data files
        String testDataDir = "TestData/Examples";

        // Get all *.imp files in the TestData directory
        try (Stream<Path> paths = Files.walk(Paths.get(testDataDir))) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".imp"))
                    .forEach(path -> {
                        try {
                            // Test each .imp file
                            testProgram(path.toString());
                        } catch (IOException e) {
                            Assertions.fail("Failed to read or test file: " + path, e);
                        }
                    });
        }
    }

    private void testProgram(String filePath) throws IOException {
        String text = readProgramFromFile(filePath);
        Program program = Parser.parseString(text);
        List<MethodDeclaration> methods = program.methods();
        Context ctx = new Context();

        for (int i = 0; i < methods.size(); i++) {
            boolean valid = Method.getInstance().Verify(ctx, methods.get(i));
            if (valid) {
                System.out.printf("Method %s is valid", methods.get(i).name());
            } else {
                System.out.printf("Method %s is invalid", methods.get(i).name());
            }
            Assert.assertTrue(valid);
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