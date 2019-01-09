/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.events.application;

import eapli.framework.application.Controller;
import java.util.ArrayList;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.application.EventService;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.domain.Event;
import pt.isep.nsheets.shared.services.AgendaDTO;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class EventsAgendaController implements Controller {

    public ArrayList<Event> eventsAgenda(AgendaDTO agendaDto) {
        return new EventService().eventsAgenda(agendaDto);
    }

}
