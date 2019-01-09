package pt.isep.nsheets.shared.lapr4.blue.s1.n1160701.StyleExtension;


import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.ext.CellExtension;
import pt.isep.nsheets.shared.lapr4.red.s2.core.n1161018.DTO.CellDTO;
import pt.isep.nsheets.shared.services.ChartDTO;


public class TextCell extends CellExtension {
    
    private ChartDTO chart;


    /**
     * Creates a typed cell.
     *
     * @param delegate: Cell to delagate extesion.
     */
    public TextCell(Cell delegate) {
        super(delegate, TextExtension.NAME);

    }

    @Override
    public boolean storeChart(ChartDTO dto) {
        boolean flag = false;
        
        if(!this.chart.equals(dto)) {
            this.chart = dto;
            flag = true;
        }
        
        return flag;
    }

    @Override
    public ChartDTO chart() {
        return this.chart;
    }

    @Override
    public CellDTO toCellDTO() {

        return this.toCellDTO();
    }

}
