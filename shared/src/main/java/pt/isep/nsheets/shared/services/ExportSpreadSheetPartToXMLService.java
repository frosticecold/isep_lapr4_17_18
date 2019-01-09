/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import pt.isep.nsheets.shared.core.Spreadsheet;

/**
 *
 * @author Car
 */
@RemoteServiceRelativePath("exportSpreadSheetPartToXMLServices")
public interface ExportSpreadSheetPartToXMLService {
    public String exportSpreadSheetPart(String fileName, Spreadsheet spreadsheet, int il, int fl, int ic, int fc);
}
