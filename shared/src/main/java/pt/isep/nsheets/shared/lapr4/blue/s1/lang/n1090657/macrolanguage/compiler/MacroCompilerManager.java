package pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.macrolanguage.compiler;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import pt.isep.nsheets.shared.core.formula.compiler.ExpressionCompiler;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.shared.lang.MacroLanguage;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.visualbasic.compiler.VisualBasicExpressionCompiler;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1131485.macroExcel.compiler.MacroExcelExpressionCompiler;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1151708.javascript.compiler.JavascriptExpressionCompiler;

public class MacroCompilerManager {

    private static final MacroCompilerManager instance = new MacroCompilerManager();

    private List<ExpressionCompiler> compilers = new ArrayList<>();

    private MacroCompilerManager() {
        initCompilers();
    }

    private void initCompilers() {
        compilers.add(new VisualBasicExpressionCompiler());
        compilers.add(new JavascriptExpressionCompiler());
        compilers.add(new MacroExcelExpressionCompiler());
    }

    public static MacroCompilerManager getInstance() {
        return instance;
    }

    public ExpressionCompiler getCompiler(final String language) {
        for (ExpressionCompiler c : compilers) {
            if (c.compilerName().equalsIgnoreCase(language)) {
                return c;
            }
        }
        throw new NoSuchElementException(language + " doesn't exist.");
    }

    public ExpressionCompiler getCompiler(final MacroLanguage lang) {
        return getCompiler(lang.getName());
    }

}
