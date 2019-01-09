package pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.application;

import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author MFerreira
 */
public class InviteUserController implements Controller{

    public Boolean inviteUser(String email, String nome) throws DataConcurrencyException, DataIntegrityViolationException {
        
    	return new PrivateChatService().invitePerson(email, nome);
    }
    
}
