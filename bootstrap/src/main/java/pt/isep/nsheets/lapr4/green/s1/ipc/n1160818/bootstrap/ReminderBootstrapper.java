package pt.isep.nsheets.lapr4.green.s1.ipc.n1160818.bootstrap;

import com.ibm.icu.util.Calendar;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.GetPropertiesService;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.domain.Description;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.domain.Reminder;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.domain.ReminderName;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.persistence.ReminderRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;

import java.util.Date;
import java.util.logging.Logger;

/**
 * @author Rui Almeida<1160818>
 */
public class ReminderBootstrapper implements Action {
    @Override
    public boolean execute() {
        Date date = new Date(System.currentTimeMillis());

        registerReminder("Wake up sleepy head..", "Don't forget to wake up!", date, "sa");
        registerReminder("Meeting", "You got a meeting with the company!", date, "john");
        registerReminder("Dinner", "Dinner with Susan!", date, "jane");
        return true;
    }


    /**
     * Calls the persistence unit and saves a reminder in the database
     *
     * @param name        - name of the reminder
     * @param description - description of the reminder
     * @param date        - date of the reminder (timestamp)
     */
    private void registerReminder(final String name, final String description, final Date date, final String username) {

        try {
            PersistenceContext.setSettings(GetPropertiesService.getPersistenceSettings());
            ReminderRepository reminders = PersistenceContext.repositories().reminders();


            ReminderName reminderName = new ReminderName(name);
            Description desc = new Description(description);

            Reminder reminder = new Reminder(reminderName, desc, date, username, true);

            reminders.save(reminder);

        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            System.out.println("Exception: " + ex.getMessage());
            Logger.getLogger(Bootstrapper.class.getSimpleName())
                    .info("Exception during bootstrapping: assuming existing record.");
        }
    }
}
