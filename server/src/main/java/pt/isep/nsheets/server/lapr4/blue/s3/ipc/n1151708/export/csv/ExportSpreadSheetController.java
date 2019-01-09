/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151708.export.csv;

import java.io.FileNotFoundException;
import pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.domain.ExportFactory;
import pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.domain.ExportFormats;
import pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.domain.ExportStrategy;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.SpreadsheetDTO;

/**
 *
 * @author 1151708
 */
public class ExportSpreadSheetController {

    public String exportSpreadSheet(ExportFormats format, SpreadsheetDTO spreadsheet, String fileName) throws FileNotFoundException {
        ExportStrategy export = ExportFactory.instance().exportStrategy(format);
        return export.exportSpreadSheet(spreadsheet, fileName);        
    }
}
