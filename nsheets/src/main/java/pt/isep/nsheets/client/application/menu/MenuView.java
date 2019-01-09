package pt.isep.nsheets.client.application.menu;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTML;
import gwt.material.design.addins.client.window.MaterialWindow;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import gwt.material.design.client.ui.*;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import gwt.material.design.client.constants.Orientation;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialDropDown;
import gwt.material.design.client.ui.MaterialImage;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialToast;
import pt.isep.nsheets.shared.services.NoteService;
import pt.isep.nsheets.shared.services.NoteDTO;
import pt.isep.nsheets.shared.services.NoteServiceAsync;
import pt.isep.nsheets.shared.services.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pt.isep.nsheets.client.CurrentUser;

class MenuView extends ViewWithUiHandlers<MenuUiHandlers> implements MenuPresenter.MyView {

    interface Binder extends UiBinder<Widget, MenuView> {
    }

    @UiField
    MaterialNavBar navBar;
    @UiField
    MaterialSideNavPush sideNav;

    @UiField
    MaterialButton btnOpenWindow;

    @UiField
    MaterialWindow window;

    @UiField
    HTMLPanel htmlPanel;

    @UiField
    MaterialLink btnNewNote;

    @UiField
    MaterialLink btnListNote;

    @UiField
    MaterialLink loginLink;
    
    @UiField
    MaterialLink logout;

    @UiField
    MaterialLink signUpLink;

    @UiField
    MaterialLink noteMenuLink;

    @UiField
    MaterialLink userLink;

    @UiField
    MaterialImage userImg;

    /**
     * ======================
     * Rui Almeida <1160818/>
     * CORE 5.3
     * ======================
     */
    @UiField
    MaterialButton postpone;

    @UiField
    MaterialButton close;

    @UiField
    MaterialDropDown dropdownTime;

    @UiField
    MaterialWindow windowAlert;

    @UiField
    MaterialButton dropdownButton;

    @UiField
    MaterialLabel alertTitle, alertTime;

    /**
     * ======================
     * Rui Almeida <1160818/>
     * CORE 5.3
     * ======================
     */


    @Inject
    MenuView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        userLink.setVisible(false);
        userImg.setVisible(false);
        logout.setVisible(false);
        
        logout.addClickHandler((ClickEvent event) -> {
            CurrentUser.setLoggedOut();
            MenuPresenter.getPresenter().refresh();
        });
        
        HTML divider = new HTML("<br><hr  style=\"width:100%; margin-top:15px\" /><br>");
        htmlPanel.add(divider);

        // NEW NOTE
        btnNewNote.addClickHandler(handler -> window.close());

        // NEW LIST
        btnListNote.addClickHandler(handler -> window.close());

        btnOpenWindow.addClickHandler(event -> {
            window.open();

            htmlPanel.clear();
            htmlPanel.add(divider);

            NoteServiceAsync nsa = GWT.create(NoteService.class);
            AsyncCallback<List<NoteDTO>> callback = new AsyncCallback<List<NoteDTO>>() {
                @Override
                public void onFailure(Throwable caught) {
                    MaterialToast.fireToast("Error: " + caught.getMessage());
                }

                @Override
                public void onSuccess(List<NoteDTO> result) {
                    for (NoteDTO note : result) {
                        MaterialCard card = new MaterialCard();
                        card.setOrientation(Orientation.LANDSCAPE);
                        card.setStyle("color:#000000");

                        //HISTORY
                        MaterialDropDown drop = new MaterialDropDown();
                        MaterialButton button = new MaterialButton("History");
                        for (NoteDTO noteDTO : note.getVersions()) {
                            MaterialLink dropLink = new MaterialLink();
                            dropLink.setText(noteDTO.getTitle() + " (" + noteDTO.getTimestamp().toString() + ")");
                            dropLink.setTextColor(Color.BLACK);
                            dropLink.addClickHandler(handler -> {
                                AsyncCallback<NoteDTO> callbackHistory = new AsyncCallback<NoteDTO>() {
                                    @Override
                                    public void onFailure(Throwable caught) {
                                        MaterialToast.fireToast("Error: " + caught.getMessage());
                                    }

                                    @Override
                                    public void onSuccess(NoteDTO result) {
                                        MaterialToast.fireToast("Note created successfully.");
                                        window.close();
                                    }
                                };
                                nsa.createNote(noteDTO, callbackHistory);
                            });
                            drop.add(dropLink);
                        }
                        button.setMarginRight(420);
                        button.setWidth("200px");
                        button.add(drop);
                        card.add(button);

                        // EDIT NOTE
                        MaterialLink editButton = new MaterialLink();
                        MaterialLink btnModalClose = new MaterialLink("Close");

                        HTMLPanel panel2 = new HTMLPanel("");

                        MaterialWindow modal = new MaterialWindow();
                        modal.setStyle("color:#000000");
                        htmlPanel.add(modal);
                        editButton.setIconColor(Color.BLACK);
                        editButton.setIconType(IconType.EDIT);
                        editButton.addClickHandler(handler -> {
                            panel2.clear();
                            modal.setTitle("Edit Note: " + note.getTitle());
//                          Editting fields
                            MaterialLabel label3 = new MaterialLabel("Current Note Title:" + note.getTitle());
                            MaterialTextBox textbox = new MaterialTextBox();
                            panel2.add(divider);
                            MaterialLabel label4 = new MaterialLabel("Current Note Description:" + note.getDescription());
                            MaterialTextArea descriptionArea = new MaterialTextArea();
                            descriptionArea.setPlaceholder(note.getDescription());
                            panel2.add(label3);
                            panel2.add(textbox);
                            panel2.add(label4);
                            panel2.add(descriptionArea);
                            MaterialLink btnEditNote = new MaterialLink("Confirm");
                            btnEditNote.setEnabled(true);
                            btnModalClose.setBackgroundColor(Color.BLUE);
                            btnEditNote.setBackgroundColor(Color.BLUE);

                            btnEditNote.addClickHandler(leggo -> {
                                NoteServiceAsync ns = GWT.create(NoteService.class);
                                AsyncCallback<Boolean> callback4 = new AsyncCallback<Boolean>() {
                                    @Override
                                    public void onFailure(Throwable caught) {
                                        MaterialToast.fireToast("Error: " + caught.getMessage());
                                    }

                                    @Override
                                    public void onSuccess(Boolean result) {
                                        AsyncCallback<Boolean> callback6 = new AsyncCallback<Boolean>() {
                                            @Override
                                            public void onFailure(Throwable caught) {
                                                MaterialToast.fireToast("Error: " + caught.getMessage());
                                            }

                                            @Override
                                            public void onSuccess(Boolean result) {
                                                MaterialToast.fireToast("Note edited successfully.");
                                            }

                                        };
                                        ns.deleteNote(note, callback6);

                                        window.close();
                                    }
                                };
                                String newTitle;
                                String newDescription;

                                if (textbox.getValue().isEmpty()) {
                                    newTitle = note.getTitle();
                                } else {
                                    newTitle = textbox.getValue();
                                }
                                if (descriptionArea.getValue().isEmpty()) {
                                    newDescription = note.getTitle();
                                } else {
                                    newDescription = descriptionArea.getValue();
                                }
//                                AsyncCallback<Boolean> callback5 = new AsyncCallback<Boolean>() {
//                                    @Override
//                                    public void onFailure(Throwable caught) {
//                                        MaterialToast.fireToast("Error: " + caught.getMessage());
//                                    }
//
//                                    @Override
//                                    public void onSuccess(Boolean result) {
//                                        MaterialToast.fireToast("Inserted a new version successfully!");
//                                    }
//
//                                };

//                                ns.createNote(note, callback5);
//                                ns.addVersion(note, callback5);
                                ns.editNote(note, newTitle, newDescription, callback4);

                            });

                            panel2.add(btnEditNote);
                            panel2.add(btnModalClose);
                            modal.add(panel2);
                            modal.open();
                        });
                        btnModalClose.addClickHandler(event -> modal.close());
                        card.add(editButton);
                        MaterialLabel label = new MaterialLabel(note.getTitle());
                        label.setFontSize("1.5em");
                        card.add(label);
                        if (note.getNoteType().equalsIgnoreCase("TEXT")) {
                            MaterialLabel label2 = new MaterialLabel(note.getDescription());
                            card.add(label2);
                        } else {
                            MaterialLabel checkBoxesLabel = new MaterialLabel();
                            String descriptionSplit[] = note.getDescription().split("\n");
                            for (String string : descriptionSplit) {
                                MaterialRow row = new MaterialRow();

                                CheckBox check = new CheckBox();
                                check.setText(string);
                                row.add(check);
                                checkBoxesLabel.add(row);
                            }
                            card.add(checkBoxesLabel);
                        }

                        // DELETE NOTE
                        MaterialLink deleteButton = new MaterialLink(note.getTitle());
                        deleteButton.setIconType(IconType.DELETE);
                        deleteButton.setBackgroundColor(Color.RED);
                        deleteButton.addClickHandler(handler -> {
                            NoteServiceAsync nsa = GWT.create(NoteService.class);
                            AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
                                @Override
                                public void onFailure(Throwable caught) {
                                    MaterialToast.fireToast("Error: " + caught.getMessage());
                                }

                                @Override
                                public void onSuccess(Boolean result) {
                                    MaterialToast.fireToast("Note deleted successfully.");
                                    window.close();
                                }
                            };
                            nsa.deleteNote(note, callback);
                        });
                        card.add(deleteButton);
                        htmlPanel.add(card);

                    }
                }

            };
            //if (CurrentUser.username() != null && !CurrentUser.username().isEmpty()) {
            nsa.getNotesByUser(CurrentUser.username(), callback);
            //}
        });

    }

    /**
     * ======================
     * Rui Almeida <1160818/>
     * CORE 5.3
     * ======================
     */
    private ReminderDTO dto;
    private String minutes = null;
    private static final int HOUR = 60;
    private static final int DAY = 24;

    @UiHandler("dropdownTime")
    public void onSelectionOfTime(SelectionEvent<Widget> callback) {
        this.minutes = ((MaterialLink) callback.getSelectedItem()).getText();
        dropdownButton.setText(minutes);
    }

    @UiHandler("close")
    public void onClose(ClickEvent e) {
        windowAlert.close();
        ReminderServiceAsync service = GWT.create(ReminderService.class);

        ReminderDTO newReminder = new ReminderDTO(dto.getName(), dto.getDescription(), dto.getUsername(), dto.getDate(), false);

        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error calling reminder service! " + caught.getMessage());
            }

            public void onSuccess(Boolean result) {
                if (result.booleanValue()) {
                    if (!newReminder.isActive()) {
                        MaterialToast.fireToast("Silencing the reminder. Visit the 'Reminder' tab to reactive it.");
                    } else {
                        MaterialToast.fireToast("Could not silence the reminder!");
                    }
                } else {
                    MaterialToast.fireToast("Could not edit reminder!");
                }
            }
        };

        service.editReminder(dto, newReminder, callback);

        dropdownButton.setText("Postpone for another..");
    }

    private int[] parseTime(int dtoHours, int dtoMinutes, int parsedMinutes) {
        int time[] = new int[2];

        int remaining = 0;

        dtoMinutes += parsedMinutes;

        if (dtoMinutes >= HOUR) {
            remaining = dtoMinutes - HOUR;
            dtoMinutes = 0;
            dtoHours++;
        }

        dtoMinutes += remaining;

        if (dtoHours >= DAY) dtoHours = 0;

        time[0] = dtoHours;
        time[1] = dtoMinutes;

        return time;
    }

    @UiHandler("postpone")
    public void onPostpone(ClickEvent e) {

        if (minutes == null) {
            MaterialToast.fireToast("Please select when you would like to be reminded again in the dropdown menu.");
        } else {

            int parsedMinutes = Integer.parseInt(minutes.trim().split(" ")[0]);

            int dtoHours = dto.getDate().getHours();
            int dtoMinutes = dto.getDate().getMinutes();

            /**
             * Parse the new time
             */
            int[] time = parseTime(dtoHours, dtoMinutes, parsedMinutes);

            Date reminderDate = dto.getDate();
            reminderDate.setHours(time[0]);
            reminderDate.setMinutes(time[1]);

            ReminderDTO newDTO = new ReminderDTO(dto.getName(), dto.getDescription(), dto.getUsername(), reminderDate, dto.isActive());

            ReminderServiceAsync service = GWT.create(ReminderService.class);

            AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
                public void onFailure(Throwable caught) {
                    MaterialToast.fireToast("Error calling reminder service! " + caught.getMessage());
                }

                public void onSuccess(Boolean result) {
                    if (result.booleanValue()) {
                        MaterialToast.fireToast("Postponed reminder to " + String.valueOf(newDTO.getDate()));
                        windowAlert.close();
                    }
                }
            };

            service.editReminder(dto, newDTO, callback);
            minutes = null;
            dropdownButton.setText("Postpone for another..");
        }
    }

    @Override
    public void checkReminders() {
        if (CurrentUser.isLogged()) {
            ReminderServiceAsync reminderService = GWT.create(ReminderService.class);

            AsyncCallback<ArrayList<ReminderDTO>> callback = new AsyncCallback<ArrayList<ReminderDTO>>() {
                public void onFailure(Throwable caught) {
                    MaterialToast.fireToast("Error calling reminder service! " + caught.getMessage());
                }

                public void onSuccess(ArrayList<ReminderDTO> result) {
                    for (ReminderDTO reminder : result) {
                        Long reminderDate = reminder.getDate().getTime();
                        Long systemDate = System.currentTimeMillis();

                        if (Long.compare(reminderDate, systemDate) <= 0 && reminder.isActive()) {
                            dto = reminder;
                            alertTitle.setText(dto.getName() + ": " + dto.getDescription());
                            alertTime.setText(String.valueOf(dto.getDate()));
                            windowAlert.open();
                        }
                    }
                }
            };

            reminderService.reminders(CurrentUser.username(), callback);
        }
    }

    /**
     * ======================
     * Rui Almeida <1160818/>
     * CORE 5.3
     * ======================
     */


    public void refresh(){
        if (CurrentUser.isLogged()) {
            loginLink.setVisible(false);
            signUpLink.setVisible(false);
            noteMenuLink.setVisible(true);
            userImg.setVisible(true);
            userLink.setText(CurrentUser.username());
            userLink.setVisible(true);
            userImg.setBackgroundColor(Color.WHITE);
            userImg.setUrl(CurrentUser.picture());
            logout.setVisible(true);
//            userImg.addClickHandler((ClickEvent event) -> {
//                Window.open("#profile","_self",null);
//            });

        }else{
            loginLink.setVisible(true);
            signUpLink.setVisible(true);
            noteMenuLink.setVisible(false);
            userLink.setVisible(false);
            userImg.setVisible(false);
            logout.setVisible(false);
        }
    }
}
