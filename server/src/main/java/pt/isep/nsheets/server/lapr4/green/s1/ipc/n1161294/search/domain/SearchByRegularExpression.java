package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161294.search.domain;

import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.Workbook;
import java.util.regex.Pattern;

/**
 * @author David Camelo <1161294@isep.ipp.pt>
 */
public class SearchByRegularExpression{

    private SearchByRegularExpression(){}

    /**
     * Search cells that match the regular expressions in a spreadsheet
     * and add the cell information to the output
     *
     * @param regExpression regular expression
     * @param workbook actual workbook
     * @param spreadsheetIndex index of spreadsheet where to search in workbook
     * @return matches of the regular expression in the spreadsheet
     */
    public static String search(String regExpression, Workbook workbook, int spreadsheetIndex){

        if(regExpression == null)
            throw new IllegalArgumentException("Regular expression is null");
        if(regExpression.equals(""))
            throw new IllegalArgumentException("Regular expression is empty");
        if(workbook == null)
            throw new IllegalArgumentException("Workbook not found");

        final String NEW_LINE = "\n";
        final String EMPTY = "";

        String output = EMPTY;

        Spreadsheet spreadsheet = workbook.getSpreadsheet(spreadsheetIndex);

        int rows = spreadsheet.getRowCount(), cols = spreadsheet.getRowCount();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                if(Pattern.matches(regExpression, spreadsheet.getCell(col, row).getContent()) ||
                        Pattern.matches(regExpression, spreadsheet.getCell(col, row).getFormula().toString()) ){
                    output += "Row: " + row + " Col: " + col;
                    output += NEW_LINE;
                }
            }
        }
        return output;
    }

    public static boolean match(String regExpression, String str, String formula){
        return Pattern.matches(regExpression, str);//; || Pattern.matches(regExpression, formula);
    }
}
