/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1150344.profile.application.ProfileController;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.GetPropertiesService;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.shared.services.ProfileService;
import pt.isep.nsheets.shared.services.UserDTO;

/**
 *
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */
public class ProfileServiceImpl extends RemoteServiceServlet implements ProfileService {

    @Override
    public boolean refreshDatabase() {
        PersistenceContext.setSettings(GetPropertiesService.getPersistenceSettings());
        return true;
    }

    @Override
    public boolean saveChanges(UserDTO user, String newUsername, String newName, String newImagePath) {
        ProfileController controller = new ProfileController();
        try {
            if (!newImagePath.isEmpty()) {
                return controller.saveChanges(user, newUsername, newName, newImagePath);
            } else {
                return controller.saveChanges(user, newUsername, newName, user.getPicture());
            }
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(ProfileServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        ProfileController controller = new ProfileController();
        return controller.getUserByUsername(username);
    }

    @Override
    public boolean deleteAccount(String username) {
        ProfileController controller = new ProfileController();
        return controller.deleteAccount(username);
    }

    @Override
    public Iterable<UserDTO> loadUsers() {
        ProfileController controller = new ProfileController();
        List<UserDTO> list = new ArrayList<>();
        for (User user : controller.loadUsers()) {
            list.add(user.toDTO());
        }
        return list;
    }

    @Override
    public void changeStatus(UserDTO user, boolean status) {
        ProfileController controller = new ProfileController();
        try {
            controller.changeStatus(user, status);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(ProfileServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
