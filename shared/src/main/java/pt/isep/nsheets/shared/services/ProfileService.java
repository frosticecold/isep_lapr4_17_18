/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */
@RemoteServiceRelativePath("profileService")
public interface ProfileService extends RemoteService {
    
    boolean refreshDatabase();
    
    boolean saveChanges(UserDTO user, String newUsername, String newName, String newImagePath);
    
    UserDTO getUserByUsername(String username);
    
    boolean deleteAccount(String username);
    
    Iterable<UserDTO> loadUsers();
    
    void changeStatus(UserDTO user, boolean status);
}
