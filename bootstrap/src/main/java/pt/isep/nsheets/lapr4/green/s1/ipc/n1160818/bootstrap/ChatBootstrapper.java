/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.lapr4.green.s1.ipc.n1160818.bootstrap;

import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.domain.PublicChat;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;

import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.persistence.ChatRepository;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class ChatBootstrapper implements Action {

    public ChatBootstrapper() {
    }

    @Override
    public boolean execute() {
        PersistenceContext.setSettings(getPersistenceSettings());
        final ChatRepository repo = PersistenceContext.repositories().publicChat();

        PublicChat chat = new PublicChat();

        try {
            repo.save(chat);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(ChatBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    private PersistenceSettings getPersistenceSettings() {
        Properties props = new Properties();

        props.put("persistence.repositoryFactory", "pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa.JpaRepositoryFactory");
        props.put("persistence.persistenceUnit", "lapr4.NSheetsPU");

        return new PersistenceSettings(props);
    }
}
