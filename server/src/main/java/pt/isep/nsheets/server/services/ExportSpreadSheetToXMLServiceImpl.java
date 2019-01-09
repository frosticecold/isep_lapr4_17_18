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
import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151186.export.xml.ExportSpreadSheetToXMLController;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.services.ExportSpreadSheetToXMLService;

/**
 *
 * @author Car
 */
public class ExportSpreadSheetToXMLServiceImpl extends RemoteServiceServlet implements ExportSpreadSheetToXMLService{

    @Override
    public String exportSpreadSheet(Spreadsheet s, String filename) {
        ExportSpreadSheetToXMLController controller = new ExportSpreadSheetToXMLController();
        try {
            return controller.exportSpreadSheet(s, filename);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExportSpreadSheetToXMLServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "erro";
    }
    
}

