/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.blue.s2.core.n1090657.application;

import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.application.ListWorkbookDescriptionController;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.domain.WorkbookDescription;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.WorkbookDescriptionRepository;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class SaveWorkbookController implements Controller {

    public boolean saveWorkbook(WorkbookDescriptionDTO dto) {

        try {
            ListWorkbookDescriptionController listwbcontroller = new ListWorkbookDescriptionController();
            Iterable<WorkbookDescription> listWorkbookDescriptions = listwbcontroller.listWorkbookDescriptions();
            WorkbookDescription wbdescription = null;

            for (WorkbookDescription desc : listWorkbookDescriptions) {
                if (desc.getName().equalsIgnoreCase(dto.getName()) && desc.getDescription().equalsIgnoreCase(dto.getDescription())) {
                    wbdescription = desc;
                    break;
                }
            }
            if (wbdescription == null) {
                return false;
            }
            wbdescription.fromDTO(dto);

            WorkbookDescriptionRepository wdrepo = PersistenceContext.repositories().workbookDescriptions();
            WorkbookDescription save = wdrepo.save(wbdescription);
            return save != null;
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(SaveWorkbookController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(SaveWorkbookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
