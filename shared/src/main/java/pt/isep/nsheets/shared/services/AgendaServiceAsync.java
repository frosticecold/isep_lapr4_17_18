/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;

/**
 *
 * @author Joana Oliveira
 */
public interface AgendaServiceAsync {

    void getAgenda(AsyncCallback<ArrayList<AgendaDTO>> callback);

    void addAgenda(AgendaDTO agendaDTO, AsyncCallback<AgendaDTO> callback);

    void removeAgenda(AgendaDTO agendaDTO, AsyncCallback<AgendaDTO> callback);
    
    void remove(AgendaDTO agendaDTO, AsyncCallback<AgendaDTO> callback);

    void findAgendaByName(String name, AsyncCallback<AgendaDTO> callback);

    void listAgenda(AsyncCallback<ArrayList<AgendaDTO>> callback);

    void editAgendaNameByID(AgendaDTO agendaDTO,AsyncCallback<AgendaDTO> callback);

}
