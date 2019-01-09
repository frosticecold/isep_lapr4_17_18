package pt.isep.nsheets.server.lapr4.blue.s2.core.n1131485.chart.application;

import pt.isep.nsheets.server.lapr4.blue.s2.core.n1131485.chart.domain.BarChart;
import pt.isep.nsheets.server.lapr4.blue.s2.core.n1131485.chart.domain.Chart;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pt.isep.nsheets.server.lapr4.blue.s2.core.n1131485.chart.domain.ChartType;
import pt.isep.nsheets.server.lapr4.blue.s2.core.n1131485.chart.domain.PieChart;
import pt.isep.nsheets.shared.core.Address;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.SpreadsheetImpl;
import pt.isep.nsheets.shared.core.Workbook;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class createChartControllerTest {

    public createChartControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of display method, of class createChartController. For a barChart
     */
    @Test
    public void ensureDisplayBarChart() {
        System.out.println("ensureDisplayBarChart");
        String type = "BARCHART";
        String name = "BARCHART";
        boolean wantsRowLabel = true;
        Workbook wb1;
        Address a1 = new Address(1, 1);
        Address a2 = new Address(3, 1);
        try {

            //creating content ...
            String contents[][] = {// first spreadsheet
                {"10", "-9", "8", "7", "1", "2", "3"}, {"8", "7", "6", "5", "4", "3", "2"},
                {"1", "2", "3", "4", "5", "6", "7"}};

            String expResult[][] = {
                {"7", "6", "5"}};
            
            wb1 = new Workbook(contents);
            assertNotNull(wb1);

            Spreadsheet sh = wb1.getSpreadsheet(0);

//            System.out.println("\n\n====================CONTENT================\n");
//            for (int r = 0; r < 3; r++) {
//                for (int c = 0; c < 7; c++) {
//                    System.out.println(contents[r][c]);
//                }
//            }

            assertNotNull(wb1);

            ChartType cT = ChartType.BARCHART;

            createChartController instance = new createChartController();
          
            Chart c = instance.createChart(type, name, wantsRowLabel);
            if (c instanceof BarChart) {
                BarChart bc = (BarChart) c;
//                System.out.println("Is Barchart!");
                cT = bc.type();
            }
//            assertEquals(wb1.getSpreadsheet(0).getRowCount(), 3);
//            System.out.println("Rows confirmed");
//            assertEquals(wb1.getSpreadsheet(0).getColumnCount(), 7);
//            System.out.println("Columns confirmed");
//            assertEquals(cT, ChartType.BARCHART);
            SpreadsheetImpl sp = (SpreadsheetImpl) sh;
//            assertNotNull(sp);
//            System.out.println("sp not null");
            String[][] result = instance.display((Chart) c, a1, a2, sp);
            assertArrayEquals(expResult, result);
//            System.out.println("Test runned with sucess");
//            System.out.println("\n====================CONTENT================\n");
//            for (int r = 0; r < 1; r++) {
//                for (int l = 0; l < 3; l++) {
//                    System.out.println(expResult[r][l]);
//                }
//            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! What happened?  " + e.getClass().toString() +   " Answer : " + e.getMessage() + "\n");
        }
    }

    
    /**
     * Test of display method, of class createChartController. For a pieChart
     */
    @Test
    public void ensureDisplayPieChart() {
        System.out.println("ensureDisplayPieChart");
        String type = "PIECHART";
        String name = "PIECHART";
        boolean wantsRowLabel = true;
        Workbook wb1;
        Address a1 = new Address(1, 1);
        Address a2 = new Address(3, 1);
        try {

            //creating content ...
            String contents[][] = {// first spreadsheet
                {"10", "-9", "8", "7", "1", "2", "3"}, {"8", "7", "6", "5", "4", "3", "2"},
                {"1", "2", "3", "4", "5", "6", "7"}};

            String expResult[][] = {
                {"7", "6", "5"}};
            
            wb1 = new Workbook(contents);
            assertNotNull(wb1);

            Spreadsheet sh = wb1.getSpreadsheet(0);

//            System.out.println("\n\n====================CONTENT================\n");
//            for (int r = 0; r < 3; r++) {
//                for (int c = 0; c < 7; c++) {
//                    System.out.println(contents[r][c]);
//                }
//            }

            assertNotNull(wb1);

            ChartType cT = ChartType.PIECHART;

            createChartController instance = new createChartController();
          
            Chart c = instance.createChart(type, name, wantsRowLabel);
            if (c instanceof PieChart) {
                PieChart bc = (PieChart) c;
//                System.out.println("Is Piechart!");
                cT = bc.type();
            }
            assertEquals(wb1.getSpreadsheet(0).getRowCount(), 3);
//            System.out.println("Rows confirmed");
            assertEquals(wb1.getSpreadsheet(0).getColumnCount(), 7);
//            System.out.println("Columns confirmed");
            assertEquals(cT, ChartType.PIECHART);
            SpreadsheetImpl sp = (SpreadsheetImpl) sh;
//            assertNotNull(sp);
//            System.out.println("sp not null");
            String[][] result = instance.display((Chart) c, a1, a2, sp);
            assertArrayEquals(expResult, result);
//            System.out.println("Test runned with sucess");
//            System.out.println("\n====================CONTENT================\n");
//            for (int r = 0; r < 1; r++) {
//                for (int l = 0; l < 3; l++) {
//                    System.out.println(expResult[r][l]);
//                }
//            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! What happened?  " + e.getClass().toString() +   " Answer : " + e.getMessage() + "\n");
        }
    }
}
