package pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.persistence;

import eapli.framework.persistence.repositories.Repository;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.domain.PrivateChat;

/**
 *
 * @author MFerreira
 */
public interface PrivateChatRepository extends Repository<PrivateChat, Long>{

    public PrivateChat findPChatbyName(String nome);
}
