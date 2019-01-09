package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.Workbook;

/**
 * @author David Camelo <1161294@isep.ipp.pt>
 */
public interface SearchServiceAsync {

    /**
     * Represent the output of the search with the regular expression in the spreadsheet
     *
     * @return output of search
     */
    void showOutputOfSearch(String regularExpression/*, Workbook workbook, int spreadsheetIndex*/, AsyncCallback<String> callback);

    void matchByPattern(String regExpression, String str, String formula, AsyncCallback<Boolean> callback);
}