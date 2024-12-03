package imp.parser;

import imp.ast.Invariant;
import imp.ast.InvariantList;
import imp.ast.Program;
import imp.ast.condition.ConditionClause;
import imp.ast.condition.ConditionList;
import imp.ast.condition.EnsuresClause;
import imp.ast.condition.RequiresClause;
import imp.ast.expression.*;
import imp.ast.expression.ArrayRefExpression;
import imp.ast.expression.array.NewArrayExpression;
import imp.ast.expression.binary.bool.compare.*;
import imp.ast.expression.binary.bool.logic.AndExpression;
import imp.ast.expression.binary.bool.logic.OrExpression;
import imp.ast.expression.binary.integer.*;
import imp.ast.expression.bool.*;
import imp.ast.expression.binary.bool.logic.ImpliesExpression;
import imp.ast.expression.bool.QuantifiedExpression;
import imp.ast.expression.constant.bool.FalseExpression;
import imp.ast.expression.constant.bool.TrueExpression;
import imp.ast.expression.constant.integer.IntExpression;
import imp.ast.expression.unary.bool.NotExpression;
import imp.ast.expression.ArrayLengthExpression;
import imp.ast.expression.unary.integer.NegExpression;
import imp.ast.method.*;
import imp.ast.statement.*;
import imp.ast.typing.*;
import imp.ast.typing.data.DataType;
import imp.ast.typing.data.array.BoolArray;
import imp.ast.typing.data.value.BoolType;
import imp.ast.typing.data.array.IntArray;
import imp.ast.typing.data.value.IntType;
import imp.ast.expression.Identifier;
import imp.parser.antlr.ImpBaseListener;
import imp.parser.antlr.ImpParser;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ASTBuilder extends ImpBaseListener {

    private Program program;

    // A map to store AST nodes corresponding to parse tree nodes
    private final ParseTreeProperty<Object> values = new ParseTreeProperty<>();

    public Program getProgram() {
        return program;
    }

    @Override
    public void exitParse(ImpParser.ParseContext ctx) {
        // Build the list of method declarations
        List<MethodDeclaration> methods = ctx.methodDeclaration().stream()
                .map(mdCtx -> (MethodDeclaration) values.get(mdCtx))
                .collect(Collectors.toList());

        program = new Program(methods);
    }

    @Override
    public void exitMethodDeclaration(ImpParser.MethodDeclarationContext ctx) {
        Identifier methodName = new Identifier(ctx.ID().getText());

        Optional<ParameterList> parameters = Optional.empty();
        if (ctx.formalParameters() != null) {
            parameters = Optional.of((ParameterList) values.get(ctx.formalParameters()));
        }

        Optional<ReturnValue> returnValue = Optional.empty();
        if (ctx.returnsBlock() != null) {
            returnValue = Optional.of((ReturnValue) values.get(ctx.returnsBlock()));
        }

        Optional<ConditionList> conditions = Optional.empty();
        if (ctx.conditionBlock() != null) {
            conditions = (Optional<ConditionList>) values.get(ctx.conditionBlock());
        }

        BlockStatement bodyBlock = (BlockStatement) values.get(ctx.block());
        MethodBody methodBody = new MethodBody(bodyBlock.statements());

        MethodDeclaration methodDeclaration = new MethodDeclaration(
                methodName,
                parameters,
                returnValue,
                conditions,
                methodBody
        );

        values.put(ctx, methodDeclaration);
    }

    @Override
    public void exitFormalParameters(ImpParser.FormalParametersContext ctx) {
        List<Parameter> params = ctx.formalParameter().stream()
                .map(fpCtx -> (Parameter) values.get(fpCtx))
                .collect(Collectors.toList());

        values.put(ctx, new ParameterList(params));
    }

    @Override
    public void exitFormalParameter(ImpParser.FormalParameterContext ctx) {
        DataType type = (DataType) values.get(ctx.type());
        Identifier name = new Identifier(ctx.ID().getText());
        values.put(ctx, new Parameter(type, name));
    }

    @Override
    public void exitReturnsBlock(ImpParser.ReturnsBlockContext ctx) {
        Parameter param = (Parameter) values.get(ctx.formalParameter());
        values.put(ctx, new ReturnValue(param.type(), param.name()));
    }

    @Override
    public void exitConditionBlock(ImpParser.ConditionBlockContext ctx) {
        if (ctx.children == null) {
            values.put(ctx, Optional.empty());
        } else {
            List<ConditionClause> clauses = ctx.children.stream()
                    .map(child -> (ConditionClause) values.get(child))
                    .collect(Collectors.toList());
            values.put(ctx, Optional.of(new ConditionList(clauses)));
        }
    }

    @Override
    public void exitRequiresClause(ImpParser.RequiresClauseContext ctx) {
        Expression expression = (Expression) values.get(ctx.expression());
        values.put(ctx, new RequiresClause(expression));
    }

    @Override
    public void exitEnsuresClause(ImpParser.EnsuresClauseContext ctx) {
        Expression expression = (Expression) values.get(ctx.expression());
        values.put(ctx, new EnsuresClause(expression));
    }

    @Override
    public void exitAssignStmt(ImpParser.AssignStmtContext ctx) {
        ReferenceExpression lhs = (ReferenceExpression) values.get(ctx.assignStatement().reference());
        Expression rhs = (Expression) values.get(ctx.assignStatement().expression());
        values.put(ctx, new AssignStatement(lhs, rhs));
    }

    @Override
    public void exitIfStmt(ImpParser.IfStmtContext ctx) {
        Expression condition = (Expression) values.get(ctx.ifStatement().parenthesizedCondition());
        BlockStatement thenBlock = (BlockStatement) values.get(ctx.ifStatement().block(0));

        Optional<BlockStatement> elseBlock = Optional.empty();
        if (ctx.ifStatement().ELSE() != null) {
            elseBlock = Optional.of((BlockStatement) values.get(ctx.ifStatement().block(1)));
        }

        values.put(ctx, new IfStatement(new Condition(condition), thenBlock, elseBlock));
    }

    @Override
    public void exitWhileStmt(ImpParser.WhileStmtContext ctx) {
        Expression condition = (Expression) values.get(ctx.whileStatement().parenthesizedCondition());

        Optional<InvariantList> invariants = Optional.empty();
        if (ctx.whileStatement().invariantList() != null) {
            invariants = Optional.of((InvariantList) values.get(ctx.whileStatement().invariantList()));
        }

        BlockStatement body = (BlockStatement) values.get(ctx.whileStatement().block());
        values.put(ctx, new WhileStatement(condition, invariants, body));
    }

    @Override
    public void exitBlockStmt(ImpParser.BlockStmtContext ctx) {
        values.put(ctx, values.get(ctx.block()));
    }

    @Override
    public void exitVarDeclStmt(ImpParser.VarDeclStmtContext ctx) {
        Type type = (Type) values.get(ctx.varDecl().type());
        Identifier name = new Identifier(ctx.varDecl().ID().getText());

        Optional<Expression> initializer = Optional.empty();
        if (ctx.varDecl().expression() != null) {
            initializer = Optional.of((Expression) values.get(ctx.varDecl().expression()));
        }

        values.put(ctx, new VariableDeclaration(type, name, initializer));
    }

    @Override
    public void exitExprStmt(ImpParser.ExprStmtContext ctx) {
        Expression expression = (Expression) values.get(ctx.expression());
        values.put(ctx, new ExpressionStatement(expression));
    }

    @Override
    public void exitBlock(ImpParser.BlockContext ctx) {
        List<Statement> stmts = ctx.statement().stream()
                .map(stmtCtx -> (Statement) values.get(stmtCtx))
                .collect(Collectors.toList());
        values.put(ctx, new BlockStatement(stmts));
    }

    @Override
    public void exitInvariantList(ImpParser.InvariantListContext ctx) {
        List<Invariant> invariants = ctx.expression().stream()
                .map(exprCtx -> new Invariant((Expression) values.get(exprCtx)))
                .collect(Collectors.toList());
        values.put(ctx, new InvariantList(invariants));
    }

    @Override
    public void exitParenthesizedCondition(ImpParser.ParenthesizedConditionContext ctx) {
        values.put(ctx, values.get(ctx.expression()));
    }

    @Override
    public void exitBoolType(ImpParser.BoolTypeContext ctx) {
        values.put(ctx, BoolType.getInstance());
    }

    @Override
    public void exitIntType(ImpParser.IntTypeContext ctx) {
        values.put(ctx, IntType.getInstance());
    }

    @Override
    public void exitArrayInt(ImpParser.ArrayIntContext ctx) {
        values.put(ctx, IntArray.getInstance());
    }

    @Override
    public void exitArrayBool(ImpParser.ArrayBoolContext ctx) {
        values.put(ctx, BoolArray.getInstance());
    }

    @Override
    public void exitParenType(ImpParser.ParenTypeContext ctx) {
        values.put(ctx, values.get(ctx.type()));
    }

    @Override
    public void exitNegExpr(ImpParser.NegExprContext ctx) {
        Expression expression = (Expression) values.get(ctx.expression());
        values.put(ctx, new NegExpression(expression));
    }

    @Override
    public void exitMulDivModExpr(ImpParser.MulDivModExprContext ctx) {
        Expression left = (Expression) values.get(ctx.expression(0));
        Expression right = (Expression) values.get(ctx.expression(1));
        String op = ctx.getChild(1).getText();

        if (op.equals("%")) {
            values.put(ctx, new ModExpression(left, right));
        } else {
            Expression result = op.equals("*") ? new MulExpression(left, right) : new DivExpression(left, right);
            values.put(ctx, result);
        }
    }

    @Override
    public void exitAddSubExpr(ImpParser.AddSubExprContext ctx) {
        Expression left = (Expression) values.get(ctx.expression(0));
        Expression right = (Expression) values.get(ctx.expression(1));
        String op = ctx.getChild(1).getText();

        Expression result = op.equals("+") ? new AddExpression(left, right) : new SubExpression(left, right);
        values.put(ctx, result);
    }

    @Override
    public void exitCompExpr(ImpParser.CompExprContext ctx) {
        Expression left = (Expression) values.get(ctx.expression(0));
        Expression right = (Expression) values.get(ctx.expression(1));
        String op = ctx.getChild(1).getText();

        Expression result;
        switch (op) {
            case "<":
                result = new LessThanExpression(left, right);
                break;
            case "<=":
                result = new LessThanOrEqualExpression(left, right);
                break;
            case ">":
                result = new GreaterThanExpression(left, right);
                break;
            case ">=":
                result = new GreaterThanOrEqualExpression(left, right);
                break;
            default:
                throw new IllegalArgumentException("Unknown operator: " + op);
        }
        values.put(ctx, result);
    }

    @Override
    public void exitNotExpr(ImpParser.NotExprContext ctx) {
        Expression expression = (Expression) values.get(ctx.expression());
        values.put(ctx, new NotExpression(expression));
    }

    @Override
    public void exitAndExpr(ImpParser.AndExprContext ctx) {
        Expression left = (Expression) values.get(ctx.expression(0));
        Expression right = (Expression) values.get(ctx.expression(1));
        values.put(ctx, new AndExpression(left, right));
    }

    @Override
    public void exitOrExpr(ImpParser.OrExprContext ctx) {
        Expression left = (Expression) values.get(ctx.expression(0));
        Expression right = (Expression) values.get(ctx.expression(1));
        values.put(ctx, new OrExpression(left, right));
    }

    @Override
    public void exitEqExpr(ImpParser.EqExprContext ctx) {
        Expression left = (Expression) values.get(ctx.expression(0));
        Expression right = (Expression) values.get(ctx.expression(1));
        values.put(ctx, new EqExpression(left, right));
    }

    @Override
    public void exitParenExpr(ImpParser.ParenExprContext ctx) {
        values.put(ctx, values.get(ctx.expression()));
    }

    @Override
    public void exitFuncCall(ImpParser.FuncCallContext ctx) {
        Identifier funcName = new Identifier(ctx.ID().getText());
        List<Expression> args = ctx.exprList() != null ? (List<Expression>) values.get(ctx.exprList()) : new ArrayList<>();
        values.put(ctx, new FuncCallExpression(funcName, args));
    }

    @Override
    public void exitIntExpr(ImpParser.IntExprContext ctx) {
        int value = Integer.parseInt(ctx.INT().getText());
        values.put(ctx, new IntExpression(value));
    }

    @Override
    public void exitTrueExpr(ImpParser.TrueExprContext ctx) {
        values.put(ctx, TrueExpression.getInstance());
    }

    @Override
    public void exitFalseExpr(ImpParser.FalseExprContext ctx) {
        values.put(ctx, FalseExpression.getInstance());
    }

    @Override
    public void exitF_Implies(ImpParser.F_ImpliesContext ctx) {
        Expression left = (Expression) values.get(ctx.expression(0));
        Expression right = (Expression) values.get(ctx.expression(1));
        values.put(ctx, new ImpliesExpression(left, right));
    }

    @Override
    public void exitQuantifiedExpr(ImpParser.QuantifiedExprContext ctx) {
        String quantifier = ctx.getChild(0).getText();
        Identifier variable = new Identifier(ctx.formalParameter().ID().getText());
        DataType type = (DataType) values.get(ctx.formalParameter().type());
        Expression expression = (Expression) values.get(ctx.expression());

        QuantifiedExpression result = quantifier.equals("forall")
                ? new ForallExpression(variable, type, expression)
                : new ExistsExpression(variable, type, expression);

        values.put(ctx, result);
    }

    @Override
    public void exitNewArray(ImpParser.NewArrayContext ctx) {
        Type elementType = (Type) values.get(ctx.type());
        Expression sizeExpression = (Expression) values.get(ctx.expression());
        values.put(ctx, new NewArrayExpression(elementType, sizeExpression));
    }

    @Override
    public void exitReferenceExpr(ImpParser.ReferenceExprContext ctx) {
        values.put(ctx, values.get(ctx.reference()));
    }

    @Override
    public void exitArrayLength(ImpParser.ArrayLengthContext ctx) {
        Expression arrayExpression = (Expression) values.get(ctx.expression());
        values.put(ctx, new ArrayLengthExpression(arrayExpression));
    }

    @Override
    public void exitVarRef(ImpParser.VarRefContext ctx) {
        Identifier name = new Identifier(ctx.ID().getText());
        values.put(ctx, new VarRefExpression(name));
    }

    @Override
    public void exitArrayRef(ImpParser.ArrayRefContext ctx) {
        Identifier arrayName = new Identifier(ctx.ID().getText());
        Expression index = (Expression) values.get(ctx.expression());
        values.put(ctx, new ArrayRefExpression(arrayName, index));
    }

    @Override
    public void exitExprList(ImpParser.ExprListContext ctx) {
        List<Expression> expressions = ctx.expression().stream()
                .map(exprCtx -> (Expression) values.get(exprCtx))
                .collect(Collectors.toList());
        values.put(ctx, expressions);
    }
}