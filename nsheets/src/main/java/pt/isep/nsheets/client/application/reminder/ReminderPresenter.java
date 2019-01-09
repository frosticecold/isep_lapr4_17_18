package pt.isep.nsheets.client.application.reminder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import gwt.material.design.client.ui.MaterialToast;
import org.omg.CORBA.Current;
import pt.isep.nsheets.client.CurrentUser;
import pt.isep.nsheets.client.application.ApplicationPresenter;
import pt.isep.nsheets.client.event.SetPageTitleEvent;
import pt.isep.nsheets.client.place.NameTokens;
import pt.isep.nsheets.shared.services.ReminderDTO;
import pt.isep.nsheets.shared.services.ReminderService;
import pt.isep.nsheets.shared.services.ReminderServiceAsync;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Rui Almeida<1160818>
 */
public class ReminderPresenter extends Presenter<ReminderPresenter.MyView, ReminderPresenter.MyProxy> implements ReminderUiHandlers {


    @ProxyStandard
    @NameToken(NameTokens.reminder)
    interface MyProxy extends ProxyPlace<ReminderPresenter> {
    }

    interface MyView extends View, HasUiHandlers<ReminderPresenter> {
        void setContents(ArrayList<ReminderDTO> results);
    }

    private ReminderServiceAsync reminderService;

    @Inject
    ReminderPresenter(EventBus eventBus,
                      ReminderPresenter.MyView view,
                      ReminderPresenter.MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_CONTENT);

        getView().setUiHandlers(this);

    }


    /**
     * Refreshes the page and adds/deletes workbooks according to the db
     */
    @Override
    public void refreshView() {
        reminderService = GWT.create(ReminderService.class);


        AsyncCallback<ArrayList<ReminderDTO>> callback = new AsyncCallback<ArrayList<ReminderDTO>>() {
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error calling reminder service! " + caught.getMessage());
            }

            public void onSuccess(ArrayList<ReminderDTO> result) {
                getView().setContents(result);
            }
        };


        reminderService.reminders(CurrentUser.username(), callback);
    }

    /**
     * Creates a new Reminder
     *
     * @param name        - name of the reminder
     * @param description - description of the reminder
     * @param date        - day of the reminder
     * @param timestamp   - time of the reminder
     */
    @Override
    public void onCreate(String name, String description, Date date, String timestamp) {

        String split[] = timestamp.trim().split(":");
        int hours = Integer.parseInt(split[0]);
        int minutes = Integer.parseInt(split[1]);
        date.setHours(hours);
        date.setMinutes(minutes);
        ReminderDTO dto = new ReminderDTO(name, description, CurrentUser.username(), date, true);

        reminderService = GWT.create(ReminderService.class);

        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error calling reminder service! " + caught.getMessage());
            }

            public void onSuccess(Boolean result) {
                if (result.booleanValue()) {
                    MaterialToast.fireToast("Creating reminder..");
                } else {
                    MaterialToast.fireToast("Could not add new reminder! A reminder with that title already exists.");
                }
                refreshView();
            }
        };

        reminderService.createReminder(dto, callback);
    }

    @Override
    protected void onReveal() {
        super.onReveal();
        SetPageTitleEvent.fire("Reminders", "Manage your reminders!", "", "", this);
        refreshView();
    }
}
