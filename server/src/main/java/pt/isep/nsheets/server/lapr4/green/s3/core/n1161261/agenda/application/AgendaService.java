/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.application;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.List;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.domain.Agenda;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.persistence.AgendaRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.shared.services.AgendaDTO;

/**
 *
 * @author Joana Oliveira
 */
public class AgendaService {

    public Agenda addAgenda(AgendaDTO dto) throws DataConcurrencyException, DataIntegrityViolationException {

        final AgendaRepository agendaRepository = PersistenceContext.repositories().agendas();

        Agenda agenda = Agenda.fromDTO(dto);

        for (Agenda a : agendaRepository.findAll()) {
            if (a.getName().equals(agenda.getName()) && a.getColor().equals(agenda.getColor())) {
                return null;
            }
        }
        agendaRepository.save(agenda);

        return agenda;

    }

    public Iterable<Agenda> listAllAgendas() {

        final AgendaRepository agendaRepository = PersistenceContext.repositories().agendas();
        Iterable<Agenda> a = agendaRepository.findAll();
        return a;
    }

    public void removeAgenda(AgendaDTO dto) {
        final AgendaRepository agendaRepository = PersistenceContext.repositories().agendas();
        Agenda a = agendaRepository.findAgendaByName(dto.getName()).get(0);
        agendaRepository.removeAgenda(a);

    }

    public void remove(AgendaDTO dto) {
        final AgendaRepository agendaRepository = PersistenceContext.repositories().agendas();
        Agenda a = agendaRepository.findAgendaByName(dto.getName()).iterator().next();
        agendaRepository.removeAgenda(a);
    }

    public Agenda findAgendaByName(String name) {
        final AgendaRepository repo = (AgendaRepository) PersistenceContext.repositories();

        List<Agenda> agenda = repo.findAgendaByName(name);

        return (Agenda) agenda;
    }

    public Agenda editAgenda(AgendaDTO dto) throws DataConcurrencyException, DataIntegrityViolationException {
        final AgendaRepository repo = PersistenceContext.repositories().agendas();
        Agenda a = Agenda.fromDTO(dto);

        repo.save(a);

        return a;
    }

    public Agenda updateName(AgendaDTO dto) throws Exception {
        final AgendaRepository repo = PersistenceContext.repositories().agendas();

        return repo.updateAgendaName(dto.getNameBefore(), dto.getName());
    }

}
