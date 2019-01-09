package pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.domain.PrivateChat;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.persistence.PrivateChatRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;

/**
 *
 * @author MFerreira
 */
public class JpaPrivateChatRepository extends NSheetsJpaRepositoryBase<PrivateChat, Long> implements PrivateChatRepository {

    public JpaPrivateChatRepository(PersistenceSettings settings) {
        super(settings);
    }

    @Override
    public PrivateChat findPChatbyName(String nome) {
        Query q = entityManager().
                createQuery("SELECT pchat FROM PrivateChat pchat "
                        + "WHERE pchat.nome=:nome", PrivateChat.class);
        q.setParameter("nome", nome);

        return (PrivateChat)q.getSingleResult();
    }

}
