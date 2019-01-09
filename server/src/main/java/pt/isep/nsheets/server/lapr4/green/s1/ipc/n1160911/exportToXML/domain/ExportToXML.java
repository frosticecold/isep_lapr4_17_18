/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160911.exportToXML.domain;

import java.io.FileNotFoundException;
import java.util.Formatter;
import pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.domain.ExportStrategy;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.SpreadsheetDTO;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.WorkbookDTO;

/**
 *
 * @author Toshiba
 */
public class ExportToXML implements ExportStrategy {

    String tag1 = "workbook";
    String tag2 = "SpreadSheet";
    String tag3 = "cells";
    String tag4 = "Content";
    String tag5 = "Row";
    String tag6 = "Column";

    public boolean changetag1(String tag1) {
        if (tag1.length() != 1) {
            return false;
        } else {
            this.tag1 = tag1;
            return true;
        }
    }

    public boolean changetag2(String tag2) {
        if (tag2.length() != 1) {
            return false;
        } else {
            this.tag2 = tag2;
            return true;
        }
    }

    public boolean changetag3(String tag3) {
        if (tag3.length() != 1) {
            return false;
        } else {
            this.tag3 = tag3;
            return true;
        }
    }

    public boolean changetag4(String tag4) {
        if (tag4.length() != 1) {
            return false;
        } else {
            this.tag4 = tag4;
            return true;
        }
    }

    public boolean changetag5(String tag5) {
        if (tag5.length() != 1) {
            return false;
        } else {
            this.tag5 = tag5;
            return true;
        }
    }

    public boolean changetag6(String tag6) {
        if (tag6.length() != 1) {
            return false;
        } else {
            this.tag6 = tag6;
            return true;
        }
    }

//    @Override
//    public boolean exportSpreadSheet(Spreadsheet spreadsheet, String fileName) throws FileNotFoundException {
//        if (fileName.isEmpty()) {
//            return false;
//        }
//        StringBuilder a = new StringBuilder();
//        Spreadsheet s;
//        Formatter output = new Formatter(fileName + ".xml");
//        a.append("< ? xml  version = " + "1.0" + " encoding = " + "UTF-8" + " ?>");
//        a.append("\n");
//        a.append("\n");
//        a.append(getInicialTag(tag2));
//        for (int j = 0; j < spreadsheet.getRowCount(); j++) {
//            for (int c = 0; c < spreadsheet.getColumnCount(); c++) {
//                spreadsheet.getCell(c, j);
//                a.append(getInicialTag(tag3));
//                a.append(getInicialTag(tag4) + spreadsheet.getCell(c, j).getContent() + getFinalTag(tag4));
//                a.append(getInicialTag(tag6) + j + getFinalTag(tag6));
//                a.append(getInicialTag(tag5) + c + getFinalTag(tag5));
//                a.append(getFinalTag(tag3));
//
//            }
//        }
//        a.append(getFinalTag(tag2));
//        output.format("s", a);
//        return true;
//
//    }

    public String getInicialTag(String tag1) {
        return "<" + tag1 + ">";
    }

    public String getFinalTag(String tag1) {
        return "</" + tag1 + ">";

    }

    @Override
    public boolean exportWorkbook(Workbook workbook, String fileName) throws FileNotFoundException {
        if (fileName.isEmpty()) {
            return false;
        }
        StringBuilder a = new StringBuilder();
        Spreadsheet s;
        Formatter output = new Formatter(fileName + ".xml");
        a.append("< ? xml  version = " + "1.0" + " encoding = " + "UTF-8" + " ?>");
        a.append("\n");
        a.append("\n");
        a.append(getInicialTag(tag1));
        for (int i = 0; i < workbook.getSpreadsheetCount(); i++) {
            s = workbook.getSpreadsheet(i);
            a.append(getInicialTag(tag2));
            for (int j = 0; j < s.getRowCount(); j++) {
                for (int c = 0; c < s.getColumnCount(); c++) {
                    s.getCell(c, j);
                    a.append(getInicialTag(tag3));
                    a.append(getInicialTag(tag4) + s.getCell(c, j).getContent() + getFinalTag(tag4));
                    a.append(getInicialTag(tag6) + j + getFinalTag(tag6));
                    a.append(getInicialTag(tag5) + c + getFinalTag(tag5));
                    a.append(getFinalTag(tag3));

                }
            }
            a.append(getFinalTag(tag2));
        }
        a.append(getFinalTag(tag1));
        output.format("s", a);
        return true;

    }

    @Override
    public boolean exportPartOfSpreadSheet(Spreadsheet sheet, String fileName, int il, int fl, int ic, int fc) throws FileNotFoundException {
               if (fileName.isEmpty()) {
            return false;
        }
        StringBuilder a = new StringBuilder();
        Spreadsheet s;
        Formatter output = new Formatter(fileName + ".xml");
        a.append("< ? xml  version = " + "1.0" + " encoding = " + "UTF-8" + " ?>");
        a.append("\n");
        a.append("\n");
        a.append(getInicialTag(tag2));
        for (int j = il; j < fl; j++) {
            for (int c = ic; c < fc; c++) {
               sheet.getCell(c, j);
                a.append(getInicialTag(tag3));
                a.append(getInicialTag(tag4) + sheet.getCell(c, j).getContent() + getFinalTag(tag4));
                a.append(getInicialTag(tag6) + j + getFinalTag(tag6));
                a.append(getInicialTag(tag5) + c + getFinalTag(tag5));
                a.append(getFinalTag(tag3));

            }
        }
        a.append(getFinalTag(tag2));
        output.format("s", a);
        return true;
    }

    @Override
    public String exportSpreadSheet(SpreadsheetDTO spreadsheet, String fileName) throws FileNotFoundException {
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
