/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.application;

import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.domain.Note;
import pt.isep.nsheets.shared.services.NoteDTO;
import java.util.List;
/**
 *
 * @author Rúben Santos <1161391@isep.ipp.pt>
 */
public class NoteController implements Controller{

    public Boolean addVersion(NoteDTO dto) throws DataConcurrencyException, DataIntegrityViolationException{
        return new NoteService().addVersion(dto);
    }
    
    public Note createNote(NoteDTO dto) throws DataConcurrencyException, DataIntegrityViolationException {
        return new NoteService().createNote(dto);
    }
    
    public List<Note> getNotesByUser(String username) throws DataConcurrencyException, DataIntegrityViolationException{
        return new NoteService().getNotesByUser(username);
    }
    
    public Boolean deleteNote(NoteDTO dto) throws DataConcurrencyException, DataIntegrityViolationException{
        return new NoteService().deleteNote(dto);
    }
    
    public Boolean editNote(NoteDTO dto, String newTitle,String newDescription) throws DataConcurrencyException, DataIntegrityViolationException{
        return new NoteService().editNote(dto, newTitle, newDescription);
    }
    
    public NoteController(){
        
    }
    
    
}
