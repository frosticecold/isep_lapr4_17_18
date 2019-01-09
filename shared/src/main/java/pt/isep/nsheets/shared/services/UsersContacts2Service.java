package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 * @author Joao Magalhaes<1160763>
 */ 
@RemoteServiceRelativePath("usersContacts2Service")
public interface UsersContacts2Service extends RemoteService{
    
    void getContacts(String username);
    void addContact(String contact, String username) throws DataException;

}