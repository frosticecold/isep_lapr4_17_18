/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.application.AddAgendaController;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.application.AgendaController;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.application.EditAgendaController;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.application.ListAgendaController;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.application.RemoveAgendaController;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.domain.Agenda;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;
import pt.isep.nsheets.shared.services.AgendaDTO;
import pt.isep.nsheets.shared.services.AgendaService;

/**
 *
 * @author Joana Oliveira
 */
public class AgendaServiceImpl extends RemoteServiceServlet implements AgendaService {

    private PersistenceSettings getPersistenceSettings() {

        Properties props = new Properties();

        props.put("persistence.repositoryFactory",
                "pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa.JpaRepositoryFactory");
        props.put("persistence.persistenceUnit", "lapr4.NSheetsPU");

        return new PersistenceSettings(props);
    }

    @Override
    public AgendaDTO addAgenda(AgendaDTO agendaDTO) {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        AddAgendaController controller = new AddAgendaController();

        Agenda agenda = null;

        try {
            agenda = controller.addCalendar(agendaDTO);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(AgendaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(AgendaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return agenda.toDTO();
    }

    @Override
    public ArrayList<AgendaDTO> getAgenda() {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        ArrayList<AgendaDTO> agendaDTO = new ArrayList<AgendaDTO>();

        ListAgendaController controller = new ListAgendaController();

        Iterable<Agenda> agendas = controller.listAllAgendas();

        agendas.forEach(a -> agendaDTO.add(a.toDTO()));

        return agendaDTO;
    }

    @Override
    public void removeAgenda(AgendaDTO dto) {
        PersistenceContext.setSettings(this.getPersistenceSettings());
        RemoveAgendaController controller = new RemoveAgendaController();

        controller.removeAgenda(dto);
    }

    @Override
    public AgendaDTO findAgendaByName(String name) {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        AgendaController controller = new AgendaController();

        Agenda agenda = controller.findAgendaByName(name);

        return agenda.toDTO();

    }

    @Override
    public Iterable<AgendaDTO> listAgenda() {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        ListAgendaController controller = new ListAgendaController();

        Iterable<Agenda> agenda = controller.listAllAgendas();

        List<AgendaDTO> list = new ArrayList<>();

        for (Agenda a : agenda) {
            list.add(a.toDTO());
        }
        return list;

    }

    @Override
    public AgendaDTO editAgendaNameByID(AgendaDTO agendaDTO) {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        EditAgendaController controller = new EditAgendaController();
        AgendaDTO dto = null;

        try {
            dto = controller.editAgenda(agendaDTO).toDTO();
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(AgendaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AgendaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dto;
    }

    @Override
    public void remove(AgendaDTO agendaDTO) {
        PersistenceContext.setSettings(this.getPersistenceSettings());
        RemoveAgendaController controller = new RemoveAgendaController();

        controller.removeAgenda(agendaDTO);
    }

}
