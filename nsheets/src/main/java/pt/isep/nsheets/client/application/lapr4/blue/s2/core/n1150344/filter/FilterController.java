package pt.isep.nsheets.client.application.lapr4.blue.s2.core.n1150344.filter;

import java.util.HashSet;
import pt.isep.nsheets.shared.core.Address;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.CellImpl;
import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaCompilationException;

/**
 *
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */
public class FilterController {

    public FilterController() {
    }

    /**
     * Method that returns if the inserted column is valid based on the range
     * provided
     *
     * @param startCellAddress The start cell address
     * @param endCellAddress The end cell address
     * @param column The column
     * @return true if is in the range, false if not
     */
    public boolean isValidColumn(String startCellAddress, String endCellAddress, String column) {
        Address startCell = correspondingAddress(startCellAddress);
        Address endCell = correspondingAddress(endCellAddress);

        Address firstCell = correspondingAddress(column + '0');

        int minColumn = startCell.getColumn();

        int maxColumn = endCell.getColumn();

        int columnIndex = firstCell.getColumn();

        return columnIndex >= minColumn && columnIndex <= maxColumn;
    }

    /**
     * Filter the cells in the column selected that are in the range provided in
     * the active spreadsheet with a given formula to apply
     *
     * @param startCellAddress The start cell address
     * @param endCellAddress The end cell address
     * @param column The column
     * @param spreadsheet The active spreadsheet
     * @param formula The formula to filter
     * @param rowsToHide The list of rows that are gonna be hidden
     * @throws FormulaCompilationException If the formula is invalid
     * @throws IllegalValueTypeException If the value in the formula is invalid
     */
    public void filter(String startCellAddress, String endCellAddress, String column, Spreadsheet spreadsheet, String formula, HashSet<Integer> rowsToHide) throws FormulaCompilationException, IllegalValueTypeException {

        Address startCell = correspondingAddress(startCellAddress);
        Address endCell = correspondingAddress(endCellAddress);

        Address firstCell = correspondingAddress(column + '1');

        int minRow = startCell.getRow();
        int maxRow = endCell.getRow();

        int columnIndex = firstCell.getColumn();

        for (int i = minRow; i <= maxRow; i++) {
            if (!filterCell(spreadsheet.getCell(columnIndex, i), formula)) {
                rowsToHide.add(i);
            }
        }
    }

    /**
     * Verifies if a cell is valid in the given formula
     *
     * @param cell The cell to apply the formula on
     * @param formula The formula
     * @return true if the result is true, false if false
     * @throws FormulaCompilationException If the formula is invalid
     * @throws IllegalValueTypeException If the value in the formula is invalid
     */
    private boolean filterCell(Cell cell, String formula) throws FormulaCompilationException, IllegalValueTypeException {
        Cell cellToCopy = new CellImpl();

        String formulaFinal = formula;

        if (formula.contains("_filter")) {
            String replace = formulaFinal.replace("_filter", cell.getContent());
            cellToCopy.setContent(replace);
        }

        return cellToCopy.getValue().toBoolean();
    }

    /**
     * Gives the corresponding address based on the cell name
     *
     * @param cellName The cell name (ex. A1)
     * @return the corresponding address (Address 0 0)
     */
    private Address correspondingAddress(String cellName) {
        int row;
        int col;

        col = cellName.charAt(0) - 'A';

        row = Integer.valueOf(cellName.substring(1)) - 1;

        return new Address(col, row);
    }

}
