/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151708.export.csv.ImportController;
import pt.isep.nsheets.shared.services.UploadCSVService;

/**
 *
 * @author MarioDias
 */
public class UploadCSVServiceImpl extends RemoteServiceServlet implements UploadCSVService {

	@Override
	public String[][] uploadCSV(String path) {
		ImportController controller = new ImportController();
		String[][] sheet = controller.importCSV(path);
		return sheet;
	}

}
