// TypeChecker.java
package imp.typechecker;

import imp.ast.*;
import imp.ast.condition.*;
import imp.ast.expression.*;
import imp.ast.expression.array.NewArrayExpression;
import imp.ast.expression.bool.ExistsExpression;
import imp.ast.expression.bool.ForallExpression;
import imp.ast.expression.constant.bool.FalseExpression;
import imp.ast.expression.constant.bool.TrueExpression;
import imp.ast.expression.constant.integer.IntExpression;
import imp.ast.method.*;
import imp.ast.statement.*;
import imp.ast.typing.*;
import imp.ast.typing.data.DataType;
import imp.ast.typing.data.array.ArrayType;
import imp.ast.typing.data.array.BoolArray;
import imp.ast.typing.data.array.IntArray;
import imp.ast.typing.data.value.BoolType;
import imp.ast.typing.data.value.IntType;
import imp.ast.expression.Identifier;

import java.util.*;

public class TypeChecker {

    public static void checkTypes(Program program) {
        TypeCheckVisitor visitor = new TypeCheckVisitor();
        program.accept(visitor);
    }

    private static class TypeCheckVisitor extends ASTVisitor {
        private TypeSymbolTable symTab;
        private Map<Identifier, FunctionType> functionSymTab;
        private Type lastType;

        public TypeCheckVisitor() {
            symTab = new TypeSymbolTable();
            functionSymTab = new HashMap<>();
            lastType = null;
        }

        @Override
        public void visit(Program program) {
            // First, collect all method signatures
            for (MethodDeclaration method : program.methods()) {
                collectMethodSignature(method);
            }
            // Now, type-check each method
            for (MethodDeclaration method : program.methods()) {
                method.accept(this);
            }
        }

        private void collectMethodSignature(MethodDeclaration methodDeclaration) {
            List<DataType> paramTypes = methodDeclaration.parameterList().parameters().stream()
                    .map(Parameter::type)
                    .toList();

            // Determine return type
            DataType returnType = Optional.ofNullable(methodDeclaration.returnValue()).map(ReturnValue::type).orElse(VoidType.getInstance());
            // Create FunctionType
            FunctionType methodType = new FunctionType(paramTypes, returnType);
            // Add to function symbol table
            functionSymTab.put(methodDeclaration.name(), methodType);
        }

        @Override
        public void visit(MethodDeclaration methodDeclaration) {
            symTab.pushState();

            // Add parameters to symbol table
            methodDeclaration.parameterList().accept(this);

            // **Add return variable to symbol table if present**
            if (methodDeclaration.returnValue() != null) {
                methodDeclaration.returnValue().accept(this);
            }

            // Process conditions (requires and ensures)
            methodDeclaration.conditionList().accept(this);

            // Visit method body
            methodDeclaration.methodBody().accept(this);

            symTab.popState();
        }

        @Override
        public void visit(ParameterList parameterList) {
            for (Parameter param : parameterList.parameters()) {
                param.accept(this);
            }
        }

        @Override
        public void visit(Parameter parameter) {
            if (symTab.containsKey(parameter.name())) {
                throw new TypeError("Parameter " + parameter.name() + " already declared.");
            }
            symTab.put(parameter.name(), parameter.type());
        }

        @Override
        public void visit(ReturnValue returnValue) {
            Identifier returnVarName = returnValue.name();
            Type returnType = returnValue.type();
            // Check for redeclaration
            if (symTab.containsKey(returnVarName)) {
                throw new TypeError("Return variable " + returnVarName + " already declared as a parameter.");
            }
            symTab.put(returnVarName, returnType);
        }

        @Override
        public void visit(ConditionList conditionList) {
            for (ConditionClause clause : conditionList.conditions()) {
                clause.accept(this);
            }
        }

        @Override
        public void visit(EnsuresClause ensuresClause) {
            ensuresClause.expr().accept(this);
            if (!(lastType instanceof BoolType)) {
                throw new TypeError("Ensures clause must be of type bool.");
            }
        }

        @Override
        public void visit(RequiresClause requiresClause) {
            requiresClause.expr().accept(this);
            if (!(lastType instanceof BoolType)) {
                throw new TypeError("Requires clause must be of type bool.");
            }
        }

        @Override
        public void visit(InvariantList invariantList) {
            for (Invariant invariant : invariantList.invariants()) {
                invariant.accept(this);
            }
        }

        @Override
        public void visit(Invariant invariant) {
            invariant.expression().accept(this);
            if (!(lastType instanceof BoolType)) {
                throw new TypeError("Invariant expression must be of type bool.");
            }
        }

        @Override
        public void visit(MethodBody methodBody) {
            for (Statement stmt : methodBody.statements()) {
                stmt.accept(this);
            }
        }

        @Override
        public void visit(VariableDeclaration variableDeclaration) {
            Identifier varName = variableDeclaration.variableName();
            Type varType = variableDeclaration.declaredType();
            if (symTab.containsKey(varName)) {
                throw new TypeError("Variable " + varName + " already declared.");
            }
            symTab.put(varName, varType);

            if (variableDeclaration.initializer().isPresent()) {
                variableDeclaration.initializer().get().accept(this);
                Type initType = lastType;
                if (!canBeAssigned(initType, varType)) {
                    throw new TypeError("Cannot assign " + initType + " to variable " + varName + " of type " + varType);
                }
            }
        }

        @Override
        public void visit(AssignStatement assignStatement) {
            assignStatement.lhs().accept(this);
            Type lhsType = lastType;

            assignStatement.expression().accept(this);
            Type rhsType = lastType;

            if (!canBeAssigned(rhsType, lhsType)) {
                throw new TypeError("Cannot assign " + rhsType + " to " + assignStatement.lhs());
            }
        }

        @Override
        public void visit(IfStatement ifStatement) {
            ifStatement.condition().accept(this);
            if (!(lastType instanceof BoolType)) {
                throw new TypeError("If condition must be of type bool.");
            }

            symTab.pushState();
            ifStatement.thenBlock().accept(this);
            symTab.popState();

            if (ifStatement.elseBlock().isPresent()) {
                symTab.pushState();
                ifStatement.elseBlock().get().accept(this);
                symTab.popState();
            }
        }

        @Override
        public void visit(WhileStatement whileStatement) {
            whileStatement.condition().accept(this);
            if (!(lastType instanceof BoolType)) {
                throw new TypeError("While condition must be of type bool.");
            }

            if (!whileStatement.invariants().invariants().isEmpty()) {
                whileStatement.invariants().accept(this);
            }

            symTab.pushState();
            whileStatement.body().accept(this);
            symTab.popState();
        }

        @Override
        public void visit(BlockStatement blockStatement) {
            symTab.pushState();
            for (Statement stmt : blockStatement.statements()) {
                stmt.accept(this);
            }
            symTab.popState();
        }

        @Override
        public void visit(ExpressionStatement expressionStatement) {
            expressionStatement.expression().accept(this);
        }

        @Override
        public void visit(VarRefExpression varRefExpression) {
            Identifier varName = varRefExpression.variableName();
            Type varType = symTab.get(varName);
            if (varType == null) {
                throw new TypeError("Variable " + varName + " is not declared.");
            }
            lastType = varType;
            if (!(lastType instanceof DataType)) {
                throw new TypeError("Variable " + varName + " is not a data type.");
            }
            varRefExpression.setType((DataType) lastType);
        }

        @Override
        public void visit(ArrayRefExpression arrayRefExpression) {
            Identifier arrayName = arrayRefExpression.arrayName();
            Type arrayType = symTab.get(arrayName);
            if (arrayType == null) {
                throw new TypeError("Array " + arrayName + " is not declared.");
            }
            if (!(arrayType instanceof ArrayType)) {
                throw new TypeError(arrayName + " is not an array.");
            }

            arrayRefExpression.indexExpr().accept(this);
            if (!(lastType instanceof IntType)) {
                throw new TypeError("Array index must be of type int.");
            }

            if (arrayType instanceof BoolArray) {
                lastType = BoolType.getInstance();
                arrayRefExpression.setElementType(BoolType.getInstance());
            } else {
                arrayRefExpression.setElementType(IntType.getInstance());
                lastType = IntType.getInstance();
            }
        }

        @Override
        public void visit(BinaryOpExpression binaryOpExpression) {
            binaryOpExpression.left().accept(this);
            Type leftType = lastType;

            binaryOpExpression.right().accept(this);
            Type rightType = lastType;

            String op = binaryOpExpression.operatorSymbol();
            Type resultType = getBinaryOpResultType(op, leftType, rightType);

            if (resultType == null) {
                throw new TypeError("Invalid operands for operator '" + op + "' in expression '" + binaryOpExpression + "': " + leftType + ", " + rightType);
            }
            lastType = resultType;
        }

        @Override
        public void visit(UnaryExpression unaryExpression) {
            unaryExpression.expression().accept(this);
            Type operandType = lastType;

            String op = unaryExpression.operatorSymbol();
            Type resultType = getUnaryOpResultType(op, operandType);

            if (resultType == null) {
                throw new TypeError("Invalid operand for operator '" + op + "' in expression '" + unaryExpression + "': " + operandType);
            }
            lastType = resultType;
        }

        @Override
        public void visit(IntExpression intExpression) {
            lastType = IntType.getInstance();
        }

        @Override
        public void visit(FalseExpression falseExpression) {
            lastType = BoolType.getInstance();
        }

        @Override
        public void visit(TrueExpression trueExpression) {
            lastType = BoolType.getInstance();
        }

        @Override
        public void visit(Condition condition) {
            condition.expression().accept(this);
            if (!(lastType instanceof BoolType)) {
                throw new TypeError("Condition expression must be of type bool.");
            }
        }

        @Override
        public void visit(FuncCallExpression funcCallExpression) {
            Identifier funcName = funcCallExpression.functionName();
            FunctionType funcType = functionSymTab.get(funcName);
            if (funcType == null) {
                throw new TypeError("Unknown function: " + funcName);
            }

            List<Expression> args = funcCallExpression.arguments();
            if (args.size() != funcType.getParameterTypes().size()) {
                throw new TypeError("Function " + funcName + " expects " + funcType.getParameterTypes().size() + " arguments, but got " + args.size());
            }

            for (int i = 0; i < args.size(); i++) {
                args.get(i).accept(this);
                Type argType = lastType;
                Type expectedType = funcType.getParameterTypes().get(i);
                if (!canBeAssigned(argType, expectedType)) {
                    throw new TypeError("Function " + funcName + " argument " + (i + 1) + " expects type " + expectedType + ", but got " + argType);
                }
            }
            funcCallExpression.setType(funcType);

            lastType = funcType.getReturnType();
        }

        @Override
        public void visit(NewArrayExpression newArrayExpression) {
            newArrayExpression.sizeExpr().accept(this);
            if (!(lastType instanceof IntType)) {
                throw new TypeError("Array size must be of type int.");
            }
            if (newArrayExpression.elementType() instanceof BoolType) {
                lastType = BoolArray.getInstance();
            } else {
                lastType = IntArray.getInstance();
            }
        }

        @Override
        public void visit(ArrayLengthExpression arrayLengthExpression) {
            arrayLengthExpression.arrayExpr().accept(this);
            if (!(lastType instanceof ArrayType)) {
                throw new TypeError("Cannot get length of non-array type.");
            }
            lastType = IntType.getInstance();
        }

        @Override
        public void visit(ForallExpression forallExpression) {
            symTab.pushState();
            symTab.put(forallExpression.variable(), forallExpression.getType());
            forallExpression.body().accept(this);
            if (!(lastType instanceof BoolType)) {
                throw new TypeError("Body of forall expression must be of type bool.");
            }
            symTab.popState();
            lastType = BoolType.getInstance();
        }

        @Override
        public void visit(ExistsExpression existsExpression) {
            symTab.pushState();
            symTab.put(existsExpression.variable(), existsExpression.getType());
            existsExpression.body().accept(this);
            if (!(lastType instanceof BoolType)) {
                throw new TypeError("Body of exists expression must be of type bool.");
            }
            symTab.popState();
            lastType = BoolType.getInstance();
        }

        @Override
        public void visit(Identifier identifier) {
            // Typically handled in VarRefExpression
        }

        // Helper methods
        private boolean canBeAssigned(Type from, Type to) {
            return from.equals(to);
        }

        private Type getBinaryOpResultType(String op, Type leftType, Type rightType) {
            if (op.matches("\\+|\\-|\\*|/|%")) {
                if (leftType instanceof IntType && rightType instanceof IntType) {
                    return IntType.getInstance();
                }
            } else if (op.matches("&&|\\|\\|")) {
                if (leftType instanceof BoolType && rightType instanceof BoolType) {
                    return BoolType.getInstance();
                }
            } else if (op.matches("<|>|<=|>=")) {
                if (leftType instanceof IntType && rightType instanceof IntType) {
                    return BoolType.getInstance();
                }
            } else if (op.matches("==")) {
                if (leftType instanceof IntType && rightType instanceof IntType) {
                    return BoolType.getInstance();
                }
                if (leftType instanceof BoolType && rightType instanceof BoolType) {
                    return BoolType.getInstance();
                }
            }
            else if (op.equals("==>")) { // Implies operator
                if (leftType instanceof BoolType && rightType instanceof BoolType) {
                    return BoolType.getInstance();
                }
            }
            return null;
        }

        private Type getUnaryOpResultType(String op, Type operandType) {
            if (op.equals("-") && operandType instanceof IntType) {
                return IntType.getInstance();
            } else if (op.equals("!") && operandType instanceof BoolType) {
                return BoolType.getInstance();
            }
            return null;
        }
    }

    // Custom exception for type errors
    public static class TypeError extends RuntimeException {
        public TypeError(String message) {
            super("TypeError: " + message);
        }
    }
}
