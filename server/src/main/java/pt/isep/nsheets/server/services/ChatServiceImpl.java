/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.application.ChatController;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.application.CreateChatController;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.application.SendEmailController;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.application.SendMessageController;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.domain.Message;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.domain.PublicChat;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;
import pt.isep.nsheets.shared.services.ChatService;
import pt.isep.nsheets.shared.services.MessageDTO;
import pt.isep.nsheets.shared.services.PublicChatDTO;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class ChatServiceImpl extends RemoteServiceServlet implements ChatService {

    private PersistenceSettings getPersistenceSettings() {

        Properties props = new Properties();

        props.put("persistence.repositoryFactory",
                "pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa.JpaRepositoryFactory");
        props.put("persistence.persistenceUnit", "lapr4.NSheetsPU");

        return new PersistenceSettings(props);
    }

    @Override
    public MessageDTO addMessageToChat(MessageDTO mDto) {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        SendMessageController controller = new SendMessageController();
        Message msg = null;

        try {
            msg = controller.addMessage(mDto);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(ChatServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return msg.toDTO(msg);
    }

    @Override
    public PublicChatDTO getChats() {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        ChatController controller = new ChatController();
        PublicChat pChat = new PublicChat();

        pChat = controller.getPublicChat();

        return pChat.toDTO();
    }

    @Override
    public Boolean createChat() {
        try {
            return new CreateChatController().createChat();
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(ChatServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    @Override
    public Boolean sendEmail(String username){
        SendEmailController controller = new SendEmailController();
        
        return controller.sendEmail(username);
    }
}
