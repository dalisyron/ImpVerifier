package imp.parser.antlr;// Generated from Imp.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ImpParser}.
 */
public interface ImpListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ImpParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(ImpParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImpParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(ImpParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SequenceStmt}
	 * labeled alternative in {@link ImpParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSequenceStmt(ImpParser.SequenceStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SequenceStmt}
	 * labeled alternative in {@link ImpParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSequenceStmt(ImpParser.SequenceStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignStmt}
	 * labeled alternative in {@link ImpParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignStmt(ImpParser.AssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignStmt}
	 * labeled alternative in {@link ImpParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignStmt(ImpParser.AssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link ImpParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(ImpParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link ImpParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(ImpParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link ImpParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(ImpParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link ImpParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(ImpParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BlockStmt}
	 * labeled alternative in {@link ImpParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStmt(ImpParser.BlockStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BlockStmt}
	 * labeled alternative in {@link ImpParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStmt(ImpParser.BlockStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ImpParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(ImpParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImpParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(ImpParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TrueCond}
	 * labeled alternative in {@link ImpParser#conditional}.
	 * @param ctx the parse tree
	 */
	void enterTrueCond(ImpParser.TrueCondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TrueCond}
	 * labeled alternative in {@link ImpParser#conditional}.
	 * @param ctx the parse tree
	 */
	void exitTrueCond(ImpParser.TrueCondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FalseCond}
	 * labeled alternative in {@link ImpParser#conditional}.
	 * @param ctx the parse tree
	 */
	void enterFalseCond(ImpParser.FalseCondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FalseCond}
	 * labeled alternative in {@link ImpParser#conditional}.
	 * @param ctx the parse tree
	 */
	void exitFalseCond(ImpParser.FalseCondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqualCond}
	 * labeled alternative in {@link ImpParser#conditional}.
	 * @param ctx the parse tree
	 */
	void enterEqualCond(ImpParser.EqualCondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqualCond}
	 * labeled alternative in {@link ImpParser#conditional}.
	 * @param ctx the parse tree
	 */
	void exitEqualCond(ImpParser.EqualCondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LeqCond}
	 * labeled alternative in {@link ImpParser#conditional}.
	 * @param ctx the parse tree
	 */
	void enterLeqCond(ImpParser.LeqCondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LeqCond}
	 * labeled alternative in {@link ImpParser#conditional}.
	 * @param ctx the parse tree
	 */
	void exitLeqCond(ImpParser.LeqCondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMulExpr(ImpParser.MulExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMulExpr(ImpParser.MulExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntegerExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIntegerExpr(ImpParser.IntegerExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntegerExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIntegerExpr(ImpParser.IntegerExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VariableExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterVariableExpr(ImpParser.VariableExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VariableExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitVariableExpr(ImpParser.VariableExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddExpr(ImpParser.AddExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddExpr(ImpParser.AddExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(ImpParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(ImpParser.ParenExprContext ctx);
}