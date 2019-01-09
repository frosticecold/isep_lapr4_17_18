/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.lapr4.green.s3.core.n1160911.CommentsExtension;

import pt.isep.nsheets.shared.core.Address;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaCompilationException;
import pt.isep.nsheets.shared.ext.Extension;
import pt.isep.nsheets.shared.ext.SpreadsheetExtension;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.SpreadsheetDTO;

/**
 *
 * @author Toshiba
 */
public class CommentExtension extends Extension {
    
    public static final String COMMENT_EXTENSION_NAME = "CommentExtensionName";
    public static final String COMMENT_EXTENSION_TYPE = "CommentExtensionType";

    private Cell cell;
    private String comment;

    public class CommentExtensionSpreadSheet extends SpreadsheetExtension {

        public CommentExtensionSpreadSheet(Spreadsheet delegate){
            super(delegate, CommentExtension.COMMENT_EXTENSION_NAME);
        }

        /**
         *
         * @return
         */
        @Override
        public SpreadsheetDTO toDTO() {
            return null;
        }

        @Override
        public Address findAddress(String reference) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public CommentExtension(Cell cell, String c){
        super(COMMENT_EXTENSION_NAME);
        setExtensionType(COMMENT_EXTENSION_TYPE);
        this.comment = c;
        this.cell = cell;
    }

    public String getComment() {
        return comment;
    }

    public Cell getCell() {
        return cell;
    }

    @Override
    public CommentExtensionSpreadSheet extend(Spreadsheet spreadsheet){
        return new CommentExtensionSpreadSheet(spreadsheet);
    }

    @Override
    public CommentCell extend(Cell cell){
        try{
            cell.setContent("Comment");
        } catch(FormulaCompilationException e){
            e.printStackTrace();
        }

        return new CommentCell(cell);
    }
}
