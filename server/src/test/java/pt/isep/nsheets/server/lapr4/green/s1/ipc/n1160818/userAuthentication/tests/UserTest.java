package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.tests;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.*;

import static org.junit.Assert.*;

/**
 * @author Rui Almeida<1160818>
 */
public class UserTest {

    @BeforeClass
    public static void setUpClass() {
        System.out.println("UserTest");
    }

    private User u;

    @Before
    public void setUp() throws Exception {
        Username user = new Username("testUser");
        Password pass = new Password("password");
        Email email = new Email("testuser@isep.ipp.pt");
        Name name = new Name("André");
        this.u = new User(user.toString(), pass.toString(), email.toString(), RoleType.USER.toString(), name.toString(),"");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        System.out.println("ensureNullIsNotAllowed");

        User instance = new User(null, null, null, null, null, null);
    }

    @Test
    public void setAsAdmin() {
        u.setAsAdmin();
        assertEquals("Should be admin", u.role(), RoleType.ADMIN);
    }

    @Test
    public void setAsDefaultUser() {
        u.setAsDefaultUser();
        assertEquals("Should be default user", u.role(), RoleType.USER);
    }

    @Test
    public void role() {
        assertEquals("Should return a role enum", u.role(), RoleType.USER);
    }

    @Test
    public void verifyPassword() {

        Password instance1 = new Password("Password");
        Password instance2 = new Password("password");
        Password instance3 = new Password("password1");
        Password instance4 = new Password("completelyUnrelated");

        assertEquals("Should not match if casing is different", u.verifyPassword(instance1.toString()), false);
        assertEquals("Should match", u.verifyPassword(instance2.toString()), true);
        assertEquals("Should not match if casing is correct but there's extra characters", u.verifyPassword(instance3.toString()), false);
        assertEquals("Should not match, strings are completely unrelated", u.verifyPassword(instance4.toString()), false);
    }

    @Test
    public void sameAs() {
        Username user = new Username("testUser");
        Password pass = new Password("password");
        Email email = new Email("testuser@isep.ipp.pt");
        Name name = new Name("André");
        User instance = new User(user.toString(), pass.toString(), email.toString(), RoleType.USER.toString(), name.toString(),"");
        assertEquals("Should match as attributes are the same", u.sameAs(instance), true);

        user = new Username("differentUsername");
        instance = new User(user.toString(), pass.toString(), email.toString(), RoleType.USER.toString(), name.toString(),"");
        assertEquals("Should not match as attribute username is different", u.sameAs(instance), false);
    }


    @Test
    public void ensureContactIsAdded(){
        Email e = new Email("gandacontacto@isep.ipp.pt");

        u.addContact(e);
        assertEquals(!u.contactsList().isEmpty(), true);
    }

    @Test
    public void ensureInvitationIsAdded(){
        Email e = new Email("top10animeinvitations@isep.ipp.pt");

        u.receiveInvitation(e);
        assertEquals(!u.invitations().isEmpty(), true);
    }

    @Test
    public void ensureUserIsBlocked(){
        Email e = new Email("blockedeheh@isep.ipp.pt");
        u.blockUser(e);
        assertEquals(!u.blockedUsers().isEmpty(), true);
    }
}