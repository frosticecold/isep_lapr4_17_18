package pt.isep.nsheets.client.application.Lapr.Red.n1161018.Search;

import org.h2.value.Value;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.SpreadsheetImpl;
import pt.isep.nsheets.shared.core.Workbook;

import static org.junit.Assert.*;

/**
 * OptionServiceTest.java
 *
 * @autor: David Blanquett<1161018@isep.ipp.pt>
 * @date: 10/06/2018
 */
public class OptionServiceTest {

    OptionService instance;
    Spreadsheet sh_test;
    Workbook wb;

    @Before
    public void setUp() throws Exception {

        instance = OptionService.getInstance();

        // Test the initialization of an Workbook
        String contents[][][] = {{ // first spreadsheet
                {"10", "-9", "8", "7", "1", "2", "3"}, {"8", "7", "6", "5", "4", "3", "2"},
                {"1", "2", "3", "4", "5", "6", "7"},}};

        wb = new Workbook(contents);
        sh_test = wb.getSpreadsheet(0);

        String contentsss[][] = { // second spreadsheet
                /*    A    B    C     D   E    F     G */
                {"1", "-1", "1", "1", "2", "2", "3"}, //1
                {"5", "3", "1", "-1", "-4", "3", "2"}, // 2
                {"1", "-2", "3", "-2", "5", "-1", "-2"}};           // 3

        sh_test.getWorkbook().addSpreadsheet(contentsss);



    }

    @After
    public void tearDown() throws Exception {

        instance = null;
    }

    @Test
    public void getRealCol() {

        int exptdNumber = 3;

        instance.setnCol(exptdNumber);

        assertTrue(instance.getRealCol() == exptdNumber);

    }

    @Test
    public void getInstance() {


        assertTrue( instance == OptionService.getInstance()); // its a singleton
    }

    @Test
    public void getnRow() {

        int exptdRow = 3;

        instance.setnRow(exptdRow);

        assertTrue(instance.getnRow() == exptdRow);

    }

    @Test
    public void setnRow() {

        int exptdRow = 3;

        instance.setnRow(exptdRow);

        assertTrue(instance.getnRow() == exptdRow);


    }

    @Test
    public void getnCol() {


        int exptdNumber = 1;

        instance.setnCol(exptdNumber);

        assertTrue(instance.getRealCol() != (exptdNumber+1));

    }

    @Test
    public void setnCol() {

        int exptdNumber = 3;

        instance.setnCol(exptdNumber);

        assertTrue(instance.getRealCol() == exptdNumber);
    }

    @Test
    public void getMySS() {


        instance.setMySS(sh_test);

        assertEquals(sh_test, instance.getMySS());
    }

    @Test
    public void setMySS() {

        instance.setMySS(sh_test);

        assertEquals(sh_test, instance.getMySS());
    }

    @Test
    public void isYesToAll() {

        instance.setYesToAll(false);

        assertFalse(instance.isYesToAll());
    }

    @Test
    public void setYesToAll() {

        instance.setYesToAll(false);

        assertFalse(instance.isYesToAll());
    }

    @Test
    public void changeMyFilter() {

        instance.changeMyFilter(pt.isep.nsheets.shared.core.Value.Type.BOOLEAN);

        assertTrue( instance.currentSelectedType() == pt.isep.nsheets.shared.core.Value.Type.BOOLEAN);
    }

    @Test
    public void currentSelectedType() {

        instance.changeMyFilter(pt.isep.nsheets.shared.core.Value.Type.BOOLEAN);

        assertTrue( instance.currentSelectedType() == pt.isep.nsheets.shared.core.Value.Type.BOOLEAN);

    }


    @Test
    public void getSheetIndex() {

        assertTrue(instance.getSheetIndex() != 0);
    }
}