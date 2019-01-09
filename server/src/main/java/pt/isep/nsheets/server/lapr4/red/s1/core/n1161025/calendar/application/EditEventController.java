/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.application;

import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Date;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.domain.*;
import pt.isep.nsheets.shared.services.AgendaDTO;
import pt.isep.nsheets.shared.services.EventDTO;

/**
 *
 * @author MFerreira
 */
public class EditEventController implements Controller {

    public Event editEvent(EventDTO eventDTO) throws DataConcurrencyException, DataIntegrityViolationException {
        return new EventService().editEvent(eventDTO);
    }
}
