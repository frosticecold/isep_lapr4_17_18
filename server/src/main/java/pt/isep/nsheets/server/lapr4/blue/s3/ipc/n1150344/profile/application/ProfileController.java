/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1150344.profile.application;

import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Username;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.UserRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.shared.services.UserDTO;

/**
 *
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */
public class ProfileController implements Controller {

    public boolean saveChanges(UserDTO user, String username, String name, String imagePath) throws DataConcurrencyException, DataIntegrityViolationException {
        UserRepository repo = PersistenceContext.repositories().users();
        User u = repo.findUserByUsername(new Username(user.getUsername()));
        if (user.getUsername().equalsIgnoreCase(username)) {
            u.changeName(name);
            u.changePhoto(imagePath);
            repo.save(u);
            return true;
        } else {
            if (repo.findUserByUsername(new Username(username)) == null) {
                u.changeUsername(username);
                u.changeName(name);
                u.changePhoto(imagePath);
                repo.save(u);
                return true;
            }
            return false;
        }
    }

    public UserDTO getUserByUsername(String username) {
        UserRepository repo = PersistenceContext.repositories().users();

        return repo.findUserByUsername(new Username(username)).toDTO();
    }

    public boolean deleteAccount(String username) {
        UserRepository repo = PersistenceContext.repositories().users();
        User u = repo.findUserByUsername(new Username(username));

        if (u != null) {
            repo.delete(u);
            return true;
        }
        return false;
    }

    public Iterable<User> loadUsers() {
        UserRepository repo = PersistenceContext.repositories().users();
        return repo.findAll();
    }

    public void changeStatus(UserDTO user, boolean status) throws DataConcurrencyException, DataIntegrityViolationException {
        UserRepository repo = PersistenceContext.repositories().users();
        User u = repo.findUserByUsername(new Username(user.getUsername()));

        u.changeUserStatus(status);

        repo.save(u);
    }
}
