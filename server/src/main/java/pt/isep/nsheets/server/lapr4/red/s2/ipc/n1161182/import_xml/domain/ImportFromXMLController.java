package pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161182.import_xml.domain;

import eapli.framework.application.Controller;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.domain.WorkbookDescription;
import pt.isep.nsheets.server.services.WorkbooksServiceImpl;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.services.DataException;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;
import pt.isep.nsheets.shared.services.WorkbooksService;

public class ImportFromXMLController implements Controller {

    public WorkbookDescriptionDTO importWorkbook(String filePath, String name, String description, boolean stat, String username) throws DataException {

        ImportXML imp = new ImportXML();
        String[][][] contents = imp.importWorkbook(filePath);

        Workbook w = new Workbook();
        w.setIsPublic(stat);

        for (String[][] c : contents) {
            try {
                w.addSpreadsheet(c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        WorkbookDescription workbookDescription = new WorkbookDescription(name, description, w);

        WorkbooksService service = new WorkbooksServiceImpl();
        service.addWorkbookDescription(workbookDescription.toDTO(), stat, username);
        return workbookDescription.toDTO();
    }


}
