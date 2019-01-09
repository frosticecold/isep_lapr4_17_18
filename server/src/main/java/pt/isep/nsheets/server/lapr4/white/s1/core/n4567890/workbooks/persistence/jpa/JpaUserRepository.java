package pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa;

import eapli.framework.persistence.repositories.impl.jpa.JpaTxRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaTxlessRepository;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Username;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.UserRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;

import java.util.HashMap;
import java.util.Map;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Email;

/**
 * @author Rui Almeida<1160818>
 */
public class JpaUserRepository extends JpaTxRepository<User, Long> implements UserRepository {

    JpaUserRepository(PersistenceSettings settings) {
        super(settings.getPersistenceUnitName());
    }

    /**
     * Given an username, finds an object user stored in the database
     * @param username - user's username
     * @return the user
     * @author Rui Almeida<1160818>
     */
    @Override
    public User findUserByUsername(Username username) {
        final Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        try{
            return matchOne("e.username=:username", params);
        }catch(Exception ex){
            return null;
        }
    }
     
    @Override
    public User findUserByEmail(Email email) {
        final Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        try{
            return matchOne("e.email=:email", params);
        }catch(Exception ex){
             return null;
        }
    }
}
