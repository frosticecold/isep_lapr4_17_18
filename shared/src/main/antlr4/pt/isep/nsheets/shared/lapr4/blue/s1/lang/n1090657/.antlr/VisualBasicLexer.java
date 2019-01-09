// Generated from e:\isep\2_ano\2SEM\LAPR4\lapr4-18-2dc\shared\src\main\antlr4\pt\isep\nsheets\shared\lapr4\blue\s1\lang\n1090657\VisualBasic.g4 by ANTLR 4.7.1

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
public class VisualBasicLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"LETTER", "FUNCTION", "CELL_REF", "STRING", "QUOT", "NUMBER", "DIGIT", 
		"EQ", "NEQ", "LTEQ", "GTEQ", "GT", "LT", "AMP", "PLUS", "MINUS", "MULTI", 
		"DIV", "POWER", "PERCENT", "ABS", "EXCL", "COLON", "ATTRIB", "COMMA", 
		"SEMI", "LPAR", "RPAR", "LCURLYBRACKET", "RCURLYBRACKET", "WS", "LINECOMMENT", 
		"DOUBLESLASH", "IF", "THEN", "ELSE", "ENDIF", "FUNC", "FOR", "ENDFOR", 
		"TO", "STEP"
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


	public VisualBasicLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "VisualBasic.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2(\u0127\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3a\n\3\r\3\16\3b\3\4\5\4f\n\4\3\4"+
		"\3\4\5\4j\n\4\3\4\5\4m\n\4\3\4\6\4p\n\4\r\4\16\4q\3\5\3\5\3\5\3\5\7\5"+
		"x\n\5\f\5\16\5{\13\5\3\5\3\5\3\6\3\6\3\7\6\7\u0082\n\7\r\7\16\7\u0083"+
		"\3\7\3\7\6\7\u0088\n\7\r\7\16\7\u0089\5\7\u008c\n\7\3\b\3\b\3\t\3\t\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20"+
		"\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27"+
		"\3\27\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35"+
		"\3\36\3\36\3\37\3\37\3 \3 \3 \3 \3!\3!\7!\u00c8\n!\f!\16!\u00cb\13!\3"+
		"!\3!\3\"\3\"\3\"\3#\3#\3#\3#\5#\u00d6\n#\3$\3$\3$\3$\3$\3$\3$\3$\5$\u00e0"+
		"\n$\3%\3%\3%\3%\3%\3%\3%\3%\5%\u00ea\n%\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&"+
		"\5&\u00f6\n&\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\5(\u0104\n(\3)"+
		"\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\5)\u0112\n)\3*\3*\3*\3*\5*\u0118\n*"+
		"\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\5+\u0126\n+\2\2,\3\2\5\3\7\4\t\5"+
		"\13\6\r\7\17\2\21\b\23\t\25\n\27\13\31\f\33\r\35\16\37\17!\20#\21%\22"+
		"\'\23)\24+\2-\2/\25\61\26\63\27\65\30\67\319\32;\33=\34?\35A\36C\37E "+
		"G!I\"K#M$O%Q&S\'U(\3\2\6\4\2C\\c|\3\2$$\5\2\13\f\17\17\"\"\4\2\f\f\17"+
		"\17\2\u0136\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2"+
		"\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2"+
		"\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2"+
		"O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\3W\3\2\2\2\5Y\3\2\2\2\7e\3"+
		"\2\2\2\ts\3\2\2\2\13~\3\2\2\2\r\u0081\3\2\2\2\17\u008d\3\2\2\2\21\u008f"+
		"\3\2\2\2\23\u0091\3\2\2\2\25\u0094\3\2\2\2\27\u0097\3\2\2\2\31\u009a\3"+
		"\2\2\2\33\u009c\3\2\2\2\35\u009e\3\2\2\2\37\u00a0\3\2\2\2!\u00a2\3\2\2"+
		"\2#\u00a4\3\2\2\2%\u00a6\3\2\2\2\'\u00a8\3\2\2\2)\u00aa\3\2\2\2+\u00ac"+
		"\3\2\2\2-\u00ae\3\2\2\2/\u00b0\3\2\2\2\61\u00b2\3\2\2\2\63\u00b5\3\2\2"+
		"\2\65\u00b7\3\2\2\2\67\u00b9\3\2\2\29\u00bb\3\2\2\2;\u00bd\3\2\2\2=\u00bf"+
		"\3\2\2\2?\u00c1\3\2\2\2A\u00c5\3\2\2\2C\u00ce\3\2\2\2E\u00d5\3\2\2\2G"+
		"\u00df\3\2\2\2I\u00e9\3\2\2\2K\u00f5\3\2\2\2M\u00f7\3\2\2\2O\u0103\3\2"+
		"\2\2Q\u0111\3\2\2\2S\u0117\3\2\2\2U\u0125\3\2\2\2WX\t\2\2\2X\4\3\2\2\2"+
		"YZ\7h\2\2Z[\7w\2\2[\\\7p\2\2\\]\7e\2\2]^\7\60\2\2^`\3\2\2\2_a\5\3\2\2"+
		"`_\3\2\2\2ab\3\2\2\2b`\3\2\2\2bc\3\2\2\2c\6\3\2\2\2df\5+\26\2ed\3\2\2"+
		"\2ef\3\2\2\2fg\3\2\2\2gi\5\3\2\2hj\5\3\2\2ih\3\2\2\2ij\3\2\2\2jl\3\2\2"+
		"\2km\5+\26\2lk\3\2\2\2lm\3\2\2\2mo\3\2\2\2np\5\17\b\2on\3\2\2\2pq\3\2"+
		"\2\2qo\3\2\2\2qr\3\2\2\2r\b\3\2\2\2sy\5\13\6\2tu\7^\2\2ux\7$\2\2vx\n\3"+
		"\2\2wt\3\2\2\2wv\3\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z|\3\2\2\2{y\3\2"+
		"\2\2|}\5\13\6\2}\n\3\2\2\2~\177\7$\2\2\177\f\3\2\2\2\u0080\u0082\5\17"+
		"\b\2\u0081\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0081\3\2\2\2\u0083"+
		"\u0084\3\2\2\2\u0084\u008b\3\2\2\2\u0085\u0087\5\63\32\2\u0086\u0088\5"+
		"\17\b\2\u0087\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u0087\3\2\2\2\u0089"+
		"\u008a\3\2\2\2\u008a\u008c\3\2\2\2\u008b\u0085\3\2\2\2\u008b\u008c\3\2"+
		"\2\2\u008c\16\3\2\2\2\u008d\u008e\4\62;\2\u008e\20\3\2\2\2\u008f\u0090"+
		"\7?\2\2\u0090\22\3\2\2\2\u0091\u0092\7>\2\2\u0092\u0093\7@\2\2\u0093\24"+
		"\3\2\2\2\u0094\u0095\7>\2\2\u0095\u0096\7?\2\2\u0096\26\3\2\2\2\u0097"+
		"\u0098\7@\2\2\u0098\u0099\7?\2\2\u0099\30\3\2\2\2\u009a\u009b\7@\2\2\u009b"+
		"\32\3\2\2\2\u009c\u009d\7>\2\2\u009d\34\3\2\2\2\u009e\u009f\7(\2\2\u009f"+
		"\36\3\2\2\2\u00a0\u00a1\7-\2\2\u00a1 \3\2\2\2\u00a2\u00a3\7/\2\2\u00a3"+
		"\"\3\2\2\2\u00a4\u00a5\7,\2\2\u00a5$\3\2\2\2\u00a6\u00a7\7\61\2\2\u00a7"+
		"&\3\2\2\2\u00a8\u00a9\7`\2\2\u00a9(\3\2\2\2\u00aa\u00ab\7\'\2\2\u00ab"+
		"*\3\2\2\2\u00ac\u00ad\7&\2\2\u00ad,\3\2\2\2\u00ae\u00af\7#\2\2\u00af."+
		"\3\2\2\2\u00b0\u00b1\7<\2\2\u00b1\60\3\2\2\2\u00b2\u00b3\7<\2\2\u00b3"+
		"\u00b4\7?\2\2\u00b4\62\3\2\2\2\u00b5\u00b6\7.\2\2\u00b6\64\3\2\2\2\u00b7"+
		"\u00b8\7=\2\2\u00b8\66\3\2\2\2\u00b9\u00ba\7*\2\2\u00ba8\3\2\2\2\u00bb"+
		"\u00bc\7+\2\2\u00bc:\3\2\2\2\u00bd\u00be\7}\2\2\u00be<\3\2\2\2\u00bf\u00c0"+
		"\7\177\2\2\u00c0>\3\2\2\2\u00c1\u00c2\t\4\2\2\u00c2\u00c3\3\2\2\2\u00c3"+
		"\u00c4\b \2\2\u00c4@\3\2\2\2\u00c5\u00c9\5C\"\2\u00c6\u00c8\n\5\2\2\u00c7"+
		"\u00c6\3\2\2\2\u00c8\u00cb\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2"+
		"\2\2\u00ca\u00cc\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cc\u00cd\b!\2\2\u00cd"+
		"B\3\2\2\2\u00ce\u00cf\7\61\2\2\u00cf\u00d0\7\61\2\2\u00d0D\3\2\2\2\u00d1"+
		"\u00d2\7K\2\2\u00d2\u00d6\7h\2\2\u00d3\u00d4\7k\2\2\u00d4\u00d6\7h\2\2"+
		"\u00d5\u00d1\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d6F\3\2\2\2\u00d7\u00d8\7"+
		"V\2\2\u00d8\u00d9\7j\2\2\u00d9\u00da\7g\2\2\u00da\u00e0\7p\2\2\u00db\u00dc"+
		"\7v\2\2\u00dc\u00dd\7j\2\2\u00dd\u00de\7g\2\2\u00de\u00e0\7p\2\2\u00df"+
		"\u00d7\3\2\2\2\u00df\u00db\3\2\2\2\u00e0H\3\2\2\2\u00e1\u00e2\7G\2\2\u00e2"+
		"\u00e3\7n\2\2\u00e3\u00e4\7u\2\2\u00e4\u00ea\7g\2\2\u00e5\u00e6\7g\2\2"+
		"\u00e6\u00e7\7n\2\2\u00e7\u00e8\7u\2\2\u00e8\u00ea\7g\2\2\u00e9\u00e1"+
		"\3\2\2\2\u00e9\u00e5\3\2\2\2\u00eaJ\3\2\2\2\u00eb\u00ec\7G\2\2\u00ec\u00ed"+
		"\7p\2\2\u00ed\u00ee\7f\2\2\u00ee\u00ef\7K\2\2\u00ef\u00f6\7h\2\2\u00f0"+
		"\u00f1\7g\2\2\u00f1\u00f2\7p\2\2\u00f2\u00f3\7f\2\2\u00f3\u00f4\7k\2\2"+
		"\u00f4\u00f6\7h\2\2\u00f5\u00eb\3\2\2\2\u00f5\u00f0\3\2\2\2\u00f6L\3\2"+
		"\2\2\u00f7\u00f8\7h\2\2\u00f8\u00f9\7w\2\2\u00f9\u00fa\7p\2\2\u00fa\u00fb"+
		"\7e\2\2\u00fb\u00fc\7\60\2\2\u00fcN\3\2\2\2\u00fd\u00fe\7H\2\2\u00fe\u00ff"+
		"\7q\2\2\u00ff\u0104\7t\2\2\u0100\u0101\7h\2\2\u0101\u0102\7q\2\2\u0102"+
		"\u0104\7t\2\2\u0103\u00fd\3\2\2\2\u0103\u0100\3\2\2\2\u0104P\3\2\2\2\u0105"+
		"\u0106\7G\2\2\u0106\u0107\7p\2\2\u0107\u0108\7f\2\2\u0108\u0109\7H\2\2"+
		"\u0109\u010a\7q\2\2\u010a\u0112\7t\2\2\u010b\u010c\7g\2\2\u010c\u010d"+
		"\7p\2\2\u010d\u010e\7f\2\2\u010e\u010f\7h\2\2\u010f\u0110\7q\2\2\u0110"+
		"\u0112\7t\2\2\u0111\u0105\3\2\2\2\u0111\u010b\3\2\2\2\u0112R\3\2\2\2\u0113"+
		"\u0114\7V\2\2\u0114\u0118\7q\2\2\u0115\u0116\7v\2\2\u0116\u0118\7q\2\2"+
		"\u0117\u0113\3\2\2\2\u0117\u0115\3\2\2\2\u0118T\3\2\2\2\u0119\u011a\7"+
		"U\2\2\u011a\u011b\7v\2\2\u011b\u011c\7g\2\2\u011c\u0126\7r\2\2\u011d\u011e"+
		"\7U\2\2\u011e\u011f\7V\2\2\u011f\u0120\7G\2\2\u0120\u0126\7R\2\2\u0121"+
		"\u0122\7u\2\2\u0122\u0123\7v\2\2\u0123\u0124\7g\2\2\u0124\u0126\7r\2\2"+
		"\u0125\u0119\3\2\2\2\u0125\u011d\3\2\2\2\u0125\u0121\3\2\2\2\u0126V\3"+
		"\2\2\2\26\2beilqwy\u0083\u0089\u008b\u00c9\u00d5\u00df\u00e9\u00f5\u0103"+
		"\u0111\u0117\u0125\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}