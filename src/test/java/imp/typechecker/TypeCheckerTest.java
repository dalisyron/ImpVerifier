package imp.typechecker;

import imp.ast.Program;
import imp.parser.ASTBuilder;
import imp.parser.Parser;
import imp.typechecker.TypeChecker.TypeError;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TypeCheckerTest {

    // Test cases that should pass type checking
    @Test
    void testValidProgram1() {
        String program = """
                method Foo()
                {
                    int x = 10;
                    x = x + 5;
                }
                """;
        testTypeCheckerOnProgramString(program);
    }

    @Test
    void testValidProgram2() {
        String program = """
                method Bar(int y)
                {
                    int[] arr = new int[10];
                    arr[0] = y;
                }
                """;
        testTypeCheckerOnProgramString(program);
    }

    @Test
    void testValidProgram3() {
        String program = """
                method Baz()
                {
                    bool flag = true;
                    if (flag) {
                        flag = false;
                    }
                }
                """;
        testTypeCheckerOnProgramString(program);
    }

    @Test
    void testValidProgram4() {
        String program = """
                method Factorial(int n) returns (int result)
                    requires n >= 0
                    ensures result >= 1
                {
                    result = 1;
                    while (n > 1)
                    {
                        result = result * n;
                        n = n - 1;
                    }
                }
                """;
        testTypeCheckerOnProgramString(program);
    }

    @Test
    void testValidProgram6() {
        String program = """
                method Max(int a, int b) returns (int max)
                {
                    if (a > b) {
                        max = a;
                    } else {
                        max = b;
                    }
                }
                """;
        testTypeCheckerOnProgramString(program);
    }

    @Test
    void testValidProgram7() {
        String program = """
                method SumArray(int[] arr, int length) returns (int sum)
                {
                    sum = 0;
                    int i = 0;
                    while (i < length)
                        invariant forall (int j) :: 0 <= j && j < i ==> sum >= arr[j]
                        invariant exists (int k) :: (0 <= k) && (k < 10) && (arr[k] == sum)
                    {
                        sum = sum + arr[i];
                        i = i + 1;
                    }
                }
                """;
        testTypeCheckerOnProgramString(program);
    }

    @Test
    void testValidProgram8() {
        String program = """
                method LogicalOps(bool x, bool y) returns (bool result)
                {
                    result = x && y || !x;
                }
                """;
        testTypeCheckerOnProgramString(program);
    }

    @Test
    void testValidProgram9() {
        String program = """
                method NestedIfs(int x)
                {
                    if (x > 0) {
                        if (x < 10) {
                            x = x * 2;
                        }
                    }
                }
                """;
        testTypeCheckerOnProgramString(program);
    }

    @Test
    void testValidProgram10() {
        String program = """
                method ForallExample()
                    ensures forall (int i) :: i >= 0 ==> i * 2 >= 0
                {
                }
                """;
        testTypeCheckerOnProgramString(program);
    }

    @Test
    void testValidProgram11() {
        String program = """
                method ReverseArray(int[] arr, int length)
                    requires length >= 0 && length <= arr.length
                {
                    int i = 0;
                    int j = length - 1;
                    while (i < j)
                        invariant 0 <= i && i <= j && j < length
                    {
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                        i = i + 1;
                        j = j - 1;
                    }
                }
                """;
        testTypeCheckerOnProgramString(program);
    }

    @Test
    void testValidProgram12() {
        String program = """
                method InitMatrix(int n) returns (int[] matrix)
                    requires n > 0
                {
                    matrix = new int[10];
                    int i = 0;
                    while (i < n)
                        invariant 0 <= i && i <= n
                    {
                        matrix = new int[n];
                        int j = 0;
                        while (j < n)
                            invariant 0 <= j && j <= n
                        {
                            matrix[j] = i * n + j;
                            j = j + 1;
                        }
                        i = i + 1;
                    }
                }
                """;
        testTypeCheckerOnProgramString(program);
    }

    @Test
    void testValidProgram14() {
        String program = """
                method ImplicationExample(bool x, bool y) returns (bool result)
                {
                    result = x ==> y;
                }
                """;
        testTypeCheckerOnProgramString(program);
    }

    @Test
    void testValidProgram15() {
        String program = """
                method NestedQuantifiers(int n)
                    requires n > 0
                    ensures forall (int i) :: 0 <= i && i < n ==>
                        exists (int j) :: 0 <= j && j < n && !(i == j)
                {
                }
                """;
        testTypeCheckerOnProgramString(program);
    }

    @Test
    void testInvalidProgram__16() {
        String program = """
                method VariableShadowing()
                {
                    int x = 5;
                    {
                        int x = 10; // Variable already declared in outer scope
                        x = x + 1;
                    }
                    x = x + 1;
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testValidProgram18() {
        String program = """
                method Add(int x, int y) returns (int result)
                {
                    result = x + y;
                }
                
                method UseAdd()
                {
                    int sum = Add(5, 10);
                }
                """;
        testTypeCheckerOnProgramString(program);
    }

    @Test
    void testValidProgram19() {
        String program = """
                method Multiply(int x, int y) returns (int result)
                {
                    result = x * y;
                }
                
                method Compute()
                {
                    int a = 5;
                    int b = 10;
                    int c = Multiply(a, b);
                }
                """;
        testTypeCheckerOnProgramString(program);
    }

    @Test
    void testValidProgram20() {
        String program = """
                method ProcessArray(int[] arr)
                {
                    int len = arr.length;
                    int i = 0;
                    while (i < len)
                    {
                        arr[i] = arr[i] * 2;
                        i = i + 1;
                    }
                }
                """;
        testTypeCheckerOnProgramString(program);
    }

    @Test
    void testValidProgram21() {
        String program = """
                method ComplexExpressions(int x, int y) returns (int result)
                {
                    result = (x + y) * (x - y) / (x * y);
                }
                """;
        testTypeCheckerOnProgramString(program);
    }

    @Test
    void testValidProgram22() {
        String program = """
                method NotExample(bool a, bool b) returns (bool result)
                {
                    result = !(a && b || !a);
                }
                """;
        testTypeCheckerOnProgramString(program);
    }

    // Test cases that should fail type checking
    @Test
    void testInvalidProgram1() {
        String program = """
                method Fail1()
                {
                    int x = true; // Invalid assignment of bool to int
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram2() {
        String program = """
                method Fail2()
                {
                    bool flag;
                    flag = 1; // Invalid assignment of int to bool
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram3() {
        String program = """
                method Fail3()
                {
                    x = 10; // Undeclared variable 'x'
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram4() {
        String program = """
                method Fail4()
                {
                    int x;
                    int x; // Redeclaration of variable 'x'
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram5() {
        String program = """
                method Fail5()
                {
                    int[] arr = new int[5];
                    arr[true] = 10; // Invalid array index type
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram6() {
        String program = """
                method Fail6()
                {
                    int x = 5 + true; // Invalid operand types for '+'
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram7() {
        String program = """
                method Fail7()
                {
                    if (10) { // Condition must be of type bool
                        int x = 0;
                    }
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram8() {
        String program = """
                method Fail8()
                {
                    int x;
                    x = new int[5]; // Cannot assign int[] to int
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram9() {
        String program = """
                method Fail9()
                {
                    int x = !5; // Invalid operand type for '!'
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram10() {
        String program = """
                method Fail10()
                {
                    while (x > 0) // 'x' is undeclared
                    {
                        x = x - 1;
                    }
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram11() {
        String program = """
                method Fail11()
                {
                    int[] arr = new int[5];
                    arr[0] = true; // Cannot assign bool to int
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram12() {
        String program = """
                method Fail12()
                {
                    bool result = forall (bool i) :: i < 10; // Invalid type in quantifier variable
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram13() {
        String program = """
                method Fail13()
                {
                    int x = arr[0]; // 'arr' is undeclared
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testValidProgram__14() {
        String program = """
                method Validdd14()
                {
                    int[] arr = new int[5];
                    int len = arr.length; // Invalid use of array length
                }
                """;
        testTypeCheckerOnProgramString(program);
    }

    @Test
    void testInvalidProgram15() {
        String program = """
                method Fail15()
                {
                    int x = 10;
                    int len = x.length; // Cannot get length of non-array type
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram16() {
        String program = """
                method Fail16()
                {
                    bool flag = true;
                    int x = flag[0]; // Cannot index a bool
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram17() {
        String program = """
                method Fail17()
                {
                    int x = 5;
                    bool y = x ==> true; // Cannot use 'implies' with int
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram18() {
        String program = """
                method Fail18()
                {
                    int x = 5;
                    x = new int[10]; // Cannot assign int[] to int
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram19() {
        String program = """
                method Fail19()
                {
                    int x = 5;
                    int x = 10; // Redeclaration of 'x' in the same scope
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram20() {
        String program = """
                method Fail20()
                {
                    int[] arr = new int[5];
                    arr[0] = new int[5]; // Cannot assign int[] to int
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram21() {
        String program = """
                method Fail21()
                {
                    int x = 5;
                    x = x + true; // Invalid operand types for '+'
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram22() {
        String program = """
                method Add(int x, int y) returns (int result)
                {
                    result = x + y;
                }
                
                method Fail22()
                {
                    int sum = Add(5, true); // Invalid argument type
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram23() {
        String program = """
                method Fail23()
                {
                    int sum = NonExistentFunction(5); // Unknown function
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram24() {
        String program = """
                method GetNumber() returns (int result)
                {
                    result = 42;
                }
                
                method Fail24()
                {
                    bool flag = GetNumber(); // Cannot assign int to bool
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram25() {
        String program = """
                method Fail25()
                {
                    int[] arr = new int[5];
                    int index = true; // Invalid index type
                    arr[index] = 10;
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    @Test
    void testInvalidProgram26() {
        String program = """
                method Fail26()
                {
                    int x = 5;
                    bool y = !x; // Invalid operand type for '!'
                }
                """;
        Assertions.assertThrows(TypeError.class, () -> testTypeCheckerOnProgramString(program));
    }

    // Helper method to test the type checker on a program string
    private void testTypeCheckerOnProgramString(String program) {
        try {
            // Parse the input program string
            Program ast = Parser.parseString(program);

            // Run the TypeChecker on the AST
            TypeChecker.checkTypes(ast);

            // If no exception is thrown, the test passes
            Assertions.assertTrue(true); // Explicitly state that the test passed
        } catch (TypeError e) {
            throw e; // Rethrow to be caught in the calling method
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Exception occurred during type checking of program string");
        }
    }
}
