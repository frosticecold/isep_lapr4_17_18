package pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.compiler;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.core.formula.compiler.*;
import pt.isep.nsheets.shared.core.formula.lang.Language;
import pt.isep.nsheets.shared.core.formula.lang.LanguageManager;
import pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.MonetaryExpressionLexer;
import pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.MonetaryExpressionParser;
import pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.compiler.MonetaryEvalVisitor;

import java.util.Collections;
import java.util.List;

public class MonetaryExpressionCompiler implements ExpressionCompiler{

    /**
     * Formula starter
     */
    public static final char FORMULA_STARTER = '#';

    /**
     * Compiler name
     */
    public static final String compilerName = "MonetaryFormula";

    /**
     * Language
     */
    private Language language = null;

    /**
     * Constructor
     */
    public MonetaryExpressionCompiler()
    {
        language = LanguageManager.getInstance().getLanguage("monetary");
    }

    /**
     * Compiles a given cell
     *
     * @param cell the cell for which the expression is to be compiled
     * @param source a string representing the expression to be compiled
     * @return expression
     * @throws FormulaCompilationException
     */
    @Override
    public Expression compile(Cell cell, String source) throws FormulaCompilationException {
        // Creates the lexer and parser
        ANTLRInputStream input = new ANTLRInputStream(source);

        // create the buffer of tokens between the lexer and parser
        MonetaryExpressionLexer lexer = new MonetaryExpressionLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        MonetaryExpressionParser parser = new MonetaryExpressionParser(tokens);

        MonetaryExpressionErrorListener formulaErrorListener =
                new MonetaryExpressionErrorListener();
        parser.removeErrorListeners(); // remove default ConsoleErrorListener
        parser.addErrorListener(formulaErrorListener); // add ours

        // Attempts to match an expression
        ParseTree tree = parser.expression();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            throw new FormulaCompilationException(formulaErrorListener.getErrorMessage());
        }

        // Visit the expression and returns it
        MonetaryEvalVisitor eval = new MonetaryEvalVisitor(cell, language);
        Expression result = eval.visit(tree);
        if (eval.getNumberOfErrors() > 0) {
            throw new FormulaCompilationException(eval.getErrorsMessage());
        }

        return result;
    }

    /**
     * Devolves starter
     *
     * @return starter
     */
    @Override
    public char getStarter() {
        return FORMULA_STARTER;
    }

    /**
     * Devolves compiler name
     *
     * @return compiler name
     */
    @Override
    public String compilerName() {
        return compilerName;
    }

    /**
     * Monetary expression error listener
     */
    public static class MonetaryExpressionErrorListener extends BaseErrorListener {

        /**
         * Error buffer
         */
        private StringBuilder buf;

        /**
         * Devolves error message
         *
         * @return error message
         */
        public String getErrorMessage() {
            return buf.toString();
        }

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                                Object offendingSymbol,
                                int line, int charPositionInLine,
                                String msg,
                                RecognitionException e) {
            List<String> stack = ((Parser) recognizer).getRuleInvocationStack();
            Collections.reverse(stack);

            buf = new StringBuilder();
            buf.append("line ").append(line).append(":").append(charPositionInLine).append(": ").append(msg);
        }

    }
}
