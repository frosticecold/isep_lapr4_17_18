/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.lapr4.blue.s1.n1160701.conditionalExtensions;

import gwt.material.design.client.ui.MaterialLabel;
import java.util.ArrayList;
import java.util.List;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.ext.CellExtension;
import pt.isep.nsheets.shared.lapr4.blue.s1.n1160701.colorsExtensions.ConditionalFormattingCellListener;
import pt.isep.nsheets.shared.lapr4.red.s2.core.n1161018.DTO.CellDTO;
import pt.isep.nsheets.shared.services.ChartDTO;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 *
 */
public class ConditionalCell extends CellExtension {

    //public static final float RED_VALUE = 0F;
    //private String myColour = "red";
    private transient List<ConditionalFormattingCellListener> listeners
            = new ArrayList<ConditionalFormattingCellListener>();

    private ChartDTO chart;

    /**
     * Creates a Red typed cell.
     *
     * @param delegate: Cell to delagate extesion.
     */
    public ConditionalCell(Cell delegate) {
        super(delegate, ConditionalExtension.NAME);

    }

    public void addConditionalFormatting(ConditionalFormattingCellListener listener) {
        listeners.add(listener);
    }

    public void removeConditionalFormatting(ConditionalFormattingCellListener listener) {
        listeners.remove(listener);
    }

    protected void conditionalFormattingChanged() {
        for (ConditionalFormattingCellListener listerner : listeners) {
            listerner.condicionalFormattingChanged(this);
        }
    }

    @Override
    public boolean storeChart(ChartDTO dto) {

        boolean flag = false;

        if (!this.chart.equals(dto)) {
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
