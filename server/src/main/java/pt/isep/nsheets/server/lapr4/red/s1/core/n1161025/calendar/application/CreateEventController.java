/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class CreateEventController implements Controller{
    
    public Event createEvent(EventDTO dto) throws DataConcurrencyException, DataIntegrityViolationException {
        
    	return new EventService().createEvent(dto);
    }
    
    
    
}
