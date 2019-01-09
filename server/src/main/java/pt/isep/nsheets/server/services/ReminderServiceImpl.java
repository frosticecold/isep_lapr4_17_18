package pt.isep.nsheets.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.application.ReminderController;
import pt.isep.nsheets.shared.services.ReminderDTO;
import pt.isep.nsheets.shared.services.ReminderService;

import java.util.ArrayList;

/**
 * @author Rui Almeida<1160818>
 */
public class ReminderServiceImpl extends RemoteServiceServlet implements ReminderService {

    /**
     * Returns the user's list of reminders
     *
     * @param username - the user
     * @return arraylist with reminder DTO's
     */
    @Override
    public ArrayList<ReminderDTO> reminders(String username) {
        ReminderController controller = new ReminderController();
        return controller.reminders(username);
    }

    /**
     * @param reminder
     * @return
     */
    @Override
    public Boolean editReminder(ReminderDTO previous, ReminderDTO reminder) {
        ReminderController controller = new ReminderController();
        return controller.editReminder(previous, reminder);
    }

    /**
     * @param reminder
     * @return
     */
    @Override
    public Boolean deleteReminder(ReminderDTO reminder) {
        ReminderController controller = new ReminderController();
        return controller.removeReminder(reminder);
    }

    /**
     * @param reminder
     */
    @Override
    public Boolean createReminder(ReminderDTO reminder) {
        ReminderController controller = new ReminderController();
        return controller.createReminder(reminder);
    }
}
