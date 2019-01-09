package pt.isep.nsheets.lapr4.green.s1.ipc.n1160818.bootstrap;

import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.*;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.GetPropertiesService;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.UserRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;

import java.util.logging.Logger;

/**
 * Saves hard-coded users to the database.
 *
 * @author Rui Almeida<1160818>
 */
public class UserBootstrapper implements Action {

    @Override
    public boolean execute() {
        registerUser("john", "Password1", "1161386@isep.ipp.pt", "João");
        registerUser("sa", "sa", "sa@isep.ipp.pt", "Manuel");
        registerUser("jane", "Password1", "1090657@isep.ipp.pt", "Jane");
        return true;
    }




    /**
     * Calls the persistence unit and saves an user in the database.
     * @param username - user's username
     * @param password - user's password
     * @param email - user's email
     */
    private void registerUser(final String username, final String password, final String email, final String name) {

        try {
            PersistenceContext.setSettings(GetPropertiesService.getPersistenceSettings());
            UserRepository users = PersistenceContext.repositories().users();

            String role = RoleType.USER.toString();
            Email e = new Email(email);

            User user = new User(username, password, email, role, name,"");
            user.receiveInvitation(e);
            user.blockUser(e);
            user.addContact(e);

            if (username.equals("sa")) { user.setAsAdmin(); }

            users.save(user);

        } catch(DataConcurrencyException | DataIntegrityViolationException ex) {
            System.out.println("Exception: " + ex.getMessage());
            Logger.getLogger(Bootstrapper.class.getSimpleName())
                    .info("Exception during bootstrapping: assuming existing record.");
        }
    }

}
