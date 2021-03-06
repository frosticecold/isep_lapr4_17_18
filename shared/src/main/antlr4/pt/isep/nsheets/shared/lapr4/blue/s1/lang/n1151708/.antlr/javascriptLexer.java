// Generated from c:\Users\MarioDias\Documents\ISEP\2�ANO\2�Semestre\LAPR4\lapr4-18-2dc\shared\src\main\antlr4\pt\isep\nsheets\shared\lapr4\blue\s1\lang\n1151708\javascript.g4 by ANTLR 4.7.1

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
public class javascriptLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"LETTER", "FUNCTION", "CELL_REF", "STRING", "QUOT", "NUMBER", "DIGIT", 
		"EQ", "DOUBLEEQ", "NEQ", "LTEQ", "GTEQ", "GT", "LT", "AMP", "PLUS", "MINUS", 
		"MULTI", "DIV", "POWER", "PERCENT", "ABS", "EXCL", "COLON", "ATTRIB", 
		"COMMA", "SEMI", "LPAR", "RPAR", "LCURLYBRACKETS", "RCURLYBRACKETS", "WS", 
		"LINECOMMENT", "DOUBLESLASH"
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


	public javascriptLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "javascript.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2 \u00be\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\3\2\3\2\3\3\6\3K\n\3\r\3\16\3L\3\4\5\4P\n\4\3\4\3\4"+
		"\5\4T\n\4\3\4\5\4W\n\4\3\4\6\4Z\n\4\r\4\16\4[\3\5\3\5\3\5\3\5\7\5b\n\5"+
		"\f\5\16\5e\13\5\3\5\3\5\3\6\3\6\3\7\6\7l\n\7\r\7\16\7m\3\7\3\7\6\7r\n"+
		"\7\r\7\16\7s\5\7v\n\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3"+
		"\f\3\f\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3"+
		"\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3"+
		"\32\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 "+
		"\3!\3!\3!\3!\3\"\3\"\7\"\u00b5\n\"\f\"\16\"\u00b8\13\"\3\"\3\"\3#\3#\3"+
		"#\2\2$\3\2\5\3\7\4\t\5\13\6\r\7\17\2\21\b\23\t\25\n\27\13\31\f\33\r\35"+
		"\16\37\17!\20#\21%\22\'\23)\24+\25-\2/\2\61\26\63\27\65\30\67\319\32;"+
		"\33=\34?\35A\36C\37E \3\2\6\4\2C\\c|\3\2$$\5\2\13\f\17\17\"\"\4\2\f\f"+
		"\17\17\2\u00c4\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2"+
		"\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65"+
		"\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3"+
		"\2\2\2\2C\3\2\2\2\2E\3\2\2\2\3G\3\2\2\2\5J\3\2\2\2\7O\3\2\2\2\t]\3\2\2"+
		"\2\13h\3\2\2\2\rk\3\2\2\2\17w\3\2\2\2\21y\3\2\2\2\23{\3\2\2\2\25~\3\2"+
		"\2\2\27\u0081\3\2\2\2\31\u0084\3\2\2\2\33\u0087\3\2\2\2\35\u0089\3\2\2"+
		"\2\37\u008b\3\2\2\2!\u008d\3\2\2\2#\u008f\3\2\2\2%\u0091\3\2\2\2\'\u0093"+
		"\3\2\2\2)\u0095\3\2\2\2+\u0097\3\2\2\2-\u0099\3\2\2\2/\u009b\3\2\2\2\61"+
		"\u009d\3\2\2\2\63\u009f\3\2\2\2\65\u00a2\3\2\2\2\67\u00a4\3\2\2\29\u00a6"+
		"\3\2\2\2;\u00a8\3\2\2\2=\u00aa\3\2\2\2?\u00ac\3\2\2\2A\u00ae\3\2\2\2C"+
		"\u00b2\3\2\2\2E\u00bb\3\2\2\2GH\t\2\2\2H\4\3\2\2\2IK\5\3\2\2JI\3\2\2\2"+
		"KL\3\2\2\2LJ\3\2\2\2LM\3\2\2\2M\6\3\2\2\2NP\5-\27\2ON\3\2\2\2OP\3\2\2"+
		"\2PQ\3\2\2\2QS\5\3\2\2RT\5\3\2\2SR\3\2\2\2ST\3\2\2\2TV\3\2\2\2UW\5-\27"+
		"\2VU\3\2\2\2VW\3\2\2\2WY\3\2\2\2XZ\5\17\b\2YX\3\2\2\2Z[\3\2\2\2[Y\3\2"+
		"\2\2[\\\3\2\2\2\\\b\3\2\2\2]c\5\13\6\2^_\7^\2\2_b\7$\2\2`b\n\3\2\2a^\3"+
		"\2\2\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2df\3\2\2\2ec\3\2\2\2fg\5"+
		"\13\6\2g\n\3\2\2\2hi\7$\2\2i\f\3\2\2\2jl\5\17\b\2kj\3\2\2\2lm\3\2\2\2"+
		"mk\3\2\2\2mn\3\2\2\2nu\3\2\2\2oq\5\65\33\2pr\5\17\b\2qp\3\2\2\2rs\3\2"+
		"\2\2sq\3\2\2\2st\3\2\2\2tv\3\2\2\2uo\3\2\2\2uv\3\2\2\2v\16\3\2\2\2wx\4"+
		"\62;\2x\20\3\2\2\2yz\7?\2\2z\22\3\2\2\2{|\7?\2\2|}\7?\2\2}\24\3\2\2\2"+
		"~\177\7#\2\2\177\u0080\7?\2\2\u0080\26\3\2\2\2\u0081\u0082\7>\2\2\u0082"+
		"\u0083\7?\2\2\u0083\30\3\2\2\2\u0084\u0085\7@\2\2\u0085\u0086\7?\2\2\u0086"+
		"\32\3\2\2\2\u0087\u0088\7@\2\2\u0088\34\3\2\2\2\u0089\u008a\7>\2\2\u008a"+
		"\36\3\2\2\2\u008b\u008c\7(\2\2\u008c \3\2\2\2\u008d\u008e\7-\2\2\u008e"+
		"\"\3\2\2\2\u008f\u0090\7/\2\2\u0090$\3\2\2\2\u0091\u0092\7,\2\2\u0092"+
		"&\3\2\2\2\u0093\u0094\7\61\2\2\u0094(\3\2\2\2\u0095\u0096\7`\2\2\u0096"+
		"*\3\2\2\2\u0097\u0098\7\'\2\2\u0098,\3\2\2\2\u0099\u009a\7&\2\2\u009a"+
		".\3\2\2\2\u009b\u009c\7#\2\2\u009c\60\3\2\2\2\u009d\u009e\7<\2\2\u009e"+
		"\62\3\2\2\2\u009f\u00a0\7<\2\2\u00a0\u00a1\7?\2\2\u00a1\64\3\2\2\2\u00a2"+
		"\u00a3\7.\2\2\u00a3\66\3\2\2\2\u00a4\u00a5\7=\2\2\u00a58\3\2\2\2\u00a6"+
		"\u00a7\7*\2\2\u00a7:\3\2\2\2\u00a8\u00a9\7+\2\2\u00a9<\3\2\2\2\u00aa\u00ab"+
		"\7}\2\2\u00ab>\3\2\2\2\u00ac\u00ad\7\177\2\2\u00ad@\3\2\2\2\u00ae\u00af"+
		"\t\4\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\b!\2\2\u00b1B\3\2\2\2\u00b2\u00b6"+
		"\5E#\2\u00b3\u00b5\n\5\2\2\u00b4\u00b3\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6"+
		"\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b9\3\2\2\2\u00b8\u00b6\3\2"+
		"\2\2\u00b9\u00ba\b\"\2\2\u00baD\3\2\2\2\u00bb\u00bc\7\61\2\2\u00bc\u00bd"+
		"\7\61\2\2\u00bdF\3\2\2\2\16\2LOSV[acmsu\u00b6\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}