/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.lapr4.green.s3.core.n1160911.CommentsExtension;

import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.ext.CellExtension;
import pt.isep.nsheets.shared.lapr4.red.s2.core.n1161018.DTO.CellDTO;
import pt.isep.nsheets.shared.services.ChartDTO;

/**
 *
 * @author Toshiba
 */
public class CommentCell extends CellExtension {

    private ChartDTO chart;

    private CommentCell(){}

    public CommentCell(Cell delegate){
        super(delegate, CommentExtension.COMMENT_EXTENSION_NAME);
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