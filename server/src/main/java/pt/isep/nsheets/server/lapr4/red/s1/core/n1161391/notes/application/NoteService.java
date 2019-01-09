/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.application;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Date;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Username;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.UserRepository;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.domain.Note;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.persistence.NoteRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.shared.services.NoteDTO;
import java.util.List;

/**
 *
 * @author Rúben Santos <1161391@isep.ipp.pt>
 */
public class NoteService {

    final NoteRepository noteRepository = PersistenceContext.repositories().notes();
    final UserRepository repo = PersistenceContext.repositories().users();

    public NoteService() {

    }

    public List<Note> getNotesByUser(String username) throws DataConcurrencyException, DataIntegrityViolationException {
        Username u = new Username(username);
        User user = repo.findUserByUsername(u);
        return noteRepository.findNotesOfUser(user);
    }

    public Note createNote(NoteDTO dto) throws DataConcurrencyException, DataIntegrityViolationException {

        String username = dto.getUser();
        Username u = new Username(username);

        User user = repo.findUserByUsername(u);
        Note note = Note.fromDTO(dto, user);
        note = noteRepository.save(note);

        return note;
    }

    public Boolean deleteNote(NoteDTO dto) throws DataConcurrencyException, DataIntegrityViolationException {
        String username = dto.getUser();

        List<Note> list = getNotesByUser(username);
        for (Note n : list) {
            if ((n.toDTO()).getTitle().equals(dto.getTitle()) && (n.toDTO()).getDescription().equals(dto.getDescription())) {
                noteRepository.deleteNoteOfUser(n);
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public Boolean editNote(NoteDTO dto, String newTitle, String newDescription) throws DataConcurrencyException, DataIntegrityViolationException {
        String username = dto.getUser();
        List<Note> list = getNotesByUser(username);
        Username u = new Username(username);
        User user = repo.findUserByUsername(u);
        for (Note n : list) {
            if ((n.toDTO()).getTitle().equals(dto.getTitle()) && (n.toDTO()).getDescription().equals(dto.getDescription())) {
                Note note = new Note(newTitle, newDescription, new Date(), user, n.toDTO().getNoteType(), n.toDTO().getVersions());
                note.addVersion(dto);
                noteRepository.save(note);
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public Boolean addVersion(NoteDTO dto) throws DataConcurrencyException, DataIntegrityViolationException {
        String username = dto.getUser();
        List<Note> list = getNotesByUser(username);
        for (Note n : list) {
            if ((n.toDTO()).getTitle().equals(dto.getTitle()) && (n.toDTO()).getDescription().equals(dto.getDescription())) {
                n.addVersion(n.toDTO());
                noteRepository.save(n);
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
