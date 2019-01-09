/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.TypedQuery;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.domain.Agenda;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.persistence.AgendaRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;
import pt.isep.nsheets.shared.services.AgendaDTO;

/**
 *
 * @author Car
 */
public class JpaAgendaRepository extends NSheetsJpaRepositoryBase<Agenda, Long> implements AgendaRepository {

    public JpaAgendaRepository(PersistenceSettings settings) {
        super(settings);
    }

    @Override
    public void removeAgenda(Agenda agenda) {
        delete(agenda);
    }

    @Override
    public List<Agenda> findAgendaByName(String name) {
        final TypedQuery<Agenda> query = entityManager().createQuery(
                "SELECT a "
                + "FROM Agenda a "
                + "WHERE a.name=:name ", Agenda.class);

        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public Agenda updateAgendaName(String name, String newName) {
        System.out.println(name+"GUE");
        Agenda a = findAgendaByName(name).get(0);
        a.setName(newName);
        try {
            save(a);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(JpaAgendaRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(JpaAgendaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    

    

}
