package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence;

import eapli.framework.persistence.repositories.DeleteableRepository;
import eapli.framework.persistence.repositories.Repository;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Email;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Username;


/**
 * @author Rui Almeida<1160818>
 */
public interface UserRepository extends Repository<User, Long>, DeleteableRepository<User, Long> {

     /**
     * Given an username, finds an object user stored in the database
     * @param username - user's username
     * @return the user
     * @author Rui Almeida<1160818>
     */
    User findUserByUsername(Username username);
    
    User findUserByEmail(Email email);

}
