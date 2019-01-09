/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161027.ipc06.application;

import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Username;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.UserRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.application.WorkbookDescriptionService;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.domain.WorkbookDescription;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.shared.services.UserDTO;

/**
 *
 * @author pedro
 */
public class ipc06Controller implements Controller {

    public List<WorkbookDescription> getWorkbooksFromCurrentUser(String username) {
        UserRepository repo = PersistenceContext.repositories().users();

        Username u = new Username(username);

        User user = repo.findUserByUsername(u);

        if (user == null) {
            return null;
        }

        Iterable<WorkbookDescription> publicList = listPublicWorkbookDescriptions();
        Iterable<WorkbookDescription> privateOwns = user.workbooksFromUser();
        Iterable<WorkbookDescription> privateSharedWith = listPrivateWorkbookDescriptions(user.getEmail().toString());

        List<WorkbookDescription> allwb = new ArrayList<>();
        publicList.forEach(wb -> allwb.add(wb));
        privateSharedWith.forEach(wb -> allwb.add(wb));
        privateOwns.forEach(wb -> allwb.add(wb));
        return allwb;
    }

    public User getCurrentUser(String username) {
        UserRepository repo = PersistenceContext.repositories().users();

        Username u = new Username(username);

        User user = repo.findUserByUsername(u);

        if (user == null) {
            return null;
        }

        return user;
    }

    public Iterable<WorkbookDescription> listPublicWorkbookDescriptions() {
        return new WorkbookDescriptionService().publicWorkbookDescriptions();
    }

    public Iterable<WorkbookDescription> listPrivateWorkbookDescriptions(String email) {
        Iterable<WorkbookDescription> list = new WorkbookDescriptionService().privateWorkbookDescriptions(email);
        List<WorkbookDescription> listFromUser = new ArrayList<>();
        for (WorkbookDescription wd : list) {
            if (!wd.getWorkbook().isIsPublic()) {
                if (wd.getAccessList().hasAnyTypeOfAccess(email)) {
                    listFromUser.add(wd);
                }
            }
        }
        return listFromUser;
    }

    public boolean saveUser(String username) throws DataConcurrencyException, DataIntegrityViolationException {
        UserRepository repo = PersistenceContext.repositories().users();

        Username u = new Username(username);

        User user = repo.findUserByUsername(u);

        repo.save(user);
        return true;
    }

    public void savePrivateUser(String username, WorkbookDescription newWB) throws DataConcurrencyException, DataIntegrityViolationException {
        UserRepository repo = PersistenceContext.repositories().users();

        Username u = new Username(username);

        User user = repo.findUserByUsername(u);

        user.workbooksFromUser().add(newWB);

        repo.save(user);
    }

    public void savePublicUser(WorkbookDescription newWB) throws DataConcurrencyException, DataIntegrityViolationException {
        UserRepository repo = PersistenceContext.repositories().users();

        Iterable<User> allUsers = repo.findAll();

        for (User u : allUsers) {
            u.workbooksFromUser().add(newWB);

            repo.save(u);
        }
    }

    public String getUserEmail(final String username) {
        UserRepository repo = PersistenceContext.repositories().users();

        Username user_name = new Username(username);
        User findUserByUsername = repo.findUserByUsername(user_name);

        return findUserByUsername.getEmail().toString();

    }

    public UserDTO findUser(String username){
        UserRepository repo = PersistenceContext.repositories().users();

        Username user_name = new Username(username);
        User user = repo.findUserByUsername(user_name);
        
        return user.toDTO();
    }
}
