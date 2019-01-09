package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import pt.isep.nsheets.shared.core.Workbook;

public interface SearchNReplaceServiceAsync {

    void manel(Workbook ola, AsyncCallback<String> callback);

  //  void showOutputOfSearch(String regularExpression/*, Workbook workbook, int spreadsheetIndex*/, AsyncCallback<String> callback);
}
