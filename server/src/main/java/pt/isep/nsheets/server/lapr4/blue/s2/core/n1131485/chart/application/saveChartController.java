package pt.isep.nsheets.server.lapr4.blue.s2.core.n1131485.chart.application;

import eapli.framework.application.Controller;
import pt.isep.nsheets.server.lapr4.blue.s2.core.n1131485.chart.domain.Chart;
import pt.isep.nsheets.shared.core.Address;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.CellImpl;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.services.ChartDTO;

/**
 *
 * @author PedroEmanuelCoelho <1131485@isep.ipp.pt>
 */
public class saveChartController implements Controller {

    public saveChartController() {

    }

    /**
     * Method to save the chart on persistence
     *
     * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
     *
     *
     * @param currentWorkbook
     * @param dto
     * @return
     */
    public boolean saveChart(Workbook currentWorkbook, int indexCurrentSpreadsheet, Chart chart, Cell activeCell) {

        ChartDTO dto = chart.toDTO(chart);
        
        CellImpl c = (CellImpl) activeCell;
        
        if(currentWorkbook.getSpreadsheet(indexCurrentSpreadsheet).getCell(c.getAddress()).storeChart(dto)) {
            
            //persist workbook
        }
        
        return false;
    }
}
