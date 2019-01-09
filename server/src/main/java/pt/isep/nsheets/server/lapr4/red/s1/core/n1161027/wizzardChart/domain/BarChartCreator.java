/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.red.s1.core.n1161027.wizzardChart.domain;

import java.util.SortedSet;
import pt.isep.nsheets.shared.core.Address;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.SpreadsheetImpl;
import pt.isep.nsheets.shared.services.BarChartCreatorDTO;

/**
 *
 * @author pedro
 */
public class BarChartCreator implements ChartCreator {

    private boolean isRowLabel;
    private boolean DataPlace;
    private String name;
    private String[] labelName;

    public BarChartCreator(String name, boolean isFirstRowOrColumnLabel) {
        this.name = name;
        this.isRowLabel = isFirstRowOrColumnLabel;
    }

    @Override
    public String[][] createChart(Address a1, Address a2, SpreadsheetImpl spreadsheet, String[][] matrix) {
        int RowEnd = a2.getRow();
        int ColumnEnd = a2.getColumn();
        int k = 0;

        matrix = new String[RowEnd][ColumnEnd];
        SortedSet<Cell> list;
        list = spreadsheet.getCells(a1, a2);

        Cell[] array = (Cell[]) list.toArray();

        if (isRowLabel) { //true
            labelName = dataInRows(ColumnEnd, array);
            k = ColumnEnd + 1;

        } else { //false
            labelName = dataInColumns(RowEnd, array);
            k = RowEnd + 1;
        }

        for (int i = 0; i < RowEnd; i++) {
            for (int j = 0; j < ColumnEnd; j++) {
                matrix[i][j] = array[k].toString();
                k++;
            }
        }
        return matrix;
    }

    private String[] dataInRows(int ColumnEnd, Cell[] array) {
        String info[] = null;
        for (int i = 0; i < ColumnEnd + 1; i++) {
            info[i] = array[i].toString();
        }
        return info;
    }

    private String[] dataInColumns(int RowEnd, Cell[] array) {
        String info[] = null;
        for (int i = 0; i < RowEnd + 1; i++) {
            info[i] = array[i].toString();
        }
        return info;
    }

    public String[] getLabelName() {
        return labelName;
    }

    public static BarChartCreator fromDTO(BarChartCreatorDTO dto) {
        return new BarChartCreator(dto.getName(), dto.isIsRowLabel());
    }

    public BarChartCreatorDTO toDTO() {
        return new BarChartCreatorDTO(this.name, this.isRowLabel);
    }
}
