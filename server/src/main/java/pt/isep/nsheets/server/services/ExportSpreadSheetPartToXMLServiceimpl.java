/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.services;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151186.export.xml.ExportSpreadSheetPartController;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.services.ExportSpreadSheetPartToXMLService;

/**
 *
 * @author Car
 */
public class ExportSpreadSheetPartToXMLServiceimpl implements ExportSpreadSheetPartToXMLService{

    @Override
    public String exportSpreadSheetPart(String filename, Spreadsheet spreadsheet, int il, int fl, int ic, int fc) {
        ExportSpreadSheetPartController controller = new ExportSpreadSheetPartController();
        try {
            return controller.exportSpreadSheetPart(filename,spreadsheet, il,fl,ic,fc);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExportSpreadSheetPartToXMLServiceimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "erro";
    }
    }
    

