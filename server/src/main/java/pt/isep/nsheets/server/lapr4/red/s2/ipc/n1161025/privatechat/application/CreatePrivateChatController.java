/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.application;

import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.domain.PrivateChat;
import pt.isep.nsheets.shared.services.PrivateChatDTO;

/**
 *
 * @author MFerreira
 */
public class CreatePrivateChatController implements Controller{
    
    
    public PrivateChat createPrivateChat(PrivateChatDTO dto) throws DataConcurrencyException, DataIntegrityViolationException {
        
    	return new PrivateChatService().createPrivateChat(dto);
    }
    
    
}
