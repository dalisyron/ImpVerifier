package imp.print;

import imp.ast.*;
import imp.ast.condition.ConditionClause;
import imp.ast.condition.ConditionList;
import imp.ast.condition.EnsuresClause;
import imp.ast.condition.RequiresClause;
import imp.ast.expression.*;
import imp.ast.expression.array.NewArrayExpression;
import imp.ast.expression.binary.bool.compare.*;
import imp.ast.expression.binary.bool.logic.AndExpression;
import imp.ast.expression.binary.bool.logic.ImpliesExpression;
import imp.ast.expression.binary.bool.logic.OrExpression;
import imp.ast.expression.binary.integer.*;
import imp.ast.expression.bool.ExistsExpression;
import imp.ast.expression.bool.ForallExpression;
import imp.ast.expression.constant.bool.FalseExpression;
import imp.ast.expression.constant.bool.TrueExpression;
import imp.ast.expression.constant.integer.IntExpression;
import imp.ast.expression.unary.bool.NotExpression;
import imp.ast.expression.unary.integer.NegExpression;
import imp.ast.method.*;
import imp.ast.statement.*;
import imp.ast.expression.Identifier;
import imp.ast.typing.FunctionType;
import imp.ast.typing.VoidType;
import imp.ast.typing.data.array.BoolArray;
import imp.ast.typing.data.array.IntArray;
import imp.ast.typing.data.value.BoolType;
import imp.ast.typing.data.value.IntType;
import imp.ast.ASTVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * Usage:
 *   String printed = ImpPrettyPrinter.getInstance().prettyPrint(programAST);
 */
public final class ImpPrettyPrinter extends ASTVisitor {

    // ------------------------------------------------------------
    // 1) Singleton pattern
    // ------------------------------------------------------------

    private static final ImpPrettyPrinter INSTANCE = new ImpPrettyPrinter();

    /**
     * A single field to hold the most recent string result during a visit.
     */
    private String result;

    private ImpPrettyPrinter() {
    }

    public static ImpPrettyPrinter getInstance() {
        return INSTANCE;
    }

    /**
     * Public entry point: pretty-print an entire ASTNode.
     */
    public String prettyPrint(ASTNode node) {
        node.accept(this);      // triggers visit() for the node
        return result;             // result is set in that visit method
    }

    // ------------------------------------------------------------
    // 2) Helper for recursion
    // ------------------------------------------------------------

    /**
     * Convenience method to:
     *   1) accept 'node' with this visitor
     *   2) return the 'result' we produced
     */
    private String visitAndGet(ASTNode node) {
        node.accept(this);
        return result;
    }

    /**
     * Similar helper for a list of AST nodes, returning a list of their string representations.
     */
    private List<String> visitAll(List<? extends ASTNode> nodeList) {
        List<String> strings = new ArrayList<>();
        for (ASTNode n : nodeList) {
            strings.add(visitAndGet(n));
        }
        return strings;
    }

    // ------------------------------------------------------------
    // 3) Visitor methods for each AST node
    // ------------------------------------------------------------

    @Override
    public void visit(Program program) {
        // Old logic: String.join("\n\n", methods.stream().map(...).toList());
        List<String> methodStrings = visitAll(program.methods());
        this.result = String.join("\n\n", methodStrings);
    }

    @Override
    public void visit(MethodDeclaration methodDeclaration) {
        StringBuilder sb = new StringBuilder();

        sb.append("method ");
        sb.append(methodDeclaration.name());

        // parameter list or ()
        if (!methodDeclaration.parameterList().isEmpty()) {
            sb.append(visitAndGet(methodDeclaration.parameterList()));
        } else {
            sb.append("()");
        }

        // return value
        if (methodDeclaration.returnValue() != null) {
            sb.append(" returns (")
                    .append(visitAndGet(methodDeclaration.returnValue()))
                    .append(")");
        }

        // condition list
        if (!methodDeclaration.conditionList().isEmpty()) {
            sb.append("\n");
            sb.append(visitAndGet(methodDeclaration.conditionList()));
        }

        // method body
        sb.append(" {\n");
        sb.append(visitAndGet(methodDeclaration.methodBody()));
        sb.append("\n}");

        this.result = sb.toString();
    }

    @Override
    public void visit(ParameterList parameterList) {
        /*
         * Representation:
         *   "(" + param1, param2, ... + ")"
         */
        List<Parameter> params = parameterList.parameters();
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < params.size(); i++) {
            sb.append(visitAndGet(params.get(i)));
            if (i < params.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        this.result = sb.toString();
    }

    @Override
    public void visit(Parameter parameter) {
        /*
         * Old toString():
         *   type + " " + name
         */
        // parameter.type() is not necessarily an ASTNode, so we call its .toString() directly
        String typeString = parameter.type() != null ? parameter.type().toString() : "nulltype";
        String nameString = parameter.name().name();
        this.result = typeString + " " + nameString;
    }

    @Override
    public void visit(ReturnValue returnValue) {
        /*
         * Old toString():
         *   type + " " + name
         */
        String typeString = returnValue.type() != null ? returnValue.type().toString() : "nulltype";
        String nameString = returnValue.name().name();
        this.result = typeString + " " + nameString;
    }

    @Override
    public void visit(ConditionList conditionList) {
        /*
         * Old toString():
         *   just prints each condition, each on its own line
         */
        List<? extends ConditionClause> conditions = conditionList.conditions();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < conditions.size(); i++) {
            sb.append(visitAndGet(conditions.get(i)));
            if (i < conditions.size() - 1) {
                sb.append("\n");
            }
        }
        this.result = sb.toString();
    }

    @Override
    public void visit(EnsuresClause ensuresClause) {
        // "ensures " + expression
        String exprStr = visitAndGet(ensuresClause.expr());
        this.result = "ensures " + exprStr;
    }

    @Override
    public void visit(RequiresClause requiresClause) {
        // "requires " + expression
        String exprStr = visitAndGet(requiresClause.expr());
        this.result = "requires " + exprStr;
    }

    @Override
    public void visit(MethodBody methodBody) {
        /*
         * Old toString():
         *   For each statement, statement.toString() + "\n"
         */
        List<Statement> statements = methodBody.statements();
        StringBuilder sb = new StringBuilder();
        for (Statement s : statements) {
            sb.append(visitAndGet(s)).append("\n");
        }
        this.result = sb.toString();
    }

    @Override
    public void visit(AssignStatement assignStatement) {
        // "lhs = rhs;"
        String lhsStr = visitAndGet(assignStatement.lhs());
        String exprStr = visitAndGet(assignStatement.expression());
        this.result = lhsStr + " = " + exprStr + ";";
    }

    @Override
    public void visit(BlockStatement blockStatement) {
        /*
         * Old toString():
         * {
         *   each statement
         * }
         */
        List<Statement> stmts = blockStatement.statements();
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (Statement s : stmts) {
            sb.append(visitAndGet(s)).append("\n");
        }
        sb.append("}");
        this.result = sb.toString();
    }

    @Override
    public void visit(IfStatement ifStatement) {
        /*
         * Representation:
         *   "if (" + condition + ") thenBlock else elseBlock"
         */
        StringBuilder sb = new StringBuilder();
        sb.append("if (")
                .append(visitAndGet(ifStatement.condition()))
                .append(") ")
                .append(visitAndGet(ifStatement.thenBlock()));

        if (ifStatement.elseBlock().isPresent()) {
            sb.append(" else ")
                    .append(visitAndGet(ifStatement.elseBlock().get()));
        }
        this.result = sb.toString();
    }

    @Override
    public void visit(VariableDeclaration variableDeclaration) {
        /*
         *   declaredType + " " + variableName [= expr] ;
         */
        StringBuilder sb = new StringBuilder();
        // variableDeclaration.declaredType() is not an AST node, so .toString() directly
        sb.append(variableDeclaration.declaredType())
                .append(" ")
                .append(variableDeclaration.variableName());
        if (variableDeclaration.initializer().isPresent()) {
            sb.append(" = ")
                    .append(visitAndGet(variableDeclaration.initializer().get()));
        }
        sb.append(";");
        this.result = sb.toString();
    }

    @Override
    public void visit(WhileStatement whileStatement) {
        /*
         *   "while (" + condition + ")" + invariants + " " + body
         */
        this.result = "while (" +
                visitAndGet(whileStatement.condition()) +
                ")" +
                visitAndGet(whileStatement.invariants()) +
                " " +
                visitAndGet(whileStatement.body());
    }


    @Override
    public void visit(Invariant invariant) {
        // "invariant <expr>"
        String exprStr = visitAndGet(invariant.expression());
        this.result = "invariant " + exprStr;
    }

    @Override
    public void visit(InvariantList invariantList) {
        List<Invariant> invariants = invariantList.invariants();
        StringBuilder sb = new StringBuilder();
        for (Invariant inv : invariants) {
            sb.append(visitAndGet(inv)).append("\n");
        }
        this.result = sb.toString();
    }

    @Override
    public void visit(Condition condition) {
        // just the expression's toString
        this.result = visitAndGet(condition.expression());
    }

    @Override
    public void visit(BoolType boolType) {
        // Representation: "bool"
        this.result = "bool";
    }

    @Override
    public void visit(IntArray intArray) {
        this.result = "int[]";
    }

    @Override
    public void visit(BoolArray boolArray) {
        this.result = "bool[]";

    }

    @Override
    public void visit(VoidType voidType) {
        this.result = "void";
    }

    @Override
    public void visit(IntType intType) {
        this.result = "int";
    }

    @Override
    public void visit(FuncCallStatement funcCallStatement) {
        this.result = visitAndGet(funcCallStatement.funcCallExpression()) + ";";
    }

    // ----------------------------------------------------------------
    // Expression classes
    // ----------------------------------------------------------------

    public void visit(BinaryOpExpression binaryOpExpression) {
        /*
         * Old toString():
         *   "(" + left + " " + operatorSymbol() + " " + right + ")"
         */
        String leftStr = visitAndGet(binaryOpExpression.left());
        String rightStr = visitAndGet(binaryOpExpression.right());

        // If operatorSymbol() still exists, use it:
        String op = binaryOpExpression.operatorSymbol();
        this.result = "(" + leftStr + " " + op + " " + rightStr + ")";
    }

    @Override
    public void visit(ArrayRefExpression arrayRefExpression) {
        // Representation: "arrayName[indexExpr]"
        String arrName = arrayRefExpression.arrayName().name();
        String idxStr = visitAndGet(arrayRefExpression.indexExpr());
        this.result = arrName + "[" + idxStr + "]";
    }

    @Override
    public void visit(VarRefExpression varRefExpression) {
        // Representation: just variableName.toString()
        this.result = varRefExpression.variableName().name();
    }

    @Override
    public void visit(ForallExpression forallExpression) {
        // Representation: "forall (type var) :: body"
        // or something similar
        this.result = "forall (" +
                forallExpression.getType() +   // type is not an AST node
                " " +
                forallExpression.variable().name() +
                ") :: " +
                visitAndGet(forallExpression.body());
    }

    @Override
    public void visit(ExistsExpression existsExpression) {
        // Representation: "exists (type var) :: body"
        this.result = "exists (" +
                existsExpression.getType() +
                " " +
                existsExpression.variable().name() +
                ") :: " +
                visitAndGet(existsExpression.body());
    }

    public void visit(UnaryExpression unaryExpression) {
        /*
         * Old toString():
         *   "(" + operatorSymbol() + expression + ")"
         * again depends on the specific subclass (NotExpression => "!", NegExpression => "-")
         */
        String exprStr = visitAndGet(unaryExpression.expression());
        String op = unaryExpression.operatorSymbol();
        this.result = "(" + op + exprStr + ")";
    }

    @Override
    public void visit(ArrayLengthExpression arrayLengthExpression) {
        // Representation: arrayExpr.toString() + ".length"
        String arrStr = visitAndGet(arrayLengthExpression.arrayExpr());
        this.result = arrStr + ".length";
    }

    @Override
    public void visit(FuncCallExpression funcCallExpression) {
        // Representation: identifier(args...)
        // see the original code
        StringBuilder sb = new StringBuilder();
        sb.append(funcCallExpression.identifier().name())
                .append("(");

        List<Expression> args = funcCallExpression.arguments();
        for (int i = 0; i < args.size(); i++) {
            sb.append(visitAndGet(args.get(i)));
            if (i < args.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        this.result = sb.toString();
    }

    @Override
    public void visit(FalseExpression falseExpression) {
        // Representation: "false"
        this.result = "false";
    }

    @Override
    public void visit(IntExpression intExpression) {
        // Representation: integer value
        this.result = String.valueOf(intExpression.value());
    }

    @Override
    public void visit(NewArrayExpression newArrayExpression) {
        // Representation: "new <type>[<sizeExpr>]"
        String typeStr = Objects.toString(newArrayExpression.elementType());
        String sizeStr = visitAndGet(newArrayExpression.sizeExpr());
        this.result = "new " + typeStr + "[" + sizeStr + "]";
    }

    @Override
    public void visit(TrueExpression trueExpression) {
        // Representation: "true"
        this.result = "true";
    }

    @Override
    public void visit(NotExpression notExpression) {
        visit((UnaryExpression) notExpression);
    }

    @Override
    public void visit(NegExpression negExpression) {
        visit((UnaryExpression) negExpression);
    }

    @Override
    public void visit(ImpliesExpression impliesExpression) {
        visit((BinaryOpExpression) impliesExpression);
    }

    @Override
    public void visit(OrExpression orExpression) {
        visit((BinaryOpExpression) orExpression);
    }

    @Override
    public void visit(MulExpression mulExpression) {
        visit((BinaryOpExpression) mulExpression);
    }

    @Override
    public void visit(EqExpression eqExpression) {
        visit((BinaryOpExpression) eqExpression);
    }

    @Override
    public void visit(LessThanOrEqualExpression lessThanOrEqualExpression) {
        visit((BinaryOpExpression) lessThanOrEqualExpression);
    }

    @Override
    public void visit(SubExpression subExpression) {
        visit((BinaryOpExpression) subExpression);
    }

    @Override
    public void visit(ModExpression modExpression) {
        visit((BinaryOpExpression) modExpression);
    }

    @Override
    public void visit(GreaterThanOrEqualExpression greaterThanOrEqualExpression) {
        visit((BinaryOpExpression) greaterThanOrEqualExpression);
    }

    @Override
    public void visit(AddExpression addExpression) {
        visit((BinaryOpExpression) addExpression);
    }

    @Override
    public void visit(LessThanExpression lessThanExpression) {
        visit((BinaryOpExpression) lessThanExpression);
    }

    @Override
    public void visit(DivExpression divExpression) {
        visit((BinaryOpExpression) divExpression);
    }

    @Override
    public void visit(GreaterThanExpression greaterThanExpression) {
        visit((BinaryOpExpression) greaterThanExpression);
    }

    @Override
    public void visit(AndExpression andExpression) {
        visit((BinaryOpExpression) andExpression);
    }
}
