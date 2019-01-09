/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public interface ChatServiceAsync {

    void addMessageToChat(MessageDTO wdDto, AsyncCallback<MessageDTO> callback);

    void getChats(AsyncCallback<PublicChatDTO> callback);

    void createChat(AsyncCallback<Boolean> callback);
    
    void sendEmail(String email, AsyncCallback<Boolean> callback);
}
