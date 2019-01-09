// Generated from e:\isep\2_ano\2SEM\LAPR4\lapr4-18-2dc\shared\src\main\antlr4\pt\isep\nsheets\shared\lapr4\blue\s1\lang\n1090657\VisualBasic.g4 by ANTLR 4.7.1

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
public class VisualBasicParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		FUNCTION=1, CELL_REF=2, STRING=3, QUOT=4, NUMBER=5, EQ=6, NEQ=7, LTEQ=8, 
		GTEQ=9, GT=10, LT=11, AMP=12, PLUS=13, MINUS=14, MULTI=15, DIV=16, POWER=17, 
		PERCENT=18, COLON=19, ATTRIB=20, COMMA=21, SEMI=22, LPAR=23, RPAR=24, 
		LCURLYBRACKET=25, RCURLYBRACKET=26, WS=27, LINECOMMENT=28, DOUBLESLASH=29, 
		IF=30, THEN=31, ELSE=32, ENDIF=33, FUNC=34, FOR=35, ENDFOR=36, TO=37, 
		STEP=38;
	public static final int
		RULE_macro = 0, RULE_line = 1, RULE_comparison = 2, RULE_concatenation = 3, 
		RULE_atom = 4, RULE_attribution = 5, RULE_function_call = 6, RULE_ifexpr = 7, 
		RULE_forexpr = 8, RULE_reference = 9, RULE_literal = 10;
	public static final String[] ruleNames = {
		"macro", "line", "comparison", "concatenation", "atom", "attribution", 
		"function_call", "ifexpr", "forexpr", "reference", "literal"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, "'\"'", null, "'='", "'<>'", "'<='", "'>='", "'>'", 
		"'<'", "'&'", "'+'", "'-'", "'*'", "'/'", "'^'", "'%'", "':'", "':='", 
		"','", "';'", "'('", "')'", "'{'", "'}'", null, null, "'//'", null, null, 
		null, null, "'func.'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "FUNCTION", "CELL_REF", "STRING", "QUOT", "NUMBER", "EQ", "NEQ", 
		"LTEQ", "GTEQ", "GT", "LT", "AMP", "PLUS", "MINUS", "MULTI", "DIV", "POWER", 
		"PERCENT", "COLON", "ATTRIB", "COMMA", "SEMI", "LPAR", "RPAR", "LCURLYBRACKET", 
		"RCURLYBRACKET", "WS", "LINECOMMENT", "DOUBLESLASH", "IF", "THEN", "ELSE", 
		"ENDIF", "FUNC", "FOR", "ENDFOR", "TO", "STEP"
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
	public String getGrammarFileName() { return "VisualBasic.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public VisualBasicParser(TokenStream input) {
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
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FUNCTION) | (1L << CELL_REF) | (1L << STRING) | (1L << NUMBER) | (1L << MINUS) | (1L << LPAR) | (1L << IF) | (1L << FOR))) != 0) );
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
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
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
		public TerminalNode EQ() { return getToken(VisualBasicParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(VisualBasicParser.NEQ, 0); }
		public TerminalNode GT() { return getToken(VisualBasicParser.GT, 0); }
		public TerminalNode LT() { return getToken(VisualBasicParser.LT, 0); }
		public TerminalNode LTEQ() { return getToken(VisualBasicParser.LTEQ, 0); }
		public TerminalNode GTEQ() { return getToken(VisualBasicParser.GTEQ, 0); }
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_comparison);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			concatenation(0);
			setState(32);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << LTEQ) | (1L << GTEQ) | (1L << GT) | (1L << LT))) != 0)) {
				{
				setState(30);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << LTEQ) | (1L << GTEQ) | (1L << GT) | (1L << LT))) != 0)) ) {
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
		public TerminalNode MINUS() { return getToken(VisualBasicParser.MINUS, 0); }
		public IfexprContext ifexpr() {
			return getRuleContext(IfexprContext.class,0);
		}
		public ForexprContext forexpr() {
			return getRuleContext(ForexprContext.class,0);
		}
		public List<ConcatenationContext> concatenation() {
			return getRuleContexts(ConcatenationContext.class);
		}
		public ConcatenationContext concatenation(int i) {
			return getRuleContext(ConcatenationContext.class,i);
		}
		public TerminalNode POWER() { return getToken(VisualBasicParser.POWER, 0); }
		public TerminalNode MULTI() { return getToken(VisualBasicParser.MULTI, 0); }
		public TerminalNode DIV() { return getToken(VisualBasicParser.DIV, 0); }
		public TerminalNode PLUS() { return getToken(VisualBasicParser.PLUS, 0); }
		public TerminalNode AMP() { return getToken(VisualBasicParser.AMP, 0); }
		public TerminalNode PERCENT() { return getToken(VisualBasicParser.PERCENT, 0); }
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
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_concatenation, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FUNCTION:
			case CELL_REF:
			case STRING:
			case NUMBER:
			case MINUS:
			case LPAR:
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
				break;
			case IF:
				{
				setState(39);
				ifexpr();
				}
				break;
			case FOR:
				{
				setState(40);
				forexpr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(59);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(57);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(43);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(44);
						match(POWER);
						setState(45);
						concatenation(6);
						}
						break;
					case 2:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(46);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(47);
						_la = _input.LA(1);
						if ( !(_la==MULTI || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(48);
						concatenation(6);
						}
						break;
					case 3:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(49);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(50);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(51);
						concatenation(5);
						}
						break;
					case 4:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(52);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(53);
						match(AMP);
						setState(54);
						concatenation(4);
						}
						break;
					case 5:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(55);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(56);
						match(PERCENT);
						}
						break;
					}
					} 
				}
				setState(61);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
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
		public TerminalNode LPAR() { return getToken(VisualBasicParser.LPAR, 0); }
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(VisualBasicParser.RPAR, 0); }
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
		enterRule(_localctx, 8, RULE_atom);
		try {
			setState(70);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(62);
				function_call();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(63);
				reference();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(64);
				literal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(65);
				match(LPAR);
				setState(66);
				comparison();
				setState(67);
				match(RPAR);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
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

	public static class AttributionContext extends ParserRuleContext {
		public TerminalNode CELL_REF() { return getToken(VisualBasicParser.CELL_REF, 0); }
		public TerminalNode ATTRIB() { return getToken(VisualBasicParser.ATTRIB, 0); }
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
		enterRule(_localctx, 10, RULE_attribution);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(CELL_REF);
			setState(73);
			match(ATTRIB);
			setState(74);
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
		public TerminalNode FUNCTION() { return getToken(VisualBasicParser.FUNCTION, 0); }
		public TerminalNode LPAR() { return getToken(VisualBasicParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(VisualBasicParser.RPAR, 0); }
		public List<ComparisonContext> comparison() {
			return getRuleContexts(ComparisonContext.class);
		}
		public ComparisonContext comparison(int i) {
			return getRuleContext(ComparisonContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(VisualBasicParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(VisualBasicParser.COMMA, i);
		}
		public Function_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_call; }
	}

	public final Function_callContext function_call() throws RecognitionException {
		Function_callContext _localctx = new Function_callContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_function_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(FUNCTION);
			setState(77);
			match(LPAR);
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FUNCTION) | (1L << CELL_REF) | (1L << STRING) | (1L << NUMBER) | (1L << MINUS) | (1L << LPAR) | (1L << IF) | (1L << FOR))) != 0)) {
				{
				setState(78);
				comparison();
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(79);
					match(COMMA);
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
			match(RPAR);
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

	public static class IfexprContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(VisualBasicParser.IF, 0); }
		public TerminalNode LPAR() { return getToken(VisualBasicParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(VisualBasicParser.RPAR, 0); }
		public TerminalNode THEN() { return getToken(VisualBasicParser.THEN, 0); }
		public List<ComparisonContext> comparison() {
			return getRuleContexts(ComparisonContext.class);
		}
		public ComparisonContext comparison(int i) {
			return getRuleContext(ComparisonContext.class,i);
		}
		public TerminalNode ENDIF() { return getToken(VisualBasicParser.ENDIF, 0); }
		public TerminalNode ELSE() { return getToken(VisualBasicParser.ELSE, 0); }
		public IfexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifexpr; }
	}

	public final IfexprContext ifexpr() throws RecognitionException {
		IfexprContext _localctx = new IfexprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ifexpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(IF);
			setState(91);
			match(LPAR);
			{
			setState(92);
			comparison();
			}
			setState(93);
			match(RPAR);
			setState(94);
			match(THEN);
			setState(95);
			comparison();
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(96);
				match(ELSE);
				setState(97);
				comparison();
				}
			}

			setState(100);
			match(ENDIF);
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

	public static class ForexprContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(VisualBasicParser.FOR, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(VisualBasicParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(VisualBasicParser.NUMBER, i);
		}
		public TerminalNode TO() { return getToken(VisualBasicParser.TO, 0); }
		public TerminalNode LCURLYBRACKET() { return getToken(VisualBasicParser.LCURLYBRACKET, 0); }
		public List<ConcatenationContext> concatenation() {
			return getRuleContexts(ConcatenationContext.class);
		}
		public ConcatenationContext concatenation(int i) {
			return getRuleContext(ConcatenationContext.class,i);
		}
		public TerminalNode RCURLYBRACKET() { return getToken(VisualBasicParser.RCURLYBRACKET, 0); }
		public TerminalNode ENDFOR() { return getToken(VisualBasicParser.ENDFOR, 0); }
		public TerminalNode STEP() { return getToken(VisualBasicParser.STEP, 0); }
		public List<TerminalNode> SEMI() { return getTokens(VisualBasicParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(VisualBasicParser.SEMI, i);
		}
		public ForexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forexpr; }
	}

	public final ForexprContext forexpr() throws RecognitionException {
		ForexprContext _localctx = new ForexprContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_forexpr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(FOR);
			setState(103);
			match(NUMBER);
			setState(104);
			match(TO);
			setState(105);
			match(NUMBER);
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STEP) {
				{
				setState(106);
				match(STEP);
				setState(107);
				match(NUMBER);
				}
			}

			setState(110);
			match(LCURLYBRACKET);
			setState(116);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(111);
					concatenation(0);
					setState(112);
					match(SEMI);
					}
					} 
				}
				setState(118);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			setState(119);
			concatenation(0);
			setState(120);
			match(RCURLYBRACKET);
			setState(121);
			match(ENDFOR);
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
		public List<TerminalNode> CELL_REF() { return getTokens(VisualBasicParser.CELL_REF); }
		public TerminalNode CELL_REF(int i) {
			return getToken(VisualBasicParser.CELL_REF, i);
		}
		public TerminalNode COLON() { return getToken(VisualBasicParser.COLON, 0); }
		public ReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reference; }
	}

	public final ReferenceContext reference() throws RecognitionException {
		ReferenceContext _localctx = new ReferenceContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_reference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(CELL_REF);
			setState(126);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				{
				setState(124);
				match(COLON);
				}
				setState(125);
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
		public TerminalNode NUMBER() { return getToken(VisualBasicParser.NUMBER, 0); }
		public TerminalNode STRING() { return getToken(VisualBasicParser.STRING, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
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
		case 3:
			return concatenation_sempred((ConcatenationContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean concatenation_sempred(ConcatenationContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 3);
		case 4:
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3(\u0085\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\3\2\6\2\32\n\2\r\2\16\2\33\3\3\3\3\3\4\3\4\3\4\5\4#\n\4\3"+
		"\5\3\5\5\5\'\n\5\3\5\3\5\3\5\5\5,\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\7\5<\n\5\f\5\16\5?\13\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\5\6I\n\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\7\bT\n\b\f\b\16"+
		"\bW\13\b\5\bY\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\te\n\t\3\t"+
		"\3\t\3\n\3\n\3\n\3\n\3\n\3\n\5\no\n\n\3\n\3\n\3\n\3\n\7\nu\n\n\f\n\16"+
		"\nx\13\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\5\13\u0081\n\13\3\f\3\f\3\f\2"+
		"\3\b\r\2\4\6\b\n\f\16\20\22\24\26\2\6\3\2\b\r\3\2\21\22\3\2\17\20\4\2"+
		"\5\5\7\7\2\u008d\2\31\3\2\2\2\4\35\3\2\2\2\6\37\3\2\2\2\b+\3\2\2\2\nH"+
		"\3\2\2\2\fJ\3\2\2\2\16N\3\2\2\2\20\\\3\2\2\2\22h\3\2\2\2\24}\3\2\2\2\26"+
		"\u0082\3\2\2\2\30\32\5\4\3\2\31\30\3\2\2\2\32\33\3\2\2\2\33\31\3\2\2\2"+
		"\33\34\3\2\2\2\34\3\3\2\2\2\35\36\5\6\4\2\36\5\3\2\2\2\37\"\5\b\5\2 !"+
		"\t\2\2\2!#\5\b\5\2\" \3\2\2\2\"#\3\2\2\2#\7\3\2\2\2$&\b\5\1\2%\'\7\20"+
		"\2\2&%\3\2\2\2&\'\3\2\2\2\'(\3\2\2\2(,\5\n\6\2),\5\20\t\2*,\5\22\n\2+"+
		"$\3\2\2\2+)\3\2\2\2+*\3\2\2\2,=\3\2\2\2-.\f\b\2\2./\7\23\2\2/<\5\b\5\b"+
		"\60\61\f\7\2\2\61\62\t\3\2\2\62<\5\b\5\b\63\64\f\6\2\2\64\65\t\4\2\2\65"+
		"<\5\b\5\7\66\67\f\5\2\2\678\7\16\2\28<\5\b\5\69:\f\t\2\2:<\7\24\2\2;-"+
		"\3\2\2\2;\60\3\2\2\2;\63\3\2\2\2;\66\3\2\2\2;9\3\2\2\2<?\3\2\2\2=;\3\2"+
		"\2\2=>\3\2\2\2>\t\3\2\2\2?=\3\2\2\2@I\5\16\b\2AI\5\24\13\2BI\5\26\f\2"+
		"CD\7\31\2\2DE\5\6\4\2EF\7\32\2\2FI\3\2\2\2GI\5\f\7\2H@\3\2\2\2HA\3\2\2"+
		"\2HB\3\2\2\2HC\3\2\2\2HG\3\2\2\2I\13\3\2\2\2JK\7\4\2\2KL\7\26\2\2LM\5"+
		"\b\5\2M\r\3\2\2\2NO\7\3\2\2OX\7\31\2\2PU\5\6\4\2QR\7\27\2\2RT\5\6\4\2"+
		"SQ\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2VY\3\2\2\2WU\3\2\2\2XP\3\2\2\2"+
		"XY\3\2\2\2YZ\3\2\2\2Z[\7\32\2\2[\17\3\2\2\2\\]\7 \2\2]^\7\31\2\2^_\5\6"+
		"\4\2_`\7\32\2\2`a\7!\2\2ad\5\6\4\2bc\7\"\2\2ce\5\6\4\2db\3\2\2\2de\3\2"+
		"\2\2ef\3\2\2\2fg\7#\2\2g\21\3\2\2\2hi\7%\2\2ij\7\7\2\2jk\7\'\2\2kn\7\7"+
		"\2\2lm\7(\2\2mo\7\7\2\2nl\3\2\2\2no\3\2\2\2op\3\2\2\2pv\7\33\2\2qr\5\b"+
		"\5\2rs\7\30\2\2su\3\2\2\2tq\3\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2wy\3"+
		"\2\2\2xv\3\2\2\2yz\5\b\5\2z{\7\34\2\2{|\7&\2\2|\23\3\2\2\2}\u0080\7\4"+
		"\2\2~\177\7\25\2\2\177\u0081\7\4\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2"+
		"\2\u0081\25\3\2\2\2\u0082\u0083\t\5\2\2\u0083\27\3\2\2\2\17\33\"&+;=H"+
		"UXdnv\u0080";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}