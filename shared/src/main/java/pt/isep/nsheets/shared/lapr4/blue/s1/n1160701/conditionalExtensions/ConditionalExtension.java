/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.lapr4.blue.s1.n1160701.conditionalExtensions;

import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaCompilationException;
import pt.isep.nsheets.shared.ext.Extension;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 * Changed class
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class ConditionalExtension extends Extension {

    /**
     * The name of the extension
     */
    public static final String NAME = "Condtional Formating";
    private final String EXTENSION_NAME ="Conditional Formating";
    /**
     * Creates a new Red extension.
     */
    public ConditionalExtension() {
        super(NAME);
        setExtensionType(EXTENSION_NAME);
    }

    /**
     * Makes the given spreadsheet Red.
     *
     * @param spreadsheet the spreadsheet to extend
     * @return a Red Typed spreadsheet
     */
    public ConditionalSpreadSheet extend(Spreadsheet spreadsheet) {

        return new ConditionalSpreadSheet(spreadsheet);
    }

    /**
     * Makes the given cell Red.
     *
     * @param cell the cell to extend
     * @return a Red typed cell
     */
    public ConditionalCell extend(Cell cell) {
        try {
            cell.setContent("Condtional Formating");
        } catch (FormulaCompilationException e) {
            e.printStackTrace();
        }
        return new ConditionalCell(cell);
    }
}
