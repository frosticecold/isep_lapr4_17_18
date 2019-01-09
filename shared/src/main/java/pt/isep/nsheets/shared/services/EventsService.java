package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author MFerreira
 */
@RemoteServiceRelativePath("eventsService")
public interface EventsService extends RemoteService {

    ArrayList<EventDTO> getEvents();

    EventDTO createEvent(EventDTO eventDTO) throws DataException;

    void removeEvent(EventDTO event);

    EventDTO editEvent(EventDTO eDto);

    ArrayList<EventDTO> getEventsAgenda(AgendaDTO agendaDto);
    
    Iterable<EventDTO> listEvent(EventDTO dto);
}
