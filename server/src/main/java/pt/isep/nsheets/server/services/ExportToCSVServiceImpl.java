/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160900.exportToCSV.application.ExportPartOfSpreadsheetController;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160900.exportToCSV.application.ExportSpreadSheetController;
import pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.application.ExportWorkbookController;
import pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.domain.ExportFormats;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.SpreadsheetDTO;
import pt.isep.nsheets.shared.services.ExportToCSVService;
import pt.isep.nsheets.shared.services.ExportToPDFService;

/**
 *
 * @author Ricardo
 */
public class ExportToCSVServiceImpl extends RemoteServiceServlet implements ExportToCSVService {

    @Override
    public boolean exportWorkbookToCSV(Workbook workbook, String fileName) {
        ExportWorkbookController controller = new ExportWorkbookController();
        try {
            return controller.exportWorkbook(ExportFormats.CSV, workbook, fileName);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WorkbooksServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean changeSeparator(String a) {
        ExportWorkbookController controller = new ExportWorkbookController();

        return controller.changeLineSeparator(a);

    }

//    @Override
//    public boolean exportSpreadSheetToCSV(SpreadsheetDTO s, String fileName) {
//        ExportSpreadSheetController controller = new ExportSpreadSheetController();
//        try {
//            return controller.exportSpreadSheet(ExportFormats.CSV, s, fileName);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(WorkbooksServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }

    @Override
    public boolean exportPartOfSpreadSheetToCSV(Spreadsheet s ,String fileName, int li, int lf, int ci, int cf) {
        ExportPartOfSpreadsheetController controller = new ExportPartOfSpreadsheetController();
        try {
            return controller.exportPartOfSpreadSheet(ExportFormats.CSV, fileName,s, li, lf, ci, cf);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WorkbooksServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean exportSpreadSheetToCSV(SpreadsheetDTO workbook, String fileName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
