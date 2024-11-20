package imp.ast;

import imp.ast.conditional.*;
import imp.ast.expression.AddExpr;
import imp.ast.expression.IntegerExpr;
import imp.ast.expression.MulExpr;
import imp.ast.expression.VariableExpr;
import imp.ast.statement.AssignStmt;
import imp.ast.statement.IfStmt;
import imp.ast.statement.PostcondStmt;
import imp.ast.statement.PrecondStmt;
import imp.ast.statement.SequenceStmt;
import imp.ast.statement.WhileStmt;
import imp.parser.antlr.ImpBaseListener;
import imp.parser.antlr.ImpParser;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.List;

public class ASTBuilder extends ImpBaseListener {

    private Statement root;

    // A map to store AST nodes corresponding to parse tree nodes
    private final ParseTreeProperty<Object> values = new ParseTreeProperty<>();

    public Statement getRoot() {
        return root;
    }

    @Override
    public void exitParse(ImpParser.ParseContext ctx) {
        // If there are multiple statements, chain them into a sequence
        if (ctx.statement().size() == 1) {
            root = (Statement) values.get(ctx.statement(0));
        } else {
            Statement seq = (Statement) values.get(ctx.statement(0));
            for (int i = 1; i < ctx.statement().size(); i++) {
                seq = new SequenceStmt(seq, (Statement) values.get(ctx.statement(i)));
            }
            root = seq;
        }
    }

    @Override
    public void exitAssignStmt(ImpParser.AssignStmtContext ctx) {
        String variable = ctx.VARIABLE().getText();
        Expression expression = (Expression) values.get(ctx.expression());
        values.put(ctx, new AssignStmt(variable, expression));
    }

    @Override
    public void exitSequenceStmt(ImpParser.SequenceStmtContext ctx) {
        List<ImpParser.SimpleStatementContext> simpleStatements = ctx.simpleStatement();
        int n = simpleStatements.size();
        if (n == 1) {
            values.put(ctx, values.get(simpleStatements.get(0)));
        } else {
            Statement seq = (Statement) values.get(simpleStatements.get(0));
            for (int i = 1; i < n; i++) {
                seq = new SequenceStmt(seq, (Statement) values.get(simpleStatements.get(i)));
            }
            values.put(ctx, seq);
        }
    }

    @Override
    public void exitIfStmt(ImpParser.IfStmtContext ctx) {
        Conditional condition = (Conditional) values.get(ctx.conditional());
        Statement thenBranch = (Statement) values.get(ctx.statement(0));
        Statement elseBranch = (Statement) values.get(ctx.statement(1));
        values.put(ctx, new IfStmt(condition, thenBranch, elseBranch));
    }
    @Override
    public void exitPrecondition(ImpParser.PreconditionContext ctx) {
        Conditional condition = (Conditional) values.get(ctx.conditional());
        values.put(ctx, new PrecondStmt(condition));
    }
    @Override
    public void exitPostcondition(ImpParser.PostconditionContext ctx) {
        Conditional condition = (Conditional) values.get(ctx.conditional());
        values.put(ctx, new PostcondStmt(condition));
    }

    @Override
    public void exitWhileStmt(ImpParser.WhileStmtContext ctx) {
        Conditional condition = (Conditional) values.get(ctx.conditional(0));
        Conditional invariant = (Conditional) values.get(ctx.conditional(1));
        Statement body = (Statement) values.get(ctx.block());
        values.put(ctx, new WhileStmt(condition, invariant ,body));
    }

    @Override
    public void exitBlockStmt(ImpParser.BlockStmtContext ctx) {
        Statement blockStatement = (Statement) values.get(ctx.block());
        values.put(ctx, blockStatement);
    }

    @Override
    public void exitBlock(ImpParser.BlockContext ctx) {
        Statement blockStatement = (Statement) values.get(ctx.statement());
        values.put(ctx, blockStatement);
    }

    @Override
    public void exitTrueCond(ImpParser.TrueCondContext ctx) {
        values.put(ctx, TrueCond.INSTANCE);
    }

    @Override
    public void exitFalseCond(ImpParser.FalseCondContext ctx) {
        values.put(ctx, FalseCond.INSTANCE);
    }

    @Override
    public void exitEqualCond(ImpParser.EqualCondContext ctx) {
        Expression left = (Expression) values.get(ctx.expression(0));
        Expression right = (Expression) values.get(ctx.expression(1));
        values.put(ctx, new EqualCond(left, right));
    }

    @Override
    public void exitLeqCond(ImpParser.LeqCondContext ctx) {
        Expression left = (Expression) values.get(ctx.expression(0));
        Expression right = (Expression) values.get(ctx.expression(1));
        values.put(ctx, new LeqCond(left, right));
    }

    @Override
    public void exitLtCond(ImpParser.LtCondContext ctx) {
        Expression left = (Expression) values.get(ctx.expression(0));
        Expression right = (Expression) values.get(ctx.expression(1));
        values.put(ctx, new LtCond(left, right));
    }

    @Override
    public void exitGeqCond(ImpParser.GeqCondContext ctx) {
        Expression left = (Expression) values.get(ctx.expression(0));
        Expression right = (Expression) values.get(ctx.expression(1));
        values.put(ctx, new GeqCond(left, right));
    }

    @Override
    public void exitGtCond(ImpParser.GtCondContext ctx) {
        Expression left = (Expression) values.get(ctx.expression(0));
        Expression right = (Expression) values.get(ctx.expression(1));
        values.put(ctx, new GtCond(left, right));
    }

    @Override
    public void exitAndCond(ImpParser.AndCondContext ctx) {
        Conditional left = (Conditional) values.get(ctx.conditional(0));
        Conditional right = (Conditional) values.get(ctx.conditional(1));
        values.put(ctx, new AndCond(left, right));
    }

    @Override
    public void exitOrCond(ImpParser.OrCondContext ctx) {
        Conditional left = (Conditional) values.get(ctx.conditional(0));
        Conditional right = (Conditional) values.get(ctx.conditional(1));
        values.put(ctx, new OrCond(left, right));
    }

    @Override
    public void exitImpliesCond(ImpParser.ImpliesCondContext ctx) {
        Conditional left = (Conditional) values.get(ctx.conditional(0));
        Conditional right = (Conditional) values.get(ctx.conditional(1));
        values.put(ctx, new ImpliesCond(left, right));
    }
    

    @Override
    public void exitIntegerExpr(ImpParser.IntegerExprContext ctx) {
        int value = Integer.parseInt(ctx.INTEGER().getText());
        values.put(ctx, new IntegerExpr(value));
    }

    @Override
    public void exitVariableExpr(ImpParser.VariableExprContext ctx) {
        String name = ctx.VARIABLE().getText();
        values.put(ctx, new VariableExpr(name));
    }

    @Override
    public void exitMulExpr(ImpParser.MulExprContext ctx) {
        Expression left = (Expression) values.get(ctx.expression(0));
        Expression right = (Expression) values.get(ctx.expression(1));
        values.put(ctx, new MulExpr(left, right));
    }

    @Override
    public void exitAddExpr(ImpParser.AddExprContext ctx) {
        Expression left = (Expression) values.get(ctx.expression(0));
        Expression right = (Expression) values.get(ctx.expression(1));
        values.put(ctx, new AddExpr(left, right));
    }

    @Override
    public void exitParenExpr(ImpParser.ParenExprContext ctx) {
        // Simply pass through the inner expression
        Expression inner = (Expression) values.get(ctx.expression());
        values.put(ctx, inner);
    }
}
