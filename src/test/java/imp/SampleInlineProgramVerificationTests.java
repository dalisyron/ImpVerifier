package imp;

import com.microsoft.z3.*;
import imp.ast.Program;
import imp.ast.method.MethodDeclaration;
import imp.parser.Parser;
import imp.verification.Method;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SampleInlineProgramVerificationTests {

    @Test
    public void testIntegerDivision() throws Exception {
        String program = """
                // Valid program: Computes the sum of the first n odd numbers
                method SumOfFirstNOddNumbers(int n) returns (int sum)
                    requires n >= 0
                    ensures sum == n * n
                {
                    sum = 0;
                    int i = 0;
                    while (i < n)
                        invariant sum == i * i
                        invariant i >= 0 && i <= n
                    {
                        sum = sum + 2 * i + 1;
                        i = i + 1;
                    }
                }
                """;
        assertProgramVerifies(program);
    }

    @Test
    public void testSumOfSquares() throws Exception {
        String program = """
                // Valid program: Computes the sum of squares of first n positive integers
                method SumOfSquares(int n) returns (int s)
                    requires n >= 0
                    ensures s == n * (n + 1) * (2 * n + 1) / 6
                {
                    s = 0;
                    int i = 1;
                    while (i <= n)
                        invariant s == (i - 1) * i * (2 * i - 1) / 6
                        invariant i >= 1 && i <= n + 1
                    {
                        s = s + i * i;
                        i = i + 1;
                    }
                }
                """;
        assertProgramVerifies(program);
    }

    @Test
    public void testTransitiveLessThan() throws Exception {
        String program = """
                // Valid program: Demonstrates transitivity of less than
                method TransitiveLessThan(int a, int b, int c)
                    requires a < b && b < c
                    ensures a < c
                {
                    a = a + 2;
                    c = c + 1;
                }
                """;
        assertProgramVerifies(program);
    }

    @Test
    public void testTransitiveLessThanInvalid() throws Exception {
        String program = """
                // Valid program: Demonstrates transitivity of less than
                method TransitiveLessThan(int a, int b, int c)
                    requires a < b && b < c
                    ensures a < c
                {
                    a = a + 3;
                    c = c + 1;
                }
                """;
        assertProgramNotVerifies(program);
    }

    @Test
    public void testSumOfFirstNNaturalNumbers() throws Exception {
        String program = """
                // Valid program: Computes the sum of the first n natural numbers
                method SumOfFirstNNaturalNumbers(int n) returns (int sum)
                    requires n >= 0
                    ensures sum == n * (n + 1) / 2
                {
                    sum = 0;
                    int i = 1;
                    while (i <= n)
                        invariant sum == i * (i - 1) / 2
                        invariant i >= 1 && i <= n + 1
                    {
                        sum = sum + i;
                        i = i + 1;
                    }
                }
                """;
        assertProgramVerifies(program);
    }


    @Test
    public void testLawOfExcludedMiddle() throws Exception {
        String program = """
                // Valid program: Demonstrates the Law of Excluded Middle
                method LawOfExcludedMiddle(bool P) returns (bool result)
                    ensures result == true
                {
                    result = P || !P;
                }
                """;
        assertProgramVerifies(program);
    }

    // Valid program: Checks that multiplying by zero results in zero
    @Test
    public void testMultiplicationByZero() throws Exception {
        String program = """
                // Valid program: Checks that multiplying by zero results in zero
                method MultiplicationByZero(int a) returns (int result)
                    ensures result == 0
                {
                    result = a * 0;
                }
                """;
        assertProgramVerifies(program);
    }

    @Test
    public void testIncrementInLoop() throws Exception {
        String program = """
                // Valid program: Increments x by n using a loop
                method IncrementInLoop(int x, int n) returns (int result)
                    requires n >= 0
                    ensures result == x + n
                {
                    result = x;
                    int i = 0;
                    while (i < n)
                        invariant result == x + i
                        invariant i >= 0 && i <= n
                    {
                        result = result + 1;
                        i = i + 1;
                    }
                }
                """;
        assertProgramVerifies(program);
    }

    // Valid program: Verifies DeMorgan's Law for AND
    @Test
    public void testDeMorganLawAND() throws Exception {
        String program = """
                // Valid program: Verifies DeMorgan's Law for AND
                method DeMorganLawAND(bool A, bool B) returns (bool result)
                    ensures result == true
                {
                    result = !(A && B) == (!A || !B);
                }
                """;
        assertProgramVerifies(program);
    }

    @Test
    public void testDistributiveProperty() throws Exception {
        String program = """
                // Valid program: Verifies the distributive property
                method DistributiveProperty(int a, int b, int c) returns (int result)
                    ensures result == a * b + a * c
                {
                    result = a * (b + c);
                }
                """;
        assertProgramVerifies(program);
    }

    @Test
    public void testDistributivePropertyInvalid() throws Exception {
        String program = """
                // Invalid program: Incorrectly applies distributive property
                method DistributivePropertyInvalid(int a, int b, int c) returns (int result)
                    ensures result == a * b + a * c
                {
                    result = a * b + c; // Error here
                }
                """;
        assertProgramNotVerifies(program);
    }

    // Test 25: Valid program checking that multiplying negative one inverts sign
    @Test
    public void testMultiplyByNegativeOne() throws Exception {
        String program = """
                // Valid program: Verifies that multiplying by -1 inverts the sign
                method MultiplyByNegativeOne(int a) returns (int result)
                    ensures result == -a
                {
                    result = a * -1;
                }
                """;
        assertProgramVerifies(program);
    }

    @Test
    public void testIsPrime() throws Exception {
        String program = """
                // Valid program: Checks if a number n > 1 is prime
                method IsPrime(int n) returns (bool isPrime)
                    requires n > 1
                    ensures isPrime == (forall (int i) :: (2 <= i && i <= n / 2) ==> !(n % i == 0))
                {
                    isPrime = true;
                    int i = 2;
                    while (i <= n / 2)
                        invariant isPrime == (forall (int j) :: (2 <= j && j < i) ==> !(n % j == 0))
                        invariant i >= 2 && i <= n / 2 + 1
                    {
                        if (n % i == 0) {
                            isPrime = false;
                        }
                        i = i + 1;
                    }
                }
                """;
        assertProgramVerifies(program);
    }

    @Test
    public void testNestedIfElse() throws Exception {
        String program = """
                // Valid program: Determines the sign of an integer
                method DetermineSign(int x) returns (int sign)
                    ensures ((x > 0) && (sign == 1)) || ((x == 0) && (sign == 0)) || ((x < 0) && (sign == -1))
                {
                    if (x > 0) {
                        sign = 1;
                    } else {
                        if (x == 0) {
                            sign = 0;
                        } else {
                            sign = -1;
                        }
                    }
                }
                """;
        assertProgramVerifies(program);
    }

    @Test
    public void testLogicalEquivalence() throws Exception {
        String program = """
                // Valid program: Verifies logical equivalence between two expressions
                method LogicalEquivalence(bool A, bool B) returns (bool result)
                    ensures result == true
                {
                    result = (A && B) == !( !A || !B );
                }
                """;
        assertProgramVerifies(program);
    }

    @Test
    public void testLogicalEquivalenceInvalid() throws Exception {
        String program = """
                // Invalid program: Incorrect logical equivalence
                method LogicalEquivalenceInvalid(bool A, bool B) returns (bool result)
                    ensures result == true
                {
                    result = (A || B) == ( !A && !B ); // Error here
                }
                """;
        assertProgramNotVerifies(program);
    }

    // Important Test
    @Test
    public void testIntBoolCombination() throws Exception {
        String program = """
                // Valid program: Uses both int and bool in computations
                method IntBoolCombination(int x, int y) returns (int result)
                    ensures ((x > y) && (result == x)) || ((x <= y) && (result == y))
                {
                    bool condition = x > y;
                    if (condition) {
                        result = x;
                    } else {
                        result = y;
                    }
                }
                """;
        assertProgramVerifies(program);
    }

    @Test
    public void testAbsoluteValue() throws Exception {
        String program = """
                // Valid program: Computes the absolute value of an integer
                method AbsoluteValue(int x) returns (int abs)
                    ensures (abs >= 0) && ((abs == x) || (abs == -x))
                {
                    if (x < 0) {
                        abs = -x;
                    } else {
                        abs = x;
                    }
                }
                """;
        assertProgramVerifies(program);
    }

    @Test
    public void testAbsoluteValueInvalid() throws Exception {
        String program = """
                // Invalid program: Incorrectly computes absolute value
                method AbsoluteValueInvalid(int x) returns (int abs)
                    ensures (abs >= 0) && ((abs == x) || (abs == -x))
                {
                    if (x < 0) {
                        abs = x; // Error here, should be -x
                    } else {
                        abs = x;
                    }
                }
                """;
        assertProgramNotVerifies(program);
    }

    @Test
    public void testMaxOfThree() throws Exception {
        String program = """
                // Valid program: Finds the maximum of three numbers
                method MaxOfThree(int a, int b, int c) returns (int max)
                    ensures max >= a && max >= b && max >= c && (max == a || max == b || max == c)
                {
                    if ((a >= b) && (a >= c)) {
                        max = a;
                    } else {
                        if ((b >= a) && (b >= c)) {
                            max = b;
                        } else {
                            max = c;
                        }
                    }
                }
                """;
        assertProgramVerifies(program);
    }


    // Helper method to assert that a program is valid
    private void assertProgramVerifies(String programText) throws Exception {
        Program program = Parser.parseStringCheckingTypes(programText);
        List<MethodDeclaration> methods = program.methods();
        Context ctx = new Context();

        for (MethodDeclaration method : methods) {
            boolean valid = Method.getInstance().Verify(ctx, method);
            if (valid) {
                System.out.printf("Method %s is valid%n", method.name());
            } else {
                System.out.printf("Method %s is invalid%n", method.name());
            }
            Assert.assertTrue("Expected method " + method.name() + " to be valid", valid);
        }
    }

    // Helper method to assert that a program is invalid
    private void assertProgramNotVerifies(String programText) throws Exception {
        Program program = Parser.parseStringCheckingTypes(programText);
        List<MethodDeclaration> methods = program.methods();
        Context ctx = new Context();

        for (MethodDeclaration method : methods) {
            boolean valid = Method.getInstance().Verify(ctx, method);
            if (valid) {
                System.out.printf("Method %s is valid%n", method.name());
            } else {
                System.out.printf("Method %s is invalid%n", method.name());
            }
            Assert.assertFalse("Expected method " + method.name() + " to be invalid", valid);
        }
    }
}
