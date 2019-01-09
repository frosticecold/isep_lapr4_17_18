/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.SpreadsheetDTO;

/**
 *
 * @author Ricardo
 */
public interface ExportToCSVServiceAsync {

    void exportWorkbookToCSV(Workbook workbook, String fileName, AsyncCallback<Boolean> callback);

    void exportSpreadSheetToCSV(SpreadsheetDTO s, String fileName, AsyncCallback<Boolean> callback);

    void exportPartOfSpreadSheetToCSV( Spreadsheet s,String fileName, int li, int lf, int ci, int cf, AsyncCallback<Boolean> callback);

    void changeSeparator(String a, AsyncCallback<Boolean> callback);
}
