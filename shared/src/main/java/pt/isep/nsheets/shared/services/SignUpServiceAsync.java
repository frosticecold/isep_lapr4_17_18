package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SignUpServiceAsync {

    void refreshDatabase(AsyncCallback<Boolean> callback);
    
    void createUser(String username, String password, String email, String role, String name, String path, AsyncCallback<Boolean> callback ) ;

}
