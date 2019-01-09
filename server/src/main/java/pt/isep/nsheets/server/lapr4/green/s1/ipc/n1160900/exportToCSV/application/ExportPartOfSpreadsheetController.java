
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160900.exportToCSV.application;

import java.io.FileNotFoundException;
import pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.domain.ExportFactory;
import pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.domain.ExportFormats;
import pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.domain.ExportStrategy;
import pt.isep.nsheets.shared.core.Spreadsheet;

/**
 *
 * @author utilizador
 */
public class ExportPartOfSpreadsheetController {

    public boolean exportPartOfSpreadSheet(ExportFormats format, String fileName, Spreadsheet s, int il, int fl, int ic, int fc) throws FileNotFoundException {
        ExportStrategy export = ExportFactory.instance().exportStrategy(format);
        if (export.exportPartOfSpreadSheet(s, fileName, il, fl, ic, fc)) {
            return true;
        } else {
            return false;
        }
    }
}
