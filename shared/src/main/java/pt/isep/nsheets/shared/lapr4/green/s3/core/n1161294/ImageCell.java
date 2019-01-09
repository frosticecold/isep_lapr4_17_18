package pt.isep.nsheets.shared.lapr4.green.s3.core.n1161294;

import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.ext.CellExtension;
import pt.isep.nsheets.shared.lapr4.red.s2.core.n1161018.DTO.CellDTO;
import pt.isep.nsheets.shared.services.ChartDTO;

public class ImageCell extends CellExtension {

    private ChartDTO chart;

    private ImageCell(){}

    public ImageCell(Cell delegate){
        super(delegate, ImageExtension.IMAGE_EXTENSION_NAME);
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
