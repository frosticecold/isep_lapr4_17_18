package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.Workbook;

/**
 * @author David Camelo <1161294@isep.ipp.pt>
 */
@RemoteServiceRelativePath("searchService")
public interface SearchService extends RemoteService{

    /**
     * Represent the output of the search with the regular expression in the spreadsheet
     *
     * @return output of search
     */
    String showOutputOfSearch(String regularExpression/*, Workbook workbook, int spreadsheetIndex*/);

    Boolean matchByPattern(String regExpression, String str, String formula);
}
