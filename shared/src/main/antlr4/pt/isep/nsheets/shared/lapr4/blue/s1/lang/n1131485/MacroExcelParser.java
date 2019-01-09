// Generated from MacroExcel.g4 by ANTLR 4.7.1

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
public class MacroExcelParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VAR=1, FUNCTION=2, CELL_REF=3, STRING=4, QUOT=5, NUMBER=6, EQ=7, NEQ=8, 
		LTEQ=9, GTEQ=10, GT=11, LT=12, AMP=13, PLUS=14, MINUS=15, MULTI=16, DIV=17, 
		POWER=18, PERCENT=19, COLON=20, ATTRIB=21, COMMA=22, SEMI=23, LPAR=24, 
		RPAR=25, LBRACKET=26, RBRACKET=27, STARTER=28, UNDER=29, WS=30, LINECOMMENT=31;
	public static final int
		RULE_macro = 0, RULE_line = 1, RULE_expression = 2, RULE_comparison = 3, 
		RULE_concatenation = 4, RULE_atom = 5, RULE_block = 6, RULE_attribution = 7, 
		RULE_function_call = 8, RULE_reference = 9, RULE_literal = 10;
	public static final String[] ruleNames = {
		"macro", "line", "expression", "comparison", "concatenation", "atom", 
		"block", "attribution", "function_call", "reference", "literal"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, "'\"'", null, "'='", "'<>'", "'<='", "'>='", 
		"'>'", "'<'", "'&'", "'+'", "'-'", "'*'", "'/'", "'^'", "'%'", "':'", 
		"':='", "','", "';'", "'('", "')'", "'{'", "'}'", "'|'", "'_'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "VAR", "FUNCTION", "CELL_REF", "STRING", "QUOT", "NUMBER", "EQ", 
		"NEQ", "LTEQ", "GTEQ", "GT", "LT", "AMP", "PLUS", "MINUS", "MULTI", "DIV", 
		"POWER", "PERCENT", "COLON", "ATTRIB", "COMMA", "SEMI", "LPAR", "RPAR", 
		"LBRACKET", "RBRACKET", "STARTER", "UNDER", "WS", "LINECOMMENT"
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
	public String getGrammarFileName() { return "MacroExcel.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MacroExcelParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class MacroContext extends ParserRuleContext {
		public List<LineContext> line() {
			return getRuleContexts(LineContext.class);
		}
		public LineContext line(int i) {
			return getRuleContext(LineContext.class,i);
		}
		public MacroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_macro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).enterMacro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).exitMacro(this);
		}
	}

	public final MacroContext macro() throws RecognitionException {
		MacroContext _localctx = new MacroContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_macro);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(22);
				line();
				}
				}
				setState(25); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VAR) | (1L << FUNCTION) | (1L << CELL_REF) | (1L << STRING) | (1L << NUMBER) | (1L << MINUS) | (1L << LPAR) | (1L << LBRACKET))) != 0) );
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

	public static class LineContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).enterLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).exitLine(this);
		}
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_line);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			expression();
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

	public static class ExpressionContext extends ParserRuleContext {
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
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
		public TerminalNode EQ() { return getToken(MacroExcelParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(MacroExcelParser.NEQ, 0); }
		public TerminalNode GT() { return getToken(MacroExcelParser.GT, 0); }
		public TerminalNode LT() { return getToken(MacroExcelParser.LT, 0); }
		public TerminalNode LTEQ() { return getToken(MacroExcelParser.LTEQ, 0); }
		public TerminalNode GTEQ() { return getToken(MacroExcelParser.GTEQ, 0); }
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).exitComparison(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_comparison);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			concatenation(0);
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << LTEQ) | (1L << GTEQ) | (1L << GT) | (1L << LT))) != 0)) {
				{
				setState(32);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << LTEQ) | (1L << GTEQ) | (1L << GT) | (1L << LT))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(33);
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
		public TerminalNode MINUS() { return getToken(MacroExcelParser.MINUS, 0); }
		public List<ConcatenationContext> concatenation() {
			return getRuleContexts(ConcatenationContext.class);
		}
		public ConcatenationContext concatenation(int i) {
			return getRuleContext(ConcatenationContext.class,i);
		}
		public TerminalNode POWER() { return getToken(MacroExcelParser.POWER, 0); }
		public TerminalNode MULTI() { return getToken(MacroExcelParser.MULTI, 0); }
		public TerminalNode DIV() { return getToken(MacroExcelParser.DIV, 0); }
		public TerminalNode PLUS() { return getToken(MacroExcelParser.PLUS, 0); }
		public TerminalNode AMP() { return getToken(MacroExcelParser.AMP, 0); }
		public TerminalNode PERCENT() { return getToken(MacroExcelParser.PERCENT, 0); }
		public ConcatenationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_concatenation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).enterConcatenation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).exitConcatenation(this);
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
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_concatenation, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MINUS) {
				{
				setState(37);
				match(MINUS);
				}
			}

			setState(40);
			atom();
			}
			_ctx.stop = _input.LT(-1);
			setState(58);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(56);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(42);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(43);
						match(POWER);
						setState(44);
						concatenation(4);
						}
						break;
					case 2:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(45);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(46);
						_la = _input.LA(1);
						if ( !(_la==MULTI || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(47);
						concatenation(4);
						}
						break;
					case 3:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(48);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(49);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(50);
						concatenation(3);
						}
						break;
					case 4:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(51);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(52);
						match(AMP);
						setState(53);
						concatenation(2);
						}
						break;
					case 5:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(54);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(55);
						match(PERCENT);
						}
						break;
					}
					} 
				}
				setState(60);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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
		public TerminalNode LPAR() { return getToken(MacroExcelParser.LPAR, 0); }
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(MacroExcelParser.RPAR, 0); }
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
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_atom);
		try {
			setState(70);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				function_call();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
				reference();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(63);
				literal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(64);
				match(LPAR);
				setState(65);
				comparison();
				setState(66);
				match(RPAR);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(68);
				block();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(69);
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
		public TerminalNode LBRACKET() { return getToken(MacroExcelParser.LBRACKET, 0); }
		public List<ComparisonContext> comparison() {
			return getRuleContexts(ComparisonContext.class);
		}
		public ComparisonContext comparison(int i) {
			return getRuleContext(ComparisonContext.class,i);
		}
		public TerminalNode RBRACKET() { return getToken(MacroExcelParser.RBRACKET, 0); }
		public List<TerminalNode> SEMI() { return getTokens(MacroExcelParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(MacroExcelParser.SEMI, i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(LBRACKET);
			setState(73);
			comparison();
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMI) {
				{
				{
				setState(74);
				match(SEMI);
				setState(75);
				comparison();
				}
				}
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(81);
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
		public TerminalNode ATTRIB() { return getToken(MacroExcelParser.ATTRIB, 0); }
		public ConcatenationContext concatenation() {
			return getRuleContext(ConcatenationContext.class,0);
		}
		public ReferenceContext reference() {
			return getRuleContext(ReferenceContext.class,0);
		}
		public TerminalNode VAR() { return getToken(MacroExcelParser.VAR, 0); }
		public AttributionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribution; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).enterAttribution(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).exitAttribution(this);
		}
	}

	public final AttributionContext attribution() throws RecognitionException {
		AttributionContext _localctx = new AttributionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_attribution);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CELL_REF:
				{
				setState(83);
				reference();
				}
				break;
			case VAR:
				{
				setState(84);
				match(VAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(87);
			match(ATTRIB);
			setState(88);
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
		public TerminalNode FUNCTION() { return getToken(MacroExcelParser.FUNCTION, 0); }
		public TerminalNode LPAR() { return getToken(MacroExcelParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(MacroExcelParser.RPAR, 0); }
		public List<ComparisonContext> comparison() {
			return getRuleContexts(ComparisonContext.class);
		}
		public ComparisonContext comparison(int i) {
			return getRuleContext(ComparisonContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(MacroExcelParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(MacroExcelParser.SEMI, i);
		}
		public Function_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).enterFunction_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).exitFunction_call(this);
		}
	}

	public final Function_callContext function_call() throws RecognitionException {
		Function_callContext _localctx = new Function_callContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_function_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(FUNCTION);
<<<<<<< HEAD
			setState(93);
			match(LPAR);
			setState(102);
=======
			setState(91);
			_la = _input.LA(1);
			if ( !(_la==LPAR || _la==LBRACKET) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(100);
>>>>>>> 13554a5c69ffc54d4140e0948613ffa1e494a28a
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VAR) | (1L << FUNCTION) | (1L << CELL_REF) | (1L << STRING) | (1L << NUMBER) | (1L << MINUS) | (1L << LPAR) | (1L << LBRACKET))) != 0)) {
				{
				setState(92);
				comparison();
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMI) {
					{
					{
					setState(93);
					match(SEMI);
					setState(94);
					comparison();
					}
					}
					setState(99);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

<<<<<<< HEAD
			setState(104);
			match(RPAR);
=======
			setState(102);
			_la = _input.LA(1);
			if ( !(_la==RPAR || _la==RBRACKET) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
>>>>>>> 13554a5c69ffc54d4140e0948613ffa1e494a28a
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
		public List<TerminalNode> CELL_REF() { return getTokens(MacroExcelParser.CELL_REF); }
		public TerminalNode CELL_REF(int i) {
			return getToken(MacroExcelParser.CELL_REF, i);
		}
		public TerminalNode COLON() { return getToken(MacroExcelParser.COLON, 0); }
		public ReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).enterReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).exitReference(this);
		}
	}

	public final ReferenceContext reference() throws RecognitionException {
		ReferenceContext _localctx = new ReferenceContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_reference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(CELL_REF);
			setState(107);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				{
				setState(105);
				match(COLON);
				}
				setState(106);
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
		public TerminalNode NUMBER() { return getToken(MacroExcelParser.NUMBER, 0); }
		public TerminalNode STRING() { return getToken(MacroExcelParser.STRING, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MacroExcelListener ) ((MacroExcelListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
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
		case 4:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!r\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f"+
<<<<<<< HEAD
		"\t\f\3\2\6\2\32\n\2\r\2\16\2\33\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\5"+
		"\5\'\n\5\3\6\3\6\5\6+\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\7\6=\n\6\f\6\16\6@\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\5\7K\n\7\3\b\3\b\3\b\3\b\7\bQ\n\b\f\b\16\bT\13\b\3\b\3\b\3"+
		"\t\3\t\5\tZ\n\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\7\nd\n\n\f\n\16\ng\13"+
		"\n\5\ni\n\n\3\n\3\n\3\13\3\13\3\13\5\13p\n\13\3\f\3\f\3\f\2\3\n\r\2\4"+
		"\6\b\n\f\16\20\22\24\26\2\6\3\2\t\16\3\2\22\23\3\2\20\21\4\2\6\6\b\b\2"+
		"z\2\31\3\2\2\2\4\35\3\2\2\2\6 \3\2\2\2\b#\3\2\2\2\n(\3\2\2\2\fJ\3\2\2"+
		"\2\16L\3\2\2\2\20Y\3\2\2\2\22^\3\2\2\2\24l\3\2\2\2\26q\3\2\2\2\30\32\5"+
		"\4\3\2\31\30\3\2\2\2\32\33\3\2\2\2\33\31\3\2\2\2\33\34\3\2\2\2\34\3\3"+
		"\2\2\2\35\36\7\36\2\2\36\37\5\6\4\2\37\5\3\2\2\2 !\7\t\2\2!\"\5\b\5\2"+
		"\"\7\3\2\2\2#&\5\n\6\2$%\t\2\2\2%\'\5\n\6\2&$\3\2\2\2&\'\3\2\2\2\'\t\3"+
		"\2\2\2(*\b\6\1\2)+\7\21\2\2*)\3\2\2\2*+\3\2\2\2+,\3\2\2\2,-\5\f\7\2->"+
		"\3\2\2\2./\f\6\2\2/\60\7\24\2\2\60=\5\n\6\6\61\62\f\5\2\2\62\63\t\3\2"+
		"\2\63=\5\n\6\6\64\65\f\4\2\2\65\66\t\4\2\2\66=\5\n\6\5\678\f\3\2\289\7"+
		"\17\2\29=\5\n\6\4:;\f\7\2\2;=\7\25\2\2<.\3\2\2\2<\61\3\2\2\2<\64\3\2\2"+
		"\2<\67\3\2\2\2<:\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?\13\3\2\2\2@>\3"+
		"\2\2\2AK\5\22\n\2BK\5\24\13\2CK\5\26\f\2DE\7\32\2\2EF\5\b\5\2FG\7\33\2"+
		"\2GK\3\2\2\2HK\5\16\b\2IK\5\20\t\2JA\3\2\2\2JB\3\2\2\2JC\3\2\2\2JD\3\2"+
		"\2\2JH\3\2\2\2JI\3\2\2\2K\r\3\2\2\2LM\7\34\2\2MR\5\b\5\2NO\7\31\2\2OQ"+
		"\5\b\5\2PN\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2SU\3\2\2\2TR\3\2\2\2U"+
		"V\7\35\2\2V\17\3\2\2\2WZ\5\24\13\2XZ\7\3\2\2YW\3\2\2\2YX\3\2\2\2Z[\3\2"+
		"\2\2[\\\7\27\2\2\\]\5\n\6\2]\21\3\2\2\2^_\7\4\2\2_h\7\32\2\2`e\5\b\5\2"+
		"ab\7\31\2\2bd\5\b\5\2ca\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2fi\3\2\2"+
		"\2ge\3\2\2\2h`\3\2\2\2hi\3\2\2\2ij\3\2\2\2jk\7\33\2\2k\23\3\2\2\2lo\7"+
		"\5\2\2mn\7\26\2\2np\7\5\2\2om\3\2\2\2op\3\2\2\2p\25\3\2\2\2qr\t\5\2\2"+
		"r\27\3\2\2\2\r\33&*<>JRYeho";
=======
		"\t\f\3\2\6\2\32\n\2\r\2\16\2\33\3\3\3\3\3\4\3\4\3\5\3\5\3\5\5\5%\n\5\3"+
		"\6\3\6\5\6)\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\7\6;\n\6\f\6\16\6>\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\5\7I\n\7\3\b\3\b\3\b\3\b\7\bO\n\b\f\b\16\bR\13\b\3\b\3\b\3\t\3\t\5\t"+
		"X\n\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\7\nb\n\n\f\n\16\ne\13\n\5\ng\n\n"+
		"\3\n\3\n\3\13\3\13\3\13\5\13n\n\13\3\f\3\f\3\f\2\3\n\r\2\4\6\b\n\f\16"+
		"\20\22\24\26\2\b\3\2\t\16\3\2\22\23\3\2\20\21\4\2\32\32\34\34\4\2\33\33"+
		"\35\35\4\2\6\6\b\b\2x\2\31\3\2\2\2\4\35\3\2\2\2\6\37\3\2\2\2\b!\3\2\2"+
		"\2\n&\3\2\2\2\fH\3\2\2\2\16J\3\2\2\2\20W\3\2\2\2\22\\\3\2\2\2\24j\3\2"+
		"\2\2\26o\3\2\2\2\30\32\5\4\3\2\31\30\3\2\2\2\32\33\3\2\2\2\33\31\3\2\2"+
		"\2\33\34\3\2\2\2\34\3\3\2\2\2\35\36\5\6\4\2\36\5\3\2\2\2\37 \5\b\5\2 "+
		"\7\3\2\2\2!$\5\n\6\2\"#\t\2\2\2#%\5\n\6\2$\"\3\2\2\2$%\3\2\2\2%\t\3\2"+
		"\2\2&(\b\6\1\2\')\7\21\2\2(\'\3\2\2\2()\3\2\2\2)*\3\2\2\2*+\5\f\7\2+<"+
		"\3\2\2\2,-\f\6\2\2-.\7\24\2\2.;\5\n\6\6/\60\f\5\2\2\60\61\t\3\2\2\61;"+
		"\5\n\6\6\62\63\f\4\2\2\63\64\t\4\2\2\64;\5\n\6\5\65\66\f\3\2\2\66\67\7"+
		"\17\2\2\67;\5\n\6\489\f\7\2\29;\7\25\2\2:,\3\2\2\2:/\3\2\2\2:\62\3\2\2"+
		"\2:\65\3\2\2\2:8\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2\2\2=\13\3\2\2\2><\3"+
		"\2\2\2?I\5\22\n\2@I\5\24\13\2AI\5\26\f\2BC\7\32\2\2CD\5\b\5\2DE\7\33\2"+
		"\2EI\3\2\2\2FI\5\16\b\2GI\5\20\t\2H?\3\2\2\2H@\3\2\2\2HA\3\2\2\2HB\3\2"+
		"\2\2HF\3\2\2\2HG\3\2\2\2I\r\3\2\2\2JK\7\34\2\2KP\5\b\5\2LM\7\31\2\2MO"+
		"\5\b\5\2NL\3\2\2\2OR\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QS\3\2\2\2RP\3\2\2\2S"+
		"T\7\35\2\2T\17\3\2\2\2UX\5\24\13\2VX\7\3\2\2WU\3\2\2\2WV\3\2\2\2XY\3\2"+
		"\2\2YZ\7\27\2\2Z[\5\n\6\2[\21\3\2\2\2\\]\7\4\2\2]f\t\5\2\2^c\5\b\5\2_"+
		"`\7\31\2\2`b\5\b\5\2a_\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2dg\3\2\2\2"+
		"ec\3\2\2\2f^\3\2\2\2fg\3\2\2\2gh\3\2\2\2hi\t\6\2\2i\23\3\2\2\2jm\7\5\2"+
		"\2kl\7\26\2\2ln\7\5\2\2mk\3\2\2\2mn\3\2\2\2n\25\3\2\2\2op\t\7\2\2p\27"+
		"\3\2\2\2\r\33$(:<HPWcfm";
>>>>>>> 13554a5c69ffc54d4140e0948613ffa1e494a28a
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}