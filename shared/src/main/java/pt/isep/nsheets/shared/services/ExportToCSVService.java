/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.SpreadsheetDTO;

/**
 *
 * @author Ricardo
 */
@RemoteServiceRelativePath("exportToCSVService")
public interface ExportToCSVService extends RemoteService {

    boolean exportWorkbookToCSV(Workbook workbook, String fileName);

    boolean exportSpreadSheetToCSV(SpreadsheetDTO workbook, String fileName);

    boolean exportPartOfSpreadSheetToCSV( Spreadsheet s, String fileName , int il, int fl, int ic, int fc);

    boolean changeSeparator(String a);
}
