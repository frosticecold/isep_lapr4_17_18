/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.domain.Message;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.application.CreatePrivateChatController;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.application.InviteUserController;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.application.ListPrivateChatsController;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.application.SendMessageController;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.domain.MessagePrivate;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.domain.PrivateChat;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;
import pt.isep.nsheets.shared.services.DataException;
import pt.isep.nsheets.shared.services.MessageDTO;
import pt.isep.nsheets.shared.services.PrivateChatDTO;
import pt.isep.nsheets.shared.services.PrivateChatService;

/**
 *
 * @author MFerreira
 */
public class PrivateChatServiceImpl extends RemoteServiceServlet implements PrivateChatService {

    private PersistenceSettings getPersistenceSettings() {

        Properties props = new Properties();

        props.put("persistence.repositoryFactory",
                "pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa.JpaRepositoryFactory");
        props.put("persistence.persistenceUnit", "lapr4.NSheetsPU");

        // Other JPA properties that one might want to override from the ones in
        // persistence.xml
        // props.put("javax.persistence.jdbc.url",
        // "jdbc:h2:../db/nsheets;MV_STORE=FALSE;MVCC=FALSE");
        // props.put("javax.persistence.jdbc.password", "");
        // props.put("javax.persistence.jdbc.driver", "org.h2.Driver");
        // props.put("javax.persistence.jdbc.user", "");
        // props.put("javax.persistence.schema-generation.database.action", "create");
        // props.put("eclipselink.logging.level", "FINE");
        return new PersistenceSettings(props);
    }

    @Override
    public PrivateChatDTO createPrivateChat(PrivateChatDTO pchatDTO) throws DataException {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        CreatePrivateChatController ctrl = new CreatePrivateChatController();

        PrivateChat pchat = null;

        try {
            pchat = ctrl.createPrivateChat(pchatDTO);

        } catch (DataConcurrencyException ex) {

            throw new DataException((Throwable) ex);

        } catch (DataIntegrityViolationException ex) {

            throw new DataException((Throwable) ex);
        }
        if (pchat != null) {
            return pchat.toDTO();
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<PrivateChatDTO> getPrivateChat(String username) {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        ArrayList<PrivateChatDTO> pchatsDTO = new ArrayList<PrivateChatDTO>();

        ListPrivateChatsController ctrl = new ListPrivateChatsController();

        Iterable<PrivateChat> allpchats = ctrl.listAllPrivateChat();

        for (PrivateChat pc : allpchats) {
            for (User user : pc.usersInConversation()) {
                if (user.username().equals(username)) {
                    pchatsDTO.add(pc.toDTO());
                }
            }
        }

        //allpchats.forEach(ev -> pchatsDTO.add(ev.toDTO()));
        return pchatsDTO;
    }

    @Override
    public MessageDTO addMessageToChat(String nome, MessageDTO mDto) {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        SendMessageController controller = new SendMessageController();
        MessagePrivate msg = null;

        try {
            msg = controller.addMessage(nome, mDto);
            
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(PrivateChatServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return msg.toDTO(msg);
        
    }

    @Override
    public Boolean invitePerson(String email, String nome) {
        PersistenceContext.setSettings(this.getPersistenceSettings());
        
        InviteUserController controller = new InviteUserController();
        
        try {
            return controller.inviteUser(email, nome);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(PrivateChatServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(PrivateChatServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public PrivateChatDTO findPrivateChatByName(String pchatName) {
        PersistenceContext.setSettings(this.getPersistenceSettings());
        
        ListPrivateChatsController ctrl = new ListPrivateChatsController();
        
        PrivateChat pchat = ctrl.findChatByName(pchatName);
        
        return pchat.toDTO();
    }
}
