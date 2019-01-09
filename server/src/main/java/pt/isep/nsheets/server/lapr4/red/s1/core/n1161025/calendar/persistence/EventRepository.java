package pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.persistence;

import eapli.framework.persistence.repositories.Repository;
import java.util.ArrayList;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.domain.Agenda;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.domain.Event;

/**
 *
 * @author MFerreira
 */
public interface EventRepository extends Repository<Event, Long>{
    
    Boolean removeEventByTitleDescription(String title, String description);

    Iterable<Event> findByTitleAndDescription(String title, String description);
    
    Event findByTitleAndDescriptionSingle(String title, String description);
    
    void removeEvent(Event event);
    
    ArrayList<Event> eventsAgenda(Agenda agenda);
}
