package pt.isep.nsheets.shared.lapr4.red.s1.ext.n1161018.YellowExtension;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.CellImpl;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.SpreadsheetImpl;

import static org.junit.Assert.*;

/**
 * YellowExtensionTest.java
 *
 * @autor: David Blanquett<1161018@isep.ipp.pt>
 * @date: 04/06/2018
 */
public class YellowExtensionTest {

    Spreadsheet sheet;
    Cell cell;

    @Before
    public void setUp() throws Exception {
        sheet = new SpreadsheetImpl();
        cell = new CellImpl();

    }

    @After
    public void tearDown() throws Exception {

        sheet = null;
        cell = null;
    }

    @Test
    public void extendSheet() {
        System.out.println("ExtendSheet");

        YellowSpreadSheet newYellowSS = new YellowSpreadSheet(sheet);

        assertEquals(newYellowSS.getWorkbook(), new YellowSpreadSheet(sheet).getWorkbook());
    }

    @Test
    public void extendCell() {
        System.out.println("ExtendCell");

        YellowCell newYellowSS = new YellowCell(cell);

        assertEquals(newYellowSS.getDelegate(), new YellowCell(cell).getDelegate());
    }
}