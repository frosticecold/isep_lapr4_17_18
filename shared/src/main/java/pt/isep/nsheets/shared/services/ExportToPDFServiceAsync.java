/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.WorkbookDTO;

/**
 *
 * @author Telmo
 */
public interface ExportToPDFServiceAsync {

    void exportWorkbookToPDF(Workbook workbook, String fileName, AsyncCallback<Boolean> callback);

    void exportSpreadsheetToPDF(WorkbookDTO spreadsheet, int n, String fileName, AsyncCallback<Boolean> callback);
    
    void exportPartOfSpreadsheet(WorkbookDTO spreadsheet, int n, String fileName,String p1, String p2,  AsyncCallback<Boolean> callback);

    void exportWorkbookToPDF(WorkbookDTO wbdto, String filename, AsyncCallback<Boolean> callback);

}
