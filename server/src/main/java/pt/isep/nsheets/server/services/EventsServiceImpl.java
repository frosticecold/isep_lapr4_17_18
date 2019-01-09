/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.events.application.EventsAgendaController;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.application.CreateEventController;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.application.EditEventController;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.application.ListEventsController;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.application.RemoveEventController;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.domain.Event;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;
import pt.isep.nsheets.shared.services.AgendaDTO;
import pt.isep.nsheets.shared.services.DataException;
import pt.isep.nsheets.shared.services.EventDTO;
import pt.isep.nsheets.shared.services.EventsService;

/**
 *
 * @author MFerreira
 */
public class EventsServiceImpl extends RemoteServiceServlet implements EventsService {

    private PersistenceSettings getPersistenceSettings() {

        Properties props = new Properties();

        props.put("persistence.repositoryFactory",
                "pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa.JpaRepositoryFactory");
        props.put("persistence.persistenceUnit", "lapr4.NSheetsPU");

        // Other JPA properties that one might want to override from the ones in
        // persistence.xml
        // props.put("javax.persistence.jdbc.url",
        // "jdbc:h2:../db/nsheets;MV_STORE=FALSE;MVCC=FALSE");
        // props.put("javax.persistence.jdbc.password", "");
        // props.put("javax.persistence.jdbc.driver", "org.h2.Driver");
        // props.put("javax.persistence.jdbc.user", "");
        // props.put("javax.persistence.schema-generation.database.action", "create");
        // props.put("eclipselink.logging.level", "FINE");
        return new PersistenceSettings(props);
    }

    @Override
    public EventDTO createEvent(EventDTO eventDTO) throws DataException {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        CreateEventController ctrl = new CreateEventController();

        Event ev = null;

        try {
            ev = ctrl.createEvent(eventDTO);

        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {

            throw new DataException((Throwable) ex);

        }
        
        if (ev != null) {
            return ev.toDTO();
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<EventDTO> getEvents() {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        ArrayList<EventDTO> eventsDTO = new ArrayList<EventDTO>();

        ListEventsController ctrl = new ListEventsController();

        Iterable<Event> events = ctrl.listAllEvents();

        events.forEach(ev -> eventsDTO.add(ev.toDTO()));

        return eventsDTO;
    }

    @Override
    public void removeEvent(EventDTO event) {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        RemoveEventController ctrl = new RemoveEventController();

        ctrl.removeEvent(event);
    }

    @Override
    public EventDTO editEvent(EventDTO eventDTO) {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        EditEventController ctrl = new EditEventController();

        Event ev = null;
        try {
            ev = ctrl.editEvent(eventDTO);

        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {

            Logger.getLogger(EventsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);

        }

        if (ev != null) {
            return ev.toDTO();
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<EventDTO> getEventsAgenda(AgendaDTO agendaDto) {
        PersistenceContext.setSettings(this.getPersistenceSettings());
        EventsAgendaController controller = new EventsAgendaController();

        Iterable<Event> event = controller.eventsAgenda(agendaDto);
        ArrayList<EventDTO> listEvent = new ArrayList<>();

        for (Event e : event) {
            listEvent.add(e.toDTO());
        }
        return listEvent;
    }

    @Override
    public Iterable<EventDTO> listEvent(EventDTO dto) {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        ListEventsController controller = new ListEventsController();

        Iterable<Event> event = controller.listAllEvents();

        List<EventDTO> list = new ArrayList<>();

        for (Event e : event) {
            list.add(e.toDTO());
        }
        return list;
    }

}
