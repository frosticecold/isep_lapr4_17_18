/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151186.export.xml;

import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151708.export.csv.NewExportStrategy;
import pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.domain.ExportFactory;
import pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.domain.ExportFormats;

/**
 *
 * @author Car
 */
public class ExportXMLController {
    public String exportWorkbook(String [][][] workbook, String filename){
        NewExportStrategy export = ExportFactory.instance().exportNewStrategy(ExportFormats.XML);
        return export.exportWorkbook(workbook, filename);
    }
}
