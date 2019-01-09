/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161838.workbooks.application;

import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.domain.WorkbookDescription;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.WorkbookDescriptionRepository;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;

/**
 *
 * @author Joao Rocha <1161838>
 */
public class EditWorkbookDescriptionController implements Controller {

    private WorkbookDescriptionRepository wdRepo;

    public EditWorkbookDescriptionController() {
        wdRepo = PersistenceContext.repositories().workbookDescriptions();
    }

    /**
     * Method that changes workbook's name
     *
     * @author Joao Rocha <1161838>
     * @param id
     * @param newName
     * @return
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     */
    public boolean editWorkbookName(WorkbookDescriptionDTO dto, String newName) throws DataConcurrencyException, DataIntegrityViolationException {
        WorkbookDescription workDesc = wdRepo.findWorkbookDescriptionByName(dto.getName());
        if (workDesc.changeName(newName)) {
            wdRepo.save(workDesc);
            return true;
        }
        return false;
    }

    /**
     * Method that changes workbook's description
     *
     * @author Joao Rocha <1161838>
     * @param dto
     * @param id
     * @param newDescription
     * @return
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     */
    public boolean editWorkbookDescription(WorkbookDescriptionDTO dto, String newDescription) throws DataConcurrencyException, DataIntegrityViolationException {
        WorkbookDescription workDesc = wdRepo.findWorkbookDescriptionByName(dto.getName());
        if (workDesc.changeDescription(newDescription)) {
            wdRepo.save(workDesc);
            return true;
        }
        return false;
    }
}
