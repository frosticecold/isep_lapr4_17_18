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
 * YellowSpreadSheetTest.java
 *
 * @autor: David Blanquett<1161018@isep.ipp.pt>
 * @date: 04/06/2018
 */
public class YellowSpreadSheetTest {

    Spreadsheet c;

    @Before
    public void setUp() throws Exception {

        c = new SpreadsheetImpl();

    }

    @Test
    public void YellowSpreadSheetConstructorTest(){
        System.out.println("Yellow Cell Contructor");


        YellowSpreadSheet yC = new YellowSpreadSheet(c);

        assertEquals(yC.getWorkbook(), c.getWorkbook());


    }


    @After
    public void tearDown() throws Exception {
        c= null;
    }
}