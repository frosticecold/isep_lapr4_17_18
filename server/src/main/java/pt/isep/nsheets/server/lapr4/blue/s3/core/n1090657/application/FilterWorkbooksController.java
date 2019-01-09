/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.blue.s3.core.n1090657.application;

import eapli.framework.application.Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Username;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.UserRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.domain.WorkbookDescription;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.WorkbookDescriptionRepository;
import pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1090657.ipc.AccessType;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class FilterWorkbooksController implements Controller {

    public List<WorkbookDescription> getEditableWorkbooks(String username) {
        UserRepository userRepo = PersistenceContext.repositories().users();
        WorkbookDescriptionRepository wbrepo = PersistenceContext.repositories().workbookDescriptions();
        User user = userRepo.findUserByUsername(new Username(username));

        if (user == null) {
            return null;
        }
        List<WorkbookDescription> filteredworkbooks = new ArrayList<>();
        Iterable<WorkbookDescription> publicWorkbooks = wbrepo.getPublicWorkbooks(true);
        /**
         * Fetches all private workbooks without filtering from user...
         */
        Iterable<WorkbookDescription> privateWorkbooks = wbrepo.getPrivateWorkbooks(user.getEmail().toString());

        /**
         * Filtering and addint to a list
         */
        publicWorkbooks.forEach(wb -> filteredworkbooks.add(wb));
        user.workbooksFromUser().forEach(wb -> filteredworkbooks.add(wb));
        StreamSupport.stream(filteredworkbooks.spliterator(), false).filter(wb -> wb.getAccessList().hasAccessType(user.getEmail().toString(), AccessType.READ_WRITE)).forEach(wb -> filteredworkbooks.add(wb));
        return filteredworkbooks;
    }

}
