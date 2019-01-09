package pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1131485.dto;

import pt.isep.nsheets.shared.core.Workbook;

import java.io.Serializable;

/**
 * DTO intended to save information a lock (cell) on a spreadsheet to help about mutual exclusion
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */

public class lockDTO implements Serializable {

    private static final int TIMER_VALUE = 10; //CONSTANT

    /**
     * Time that the thread will wait until releasing the lock, in seconds
     */
    private int timer;

    /**
     * Workbook
     */
    private Workbook wb;

    /**
     * title of the current Spreadsheet of the workbook
     */
    private String ssTitle;

    /**
     * Row of the current Cell
     */
    private int cellRow;

    /**
     * Column of the current cell
     */
    private int cellColumn;

    /**
     * empty constructor mandatory in the usage of DTO
     */
    public lockDTO() {

    }

    /**
     * Full constructor
     */
    public lockDTO(Workbook wb, String title, int row, int column) {
        this.timer = TIMER_VALUE;
        this.cellRow = row;
        this.cellColumn = column;
        this.wb = wb;
        this.ssTitle = title;
    }

    /**
     * Returns the workbook Name
     */
    public Workbook workbook() {

        return this.wb;
    }

    /**
     * returns the index of the current spreadsheet
     */
    public String spreadTitile() {

        return this.ssTitle;
    }

    /**
     * Returns timer
     */
    public int timer() {

        return this.timer;
    }

    /**
     * Returns the row of the current cell
     */
    public int cellRow() {

        return this.cellRow;
    }

    /**
     * Returns the column of the current cell
     */
    public int cellColumn() {

        return this.cellColumn;
    }

    @Override
    public boolean equals(Object other) {

        boolean flag = false;

        if(other instanceof lockDTO) {
            lockDTO lo = (lockDTO) other;
            if(this.ssTitle.compareTo(((lockDTO) other).spreadTitile()) == 0) {
                if (this.cellColumn == lo.cellColumn() && this.cellRow == lo.cellRow()) {
                    flag = true;
                }
            }
        }

        return flag;
    }
}
