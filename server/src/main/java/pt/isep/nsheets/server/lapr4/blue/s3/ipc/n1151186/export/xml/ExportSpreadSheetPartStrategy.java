/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151186.export.xml;

import java.io.FileNotFoundException;
import pt.isep.nsheets.shared.core.Spreadsheet;

/**
 *
 * @author Car
 */
public interface ExportSpreadSheetPartStrategy {
    public String exportSpreadSheetPart(Spreadsheet spreadsheet, String fileName, int il, int fl, int ic, int fc) throws FileNotFoundException;
}
