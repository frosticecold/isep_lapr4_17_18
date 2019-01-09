package pt.isep.nsheets.shared.lapr4.green.s3.core.n1161294;

import pt.isep.nsheets.shared.core.Address;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaCompilationException;
import pt.isep.nsheets.shared.ext.Extension;
import pt.isep.nsheets.shared.ext.SpreadsheetExtension;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.SpreadsheetDTO;

public class ImageExtension extends Extension {

    public static final String IMAGE_EXTENSION_NAME = "ImageExtensionName";
    public static final String IMAGE_EXTENSION_TYPE = "ImageExtensionType";

    private Cell cell;
    private String imageUrl;

    public class ImageExtensionSpreadSheet extends SpreadsheetExtension {

        public ImageExtensionSpreadSheet(Spreadsheet delegate){
            super(delegate, ImageExtension.IMAGE_EXTENSION_NAME);
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

    public ImageExtension(Cell cell, String imageUrl){
        super(IMAGE_EXTENSION_NAME);
        setExtensionType(IMAGE_EXTENSION_TYPE);
        this.imageUrl = imageUrl;
        this.cell = cell;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Cell getCell() {
        return cell;
    }

    public ImageExtensionSpreadSheet extend(Spreadsheet spreadsheet){
        return new ImageExtensionSpreadSheet(spreadsheet);
    }

    public ImageCell extend(Cell cell){
        try{
            cell.setContent("Image");
        } catch(FormulaCompilationException e){
            e.printStackTrace();
        }

        return new ImageCell(cell);
    }
}
