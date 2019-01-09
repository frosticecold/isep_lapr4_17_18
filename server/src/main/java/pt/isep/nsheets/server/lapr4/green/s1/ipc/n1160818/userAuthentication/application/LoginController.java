package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.application;

import eapli.framework.application.Controller;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Username;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.UserRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;

import javax.persistence.NoResultException;

/**
 * @author Rui Almeida<1160818>
 */
public class LoginController implements Controller {

    /**
     * First, it tries to find the user by its username in the database. If the user is found,
     * then we need to compare the passwords, if they match, try to log the user in. If they don't match,
     * then user can't login. If the user is not found in the database, return false as well.
     *
     * @param username - user's username
     * @param password - user's password
     * @return true if user was logged in with success, false if not
     * @author Rui Almeida<1160818>
     */
    public boolean verifyPassword(String username, String password) {
        UserRepository repo = PersistenceContext.repositories().users();
        try {

            Username u = new Username(username);

            User user = repo.findUserByUsername(u);

            if (user == null || !user.isActive()) { return false; }

            if (user.verifyPassword(password)) { return true; }

        } catch (NoResultException ex) {  return false; }
        
        return false;
    }
    
    public String getURL(String username) {
        UserRepository repo = PersistenceContext.repositories().users();
        
        Username u = new Username(username);
        User user = repo.findUserByUsername(u);
        try {
            return user.getPicURL();
        }catch(Exception ex){
            return null;
        }
    }
}
