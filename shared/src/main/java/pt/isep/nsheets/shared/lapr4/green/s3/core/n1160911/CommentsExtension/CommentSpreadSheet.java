/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.lapr4.green.s3.core.n1160911.CommentsExtension;

import pt.isep.nsheets.shared.core.Address;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.ext.SpreadsheetExtension;
import pt.isep.nsheets.shared.lapr4.blue.s1.n1160701.StyleExtension.BorderExtension;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.SpreadsheetDTO;

/**
 *
 * @author Toshiba
 */
public class CommentSpreadSheet extends SpreadsheetExtension {

    /**
     * Creates a new spreadsheet extension.
     *
     * @param delegate the delegate of the extension
     */
    public CommentSpreadSheet(Spreadsheet delegate) {
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

    

