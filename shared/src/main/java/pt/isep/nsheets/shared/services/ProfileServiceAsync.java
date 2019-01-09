/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */
public interface ProfileServiceAsync {
    void refreshDatabase(AsyncCallback<Boolean> callback);
    
    void saveChanges(UserDTO user, String newUsername, String newName, String newImagePath, AsyncCallback<Boolean> callback);
    
    void getUserByUsername(String username, AsyncCallback<UserDTO> callback);
    
    void deleteAccount(String username, AsyncCallback<Boolean> callback);
    
    void loadUsers(AsyncCallback<Iterable<UserDTO>> callback);

    void changeStatus(UserDTO user, boolean status, AsyncCallback<Boolean> callback);
}
