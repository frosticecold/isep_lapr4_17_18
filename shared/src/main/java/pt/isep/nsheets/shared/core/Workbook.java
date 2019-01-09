/*
 * Copyright (c) 2005 Einar Pehrson <einar@pehrson.nu>.
 *
 * This file is part of
 * CleanSheets - a spreadsheet application for the Java platform.
 *
 * CleanSheets is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * CleanSheets is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CleanSheets; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package pt.isep.nsheets.shared.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.SpreadsheetDTO;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.WorkbookDTO;

import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1131485.macro.domain.Macro;
import pt.isep.nsheets.shared.lapr4.s2.lang.n1160911.GlobalVariable.GlobalVariable;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;

/**
 * A workbook which can contain several spreadsheets.
 *
 * @author Einar Pehrson
 */
/**
 * 1090657 - Raúl Correia Sprint 2
 *
 * Modification so it can persist in Database Modification so it can have
 * multiple spreadsheets
 */
@Entity
public class Workbook implements Iterable<Spreadsheet>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    /**
     * The unique version identifier used for serialization
     */
    private static final long serialVersionUID = -6324252462576447242L;
    /**
     * The spreadsheets of which the workbook consists
     */
    @OneToMany(targetEntity = SpreadsheetImpl.class)
    private List<Spreadsheet> spreadsheets = new ArrayList<>();

    @ElementCollection
    private List<Macro> macros = new ArrayList<>();

    /**
     * The cell listeners that have been registered on the cell
     */
    private transient List<WorkbookListener> listeners = new ArrayList<>();

    
    private transient List<GlobalVariable> globals = new ArrayList<>();
    /**
     * The number of spreadsheets that have been created in the workbook
     */
    private int createdSpreadsheets;

    private Boolean isPublic = Boolean.TRUE;
    
    private int activeSpread=0;

    /**
     * Creates a new empty workbook.
     */
    public Workbook() {
        this.spreadsheets = new ArrayList<>();
        this.spreadsheets.add(new SpreadsheetImpl(this, getNextSpreadsheetTitle()));
    }

    public boolean isIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    /**
     * Creates a new workbook, which initially contains the given number of
     * blank spreadsheets.
     *
     * @param sheets the number of sheets to create initially
     */
    public Workbook(int sheets) {
        for (int i = 0; i < sheets; i++) {
            spreadsheets.add(new SpreadsheetImpl(this, getNextSpreadsheetTitle()));
        }
    }

    public Workbook(Boolean isPublic) {
        this.spreadsheets = new ArrayList<>();
        this.isPublic = isPublic;
    }

    /**
     * DEAD CORE - 1090657 Creates a new workbook, which initially contains the
     * given number of blank spreadsheets.
     *
     * @param sheets the number of sheets to create initially
     */
    public Workbook(int sheets, WorkbookDescriptionDTO workbookDescription) {
        for (int i = 0; i < sheets; i++) {
            spreadsheets.add(new SpreadsheetImpl(this, getNextSpreadsheetTitle()));
        }
    }

    /**
     * New Implemetantion - 1090657 Creates a new workbook, from a DTO
     *
     */
    public Workbook(final WorkbookDTO dto) {
        this.isPublic = dto.isPublic();
        this.createdSpreadsheets = dto.createdSpreadsheets;
        for (SpreadsheetDTO s : dto.spreadsheets) {
            spreadsheets.add(new SpreadsheetImpl(this, getNextSpreadsheetTitle(), s.content));
        }

    }

    /**
     * Creates a new workbook, using the given content matrix to create
     * spreadsheets initially.
     *
     * @param contents the content matrices to use when creating spreadsheets
     */
    public Workbook(String[][]... contents) {
        spreadsheets = new ArrayList<>();
        for (String[][] spreadsheetcontent : contents) {
            spreadsheets.add(new SpreadsheetImpl(this, getNextSpreadsheetTitle(), spreadsheetcontent));
        }
        this.macros = new ArrayList<>();
    }

    /**
     * Adds a blank spreadsheet to the end of the workbook.
     */
    public void addSpreadsheet() throws Exception {
        Spreadsheet spreadsheet = new SpreadsheetImpl(this,
                getNextSpreadsheetTitle());
        spreadsheets.add(spreadsheet);
        fireSpreadsheetInserted(spreadsheet, spreadsheets.size() - 1);
    }

    /**
     * Adds a new spreadsheet to the workbook, in which cells are initialized
     * with data from the given content matrix.
     *
     * @param content the contents of the cells in the spreadsheet
     */
    public void addSpreadsheet(String[][] content) throws Exception {
//        throw new Exception("Can only have on spreadsheet");
        Spreadsheet spreadsheet = new SpreadsheetImpl(this,
                getNextSpreadsheetTitle(), content);
        spreadsheets.add(spreadsheet);
        fireSpreadsheetInserted(spreadsheet, spreadsheets.size() - 1);
    }

    /**
     * Returns the title to be used for the next spreadsheet added.
     *
     * @return the title to be used for the next spreadsheet added
     */
    private String getNextSpreadsheetTitle() {
        return SpreadsheetImpl.BASE_TITLE + " " + (createdSpreadsheets++ + 1);
    }

    /**
     * Adds a new blank spreadsheet to the workbook.
     *
     * @param spreadsheet spreadsheet
     */
    public void removeSpreadsheet(Spreadsheet spreadsheet) throws Exception {
//        throw new Exception("Cannot remove only spreadsheet in workbook!");
        spreadsheets.remove(spreadsheet);
        // Remove references to the spreadsheet in remaining spreadsheets!
        fireSpreadsheetRemoved(spreadsheet);
    }

    /**
     * Returns the spreadsheet at the given index.
     *
     * @param index the index of the spreadsheet in the workbook
     * @return the spreadsheet at the given index
     * @throws IndexOutOfBoundsException if the index is out of range (index
     * less than 0 or index greater or equal |spreadsheets|)
     */
    public Spreadsheet getSpreadsheet(int index) throws IndexOutOfBoundsException {
        return spreadsheets.get(index);
    }

    /**
     * Returns the number of spreadsheets in the the workbook.
     *
     * @return the number of spreadsheets in the the workbook
     */
    public int getSpreadsheetCount() {
        return spreadsheets.size();
    }

    /**
     * Returns an iterator over the spreadsheets in the workbook.
     *
     * @return an iterator over the spreadsheets in the workbook
     */
    public Iterator<Spreadsheet> iterator() {
        return spreadsheets.iterator();
    }

    /*
     * EVENT HANDLING
     */
    /**
     * Registers the given listener on the workbook.
     *
     * @param listener the listener to be added
     */
    public void addWorkbookListener(WorkbookListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes the given listener from the workbook.
     *
     * @param listener the listener to be removed
     */
    public void removeWorkbookListener(WorkbookListener listener) {
        listeners.remove(listener);
    }

    /**
     * Returns the listeners that have been registered on the workbook.
     *
     * @return the listeners that have been registered on the workbook
     */
    public WorkbookListener[] getWorkbookListeners() {
        return listeners.toArray(new WorkbookListener[listeners.size()]);
    }
    
    
    public void addGlobalVariablesToList(GlobalVariable gl) {
        globals.add(gl);
    }

    public void removeGlobalVariablesFromList(GlobalVariable gl) {
        globals.remove(gl);
    }

    public List<GlobalVariable> returnListGlobalVariables() {
        return globals;
    }

    /**
     * Notifies all registered listeners that a spreadsheet has been inserted.
     *
     * @param spreadsheet the spreadsheet that was inserted
     * @param index the index at which the spreadsheet was inserted
     */
    private void fireSpreadsheetInserted(Spreadsheet spreadsheet, int index) {
        for (WorkbookListener listener : listeners) {
            listener.spreadsheetInserted(spreadsheet, index);
        }
    }

    /**
     * Notifies all registered listeners that a spreadsheet has been removed.
     *
     * @param spreadsheet the spreadsheet that was removed
     */
    private void fireSpreadsheetRemoved(Spreadsheet spreadsheet) {
        for (WorkbookListener listener : listeners) {
            listener.spreadsheetRemoved(spreadsheet);
        }
    }

    /**
     * Notifies all registered listeners that a spreadsheet has been renamed.
     *
     * @param spreadsheet the spreadsheet that was renamed
     */
    @SuppressWarnings("unused")
    private void fireSpreadsheetRenamed(Spreadsheet spreadsheet) {
        for (WorkbookListener listener : listeners) {
            listener.spreadsheetRenamed(spreadsheet);
        }
    }

    /**
     * Customizes deserialization by recreating the listener list.
     *
     * occur
     * @throws ClassNotFoundException If the class of a serialized object cannot
     * be found.
     */
    // java.io.ObjectInputStream not supportted in GWT !
//	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
//		stream.defaultReadObject();
//		listeners = new ArrayList<WorkbookListener>();
//	}

    /**
     * @return the list of macros saved in the workbook
     * @author Rui Almeida <1160818>
     */
    public List<Macro> macros() { return this.macros; }

    /**
     * Adds a macro to the current list of macros
     * @param macro - macro to add
     * @return true if macro was added with success, false if not
     * @author Rui Almeida <1160818>
     */
    public boolean addMacro(Macro macro) { return this.macros.add(macro); }

    /**
     * Removes a macro from the current list of macros given a name
     * @param macro - the macro name
     * @return true if macro was removed with success, false if not
     * @author Rui Almeida <1160818>
     */
    public boolean removeMacro(String macro) {
        for (Macro m : macros) {
            if (m.name().equals(macro)) return macros.remove(m);
        }
        return false;
    }

    public WorkbookDTO toDTO() {
        List<SpreadsheetDTO> list = new ArrayList<>();
        for (Spreadsheet s : spreadsheets) {
            SpreadsheetDTO dto = s.toDTO();
            list.add(dto);
        }
        return new WorkbookDTO(list, createdSpreadsheets, isPublic);
    }

    /**
     * Saves on a object data received from DTO Todo Macros and variables
     *
     * @param dto
     * @throws Exception
     */
    public void fromDTO(final WorkbookDTO dto) throws Exception {
        /**
         * Reset all data before updating
         */
        macros.clear();
        listeners.clear();
        globals.clear();
        spreadsheets.clear();

        /**
         * Transform DTO data
         */
        for (SpreadsheetDTO s : dto.getSpreadsheets()) {
            addSpreadsheet(s.getContent());
        }
        this.isPublic = dto.isPublic();
        this.createdSpreadsheets = dto.getCreatedSpreadsheets();
    }

    /**
     * @return spreadsheets of this workbook.
     */
    public List<Spreadsheet> getSpreadSheets() {

        return this.spreadsheets;
    }
    
    public Spreadsheet getActiveSpread(){
        return spreadsheets.get(activeSpread);
    }
    
    public void setActiveSpread(int activeSpread){
        this.activeSpread = activeSpread;
    }
}
