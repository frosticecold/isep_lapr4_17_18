package pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.application;

import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.domain.Description;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.domain.Reminder;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.domain.ReminderName;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.persistence.ReminderRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.shared.services.ReminderDTO;

import java.util.ArrayList;

/**
 * @author Rui Almeida<1160818>
 */
public class ReminderController implements Controller {

    /**
     * Searches the database and returns a list of the user's reminders
     * @param username - the user to search
     * @return the reminders as DTO
     */
    public ArrayList<ReminderDTO> reminders(String username) {

        ReminderRepository repo = PersistenceContext.repositories().reminders();

        ArrayList<ReminderDTO> remindersDTO = new ArrayList<>();

        for (Reminder reminder : repo.findRemindersByUser(username)) {
            remindersDTO.add(reminder.toDTO());
        }

        return remindersDTO;
    }

    /**
     * Removes a reminder from the databse
     * @param reminderDTO - the reminder to remove
     * @return
     */
    public Boolean removeReminder(ReminderDTO reminderDTO) {
        ReminderRepository repo = PersistenceContext.repositories().reminders();
        Reminder reminder = repo.findReminderByName(reminderDTO.getName(), reminderDTO.getUsername());
        repo.delete(reminder);
        return true;
    }

    /**
     * Creates a new reminder
     * @param reminderDTO - the new reminder to add
     * @return true if the reminder was added with success, false if not
     */
    public Boolean createReminder(ReminderDTO reminderDTO) {
        ReminderRepository repo = PersistenceContext.repositories().reminders();

        if (repo.findReminderByName(reminderDTO.getName(), reminderDTO.getUsername()) != null) {
            return false;
        }

        ReminderName rname = new ReminderName(reminderDTO.getName());
        Description  desc  = new Description(reminderDTO.getDescription());
        Reminder reminder = new Reminder(rname, desc, reminderDTO.getDate(), reminderDTO.getUsername(), reminderDTO.isActive());

        try {
            repo.save(reminder);
        } catch (DataConcurrencyException | DataIntegrityViolationException e) {
            return false;
        }

        return true;
    }

    /**
     * Edits a reminder
     * @param previous - the reminder to remove
     * @param reminder - updated reminder to add
     * @return true if edit was successful, false if not
     */
    public Boolean editReminder(ReminderDTO previous, ReminderDTO reminder) {
        ReminderRepository repo = PersistenceContext.repositories().reminders();
        Reminder toRemove = repo.findReminderByName(previous.getName(), previous.getUsername());
        repo.delete(toRemove);

        ReminderName rname = new ReminderName(reminder.getName());
        Description  desc  = new Description(reminder.getDescription());
        Reminder toAdd = new Reminder(rname, desc, reminder.getDate(), reminder.getUsername(), reminder.isActive());
        try {
            repo.save(toAdd);
        } catch (DataConcurrencyException | DataIntegrityViolationException e) {
            return false;
        }
        return true;
    }
}















