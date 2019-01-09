/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author Joao Magalhaes<1160763>
 */
public interface UsersServiceAsync {
    
    void getContacts(String username, AsyncCallback<UserDTO> callback);
    
    void addContact(String email, String username, AsyncCallback<String> callback);
    
    void saveUser(String username,AsyncCallback<Boolean> callback);
    
    void getWorkbooksFromUser(String username,AsyncCallback<List<WorkbookDescriptionDTO>> callback);
    
    void getUserEmail(final String username, AsyncCallback<String> callback);
}
