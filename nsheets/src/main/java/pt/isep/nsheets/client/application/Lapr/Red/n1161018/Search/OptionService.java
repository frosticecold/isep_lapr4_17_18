package pt.isep.nsheets.client.application.Lapr.Red.n1161018.Search;

import com.google.gwt.user.client.Window;
import pt.isep.nsheets.client.application.workbook.WorkbookView;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.Workbook;

import java.util.List;

/**
 * OptionService.java
 *
 * SINGLETON
 *
 * Class used to manage filters, and stop recursion of next method @WorkBookView.java
 *
 * @autor: David Blanquett<1161018@isep.ipp.pt>
 * @date: 06/06/2018
 */
public class OptionService {


    /**
     * Is a Singleton.
     */
    private static OptionService instance;

    /* current row and column */
    int nRow;
    int nCol;


    /**
     * Used as flag to SearchNReplace yesToAll option.
     */
    boolean yesToAll;

    /**
     * Used flag to Search and SearchNReplace filter.
     */
    Value.Type selectedType;

    /**
     * The current spreadSheet in search
     */
    Spreadsheet mySS;


    /**
     * The selected workbook
     */
    private Workbook workbook;

    /**
     * Sheets from this.workbook;
     */
    private List<Spreadsheet> sSheets;

    /**
     * sheet thats being processed.
     */
    int sSheets_index;


    /**
     * Private constructor, since its a singleton. Starts the default values
     */
    private OptionService(){

        nRow =0;
        nCol =0;
        yesToAll = true;
        selectedType = Value.Type.UNDEFINED;
    }

    /**
     * Used to get current column.
     *
     * @return the current searched col.
     */
    public int getRealCol(){
        return nCol;
    }

    /**
     * Returns the singleton instance.
     *
     * @return the singleton instance
     */
    public static OptionService getInstance() {

        if (instance == null) {

            instance = new OptionService();
        }

        return instance;
    }

    /**
     * Method that returns the row.
     *
     * @return the current row
     */
    public int getnRow() {
        return nRow;
    }

    /**
     * Changes current row.
     *
     * @param nRow: row to be changed
     */
    public void setnRow(int nRow) {
        this.nRow = nRow;
    }


    /**
     * MAIN METHOD:
     * Method used to coordinate the search.
     * Its responsible for changing {row,col,currentSpreadSheet} values.
     *
     * @return next collumn to be searched.
     */
    public int getnCol() {

        int ss_col = mySS.getColumnCount();
        int ss_row = mySS.getRowCount();

        nCol++;

        if( nCol >= ss_col){
            nRow++;

            if( nRow >= ss_row) {

                Spreadsheet s = nextSpreadSheet();

                if( s == null){ // END OF NEXT
                    instance = new OptionService();
                    return -1;
                }

                mySS = s;

                nRow=0;

            }
            nCol =0;





        }




        return nCol;
    }

    public void setnCol(int nCol) {
        this.nCol = nCol;
    }

    public Spreadsheet getMySS() {
        return mySS;
    }

    public void setMySS(Spreadsheet mySS) {
        this.mySS = mySS;
    }

    public boolean isYesToAll() {
        return yesToAll;
    }

    public void setYesToAll(boolean yesToAll) {
        this.yesToAll = yesToAll;
    }

    public void changeMyFilter(Value.Type selectedType) {
        this.selectedType = selectedType;
    }

    public Value.Type currentSelectedType() {
        return selectedType;
    }

    public void setMyWb(Workbook workbook) {
        this.sSheets = workbook.getSpreadSheets();
        sSheets_index =0;
        this.workbook = workbook;
    }

    public Spreadsheet nextSpreadSheet(){

        if( sSheets.get(sSheets_index + 1) == null){

            return null;
        }else{

            sSheets_index++;
            return sSheets.get(sSheets_index);
        }
    }

    public int getSheetIndex(){

        return sSheets_index+1;
    }
}
