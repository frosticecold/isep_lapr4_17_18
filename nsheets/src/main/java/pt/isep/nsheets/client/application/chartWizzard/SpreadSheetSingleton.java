/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.chartWizzard;

import gwt.material.design.client.ui.table.MaterialDataTable;
import pt.isep.nsheets.client.application.workbook.WorkbookView;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.Workbook;

/**
 *
 * @author pedro
 */
public class SpreadSheetSingleton {

    public Integer[][] ssheet;
    public Workbook wb;
    public static SpreadSheetSingleton instance;

    private SpreadSheetSingleton() {

    }

    public static SpreadSheetSingleton getInstance() {

        if (instance == null) {

            instance = new SpreadSheetSingleton();
        }

        return instance;

    }

    public Spreadsheet getSheet() {

        if (wb == null) {
            return null;
        }

        return wb.getSpreadsheet(0);
    }
}
