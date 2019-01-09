/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.core.formula;

import org.antlr.v4.runtime.tree.TerminalNode;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.core.formula.compiler.ExpressionCompiler;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaCompilationException;
import pt.isep.nsheets.shared.core.formula.util.ExpressionVisitor;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.macrolanguage.compiler.MacroCompilerManager;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1131485.macro.domain.Macro;

/**
 *
 * @author utilizador
 */
public class MacroCallExcel implements Expression {

    private TerminalNode name;
    private Cell cell;

    public MacroCallExcel(TerminalNode name, Cell cell) {
        this.name = name;
        this.cell = cell;
    }

    protected MacroCallExcel() {
    }

    @Override
    public Value evaluate() throws IllegalValueTypeException {
        Workbook wb = cell.getSpreadsheet().getWorkbook();

        String macroName = name.toString();

        for (Macro m : wb.macros()) {

            String savedMacroName = "\"" + m.name() + "\"";

            if (savedMacroName.equalsIgnoreCase(macroName)) {

                try {
                    ExpressionCompiler compiler = MacroCompilerManager.getInstance().getCompiler("MacroExcel");
                    Expression expression = compiler.compile(cell, m.commands());
                    return expression.evaluate();
                } catch (FormulaCompilationException ex) {
                    return null;
                }

            }
        }

        return null;
    }

    @Override
    public Object accept(ExpressionVisitor visitor) {
        return null;
    }
}
