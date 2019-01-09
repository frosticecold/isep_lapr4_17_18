package pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.application;

import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.domain.Event;
import pt.isep.nsheets.shared.services.EventDTO;

/**
 *
 * @author MFerreira
 */
public class RemoveEventController implements Controller{
    
    public void removeEvent(EventDTO event){
        
    	new EventService().removeEvent(event);
    }
}
