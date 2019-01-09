/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.application;

import eapli.framework.application.Controller;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.domain.Event;

/**
 *
 * @author MFerreira
 */
public class ListEventsController implements Controller{
    
    public Iterable<Event> listAllEvents() {
        return new EventService().listAllEvents();
    }
}
