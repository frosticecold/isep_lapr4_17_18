package pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.visualbasic.compiler;

import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;

import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.core.formula.compiler.ExpressionCompiler;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaCompilationException;
import pt.isep.nsheets.shared.core.formula.lang.LanguageManager;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.VisualBasicLexer;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.VisualBasicParser;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.shared.lang.MacroLanguage;

public class VisualBasicExpressionCompiler implements ExpressionCompiler {
	/**
	 * The character that signals that a cell's content is a formula ('=')
	 */
	public static final char FORMULA_STARTER = '=';

	public static final String compilerName ="VisualBasic";	
	private MacroLanguage language = null;

	public VisualBasicExpressionCompiler() {
		language = (MacroLanguage) LanguageManager.getInstance().getLanguage("visualbasic");
	}

	@Override
	public char getStarter() {
		return FORMULA_STARTER;
	}

	@Override
	public Expression compile(Cell cell, String source) throws FormulaCompilationException {

		// Create the lexer and parser
		ANTLRInputStream input = new ANTLRInputStream(source);

		// create the buffer of the tokens between the lexer and parser

		VisualBasicLexer lexer = new VisualBasicLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		VisualBasicParser parser = new VisualBasicParser(tokens);

		VisualBasicErrorListener vbErrorListener = new VisualBasicErrorListener();
		parser.removeErrorListeners();
		parser.addErrorListener(vbErrorListener);

		ParseTree tree = parser.macro();
		if (parser.getNumberOfSyntaxErrors() > 0) {
			throw new FormulaCompilationException(vbErrorListener.getErrorMessage());
		}

		// Visit the expression and returns it
		VisualBasicEvalVisitor eval = new VisualBasicEvalVisitor(cell, language);
		Expression result = eval.visit(tree);
		if (eval.getNumberOfErrors() > 0) {
			throw new FormulaCompilationException(eval.getErrorsMessage());
		}
		return result;	
	}

	public static class VisualBasicErrorListener extends BaseErrorListener {

		private StringBuilder buf;

		public String getErrorMessage() {
			return buf.toString();
		}

		@Override
		public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
				String msg, RecognitionException e) {
			List<String> stack = ((Parser) recognizer).getRuleInvocationStack();
			Collections.reverse(stack);

			buf = new StringBuilder();
			buf.append("line ").append(line).append(":").append(charPositionInLine).append(": ").append(msg);
		}
	}

	@Override
	public String compilerName() {
		return compilerName;
	}
}
