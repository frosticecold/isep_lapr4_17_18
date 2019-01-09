package pt.isep.nsheets.server.lapr4.red.s1.core.n1160763.ContactEdition.application;

import java.util.ArrayList;
import java.util.List;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1160763.ContactEdition.domain.Contact;

/**
 *
 * @author João Magalhães<1160763>
 */
public class EditUserContactsController {
    
    public List<Contact> listContacts(){
        List<Contact> list = new ArrayList<>();
        
        return list;
    }
    
    public void addContact(String Email){
      /*Se algum invitation tem este email:
       * remove invitation com este email
       * add contact a ambos os Users
       */
     
      /*else manda invitation para o email introduzido
       * cria invitation com este email e name
       */
    }
    
    public List<User> listBlockedUsers(){
        List<User> list = new ArrayList<>();
        
        return list;
    }
    
    public void blockUser(String Email){
      //getUserbyEmail
      //Adiciona aos Blocked
    }
}
