/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.domain;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isep.nsheets.shared.core.Cell;
import com.itextpdf.text.Element;
import gwt.material.design.client.ui.MaterialToast;
import java.util.SortedSet;
import pt.isep.nsheets.shared.core.Address;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.SpreadsheetDTO;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.WorkbookDTO;

/**
 *
 * @author Telmo
 */
public class ExportToPDF implements ExportStrategy {

    /**
     * The highest character to be used in a column name
     */
    public static final String COLUMN_CHARS = "ABCDEFGHIJKLMNOPQRSTUVXZ";

    @Override
    public boolean exportWorkbook(Workbook workbook, String fileName) throws FileNotFoundException {
        boolean flag = false;
        Document documento = new Document();
        Path baseFolder = Paths.get(System.getProperty("user.dir"));
        Path exportPath = Paths.get(baseFolder + "/exports/pdf/");
        File file = new File(exportPath.toUri());
        try {
            file.mkdirs();
            file = new File(file, fileName + ".pdf");
            file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(ExportToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        FileOutputStream fop = new FileOutputStream(file);
        try {
            PdfWriter.getInstance(documento, fop);
        } catch (DocumentException ex) {
            Logger.getLogger(ExportToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        documento.open();
        Font f = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
        font.setColor(BaseColor.BLUE);

        Font font2 = new Font(Font.FontFamily.COURIER, 16, Font.NORMAL);

        int spreds = workbook.getSpreadsheetCount();

        Paragraph p = new Paragraph("Number of spreadsheets:" + spreds + "\n", font);
        try {
            documento.add(p);
        } catch (DocumentException ex) {
            Logger.getLogger(ExportToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < spreds; i++) {
            Paragraph p1 = new Paragraph("Spreadheet:" + i + "\n", font2);
            try {
                documento.add(p1);
            } catch (DocumentException ex) {
                Logger.getLogger(ExportToPDF.class.getName()).log(Level.SEVERE, null, ex);
            }
            Spreadsheet s = workbook.getSpreadsheet(i);
            int rows = s.getRowCount();
            int columns = s.getColumnCount();
            PdfPTable tabela = new PdfPTable(columns);

            for (int c = 0; c < columns; c++) {
                Phrase frase = new Phrase(COLUMN_CHARS.charAt(c) + "", f);
                PdfPCell cell = new PdfPCell(frase);
                cell.setBackgroundColor(new BaseColor(Color.lightGray.getRGB()));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabela.addCell(cell);
                tabela.setHeaderRows(1);

            }
            for (int r = 0; r < rows; r++) {

                for (int c = 0; c < columns; c++) {

                    Phrase frase = new Phrase(s.getCell(c, r).getContent(), f);
                    PdfPCell cell = new PdfPCell(frase);
                    tabela.addCell(cell);

                }
            }
            try {
                documento.add(tabela);
            } catch (DocumentException ex) {
                Logger.getLogger(ExportToPDF.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        documento.close();
        try {
            fop.flush();
        } catch (IOException ex) {
            Logger.getLogger(ExportToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fop.close();
        } catch (IOException ex) {
            Logger.getLogger(ExportToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }
        flag = true;
        return flag;
    }

    @Override
    public String exportWorkbook(WorkbookDTO workbookDTO, String fileName) throws FileNotFoundException {
        return null;
    }

    @Override
    public boolean exportSpreadSheet(Workbook workbook, int n, String fileName) throws FileNotFoundException {
        boolean flag = false;
        Document documento = new Document();
        Path baseFolder = Paths.get(System.getProperty("user.dir"));
        Path exportPath = Paths.get(baseFolder + "/exports/pdf/");
        File file = new File(exportPath.toUri());
        try {
            file.mkdirs();
            file = new File(file, fileName + ".pdf");
            file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(ExportToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        FileOutputStream fop = new FileOutputStream(file);
        try {
            PdfWriter.getInstance(documento, fop);
        } catch (DocumentException ex) {
            Logger.getLogger(ExportToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        documento.open();
        Font f = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
        font.setColor(BaseColor.BLUE);

        int spreds = workbook.getSpreadsheetCount();

        Paragraph p = new Paragraph("Spreadsheet: " + n + "\n", font);
        try {
            documento.add(p);
        } catch (DocumentException ex) {
            Logger.getLogger(ExportToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < spreds; i++) {
            if (n == i) {

                Spreadsheet s = workbook.getSpreadsheet(i);
                int rows = s.getRowCount();
                int columns = s.getColumnCount();
                PdfPTable tabela = new PdfPTable(columns);

                for (int c = 0; c < columns; c++) {

                    Phrase frase = new Phrase(COLUMN_CHARS.charAt(c) + "", f);
                    PdfPCell cell = new PdfPCell(frase);
                    cell.setBackgroundColor(new BaseColor(Color.lightGray.getRGB()));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tabela.addCell(cell);
                    tabela.setHeaderRows(1);

                }
                for (int r = 0; r < rows; r++) {

                    for (int c = 0; c < columns; c++) {

                        Phrase frase = new Phrase(s.getCell(c, r).getContent(), f);
                        PdfPCell cell = new PdfPCell(frase);
                        tabela.addCell(cell);

                    }
                }
                try {
                    documento.add(tabela);
                } catch (DocumentException ex) {
                    Logger.getLogger(ExportToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        documento.close();
        try {
            fop.flush();
        } catch (IOException ex) {
            Logger.getLogger(ExportToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fop.close();
        } catch (IOException ex) {
            Logger.getLogger(ExportToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }
        flag = true;
        return flag;
    }

    @Override
    public boolean exportPartOfSpreadsheet(Workbook sheet, int n, String fileName,
            String p1, String p2) throws FileNotFoundException {
        boolean flag = false;
        Spreadsheet s = sheet.getSpreadsheet(n);
        Address pos1 = s.findAddress(p1);
        Address pos2 = s.findAddress(p2);

        Document documento = new Document();
        Path baseFolder = Paths.get(System.getProperty("user.dir"));
        Path exportPath = Paths.get(baseFolder + "/exports/pdf/");
        File file = new File(exportPath.toUri());
        try {
            file.mkdirs();
            file = new File(file, fileName + ".pdf");
            file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(ExportToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        FileOutputStream fop = new FileOutputStream(file);
        try {
            PdfWriter.getInstance(documento, fop);
        } catch (DocumentException ex) {
            Logger.getLogger(ExportToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        documento.open();
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

        Font f = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
        Paragraph p = new Paragraph("Part of Spreadsheet: " + n + "\nFrom " + p1 + ", to " + p2, f);
        try {
            documento.add(p);
        } catch (DocumentException ex) {
            Logger.getLogger(ExportToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }

        SortedSet<Cell> lista = s.getCells(pos1, pos2);
        PdfPTable tabela = new PdfPTable(s.getColumnCount());
        for (Cell cell : lista) {
            Phrase frase = new Phrase(cell.getContent(), font);
            PdfPCell cell1 = new PdfPCell(frase);
            tabela.addCell(cell1);
        }

        try {
            documento.add(tabela);
        } catch (DocumentException ex) {
            Logger.getLogger(ExportToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        documento.close();
        try {
            fop.flush();
        } catch (IOException ex) {
            Logger.getLogger(ExportToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fop.close();
        } catch (IOException ex) {
            Logger.getLogger(ExportToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }

        flag = true;
        return flag;
    }

    @Override
    public String exportSpreadSheet(SpreadsheetDTO spreadsheet, String fileName) throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exportPartOfSpreadSheet(Spreadsheet spreadsheet, String fileName, int il, int fl, int ic, int fc) throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
