package pt.isep.nsheets.shared.lapr4.red.s1.ext.n1161018.YellowExtension;

import pt.isep.nsheets.shared.core.Address;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.ext.SpreadsheetExtension;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.SpreadsheetDTO;

/**
 * The {@link Spreadsheet} extension for Yellow typed Spreadsheets
 *
 * @author David Blanquett<1161018@isep.ipp.pt>
 */
public class YellowSpreadSheet extends SpreadsheetExtension {

    /**
     * Creates a new spreadsheet extension.
     *
     * @param delegate the delegate of the extension
     */
    public YellowSpreadSheet(Spreadsheet delegate) {
        super(delegate, YellowExtension.NAME);
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
