package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Rui Almeida<1160818>
 */
@RemoteServiceRelativePath("authentication")
public interface AuthenticationService extends RemoteService {

     /**
     * Validates the user's username and password.
     * @param username - user's username
     * @param password - user's password
     * @return - true if the user authentication is valid, false if not
     * @author - Rui Almeida <1160818>
     */
    Boolean validateCredentials(String username, String password);

    String getURL(String username);
    /**
     * Refreshes the database and gathers the persistence settings
     * @author - Rui Almeida <1160818>
     */
    Boolean refreshDatabase();

}
