package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("signUpValidation")
public interface SignUpService extends RemoteService {

    Boolean refreshDatabase();
    
    Boolean createUser(String username, String password, String email, String role, String name, String path);
    
}
