package pt.isep.nsheets.shared.lapr4.red.s1.ext.n1161018.YellowExtension;

import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaCompilationException;
import pt.isep.nsheets.shared.ext.Extension;

/**
 * The extension for Yellow typed elements
 *
 * @author David Blanquett<1161018@isep.ipp.pt>
 */
public class YellowExtension extends Extension {

    /** The name of the extension */
    public static final String NAME = "YELLOW";
    private final String EXTENSION_NAME ="Cell Background";
    /**
     * Creates a new Yellow extension.
     */
    public YellowExtension() {
        super(NAME);
        setExtensionType(EXTENSION_NAME);
    }


    /**
     * Makes the given spreadsheet Yellow.
     *
     * @param spreadsheet the spreadsheet to extend
     * @return a Yellow Typed spreadsheet
     */
    public YellowSpreadSheet extend(Spreadsheet spreadsheet){

        return new YellowSpreadSheet(spreadsheet);
    }


    /**
     * Makes the given cell Yellow.
     *
     * @param cell the cell to extend
     * @return a Yellow typed cell
     */
    public YellowCell extend(Cell cell){
        try {
            cell.setContent("SOU AMARELA CRL");
        } catch (FormulaCompilationException e) {
            e.printStackTrace();
        }
        return new YellowCell(cell);
    }
}

