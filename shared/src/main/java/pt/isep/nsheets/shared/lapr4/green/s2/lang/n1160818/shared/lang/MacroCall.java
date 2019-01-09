package pt.isep.nsheets.shared.lapr4.green.s2.lang.n1160818.shared.lang;

import org.antlr.v4.runtime.tree.TerminalNode;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.core.formula.compiler.ExpressionCompiler;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaCompilationException;
import pt.isep.nsheets.shared.core.formula.util.ExpressionVisitor;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.macrolanguage.compiler.MacroCompilerManager;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1131485.macro.domain.Macro;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a macro call expression and evalutes the expression
 *
 * @author Rui Almeida<1160818>
 */
public class MacroCall implements Expression {

    private TerminalNode name;
    private ArrayList<String> parameters;
    private Cell cell;

    public MacroCall(TerminalNode name, Cell cell) {

        this.name = name;
        this.cell = cell;
        this.parameters = new ArrayList<>();
    }

    public MacroCall(TerminalNode name, ArrayList<String> parameters, Cell cell) {

        this.name = name;
        this.cell = cell;
        this.parameters = parameters;
    }

    protected MacroCall() {
    }

    @Override
    public Value evaluate() throws IllegalValueTypeException {
        Workbook wb = cell.getSpreadsheet().getWorkbook();

        String macroName = name.toString();

        for (Macro m : wb.macros()) {

            String savedMacroName = "\"" + m.name() + "\"";

            if (savedMacroName.equalsIgnoreCase(macroName)) {
                int i = 0;
                if((m.commands().contains("_a") || m.commands().contains("_b"))  && !parameters.isEmpty()){
                    if(m.commands().contains("_a") && m.commands().contains("_b")){
                        if(m.commands().indexOf("_a") > m.commands().indexOf("_b")){
                            m.addCommand(m.commands().replace("_b", parameters.get(0)));
                            m.addCommand(m.commands().replace("_a", parameters.get(1)));
                        } else {
                            m.addCommand(m.commands().replace("_a", parameters.get(1)));
                            m.addCommand(m.commands().replace("_b", parameters.get(0)));
                        }
                    } else if(m.commands().contains("_a")) {
                        m.addCommand(m.commands().replace("_a", parameters.get(i)));
                        i++;
                    } else if(m.commands().contains("_b")) {
                        m.addCommand(m.commands().replace("_b", parameters.get(i)));
                        i++;
                    }

                }

                try {
                    ExpressionCompiler compiler = MacroCompilerManager.getInstance().getCompiler("VisualBasic");
                    Expression expression = compiler.compile(cell, m.commands());
                    return expression.evaluate();
                } catch(FormulaCompilationException ex) {
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
