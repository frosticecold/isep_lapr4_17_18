/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.lapr4.green.s1.ipc.n1160818.bootstrap;

import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.GetPropertiesService;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.domain.Agenda;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.persistence.AgendaRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import gwt.material.design.client.constants.Color;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class AgendaBootstrapper implements Action {

    @Override
    public boolean execute() {
        try {
            registerAgenda("LAPR", "La", Color.BLUE);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(AgendaBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void registerAgenda(final String name, final String description, final Color color) throws DataConcurrencyException, DataIntegrityViolationException {
        PersistenceContext.setSettings(GetPropertiesService.getPersistenceSettings());
        AgendaRepository repo = PersistenceContext.repositories().agendas();

        Agenda agenda = new Agenda(name, description, color);

        repo.save(agenda);
    }

}
