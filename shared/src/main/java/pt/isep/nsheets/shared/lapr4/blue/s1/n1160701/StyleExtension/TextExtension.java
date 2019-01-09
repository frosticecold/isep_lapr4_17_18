package pt.isep.nsheets.shared.lapr4.blue.s1.n1160701.StyleExtension;

import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaCompilationException;
import pt.isep.nsheets.shared.ext.Extension;


public class TextExtension extends Extension {

    /** The name of the extension */
    public static final String NAME = "BLUE";

      private final String EXTENSION_NAME ="Text Extension";
    /**
     * Creates a new extension.
     */
    public TextExtension() {
        super(NAME);
        setExtensionType(EXTENSION_NAME);
    }


    public TextSpreadSheet extend(Spreadsheet spreadsheet){

        return new TextSpreadSheet(spreadsheet);
    }


    /**
     * change the color of text of the cell.
     *
     * @param cell the cell to extend
     * @return a textcell
     */
    public TextCell extend(Cell cell){
        try {
            cell.setContent("BLUE");
        } catch (FormulaCompilationException e) {
            e.printStackTrace();
        }
        return new TextCell(cell);
    }
}

