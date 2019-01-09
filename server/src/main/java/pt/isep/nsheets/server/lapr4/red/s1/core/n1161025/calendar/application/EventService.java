package pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.application;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.domain.Event;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.persistence.EventRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.shared.services.AgendaDTO;
import pt.isep.nsheets.shared.services.EventDTO;

/**
 *
 * @author MFerreira
 */
public class EventService {

    public Event createEvent(EventDTO dto) throws DataConcurrencyException, DataIntegrityViolationException {

        final EventRepository eventRepository = PersistenceContext.repositories().events();

        Event ev = Event.fromDTO(dto);

        for (Event e : eventRepository.findAll()) {
            if ((e.title().equals(ev.title())) && (e.description().equals(ev.description()))) {
                return null;
            }
        }
        eventRepository.save(ev);

        return ev;

    }

    public Iterable<Event> listAllEvents() {

        final EventRepository eventRepository = PersistenceContext.repositories().events();
        Iterable<Event> e = eventRepository.findAll();

        return e;
    }

    public void removeEvent(EventDTO event) {
        final EventRepository eventRepository = PersistenceContext.repositories().events();
        Event e = eventRepository.findByTitleAndDescription(event.getTitle(), event.getDescription()).iterator().next();
        eventRepository.removeEvent(e);
    }

    public Event editEvent(EventDTO eventDTO) throws DataConcurrencyException, DataIntegrityViolationException {
        final EventRepository eventRepository = PersistenceContext.repositories().events();

        Event event = eventRepository.findByTitleAndDescriptionSingle(eventDTO.getTitle(), eventDTO.getDescription());

        event.editEvent(eventDTO.getTitle(), eventDTO.getDescription(), eventDTO.getDate(), eventDTO.getHour(), eventDTO.getDuration());

        eventRepository.save(event);

        return event;
    }

    public ArrayList<Event> eventsAgenda(AgendaDTO agendaDto) {
        Iterable<Event> listEvent = listAllEvents();

        ArrayList<Event> listEvents = new ArrayList<>();
        for (Event event : listEvent) {
            if (event.getCalendar().equals(agendaDto)) {
                listEvents.add(event);
            }
        }
        return listEvents;
    }
}
