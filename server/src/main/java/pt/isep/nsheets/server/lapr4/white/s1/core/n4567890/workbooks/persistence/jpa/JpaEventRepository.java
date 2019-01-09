/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa;

import eapli.framework.persistence.repositories.impl.jpa.JpaTxRepository;
import java.util.ArrayList;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.domain.Agenda;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.domain.Event;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.persistence.EventRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;

/**
 *
 * @author MFerreira
 */
public class JpaEventRepository extends JpaTxRepository<Event, Long> implements EventRepository {

    public JpaEventRepository(PersistenceSettings settings) {
        super(settings.getPersistenceUnitName());
    }

    @Override
    public Boolean removeEventByTitleDescription(String title, String description) {
        Query q = entityManager().
                createQuery("DELETE FROM Event "
                        + "WHERE id>=0");

        //q.setParameter("title", title);
        //q.setParameter("description", description);
        return true;
    }

    @Override
    public void removeEvent(Event entity) {
        delete(entity);
    }

    @Override
    public Iterable<Event> findByTitleAndDescription(String title, String description) {
        Query q = null;
        try {

            q = entityManager().
                    createQuery("SELECT event FROM Event event "
                            + "WHERE event.title=:title "
                            + "AND event.description=:description", Event.class);
            q.setParameter("title", title);
            q.setParameter("description", description);

        } catch (NoResultException e) {

        }
        return q.getResultList();
    }

    @Override
    public Event findByTitleAndDescriptionSingle(String title, String description) {
        Query q = null;
        try {
            q = entityManager().
                    createQuery("SELECT event FROM Event event "
                            + "WHERE event.title=:title "
                            + "AND event.description=:description", Event.class);
            q.setParameter("title", title);
            q.setParameter("description", description);

            return (Event) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public ArrayList<Event> eventsAgenda(Agenda agenda) {
        return (ArrayList<Event>) match("event.calendar=:agenda");
    }

}
