/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import pt.isep.nsheets.server.lapr4.green.s3.core.n1161838.Notes.application.ExportNoteController;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161838.Notes.application.ListFilteredNotesController;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161838.Notes.application.OpenNoteController;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.application.NoteController;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.domain.Note;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.SpreadsheetDTO;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.WorkbookDTO;
import pt.isep.nsheets.shared.services.DataException;
import pt.isep.nsheets.shared.services.NoteDTO;
import pt.isep.nsheets.shared.services.NoteService;

/**
 *
 * @author Rúben Santos <1161391@isep.ipp.pt>
 */
public class NoteServiceImpl extends RemoteServiceServlet implements NoteService {

    private PersistenceSettings getPersistenceSettings() {

        Properties props = new Properties();

        props.put("persistence.repositoryFactory",
                "pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa.JpaRepositoryFactory");
        props.put("persistence.persistenceUnit", "lapr4.NSheetsPU");

        return new PersistenceSettings(props);
    }

    @Override
    public List<NoteDTO> getNotesByUser(String username) {

        PersistenceContext.setSettings(this.getPersistenceSettings());
        NoteController controller = new NoteController();

        List<Note> list = new ArrayList<>();
        try {
            list = controller.getNotesByUser(username);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(NoteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<NoteDTO> DTOList = new ArrayList<>();
        for (Note note : list) {
            DTOList.add(note.toDTO());
        }
        return DTOList;
    }

    @Override
    public NoteDTO createNote(NoteDTO note) {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        NoteController controller = new NoteController();

        Note newNote = null;

        try {
            newNote = controller.createNote(note);

        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {

            try {
                throw new DataException((Throwable) ex);
            } catch (DataException ex1) {
                Logger.getLogger(NoteServiceImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

        return newNote.toDTO();
    }

    @Override
    public Boolean deleteNote(NoteDTO note) {
        NoteController controller = new NoteController();
        try {
            controller.deleteNote(note);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(NoteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    @Override
    public Boolean editNote(NoteDTO note, String newTitle, String newDescription){
        NoteController controller = new NoteController();
        try{
            controller.editNote(note, newTitle, newDescription);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(NoteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean addVersion(NoteDTO note) {
        NoteController controller = new NoteController();
        
        try {
            controller.addVersion(note);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(NoteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(NoteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return Boolean.FALSE;
        }
        
        return Boolean.TRUE;
    }

    @Override
    public List<NoteDTO> findUserFilteredNotesInIntervalTime(String username,String filter, Date initialDate, Date finalDate,String type){
        ListFilteredNotesController controller = new ListFilteredNotesController();
        List<NoteDTO> notes = controller.findUsersFilteredNotesInIntervalTime(username,filter,initialDate,finalDate,type);
        return notes;
    }

    @Override
    public NoteDTO openNote(String username,NoteDTO dto){
        OpenNoteController controller = new OpenNoteController();
        NoteDTO note = controller.openNote(username,dto);
        return note;
    }

    @Override
    public WorkbookDTO exportNotesToSpreadSheet(WorkbookDTO workbook, String spreadsheet, List<NoteDTO> notes, int startRow, int startCol){
        ExportNoteController controller = new ExportNoteController();
        SpreadsheetDTO spread = null;
//
        Logger logger2 = Logger.getLogger("logger");
        logger2.log(Level.SEVERE, "note service 1");
        for(SpreadsheetDTO s : workbook.getSpreadsheets()){

            if(s.getTitle().equalsIgnoreCase(spreadsheet)){
                spread = s;
            }
        }

        Logger logger = Logger.getLogger("logger");
        logger.log(Level.SEVERE, "note service");
        return controller.exportNotesToSpreadsheet(workbook,spread,notes,startRow,startCol);
    }
}
