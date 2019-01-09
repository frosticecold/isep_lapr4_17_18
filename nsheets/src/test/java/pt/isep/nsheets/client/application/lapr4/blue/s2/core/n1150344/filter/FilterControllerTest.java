/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.lapr4.blue.s2.core.n1150344.filter;

import java.util.HashSet;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;
import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaCompilationException;

/**
 *
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */
public class FilterControllerTest {

    FilterController controller;
    Workbook wb;
    Spreadsheet sheet;

    @Before
    public void setUp() throws Exception {
        controller = new FilterController();
        String contents[][] = { // first spreadsheet
            {"10", "-9", "8", "7", "1", "2", "3"},
            {"8", "7", "6", "5", "4", "3", "2"},
            {"1", "2", "3", "4", "5", "6", "7"}};

        wb = new Workbook(contents);
        sheet = wb.getSpreadsheet(0);
    }

    @After
    public void tearDown() {
        controller = null;
        wb = null;
        sheet = null;
    }

    public FilterControllerTest() {
    }

    /**
     * Test of isValidColumn method, of class FilterController.
     */
    @Test
    public void testIsValidColumn() {
        assertTrue(controller.isValidColumn("A1", "B3", "A"));
        assertTrue(controller.isValidColumn("A1", "C4", "B"));
        assertFalse(controller.isValidColumn("A1", "C3", "D"));
    }

    /**
     * Test of filter method, of class FilterController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFilter() throws Exception {
        HashSet<Integer> rowsToHide = new HashSet<>();
        controller.filter("A1", "B3", "A", sheet, "=IF(_filter<10;\"true\";\"false\")", rowsToHide);
        assertTrue(rowsToHide.contains(0));
        
        rowsToHide.clear();
        
        controller.filter("A1", "B3", "A", sheet, "=IF(_filter<3;\"true\";\"false\")", rowsToHide);
        assertTrue(rowsToHide.contains(0) && rowsToHide.contains(1));
    }
    
    @Test(expected = IllegalValueTypeException.class)
    public void testFailFormula() throws FormulaCompilationException, IllegalValueTypeException {
        HashSet<Integer> rowsToHide = new HashSet<>();
        controller.filter("A1", "B3", "A", sheet, "IF(_filter<10)", rowsToHide);    // Invalid formula
    }
}
