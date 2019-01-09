/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151186.export.xml;

import java.io.FileNotFoundException;
import pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.domain.ExportFactory;
import pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.domain.ExportFormats;
import pt.isep.nsheets.shared.core.Spreadsheet;

/**
 *
 * @author Car
 */
public class ExportSpreadSheetPartController {
    public String exportSpreadSheetPart( String fileName, Spreadsheet spreadsheet, int il, int fl, int ic, int fc) throws FileNotFoundException {
        ExportSpreadSheetPartStrategy export = ExportFactory.instance().exportSpreadSheetPartStrategy(ExportFormats.XML);
        return export.exportSpreadSheetPart(spreadsheet,  fileName,  il,  fl,  ic,  fc);
    }
}