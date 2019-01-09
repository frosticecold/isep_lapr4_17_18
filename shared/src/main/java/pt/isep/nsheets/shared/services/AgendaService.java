/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.ArrayList;

/**
 *
 * @author Joana Oliveira
 */
@RemoteServiceRelativePath("agendaService")
public interface AgendaService extends RemoteService {

    ArrayList<AgendaDTO> getAgenda();

    AgendaDTO addAgenda(AgendaDTO agendaDTO);

    void removeAgenda(AgendaDTO agendaDTO);

    void remove(AgendaDTO agendaDTO);
    
    AgendaDTO findAgendaByName(String name);
    
    Iterable<AgendaDTO> listAgenda();
    
    AgendaDTO editAgendaNameByID(AgendaDTO agendaDTO);

}
