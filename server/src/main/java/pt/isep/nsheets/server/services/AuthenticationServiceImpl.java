package pt.isep.nsheets.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.application.LoginController;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.GetPropertiesService;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.shared.services.AuthenticationService;

/**
 * @author Rui Almeida<1160818>
 */
public class AuthenticationServiceImpl extends RemoteServiceServlet implements AuthenticationService {

    /**
     * Validates the user's username and password.
     * @param username - user's username
     * @param password - user's password
     * @return - true if the user authentication is valid, false if not
     * @author - Rui Almeida <1160818>
     */
    @Override
    public Boolean validateCredentials(String username, String password) {
        LoginController controller = new LoginController();
        return controller.verifyPassword(username, password);
    }
    
    @Override
    public String getURL(String username) {
        LoginController controller = new LoginController();
        return controller.getURL(username);
    }

    @Override
    public Boolean refreshDatabase() {
        // Setup the persistence settings
        PersistenceContext.setSettings(GetPropertiesService.getPersistenceSettings());
        return true;
    }
}
