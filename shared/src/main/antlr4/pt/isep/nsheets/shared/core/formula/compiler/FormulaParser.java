// Generated from .\Formula.g4 by ANTLR 4.7.1

//    package pt.isep.nsheets.shared.core.formula.compiler;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FormulaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VAR=1, FUNCTION=2, CELL_REF=3, STRING=4, QUOT=5, NUMBER=6, EQ=7, NEQ=8, 
		LTEQ=9, GTEQ=10, GT=11, LT=12, AMP=13, PLUS=14, MINUS=15, MULTI=16, DIV=17, 
		POWER=18, PERCENT=19, COLON=20, ATTRIB=21, COMMA=22, SEMI=23, LPAR=24, 
		RPAR=25, LBRACKET=26, RBRACKET=27, UNDER=28, WS=29;
	public static final int
		RULE_expression = 0, RULE_comparison = 1, RULE_concatenation = 2, RULE_atom = 3, 
		RULE_block = 4, RULE_attribution = 5, RULE_function_call = 6, RULE_reference = 7, 
		RULE_literal = 8;
	public static final String[] ruleNames = {
		"expression", "comparison", "concatenation", "atom", "block", "attribution", 
		"function_call", "reference", "literal"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, "'\"'", null, "'='", "'<>'", "'<='", "'>='", 
		"'>'", "'<'", "'&'", "'+'", "'-'", "'*'", "'/'", "'^'", "'%'", "':'", 
		"':='", "','", "';'", "'('", "')'", "'{'", "'}'", "'_'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "VAR", "FUNCTION", "CELL_REF", "STRING", "QUOT", "NUMBER", "EQ", 
		"NEQ", "LTEQ", "GTEQ", "GT", "LT", "AMP", "PLUS", "MINUS", "MULTI", "DIV", 
		"POWER", "PERCENT", "COLON", "ATTRIB", "COMMA", "SEMI", "LPAR", "RPAR", 
		"LBRACKET", "RBRACKET", "UNDER", "WS"
	};
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
	public String getGrammarFileName() { return "Formula.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FormulaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(FormulaParser.EQ, 0); }
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			match(EQ);
			setState(19);
			comparison();
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

	public static class ComparisonContext extends ParserRuleContext {
		public List<ConcatenationContext> concatenation() {
			return getRuleContexts(ConcatenationContext.class);
		}
		public ConcatenationContext concatenation(int i) {
			return getRuleContext(ConcatenationContext.class,i);
		}
		public TerminalNode EQ() { return getToken(FormulaParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(FormulaParser.NEQ, 0); }
		public TerminalNode GT() { return getToken(FormulaParser.GT, 0); }
		public TerminalNode LT() { return getToken(FormulaParser.LT, 0); }
		public TerminalNode LTEQ() { return getToken(FormulaParser.LTEQ, 0); }
		public TerminalNode GTEQ() { return getToken(FormulaParser.GTEQ, 0); }
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitComparison(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_comparison);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			concatenation(0);
			setState(24);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << LTEQ) | (1L << GTEQ) | (1L << GT) | (1L << LT))) != 0)) {
				{
				setState(22);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << LTEQ) | (1L << GTEQ) | (1L << GT) | (1L << LT))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(23);
				concatenation(0);
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

	public static class ConcatenationContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(FormulaParser.MINUS, 0); }
		public List<ConcatenationContext> concatenation() {
			return getRuleContexts(ConcatenationContext.class);
		}
		public ConcatenationContext concatenation(int i) {
			return getRuleContext(ConcatenationContext.class,i);
		}
		public TerminalNode POWER() { return getToken(FormulaParser.POWER, 0); }
		public TerminalNode MULTI() { return getToken(FormulaParser.MULTI, 0); }
		public TerminalNode DIV() { return getToken(FormulaParser.DIV, 0); }
		public TerminalNode PLUS() { return getToken(FormulaParser.PLUS, 0); }
		public TerminalNode AMP() { return getToken(FormulaParser.AMP, 0); }
		public TerminalNode PERCENT() { return getToken(FormulaParser.PERCENT, 0); }
		public ConcatenationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_concatenation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterConcatenation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitConcatenation(this);
		}
	}

	public final ConcatenationContext concatenation() throws RecognitionException {
		return concatenation(0);
	}

	private ConcatenationContext concatenation(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConcatenationContext _localctx = new ConcatenationContext(_ctx, _parentState);
		ConcatenationContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_concatenation, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(28);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MINUS) {
				{
				setState(27);
				match(MINUS);
				}
			}

			setState(30);
			atom();
			}
			_ctx.stop = _input.LT(-1);
			setState(48);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(46);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
					case 1:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(32);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(33);
						match(POWER);
						setState(34);
						concatenation(4);
						}
						break;
					case 2:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(35);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(36);
						_la = _input.LA(1);
						if ( !(_la==MULTI || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(37);
						concatenation(4);
						}
						break;
					case 3:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(38);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(39);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(40);
						concatenation(3);
						}
						break;
					case 4:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(41);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(42);
						match(AMP);
						setState(43);
						concatenation(2);
						}
						break;
					case 5:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(44);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(45);
						match(PERCENT);
						}
						break;
					}
					} 
				}
				setState(50);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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

	public static class AtomContext extends ParserRuleContext {
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public ReferenceContext reference() {
			return getRuleContext(ReferenceContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(FormulaParser.LPAR, 0); }
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(FormulaParser.RPAR, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public AttributionContext attribution() {
			return getRuleContext(AttributionContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_atom);
		try {
			setState(60);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(51);
				function_call();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				reference();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(53);
				literal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(54);
				match(LPAR);
				setState(55);
				comparison();
				setState(56);
				match(RPAR);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(58);
				block();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(59);
				attribution();
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

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LBRACKET() { return getToken(FormulaParser.LBRACKET, 0); }
		public List<ComparisonContext> comparison() {
			return getRuleContexts(ComparisonContext.class);
		}
		public ComparisonContext comparison(int i) {
			return getRuleContext(ComparisonContext.class,i);
		}
		public TerminalNode RBRACKET() { return getToken(FormulaParser.RBRACKET, 0); }
		public List<TerminalNode> SEMI() { return getTokens(FormulaParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(FormulaParser.SEMI, i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(LBRACKET);
			setState(63);
			comparison();
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMI) {
				{
				{
				setState(64);
				match(SEMI);
				setState(65);
				comparison();
				}
				}
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(71);
			match(RBRACKET);
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

	public static class AttributionContext extends ParserRuleContext {
		public TerminalNode ATTRIB() { return getToken(FormulaParser.ATTRIB, 0); }
		public ConcatenationContext concatenation() {
			return getRuleContext(ConcatenationContext.class,0);
		}
		public ReferenceContext reference() {
			return getRuleContext(ReferenceContext.class,0);
		}
		public TerminalNode VAR() { return getToken(FormulaParser.VAR, 0); }
		public AttributionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribution; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterAttribution(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitAttribution(this);
		}
	}

	public final AttributionContext attribution() throws RecognitionException {
		AttributionContext _localctx = new AttributionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_attribution);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CELL_REF:
				{
				setState(73);
				reference();
				}
				break;
			case VAR:
				{
				setState(74);
				match(VAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(77);
			match(ATTRIB);
			setState(78);
			concatenation(0);
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

	public static class Function_callContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(FormulaParser.FUNCTION, 0); }
		public TerminalNode LPAR() { return getToken(FormulaParser.LPAR, 0); }
		public TerminalNode LBRACKET() { return getToken(FormulaParser.LBRACKET, 0); }
		public TerminalNode RPAR() { return getToken(FormulaParser.RPAR, 0); }
		public TerminalNode RBRACKET() { return getToken(FormulaParser.RBRACKET, 0); }
		public List<ComparisonContext> comparison() {
			return getRuleContexts(ComparisonContext.class);
		}
		public ComparisonContext comparison(int i) {
			return getRuleContext(ComparisonContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(FormulaParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(FormulaParser.SEMI, i);
		}
		public Function_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterFunction_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitFunction_call(this);
		}
	}

	public final Function_callContext function_call() throws RecognitionException {
		Function_callContext _localctx = new Function_callContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_function_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(FUNCTION);
			setState(81);
			_la = _input.LA(1);
			if ( !(_la==LPAR || _la==LBRACKET) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VAR) | (1L << FUNCTION) | (1L << CELL_REF) | (1L << STRING) | (1L << NUMBER) | (1L << MINUS) | (1L << LPAR) | (1L << LBRACKET))) != 0)) {
				{
				setState(82);
				comparison();
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMI) {
					{
					{
					setState(83);
					match(SEMI);
					setState(84);
					comparison();
					}
					}
					setState(89);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(92);
			_la = _input.LA(1);
			if ( !(_la==RPAR || _la==RBRACKET) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class ReferenceContext extends ParserRuleContext {
		public List<TerminalNode> CELL_REF() { return getTokens(FormulaParser.CELL_REF); }
		public TerminalNode CELL_REF(int i) {
			return getToken(FormulaParser.CELL_REF, i);
		}
		public TerminalNode COLON() { return getToken(FormulaParser.COLON, 0); }
		public ReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitReference(this);
		}
	}

	public final ReferenceContext reference() throws RecognitionException {
		ReferenceContext _localctx = new ReferenceContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_reference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(CELL_REF);
			setState(97);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				{
				setState(95);
				match(COLON);
				}
				setState(96);
				match(CELL_REF);
				}
				break;
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

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(FormulaParser.NUMBER, 0); }
		public TerminalNode STRING() { return getToken(FormulaParser.STRING, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			_la = _input.LA(1);
			if ( !(_la==STRING || _la==NUMBER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
		case 2:
			return concatenation_sempred((ConcatenationContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean concatenation_sempred(ConcatenationContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		case 2:
			return precpred(_ctx, 2);
		case 3:
			return precpred(_ctx, 1);
		case 4:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\37h\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\3\2"+
		"\3\3\3\3\3\3\5\3\33\n\3\3\4\3\4\5\4\37\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\61\n\4\f\4\16\4\64\13\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5?\n\5\3\6\3\6\3\6\3\6\7\6E\n\6\f\6\16"+
		"\6H\13\6\3\6\3\6\3\7\3\7\5\7N\n\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\7\b"+
		"X\n\b\f\b\16\b[\13\b\5\b]\n\b\3\b\3\b\3\t\3\t\3\t\5\td\n\t\3\n\3\n\3\n"+
		"\2\3\6\13\2\4\6\b\n\f\16\20\22\2\b\3\2\t\16\3\2\22\23\3\2\20\21\4\2\32"+
		"\32\34\34\4\2\33\33\35\35\4\2\6\6\b\b\2o\2\24\3\2\2\2\4\27\3\2\2\2\6\34"+
		"\3\2\2\2\b>\3\2\2\2\n@\3\2\2\2\fM\3\2\2\2\16R\3\2\2\2\20`\3\2\2\2\22e"+
		"\3\2\2\2\24\25\7\t\2\2\25\26\5\4\3\2\26\3\3\2\2\2\27\32\5\6\4\2\30\31"+
		"\t\2\2\2\31\33\5\6\4\2\32\30\3\2\2\2\32\33\3\2\2\2\33\5\3\2\2\2\34\36"+
		"\b\4\1\2\35\37\7\21\2\2\36\35\3\2\2\2\36\37\3\2\2\2\37 \3\2\2\2 !\5\b"+
		"\5\2!\62\3\2\2\2\"#\f\6\2\2#$\7\24\2\2$\61\5\6\4\6%&\f\5\2\2&\'\t\3\2"+
		"\2\'\61\5\6\4\6()\f\4\2\2)*\t\4\2\2*\61\5\6\4\5+,\f\3\2\2,-\7\17\2\2-"+
		"\61\5\6\4\4./\f\7\2\2/\61\7\25\2\2\60\"\3\2\2\2\60%\3\2\2\2\60(\3\2\2"+
		"\2\60+\3\2\2\2\60.\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63"+
		"\7\3\2\2\2\64\62\3\2\2\2\65?\5\16\b\2\66?\5\20\t\2\67?\5\22\n\289\7\32"+
		"\2\29:\5\4\3\2:;\7\33\2\2;?\3\2\2\2<?\5\n\6\2=?\5\f\7\2>\65\3\2\2\2>\66"+
		"\3\2\2\2>\67\3\2\2\2>8\3\2\2\2><\3\2\2\2>=\3\2\2\2?\t\3\2\2\2@A\7\34\2"+
		"\2AF\5\4\3\2BC\7\31\2\2CE\5\4\3\2DB\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2"+
		"\2\2GI\3\2\2\2HF\3\2\2\2IJ\7\35\2\2J\13\3\2\2\2KN\5\20\t\2LN\7\3\2\2M"+
		"K\3\2\2\2ML\3\2\2\2NO\3\2\2\2OP\7\27\2\2PQ\5\6\4\2Q\r\3\2\2\2RS\7\4\2"+
		"\2S\\\t\5\2\2TY\5\4\3\2UV\7\31\2\2VX\5\4\3\2WU\3\2\2\2X[\3\2\2\2YW\3\2"+
		"\2\2YZ\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2\\T\3\2\2\2\\]\3\2\2\2]^\3\2\2\2^_\t"+
		"\6\2\2_\17\3\2\2\2`c\7\5\2\2ab\7\26\2\2bd\7\5\2\2ca\3\2\2\2cd\3\2\2\2"+
		"d\21\3\2\2\2ef\t\7\2\2f\23\3\2\2\2\f\32\36\60\62>FMY\\c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}