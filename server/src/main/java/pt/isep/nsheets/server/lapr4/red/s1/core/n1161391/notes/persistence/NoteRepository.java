/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.persistence;

import eapli.framework.persistence.repositories.Repository;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.domain.Note;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;

/**
 *
 * @author Rúben Santos <1161391@isep.ipp.pt>
 */
public interface NoteRepository extends Repository<Note, Long>{

    List<Note> findNotesOfUser(User user);

    Boolean deleteNoteOfUser(Note note);

    List<Note> findUserNotesInTimeInterval(User user, Date initialDate, Date finalDate);

    Note findNoteByTitle(User user,String title);
}
