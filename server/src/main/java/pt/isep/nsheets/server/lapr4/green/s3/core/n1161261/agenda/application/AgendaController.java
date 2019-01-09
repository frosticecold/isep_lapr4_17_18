/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.application;

import eapli.framework.application.Controller;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.domain.Agenda;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class AgendaController implements Controller {

    public Agenda findAgendaByName(String name) {
        return new AgendaService().findAgendaByName(name);

    }

}
