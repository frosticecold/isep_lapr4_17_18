/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.application;

import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.domain.Message;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.domain.MessagePrivate;
import pt.isep.nsheets.shared.services.MessageDTO;

/**
 *
 * @author MFerreira
 */
public class SendMessageController implements Controller{

    public MessagePrivate addMessage(String nome, MessageDTO mDto) throws DataConcurrencyException, DataIntegrityViolationException {
        return new PrivateChatService().addMessageToChat(nome, mDto);
    }
    
}
