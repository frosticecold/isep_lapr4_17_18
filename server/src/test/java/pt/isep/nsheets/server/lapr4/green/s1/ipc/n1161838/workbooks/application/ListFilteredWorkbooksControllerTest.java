/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161838.workbooks.application;

import java.util.ArrayList;
import org.junit.*;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author jpfr8
 */
public class ListFilteredWorkbooksControllerTest {

    public ListFilteredWorkbooksControllerTest() {
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
     * Test of findFilteredWorkbookDescriptions method, of class ListFilteredWorkbooksController.
     */
    @Test
    public void testFindFilteredWorkbookDescriptions() {

        ListFilteredWorkbooksController controller = new ListFilteredWorkbooksController();

        ArrayList<WorkbookDescriptionDTO> list = new ArrayList<>();
        Workbook wb = new Workbook();

        WorkbookDescriptionDTO w = new WorkbookDescriptionDTO("LUL", "AHAHA","ISTO FOI ACRESCENTADO AQUI TOPKEK");
        WorkbookDescriptionDTO w2 = new WorkbookDescriptionDTO("LUL", "AHAHA","ISTO FOI ACRESCENTADO AQUI TOPKEK");
        WorkbookDescriptionDTO w3 = new WorkbookDescriptionDTO("LUL", "AHAHA","ISTO FOI ACRESCENTADO AQUI TOPKEK");

        list.add(w);
        list.add(w2);
        list.add(w3);

        String filter = "LUL";

        ArrayList<WorkbookDescriptionDTO> result = controller.findFilteredWorkbookDescriptions(list, filter);
        assertEquals(list,result);
    }

}
