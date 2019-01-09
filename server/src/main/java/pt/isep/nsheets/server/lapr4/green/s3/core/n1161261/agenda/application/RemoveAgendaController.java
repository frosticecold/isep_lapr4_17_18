/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.application;

import eapli.framework.application.Controller;
import pt.isep.nsheets.shared.services.AgendaDTO;

/**
 *
 * @author Car
 */
public class RemoveAgendaController implements Controller {

    public void removeAgenda(AgendaDTO dto) {

        new AgendaService().removeAgenda(dto);
    }
}
