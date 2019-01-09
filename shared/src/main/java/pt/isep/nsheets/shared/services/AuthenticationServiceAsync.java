package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Rui Almeida<1160818>
 */
public interface AuthenticationServiceAsync {

      /**
     * Validates the user's username and password in async mode.
     * @param username - user's username
     * @param password - user's password
     * @author - Rui Almeida <1160818>
     */
    void validateCredentials(String username, String password, AsyncCallback<Boolean> callback);

    /**
     * Refreshes the database and gathers the persistence settings
     * @author - Rui Almeida <1160818>
     */
    void refreshDatabase(AsyncCallback<Boolean> callback);
    
    void getURL(String username, AsyncCallback<String> callback);

}
