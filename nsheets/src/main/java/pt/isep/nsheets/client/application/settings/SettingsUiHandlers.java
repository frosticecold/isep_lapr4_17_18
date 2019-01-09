package pt.isep.nsheets.client.application.settings;

import com.gwtplatform.mvp.client.UiHandlers;

public interface SettingsUiHandlers  extends UiHandlers {
    
    void onListingContacts(String username);

    void onListingBlockedUsers(String username);
    
    void onCreatingContact(String email);
    
    void onBlockingUser(String email);
    
}
