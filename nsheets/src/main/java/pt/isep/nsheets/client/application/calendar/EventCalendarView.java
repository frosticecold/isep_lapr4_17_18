/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.calendar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.addins.client.timepicker.MaterialTimePicker;
import gwt.material.design.addins.client.window.MaterialWindow;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialCardContent;
import gwt.material.design.client.ui.MaterialCardTitle;
import gwt.material.design.client.ui.MaterialCollectionItem;
import gwt.material.design.client.ui.MaterialDatePicker;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialListValueBox;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;
import java.util.ArrayList;
import pt.isep.nsheets.shared.services.AgendaDTO;
import javax.inject.Inject;
import pt.isep.nsheets.shared.services.EventDTO;
import pt.isep.nsheets.shared.services.EventsService;
import pt.isep.nsheets.shared.services.EventsServiceAsync;
import pt.isep.nsheets.shared.services.AgendaService;
import pt.isep.nsheets.shared.services.AgendaServiceAsync;

/**
 *
 * @author MFerreira
 */
public class EventCalendarView extends ViewImpl implements EventCalendarPresenter.MyView {

    @Override
    public void getStringEmpty() {
        title.setText("");
        desc.setText("");
        dur.setText("");
        time.clear();
        dp.clear();
    }

    //Calendar - Create Event
    @UiField
    MaterialButton createEvent;

    @UiField
    MaterialWindow windowCreateEvent;

    @UiField
    MaterialTextBox title;

    @UiField
    MaterialTextBox desc;

    @UiField
    MaterialListValueBox<AgendaDTO> listCalendars;

    @UiField
    MaterialTextBox dur;

    @UiField
    MaterialDatePicker dp;

    @UiField
    MaterialTimePicker time;

    @UiField
    MaterialButton create;

    @UiField
    MaterialButton cancelEvent;

    @UiHandler("createEvent")
    void windowCreateEvent(ClickEvent e) {
        AgendaServiceAsync agendaSvc = GWT.create(AgendaService.class);
        listCalendars.clear();

        AsyncCallback<ArrayList<AgendaDTO>> callback = new AsyncCallback<ArrayList<AgendaDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error! " + caught.getMessage());
                windowCreateEvent.close();
            }

            @Override
            public void onSuccess(ArrayList<AgendaDTO> result) {
                for (AgendaDTO agendaDto : result) {
                    listCalendars.add(agendaDto);
                }
            }
        };
        agendaSvc.listAgenda(callback);
        windowCreateEvent.open();

    }

    @UiHandler("cancelEvent")
    void cancelEvent(ClickEvent e) {
        windowCreateEvent.close();
    }

    @UiHandler("create")
    void createEvent(ClickEvent e) {
        EventsServiceAsync eventSvc = GWT.create(EventsService.class);

        AsyncCallback<EventDTO> callback = new AsyncCallback<EventDTO>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error! " + caught.getMessage());
                windowCreateEvent.close();
            }

            @Override
            public void onSuccess(EventDTO result) {
                MaterialToast.fireToast("Event created! ", "rounded");
                getStringEmpty();
                windowCreateEvent.close();
            }
        };
        EventDTO eventDto = new EventDTO(title.getValue(), desc.getValue(),
                dp.getValue(), time.getValue(), Long.parseLong(dur.getValue()), listCalendars.getSelectedValue());
        eventSvc.createEvent(eventDto, callback);
    }

    //Calendar - Edit Event
    @UiField
    MaterialButton editEvent;

    @UiField
    MaterialWindow windowEditEvent;

    @UiField
    MaterialTextBox titleEdit;

    @UiField
    MaterialTextBox descEdit;

    @UiField
    MaterialListValueBox<AgendaDTO> listCalendarsEdit;

    @UiField
    MaterialTextBox durEdit;

    @UiField
    MaterialDatePicker dpEdit;

    @UiField
    MaterialButton creEdit;

    @UiField
    MaterialButton cancelEventEdit;

    @UiField
    MaterialListValueBox<EventDTO> listEvent;

    @UiHandler("editEvent")
    void editEvent(ClickEvent e) {
        windowEditEvent.open();

        EventsServiceAsync eventSvc = GWT.create(EventsService.class);

        AsyncCallback<ArrayList<EventDTO>> callback = new AsyncCallback<ArrayList<EventDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error! " + caught.getMessage());
                windowEditEvent.close();

            }

            @Override
            public void onSuccess(ArrayList<EventDTO> result) {
                MaterialToast.fireToast("Event has been edited! ", "rounded");
                listEvent.clear();
                for (EventDTO aDto : result) {
                    listEvent.add(aDto);
                }
            }

        };
        eventSvc.getEvents(callback);
        AgendaServiceAsync aSvc = GWT.create(AgendaService.class);

        AsyncCallback<ArrayList<AgendaDTO>> callback2 = new AsyncCallback<ArrayList<AgendaDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error! " + caught.getMessage());
                windowEditEvent.close();

            }

            @Override
            public void onSuccess(ArrayList<AgendaDTO> result) {
                MaterialToast.fireToast("Event has been edited! ", "rounded");
                for (AgendaDTO agDTO : result) {
                    listCalendarsEdit.add(agDTO);
                }
            }
        };
        aSvc.getAgenda(callback2);
    }

    @UiHandler("creEdit")
    void create(ClickEvent e) {
        EventsServiceAsync eventSvc = GWT.create(EventsService.class);

        AsyncCallback<ArrayList<EventDTO>> callback = new AsyncCallback<ArrayList<EventDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error! " + caught.getMessage());
                windowEditEvent.close();

            }

            @Override
            public void onSuccess(ArrayList<EventDTO> result) {
                MaterialToast.fireToast("Event has been edited! ", "rounded");
                windowEditEvent.close();
            }
        };

        EventDTO eDto = new EventDTO(titleEdit.getValue(), descriptionEdit.getValue(),
                dpEdit.getValue(), time.getValue(), Long.parseLong(durEdit.getValue()), listCalendarsEdit.getSelectedValue());

        eventSvc.editEvent(eDto, callback);

    }

    @UiHandler("cancelEvent")
    void cancelEditEvent(ClickEvent e) {
        windowEditEvent.close();
    }

    //Calendar - Delete Event
    @UiField
    MaterialButton deleteEvent;

    @UiField
    MaterialWindow windowDeleteEvent;

    @UiField
    MaterialListValueBox<EventDTO> listEventDelete;

    @UiField
    MaterialButton creDelete;

    @UiField
    MaterialButton cancelEventDelete;

    @UiHandler("deleteEvent")
    void deleteEventButton(ClickEvent e) {
        windowDeleteEvent.open();

        EventsServiceAsync eventSvc = GWT.create(EventsService.class);

        AsyncCallback<ArrayList<EventDTO>> callback = new AsyncCallback<ArrayList<EventDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error! " + caught.getMessage());
                windowCreateEvent.close();
            }

            @Override
            public void onSuccess(ArrayList<EventDTO> result) {
                listEventDelete.clear();
                for (EventDTO eventDto : result) {
                    listEventDelete.add(eventDto);
                }
            }
        };
        eventSvc.getEvents(callback);
    }

    @UiHandler("creDelete")
    void creDelete(ClickEvent e) {
        EventsServiceAsync eventSvc = GWT.create(EventsService.class);

        AsyncCallback<EventDTO> callback = new AsyncCallback<EventDTO>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error! " + caught.getMessage());
                windowCreateEvent.close();
            }

            @Override
            public void onSuccess(EventDTO result) {
                MaterialToast.fireToast("Event has been removed! ", "rounded");
                windowDeleteEvent.close();

            }
        };
        EventDTO eventDto = listEventDelete.getSelectedValue();
        eventSvc.removeEvent(eventDto, callback);

    }

    @UiHandler("cancelEventDelete")
    void cancelEventDelete(ClickEvent e) {
        windowDeleteEvent.close();
    }

    //Calendar - List Events
    @UiField
    MaterialButton showEvent;

    @UiField
    MaterialWindow windowListEvent;

    @UiField
    MaterialButton creList;

    @UiField
    MaterialListValueBox<EventDTO> showEventList;

    @UiHandler("showEvent")
    void listEvents(ClickEvent e) {
        windowListEvent.open();
        showEventList.clear();

        EventsServiceAsync eventSvc = GWT.create(EventsService.class);
        AsyncCallback<ArrayList<EventDTO>> callback = new AsyncCallback<ArrayList<EventDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error! " + caught.getMessage());
                windowListEvent.close();
            }

            @Override
            public void onSuccess(ArrayList<EventDTO> result) {
                for (EventDTO dto : result) {
                    showEventList.add(dto);
                }
            }
        };
        EventDTO even = showEventList.getSelectedValue();
        eventSvc.listEvent(even, callback);
    }

    @UiHandler("creList")
    void cancelList(ClickEvent e) {
        windowListEvent.close();
    }

    //Basic Agenda -- Create Calendar
    @UiField
    MaterialButton createCalendar;

    @UiField
    MaterialWindow windowCreateCalendars;

    @UiField
    MaterialTextBox name;

    @UiField
    MaterialTextBox description;

    @UiField
    MaterialButton createCal;

    @UiField
    MaterialButton cancel;

    @UiField
    MaterialListValueBox<Color> ColorCalendarList;

    @UiHandler("createCalendar")
    void CalendarCreate(ClickEvent e) {
        windowCreateCalendars.open();
    }

    @UiHandler("cancel")
    void CalendarCancel(ClickEvent e) {
        windowCreateCalendars.close();;
    }

    @UiHandler("createCal")
    void createCal(ClickEvent e) {
        AgendaServiceAsync agendaSvc = GWT.create(AgendaService.class);

        // Set up the callback object.
        AsyncCallback<AgendaDTO> callback = new AsyncCallback<AgendaDTO>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error! " + caught.getMessage());
                windowCreateCalendars.close();
            }

            @Override
            public void onSuccess(AgendaDTO result) {
                MaterialToast.fireToast("Calendar has been created...", "rounded");
                windowCreateCalendars.close();
            }
        };

        AgendaDTO agendaDto = new AgendaDTO(name.getValue(), description.getValue(), ColorCalendarList.getSelectedValue());
        agendaSvc.addAgenda(agendaDto, callback);
    }

    //Basic Agenda -- Edit Calendar
    @UiField
    MaterialButton editCalendar;

    @UiField
    MaterialWindow windowEditCalendars;

    @UiField
    MaterialTextBox nameEdit;

    @UiField
    MaterialTextBox descriptionEdit;

    @UiField
    MaterialButton editCal;

    @UiField
    MaterialButton cancelEdit;

    @UiField
    MaterialListValueBox<Color> ColorCalendarListEdit;

    @UiField
    MaterialListValueBox<AgendaDTO> CalendarListEdit;

    public MaterialButton getCancelEdit() {
        return cancel;
    }

    @UiHandler("editCalendar")
    void editCalendar(ClickEvent e) {
        windowEditCalendars.open();
        CalendarListEdit.clear();
        AgendaServiceAsync agendaSvc = GWT.create(AgendaService.class);

        // Set up the callback object.
        AsyncCallback<ArrayList<AgendaDTO>> callback = new AsyncCallback<ArrayList<AgendaDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error! " + caught.getMessage());
                windowEditCalendars.close();
            }

            @Override
            public void onSuccess(ArrayList<AgendaDTO> result) {
                MaterialToast.fireToast("Calendar ready...", "rounded");
                for (AgendaDTO agendaDTO : result) {
                    CalendarListEdit.add(agendaDTO);
                }

            }
        };
        agendaSvc.getAgenda(callback);
        //windowCreateCalendars.open();
    }

    @UiHandler("cancelEdit")
    void cancelEdita(ClickEvent e) {
        windowEditCalendars.close();
    }

    @UiHandler("editCal")
    void editCal(ClickEvent e) {
        AgendaServiceAsync agendaSvc = GWT.create(AgendaService.class);

        AsyncCallback<AgendaDTO> callback = new AsyncCallback<AgendaDTO>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error! " + caught.getMessage());
                windowDeleteCalendars.close();
            }

            @Override
            public void onSuccess(AgendaDTO result) {
                MaterialToast.fireToast("Calendar version has been removed...", "rounded");
                windowDeleteCalendars.close();
            }
        };
        AgendaDTO a = CalendarListEdit.getSelectedValue();
        agendaSvc.removeAgenda(a, callback);

        AsyncCallback<AgendaDTO> callback2 = new AsyncCallback<AgendaDTO>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error! " + caught.getMessage());
                windowEditCalendars.close();
            }

            @Override
            public void onSuccess(AgendaDTO result) {
                MaterialToast.fireToast("Calendar has been edited...", "rounded");
                windowEditCalendars.close();
            }

        };
        AgendaDTO aDTO = new AgendaDTO(nameEdit.getValue(), descriptionEdit.getValue(), CalendarListEdit.getSelectedItemText(), ColorCalendarListEdit.getSelectedValue());
        agendaSvc.addAgenda(aDTO, callback2);
    }

    //Basic Agenda - Delete Calendar
    @UiField
    MaterialButton deleteCalendar;

    @UiField
    MaterialWindow windowDeleteCalendars;

    @UiField
    MaterialButton deleteCal;

    @UiField
    MaterialButton cancelDelete;

    @UiField
    MaterialListValueBox<AgendaDTO> CalendarListDelete;

    public MaterialButton getDelete() {
        return deleteCalendar;
    }

    public MaterialButton getCancelDelete() {
        return cancelDelete;
    }

    public MaterialButton getDeleteCalendar() {
        return deleteCal;
    }

    @UiHandler("deleteCalendar")
    void deleteCalendar(ClickEvent e) {
        windowDeleteCalendars.open();
        CalendarListDelete.clear();

        AgendaServiceAsync aSvc = GWT.create(AgendaService.class);

        AsyncCallback<ArrayList<AgendaDTO>> callback = new AsyncCallback<ArrayList<AgendaDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error! " + caught.getMessage());
                windowDeleteCalendars.close();
            }

            @Override
            public void onSuccess(ArrayList<AgendaDTO> result) {
                MaterialToast.fireToast("Calendar has been removed...", "rounded");
                for (AgendaDTO aDTO : result) {
                    CalendarListDelete.add(aDTO);
                }
            }
        };
        aSvc.getAgenda(callback);
    }

    @UiHandler("cancelDelete")
    void cancelDelete(ClickEvent e) {
        windowDeleteCalendars.close();
    }

    @UiHandler("deleteCal")
    void deleteCal(ClickEvent e) {
        AgendaServiceAsync aSvc = GWT.create(AgendaService.class);

        AsyncCallback<AgendaDTO> callback = new AsyncCallback<AgendaDTO>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error! " + caught.getMessage());
                windowDeleteCalendars.close();
            }

            @Override
            public void onSuccess(AgendaDTO result) {
                MaterialToast.fireToast("Calendar has been removed...", "rounded");
                windowDeleteCalendars.close();
            }
        };
        AgendaDTO a = CalendarListDelete.getSelectedValue();
        aSvc.remove(a, callback);
    }

    //Basic Agenda - View all Events
    @UiField
    MaterialButton viewEvent;

    @UiField
    MaterialWindow windowViewEvents;

    @UiField
    MaterialCard mCard;

    @UiField
    MaterialButton close;

    @UiField
    MaterialCollectionItem content;

    @UiHandler("viewEvent")
    void viewEvent(ClickEvent e) {
        windowViewEvents.open();
        content.clear();

        EventsServiceAsync eventsSvc = GWT.create(EventsService.class);

        // Set up the callback object.
        AsyncCallback<ArrayList<EventDTO>> callback = new AsyncCallback<ArrayList<EventDTO>>() {
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error! " + caught.getMessage());
                windowViewEvents.close();
            }

            public void onSuccess(ArrayList<EventDTO> result) {
                for (EventDTO eDTO : result) {
                    mCard = new MaterialCard();
                    MaterialCardContent mCardC = new MaterialCardContent();

                    MaterialCardTitle cardAgenda = new MaterialCardTitle();
                    cardAgenda.setText(eDTO.getAgendaDTO().getName());
                    cardAgenda.setTextAlign(TextAlign.CENTER);
                    cardAgenda.setFontWeight(Style.FontWeight.BOLD);

                    MaterialCardTitle cardTitle = new MaterialCardTitle();
                    cardTitle.setText(eDTO.getTitle());
                    cardTitle.setTextAlign(TextAlign.CENTER);
                    cardTitle.setFontWeight(Style.FontWeight.BOLD);

                    MaterialLabel label1 = new MaterialLabel();
                    label1.setTitle("Event Description");
                    label1.setText(eDTO.getDescription());

                    MaterialLabel label2 = new MaterialLabel();
                    label2.setTitle("Event Date");
                    int year = eDTO.getDate().getYear() + 1900;
                    label2.setText("Date: " + eDTO.getDate().getDate() + "/" + eDTO.getDate().getMonth() + "/" + year);

                    MaterialLabel label3 = new MaterialLabel();
                    label3.setTitle("Event Date");
                    label3.setText("Time: " + eDTO.getHour().getHours() + "h" + eDTO.getHour().getMinutes());
                    MaterialLabel label4 = new MaterialLabel();
                    label4.setTitle("Event Duration (minutes)");
                    label4.setText("Duration: " + String.valueOf(eDTO.getDuration()) + " minutes");

                    MaterialLabel label5 = new MaterialLabel();

                    label5.setText(eDTO.getAgendaDTO().getName().toString());

                    mCardC.add(label5);
                    mCardC.add(cardTitle);
                    mCardC.add(label1);
                    mCardC.add(label2);
                    mCardC.add(label3);
                    mCardC.add(label4);
                    mCard.setWaves(WavesType.DEFAULT);
                    mCard.add(mCardC);
                    mCard.setBorder("SOLID");
                    mCard.setShadow(4);
                    mCard.setBackgroundColor(eDTO.getAgendaDTO().getColor());
                    content.add(mCard);
                }
//                eventsList.close();
                MaterialToast.fireToast("Listed...", "rounded");
            }
        };
        eventsSvc.getEvents(callback);
    }

    @UiHandler("close")
    void close(ClickEvent e) {
        windowViewEvents.close();
    }

    //Basic Agenda - View all Events by agenda
    @UiField
    MaterialButton viewEventCalendar;

    @UiField
    MaterialWindow windowViewCalendar;

    @UiField
    MaterialListValueBox<AgendaDTO> calendarListChoose;

    @UiField
    MaterialCollectionItem contentCalendar;

    @UiField
    MaterialCard mCardCalendar;

    @UiField
    MaterialButton filterCalendar;

    @UiField
    MaterialButton closeCalendar;

    @UiHandler("closeCalendar")
    void closeCalendar(ClickEvent e) {
        windowViewCalendar.close();
    }

    @UiHandler("viewEventCalendar")
    void viewEventCalendar(ClickEvent e) {
        windowViewCalendar.open();
        calendarListChoose.clear();

        AgendaServiceAsync aSvc = GWT.create(AgendaService.class);
        AsyncCallback<ArrayList<AgendaDTO>> callback = new AsyncCallback<ArrayList<AgendaDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error! " + caught.getMessage());
                windowViewCalendar.close();
            }

            @Override
            public void onSuccess(ArrayList<AgendaDTO> result) {
                for (AgendaDTO dto : result) {
                    calendarListChoose.add(dto);
                }
            }
        };
        aSvc.getAgenda(callback);
    }

    @UiHandler("filterCalendar")
    void filterCalendar(ClickEvent e) {
        contentCalendar.clear();

        EventsServiceAsync eSvc = GWT.create(EventsService.class);

        AsyncCallback<ArrayList<EventDTO>> callback = new AsyncCallback<ArrayList<EventDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error! " + caught.getMessage());
            }

            @Override
            public void onSuccess(ArrayList<EventDTO> result) {
                for (EventDTO eDTO : result) {
                    mCardCalendar = new MaterialCard();

                    MaterialCardContent mCardC = new MaterialCardContent();

                    MaterialCardTitle cardTitle = new MaterialCardTitle();
                    cardTitle.setText(eDTO.getTitle());
                    cardTitle.setTextAlign(TextAlign.CENTER);
                    cardTitle.setFontWeight(Style.FontWeight.BOLD);

                    MaterialLabel label1 = new MaterialLabel();
                    label1.setTitle("Event Description");
                    label1.setText(eDTO.getDescription());

                    MaterialLabel label2 = new MaterialLabel();
                    label2.setTitle("Event Date");
                    int year = eDTO.getDate().getYear() + 1900;
                    label2.setText("Date: " + eDTO.getDate().getDate() + "/" + eDTO.getDate().getMonth() + "/" + year);

                    MaterialLabel label3 = new MaterialLabel();
                    label3.setTitle("Event Date");
                    label3.setText("Time: " + eDTO.getHour().getHours() + "h" + eDTO.getHour().getMinutes());

                    MaterialLabel label4 = new MaterialLabel();
                    label4.setTitle("Event Duration (minutes)");
                    label4.setText("Duration: " + String.valueOf(eDTO.getDuration()) + " minutes");

                    mCardC.add(cardTitle);
                    mCardC.add(label1);
                    mCardC.add(label2);
                    mCardC.add(label3);
                    mCardC.add(label4);
                    mCard.setWaves(WavesType.DEFAULT);
                    mCard.add(mCardC);
                    mCard.setBorder("SOLID");
                    mCard.setShadow(4);
                    mCard.setBackgroundColor(eDTO.getAgendaDTO().getColor());
                    contentCalendar.add(mCard);

                }
                MaterialToast.fireToast("Listed...", "rounded");
            }
        };
        eSvc.getEvents(callback);

    }

    @Override
    public void addClickHandler(ClickHandler ch) {
        //
    }

    interface Binder extends UiBinder<Widget, EventCalendarView> {
    }

    @Inject
    EventCalendarView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        for (Color color : Color.values()) {
            ColorCalendarList.add(color);
            ColorCalendarListEdit.add(color);
        }

    }

    @Override
    public void setContents(ArrayList<EventDTO> contents) {
    }

}
