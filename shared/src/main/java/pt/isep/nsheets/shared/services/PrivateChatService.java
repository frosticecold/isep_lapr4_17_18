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
 * @author MFerreira
 */
@RemoteServiceRelativePath("privateChatService")
public interface PrivateChatService extends RemoteService{
    
    ArrayList<PrivateChatDTO> getPrivateChat(String username);
    PrivateChatDTO createPrivateChat(PrivateChatDTO pchatDTO) throws DataException;
    MessageDTO addMessageToChat(String nome, MessageDTO mDto);
    Boolean invitePerson(String email, String nome);
    PrivateChatDTO findPrivateChatByName(String pchatName);
}
