package imp.parser.antlr;
// Generated from Imp.g4 by ANTLR 4.13.2
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
	 * Enter a parse tree produced by {@link ImpParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(ImpParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImpParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(ImpParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ImpParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameters(ImpParser.FormalParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImpParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameters(ImpParser.FormalParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link ImpParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameter(ImpParser.FormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImpParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameter(ImpParser.FormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link ImpParser#returnsBlock}.
	 * @param ctx the parse tree
	 */
	void enterReturnsBlock(ImpParser.ReturnsBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImpParser#returnsBlock}.
	 * @param ctx the parse tree
	 */
	void exitReturnsBlock(ImpParser.ReturnsBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ImpParser#conditionBlock}.
	 * @param ctx the parse tree
	 */
	void enterConditionBlock(ImpParser.ConditionBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImpParser#conditionBlock}.
	 * @param ctx the parse tree
	 */
	void exitConditionBlock(ImpParser.ConditionBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ImpParser#requiresClause}.
	 * @param ctx the parse tree
	 */
	void enterRequiresClause(ImpParser.RequiresClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImpParser#requiresClause}.
	 * @param ctx the parse tree
	 */
	void exitRequiresClause(ImpParser.RequiresClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ImpParser#ensuresClause}.
	 * @param ctx the parse tree
	 */
	void enterEnsuresClause(ImpParser.EnsuresClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImpParser#ensuresClause}.
	 * @param ctx the parse tree
	 */
	void exitEnsuresClause(ImpParser.EnsuresClauseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignStmt}
	 * labeled alternative in {@link ImpParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAssignStmt(ImpParser.AssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignStmt}
	 * labeled alternative in {@link ImpParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAssignStmt(ImpParser.AssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link ImpParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(ImpParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link ImpParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(ImpParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link ImpParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(ImpParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link ImpParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(ImpParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BlockStmt}
	 * labeled alternative in {@link ImpParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStmt(ImpParser.BlockStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BlockStmt}
	 * labeled alternative in {@link ImpParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStmt(ImpParser.BlockStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarDeclStmt}
	 * labeled alternative in {@link ImpParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclStmt(ImpParser.VarDeclStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarDeclStmt}
	 * labeled alternative in {@link ImpParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclStmt(ImpParser.VarDeclStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprStmt}
	 * labeled alternative in {@link ImpParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExprStmt(ImpParser.ExprStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprStmt}
	 * labeled alternative in {@link ImpParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExprStmt(ImpParser.ExprStmtContext ctx);
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
	 * Enter a parse tree produced by {@link ImpParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(ImpParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImpParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(ImpParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ImpParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(ImpParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImpParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(ImpParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ImpParser#invariantList}.
	 * @param ctx the parse tree
	 */
	void enterInvariantList(ImpParser.InvariantListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImpParser#invariantList}.
	 * @param ctx the parse tree
	 */
	void exitInvariantList(ImpParser.InvariantListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ImpParser#parenthesizedCondition}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedCondition(ImpParser.ParenthesizedConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImpParser#parenthesizedCondition}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedCondition(ImpParser.ParenthesizedConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ImpParser#assignStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignStatement(ImpParser.AssignStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImpParser#assignStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignStatement(ImpParser.AssignStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ImpParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(ImpParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImpParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(ImpParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BoolType}
	 * labeled alternative in {@link ImpParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBoolType(ImpParser.BoolTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BoolType}
	 * labeled alternative in {@link ImpParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBoolType(ImpParser.BoolTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntType}
	 * labeled alternative in {@link ImpParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIntType(ImpParser.IntTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntType}
	 * labeled alternative in {@link ImpParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIntType(ImpParser.IntTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayInt}
	 * labeled alternative in {@link ImpParser#type}.
	 * @param ctx the parse tree
	 */
	void enterArrayInt(ImpParser.ArrayIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayInt}
	 * labeled alternative in {@link ImpParser#type}.
	 * @param ctx the parse tree
	 */
	void exitArrayInt(ImpParser.ArrayIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayBool}
	 * labeled alternative in {@link ImpParser#type}.
	 * @param ctx the parse tree
	 */
	void enterArrayBool(ImpParser.ArrayBoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayBool}
	 * labeled alternative in {@link ImpParser#type}.
	 * @param ctx the parse tree
	 */
	void exitArrayBool(ImpParser.ArrayBoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenType}
	 * labeled alternative in {@link ImpParser#type}.
	 * @param ctx the parse tree
	 */
	void enterParenType(ImpParser.ParenTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenType}
	 * labeled alternative in {@link ImpParser#type}.
	 * @param ctx the parse tree
	 */
	void exitParenType(ImpParser.ParenTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(ImpParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(ImpParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TrueExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterTrueExpr(ImpParser.TrueExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TrueExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitTrueExpr(ImpParser.TrueExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code QuantifiedExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterQuantifiedExpr(ImpParser.QuantifiedExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code QuantifiedExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitQuantifiedExpr(ImpParser.QuantifiedExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReferenceExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterReferenceExpr(ImpParser.ReferenceExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReferenceExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitReferenceExpr(ImpParser.ReferenceExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NegExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNegExpr(ImpParser.NegExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NegExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNegExpr(ImpParser.NegExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayLength}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayLength(ImpParser.ArrayLengthContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayLength}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayLength(ImpParser.ArrayLengthContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CompExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCompExpr(ImpParser.CompExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CompExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCompExpr(ImpParser.CompExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(ImpParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(ImpParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FalseExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFalseExpr(ImpParser.FalseExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FalseExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFalseExpr(ImpParser.FalseExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FuncCall}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(ImpParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FuncCall}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(ImpParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NewArray}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNewArray(ImpParser.NewArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NewArray}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNewArray(ImpParser.NewArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code F_Implies}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterF_Implies(ImpParser.F_ImpliesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code F_Implies}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitF_Implies(ImpParser.F_ImpliesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDivModExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMulDivModExpr(ImpParser.MulDivModExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDivModExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMulDivModExpr(ImpParser.MulDivModExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqExpr(ImpParser.EqExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqExpr(ImpParser.EqExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(ImpParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(ImpParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIntExpr(ImpParser.IntExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIntExpr(ImpParser.IntExprContext ctx);
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
	/**
	 * Enter a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpr(ImpParser.AddSubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link ImpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpr(ImpParser.AddSubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarRef}
	 * labeled alternative in {@link ImpParser#reference}.
	 * @param ctx the parse tree
	 */
	void enterVarRef(ImpParser.VarRefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarRef}
	 * labeled alternative in {@link ImpParser#reference}.
	 * @param ctx the parse tree
	 */
	void exitVarRef(ImpParser.VarRefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayRef}
	 * labeled alternative in {@link ImpParser#reference}.
	 * @param ctx the parse tree
	 */
	void enterArrayRef(ImpParser.ArrayRefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayRef}
	 * labeled alternative in {@link ImpParser#reference}.
	 * @param ctx the parse tree
	 */
	void exitArrayRef(ImpParser.ArrayRefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ImpParser#exprList}.
	 * @param ctx the parse tree
	 */
	void enterExprList(ImpParser.ExprListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImpParser#exprList}.
	 * @param ctx the parse tree
	 */
	void exitExprList(ImpParser.ExprListContext ctx);
}