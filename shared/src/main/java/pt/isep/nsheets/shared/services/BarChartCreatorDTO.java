/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

/**
 *
 * @author pedro
 */
@SuppressWarnings("serial")
public class BarChartCreatorDTO {

    private boolean isRowLabel;
    private boolean DataPlace;
    private String name;
    private String[] labelName;

    public BarChartCreatorDTO(String name, boolean isFirstRowOrColumnLabel) {
        this.name = name;
        this.isRowLabel = isFirstRowOrColumnLabel;
    }

    public boolean isIsRowLabel() {
        return isRowLabel;
    }

    public boolean isDataPlace() {
        return DataPlace;
    }

    public String[] getLabelName() {
        return labelName;
    }

    public String getName() {
        return name;
    }
}
