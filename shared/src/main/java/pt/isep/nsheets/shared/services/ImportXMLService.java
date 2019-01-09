package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import pt.isep.nsheets.shared.core.Spreadsheet;

/**
 * @author Vitor Brito 1161182@isep.ipp.pt
 */
@RemoteServiceRelativePath("ImportXMLService")
public interface ImportXMLService extends RemoteService {

    boolean exportSpreadSheetToXML(Spreadsheet s, String fileName);

    boolean exportPartOfSpreadSheetToXML(Spreadsheet s, String fileName, int il, int fl, int ic, int fc);

    WorkbookDescriptionDTO importWorkbookToXML(String fileName, String name, String desc, String username);
}
