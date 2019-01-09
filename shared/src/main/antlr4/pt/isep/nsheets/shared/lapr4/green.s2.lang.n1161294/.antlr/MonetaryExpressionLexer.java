// Generated from D:/School/LAPR/project/shared/src/main/antlr4/pt/isep/nsheets/shared/lapr4/green.s2.lang.n1161294\MonetaryExpression.g4 by ANTLR 4.7

//    package pt.isep.nsheets.shared.core.formula.compiler;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MonetaryExpressionLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LPAR=1, RPAR=2, LBRACKET=3, RBRACKET=4, PLUS=5, MINUS=6, MULTI=7, DIV=8, 
		STARTER=9, FINAL_CURRENCY=10, CURRENCY=11, NUMBER=12, POINT=13;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"LPAR", "RPAR", "LBRACKET", "RBRACKET", "PLUS", "MINUS", "MULTI", "DIV", 
		"STARTER", "FINAL_CURRENCY", "CURRENCY", "NUMBER", "POINT", "DIGIT"
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


	public MonetaryExpressionLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MonetaryExpression.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\17Y\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3"+
		"\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13A\n\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\5\fI\n\f\3\r\6\rL\n\r\r\r\16\rM\3\r\3\r\3\r\3\r\5\rT\n"+
		"\r\3\16\3\16\3\17\3\17\2\2\20\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\35\2\3\2\2\2]\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\3\37\3\2"+
		"\2\2\5!\3\2\2\2\7#\3\2\2\2\t%\3\2\2\2\13\'\3\2\2\2\r)\3\2\2\2\17+\3\2"+
		"\2\2\21-\3\2\2\2\23/\3\2\2\2\25@\3\2\2\2\27H\3\2\2\2\31K\3\2\2\2\33U\3"+
		"\2\2\2\35W\3\2\2\2\37 \7*\2\2 \4\3\2\2\2!\"\7+\2\2\"\6\3\2\2\2#$\7}\2"+
		"\2$\b\3\2\2\2%&\7\177\2\2&\n\3\2\2\2\'(\7-\2\2(\f\3\2\2\2)*\7/\2\2*\16"+
		"\3\2\2\2+,\7,\2\2,\20\3\2\2\2-.\7\61\2\2.\22\3\2\2\2/\60\7%\2\2\60\24"+
		"\3\2\2\2\61\62\7g\2\2\62\63\7w\2\2\63\64\7t\2\2\64A\7q\2\2\65\66\7f\2"+
		"\2\66\67\7q\2\2\678\7n\2\289\7n\2\29:\7c\2\2:A\7t\2\2;<\7r\2\2<=\7q\2"+
		"\2=>\7w\2\2>?\7p\2\2?A\7f\2\2@\61\3\2\2\2@\65\3\2\2\2@;\3\2\2\2A\26\3"+
		"\2\2\2BC\7\u00e4\2\2CD\7\u201c\2\2DI\7\u00ae\2\2EI\7&\2\2FG\7\u00c4\2"+
		"\2GI\7\u00a5\2\2HB\3\2\2\2HE\3\2\2\2HF\3\2\2\2I\30\3\2\2\2JL\5\35\17\2"+
		"KJ\3\2\2\2LM\3\2\2\2MK\3\2\2\2MN\3\2\2\2NS\3\2\2\2OP\5\33\16\2PQ\5\35"+
		"\17\2QR\5\35\17\2RT\3\2\2\2SO\3\2\2\2ST\3\2\2\2T\32\3\2\2\2UV\7\60\2\2"+
		"V\34\3\2\2\2WX\4\62;\2X\36\3\2\2\2\7\2@HMS\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}