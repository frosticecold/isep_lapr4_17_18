// Generated from D:/School/LAPR/project/shared/src/main/antlr4/pt/isep/nsheets/shared/lapr4/green.s2.lang.n1161294\MonetaryExpression.g4 by ANTLR 4.7

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
public class MonetaryExpressionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LPAR=1, RPAR=2, LBRACKET=3, RBRACKET=4, PLUS=5, MINUS=6, MULTI=7, DIV=8, 
		STARTER=9, FINAL_CURRENCY=10, CURRENCY=11, NUMBER=12, POINT=13;
	public static final int
		RULE_expression = 0, RULE_monetary_formula = 1, RULE_operations = 2;
	public static final String[] ruleNames = {
		"expression", "monetary_formula", "operations"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'{'", "'}'", "'+'", "'-'", "'*'", "'/'", "'#'", null, 
		null, null, "'.'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LPAR", "RPAR", "LBRACKET", "RBRACKET", "PLUS", "MINUS", "MULTI", 
		"DIV", "STARTER", "FINAL_CURRENCY", "CURRENCY", "NUMBER", "POINT"
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
	public String getGrammarFileName() { return "MonetaryExpression.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MonetaryExpressionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode STARTER() { return getToken(MonetaryExpressionParser.STARTER, 0); }
		public Monetary_formulaContext monetary_formula() {
			return getRuleContext(Monetary_formulaContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MonetaryExpressionListener ) ((MonetaryExpressionListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MonetaryExpressionListener ) ((MonetaryExpressionListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonetaryExpressionVisitor ) return ((MonetaryExpressionVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(6);
			match(STARTER);
			setState(7);
			monetary_formula();
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

	public static class Monetary_formulaContext extends ParserRuleContext {
		public TerminalNode FINAL_CURRENCY() { return getToken(MonetaryExpressionParser.FINAL_CURRENCY, 0); }
		public TerminalNode LBRACKET() { return getToken(MonetaryExpressionParser.LBRACKET, 0); }
		public OperationsContext operations() {
			return getRuleContext(OperationsContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(MonetaryExpressionParser.RBRACKET, 0); }
		public Monetary_formulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_monetary_formula; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MonetaryExpressionListener ) ((MonetaryExpressionListener)listener).enterMonetary_formula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MonetaryExpressionListener ) ((MonetaryExpressionListener)listener).exitMonetary_formula(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonetaryExpressionVisitor ) return ((MonetaryExpressionVisitor<? extends T>)visitor).visitMonetary_formula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Monetary_formulaContext monetary_formula() throws RecognitionException {
		Monetary_formulaContext _localctx = new Monetary_formulaContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_monetary_formula);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(9);
			match(FINAL_CURRENCY);
			setState(10);
			match(LBRACKET);
			setState(11);
			operations(0);
			setState(12);
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

	public static class OperationsContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(MonetaryExpressionParser.NUMBER, 0); }
		public TerminalNode CURRENCY() { return getToken(MonetaryExpressionParser.CURRENCY, 0); }
		public List<OperationsContext> operations() {
			return getRuleContexts(OperationsContext.class);
		}
		public OperationsContext operations(int i) {
			return getRuleContext(OperationsContext.class,i);
		}
		public TerminalNode MULTI() { return getToken(MonetaryExpressionParser.MULTI, 0); }
		public TerminalNode DIV() { return getToken(MonetaryExpressionParser.DIV, 0); }
		public TerminalNode PLUS() { return getToken(MonetaryExpressionParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(MonetaryExpressionParser.MINUS, 0); }
		public OperationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MonetaryExpressionListener ) ((MonetaryExpressionListener)listener).enterOperations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MonetaryExpressionListener ) ((MonetaryExpressionListener)listener).exitOperations(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonetaryExpressionVisitor ) return ((MonetaryExpressionVisitor<? extends T>)visitor).visitOperations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationsContext operations() throws RecognitionException {
		return operations(0);
	}

	private OperationsContext operations(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		OperationsContext _localctx = new OperationsContext(_ctx, _parentState);
		OperationsContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_operations, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(15);
			match(NUMBER);
			setState(16);
			match(CURRENCY);
			}
			_ctx.stop = _input.LT(-1);
			setState(26);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(24);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						_localctx = new OperationsContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_operations);
						setState(18);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(19);
						_la = _input.LA(1);
						if ( !(_la==MULTI || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(20);
						operations(3);
						}
						break;
					case 2:
						{
						_localctx = new OperationsContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_operations);
						setState(21);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(22);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(23);
						operations(2);
						}
						break;
					}
					} 
				}
				setState(28);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return operations_sempred((OperationsContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean operations_sempred(OperationsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\17 \4\2\t\2\4\3\t"+
		"\3\4\4\t\4\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\7\4\33\n\4\f\4\16\4\36\13\4\3\4\2\3\6\5\2\4\6\2\4\3\2\t\n"+
		"\3\2\7\b\2\36\2\b\3\2\2\2\4\13\3\2\2\2\6\20\3\2\2\2\b\t\7\13\2\2\t\n\5"+
		"\4\3\2\n\3\3\2\2\2\13\f\7\f\2\2\f\r\7\5\2\2\r\16\5\6\4\2\16\17\7\6\2\2"+
		"\17\5\3\2\2\2\20\21\b\4\1\2\21\22\7\16\2\2\22\23\7\r\2\2\23\34\3\2\2\2"+
		"\24\25\f\4\2\2\25\26\t\2\2\2\26\33\5\6\4\5\27\30\f\3\2\2\30\31\t\3\2\2"+
		"\31\33\5\6\4\4\32\24\3\2\2\2\32\27\3\2\2\2\33\36\3\2\2\2\34\32\3\2\2\2"+
		"\34\35\3\2\2\2\35\7\3\2\2\2\36\34\3\2\2\2\4\32\34";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}