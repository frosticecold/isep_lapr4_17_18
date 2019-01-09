/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.application;

import eapli.framework.application.Controller;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.domain.PublicChat;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.persistence.ChatRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class ChatController implements Controller {
    public PublicChat getPublicChat() {
        ChatRepository repo = PersistenceContext.repositories().publicChat();
        return repo.findAll().iterator().next();
    }
}
