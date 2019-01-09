/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.workbook;

import com.gwtplatform.mvp.client.UiHandlers;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.Workbook;

/**
 *
 * @author utilizador
 */
public interface WorkbookUIHandlers extends UiHandlers {
   

    void exportWorkbookToXML(Workbook wb , String fileName);
    

      void exportSpreadsheetToXML(Spreadsheet s , String fileName);
      
      void exportPartOfSpreadsheetToXML(Spreadsheet wb , String fileName, int il, int fl, int ic , int fc);


}
