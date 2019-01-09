package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.ArrayList;

/**
 * @author Rui Almeida<1160818>
 */
@RemoteServiceRelativePath("reminderServices")
public interface ReminderService extends RemoteService {

    /**
     * Gets all the reminders saved in the database given an username
     * @return an arraylist with all the reminders in the db
     */
    ArrayList<ReminderDTO> reminders(String username);

    Boolean editReminder(ReminderDTO previous, ReminderDTO reminder);

    Boolean deleteReminder(ReminderDTO reminder);

    Boolean createReminder(ReminderDTO reminder);
}
