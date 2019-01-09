// Generated from c:\Users\MarioDias\Documents\ISEP\2ºANO\2ºSemestre\LAPR4\lapr4-18-2dc\shared\src\main\antlr4\pt\isep\nsheets\shared\core\formula\compiler\Formula.g4 by ANTLR 4.7.1

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
public class FormulaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VAR=1, FUNCTION=2, CELL_REF=3, STRING=4, QUOT=5, NUMBER=6, EQ=7, NEQ=8, 
		LTEQ=9, GTEQ=10, GT=11, LT=12, AMP=13, PLUS=14, MINUS=15, MULTI=16, DIV=17, 
		POWER=18, PERCENT=19, COLON=20, ATTRIB=21, COMMA=22, SEMI=23, LPAR=24, 
		RPAR=25, UNDER=26, WS=27;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"VAR", "LETTER", "FUNCTION", "CELL_REF", "STRING", "QUOT", "NUMBER", "DIGIT", 
		"EQ", "NEQ", "LTEQ", "GTEQ", "GT", "LT", "AMP", "PLUS", "MINUS", "MULTI", 
		"DIV", "POWER", "PERCENT", "ABS", "EXCL", "COLON", "ATTRIB", "COMMA", 
		"SEMI", "LPAR", "RPAR", "UNDER", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, "'\"'", null, "'='", "'<>'", "'<='", "'>='", 
		"'>'", "'<'", "'&'", "'+'", "'-'", "'*'", "'/'", "'^'", "'%'", "':'", 
		"':='", "','", "';'", "'('", "')'", "'_'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "VAR", "FUNCTION", "CELL_REF", "STRING", "QUOT", "NUMBER", "EQ", 
		"NEQ", "LTEQ", "GTEQ", "GT", "LT", "AMP", "PLUS", "MINUS", "MULTI", "DIV", 
		"POWER", "PERCENT", "COLON", "ATTRIB", "COMMA", "SEMI", "LPAR", "RPAR", 
		"UNDER", "WS"
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


	public FormulaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Formula.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\35\u00b2\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \3\2\3\2\3\2\6\2E\n\2\r\2\16\2F\3\3\3\3\3\4\6\4L\n\4\r\4\16\4M\3\5\5"+
		"\5Q\n\5\3\5\3\5\5\5U\n\5\3\5\5\5X\n\5\3\5\6\5[\n\5\r\5\16\5\\\3\6\3\6"+
		"\3\6\3\6\7\6c\n\6\f\6\16\6f\13\6\3\6\3\6\3\7\3\7\3\b\6\bm\n\b\r\b\16\b"+
		"n\3\b\3\b\6\bs\n\b\r\b\16\bt\5\bw\n\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3"+
		"\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3"+
		"\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3"+
		"\31\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3"+
		" \3 \3 \3 \5 \u00af\n \3 \3 \2\2!\3\3\5\2\7\4\t\5\13\6\r\7\17\b\21\2\23"+
		"\t\25\n\27\13\31\f\33\r\35\16\37\17!\20#\21%\22\'\23)\24+\25-\2/\2\61"+
		"\26\63\27\65\30\67\319\32;\33=\34?\35\3\2\4\4\2C\\c|\3\2$$\2\u00ba\2\3"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)"+
		"\3\2\2\2\2+\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2"+
		"\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\3A\3\2\2\2\5H\3\2\2\2\7"+
		"K\3\2\2\2\tP\3\2\2\2\13^\3\2\2\2\ri\3\2\2\2\17l\3\2\2\2\21x\3\2\2\2\23"+
		"z\3\2\2\2\25|\3\2\2\2\27\177\3\2\2\2\31\u0082\3\2\2\2\33\u0085\3\2\2\2"+
		"\35\u0087\3\2\2\2\37\u0089\3\2\2\2!\u008b\3\2\2\2#\u008d\3\2\2\2%\u008f"+
		"\3\2\2\2\'\u0091\3\2\2\2)\u0093\3\2\2\2+\u0095\3\2\2\2-\u0097\3\2\2\2"+
		"/\u0099\3\2\2\2\61\u009b\3\2\2\2\63\u009d\3\2\2\2\65\u00a0\3\2\2\2\67"+
		"\u00a2\3\2\2\29\u00a4\3\2\2\2;\u00a6\3\2\2\2=\u00a8\3\2\2\2?\u00ae\3\2"+
		"\2\2AB\5=\37\2BD\5\5\3\2CE\3\2\2\2DC\3\2\2\2EF\3\2\2\2FD\3\2\2\2FG\3\2"+
		"\2\2G\4\3\2\2\2HI\t\2\2\2I\6\3\2\2\2JL\5\5\3\2KJ\3\2\2\2LM\3\2\2\2MK\3"+
		"\2\2\2MN\3\2\2\2N\b\3\2\2\2OQ\5-\27\2PO\3\2\2\2PQ\3\2\2\2QR\3\2\2\2RT"+
		"\5\5\3\2SU\5\5\3\2TS\3\2\2\2TU\3\2\2\2UW\3\2\2\2VX\5-\27\2WV\3\2\2\2W"+
		"X\3\2\2\2XZ\3\2\2\2Y[\5\21\t\2ZY\3\2\2\2[\\\3\2\2\2\\Z\3\2\2\2\\]\3\2"+
		"\2\2]\n\3\2\2\2^d\5\r\7\2_`\7^\2\2`c\7$\2\2ac\n\3\2\2b_\3\2\2\2ba\3\2"+
		"\2\2cf\3\2\2\2db\3\2\2\2de\3\2\2\2eg\3\2\2\2fd\3\2\2\2gh\5\r\7\2h\f\3"+
		"\2\2\2ij\7$\2\2j\16\3\2\2\2km\5\21\t\2lk\3\2\2\2mn\3\2\2\2nl\3\2\2\2n"+
		"o\3\2\2\2ov\3\2\2\2pr\5\65\33\2qs\5\21\t\2rq\3\2\2\2st\3\2\2\2tr\3\2\2"+
		"\2tu\3\2\2\2uw\3\2\2\2vp\3\2\2\2vw\3\2\2\2w\20\3\2\2\2xy\4\62;\2y\22\3"+
		"\2\2\2z{\7?\2\2{\24\3\2\2\2|}\7>\2\2}~\7@\2\2~\26\3\2\2\2\177\u0080\7"+
		">\2\2\u0080\u0081\7?\2\2\u0081\30\3\2\2\2\u0082\u0083\7@\2\2\u0083\u0084"+
		"\7?\2\2\u0084\32\3\2\2\2\u0085\u0086\7@\2\2\u0086\34\3\2\2\2\u0087\u0088"+
		"\7>\2\2\u0088\36\3\2\2\2\u0089\u008a\7(\2\2\u008a \3\2\2\2\u008b\u008c"+
		"\7-\2\2\u008c\"\3\2\2\2\u008d\u008e\7/\2\2\u008e$\3\2\2\2\u008f\u0090"+
		"\7,\2\2\u0090&\3\2\2\2\u0091\u0092\7\61\2\2\u0092(\3\2\2\2\u0093\u0094"+
		"\7`\2\2\u0094*\3\2\2\2\u0095\u0096\7\'\2\2\u0096,\3\2\2\2\u0097\u0098"+
		"\7&\2\2\u0098.\3\2\2\2\u0099\u009a\7#\2\2\u009a\60\3\2\2\2\u009b\u009c"+
		"\7<\2\2\u009c\62\3\2\2\2\u009d\u009e\7<\2\2\u009e\u009f\7?\2\2\u009f\64"+
		"\3\2\2\2\u00a0\u00a1\7.\2\2\u00a1\66\3\2\2\2\u00a2\u00a3\7=\2\2\u00a3"+
		"8\3\2\2\2\u00a4\u00a5\7*\2\2\u00a5:\3\2\2\2\u00a6\u00a7\7+\2\2\u00a7<"+
		"\3\2\2\2\u00a8\u00a9\7a\2\2\u00a9>\3\2\2\2\u00aa\u00af\7\"\2\2\u00ab\u00ac"+
		"\7\17\2\2\u00ac\u00af\7\f\2\2\u00ad\u00af\4\13\f\2\u00ae\u00aa\3\2\2\2"+
		"\u00ae\u00ab\3\2\2\2\u00ae\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1"+
		"\b \2\2\u00b1@\3\2\2\2\17\2FMPTW\\bdntv\u00ae\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}