package pt.isep.nsheets.shared.lapr4.blue.s1.n1160701.StyleExtension;

import pt.isep.nsheets.shared.core.Address;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.ext.SpreadsheetExtension;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.SpreadsheetDTO;

public class BorderSpreadSheet extends SpreadsheetExtension {

    /**
     * Creates a new spreadsheet extension.
     *
     * @param delegate the delegate of the extension
     */
    public BorderSpreadSheet(Spreadsheet delegate) {
        super(delegate, BorderExtension.NAME);
    }

    @Override
    public SpreadsheetDTO toDTO() {
        return null;

    }

    @Override
    public Address findAddress(String reference) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
