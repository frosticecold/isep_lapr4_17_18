/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa;

import eapli.framework.persistence.repositories.impl.jpa.JpaTxRepository;

import java.util.*;

import pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.domain.Note;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.persistence.NoteRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;

import java.util.function.Consumer;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;

import javax.persistence.Query;


/**
 *
 * @author Rúben Santos <1161391@isep.ipp.pt>
 */
public class JpaNoteRepository extends JpaTxRepository<Note, Long> implements NoteRepository{

    public JpaNoteRepository(PersistenceSettings settings) {
        super(settings.getPersistenceUnitName());
    }

    @Override
    public List<Note> findNotesOfUser(User user) {
        final Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        return match("e.user=:user", params);
    }

    @Override
    public Boolean deleteNoteOfUser(Note note){
        delete(note);
        return Boolean.TRUE;
    }

    @Override
    public void forEach(Consumer<? super Note> action) {
        super.forEach(action);
    }

    @Override
    public Spliterator<Note> spliterator() {
        return super.spliterator();
    }

    /**
     * Method that finds a user notes that were created in an interval of time
     * @param user
     * @param initialDate
     * @param finalDate
     * @return
     */
    @Override
    public List<Note> findUserNotesInTimeInterval(User user, Date initialDate, Date finalDate) {
        final Query q;
        q = entityManager().createQuery("SELECT w FROM Note w " +
                "WHERE w.user = :user " +
                "AND w.timestamp > :initialDate " +
                "AND w.timestamp < :finalDate ");
        q.setParameter("user",user);
        q.setParameter("initialDate",initialDate);
        q.setParameter("finalDate",finalDate);
        return q.getResultList();
    }

    @Override
    public Note findNoteByTitle(User user,String title){
        final Query q;
        q = entityManager().createQuery("SELECT w FROM Note w " +
                "WHERE w.title = :title " +
                "AND w.user = :user");
        q.setParameter("title",title);
        q.setParameter("user",user);
        return (Note)q.getSingleResult();
    }

}
