/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.application;

import gwt.material.design.client.ui.MaterialToast;
import java.io.FileNotFoundException;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160900.exportToCSV.domain.ExportToCSV;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160911.exportToXML.domain.ExportToXML;
import pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.domain.ExportFactory;
import pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.domain.ExportFormats;
import pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.domain.ExportStrategy;
import pt.isep.nsheets.shared.core.Workbook;

/**
 *
 * @author Telmo
 */
public class ExportWorkbookController {

    public ExportWorkbookController() {

    }

    public boolean exportWorkbook(ExportFormats format, Workbook workbook, String fileName) throws FileNotFoundException {
        ExportStrategy export = ExportFactory.instance().exportStrategy(format);
        if (export.exportWorkbook(workbook, fileName)) {
            return true;
        } else {
            return false;
        }
    }

    
    public boolean exportSpreadsheet(ExportFormats format, Workbook workbook, int n,String fileName) throws FileNotFoundException {
        ExportStrategy export = ExportFactory.instance().exportStrategy(format);
        if (export.exportSpreadSheet(workbook,n, fileName)) {
            return true;
        } else {
            return false;
        }
    }
    
        public boolean exportPartOfSpreadSheet(ExportFormats format, String fileName, int n, Workbook s, String p1, String p2) throws FileNotFoundException {
        ExportStrategy export = ExportFactory.instance().exportStrategy(format);
        if (export.exportPartOfSpreadsheet(s, n, fileName,p1,p2)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean changetag1(String a) {
        ExportStrategy export = ExportFactory.instance().exportStrategy(ExportFormats.XML);
        if (((ExportToXML) export).changetag1(a)) {
            return true;
        }
        return false;
    }

    public boolean changetag2(String a) {
        ExportStrategy export = ExportFactory.instance().exportStrategy(ExportFormats.XML);
        if (((ExportToXML) export).changetag2(a)) {
            return true;
        }
        return false;
    }

    public boolean changetag3(String a) {
        ExportStrategy export = ExportFactory.instance().exportStrategy(ExportFormats.XML);
        if (((ExportToXML) export).changetag3(a)) {
            return true;
        }
        return false;
    }

    public boolean changetag4(String a) {
        ExportStrategy export = ExportFactory.instance().exportStrategy(ExportFormats.XML);
        if (((ExportToXML) export).changetag4(a)) {
            return true;
        }
        return false;
    }

    public boolean changetag5(String a) {
        ExportStrategy export = ExportFactory.instance().exportStrategy(ExportFormats.XML);
        if (((ExportToXML) export).changetag5(a)) {
            return true;
        }
        return false;
    }

    public boolean changetag6(String a) {
        ExportStrategy export = ExportFactory.instance().exportStrategy(ExportFormats.XML);
        if (((ExportToXML) export).changetag6(a)) {
            return true;
        }
        return false;
    }
    
    public boolean changeLineSeparator(String a ){
         ExportStrategy export = ExportFactory.instance().exportStrategy(ExportFormats.CSV);
       
        if(((ExportToCSV)export).changeSeparator(a)){

            return true;
        }
        return false;
    }
}
