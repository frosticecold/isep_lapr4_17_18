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
 * @author Rafael Teixeira <1160911@isep.ipp.pt>
 */
public interface ExportToXMLServiceAsync {

    void exportWorkbookToXML(Workbook workbook, String fileName, AsyncCallback<Boolean> callback);
    
    void exportSpreadSheetToXML(SpreadsheetDTO s, String fileName, AsyncCallback<Boolean> callback);
    
    void exportPartOfSpreadSheetToXML( Spreadsheet s,String fileName, int il, int fl, int ic, int fc, AsyncCallback<Boolean> callback);

    void changetag1(String a, AsyncCallback<Boolean> callback);
    
    void changetag2(String a, AsyncCallback<Boolean> callback);

    void changetag3(String a, AsyncCallback<Boolean> callback);

    void changetag4(String a, AsyncCallback<Boolean> callback);

    void changetag5(String a, AsyncCallback<Boolean> callback);

    void changetag6(String a, AsyncCallback<Boolean> callback);
}
