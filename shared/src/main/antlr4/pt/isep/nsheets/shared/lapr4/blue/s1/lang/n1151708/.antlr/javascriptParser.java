// Generated from c:\Users\MarioDias\Documents\ISEP\2ºANO\2ºSemestre\LAPR4\lapr4-18-2dc\shared\src\main\antlr4\pt\isep\nsheets\shared\lapr4\blue\s1\lang\n1151708\javascript.g4 by ANTLR 4.7.1

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
public class javascriptParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		FUNCTION=1, CELL_REF=2, STRING=3, QUOT=4, NUMBER=5, EQ=6, DOUBLEEQ=7, 
		NEQ=8, LTEQ=9, GTEQ=10, GT=11, LT=12, AMP=13, PLUS=14, MINUS=15, MULTI=16, 
		DIV=17, POWER=18, PERCENT=19, COLON=20, ATTRIB=21, COMMA=22, SEMI=23, 
		LPAR=24, RPAR=25, LCURLYBRACKETS=26, RCURLYBRACKETS=27, WS=28, LINECOMMENT=29, 
		DOUBLESLASH=30;
	public static final int
		RULE_macro = 0, RULE_line = 1, RULE_expression = 2, RULE_comparison = 3, 
		RULE_concatenation = 4, RULE_atom = 5, RULE_attribution = 6, RULE_function_call = 7, 
		RULE_reference = 8, RULE_literal = 9;
	public static final String[] ruleNames = {
		"macro", "line", "expression", "comparison", "concatenation", "atom", 
		"attribution", "function_call", "reference", "literal"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, "'\"'", null, "'='", "'=='", "'!='", "'<='", "'>='", 
		"'>'", "'<'", "'&'", "'+'", "'-'", "'*'", "'/'", "'^'", "'%'", "':'", 
		"':='", "','", "';'", "'('", "')'", "'{'", "'}'", null, null, "'//'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "FUNCTION", "CELL_REF", "STRING", "QUOT", "NUMBER", "EQ", "DOUBLEEQ", 
		"NEQ", "LTEQ", "GTEQ", "GT", "LT", "AMP", "PLUS", "MINUS", "MULTI", "DIV", 
		"POWER", "PERCENT", "COLON", "ATTRIB", "COMMA", "SEMI", "LPAR", "RPAR", 
		"LCURLYBRACKETS", "RCURLYBRACKETS", "WS", "LINECOMMENT", "DOUBLESLASH"
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
	public String getGrammarFileName() { return "javascript.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public javascriptParser(TokenStream input) {
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
	}

	public final MacroContext macro() throws RecognitionException {
		MacroContext _localctx = new MacroContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_macro);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(20);
				line();
				}
				}
				setState(23); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FUNCTION) | (1L << CELL_REF) | (1L << STRING) | (1L << NUMBER) | (1L << MINUS) | (1L << LPAR))) != 0) );
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
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_line);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
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
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
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
		public TerminalNode DOUBLEEQ() { return getToken(javascriptParser.DOUBLEEQ, 0); }
		public TerminalNode EQ() { return getToken(javascriptParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(javascriptParser.NEQ, 0); }
		public TerminalNode GT() { return getToken(javascriptParser.GT, 0); }
		public TerminalNode LT() { return getToken(javascriptParser.LT, 0); }
		public TerminalNode LTEQ() { return getToken(javascriptParser.LTEQ, 0); }
		public TerminalNode GTEQ() { return getToken(javascriptParser.GTEQ, 0); }
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_comparison);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			concatenation(0);
			setState(32);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << DOUBLEEQ) | (1L << NEQ) | (1L << LTEQ) | (1L << GTEQ) | (1L << GT) | (1L << LT))) != 0)) {
				{
				setState(30);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << DOUBLEEQ) | (1L << NEQ) | (1L << LTEQ) | (1L << GTEQ) | (1L << GT) | (1L << LT))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(31);
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
		public TerminalNode MINUS() { return getToken(javascriptParser.MINUS, 0); }
		public List<ConcatenationContext> concatenation() {
			return getRuleContexts(ConcatenationContext.class);
		}
		public ConcatenationContext concatenation(int i) {
			return getRuleContext(ConcatenationContext.class,i);
		}
		public TerminalNode POWER() { return getToken(javascriptParser.POWER, 0); }
		public TerminalNode MULTI() { return getToken(javascriptParser.MULTI, 0); }
		public TerminalNode DIV() { return getToken(javascriptParser.DIV, 0); }
		public TerminalNode PLUS() { return getToken(javascriptParser.PLUS, 0); }
		public TerminalNode AMP() { return getToken(javascriptParser.AMP, 0); }
		public TerminalNode PERCENT() { return getToken(javascriptParser.PERCENT, 0); }
		public ConcatenationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_concatenation; }
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
			setState(36);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MINUS) {
				{
				setState(35);
				match(MINUS);
				}
			}

			setState(38);
			atom();
			}
			_ctx.stop = _input.LT(-1);
			setState(56);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(54);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(40);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(41);
						match(POWER);
						setState(42);
						concatenation(4);
						}
						break;
					case 2:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(43);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(44);
						_la = _input.LA(1);
						if ( !(_la==MULTI || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(45);
						concatenation(4);
						}
						break;
					case 3:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(46);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(47);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(48);
						concatenation(3);
						}
						break;
					case 4:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(49);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(50);
						match(AMP);
						setState(51);
						concatenation(2);
						}
						break;
					case 5:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(52);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(53);
						match(PERCENT);
						}
						break;
					}
					} 
				}
				setState(58);
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
		public TerminalNode LPAR() { return getToken(javascriptParser.LPAR, 0); }
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(javascriptParser.RPAR, 0); }
		public AttributionContext attribution() {
			return getRuleContext(AttributionContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_atom);
		try {
			setState(67);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(59);
				function_call();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				reference();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(61);
				literal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(62);
				match(LPAR);
				setState(63);
				comparison();
				setState(64);
				match(RPAR);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(66);
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

	public static class AttributionContext extends ParserRuleContext {
		public TerminalNode CELL_REF() { return getToken(javascriptParser.CELL_REF, 0); }
		public TerminalNode ATTRIB() { return getToken(javascriptParser.ATTRIB, 0); }
		public ConcatenationContext concatenation() {
			return getRuleContext(ConcatenationContext.class,0);
		}
		public AttributionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribution; }
	}

	public final AttributionContext attribution() throws RecognitionException {
		AttributionContext _localctx = new AttributionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_attribution);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(CELL_REF);
			setState(70);
			match(ATTRIB);
			setState(71);
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
		public TerminalNode FUNCTION() { return getToken(javascriptParser.FUNCTION, 0); }
		public TerminalNode LPAR() { return getToken(javascriptParser.LPAR, 0); }
		public List<ComparisonContext> comparison() {
			return getRuleContexts(ComparisonContext.class);
		}
		public ComparisonContext comparison(int i) {
			return getRuleContext(ComparisonContext.class,i);
		}
		public TerminalNode RPAR() { return getToken(javascriptParser.RPAR, 0); }
		public TerminalNode LCURLYBRACKETS() { return getToken(javascriptParser.LCURLYBRACKETS, 0); }
		public TerminalNode RCURLYBRACKETS() { return getToken(javascriptParser.RCURLYBRACKETS, 0); }
		public List<TerminalNode> SEMI() { return getTokens(javascriptParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(javascriptParser.SEMI, i);
		}
		public Function_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_call; }
	}

	public final Function_callContext function_call() throws RecognitionException {
		Function_callContext _localctx = new Function_callContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_function_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(FUNCTION);
			setState(74);
			match(LPAR);
			setState(75);
			comparison();
			setState(76);
			match(RPAR);
			setState(89);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(77);
				match(LCURLYBRACKETS);
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FUNCTION) | (1L << CELL_REF) | (1L << STRING) | (1L << NUMBER) | (1L << MINUS) | (1L << LPAR))) != 0)) {
					{
					setState(78);
					comparison();
					setState(83);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==SEMI) {
						{
						{
						setState(79);
						match(SEMI);
						setState(80);
						comparison();
						}
						}
						setState(85);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(88);
				match(RCURLYBRACKETS);
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

	public static class ReferenceContext extends ParserRuleContext {
		public List<TerminalNode> CELL_REF() { return getTokens(javascriptParser.CELL_REF); }
		public TerminalNode CELL_REF(int i) {
			return getToken(javascriptParser.CELL_REF, i);
		}
		public TerminalNode COLON() { return getToken(javascriptParser.COLON, 0); }
		public ReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reference; }
	}

	public final ReferenceContext reference() throws RecognitionException {
		ReferenceContext _localctx = new ReferenceContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_reference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(CELL_REF);
			setState(94);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				{
				setState(92);
				match(COLON);
				}
				setState(93);
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
		public TerminalNode NUMBER() { return getToken(javascriptParser.NUMBER, 0); }
		public TerminalNode STRING() { return getToken(javascriptParser.STRING, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 e\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3\2"+
		"\6\2\30\n\2\r\2\16\2\31\3\3\3\3\3\4\3\4\3\5\3\5\3\5\5\5#\n\5\3\6\3\6\5"+
		"\6\'\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\7\69\n\6\f\6\16\6<\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7F\n\7\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\tT\n\t\f\t\16\tW\13\t"+
		"\5\tY\n\t\3\t\5\t\\\n\t\3\n\3\n\3\n\5\na\n\n\3\13\3\13\3\13\2\3\n\f\2"+
		"\4\6\b\n\f\16\20\22\24\2\6\3\2\b\16\3\2\22\23\3\2\20\21\4\2\5\5\7\7\2"+
		"j\2\27\3\2\2\2\4\33\3\2\2\2\6\35\3\2\2\2\b\37\3\2\2\2\n$\3\2\2\2\fE\3"+
		"\2\2\2\16G\3\2\2\2\20K\3\2\2\2\22]\3\2\2\2\24b\3\2\2\2\26\30\5\4\3\2\27"+
		"\26\3\2\2\2\30\31\3\2\2\2\31\27\3\2\2\2\31\32\3\2\2\2\32\3\3\2\2\2\33"+
		"\34\5\6\4\2\34\5\3\2\2\2\35\36\5\b\5\2\36\7\3\2\2\2\37\"\5\n\6\2 !\t\2"+
		"\2\2!#\5\n\6\2\" \3\2\2\2\"#\3\2\2\2#\t\3\2\2\2$&\b\6\1\2%\'\7\21\2\2"+
		"&%\3\2\2\2&\'\3\2\2\2\'(\3\2\2\2()\5\f\7\2):\3\2\2\2*+\f\6\2\2+,\7\24"+
		"\2\2,9\5\n\6\6-.\f\5\2\2./\t\3\2\2/9\5\n\6\6\60\61\f\4\2\2\61\62\t\4\2"+
		"\2\629\5\n\6\5\63\64\f\3\2\2\64\65\7\17\2\2\659\5\n\6\4\66\67\f\7\2\2"+
		"\679\7\25\2\28*\3\2\2\28-\3\2\2\28\60\3\2\2\28\63\3\2\2\28\66\3\2\2\2"+
		"9<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;\13\3\2\2\2<:\3\2\2\2=F\5\20\t\2>F\5\22"+
		"\n\2?F\5\24\13\2@A\7\32\2\2AB\5\b\5\2BC\7\33\2\2CF\3\2\2\2DF\5\16\b\2"+
		"E=\3\2\2\2E>\3\2\2\2E?\3\2\2\2E@\3\2\2\2ED\3\2\2\2F\r\3\2\2\2GH\7\4\2"+
		"\2HI\7\27\2\2IJ\5\n\6\2J\17\3\2\2\2KL\7\3\2\2LM\7\32\2\2MN\5\b\5\2N[\7"+
		"\33\2\2OX\7\34\2\2PU\5\b\5\2QR\7\31\2\2RT\5\b\5\2SQ\3\2\2\2TW\3\2\2\2"+
		"US\3\2\2\2UV\3\2\2\2VY\3\2\2\2WU\3\2\2\2XP\3\2\2\2XY\3\2\2\2YZ\3\2\2\2"+
		"Z\\\7\35\2\2[O\3\2\2\2[\\\3\2\2\2\\\21\3\2\2\2]`\7\4\2\2^_\7\26\2\2_a"+
		"\7\4\2\2`^\3\2\2\2`a\3\2\2\2a\23\3\2\2\2bc\t\5\2\2c\25\3\2\2\2\f\31\""+
		"&8:EUX[`";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}