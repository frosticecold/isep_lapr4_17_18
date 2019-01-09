package pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.tests;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.application.ReminderController;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.domain.Description;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.domain.Reminder;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.domain.ReminderName;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.persistence.ReminderRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;
import pt.isep.nsheets.shared.services.ReminderDTO;

import java.util.Date;
import java.util.Properties;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

/**
 * @author Rui Almeida<1160818>
 */
public class ReminderControllerTest {

    private ReminderController controller;
    private Reminder reminder;

    @Before
    public void setUp() {
        Properties props = new Properties();
        props.put("persistence.repositoryFactory", "pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa.JpaRepositoryFactory");
        props.put("persistence.persistenceUnit", "lapr4.NSheetsPU-test");
        props.put("javax.persistence.jdbc.url", "jdbc:h2:mem:ReminderControllerTest");
        props.put("javax.persistence.schema-generation.database.action", "create");
        PersistenceSettings extensionSettings = new PersistenceSettings(props);
        PersistenceContext.setSettings(extensionSettings);
        controller = new ReminderController();
        reminder = new Reminder(new ReminderName("Dinner"), new Description("Dinner @ 8 with Susan"), new Date(System.currentTimeMillis()), "sa", true);
    }

//    @Test
//    public void testReminders() {
//        ReminderRepository repo = PersistenceContext.repositories().reminders();
//
//        try {
//            repo.save(reminder);
//        } catch (DataConcurrencyException | DataIntegrityViolationException e) {
//            fail(e.getMessage());
//        }
//
//        for (ReminderDTO r : controller.reminders("sa")) {
//            Reminder fromDTO = new Reminder(new ReminderName(r.getName()), new Description(r.getDescription()), r.getDate(), r.getUsername(), r.isActive());
//            assertTrue("Should be true", fromDTO.sameAs(r));
//        }
//
//    }
//
//    @Test
//    public void testRemoveReminder() {
//        ReminderRepository repo = PersistenceContext.repositories().reminders();
//
//        try {
//            repo.save(reminder);
//        } catch (DataConcurrencyException | DataIntegrityViolationException e) {
//            fail(e.getMessage());
//        }
//
//        assertTrue("Should return true", controller.removeReminder(reminder));
//
//    }
//
//    @Test
//    public void testCreateReminder() {
//        ReminderRepository repo = PersistenceContext.repositories().reminders();
//
//        ReminderDTO rmDTO = new ReminderDTO("testname", "testdesc", "sa", new Date(System.currentTimeMillis()), true);
//        try {
//            assertTrue("Should be able to create a reminder", controller.createReminder(rmDTO);
//        } catch (DataConcurrencyException | DataIntegrityViolationException e) {
//            fail(e.getMessage());
//        }
//
//    }
//
//    @Test
//    public void testEditReminder() {
//        ReminderRepository repo = PersistenceContext.repositories().reminders();
//
//        ReminderDTO rmDTO = new ReminderDTO("testname2", "testdesc2", "sa", new Date(System.currentTimeMillis()), true);
//        try {
//
//            controller.createReminder(rmDTO);
//            ReminderDTO newRmDTO = new ReminderDTO("testname3", "testdesc2", "sa", new Date(System.currentTimeMillis()), true);
//
//            assertTrue("Should be able to edit", rmDTO, newRmDTO);
//
//        } catch (DataConcurrencyException | DataIntegrityViolationException e) {
//            fail(e.getMessage());
//        }
//    }
}