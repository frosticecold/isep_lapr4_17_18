package pt.isep.nsheets.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161294.search.application.SearchController;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161294.search.domain.SearchByRegularExpression;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.services.SearchService;

/**
 * @author David Camelo <1161294@isep.ipp.pt>
 */
public class SearchServiceImpl extends RemoteServiceServlet implements SearchService {

    @Override
    public String showOutputOfSearch(String regularExpression/*, Workbook workbook, int spreadsheetIndex*/) {
        SearchController searchController = new SearchController(regularExpression/*, workbook, spreadsheetIndex*/);

        return searchController.showOutputOfSearch();
    }

    public Boolean matchByPattern(String regExpression, String str, String formula){
        return SearchByRegularExpression.match(regExpression, str, formula);
    }
}
