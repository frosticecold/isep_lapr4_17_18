package pt.isep.nsheets.client.application.reminder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import gwt.material.design.addins.client.window.MaterialWindow;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.*;
import pt.isep.nsheets.client.CurrentUser;
import pt.isep.nsheets.shared.services.ReminderDTO;
import pt.isep.nsheets.shared.services.ReminderService;
import pt.isep.nsheets.shared.services.ReminderServiceAsync;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Rui Almeida<1160818>
 */
public class ReminderView extends ViewWithUiHandlers<ReminderPresenter> implements ReminderPresenter.MyView {

    @UiField
    HTMLPanel htmlPanel;

    @UiField
    MaterialButton createReminder;

    @UiField
    MaterialWindow windowCreate;

    @UiField
    MaterialButton save;

    @UiField
    MaterialTextBox title;

    @UiField
    MaterialTextBox desc;

    @UiField
    MaterialTextBox time;

    @UiField
    MaterialDatePicker date;

    @UiField
    MaterialWindow windowEdit;

    @UiField
    MaterialButton saveEdit;

    @UiField
    MaterialTextBox titleEdit;

    @UiField
    MaterialTextBox descEdit;

    @UiField
    MaterialTextBox timeEdit;

    @UiField
    MaterialDatePicker dateEdit;

    @UiField
    MaterialButton cancelCreate;

    @UiField
    MaterialButton cancelEdit;

    private String name;
    private ReminderDTO previousReminder;
    private String currentTitle;
    private String currentDescr;
    private Date currentDate;
    private String currentTime;
    private Boolean isActive;

    interface Binder extends UiBinder<Widget, ReminderView> {
    }

    @Inject
    ReminderView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    /**
     * Creates a new reminder, sends parameters to the presenter so it can process them.
     *
     * @param event - the click event
     */
    @UiHandler("createReminder")
    public void openWindow(ClickEvent event) {
        windowCreate.open();
    }

    @UiHandler("cancelCreate")
    public void onCancelCreate(ClickEvent event) {
        windowCreate.close();
    }

    @UiHandler("cancelEdit")
    public void onCancelEdit(ClickEvent event) {
        windowEdit.close();
    }

    /**
     * Edits a reminder
     *
     * @param event
     */
    @UiHandler("saveEdit")
    public void onSaveEdit(ClickEvent event) {
        if (!currentTitle.equals(titleEdit.getText())
                || !currentDescr.equals(descEdit.getText())
                || !currentDate.equals(dateEdit.getDate())
                || !currentTime.equals(timeEdit.getText())
                && isActive != null) {

            String split[] = timeEdit.getText().trim().split(":");

            if (split[0].matches("\\d")) timeEdit.setText("0" + split[0] + ":" + split[1]);
            if (split[1].matches("\\d")) timeEdit.setText(split[0] + ":" + "0" + split[1]);

            if (!timeEdit.getText().matches("\\d\\d:\\d\\d")) {

                MaterialToast.fireToast("Time is in wrong format! Please re-check your input.");

            } else if (titleEdit.getText().isEmpty()
                    || descEdit.getText().isEmpty()
                    || dateEdit.getValue() == null
                    || timeEdit.getText().isEmpty()) {

                MaterialToast.fireToast("Please re-check your input - there are null/empty fields!");

            } else {

                Date date = dateEdit.getDate();
                int hours = Integer.parseInt(split[0]);
                int minutes = Integer.parseInt(split[1]);
                date.setHours(hours);
                date.setMinutes(minutes);

                ReminderDTO editedReminder = new ReminderDTO(titleEdit.getText(), descEdit.getText(), CurrentUser.username(), date, isActive.booleanValue());

                ReminderServiceAsync service = GWT.create(ReminderService.class);

                AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
                    public void onFailure(Throwable caught) {
                        MaterialToast.fireToast("Error calling reminder service! " + caught.getMessage());
                    }

                    public void onSuccess(Boolean result) {
                        if (result.booleanValue()) {
                            MaterialToast.fireToast("Reminder edited successfully.");
                        } else {
                            MaterialToast.fireToast("Could not edit reminder!");
                        }
                        getUiHandlers().refreshView();
                    }
                };

                service.editReminder(previousReminder, editedReminder, callback);
                windowEdit.close();
            }
        }
    }

    private void onEdit(ClickEvent event) {
        windowEdit.open();

        ReminderServiceAsync service = GWT.create(ReminderService.class);

        AsyncCallback<ArrayList<ReminderDTO>> callback = new AsyncCallback<ArrayList<ReminderDTO>>() {
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error calling reminder service! " + caught.getMessage());
            }

            public void onSuccess(ArrayList<ReminderDTO> result) {
                for (ReminderDTO reminder : result) {
                    if (reminder.getName().equals(name)) {
                        titleEdit.setText(reminder.getName());
                        descEdit.setText(reminder.getDescription());
                        dateEdit.setDate(reminder.getDate());
                        timeEdit.setText(reminder.getDate().getHours() + ":" + reminder.getDate().getMinutes());
                        isActive = reminder.isActive();
                        currentTitle = titleEdit.getText();
                        currentDescr = descEdit.getText();
                        currentDate = dateEdit.getDate();
                        currentTime = timeEdit.getText();
                    }
                }
            }
        };

        service.reminders(CurrentUser.username(), callback);
    }

    @UiHandler("save")
    public void onCreate(ClickEvent event) {
        if (title.getText().isEmpty() || desc.getText().isEmpty() || time.getText().isEmpty() || date.getValue() == null) {
            if (title.getText().isEmpty()) MaterialToast.fireToast("Please enter a title for the reminder!");
            if (desc.getText().isEmpty()) MaterialToast.fireToast("Please enter a description for the reminder!");
            if (time.getText().isEmpty()) MaterialToast.fireToast("Please enter a time of day for the reminder!");
            if (date.getValue() == null) MaterialToast.fireToast("Please enter a date for the reminder!");
        } else if (!time.getText().matches("\\d\\d:\\d\\d")) {
            MaterialToast.fireToast("Time is in wrong format! Please re-check your input.");
        } else {
            String name = title.getText();
            String description = desc.getText();
            Date day = date.getDate();
            String timestamp = time.getText();
            getUiHandlers().onCreate(name, description, day, timestamp);
            title.clear();
            desc.clear();
            time.clear();
            date.clear();
            windowCreate.close();
        }
    }

    /**
     * Creates a material card for each reminder DTO in the array
     *
     * @param reminders - list of the user's reminders
     */
    @Override
    public void setContents(ArrayList<ReminderDTO> reminders) {
        int colCount = 1;

        MaterialRow row = null;

        htmlPanel.clear();

        //Creates a card for each reminder in the array
        for (ReminderDTO reminder : reminders) {
            MaterialCard card = createCard(reminder);

            if (colCount == 1) {
                row = new MaterialRow();
                htmlPanel.add(row);
                ++colCount;
                if (colCount >= 4) {
                    colCount = 1;
                }
            }

            MaterialColumn col = new MaterialColumn();
            col.setGrid("l4");
            row.add(col);

            col.add(card);
        }
    }


    /**
     * Generates one material card for a ReminderDTO
     *
     * @param reminder - the reminder
     * @return the material card
     */
    private MaterialCard createCard(ReminderDTO reminder) {
        //Creates the material card
        MaterialCard card = new MaterialCard();

        //Sets the color of the card
        card.setBackgroundColor(Color.BLUE_GREY);

        //Card Content that contains the material buttons
        MaterialCardContent buttonContent = new MaterialCardContent();

        //Remove card button
        MaterialButton remove = new MaterialButton();
        remove.setBackgroundColor(Color.GREY_DARKEN_3);
        remove.setIconType(IconType.REMOVE);

        //Active/Deactivate button
        MaterialButton setActive = new MaterialButton();
        setActive.setIconType(IconType.NOTIFICATIONS_ACTIVE);
        if (reminder.isActive()) {
            setActive.setBackgroundColor(Color.GREEN);
        } else {
            setActive.setBackgroundColor(Color.GREY_DARKEN_3);
        }

        //Edit card button
        MaterialButton edit = new MaterialButton();
        edit.setBackgroundColor(Color.GREY_DARKEN_3);
        edit.setIconType(IconType.EDIT);

        /**
         * Activate/Deactivate click event
         */
        setActive.addClickHandler(event -> {
            ReminderServiceAsync service = GWT.create(ReminderService.class);

            ReminderDTO newReminder = new ReminderDTO(reminder.getName(), reminder.getDescription(), reminder.getUsername(), reminder.getDate(), reminder.isActive());

            if (newReminder.isActive()) {
                newReminder.setInactive();
            } else {
                newReminder.setActive();
            }

            AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
                public void onFailure(Throwable caught) {
                    MaterialToast.fireToast("Error calling reminder service! " + caught.getMessage());
                }

                public void onSuccess(Boolean result) {
                    if (result.booleanValue()) {
                        String toastMessage;
                        if (newReminder.isActive()) {
                            toastMessage = "The reminder has been reactivated.";
                        } else {
                            toastMessage = "The reminder has been silenced.";
                        }
                        MaterialToast.fireToast(toastMessage);
                    } else {
                        MaterialToast.fireToast("Could not edit reminder!");
                    }
                    getUiHandlers().refreshView();
                }
            };

            service.editReminder(reminder, newReminder, callback);
        });

        /**
         * Edit button on click event
         */
        edit.addClickHandler(event -> {
            this.name = reminder.getName();
            this.previousReminder = reminder;
            onEdit(event);
        });

        /**
         * Remove button on click event
         */
        remove.addClickHandler(event -> {
            ReminderServiceAsync service = GWT.create(ReminderService.class);

            AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
                public void onFailure(Throwable caught) {
                    MaterialToast.fireToast("Error calling reminder service! " + caught.getMessage());
                }

                public void onSuccess(Boolean result) {
                    MaterialToast.fireToast("Removing reminder..");
                    getUiHandlers().refreshView();
                }
            };

            service.deleteReminder(reminder, callback);
        });

        //Add the buttons to the card content
        buttonContent.add(edit);
        buttonContent.add(remove);
        buttonContent.add(setActive);

        //Add the buttons to the material card
        card.add(buttonContent);

        card.add(getReminderInfoAsCard(reminder));

        return card;
    }


    /**
     * Creates the 'core' information of the card, displaying all the reminder information
     *
     * @param reminder - the reminder to display
     * @return a material card content with the
     */
    private MaterialCardContent getReminderInfoAsCard(ReminderDTO reminder) {
        MaterialCardContent content = new MaterialCardContent();
        content.setTextColor(Color.WHITE);

        //Card header
        MaterialCardTitle title = new MaterialCardTitle();
        title.setText(reminder.getName());
        title.setTextColor(Color.WHITE);
        title.setIconType(IconType.MENU);
        title.setIconPosition(IconPosition.RIGHT);

        //The description
        MaterialLabel description = new MaterialLabel(reminder.getDescription());
        description.setTextColor(Color.WHITE);

        //The date
        MaterialLabel dueDate = new MaterialLabel(String.valueOf(reminder.getDate()));

        content.add(title);
        content.add(description);
        content.add(dueDate);

        return content;
    }

}
