package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;

/**
 *
 * @author MFerreira
 */
public interface PrivateChatServiceAsync {
    
    void createPrivateChat(PrivateChatDTO pcDto, AsyncCallback<PrivateChatDTO> callback);

    void getPrivateChat(String username, AsyncCallback<ArrayList<PrivateChatDTO>> callback);
    
    void addMessageToChat(String nome, MessageDTO wdDto, AsyncCallback<MessageDTO> callback);
    
    void invitePerson(String email, String nome, AsyncCallback<Boolean> callback);

    void findPrivateChatByName(String pchatName, AsyncCallback<PrivateChatDTO> callback);
}
