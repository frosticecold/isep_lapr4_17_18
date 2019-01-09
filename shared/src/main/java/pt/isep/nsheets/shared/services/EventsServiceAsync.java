/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author MFerreira
 */
public interface EventsServiceAsync {

    void createEvent(EventDTO wdDto, AsyncCallback<EventDTO> callback);

    void editEvent(EventDTO wdDto,AsyncCallback<ArrayList<EventDTO>> callback);

    void getEvents(AsyncCallback<ArrayList<EventDTO>> callback);

    void removeEvent(EventDTO event, AsyncCallback<EventDTO> callback);

    void getEventsAgenda(AgendaDTO agendaDto, AsyncCallback<ArrayList<EventDTO>> callback);

    void listEvent(EventDTO dto, AsyncCallback<ArrayList<EventDTO>> callback);

    

}
