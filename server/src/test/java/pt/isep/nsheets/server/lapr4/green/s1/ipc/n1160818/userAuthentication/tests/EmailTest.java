package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.tests;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Email;

import static org.junit.Assert.*;

/**
 * @author Rui Almeida<1160818>
 */
public class EmailTest {

    @BeforeClass
    public static void setUpClass() {
        System.out.println("EmailTest");
    }

    private Email u;

    @Before
    public void setUp() throws Exception {
        this.u = new Email("test");
    }


    @Test
    public void equals() {
        Email other = new Email("test");
        assertEquals("Should match", u.equals(other), true);
        other = new Email("err");
        assertEquals("Should not match", u.equals(other), false);
    }

}