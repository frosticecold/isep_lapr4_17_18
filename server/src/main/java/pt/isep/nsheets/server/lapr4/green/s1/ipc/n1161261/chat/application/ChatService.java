
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.application;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.domain.Message;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.domain.PublicChat;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.persistence.ChatReportingRepository;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.persistence.ChatRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class ChatService {

    public PublicChat addMessageToPublicChat(Message message) throws DataConcurrencyException, DataIntegrityViolationException {

        final ChatReportingRepository chatReporting = PersistenceContext.repositories().chats();

        PublicChat chat = (PublicChat) chatReporting.getPublicChat();
        chat.addChatMessage(message);

        final ChatRepository repository = PersistenceContext.repositories().publicChat();
        repository.save(chat);

        return chat;
    }
    
    public boolean sendEmail(String email){
        return true;
    }
}

