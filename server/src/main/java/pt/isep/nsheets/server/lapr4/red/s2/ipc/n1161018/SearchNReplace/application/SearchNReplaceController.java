package pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161018.SearchNReplace.application;

import eapli.framework.application.Controller;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.Workbook;

/**
 * SearchNReplaceController.java
 *
 * @autor: David Blanquett<1161018@isep.ipp.pt>
 * @date: 06/06/2018
 */
public class SearchNReplaceController implements Controller {

    private Spreadsheet mySpreadsheet;
    private int nRows;
    private int nCols;


    public SearchNReplaceController(Spreadsheet mySpreadsheet) {

        this.mySpreadsheet = mySpreadsheet;
        nRows = mySpreadsheet.getRowCount();
        nCols = mySpreadsheet.getColumnCount();
    }

    public boolean showNext(int whatRow, int whatCol){

        if( whatCol >= nCols || whatRow >= nRows){
            return false;
        }


        return true;


    }

    public void replaceNext(int whatRow, int whatCol, String content){




    }


}
