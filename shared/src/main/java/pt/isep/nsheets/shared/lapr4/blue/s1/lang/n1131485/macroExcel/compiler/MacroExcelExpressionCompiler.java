package pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1131485.macroExcel.compiler;

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
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1131485.MacroExcelLexer;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1131485.MacroExcelParser;

/**
 *
 * @author PedroEmanuelCoelho <1131485@isep.ipp.pt>
 */
public class MacroExcelExpressionCompiler implements ExpressionCompiler {

    public static final char LINE_STARTER = '|';

    public final String compilerName = "MacroExcel";

    private MacroLanguage language = null;

    public MacroExcelExpressionCompiler() {

        this.language = (MacroLanguage) LanguageManager.getInstance().getLanguage("MacroExcel");
    }

    @Override
    public Expression compile(Cell cell, String source) throws FormulaCompilationException {

        // Create the lexer and parser
        ANTLRInputStream input = new ANTLRInputStream(source);

        // create the buffer of the tokens between the lexer and parser
        MacroExcelLexer lexer = new MacroExcelLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        MacroExcelParser parser = new MacroExcelParser(tokens);

        MacroExcelErrorListener excelErrorListener = new MacroExcelErrorListener();
        parser.removeErrorListeners();
        parser.addErrorListener(excelErrorListener);

        ParseTree tree = parser.macro();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            throw new FormulaCompilationException(excelErrorListener.getErrorMessage());
        }

        // Visit the expression and returns it
        MacroExcelEvalVisitor eval = new MacroExcelEvalVisitor(cell, language);
        Expression result = eval.visit(tree);
        if (eval.getNumberOfErrors() > 0) {
            throw new FormulaCompilationException(eval.getErrorsMessage());
        }
        return result;
    }

    @Override
    public char getStarter() {
        return LINE_STARTER;
    }

    @Override
    public String compilerName() {
        return compilerName;
    }

    //
    public static class MacroExcelErrorListener extends BaseErrorListener {

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
}
