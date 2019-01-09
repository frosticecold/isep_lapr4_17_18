package pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1131485.domain;

import org.junit.*;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1131485.dto.lockDTO;

import static org.junit.Assert.*;

public class SessionTest {

    /**
     * Main instance for the tests
     */
    private Session instance;

    public SessionTest() {
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
     * Method to ensure creation of Session
     */
    @Test
    public void ensureSessionBuilt() {
        System.out.println("ensureSessionBuilt");
        Workbook wb1 = new Workbook();
        this.instance = new Session(wb1);
        assertNotNull(this.instance);
       assertTrue(this.instance.equals(this.instance));
    }

    /**
     * test to ensure the session is active by start
     */
    @Test
    public void isActive() {
        //because session was just created , session by default is active
        System.out.println("isActive");

        Workbook wb1 = new Workbook();
        this.instance = new Session(wb1);
        assertTrue(this.instance.isActive());
    }

    /**
     * test to ensure that locks are being added to session
     */
    @Test
    public void addNewLock() {
        System.out.println("addNewLock");
        int cellRow = 0;
        int cellColumn = 0;

        Workbook wb1 = new Workbook();
        this.instance = new Session(wb1);
        lockDTO dto = new lockDTO(wb1, "title", cellRow, cellColumn);
        this.instance.addNewLock(dto);
        assertEquals(1,this.instance.locks().size());

    }

    /**
     * test to ensure that the session is closed
     */
    @Test
    public void closeSession() {
        System.out.println("closeSession");
        Workbook wb1 = new Workbook();
        this.instance = new Session(wb1);
        this.instance.closeSession();
        assertFalse(this.instance.isActive());

    }

    /**
     * ensure that a lock is disabled
     */
    @Test
    public void disableLock() {
        System.out.println("disableLock");
        int cellRow = 0;
        int cellColumn = 0;
        Workbook wb1 = new Workbook();
        this.instance = new Session(wb1);
        lockDTO dto = new lockDTO(wb1, "title", cellRow, cellColumn);
        this.instance.addNewLock(dto);
        this.instance.disableLock(dto);
        assertFalse(this.instance.locks().get(dto));
    }
}