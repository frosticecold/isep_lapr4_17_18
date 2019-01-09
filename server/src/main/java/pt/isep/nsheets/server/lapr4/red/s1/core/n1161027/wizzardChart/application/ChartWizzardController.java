/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.red.s1.core.n1161027.wizzardChart.application;

import pt.isep.nsheets.shared.services.BarChartCreatorDTO;

/**
 *
 * @author pedro
 */
public class ChartWizzardController {

    public String getName(BarChartCreatorDTO dto) {
        return dto.getName();
    }

    public boolean LabelOnRow(BarChartCreatorDTO dto) {
        return dto.isIsRowLabel();
    }
}
