package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.tests;

import java.util.Properties;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.application.LoginController;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.*;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.UserRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;

import static org.eclipse.persistence.jpa.jpql.Assert.fail;
import static org.junit.Assert.assertEquals;

/**
 * @author Rui Almeida<1160818>
 */
public class LoginControllerTest {

    @BeforeClass
    public static void setUpClass() {
        System.out.println("LoginControllerTest");
    }

    private LoginController controller;
    private User u;

    @Before
    public void setUp() {
        Properties props=new Properties();
        props.put("persistence.repositoryFactory", "pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa.JpaRepositoryFactory");
        props.put("persistence.persistenceUnit", "lapr4.NSheetsPU-test");
        props.put("javax.persistence.jdbc.url", "jdbc:h2:mem:LoginControllerTest");
        props.put("javax.persistence.schema-generation.database.action", "create");
        PersistenceSettings extensionSettings=new PersistenceSettings(props);
        PersistenceContext.setSettings(extensionSettings);
        this.controller = new LoginController();
        Username user = new Username("testUser");
        Password pass = new Password("password");
        Email email = new Email("testuser@isep.ipp.pt");
        Name name = new Name("André");
        this.u = new User(user.toString(), pass.toString(), email.toString(), RoleType.USER.toString(), name.toString(), "");
    }

//    @Test
//    public void verifyPassword() {
//        UserRepository repo = PersistenceContext.repositories().users();
//
//        try {
//            repo.save(u);
//        } catch(DataConcurrencyException | DataIntegrityViolationException ex) {
//            fail(ex.getMessage());
//        }
//
//        assertEquals("Should validate", controller.verifyPassword("testUser", "password"), true);
//    }
}