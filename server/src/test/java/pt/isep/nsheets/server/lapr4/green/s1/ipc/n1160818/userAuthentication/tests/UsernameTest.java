package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.tests;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Username;

import static org.junit.Assert.*;

/**
 * @author Rui Almeida<1160818>
 */
public class UsernameTest {

    @BeforeClass
    public static void setUpClass() {
        System.out.println("UsernameTest");
    }

    private Username u;

    @Before
    public void setUp() throws Exception {
        this.u = new Username("test");
    }


    @Test
    public void equals() {
        Username other = new Username("test");
        assertEquals("Should match", u.equals(other), true);
        other = new Username("err");
        assertEquals("Should not match", u.equals(other), false);
    }

}