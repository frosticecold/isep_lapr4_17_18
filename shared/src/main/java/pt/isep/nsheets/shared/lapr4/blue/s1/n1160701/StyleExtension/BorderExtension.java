package pt.isep.nsheets.shared.lapr4.blue.s1.n1160701.StyleExtension;

import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaCompilationException;
import pt.isep.nsheets.shared.ext.Extension;


public class BorderExtension extends Extension {

    /** The name of the extension */
    public static final String NAME = "SOLID";
    /** ALL OPTIONS : HIDDEN ; SOLID ; DASHED ; DOTTED ; NONE*/
    private final String EXTENSION_NAME ="Border Extension";
    
    
    /**
     * Creates a new extension.
     */
    public BorderExtension() {
        super(NAME);
        setExtensionType(EXTENSION_NAME);
    }


    public BorderSpreadSheet extend(Spreadsheet spreadsheet){

        return new BorderSpreadSheet(spreadsheet);
    }


    /**
     * change the border of text of the cell.
     *
     * @param cell the cell to extend
     * @return a bordercell
     */
    public BorderCell extend(Cell cell){
        try {
            cell.setContent("SOLID");
        } catch (FormulaCompilationException e) {
            e.printStackTrace();
        }
        return new BorderCell(cell);
    }
}

