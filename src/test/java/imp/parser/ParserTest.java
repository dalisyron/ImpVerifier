package imp.parser;

import imp.ast.Statement;
import imp.ast.expression.*;
import imp.ast.statement.*;
import imp.ast.conditional.*;
import imp.ast.Expression;
import imp.ast.Conditional;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class ParserTest {

    @Test
    public void testParseSimpleAssignment() {
        String program = "x := 5";
        Statement ast = Parser.parseString(program);

        // Expected AST
        Expression expr = new IntegerExpr(5);
        Statement expectedAst = new AssignStmt("x", expr);

        // Compare ASTs
        assertStatementsEqual(expectedAst, ast);
    }

    @Test
    public void testParseIfStatement() {
        String program = "if x <= 5 then y := 1 else y := 2";
        Statement ast = Parser.parseString(program);

        // Expected AST
        Expression x = new VariableExpr("x");
        Expression five = new IntegerExpr(5);
        Conditional condition = new LeqCond(x, five);

        Statement thenStmt = new AssignStmt("y", new IntegerExpr(1));
        Statement elseStmt = new AssignStmt("y", new IntegerExpr(2));

        Statement expectedAst = new IfStmt(condition, thenStmt, elseStmt);

        // Compare ASTs
        assertStatementsEqual(expectedAst, ast);
    }

    @Test
    public void testParseWhileLoop() {
        String program = "while x <= 10 do begin x := x + 1 end";
        Statement ast = Parser.parseString(program);

        // Expected AST
        Expression x = new VariableExpr("x");
        Expression ten = new IntegerExpr(10);
        Conditional condition = new LeqCond(x, ten);

        Expression increment = new AddExpr(x, new IntegerExpr(1));
        Statement body = new AssignStmt("x", increment);

        Statement expectedAst = new WhileStmt(condition, body);

        // Compare ASTs
        assertStatementsEqual(expectedAst, ast);
    }

    @Test
    public void testParseSequence() {
        String program = "x := 1; y := 2; z := x + y";
        Statement ast = Parser.parseString(program);

        // Expected AST
        Statement stmt1 = new AssignStmt("x", new IntegerExpr(1));
        Statement stmt2 = new AssignStmt("y", new IntegerExpr(2));
        Expression x = new VariableExpr("x");
        Expression y = new VariableExpr("y");
        Expression sum = new AddExpr(x, y);
        Statement stmt3 = new AssignStmt("z", sum);

        // Corrected to reflect left-associative parsing
        Statement expectedAst = new SequenceStmt(
                new SequenceStmt(stmt1, stmt2),
                stmt3
        );

        // Compare ASTs
        assertStatementsEqual(expectedAst, ast);
    }


    @Test
    public void testParseFileSimpleAssignment() throws IOException {
        String program = "x := 5";
        File tempFile = File.createTempFile("testProgram", ".imp");
        tempFile.deleteOnExit();

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(program);
        }

        Statement ast = Parser.parseFile(tempFile.getAbsolutePath());

        // Expected AST
        Expression expr = new IntegerExpr(5);
        Statement expectedAst = new AssignStmt("x", expr);

        // Compare ASTs
        assertStatementsEqual(expectedAst, ast);
    }

    @Test
    public void testParseFileComplexProgram() throws IOException {
        String program =
                "x := 0;\n" +
                        "while x <= 10 do\n" +
                        "begin\n" +
                        "    x := x + 1\n" +
                        "end;\n" +
                        "if x = 11 then\n" +
                        "    y := x * 2\n" +
                        "else\n" +
                        "    y := x * 3";

        File tempFile = File.createTempFile("testProgram", ".imp");
        tempFile.deleteOnExit();

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(program);
        }

        Statement ast = Parser.parseFile(tempFile.getAbsolutePath());

        // Expected AST
        // Build the AST manually
        Expression x = new VariableExpr("x");
        Statement stmt1 = new AssignStmt("x", new IntegerExpr(0));

        Conditional whileCond = new LeqCond(x, new IntegerExpr(10));
        Expression xPlusOne = new AddExpr(x, new IntegerExpr(1));
        Statement whileBody = new AssignStmt("x", xPlusOne);
        Statement whileStmt = new WhileStmt(whileCond, whileBody);

        Conditional ifCond = new EqualCond(x, new IntegerExpr(11));
        Statement thenStmt = new AssignStmt("y", new MulExpr(x, new IntegerExpr(2)));
        Statement elseStmt = new AssignStmt("y", new MulExpr(x, new IntegerExpr(3)));
        Statement ifStmt = new IfStmt(ifCond, thenStmt, elseStmt);

        Statement expectedAst = new SequenceStmt(
                new SequenceStmt(stmt1, whileStmt),
                ifStmt
        );

        // Compare ASTs
        assertStatementsEqual(expectedAst, ast);
    }

    // Helper methods to compare AST nodes
    private void assertStatementsEqual(Statement expected, Statement actual) {
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getClass(), actual.getClass());

        if (expected instanceof AssignStmt) {
            AssignStmt expectedAssign = (AssignStmt) expected;
            AssignStmt actualAssign = (AssignStmt) actual;
            Assert.assertEquals(expectedAssign.getVariable(), actualAssign.getVariable());
            assertExpressionsEqual(expectedAssign.getExpression(), actualAssign.getExpression());
        } else if (expected instanceof SequenceStmt) {
            SequenceStmt expectedSeq = (SequenceStmt) expected;
            SequenceStmt actualSeq = (SequenceStmt) actual;
            assertStatementsEqual(expectedSeq.getFirst(), actualSeq.getFirst());
            assertStatementsEqual(expectedSeq.getSecond(), actualSeq.getSecond());
        } else if (expected instanceof IfStmt) {
            IfStmt expectedIf = (IfStmt) expected;
            IfStmt actualIf = (IfStmt) actual;
            assertConditionalsEqual(expectedIf.getCondition(), actualIf.getCondition());
            assertStatementsEqual(expectedIf.getThenBranch(), actualIf.getThenBranch());
            assertStatementsEqual(expectedIf.getElseBranch(), actualIf.getElseBranch());
        } else if (expected instanceof WhileStmt) {
            WhileStmt expectedWhile = (WhileStmt) expected;
            WhileStmt actualWhile = (WhileStmt) actual;
            assertConditionalsEqual(expectedWhile.getCondition(), actualWhile.getCondition());
            assertStatementsEqual(expectedWhile.getBody(), actualWhile.getBody());
        } else {
            Assert.fail("Unknown statement type: " + expected.getClass());
        }
    }

    private void assertExpressionsEqual(Expression expected, Expression actual) {
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getClass(), actual.getClass());

        if (expected instanceof IntegerExpr) {
            IntegerExpr expectedInt = (IntegerExpr) expected;
            IntegerExpr actualInt = (IntegerExpr) actual;
            Assert.assertEquals(expectedInt.value(), actualInt.value());
        } else if (expected instanceof VariableExpr) {
            VariableExpr expectedVar = (VariableExpr) expected;
            VariableExpr actualVar = (VariableExpr) actual;
            Assert.assertEquals(expectedVar.name(), actualVar.name());
        } else if (expected instanceof AddExpr) {
            AddExpr expectedAdd = (AddExpr) expected;
            AddExpr actualAdd = (AddExpr) actual;
            assertExpressionsEqual(expectedAdd.left(), actualAdd.left());
            assertExpressionsEqual(expectedAdd.right(), actualAdd.right());
        } else if (expected instanceof MulExpr) {
            MulExpr expectedMul = (MulExpr) expected;
            MulExpr actualMul = (MulExpr) actual;
            assertExpressionsEqual(expectedMul.left(), actualMul.left());
            assertExpressionsEqual(expectedMul.right(), actualMul.right());
        } else {
            Assert.fail("Unknown expression type: " + expected.getClass());
        }
    }

    private void assertConditionalsEqual(Conditional expected, Conditional actual) {
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getClass(), actual.getClass());

        if (expected instanceof TrueCond) {
            // Nothing to compare for singleton
        } else if (expected instanceof FalseCond) {
            // Nothing to compare for singleton
        } else if (expected instanceof EqualCond expectedEq) {
            EqualCond actualEq = (EqualCond) actual;
            assertExpressionsEqual(expectedEq.left(), actualEq.left());
            assertExpressionsEqual(expectedEq.right(), actualEq.right());
        } else if (expected instanceof LeqCond expectedLeq) {
            LeqCond actualLeq = (LeqCond) actual;
            assertExpressionsEqual(expectedLeq.left(), actualLeq.left());
            assertExpressionsEqual(expectedLeq.right(), actualLeq.right());
        } else {
            Assert.fail("Unknown conditional type: " + expected.getClass());
        }
    }
}
