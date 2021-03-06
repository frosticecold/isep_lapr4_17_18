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

import com.google.gwt.user.client.rpc.IsSerializable;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import org.eclipse.persistence.annotations.VariableOneToOne;
import pt.isep.nsheets.shared.core.formula.Formula;
import pt.isep.nsheets.shared.core.formula.Reference;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaCompilationException;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaCompiler;
import pt.isep.nsheets.shared.core.formula.util.ReferenceTransposer;
import pt.isep.nsheets.shared.ext.CellExtension;
import pt.isep.nsheets.shared.ext.Extension;
import pt.isep.nsheets.shared.lapr4.red.s2.core.n1161018.DTO.CellDTO;
import pt.isep.nsheets.shared.services.ChartDTO;

//import java.io.ObjectInputStream;		// not supported in GWT
//import java.io.ObjectOutputStream;		// not supported in GWT
/**
 * The implementation of the <code>Cell</code> interface.
 *
 * @author Einar Pehrson
 */
@Entity
public class CellImpl implements Cell, Serializable, IsSerializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**
     * The unique version identifier used for serialization
     */
    private static final long serialVersionUID = 926673794084390673L;

    /**
     * The spreadsheet to which the cell belongs
     */
    @VariableOneToOne
    private Spreadsheet spreadsheet;

    /**
     * The address of the cell
     */
    @Embedded
    private Address address;

    /**
     * The value of the cell
     */
    @Embedded
    private Value value = new Value();

    /**
     * Stuff of chart
     *
     * Can be null
     *
     * @author PedroEmanuelCoelho <1131485@isep.ipp.pt>
     */
    @Embedded
    @Column(name = "given_chart")
    private ChartDTO chart;

    /**
     * The content of the cell
     */
    private String content = "";

    /**
     * The cell's formula
     */
    private Formula formula;

    /**
     * The cell's precedents
     */
    @ElementCollection
    private Set<Cell> precedents = new TreeSet<Cell>();

    /**
     * The cell's dependents
     */
    @ElementCollection
    private Set<Cell> dependents = new TreeSet<Cell>();

    /**
     * The cell listeners that have been registered on the cell
     */
    private transient List<CellListener> listeners
            = new ArrayList<CellListener>();

    /**
     * The cell extensions that have been instantiated
     */
    private transient Map<String, CellExtension> extensions
            = new HashMap<String, CellExtension>();
    /**
     * Customizes serialization by writing extensions separately.
     *
     * @param stream the object output stream to which the object is to be
     * written
     * @throws IOException If any of the usual Input/Output related exceptions
     * occur
     */
    // not supported in gwt
//	private void writeObject(ObjectOutputStream stream) throws IOException {
//		stream.defaultWriteObject();
//
//		// Writes extensions
//		stream.writeInt(extensions.size());
//		for (CellExtension extension : extensions.values())
//			stream.writeObject(extension);
//	}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Creates a new cell at the given address in the given spreadsheet. (not
     * intended to be used directly).
     *
     * @param spreadsheet the spreadsheet
     * @param address the address of the cell
     * @see Spreadsheet#getCell(Address)
     */
    CellImpl(Spreadsheet spreadsheet, Address address) {
        this.spreadsheet = spreadsheet;
        this.address = address;
    }

    /**
     * Creates a new cell at the given address in the given spreadsheet,
     * initialized with the given content (not intended to be used directly).
     *
     * @param spreadsheet the spreadsheet
     * @param address the address of the cell
     * @param content the content of the cell
     * @throws FormulaCompilationException if an incorrectly formatted formula
     * was entered
     * @see Spreadsheet#getCell(Address)
     */
    public CellImpl(Spreadsheet spreadsheet, Address address, String content) throws FormulaCompilationException {
        this(spreadsheet, address);
        storeContent(content);
        reevaluate();
    }

    /*
     * LOCATION
     */
    public CellImpl() {
    }

    public Spreadsheet getSpreadsheet() {
        return spreadsheet;
    }

    /*
     * VALUE
     */
    public Address getAddress() {
        return address;
    }

    public Value getValue() {
        return value;
    }

    /**
     * Returns chart info
     *
     * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
     *
     *
     * @return
     */
    @Override
    public ChartDTO chart() {

        return this.chart;
    }

    /**
     * Updates the cell's value, and fires an event if it changed.
     */
    private void reevaluate() {
        Value oldValue = value;

        // Fetches the new value
        Value newValue;
        if (formula != null) {
            try {
                newValue = formula.evaluate();
            } catch (IllegalValueTypeException e) {
                newValue = new Value(e);
            }
        } else {
            newValue = Value.parseValue(content);
        }

        // Stores value
        value = newValue;

        // Checks for change
        if (!newValue.equals(oldValue)) {
            fireValueChanged();
        }
    }

    /*
     * CONTENT
     */
    /**
     * Notifies all registered listeners that the value of the cell changed.
     */
    private void fireValueChanged() {
        if (listeners != null) {
            for (CellListener listener : listeners) {
                listener.valueChanged(this);
            }
        }
        if (extensions != null) {
            for (CellExtension extension : extensions.values()) {
                extension.valueChanged(this);
            }
        }
        if (dependents != null) {
            // Notifies dependents of the changed value
            for (Cell dependent : dependents) {
                if (dependent instanceof CellImpl) {
                    ((CellImpl) dependent).reevaluate();
                }
            }
        }
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) throws FormulaCompilationException {
        if (!this.content.equals(content)) {
            storeContent(content);
            fireContentChanged();
            reevaluate();
        }
    }

//    @Override
//    public void setNoteContent(String content){
//        if(!this.content.equalsIgnoreCase(content))
//        this.content = content;
//    }
    @Override
    public Formula getFormula() {
        return formula;
    }

    @Override
    public void clear() {
        try {
            setContent("");
        } catch (FormulaCompilationException e) {
        }
        fireCellCleared();
    }

    /**
     * Updates the chart content stored on Cell
     *
     * @author PedroEmanuelCoelho <1131485@isep.ipp.pt>
     */
    @Override
    public boolean storeChart(ChartDTO dto) {

        boolean ret = false;

        if (dto.equals(dto)) {
            this.chart = dto;
            ret = true;
        }

        return ret;
    }

    /**
     * Updates the cell's content, and registers dependencies.
     *
     * @param content the content to store
     * @throws FormulaCompilationException if an incorrectly formatted formula
     * was entered
     */
    private void storeContent(String content) throws FormulaCompilationException {
        // Parses formula
        Formula formula = null;
        if (content.length() > 1) {
            formula = FormulaCompiler.getInstance().compile(this, content);
        }

        // Stores content and formula
        this.content = content;
        this.formula = formula;
        updateDependencies();
    }

    /**
     * Updates the cell's dependencies.
     */
    private void updateDependencies() {
        // Deregisters as dependent with each old precedent
        for (Cell precedent : precedents) {
            ((CellImpl) precedent).removeDependent(this);
        }
        precedents.clear();

        if (formula != null) // Registers as dependent with each new precedent
        {
            for (Reference reference : formula.getReferences()) {
                for (Cell precedent : reference.getCells()) {
                    if (!this.equals(precedent)) {
                        precedents.add(precedent);
                        ((CellImpl) precedent).addDependent(this);
                    }
                }
            }
        }
    }

    /**
     * Notifies all registered listeners that the content of the cell changed.
     */
    private void fireContentChanged() {
        if (listeners != null && extensions != null) {
            for (CellListener listener : listeners) {
                listener.contentChanged(this);
            }
            for (CellExtension extension : extensions.values()) {
                extension.contentChanged(this);
            }
        }
    }

    /*
     * DEPENDENCIES
     */
    /**
     * Notifies all registered listeners that the cell was cleared.
     */
    private void fireCellCleared() {
        for (CellListener listener : listeners) {
            listener.cellCleared(this);
        }
        for (CellExtension extension : extensions.values()) {
            extension.cellCleared(this);
        }
    }

    public SortedSet<Cell> getPrecedents() {
        return new TreeSet<Cell>(precedents);
    }

    public SortedSet<Cell> getDependents() {
        return new TreeSet<Cell>(dependents);
    }

    /**
     * Adds the given cell as a dependent of this cell, to be notified when its
     * value changes.
     *
     * @param cell the dependent to add
     */
    private void addDependent(Cell cell) {
        dependents.add(cell);
        fireDependentsChanged();
    }

    /**
     * Removes the given cell as a dependent of this cell.
     *
     * @param cell the dependent to remove
     */
    private void removeDependent(Cell cell) {
        dependents.remove(cell);
        fireDependentsChanged();
    }

    /**
     * Notifies all registered listeners that the content of the cell changed.
     */
    private void fireDependentsChanged() {
        for (CellListener listener : listeners) {
            listener.dependentsChanged(this);
        }
        for (CellExtension extension : extensions.values()) {
            extension.dependentsChanged(this);
        }
    }

    public void copyFrom(Cell source) {
        // Copies content
        if (source.getFormula() == null) {
            try {
                setContent(source.getContent());
            } catch (FormulaCompilationException e) {
            }
        } else {
            // Copies and transposes formula
            this.formula = new Formula(this,
                    new ReferenceTransposer(
                            getAddress().getColumn() - source.getAddress().getColumn(),
                            getAddress().getRow() - source.getAddress().getRow()
                    ).getExpression(source.getFormula().getExpression())
            );
            this.content = source.getContent().charAt(0) + formula.toString();
            updateDependencies();
            fireContentChanged();
            reevaluate();
        }
        fireCellCopied(source);
    }

    public void moveFrom(Cell source) {
        // Change the address of the source cell
        // Remove the target cell from the spreadsheet
        // Flag the target cell as overwritten!

        // fireCellCopied(source);
    }

    /*
     * EVENT HANDLING
     */
    /**
     * Notifies all registered listeners that the cell was copied (or moved).
     *
     * @param source the cell from which data was copied
     */
    private void fireCellCopied(Cell source) {
        for (CellListener listener : listeners) {
            listener.cellCopied(this, source);
        }
        for (CellExtension extension : extensions.values()) {
            extension.cellCopied(this, source);
        }
    }

    public void addCellListener(CellListener listener) {
        listeners.add(listener);
    }

    public void removeCellListener(CellListener listener) {
        listeners.remove(listener);
    }

    /*
     * EXTENSIONS
     */
    public CellListener[] getCellListeners() {
        return listeners.toArray(new CellListener[listeners.size()]);
    }

    /**
     * @return a Cell Data Sack
     */
    @Override
    public CellDTO toCellDTO() {

        CellDTO dto = new CellDTO(this.address, this.value.toString());

        return dto;
    }

    public Cell getExtension(String name) {
        // Looks for an existing cell extension
        CellExtension extension = extensions.get(name);
        if (extension == null) {
            // Creates a new cell extension
            Extension x = null; // ExtensionManager.getInstance().getExtension(name);
            if (x != null) {
                extension = x.extend(this);
                if (extension != null) {
                    extensions.put(name, extension);
                }
            }
        }
        return extension;
    }

    /*
     * GENERAL
     */
    public void addExtension(CellExtension extension) {

        if (!extensions.containsKey(extension.getName()) && extension != null) {

            extensions.put(extension.getName(), extension);
        }

    }

    /**
     * Compares this cell with the specified cell for order, by comparing their
     * addresses.
     *
     * @param cell the cell to be compared
     * @return a negative integer, zero, or a positive integer as this object is
     * less than, equal to, or greater than the specified object.
     */
    public int compareTo(Cell cell) {
        if (spreadsheet != cell.getSpreadsheet()) {
            return -1;
        } else {
            return address.compareTo(cell.getAddress());
        }
    }

    /**
     * Returns a string representation of the cell.
     *
     * @return the cell's content
     */
    public String toString() {
        return address.toString();
    }

    /**
     * Customizes deserialization by recreating the listener list and by
     * catching exceptions when extensions are not found.
     *
     * @param stream the object input stream from which the object is to be read
     * @throws IOException If any of the usual Input/Output related exceptions
     * occur
     * @throws ClassNotFoundException If the class of a serialized object cannot
     * be found.
     */
    // not supported in gwt
//	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
//		stream.defaultReadObject();
//		listeners = new ArrayList<CellListener>();
//
//		// Reads extensions
//		extensions = new HashMap<String, CellExtension>();
//		int extCount = stream.readInt();
//		for (int i = 0; i < extCount; i++) {
//			try {
//				CellExtension extension = (CellExtension)stream.readObject();
//				extensions.put(extension.getName(), extension);
//			} catch (ClassNotFoundException YellowExtension) {}
//		}
//	}
    /**
     * Customizes serialization by writing extensions separately.
     *
     * @param stream the object output stream to which the object is to be
     * written
     * @throws IOException If any of the usual Input/Output related exceptions
     * occur
     */
    // not supported in gwt
//	private void writeObject(ObjectOutputStream stream) throws IOException {
//		stream.defaultWriteObject();
//
//		// Writes extensions
//		stream.writeInt(extensions.size());
//		for (CellExtension extension : extensions.values())
//			stream.writeObject(extension);
//	}
}
