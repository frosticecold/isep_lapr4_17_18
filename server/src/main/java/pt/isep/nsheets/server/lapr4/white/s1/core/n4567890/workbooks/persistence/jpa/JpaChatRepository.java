
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.domain.PublicChat;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.persistence.ChatRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;
import pt.isep.nsheets.shared.services.PublicChatDTO;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class JpaChatRepository extends NSheetsJpaRepositoryBase<PublicChat, Long> implements ChatRepository {

    public JpaChatRepository(PersistenceSettings settings) {
        super(settings);
    }

}
