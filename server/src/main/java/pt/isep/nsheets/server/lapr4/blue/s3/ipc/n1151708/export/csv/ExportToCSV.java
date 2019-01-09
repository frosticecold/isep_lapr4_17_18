/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151708.export.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1151708
 */
public class ExportToCSV implements NewExportStrategy {

    public static String SEPARATOR = ";";

    public boolean changeSeparator(String s) {
        if (s.length() != 1) {
            return false;
        } else {
            SEPARATOR = s;
            return true;
        }
    }

    @Override
    public String exportWorkbook(String[][][] workbook, String filename) {
        PrintWriter pw = null;
        try {
            if (filename.isEmpty()) {
                return "Error";
            }
            int spreadsheetNum = 0;
            pw = new PrintWriter(new File("server/export/" + filename + ".csv"));
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < workbook.length; i++) {
                for (int j = 0; j < workbook[i].length; j++) {
                    for (int k = 0; k < workbook[i][j].length; k++) {
                        s.append(spreadsheetNum);
                        s.append(SEPARATOR);
                        s.append(workbook[i][j][k]);
                        s.append(SEPARATOR);
                    }
                    s.append("\n");
                }
                spreadsheetNum++;
            }
            pw.write(s.toString());
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExportToCSV.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }

        return filename + ".csv";
    }

}
