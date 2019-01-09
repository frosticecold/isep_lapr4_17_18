package pt.isep.nsheets.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import eapli.framework.application.Controller;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161182.import_xml.domain.ImportFromXMLController;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.services.DataException;
import pt.isep.nsheets.shared.services.ImportXMLService;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;

public class ImportXMLServiceImpl extends RemoteServiceServlet implements ImportXMLService {

    @Override
    public boolean exportSpreadSheetToXML(Spreadsheet s, String fileName) {
        return false;
    }

    @Override
    public boolean exportPartOfSpreadSheetToXML(Spreadsheet s, String fileName, int il, int fl, int ic, int fc) {
        return false;
    }

    @Override
    public WorkbookDescriptionDTO importWorkbookToXML(String fileName, String name, String desc, String username) {
        Controller c = new ImportFromXMLController();

        WorkbookDescriptionDTO workbookDescriptionDTO = null;
        try {
            workbookDescriptionDTO = ((ImportFromXMLController) c).importWorkbook(fileName, name, desc, false, username);
        } catch (DataException e) {
            e.printStackTrace();
        }

        return workbookDescriptionDTO;
    }
}
