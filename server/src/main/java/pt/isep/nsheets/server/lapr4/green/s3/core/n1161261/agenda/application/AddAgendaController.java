/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.application;

import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.domain.Agenda;
import pt.isep.nsheets.shared.services.AgendaDTO;


/**
 *
 * @author Car
 */
public class AddAgendaController implements Controller{
    
    public Agenda addCalendar(AgendaDTO agendaDTO) throws DataConcurrencyException, DataIntegrityViolationException {
        return new AgendaService().addAgenda(agendaDTO);
    }
}