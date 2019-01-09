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
import pt.isep.nsheets.shared.services.ExportToPDFService;
import pt.isep.nsheets.shared.services.ExportToXMLService;

/**
 *
 * @author Rafael Teixeira
 */
public class ExportToXMLServiceImpl extends RemoteServiceServlet implements ExportToXMLService {

    @Override
    public boolean exportWorkbookToXML(Workbook workbook, String fileName) {
        ExportWorkbookController controller = new ExportWorkbookController();
        try {
            return controller.exportWorkbook(ExportFormats.XML, workbook, fileName);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WorkbooksServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

//    @Override
//    public boolean exportSpreadSheetToXML(SpreadsheetDTO spreadsheet, String fileName) {
//        ExportSpreadSheetController controller = new ExportSpreadSheetController();
//        try {
//            return controller.exportSpreadSheet(ExportFormats.XML, spreadsheet, fileName);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(WorkbooksServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }

    @Override
    public boolean exportPartOfSpreadSheetToXML(Spreadsheet sheet, String fileName, int il, int fl, int ic, int fc) {
        ExportPartOfSpreadsheetController controller = new ExportPartOfSpreadsheetController();
        try {
            return controller.exportPartOfSpreadSheet(ExportFormats.XML, fileName, sheet, il, fl, ic, fc);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WorkbooksServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean changetag1(String a) {
        ExportWorkbookController controller = new ExportWorkbookController();

        return controller.changetag1(a);
    }

    @Override
    public boolean changetag2(String a) {
        ExportWorkbookController controller = new ExportWorkbookController();

        return controller.changetag2(a);
    }

    @Override
    public boolean changetag3(String a) {
        ExportWorkbookController controller = new ExportWorkbookController();

        return controller.changetag3(a);
    }

    @Override
    public boolean changetag4(String a) {
        ExportWorkbookController controller = new ExportWorkbookController();

        return controller.changetag4(a);
    }

    @Override
    public boolean changetag5(String a) {
        ExportWorkbookController controller = new ExportWorkbookController();

        return controller.changetag5(a);
    }

    @Override
    public boolean changetag6(String a) {
        ExportWorkbookController controller = new ExportWorkbookController();

        return controller.changetag6(a);
    }

    @Override
    public boolean exportSpreadSheetToXML(SpreadsheetDTO spreadsheet, String fileName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
