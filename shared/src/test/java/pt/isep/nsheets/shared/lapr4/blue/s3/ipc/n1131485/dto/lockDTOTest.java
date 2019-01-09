package pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1131485.dto;

import org.junit.*;
import pt.isep.nsheets.shared.core.Address;
import pt.isep.nsheets.shared.core.Workbook;

import static org.junit.Assert.*;

public class lockDTOTest {

    /**
     * Main instance for the tests
     */
    private static lockDTO instance;

    public lockDTOTest() {
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
     * Ensure that a well built dto is created
     */
    @Test
    public void ensureDTOIsValid() {

        System.out.println("ensureDTOIsValid");
        Workbook wb1 = new Workbook();
        int cellRow = 0;
        int cellColumn = 0;
        this.instance = new lockDTO(wb1, "title", cellRow, cellColumn);
        assertNotNull(instance);
        assertTrue(instance.equals(instance));
    }


    /**
     * Check if returns the right cell, comparision by address
     */
    @Test
    public void ensureCorrectCell() {

        System.out.println("ensureCorrectCell");
        Address expResult = new Address(0,0);
        Address result = new Address(instance.cellColumn(),instance.cellRow());
        assertEquals(expResult, result);
    }

    /**
     * Check if returns the correct spreadsheet
     */
    @Test
    public void ensureCorrectSpreadsheet()  {
        System.out.println("ensureCorrectSpreadsheet");
        String expResult = "title";
        String result = instance.spreadTitile();
        assertEquals(expResult, result);
    }

    /**
     * Test timer which is a constant
     */
    @Test
    public void ensureTimerSet() {

        System.out.println("ensureTimerSet");
        assertEquals(10, instance.timer());
    }

    /**
     * Ensure that equals fails when comparing different lockDTO´s
     */
    @Test
    public void ensureEqualsFalse() {

        System.out.println("ensureEqualsFalse");
        Workbook wb = new Workbook();
        lockDTO lo1 = new lockDTO(wb,"title",1,0);
        assertFalse(instance.equals(lo1));
    }

    /**
     * Ensure that a lock with the same cell is equal to this one , while using the methd equals
     */
    @Test
    public void ensureEqualsTrue() {

        System.out.println("ensureEqualsTrue");
        Workbook wb = new Workbook();
        lockDTO lo2 = new lockDTO(wb,"title",0,0);
        assertTrue(instance.equals(lo2));
    }
}