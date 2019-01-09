/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151708.export.csv;

import pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.domain.ExportFactory;
import pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.domain.ExportFormats;

/**
 *
 * @author MarioDias
 */
public class ExportController {
    
    public String exportWorkbook(String [][][] workbook, String filename){
        NewExportStrategy export = ExportFactory.instance().exportNewStrategy(ExportFormats.CSV);
        return export.exportWorkbook(workbook, filename);
    }
    
}
