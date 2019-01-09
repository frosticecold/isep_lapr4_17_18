
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
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.persistence.ChatRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.shared.services.MessageDTO;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class PublicChatService {

    public Message addMessageToChat(MessageDTO message) throws DataConcurrencyException, DataIntegrityViolationException {

        final ChatRepository chatReporting = PersistenceContext.repositories().publicChat();

        PublicChat chat = chatReporting.findAll().iterator().next();

        Message msg = Message.fromDTO(message);
        chat.addChatMessage(msg);

        final ChatRepository repo = PersistenceContext.repositories().publicChat();
        repo.save(chat);

        return msg;
    }

    public PublicChat getChats() {
        final ChatRepository chatReporting = PersistenceContext.repositories().publicChat();

        return chatReporting.findAll().iterator().next();
    }

    public boolean checkCreateChat() throws DataConcurrencyException, DataIntegrityViolationException {
        final ChatRepository reporting = PersistenceContext.repositories().publicChat();
        if (!reporting.findAll().iterator().hasNext()) {
            PublicChat c = new PublicChat();
            reporting.save(c);
            return true;
        }
        return false;
    }
}
