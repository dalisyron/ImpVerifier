package imp.parser.antlr; // Generated from Imp.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class ImpParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, TRUE=8, FALSE=9, 
		IF=10, ELSE=11, WHILE=12, ASSIGN=13, PLUS=14, TIMES=15, EQUAL=16, LEQ=17, 
		GEQ=18, SEMICOLON=19, GREATER=20, LESS=21, NOT=22, AND=23, OR=24, LPAREN=25, 
		RPAREN=26, LBRACE=27, RBRACE=28, REQUIRES=29, ENSURES=30, INVARIANT=31, 
		FORALL=32, EXISTS=33, IMPLIES=34, DOUBLECOLON=35, MINUS=36, INTDIV=37, 
		RETURNS=38, ARGSEP=39, NEW=40, ID=41, INT=42, WS=43, SL_COMMENT=44;
	public static final int
		RULE_parse = 0, RULE_procDecl = 1, RULE_formalParameters = 2, RULE_formalParameter = 3, 
		RULE_returnsBlock = 4, RULE_conditionBlock = 5, RULE_requiresClause = 6, 
		RULE_ensuresClause = 7, RULE_statement = 8, RULE_block = 9, RULE_ifStatement = 10, 
		RULE_whileStatement = 11, RULE_parenthesizedCondition = 12, RULE_assignStatement = 13, 
		RULE_varDecl = 14, RULE_type = 15, RULE_expr = 16, RULE_var = 17, RULE_exprList = 18;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "procDecl", "formalParameters", "formalParameter", "returnsBlock", 
			"conditionBlock", "requiresClause", "ensuresClause", "statement", "block", 
			"ifStatement", "whileStatement", "parenthesizedCondition", "assignStatement", 
			"varDecl", "type", "expr", "var", "exprList"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'procedure'", "'bool'", "'int'", "'[]'", "'['", "']'", "'.length'", 
			"'true'", "'false'", "'if'", "'else'", "'while'", "'='", "'+'", "'*'", 
			"'=='", "'<='", "'>='", "';'", "'>'", "'<'", "'!'", "'&&'", "'||'", "'('", 
			"')'", "'{'", "'}'", "'requires'", "'ensures'", "'invariant'", "'forall'", 
			"'exists'", "'==>'", "'::'", "'-'", "'/'", "'returns'", "','", "'new'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "TRUE", "FALSE", "IF", 
			"ELSE", "WHILE", "ASSIGN", "PLUS", "TIMES", "EQUAL", "LEQ", "GEQ", "SEMICOLON", 
			"GREATER", "LESS", "NOT", "AND", "OR", "LPAREN", "RPAREN", "LBRACE", 
			"RBRACE", "REQUIRES", "ENSURES", "INVARIANT", "FORALL", "EXISTS", "IMPLIES", 
			"DOUBLECOLON", "MINUS", "INTDIV", "RETURNS", "ARGSEP", "NEW", "ID", "INT", 
			"WS", "SL_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Imp.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ImpParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParseContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ImpParser.EOF, 0); }
		public List<ProcDeclContext> procDecl() {
			return getRuleContexts(ProcDeclContext.class);
		}
		public ProcDeclContext procDecl(int i) {
			return getRuleContext(ProcDeclContext.class,i);
		}
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitParse(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(38);
				procDecl();
				}
				}
				setState(41); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			setState(43);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProcDeclContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ImpParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(ImpParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ImpParser.RPAREN, 0); }
		public ConditionBlockContext conditionBlock() {
			return getRuleContext(ConditionBlockContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public ReturnsBlockContext returnsBlock() {
			return getRuleContext(ReturnsBlockContext.class,0);
		}
		public ProcDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterProcDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitProcDecl(this);
		}
	}

	public final ProcDeclContext procDecl() throws RecognitionException {
		ProcDeclContext _localctx = new ProcDeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_procDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(T__0);
			setState(46);
			match(ID);
			setState(47);
			match(LPAREN);
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 33554444L) != 0)) {
				{
				setState(48);
				formalParameters();
				}
			}

			setState(51);
			match(RPAREN);
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURNS) {
				{
				setState(52);
				returnsBlock();
				}
			}

			setState(55);
			conditionBlock();
			setState(56);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FormalParametersContext extends ParserRuleContext {
		public List<FormalParameterContext> formalParameter() {
			return getRuleContexts(FormalParameterContext.class);
		}
		public FormalParameterContext formalParameter(int i) {
			return getRuleContext(FormalParameterContext.class,i);
		}
		public List<TerminalNode> ARGSEP() { return getTokens(ImpParser.ARGSEP); }
		public TerminalNode ARGSEP(int i) {
			return getToken(ImpParser.ARGSEP, i);
		}
		public FormalParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterFormalParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitFormalParameters(this);
		}
	}

	public final FormalParametersContext formalParameters() throws RecognitionException {
		FormalParametersContext _localctx = new FormalParametersContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_formalParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			formalParameter();
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ARGSEP) {
				{
				{
				setState(59);
				match(ARGSEP);
				setState(60);
				formalParameter();
				}
				}
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FormalParameterContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(ImpParser.ID, 0); }
		public FormalParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterFormalParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitFormalParameter(this);
		}
	}

	public final FormalParameterContext formalParameter() throws RecognitionException {
		FormalParameterContext _localctx = new FormalParameterContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_formalParameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			type(0);
			setState(67);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnsBlockContext extends ParserRuleContext {
		public TerminalNode RETURNS() { return getToken(ImpParser.RETURNS, 0); }
		public TerminalNode LPAREN() { return getToken(ImpParser.LPAREN, 0); }
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ImpParser.RPAREN, 0); }
		public ReturnsBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnsBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterReturnsBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitReturnsBlock(this);
		}
	}

	public final ReturnsBlockContext returnsBlock() throws RecognitionException {
		ReturnsBlockContext _localctx = new ReturnsBlockContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_returnsBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(RETURNS);
			setState(70);
			match(LPAREN);
			setState(71);
			formalParameters();
			setState(72);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConditionBlockContext extends ParserRuleContext {
		public List<RequiresClauseContext> requiresClause() {
			return getRuleContexts(RequiresClauseContext.class);
		}
		public RequiresClauseContext requiresClause(int i) {
			return getRuleContext(RequiresClauseContext.class,i);
		}
		public List<EnsuresClauseContext> ensuresClause() {
			return getRuleContexts(EnsuresClauseContext.class);
		}
		public EnsuresClauseContext ensuresClause(int i) {
			return getRuleContext(EnsuresClauseContext.class,i);
		}
		public ConditionBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterConditionBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitConditionBlock(this);
		}
	}

	public final ConditionBlockContext conditionBlock() throws RecognitionException {
		ConditionBlockContext _localctx = new ConditionBlockContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_conditionBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==REQUIRES || _la==ENSURES) {
				{
				setState(76);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case REQUIRES:
					{
					setState(74);
					requiresClause();
					}
					break;
				case ENSURES:
					{
					setState(75);
					ensuresClause();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RequiresClauseContext extends ParserRuleContext {
		public TerminalNode REQUIRES() { return getToken(ImpParser.REQUIRES, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public RequiresClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requiresClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterRequiresClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitRequiresClause(this);
		}
	}

	public final RequiresClauseContext requiresClause() throws RecognitionException {
		RequiresClauseContext _localctx = new RequiresClauseContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_requiresClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(REQUIRES);
			setState(82);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EnsuresClauseContext extends ParserRuleContext {
		public TerminalNode ENSURES() { return getToken(ImpParser.ENSURES, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public EnsuresClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ensuresClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterEnsuresClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitEnsuresClause(this);
		}
	}

	public final EnsuresClauseContext ensuresClause() throws RecognitionException {
		EnsuresClauseContext _localctx = new EnsuresClauseContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ensuresClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(ENSURES);
			setState(85);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfStmtContext extends StatementContext {
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public IfStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitIfStmt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprStmtContext extends StatementContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(ImpParser.SEMICOLON, 0); }
		public ExprStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterExprStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitExprStmt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WhileStmtContext extends StatementContext {
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public WhileStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterWhileStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitWhileStmt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignStmtContext extends StatementContext {
		public AssignStatementContext assignStatement() {
			return getRuleContext(AssignStatementContext.class,0);
		}
		public AssignStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterAssignStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitAssignStmt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarDeclStmtContext extends StatementContext {
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public VarDeclStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterVarDeclStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitVarDeclStmt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BlockStmtContext extends StatementContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BlockStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterBlockStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitBlockStmt(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_statement);
		try {
			setState(95);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new AssignStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				assignStatement();
				}
				break;
			case 2:
				_localctx = new IfStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(88);
				ifStatement();
				}
				break;
			case 3:
				_localctx = new WhileStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(89);
				whileStatement();
				}
				break;
			case 4:
				_localctx = new BlockStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(90);
				block();
				}
				break;
			case 5:
				_localctx = new VarDeclStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(91);
				varDecl();
				}
				break;
			case 6:
				_localctx = new ExprStmtContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(92);
				expr(0);
				setState(93);
				match(SEMICOLON);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(ImpParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(ImpParser.RBRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			match(LBRACE);
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 7778357753612L) != 0)) {
				{
				{
				setState(98);
				statement();
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(104);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(ImpParser.IF, 0); }
		public ParenthesizedConditionContext parenthesizedCondition() {
			return getRuleContext(ParenthesizedConditionContext.class,0);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(ImpParser.ELSE, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitIfStatement(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_ifStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(IF);
			setState(107);
			parenthesizedCondition();
			setState(108);
			block();
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(109);
				match(ELSE);
				setState(110);
				block();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhileStatementContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(ImpParser.WHILE, 0); }
		public ParenthesizedConditionContext parenthesizedCondition() {
			return getRuleContext(ParenthesizedConditionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<TerminalNode> INVARIANT() { return getTokens(ImpParser.INVARIANT); }
		public TerminalNode INVARIANT(int i) {
			return getToken(ImpParser.INVARIANT, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitWhileStatement(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_whileStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(WHILE);
			setState(114);
			parenthesizedCondition();
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==INVARIANT) {
				{
				{
				setState(115);
				match(INVARIANT);
				setState(116);
				expr(0);
				}
				}
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(122);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParenthesizedConditionContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ImpParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ImpParser.RPAREN, 0); }
		public ParenthesizedConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenthesizedCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterParenthesizedCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitParenthesizedCondition(this);
		}
	}

	public final ParenthesizedConditionContext parenthesizedCondition() throws RecognitionException {
		ParenthesizedConditionContext _localctx = new ParenthesizedConditionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_parenthesizedCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(LPAREN);
			setState(125);
			expr(0);
			setState(126);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignStatementContext extends ParserRuleContext {
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(ImpParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(ImpParser.SEMICOLON, 0); }
		public AssignStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterAssignStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitAssignStatement(this);
		}
	}

	public final AssignStatementContext assignStatement() throws RecognitionException {
		AssignStatementContext _localctx = new AssignStatementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_assignStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			var();
			setState(129);
			match(ASSIGN);
			setState(130);
			expr(0);
			setState(131);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarDeclContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(ImpParser.ID, 0); }
		public TerminalNode SEMICOLON() { return getToken(ImpParser.SEMICOLON, 0); }
		public TerminalNode ASSIGN() { return getToken(ImpParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public VarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitVarDecl(this);
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_varDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			type(0);
			setState(134);
			match(ID);
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(135);
				match(ASSIGN);
				setState(136);
				expr(0);
				}
			}

			setState(139);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayTypeContext extends TypeContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ArrayTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitArrayType(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BoolTypeContext extends TypeContext {
		public BoolTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterBoolType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitBoolType(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenTypeContext extends TypeContext {
		public TerminalNode LPAREN() { return getToken(ImpParser.LPAREN, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ImpParser.RPAREN, 0); }
		public ParenTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterParenType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitParenType(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntTypeContext extends TypeContext {
		public IntTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterIntType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitIntType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				{
				_localctx = new BoolTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(142);
				match(T__1);
				}
				break;
			case T__2:
				{
				_localctx = new IntTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(143);
				match(T__2);
				}
				break;
			case LPAREN:
				{
				_localctx = new ParenTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(144);
				match(LPAREN);
				setState(145);
				type(0);
				setState(146);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(154);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArrayTypeContext(new TypeContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(150);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(151);
					match(T__3);
					}
					} 
				}
				setState(156);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AndExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode AND() { return getToken(ImpParser.AND, 0); }
		public AndExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitAndExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TrueExprContext extends ExprContext {
		public TerminalNode TRUE() { return getToken(ImpParser.TRUE, 0); }
		public TrueExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterTrueExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitTrueExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NegExprContext extends ExprContext {
		public TerminalNode MINUS() { return getToken(ImpParser.MINUS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NegExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterNegExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitNegExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayLengthContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArrayLengthContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterArrayLength(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitArrayLength(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CompExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LEQ() { return getToken(ImpParser.LEQ, 0); }
		public TerminalNode GEQ() { return getToken(ImpParser.GEQ, 0); }
		public TerminalNode GREATER() { return getToken(ImpParser.GREATER, 0); }
		public TerminalNode LESS() { return getToken(ImpParser.LESS, 0); }
		public CompExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterCompExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitCompExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OrExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OR() { return getToken(ImpParser.OR, 0); }
		public OrExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitOrExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FalseExprContext extends ExprContext {
		public TerminalNode FALSE() { return getToken(ImpParser.FALSE, 0); }
		public FalseExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterFalseExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitFalseExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FuncCallContext extends ExprContext {
		public TerminalNode ID() { return getToken(ImpParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(ImpParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ImpParser.RPAREN, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public FuncCallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterFuncCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitFuncCall(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NewArrayContext extends ExprContext {
		public TerminalNode NEW() { return getToken(ImpParser.NEW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NewArrayContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterNewArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitNewArray(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayAccessContext extends ExprContext {
		public TerminalNode ID() { return getToken(ImpParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArrayAccessContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterArrayAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitArrayAccess(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MulDivExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode TIMES() { return getToken(ImpParser.TIMES, 0); }
		public TerminalNode INTDIV() { return getToken(ImpParser.INTDIV, 0); }
		public MulDivExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterMulDivExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitMulDivExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class F_ImpliesContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode IMPLIES() { return getToken(ImpParser.IMPLIES, 0); }
		public F_ImpliesContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterF_Implies(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitF_Implies(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class F_QuantContext extends ExprContext {
		public TerminalNode ID() { return getToken(ImpParser.ID, 0); }
		public TerminalNode DOUBLECOLON() { return getToken(ImpParser.DOUBLECOLON, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode FORALL() { return getToken(ImpParser.FORALL, 0); }
		public TerminalNode EXISTS() { return getToken(ImpParser.EXISTS, 0); }
		public F_QuantContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterF_Quant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitF_Quant(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode EQUAL() { return getToken(ImpParser.EQUAL, 0); }
		public EqExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterEqExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitEqExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarExprContext extends ExprContext {
		public TerminalNode ID() { return getToken(ImpParser.ID, 0); }
		public VarExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterVarExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitVarExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotExprContext extends ExprContext {
		public TerminalNode NOT() { return getToken(ImpParser.NOT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterNotExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitNotExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntExprContext extends ExprContext {
		public TerminalNode INT() { return getToken(ImpParser.INT, 0); }
		public IntExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterIntExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitIntExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenExprContext extends ExprContext {
		public TerminalNode LPAREN() { return getToken(ImpParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ImpParser.RPAREN, 0); }
		public ParenExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterParenExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitParenExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddSubExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(ImpParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(ImpParser.MINUS, 0); }
		public AddSubExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterAddSubExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitAddSubExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				_localctx = new NegExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(158);
				match(MINUS);
				setState(159);
				expr(19);
				}
				break;
			case 2:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(160);
				match(NOT);
				setState(161);
				expr(15);
				}
				break;
			case 3:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(162);
				match(LPAREN);
				setState(163);
				expr(0);
				setState(164);
				match(RPAREN);
				}
				break;
			case 4:
				{
				_localctx = new FuncCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(166);
				match(ID);
				setState(167);
				match(LPAREN);
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 7778223522560L) != 0)) {
					{
					setState(168);
					exprList();
					}
				}

				setState(171);
				match(RPAREN);
				}
				break;
			case 5:
				{
				_localctx = new IntExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(172);
				match(INT);
				}
				break;
			case 6:
				{
				_localctx = new TrueExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(173);
				match(TRUE);
				}
				break;
			case 7:
				{
				_localctx = new FalseExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(174);
				match(FALSE);
				}
				break;
			case 8:
				{
				_localctx = new VarExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(175);
				match(ID);
				}
				break;
			case 9:
				{
				_localctx = new F_QuantContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(179);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case FORALL:
					{
					setState(176);
					match(FORALL);
					}
					break;
				case ID:
					{
					}
					break;
				case EXISTS:
					{
					setState(178);
					match(EXISTS);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(181);
				match(ID);
				setState(182);
				match(DOUBLECOLON);
				setState(183);
				expr(4);
				}
				break;
			case 10:
				{
				_localctx = new NewArrayContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(184);
				match(NEW);
				setState(185);
				type(0);
				setState(186);
				match(T__4);
				setState(187);
				expr(0);
				setState(188);
				match(T__5);
				}
				break;
			case 11:
				{
				_localctx = new ArrayAccessContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(190);
				match(ID);
				setState(191);
				match(T__4);
				setState(192);
				expr(0);
				setState(193);
				match(T__5);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(222);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(220);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(197);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(198);
						_la = _input.LA(1);
						if ( !(_la==TIMES || _la==INTDIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(199);
						expr(19);
						}
						break;
					case 2:
						{
						_localctx = new AddSubExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(200);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(201);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(202);
						expr(18);
						}
						break;
					case 3:
						{
						_localctx = new CompExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(203);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(204);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 3538944L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(205);
						expr(17);
						}
						break;
					case 4:
						{
						_localctx = new AndExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(206);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(207);
						match(AND);
						setState(208);
						expr(15);
						}
						break;
					case 5:
						{
						_localctx = new OrExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(209);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(210);
						match(OR);
						setState(211);
						expr(14);
						}
						break;
					case 6:
						{
						_localctx = new EqExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(212);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(213);
						match(EQUAL);
						setState(214);
						expr(13);
						}
						break;
					case 7:
						{
						_localctx = new F_ImpliesContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(215);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(216);
						match(IMPLIES);
						{
						setState(217);
						expr(0);
						}
						}
						break;
					case 8:
						{
						_localctx = new ArrayLengthContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(218);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(219);
						match(T__6);
						}
						break;
					}
					} 
				}
				setState(224);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ImpParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(ImpParser.LPAREN, 0); }
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ImpParser.RPAREN, 0); }
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitVar(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_var);
		try {
			setState(236);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(226);
				match(ID);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(227);
				match(ID);
				setState(228);
				match(T__4);
				setState(229);
				expr(0);
				setState(230);
				match(T__5);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(232);
				match(LPAREN);
				setState(233);
				var();
				setState(234);
				match(RPAREN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> ARGSEP() { return getTokens(ImpParser.ARGSEP); }
		public TerminalNode ARGSEP(int i) {
			return getToken(ImpParser.ARGSEP, i);
		}
		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).enterExprList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ImpListener ) ((ImpListener)listener).exitExprList(this);
		}
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_exprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			expr(0);
			setState(243);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ARGSEP) {
				{
				{
				setState(239);
				match(ARGSEP);
				setState(240);
				expr(0);
				}
				}
				setState(245);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 15:
			return type_sempred((TypeContext)_localctx, predIndex);
		case 16:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 18);
		case 2:
			return precpred(_ctx, 17);
		case 3:
			return precpred(_ctx, 16);
		case 4:
			return precpred(_ctx, 14);
		case 5:
			return precpred(_ctx, 13);
		case 6:
			return precpred(_ctx, 12);
		case 7:
			return precpred(_ctx, 5);
		case 8:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001,\u00f7\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0001\u0000\u0004\u0000(\b\u0000\u000b\u0000\f\u0000)\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u00012\b"+
		"\u0001\u0001\u0001\u0001\u0001\u0003\u00016\b\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002>\b"+
		"\u0002\n\u0002\f\u0002A\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0005\u0005M\b\u0005\n\u0005\f\u0005P\t\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b`\b\b\u0001\t"+
		"\u0001\t\u0005\td\b\t\n\t\f\tg\t\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0003\np\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0005\u000bv\b\u000b\n\u000b\f\u000by\t\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r"+
		"\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u008a"+
		"\b\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0095\b\u000f\u0001"+
		"\u000f\u0001\u000f\u0005\u000f\u0099\b\u000f\n\u000f\f\u000f\u009c\t\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0003\u0010\u00aa\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00b4\b\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0003\u0010\u00c4\b\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00dd\b\u0010\n\u0010"+
		"\f\u0010\u00e0\t\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0003\u0011\u00ed\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0005\u0012\u00f2\b\u0012\n\u0012\f\u0012\u00f5\t\u0012\u0001\u0012\u0000"+
		"\u0002\u001e \u0013\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014"+
		"\u0016\u0018\u001a\u001c\u001e \"$\u0000\u0003\u0002\u0000\u000f\u000f"+
		"%%\u0002\u0000\u000e\u000e$$\u0002\u0000\u0011\u0012\u0014\u0015\u010e"+
		"\u0000\'\u0001\u0000\u0000\u0000\u0002-\u0001\u0000\u0000\u0000\u0004"+
		":\u0001\u0000\u0000\u0000\u0006B\u0001\u0000\u0000\u0000\bE\u0001\u0000"+
		"\u0000\u0000\nN\u0001\u0000\u0000\u0000\fQ\u0001\u0000\u0000\u0000\u000e"+
		"T\u0001\u0000\u0000\u0000\u0010_\u0001\u0000\u0000\u0000\u0012a\u0001"+
		"\u0000\u0000\u0000\u0014j\u0001\u0000\u0000\u0000\u0016q\u0001\u0000\u0000"+
		"\u0000\u0018|\u0001\u0000\u0000\u0000\u001a\u0080\u0001\u0000\u0000\u0000"+
		"\u001c\u0085\u0001\u0000\u0000\u0000\u001e\u0094\u0001\u0000\u0000\u0000"+
		" \u00c3\u0001\u0000\u0000\u0000\"\u00ec\u0001\u0000\u0000\u0000$\u00ee"+
		"\u0001\u0000\u0000\u0000&(\u0003\u0002\u0001\u0000\'&\u0001\u0000\u0000"+
		"\u0000()\u0001\u0000\u0000\u0000)\'\u0001\u0000\u0000\u0000)*\u0001\u0000"+
		"\u0000\u0000*+\u0001\u0000\u0000\u0000+,\u0005\u0000\u0000\u0001,\u0001"+
		"\u0001\u0000\u0000\u0000-.\u0005\u0001\u0000\u0000./\u0005)\u0000\u0000"+
		"/1\u0005\u0019\u0000\u000002\u0003\u0004\u0002\u000010\u0001\u0000\u0000"+
		"\u000012\u0001\u0000\u0000\u000023\u0001\u0000\u0000\u000035\u0005\u001a"+
		"\u0000\u000046\u0003\b\u0004\u000054\u0001\u0000\u0000\u000056\u0001\u0000"+
		"\u0000\u000067\u0001\u0000\u0000\u000078\u0003\n\u0005\u000089\u0003\u0012"+
		"\t\u00009\u0003\u0001\u0000\u0000\u0000:?\u0003\u0006\u0003\u0000;<\u0005"+
		"\'\u0000\u0000<>\u0003\u0006\u0003\u0000=;\u0001\u0000\u0000\u0000>A\u0001"+
		"\u0000\u0000\u0000?=\u0001\u0000\u0000\u0000?@\u0001\u0000\u0000\u0000"+
		"@\u0005\u0001\u0000\u0000\u0000A?\u0001\u0000\u0000\u0000BC\u0003\u001e"+
		"\u000f\u0000CD\u0005)\u0000\u0000D\u0007\u0001\u0000\u0000\u0000EF\u0005"+
		"&\u0000\u0000FG\u0005\u0019\u0000\u0000GH\u0003\u0004\u0002\u0000HI\u0005"+
		"\u001a\u0000\u0000I\t\u0001\u0000\u0000\u0000JM\u0003\f\u0006\u0000KM"+
		"\u0003\u000e\u0007\u0000LJ\u0001\u0000\u0000\u0000LK\u0001\u0000\u0000"+
		"\u0000MP\u0001\u0000\u0000\u0000NL\u0001\u0000\u0000\u0000NO\u0001\u0000"+
		"\u0000\u0000O\u000b\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000"+
		"QR\u0005\u001d\u0000\u0000RS\u0003 \u0010\u0000S\r\u0001\u0000\u0000\u0000"+
		"TU\u0005\u001e\u0000\u0000UV\u0003 \u0010\u0000V\u000f\u0001\u0000\u0000"+
		"\u0000W`\u0003\u001a\r\u0000X`\u0003\u0014\n\u0000Y`\u0003\u0016\u000b"+
		"\u0000Z`\u0003\u0012\t\u0000[`\u0003\u001c\u000e\u0000\\]\u0003 \u0010"+
		"\u0000]^\u0005\u0013\u0000\u0000^`\u0001\u0000\u0000\u0000_W\u0001\u0000"+
		"\u0000\u0000_X\u0001\u0000\u0000\u0000_Y\u0001\u0000\u0000\u0000_Z\u0001"+
		"\u0000\u0000\u0000_[\u0001\u0000\u0000\u0000_\\\u0001\u0000\u0000\u0000"+
		"`\u0011\u0001\u0000\u0000\u0000ae\u0005\u001b\u0000\u0000bd\u0003\u0010"+
		"\b\u0000cb\u0001\u0000\u0000\u0000dg\u0001\u0000\u0000\u0000ec\u0001\u0000"+
		"\u0000\u0000ef\u0001\u0000\u0000\u0000fh\u0001\u0000\u0000\u0000ge\u0001"+
		"\u0000\u0000\u0000hi\u0005\u001c\u0000\u0000i\u0013\u0001\u0000\u0000"+
		"\u0000jk\u0005\n\u0000\u0000kl\u0003\u0018\f\u0000lo\u0003\u0012\t\u0000"+
		"mn\u0005\u000b\u0000\u0000np\u0003\u0012\t\u0000om\u0001\u0000\u0000\u0000"+
		"op\u0001\u0000\u0000\u0000p\u0015\u0001\u0000\u0000\u0000qr\u0005\f\u0000"+
		"\u0000rw\u0003\u0018\f\u0000st\u0005\u001f\u0000\u0000tv\u0003 \u0010"+
		"\u0000us\u0001\u0000\u0000\u0000vy\u0001\u0000\u0000\u0000wu\u0001\u0000"+
		"\u0000\u0000wx\u0001\u0000\u0000\u0000xz\u0001\u0000\u0000\u0000yw\u0001"+
		"\u0000\u0000\u0000z{\u0003\u0012\t\u0000{\u0017\u0001\u0000\u0000\u0000"+
		"|}\u0005\u0019\u0000\u0000}~\u0003 \u0010\u0000~\u007f\u0005\u001a\u0000"+
		"\u0000\u007f\u0019\u0001\u0000\u0000\u0000\u0080\u0081\u0003\"\u0011\u0000"+
		"\u0081\u0082\u0005\r\u0000\u0000\u0082\u0083\u0003 \u0010\u0000\u0083"+
		"\u0084\u0005\u0013\u0000\u0000\u0084\u001b\u0001\u0000\u0000\u0000\u0085"+
		"\u0086\u0003\u001e\u000f\u0000\u0086\u0089\u0005)\u0000\u0000\u0087\u0088"+
		"\u0005\r\u0000\u0000\u0088\u008a\u0003 \u0010\u0000\u0089\u0087\u0001"+
		"\u0000\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u008b\u0001"+
		"\u0000\u0000\u0000\u008b\u008c\u0005\u0013\u0000\u0000\u008c\u001d\u0001"+
		"\u0000\u0000\u0000\u008d\u008e\u0006\u000f\uffff\uffff\u0000\u008e\u0095"+
		"\u0005\u0002\u0000\u0000\u008f\u0095\u0005\u0003\u0000\u0000\u0090\u0091"+
		"\u0005\u0019\u0000\u0000\u0091\u0092\u0003\u001e\u000f\u0000\u0092\u0093"+
		"\u0005\u001a\u0000\u0000\u0093\u0095\u0001\u0000\u0000\u0000\u0094\u008d"+
		"\u0001\u0000\u0000\u0000\u0094\u008f\u0001\u0000\u0000\u0000\u0094\u0090"+
		"\u0001\u0000\u0000\u0000\u0095\u009a\u0001\u0000\u0000\u0000\u0096\u0097"+
		"\n\u0002\u0000\u0000\u0097\u0099\u0005\u0004\u0000\u0000\u0098\u0096\u0001"+
		"\u0000\u0000\u0000\u0099\u009c\u0001\u0000\u0000\u0000\u009a\u0098\u0001"+
		"\u0000\u0000\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b\u001f\u0001"+
		"\u0000\u0000\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009d\u009e\u0006"+
		"\u0010\uffff\uffff\u0000\u009e\u009f\u0005$\u0000\u0000\u009f\u00c4\u0003"+
		" \u0010\u0013\u00a0\u00a1\u0005\u0016\u0000\u0000\u00a1\u00c4\u0003 \u0010"+
		"\u000f\u00a2\u00a3\u0005\u0019\u0000\u0000\u00a3\u00a4\u0003 \u0010\u0000"+
		"\u00a4\u00a5\u0005\u001a\u0000\u0000\u00a5\u00c4\u0001\u0000\u0000\u0000"+
		"\u00a6\u00a7\u0005)\u0000\u0000\u00a7\u00a9\u0005\u0019\u0000\u0000\u00a8"+
		"\u00aa\u0003$\u0012\u0000\u00a9\u00a8\u0001\u0000\u0000\u0000\u00a9\u00aa"+
		"\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u00c4"+
		"\u0005\u001a\u0000\u0000\u00ac\u00c4\u0005*\u0000\u0000\u00ad\u00c4\u0005"+
		"\b\u0000\u0000\u00ae\u00c4\u0005\t\u0000\u0000\u00af\u00c4\u0005)\u0000"+
		"\u0000\u00b0\u00b4\u0005 \u0000\u0000\u00b1\u00b4\u0001\u0000\u0000\u0000"+
		"\u00b2\u00b4\u0005!\u0000\u0000\u00b3\u00b0\u0001\u0000\u0000\u0000\u00b3"+
		"\u00b1\u0001\u0000\u0000\u0000\u00b3\u00b2\u0001\u0000\u0000\u0000\u00b4"+
		"\u00b5\u0001\u0000\u0000\u0000\u00b5\u00b6\u0005)\u0000\u0000\u00b6\u00b7"+
		"\u0005#\u0000\u0000\u00b7\u00c4\u0003 \u0010\u0004\u00b8\u00b9\u0005("+
		"\u0000\u0000\u00b9\u00ba\u0003\u001e\u000f\u0000\u00ba\u00bb\u0005\u0005"+
		"\u0000\u0000\u00bb\u00bc\u0003 \u0010\u0000\u00bc\u00bd\u0005\u0006\u0000"+
		"\u0000\u00bd\u00c4\u0001\u0000\u0000\u0000\u00be\u00bf\u0005)\u0000\u0000"+
		"\u00bf\u00c0\u0005\u0005\u0000\u0000\u00c0\u00c1\u0003 \u0010\u0000\u00c1"+
		"\u00c2\u0005\u0006\u0000\u0000\u00c2\u00c4\u0001\u0000\u0000\u0000\u00c3"+
		"\u009d\u0001\u0000\u0000\u0000\u00c3\u00a0\u0001\u0000\u0000\u0000\u00c3"+
		"\u00a2\u0001\u0000\u0000\u0000\u00c3\u00a6\u0001\u0000\u0000\u0000\u00c3"+
		"\u00ac\u0001\u0000\u0000\u0000\u00c3\u00ad\u0001\u0000\u0000\u0000\u00c3"+
		"\u00ae\u0001\u0000\u0000\u0000\u00c3\u00af\u0001\u0000\u0000\u0000\u00c3"+
		"\u00b3\u0001\u0000\u0000\u0000\u00c3\u00b8\u0001\u0000\u0000\u0000\u00c3"+
		"\u00be\u0001\u0000\u0000\u0000\u00c4\u00de\u0001\u0000\u0000\u0000\u00c5"+
		"\u00c6\n\u0012\u0000\u0000\u00c6\u00c7\u0007\u0000\u0000\u0000\u00c7\u00dd"+
		"\u0003 \u0010\u0013\u00c8\u00c9\n\u0011\u0000\u0000\u00c9\u00ca\u0007"+
		"\u0001\u0000\u0000\u00ca\u00dd\u0003 \u0010\u0012\u00cb\u00cc\n\u0010"+
		"\u0000\u0000\u00cc\u00cd\u0007\u0002\u0000\u0000\u00cd\u00dd\u0003 \u0010"+
		"\u0011\u00ce\u00cf\n\u000e\u0000\u0000\u00cf\u00d0\u0005\u0017\u0000\u0000"+
		"\u00d0\u00dd\u0003 \u0010\u000f\u00d1\u00d2\n\r\u0000\u0000\u00d2\u00d3"+
		"\u0005\u0018\u0000\u0000\u00d3\u00dd\u0003 \u0010\u000e\u00d4\u00d5\n"+
		"\f\u0000\u0000\u00d5\u00d6\u0005\u0010\u0000\u0000\u00d6\u00dd\u0003 "+
		"\u0010\r\u00d7\u00d8\n\u0005\u0000\u0000\u00d8\u00d9\u0005\"\u0000\u0000"+
		"\u00d9\u00dd\u0003 \u0010\u0000\u00da\u00db\n\u0001\u0000\u0000\u00db"+
		"\u00dd\u0005\u0007\u0000\u0000\u00dc\u00c5\u0001\u0000\u0000\u0000\u00dc"+
		"\u00c8\u0001\u0000\u0000\u0000\u00dc\u00cb\u0001\u0000\u0000\u0000\u00dc"+
		"\u00ce\u0001\u0000\u0000\u0000\u00dc\u00d1\u0001\u0000\u0000\u0000\u00dc"+
		"\u00d4\u0001\u0000\u0000\u0000\u00dc\u00d7\u0001\u0000\u0000\u0000\u00dc"+
		"\u00da\u0001\u0000\u0000\u0000\u00dd\u00e0\u0001\u0000\u0000\u0000\u00de"+
		"\u00dc\u0001\u0000\u0000\u0000\u00de\u00df\u0001\u0000\u0000\u0000\u00df"+
		"!\u0001\u0000\u0000\u0000\u00e0\u00de\u0001\u0000\u0000\u0000\u00e1\u00ed"+
		"\u0001\u0000\u0000\u0000\u00e2\u00ed\u0005)\u0000\u0000\u00e3\u00e4\u0005"+
		")\u0000\u0000\u00e4\u00e5\u0005\u0005\u0000\u0000\u00e5\u00e6\u0003 \u0010"+
		"\u0000\u00e6\u00e7\u0005\u0006\u0000\u0000\u00e7\u00ed\u0001\u0000\u0000"+
		"\u0000\u00e8\u00e9\u0005\u0019\u0000\u0000\u00e9\u00ea\u0003\"\u0011\u0000"+
		"\u00ea\u00eb\u0005\u001a\u0000\u0000\u00eb\u00ed\u0001\u0000\u0000\u0000"+
		"\u00ec\u00e1\u0001\u0000\u0000\u0000\u00ec\u00e2\u0001\u0000\u0000\u0000"+
		"\u00ec\u00e3\u0001\u0000\u0000\u0000\u00ec\u00e8\u0001\u0000\u0000\u0000"+
		"\u00ed#\u0001\u0000\u0000\u0000\u00ee\u00f3\u0003 \u0010\u0000\u00ef\u00f0"+
		"\u0005\'\u0000\u0000\u00f0\u00f2\u0003 \u0010\u0000\u00f1\u00ef\u0001"+
		"\u0000\u0000\u0000\u00f2\u00f5\u0001\u0000\u0000\u0000\u00f3\u00f1\u0001"+
		"\u0000\u0000\u0000\u00f3\u00f4\u0001\u0000\u0000\u0000\u00f4%\u0001\u0000"+
		"\u0000\u0000\u00f5\u00f3\u0001\u0000\u0000\u0000\u0014)15?LN_eow\u0089"+
		"\u0094\u009a\u00a9\u00b3\u00c3\u00dc\u00de\u00ec\u00f3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}