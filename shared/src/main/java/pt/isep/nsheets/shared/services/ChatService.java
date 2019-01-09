/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.ArrayList;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
@RemoteServiceRelativePath("chatsService")
public interface ChatService extends RemoteService {

    PublicChatDTO getChats();

    MessageDTO addMessageToChat(MessageDTO mDto);
    
    Boolean createChat();

    Boolean sendEmail(String email);
}
