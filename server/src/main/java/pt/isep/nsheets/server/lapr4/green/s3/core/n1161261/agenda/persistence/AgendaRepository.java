/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.persistence;

import eapli.framework.persistence.repositories.Repository;
import java.util.List;
import java.util.Optional;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.domain.Agenda;
import pt.isep.nsheets.shared.services.AgendaDTO;

/**
 *
 * @author Joana Oliveira
 */
public interface AgendaRepository extends Repository<Agenda, Long> {

    void removeAgenda(Agenda agenda);

    public List<Agenda> findAgendaByName(String name);

    public Agenda updateAgendaName(String name, String newName);

}
