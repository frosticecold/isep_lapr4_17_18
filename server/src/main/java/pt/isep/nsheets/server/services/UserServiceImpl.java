/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.services;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161027.ipc06.application.ipc06Controller;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.domain.WorkbookDescription;
import pt.isep.nsheets.shared.services.DataException;
import pt.isep.nsheets.shared.services.UserDTO;
import pt.isep.nsheets.shared.services.UsersService;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;

/**
 *
 * @author pedro
 */
public class UserServiceImpl extends RemoteServiceServlet implements UsersService {

    @Override
    public void getContacts(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addContact(String contact, String username) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveUser(String username) {
        ipc06Controller c = new ipc06Controller();

        try {
            c.saveUser(username);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public List<WorkbookDescriptionDTO> getWorkbooksFromUser(String username) {
        ipc06Controller c = new ipc06Controller();

        List<WorkbookDescription> list = c.getWorkbooksFromCurrentUser(username);
        List<WorkbookDescriptionDTO> listDTO = new ArrayList<>();
        for (WorkbookDescription w : list) {
            listDTO.add(w.toDTO());
        }
        return listDTO;
    }

    //@Override
    public String getUserEmail(String username) {
        ipc06Controller controller = new ipc06Controller();

        return controller.getUserEmail(username);
    }

}
