/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.domain;

import java.io.FileNotFoundException;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.SpreadsheetDTO;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.WorkbookDTO;

/**
 *
 * @author Telmo
 */

public interface ExportStrategy {

    public boolean exportWorkbook(Workbook workbook, String fileName) throws FileNotFoundException;

    public String exportWorkbook(WorkbookDTO workbookDTO, String fileName) throws FileNotFoundException;

    public String exportSpreadSheet(SpreadsheetDTO spreadsheet, String fileName) throws FileNotFoundException;

    public boolean exportSpreadSheet(Workbook spreadsheet, int n, String fileName) throws FileNotFoundException;

    public boolean exportPartOfSpreadSheet(Spreadsheet spreadsheet, String fileName, int il, int fl, int ic, int fc) throws FileNotFoundException;

    public boolean exportPartOfSpreadsheet(Workbook spreadsheet, int n, String fileName, String p1, String p2) throws FileNotFoundException;

}
