/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.application;

import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.domain.Message;
import pt.isep.nsheets.shared.services.MessageDTO;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class SendMessageController implements Controller {

    public Message addMessage(MessageDTO message) throws DataConcurrencyException, DataIntegrityViolationException {
        return new PublicChatService().addMessageToChat(message);
    }

}
