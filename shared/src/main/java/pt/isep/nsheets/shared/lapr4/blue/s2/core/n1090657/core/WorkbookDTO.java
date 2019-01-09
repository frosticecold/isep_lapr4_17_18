/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class WorkbookDTO implements Serializable,IsSerializable{

    public List<SpreadsheetDTO> spreadsheets;

    public int createdSpreadsheets;

    public boolean isPublic;
    

    /**
     * for ORM
     */
    public WorkbookDTO() {
        spreadsheets= new ArrayList<>();
        spreadsheets.add(new SpreadsheetDTO());
        createdSpreadsheets=0;
        isPublic=true;
        
    }

    
    public WorkbookDTO(List<SpreadsheetDTO> list, int createdSpreadsheets, boolean isPublic) {
        this.spreadsheets = list;
        this.createdSpreadsheets = createdSpreadsheets;
        this.isPublic = isPublic;
    }

    public List<SpreadsheetDTO> getSpreadsheets() {
        return spreadsheets;
    }

    public int getCreatedSpreadsheets() {
        return createdSpreadsheets;
    }

    public boolean isPublic() {
        return isPublic;
    }
    
    
}
