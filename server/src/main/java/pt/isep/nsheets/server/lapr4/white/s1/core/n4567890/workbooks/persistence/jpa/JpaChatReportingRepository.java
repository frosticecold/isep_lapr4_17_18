/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa;

import java.util.List;
import java.util.Optional;
import javax.persistence.TypedQuery;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.domain.PublicChat;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.persistence.ChatReportingRepository;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class JpaChatReportingRepository extends NSheetsJpaRepositoryBase<PublicChat, Long> implements ChatReportingRepository {

    public JpaChatReportingRepository(PersistenceSettings settings) {
        super(settings);
    }

    @Override
    public List<PublicChat> getPublicChat() {
        final TypedQuery<PublicChat> query = entityManager().createQuery(
                "SELECT pChat "
                + "FROM PublicChat pChat ", PublicChat.class);

        return (List<PublicChat>) query.getSingleResult();
    }

    @Override
    public List<PublicChat> olderChats() {
        final TypedQuery<PublicChat> query = entityManager().createQuery(
                "SELECT pChat "
                + "FROM PublicChat pChat "
                + "ORDER BY pChat.date ", PublicChat.class);
        List<PublicChat> list = query.getResultList();
        int size = list.size();
        if (size > 0) {
            list.remove(size - 1);
        }
        return list;
    }
}
