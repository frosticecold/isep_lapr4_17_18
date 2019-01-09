/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.application;

import eapli.framework.application.Controller;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.domain.PrivateChat;

/**
 *
 * @author MFerreira
 */
public class ListPrivateChatsController implements Controller{
    
    public Iterable<PrivateChat> listAllPrivateChat() {
        return new PrivateChatService().listUserPrivateChats();
    }

    public PrivateChat findChatByName(String pchatName) {
        return new PrivateChatService().finChatByName(pchatName);
    }
    
    
    
    
}
