package pt.isep.nsheets.server.lapr4.blue.s2.core.n1131485.chart.application;

import eapli.framework.application.Controller;
import pt.isep.nsheets.shared.core.Address;
import pt.isep.nsheets.shared.core.SpreadsheetImpl;
import pt.isep.nsheets.server.lapr4.blue.s2.core.n1131485.chart.domain.Chart;
import pt.isep.nsheets.server.lapr4.blue.s2.core.n1131485.chart.domain.ChartFactory;
import pt.isep.nsheets.server.lapr4.blue.s2.core.n1131485.chart.domain.ChartType;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class createChartController implements Controller {
    
    public createChartController() {
        
    }

    /**
     * Uses Factory to build the given chart
     *
     * @param type
     * @param name
     * @param wantsLabel
     * @return
     */
    public Chart createChart(String type, String name, boolean wantsLabel) {
        
        ChartType cT = ChartType.valueOf(type);
        
        return ChartFactory.getInstance().createChart(cT, name, wantsLabel);
    }
    
    public String[][] display(Chart c, Address a1, Address a2, SpreadsheetImpl sp) {
            
        return c.makeChart(a1, a2, sp);
    }
}
