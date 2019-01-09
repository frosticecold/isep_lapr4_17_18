package pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161032.application;

import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.UserRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;

import javax.persistence.NoResultException;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Email;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Username;

public class SignUpController implements Controller {
    
    public boolean createUser(String username, String password, String email, String role, String name, String path) throws DataConcurrencyException, DataIntegrityViolationException {
        UserRepository repo = PersistenceContext.repositories().users();

        User user = new User(username, password, email, role, name, path);
        Email e= new Email(email);
        Username usn= new Username(username);
        try {
            User us = repo.findUserByEmail(e);
            User use = repo.findUserByUsername(usn);
            if (us == null && use == null) { 
                repo.save(user);
                return true; 
            }else{
                return false;
            }
            
        } catch (NoResultException ex) {  
             repo.save(user); // faço o save aqui porque se o utilizador de facto não existir lança a exceção de NoResulException
             return true; 
        }
        
    }
    
}
