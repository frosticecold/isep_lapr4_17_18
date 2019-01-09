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
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.WorkbookDTO;
import pt.isep.nsheets.shared.services.ExportToPDFService;

/**
 *
 * @author Telmo
 */
public class ExportToPDFServiceImpl extends RemoteServiceServlet implements ExportToPDFService {

    @Override
    public boolean exportWorkbookToPDF(Workbook workbook, String fileName) {
        ExportWorkbookController controller = new ExportWorkbookController();
        try {
            return controller.exportWorkbook(ExportFormats.PDF, workbook, fileName);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WorkbooksServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean exportSpreadsheetToPDF(WorkbookDTO spreadsheet, int n, String fileName) {
        ExportWorkbookController controller = new ExportWorkbookController();
        Workbook wb = new Workbook(spreadsheet);
        try {
            return controller.exportSpreadsheet(ExportFormats.PDF, wb, n, fileName);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WorkbooksServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean exportPartOfSpreadsheet(WorkbookDTO spreadsheet, int n, String fileName, String p1 , String p2) {
        ExportWorkbookController controller = new ExportWorkbookController();
        Workbook wb = new Workbook(spreadsheet);
        try {
            return controller.exportPartOfSpreadSheet(ExportFormats.PDF, fileName,n, wb, p1,p2);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WorkbooksServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean exportWorkbookToPDF(WorkbookDTO wbDTO, String fileName) {
        ExportWorkbookController controller = new ExportWorkbookController();
        Workbook wb = new Workbook(wbDTO);
        try {
            return controller.exportWorkbook(ExportFormats.PDF, wb, fileName);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WorkbooksServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
