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
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class EditAgendaController implements Controller {

    public Agenda editAgenda(AgendaDTO dto) throws DataConcurrencyException, DataIntegrityViolationException, Exception {
        return new AgendaService().updateName(dto);
    }

}
