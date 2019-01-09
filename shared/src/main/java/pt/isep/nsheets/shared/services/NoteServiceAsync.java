/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.WorkbookDTO;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Rúben Santos <1161391@isep.ipp.pt>
 */
public interface NoteServiceAsync {

    public void getNotesByUser(String username,AsyncCallback<List<NoteDTO>> callback);
    
    public void createNote(NoteDTO note,AsyncCallback<NoteDTO> callback);
    
    public void deleteNote(NoteDTO note, AsyncCallback<Boolean> callback);
    
    public void editNote(NoteDTO note, String newTitle, String newDescription, AsyncCallback<Boolean> callback);

    public void findUserFilteredNotesInIntervalTime(String username, String filter, Date initialDate, Date finalDate,String type, AsyncCallback<List<NoteDTO>> callback);

    public void addVersion(NoteDTO note, AsyncCallback<Boolean> async);

    public void openNote(String username,NoteDTO dto,AsyncCallback<NoteDTO> callback);

    public void exportNotesToSpreadSheet(WorkbookDTO workbook, String spreadsheet, List<NoteDTO> notes, int startRow, int startCol, AsyncCallback<WorkbookDTO> callback);

}
