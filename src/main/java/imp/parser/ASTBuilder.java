package imp.parser;

import imp.ast.Program;
import imp.ast.condition.ConditionClause;
import imp.ast.condition.ConditionList;
import imp.ast.condition.EnsuresClause;
import imp.ast.condition.RequiresClause;
import imp.ast.expression.*;
import imp.ast.expression.ArrayRefExpr;
import imp.ast.expression.array.NewArrayExpr;
import imp.ast.expression.bool.*;
import imp.ast.expression.formula.ExistsExpr;
import imp.ast.expression.formula.ForallExpr;
import imp.ast.expression.formula.ImpliesExpr;
import imp.ast.expression.formula.QuantifiedExpr;
import imp.ast.expression.integer.*;
import imp.ast.method.*;
import imp.ast.statement.*;
import imp.ast.type.ArrayType;
import imp.ast.type.BoolType;
import imp.ast.type.IntType;
import imp.ast.type.Type;
import imp.ast.variable.Identifier;
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
        Type type = (Type) values.get(ctx.type());
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
        Expr expr = (Expr) values.get(ctx.expr());
        values.put(ctx, new RequiresClause(expr));
    }

    @Override
    public void exitEnsuresClause(ImpParser.EnsuresClauseContext ctx) {
        Expr expr = (Expr) values.get(ctx.expr());
        values.put(ctx, new EnsuresClause(expr));
    }

    @Override
    public void exitAssignStmt(ImpParser.AssignStmtContext ctx) {
        ReferenceExpr lhs = (ReferenceExpr) values.get(ctx.assignStatement().reference());
        Expr rhs = (Expr) values.get(ctx.assignStatement().expr());
        values.put(ctx, new AssignStatement(lhs, rhs));
    }

    @Override
    public void exitIfStmt(ImpParser.IfStmtContext ctx) {
        Expr condition = (Expr) values.get(ctx.ifStatement().parenthesizedCondition());
        BlockStatement thenBlock = (BlockStatement) values.get(ctx.ifStatement().block(0));

        Optional<BlockStatement> elseBlock = Optional.empty();
        if (ctx.ifStatement().ELSE() != null) {
            elseBlock = Optional.of((BlockStatement) values.get(ctx.ifStatement().block(1)));
        }

        values.put(ctx, new IfStatement(condition, thenBlock, elseBlock));
    }

    @Override
    public void exitWhileStmt(ImpParser.WhileStmtContext ctx) {
        Expr condition = (Expr) values.get(ctx.whileStatement().parenthesizedCondition());

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

        Optional<Expr> initializer = Optional.empty();
        if (ctx.varDecl().expr() != null) {
            initializer = Optional.of((Expr) values.get(ctx.varDecl().expr()));
        }

        values.put(ctx, new VariableDeclaration(type, name, initializer));
    }

    @Override
    public void exitExprStmt(ImpParser.ExprStmtContext ctx) {
        Expr expr = (Expr) values.get(ctx.expr());
        values.put(ctx, new ExpressionStatement(expr));
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
        List<Invariant> invariants = ctx.expr().stream()
                .map(exprCtx -> new Invariant((Expr) values.get(exprCtx)))
                .collect(Collectors.toList());
        values.put(ctx, new InvariantList(invariants));
    }

    @Override
    public void exitParenthesizedCondition(ImpParser.ParenthesizedConditionContext ctx) {
        values.put(ctx, values.get(ctx.expr()));
    }

    @Override
    public void exitBoolType(ImpParser.BoolTypeContext ctx) {
        values.put(ctx, new BoolType());
    }

    @Override
    public void exitIntType(ImpParser.IntTypeContext ctx) {
        values.put(ctx, new IntType());
    }

    @Override
    public void exitArrayType(ImpParser.ArrayTypeContext ctx) {
        Type elementType = (Type) values.get(ctx.type());
        values.put(ctx, new ArrayType(elementType));
    }

    @Override
    public void exitParenType(ImpParser.ParenTypeContext ctx) {
        values.put(ctx, values.get(ctx.type()));
    }

    @Override
    public void exitNegExpr(ImpParser.NegExprContext ctx) {
        Expr expr = (Expr) values.get(ctx.expr());
        values.put(ctx, new NegExpr(expr));
    }

    @Override
    public void exitMulDivExpr(ImpParser.MulDivExprContext ctx) {
        Expr left = (Expr) values.get(ctx.expr(0));
        Expr right = (Expr) values.get(ctx.expr(1));
        String op = ctx.getChild(1).getText();

        Expr result = op.equals("*") ? new MulExpr(left, right) : new DivExpr(left, right);
        values.put(ctx, result);
    }

    @Override
    public void exitAddSubExpr(ImpParser.AddSubExprContext ctx) {
        Expr left = (Expr) values.get(ctx.expr(0));
        Expr right = (Expr) values.get(ctx.expr(1));
        String op = ctx.getChild(1).getText();

        Expr result = op.equals("+") ? new AddExpr(left, right) : new SubExpr(left, right);
        values.put(ctx, result);
    }

    @Override
    public void exitCompExpr(ImpParser.CompExprContext ctx) {
        Expr left = (Expr) values.get(ctx.expr(0));
        Expr right = (Expr) values.get(ctx.expr(1));
        String op = ctx.getChild(1).getText();

        Expr result;
        switch (op) {
            case "<":
                result = new LessThanExpr(left, right);
                break;
            case "<=":
                result = new LessThanOrEqualExpr(left, right);
                break;
            case ">":
                result = new GreaterThanExpr(left, right);
                break;
            case ">=":
                result = new GreaterThanOrEqualExpr(left, right);
                break;
            default:
                throw new IllegalArgumentException("Unknown operator: " + op);
        }
        values.put(ctx, result);
    }

    @Override
    public void exitNotExpr(ImpParser.NotExprContext ctx) {
        Expr expr = (Expr) values.get(ctx.expr());
        values.put(ctx, new NotExpr(expr));
    }

    @Override
    public void exitAndExpr(ImpParser.AndExprContext ctx) {
        Expr left = (Expr) values.get(ctx.expr(0));
        Expr right = (Expr) values.get(ctx.expr(1));
        values.put(ctx, new AndExpr(left, right));
    }

    @Override
    public void exitOrExpr(ImpParser.OrExprContext ctx) {
        Expr left = (Expr) values.get(ctx.expr(0));
        Expr right = (Expr) values.get(ctx.expr(1));
        values.put(ctx, new OrExpr(left, right));
    }

    @Override
    public void exitEqExpr(ImpParser.EqExprContext ctx) {
        Expr left = (Expr) values.get(ctx.expr(0));
        Expr right = (Expr) values.get(ctx.expr(1));
        values.put(ctx, new EqExpr(left, right));
    }

    @Override
    public void exitParenExpr(ImpParser.ParenExprContext ctx) {
        values.put(ctx, values.get(ctx.expr()));
    }

    @Override
    public void exitFuncCall(ImpParser.FuncCallContext ctx) {
        Identifier funcName = new Identifier(ctx.ID().getText());
        List<Expr> args = ctx.exprList() != null ? (List<Expr>) values.get(ctx.exprList()) : new ArrayList<>();
        values.put(ctx, new FuncCallExpr(funcName, args));
    }

    @Override
    public void exitIntExpr(ImpParser.IntExprContext ctx) {
        int value = Integer.parseInt(ctx.INT().getText());
        values.put(ctx, new IntExpr(value));
    }

    @Override
    public void exitTrueExpr(ImpParser.TrueExprContext ctx) {
        values.put(ctx, TrueExpr.getInstance());
    }

    @Override
    public void exitFalseExpr(ImpParser.FalseExprContext ctx) {
        values.put(ctx, FalseExpr.getInstance());
    }

    @Override
    public void exitF_Implies(ImpParser.F_ImpliesContext ctx) {
        Expr left = (Expr) values.get(ctx.expr(0));
        Expr right = (Expr) values.get(ctx.expr(1));
        values.put(ctx, new ImpliesExpr(left, right));
    }

    @Override
    public void exitF_Quant(ImpParser.F_QuantContext ctx) {
        String quantifier = ctx.getChild(0).getText();
        Identifier variable = new Identifier(ctx.ID().getText());
        Expr expr = (Expr) values.get(ctx.expr());

        QuantifiedExpr result = quantifier.equals("forall")
                ? new ForallExpr(variable, expr)
                : new ExistsExpr(variable, expr);

        values.put(ctx, result);
    }

    @Override
    public void exitNewArray(ImpParser.NewArrayContext ctx) {
        Type elementType = (Type) values.get(ctx.type());
        Expr sizeExpr = (Expr) values.get(ctx.expr());
        values.put(ctx, new NewArrayExpr(elementType, sizeExpr));
    }

    @Override
    public void exitReferenceExpr(ImpParser.ReferenceExprContext ctx) {
        values.put(ctx, values.get(ctx.reference()));
    }

    @Override
    public void exitArrayLength(ImpParser.ArrayLengthContext ctx) {
        Expr arrayExpr = (Expr) values.get(ctx.expr());
        values.put(ctx, new ArrayLengthExpr(arrayExpr));
    }

    @Override
    public void exitVarRef(ImpParser.VarRefContext ctx) {
        Identifier name = new Identifier(ctx.ID().getText());
        values.put(ctx, new VarRefExpr(name));
    }

    @Override
    public void exitArrayRef(ImpParser.ArrayRefContext ctx) {
        Identifier arrayName = new Identifier(ctx.ID().getText());
        Expr index = (Expr) values.get(ctx.expr());
        values.put(ctx, new ArrayRefExpr(arrayName, index));
    }

    @Override
    public void exitExprList(ImpParser.ExprListContext ctx) {
        List<Expr> expressions = ctx.expr().stream()
                .map(exprCtx -> (Expr) values.get(exprCtx))
                .collect(Collectors.toList());
        values.put(ctx, expressions);
    }
}