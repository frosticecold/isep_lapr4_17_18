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
 * @author Rafael <1160911@isep.ipp.pt>
 */
@RemoteServiceRelativePath("exportToXMLService")
public interface ExportToXMLService extends RemoteService {

    boolean exportWorkbookToXML(Workbook workbook, String fileName);

    boolean exportSpreadSheetToXML(SpreadsheetDTO spreadsheet, String fileName);

    boolean changetag1(String a);

    boolean changetag2(String a);

    boolean changetag3(String a);

    boolean changetag4(String a);

    boolean changetag5(String a);

    boolean changetag6(String a);

    boolean exportPartOfSpreadSheetToXML(Spreadsheet sheet, String fileName, int il, int fl, int ic, int fc);
}
