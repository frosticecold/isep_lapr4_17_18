package pt.isep.nsheets.server.lapr4.green.s3.core.n1161838.Notes.application;

import eapli.framework.application.Controller;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Username;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.UserRepository;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.persistence.NoteRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.shared.services.NoteDTO;

/**
 * @author Joao Rocha <161838>
 */
public class OpenNoteController implements Controller {

    private NoteRepository noteRepository;
    private UserRepository userRepository;

    public OpenNoteController(){
        noteRepository = PersistenceContext.repositories().notes();
        userRepository = PersistenceContext.repositories().users();
    }

    public NoteDTO openNote(String username, NoteDTO noteDTO){
        Username name = new Username(username);
        User user = userRepository.findUserByUsername(name);
        NoteDTO dto = noteRepository.findNoteByTitle(user,noteDTO.getTitle()).toDTO();
        return dto;
    }
}
