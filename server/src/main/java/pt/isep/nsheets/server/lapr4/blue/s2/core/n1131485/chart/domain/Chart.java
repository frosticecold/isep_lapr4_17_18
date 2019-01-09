package pt.isep.nsheets.server.lapr4.blue.s2.core.n1131485.chart.domain;

import pt.isep.nsheets.shared.core.Address;
import pt.isep.nsheets.shared.core.SpreadsheetImpl;
import pt.isep.nsheets.shared.services.ChartDTO;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 *
 * Interface to generalize what a chart is
 */
public interface Chart {

    ChartType type();
    
    ChartDTO toDTO(Chart c);
    
    Chart fromDTO(ChartDTO dto);

    String[][] makeChart(Address a1, Address a2, SpreadsheetImpl spreadsheet);
}
