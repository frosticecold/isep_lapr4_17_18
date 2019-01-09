package pt.isep.nsheets.shared.services;

//import pt.isep.nsheets.server.lapr4.blue.s2.core.n1131485.chart.domain.ChartType;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.io.Serializable;
import javax.persistence.Embeddable;
import pt.isep.nsheets.shared.core.Address;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
@Embeddable
public class ChartDTO implements Serializable, IsSerializable{

    private String[][] content;
    private String name;
    private String type;
    private boolean label;
    private String firstCell;
    private String lastCell;
    private String labelName;

    //Blank construtor just because
    public ChartDTO() {
        this.content = null;
        this.name = "";
        this.type = "BARCHART";
        this.firstCell = "";
        this.lastCell = "";
        this.labelName = "";
        this.label = false;
    }

    //FULL CONSTRUCT
    public ChartDTO(Address a1, Address a2, String name, String type, String[][] content, boolean boo, String labelName) {
        
        this.type = type;
        this.name = name;
        this.content = content;
        this.label = boo;
        this.labelName = labelName;
    }

    public String[][] content() {

        return this.content;
    }

    public String name() {

        return this.name;
    }

    public String type() {

        return this.type;
    }
    
    public String firstCell() {
        
        return this.firstCell;
    }
    
    public String lastCell() {
        
        return this.lastCell;
    }
    
    public boolean label() {
        
        return this.label;
    }

    @Override
    public boolean equals(Object other) {

        boolean ret = false;
        if (other instanceof ChartDTO) {
            ChartDTO c = (ChartDTO)other;
            
            if (this.name.compareTo(c.name()) == 0) {
                ret = true;
            }
        }
        
        return ret;
    }

    
    public String labelName() {
        
        return this.labelName;
    }
}
