/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.WorkbookDTO;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Rúben Santos <1161391@isep.ipp.pt>
 */
@RemoteServiceRelativePath("noteService")
public interface NoteService extends RemoteService{
    public Boolean addVersion(NoteDTO note);
    
    public List<NoteDTO> getNotesByUser(String username);
    
    public NoteDTO createNote(NoteDTO note);
    
    public Boolean deleteNote(NoteDTO note);
    
    public Boolean editNote(NoteDTO note, String newTitle, String newDescription);

    public List<NoteDTO> findUserFilteredNotesInIntervalTime(String username,String filter, Date initialDate, Date finalDate,String type);

    public NoteDTO openNote(String username,NoteDTO dto);

    public WorkbookDTO exportNotesToSpreadSheet(WorkbookDTO workbook, String spreadsheet, List<NoteDTO> notes, int startRow, int startCol);

}
