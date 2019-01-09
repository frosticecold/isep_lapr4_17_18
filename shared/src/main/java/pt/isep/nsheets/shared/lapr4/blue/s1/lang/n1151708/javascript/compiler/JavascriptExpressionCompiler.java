/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1151708.javascript.compiler;

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
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.shared.lang.MacroLanguage;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1151708.formula.lang.JavascriptLanguage;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1151708.javascriptLexer;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1151708.javascriptParser;

/**
 *
 * @author MarioDias
 */
public class JavascriptExpressionCompiler implements ExpressionCompiler {

    /**
     * The character that signals that a cell's content is a formula ('=')
     */
    public static final char FORMULA_STARTER = '=';

    public static final String compilerName = "javas";
    private MacroLanguage language = null;

    public JavascriptExpressionCompiler() {
        language = (MacroLanguage) LanguageManager.getInstance().getLanguage("javas");
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
        javascriptLexer lexer = new javascriptLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        javascriptParser parser = new javascriptParser(tokens);

        JavascriptErrorListener javasError = new JavascriptErrorListener();
        parser.removeErrorListeners();
        parser.addErrorListener(javasError);

        ParseTree tree = parser.macro();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            throw new FormulaCompilationException(javasError.getErrorMessage());
        }

        // Visit the expression and returns it
        JavascriptEvalVisitor eval = new JavascriptEvalVisitor(cell, new JavascriptLanguage("javas",""));
        Expression result = eval.visit(tree);
        if (eval.getNumberOfErrors() > 0) {
            throw new FormulaCompilationException(eval.getErrorsMessage());
        }
        return result;
    }

    public static class JavascriptErrorListener extends BaseErrorListener {

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
