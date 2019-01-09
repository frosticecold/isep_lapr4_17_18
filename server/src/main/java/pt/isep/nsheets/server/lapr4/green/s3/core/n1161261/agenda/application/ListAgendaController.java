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
 * @author Car
 */
public class ListAgendaController implements Controller{
    
    public Iterable<Agenda> listAllAgendas() {
        return new AgendaService().listAllAgendas();
    }
}
