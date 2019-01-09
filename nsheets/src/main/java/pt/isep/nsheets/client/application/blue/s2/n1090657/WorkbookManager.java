package pt.isep.nsheets.client.application.blue.s2.n1090657;

import pt.isep.nsheets.shared.core.Workbook;

import java.util.ArrayList;
import java.util.List;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;

/**
 * Raúl Correia - 1090657 - 06/06/2018 This class is a WorkbookManager It's
 * purpose is to manage multiple workbooks Current implementation is supposed to
 * support only one Workbook;
 */
public class WorkbookManager {

    /**
     * Classic singleton instance variable
     */
    private static WorkbookManager instance = null;

    /**
     * List with opened workbooks
     */
    private List<WorkbookDescriptionDTO> openedWorkbookList;

    /**
     * Current active workbook
     */
    private Workbook activeWorkbook = null;

    private WorkbookDescriptionDTO activeDescription = null;

    /**
     * Private singleton WorkbookManager constructor
     */
    private WorkbookManager() {
        openedWorkbookList = new ArrayList<>();
    }

    /**
     * Classic Singleton instance getter It constructs one if it doesn't exist
     *
     * @return WorkbookManager
     */
    public static WorkbookManager getInstance() {
        if (instance == null) {
            return (instance = new WorkbookManager());
        } else {
            return instance;
        }
    }

    /**
     * Returns how many workbooks are currently opened
     *
     * @return how manyWorkbooks are opened
     */
    public int howManyWorkbooksOpened() {
        return this.openedWorkbookList.size();
    }

    /**
     * Add a workbook to the opened openedWorkbooks
     */
    public boolean addOpenedWorkbook(final WorkbookDescriptionDTO wb) {
        boolean added = false;
        if (!openedWorkbookList.contains(wb)) {
            added = openedWorkbookList.add(wb);
            setCurrentActiveWorkbook(wb);
        }
        return added;
    }

    /**
     * Remove a workbook from the manager
     */
    public boolean removeOpenedWorkbook(final WorkbookDescriptionDTO wb) {
        boolean removed = false;
        if (openedWorkbookList.contains(wb)) {
            removed = openedWorkbookList.remove(wb);
        }
        return removed;
    }

    /**
     * Changes the current active workbook passed in as parameter
     */
    public void setCurrentActiveWorkbook(final WorkbookDescriptionDTO wb) {
        if (wb != null) {
            this.activeWorkbook = new Workbook(wb.getWorkbook());
            this.activeDescription = wb;
        }
    }

    public Workbook getCurrentActiveWorkbook() {
        return this.activeWorkbook;
    }

    public WorkbookDescriptionDTO getCurrentDescription() {
        return this.activeDescription;
    }
}
