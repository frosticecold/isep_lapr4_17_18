/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161838.workbooks.application;

import eapli.framework.application.Controller;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.domain.WorkbookDescription;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.WorkbookDescriptionRepository;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;

/**
 *
 * @author Joao Rocha <1161838>
 */
public class DeleteWorkbookController implements Controller{
    
    
    private WorkbookDescriptionRepository wdRepo;
    
    public DeleteWorkbookController(){
        wdRepo = PersistenceContext.repositories().workbookDescriptions();
    }
    
    /**
     * Method that deletes a specific workbook by its description
     * @param id
     * @return 
     */
    public boolean deleteWorkbook(WorkbookDescriptionDTO dto){
        WorkbookDescription wd = wdRepo.findWorkbookDescriptionByName(dto.getName());
        if(wd != null){
            wdRepo.delete(wd);
            return true;
        }
        return false;
    }
    
}
