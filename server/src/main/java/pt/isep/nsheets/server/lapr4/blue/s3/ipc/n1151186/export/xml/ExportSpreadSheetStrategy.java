/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151186.export.xml;

import pt.isep.nsheets.shared.core.Spreadsheet;

/**
 *
 * @author Car
 */
public interface ExportSpreadSheetStrategy {
    public String exportSpreadSheet(Spreadsheet spreadsheet, String fileName);
}
