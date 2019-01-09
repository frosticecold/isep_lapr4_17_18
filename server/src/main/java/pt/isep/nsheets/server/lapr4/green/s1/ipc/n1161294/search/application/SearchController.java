package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161294.search.application;

import eapli.framework.application.Controller;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161294.search.domain.SearchByRegularExpression;
import pt.isep.nsheets.shared.core.Workbook;

/**
 * @author David Camelo <1161294@isep.ipp.pt>
 */
public class SearchController implements Controller {

    /**
     * Regular expression
     */
    private String regularExpression;

    /**
     * Actual workbook
     */
    private Workbook workbook;

    /**
     * Index of spreadsheet in workbook
     */
    private int spreadsheetIndex;

    /**
     * Constructor
     *
     * @param regularExpression regular expression
     * @param workbook actual workbook
     * @param spreadsheetIndex  index of spreadsheet in workbook
     */
    public SearchController(String regularExpression/*, Workbook workbook, int spreadsheetIndex*/){

        this.regularExpression = regularExpression;
        this.workbook = workbook;
        this.spreadsheetIndex = spreadsheetIndex;
    }

    /**
     * Represent the output of the search with the regular expression in the spreadsheet
     *
     * @return output of search
     */
    public String showOutputOfSearch(){
        return SearchByRegularExpression.search(regularExpression, workbook, spreadsheetIndex);
    }
}
