package pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.application;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Email;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Username;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.UserRepository;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.domain.Message;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.domain.MessagePrivate;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.domain.PrivateChat;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.persistence.PrivateChatRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.shared.services.MessageDTO;
import pt.isep.nsheets.shared.services.PrivateChatDTO;

/**
 *
 * @author MFerreira
 */
public class PrivateChatService {

    PrivateChat createPrivateChat(PrivateChatDTO dto) throws DataConcurrencyException, DataIntegrityViolationException {
        final PrivateChatRepository pchatRepository = PersistenceContext.repositories().privateChat();
        final UserRepository userRepository = PersistenceContext.repositories().users();

        ArrayList<User> users = new ArrayList<>();

        for (String uName : dto.getUsernames()) {
            users.add(userRepository.findUserByUsername(new Username(uName)));
        }

        for(PrivateChat pc : pchatRepository.findAll()){
            if(pc.nome().equalsIgnoreCase(dto.getNome())){
                return null;
            }
        }
        
        PrivateChat pchat = PrivateChat.fromDTO(dto, users);

        pchatRepository.save(pchat);

        return pchat;
        
    }

    Iterable<PrivateChat> listUserPrivateChats() {
        final PrivateChatRepository pchatRepository = PersistenceContext.repositories().privateChat();

        return pchatRepository.findAll();
    }

    MessagePrivate addMessageToChat(String nome, MessageDTO mDto) throws DataConcurrencyException, DataIntegrityViolationException {
        final PrivateChatRepository pchatRepository = PersistenceContext.repositories().privateChat();

        PrivateChat pchat = pchatRepository.findPChatbyName(nome);
  
        MessagePrivate msg = MessagePrivate.fromDTO(mDto);
        pchat.addChatMessage(msg);

        pchatRepository.save(pchat);

        return msg;
    }

    Boolean invitePerson(String email, String nome) throws DataConcurrencyException, DataIntegrityViolationException {
        final PrivateChatRepository pchatRepository = PersistenceContext.repositories().privateChat();
        final UserRepository userRepository = PersistenceContext.repositories().users();
        
        User user = userRepository.findUserByEmail(new Email(email));
        
        PrivateChat pchat = pchatRepository.findPChatbyName(nome);
        
        Boolean add = pchat.usersInConversation().add(user);
        
        pchatRepository.save(pchat);
        
        return add;
    }

    PrivateChat finChatByName(String pchatName) {
        final PrivateChatRepository pchatRepository = PersistenceContext.repositories().privateChat();
        
        return pchatRepository.findPChatbyName(pchatName);
    }
}
