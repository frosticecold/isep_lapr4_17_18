package pt.isep.nsheets.client.application.reminder;

import com.gwtplatform.mvp.client.UiHandlers;

import java.util.Date;

/**
 * @author Rui Almeida<1160818>
 */
public interface ReminderUiHandlers extends UiHandlers {

    /**
     * Refreshes the reminder page and gathers remidners from the db
     */
    void refreshView();

    /**
     * creates a new reminder
     */
    void onCreate(String name, String description, Date date, String timestamp);


}
