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
@RemoteServiceRelativePath("exportSpreadSheetToXMLServices")
public interface ExportSpreadSheetToXMLService {
    public String exportSpreadSheet(Spreadsheet s, String filename); 
}
