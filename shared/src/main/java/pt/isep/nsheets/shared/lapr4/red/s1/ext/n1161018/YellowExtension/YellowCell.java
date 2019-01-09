package pt.isep.nsheets.shared.lapr4.red.s1.ext.n1161018.YellowExtension;

import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.CellImpl;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaCompilationException;
import pt.isep.nsheets.shared.ext.CellExtension;
import pt.isep.nsheets.shared.lapr4.red.s2.core.n1161018.DTO.CellDTO;
import pt.isep.nsheets.shared.services.ChartDTO;

/**
 * The Cell extension for Yellow typed cells
 *
 * @author David Blanquett<1161018@isep.ipp.pt>
 */
public class YellowCell extends CellExtension {

    public static final float YELLOW_VALUE = 0F;
    private String myColour= "yellow";
    
    private ChartDTO chart;

    /**
     * Creates a Yellow typed cell.
     *
     * @param delegate: Cell to delagate extesion.
     */
    public YellowCell(Cell delegate) {

        
        super(delegate, YellowExtension.NAME);

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
