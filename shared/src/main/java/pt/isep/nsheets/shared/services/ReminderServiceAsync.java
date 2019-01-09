package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;

/**
 * @author Rui Almeida<1160818>
 */
public interface ReminderServiceAsync {

    /**
     * Gets all the reminders saved in the database given an username
     * @return
     */
    void reminders(String username, AsyncCallback<ArrayList<ReminderDTO>> async);

    /**
     * Edits a reminder
     * @param reminder
     * @param async
     */
    void editReminder(ReminderDTO previous, ReminderDTO reminder, AsyncCallback<Boolean> async);

    /**
     * Deletes a reminder
     * @param reminder
     * @param async
     */
    void deleteReminder(ReminderDTO reminder, AsyncCallback<Boolean> async);


    /**
     * Creates a reminder
     * @param reminder
     * @param async
     */
    void createReminder(ReminderDTO reminder, AsyncCallback<Boolean> async);
}
