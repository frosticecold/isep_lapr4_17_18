/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.red.s1.core.n1161027.wizzardChart.domain;

import pt.isep.nsheets.shared.core.Address;
import pt.isep.nsheets.shared.core.SpreadsheetImpl;

/**
 *
 * @author pedro
 */
public interface ChartCreator {
    
    String[][] createChart(Address a1,Address a2,SpreadsheetImpl s,String[][]matrix);
}
