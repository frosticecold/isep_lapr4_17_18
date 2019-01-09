package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.tests;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Password;

import static org.junit.Assert.*;

/**
 * @author Rui Almeida<1160818>
 */
public class PasswordTest {

    @BeforeClass
    public static void setUpClass() {
        System.out.println("PasswordTest");
    }

    private Password u;

    @Before
    public void setUp() throws Exception {
        this.u = new Password("test");
    }


    @Test
    public void equals() {
        Password other = new Password("test");
        assertEquals("Should match", u.equals(other), true);
        other = new Password("err");
        assertEquals("Should not match", u.equals(other), false);
    }

}