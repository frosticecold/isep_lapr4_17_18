/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.s3.ipc.n1040862.GlobalSearh.application;

import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.domain.WorkbookDescription;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.WorkbookDescriptionRepository;

/**
 *
 * @author Oliveira
 */
public class GlobalSearchAndReplaceService {
    
    public Iterable<WorkbookDescription> showOutputOfGlobalSearch(String username, String regularExpression){
        final WorkbookDescriptionRepository repo = PersistenceContext.repositories().workbookDescriptions();
        return repo.findAll();
    }
    
}
