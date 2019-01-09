package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import pt.isep.nsheets.shared.core.Spreadsheet;

public interface ImportXMLServiceAsync {

    void exportSpreadSheetToXML(Spreadsheet s, String fileName, AsyncCallback<Boolean> callback);

    void exportPartOfSpreadSheetToXML(Spreadsheet s, String fileName, int il, int fl, int ic, int fc, AsyncCallback<Boolean> callback);

    void importWorkbookToXML(String fileName, String name, String desc, String username, AsyncCallback<WorkbookDescriptionDTO> callback);
}
