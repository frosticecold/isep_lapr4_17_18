/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160900.exportToCSV.domain;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.util.Arrays.stream;
import java.util.Formatter;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.domain.*;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.SpreadsheetDTO;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.WorkbookDTO;

/**
 *
 * @author Telmo
 */
public class ExportToCSV implements ExportStrategy {

    public static String SEPARATOR = ";";

    /**
     *
     * @param workbook
     * @return
     * @throws FileNotFoundException
     */
    @Override
    public boolean exportWorkbook(Workbook workbook, String fileName) throws FileNotFoundException {
        if (fileName.isEmpty()) {
            return false;
        }
        Formatter output = new Formatter(fileName + ".csv");

        Spreadsheet sheet;
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < workbook.getSpreadsheetCount(); i++) {
            sheet = workbook.getSpreadsheet(i);
            for (int row = 0; row < sheet.getRowCount(); row++) {
                for (int column = 0; column < sheet.getColumnCount(); column++) {
                    s.append(sheet.getCell(column, row));
                    s.append(SEPARATOR);
                }
                if (row + 1 < sheet.getRowCount()) {
                    s.append("\n");
                }
            }
            
            

        }
        output.format("s", s);
        return true;
    }

    public boolean changeSeparator(String s) {
        if (s.length() != 1) {
            return false;
        } else {
            SEPARATOR = s;
            return true;
        }
    }

//    @Override
//    public boolean exportSpreadSheet(Spreadsheet sheet, String fileName) throws FileNotFoundException {
//        if (fileName.isEmpty()) {
//            return false;
//        }
//        Formatter output = new Formatter(fileName + ".csv");
//
//        StringBuilder s = new StringBuilder();
//        for (int row = 0; row < sheet.getRowCount(); row++) {
//            for (int column = 0; column < sheet.getColumnCount(); column++) {
//                s.append(sheet.getCell(column, row));
//                s.append(SEPARATOR);
//            }
//            if (row + 1 < sheet.getRowCount()) {
//                s.append("\n");
//            }
//        }
//        output.format("s", s);
//        return true;
//    }
     @Override
    public boolean exportPartOfSpreadSheet(Spreadsheet sheet, String fileName, int il, int fl, int ic, int fc) throws FileNotFoundException {
        if (fileName.isEmpty()) {
            return false;
        }
        Formatter output = new Formatter(fileName + ".csv");

        StringBuilder s = new StringBuilder();
        for (int row = il; row < fl; row++) {
            for (int column = ic; column < fc; column++) {
                s.append(sheet.getCell(column, row));
                s.append(SEPARATOR);
            }
            if (row + 1 < sheet.getRowCount()) {
                s.append("\n");
            }
        }
        output.format("s", s);
        return true;
    }

    @Override
    public String exportSpreadSheet(SpreadsheetDTO spreadsheet, String fileName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String exportWorkbook(WorkbookDTO workbookDTO, String fileName) throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exportSpreadSheet(Workbook spreadsheet, int n, String fileName) throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public boolean exportPartOfSpreadsheet(Workbook spreadsheet, int n, String fileName, String p1, String p2) throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
