/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161838.workbooks.application;

import eapli.framework.application.Controller;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161838.workbooks.persistence.WorkbookRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.domain.WorkbookDescription;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.WorkbookDescriptionRepository;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;

/**
 *
 * @author Joao Rocha <1161838>
 */
public class OpenWorkbookController implements Controller{
    
    private WorkbookRepository workbookRepo;
    private WorkbookDescriptionRepository workbookDescRepo;
    
    public OpenWorkbookController(){
        workbookRepo = PersistenceContext.repositories().workbooks();
    }
    
    /**
     * Method that gets a workbook to be open
     * NOT IMPLEMENTED BECAUSE AT THE TIME THERE WASNT A WORKBOOK REPOSITORY IMPLEMENTED
     * @param id
     * @return 
     */
    public Workbook openWorkbook(WorkbookDescriptionDTO wd){
        WorkbookDescription desc = workbookDescRepo.findWorkbookDescriptionByName(wd.getName());
        Workbook workbook = null;
//        workbook = workbookRepo.findWorkbookByDescription(desc);
        return workbook;
    }
}
