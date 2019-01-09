package pt.isep.nsheets.server.lapr4.green.s3.core.n1161838.Notes.application;

import eapli.framework.application.Controller;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Username;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.UserRepository;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.domain.Note;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.persistence.NoteRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.shared.services.NoteDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Joao Rocha <161838>
 */
public class ListFilteredNotesController implements Controller {

    private NoteRepository noteRepository;
    private UserRepository userRepository;

    public ListFilteredNotesController() {
        noteRepository = PersistenceContext.repositories().notes();
        userRepository = PersistenceContext.repositories().users();
    }

    /**
     * Method that finds a user filtered notes in a time interval
     * Uses a REGEXP filter to the search
     *
     * @param filter
     * @param initialDate
     * @param finalDate
     * @return
     * @author Joao Rocha
     */
    public List<NoteDTO> findUsersFilteredNotesInIntervalTime(String username,String filter, Date initialDate, Date finalDate, String type) {
        User user = userRepository.findUserByUsername(new Username(username));
        List<Note> notes = noteRepository.findUserNotesInTimeInterval(user, initialDate, finalDate);

        List<NoteDTO> dtos = noteListOfCertainType(notes,type);
        List<NoteDTO> filteredNotes = new ArrayList<>();
        Pattern pattern = Pattern.compile(filter);
        Matcher matcher;

        if (filter.isEmpty()) {
            return dtos;
        }

        for (NoteDTO note : dtos) {
            boolean flag = true;

            matcher = pattern.matcher(note.getTitle());
            if (!matcher.matches()) {
                matcher = pattern.matcher(note.getDescription());
                if (!matcher.matches()) {
                    flag = false;
                }
            }
            if (flag) filteredNotes.add(note);
        }
        return filteredNotes;
    }

    /**
     * Method that Lists only notes of a certain type (lists or texts or both)
     * It converts each Note to its correspondent DTO
     * @param notes
     * @param type
     * @return
     */
    private List<NoteDTO> noteListOfCertainType(List<Note> notes,String type) {
        List<NoteDTO> notesDTO = new ArrayList<>();
        for (Note note : notes) {
            NoteDTO n = note.toDTO();
            if(type.equalsIgnoreCase("BOTH")){
                notesDTO.add(n);
            }
            else if(n.getNoteType().equalsIgnoreCase(type))
                notesDTO.add(n);
        }
        return notesDTO;
    }


}
