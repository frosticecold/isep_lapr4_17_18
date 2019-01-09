/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151708.export.csv.ExportController;
import pt.isep.nsheets.shared.services.ExportToCSVFormatService;

/**
 *
 * @author MarioDias
 */
public class ExportToCSVFormatServiceImpl extends RemoteServiceServlet implements ExportToCSVFormatService{

    @Override
    public String exportWorkbook(String[][][] workbook, String filename) {
        ExportController controller = new ExportController();
        return controller.exportWorkbook(workbook, filename);
    }
    
}
