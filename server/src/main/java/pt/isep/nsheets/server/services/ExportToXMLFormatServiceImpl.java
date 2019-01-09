/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151186.export.xml.ExportXMLController;
import pt.isep.nsheets.shared.services.ExportToXMLFormatService;

/**
 *
 * @author Car
 */
public class ExportToXMLFormatServiceImpl extends RemoteServiceServlet implements ExportToXMLFormatService{

    @Override
    public String exportWorkbook(String[][][] workbook, String filename) {
        ExportXMLController controller = new ExportXMLController();
        return controller.exportWorkbook(workbook, filename);
    }
    
}