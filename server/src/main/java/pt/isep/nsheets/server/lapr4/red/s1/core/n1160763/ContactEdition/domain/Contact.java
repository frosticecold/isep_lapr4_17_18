package pt.isep.nsheets.server.lapr4.red.s1.core.n1160763.ContactEdition.domain;

import eapli.framework.domain.ValueObject;
import eapli.util.Strings;
import java.io.Serializable;
import java.util.Objects;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Email;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Username;

import javax.persistence.Embeddable;

/**
 *
 * @author João Magalhães<1160763>
 */
@Embeddable
public class Contact implements ValueObject, Serializable {
    
    /**
     * User's username
     */
    private Username name;
    
    /**
     * User's email
     */
    private Email email;
    

    /**
     * Constructor for ORM
     */
    protected Contact() {}
    
    /**
     * Creates an instance of email
     *
     * @param name - string with user name
     * @param email - string with user email
     */
    public Contact(String name,String email) {
        if (Strings.isNullOrEmpty(email)) throw new IllegalArgumentException();
        if (Strings.isNullOrEmpty(name)) throw new IllegalArgumentException();

        this.email = new Email(email);
        this.name = new Username(name);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.email);
        return hash;
    }

    /**
     * Compares a email with another object
     *
     * @param obj - other object
     * @return true if they're referencing the same object, false is the class of the objects are different or one of them is null. true if they have the same email
     */
    @Override    
    public boolean equals(Object obj) {
        
        if (this == obj) {return true;}
        
        if (obj == null) {return false;}
        
        if (getClass() != obj.getClass()) {return false;}
        
        final Contact other = (Contact) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }

    @Override
    public String toString() {
        return "Contact:" + "Name=" + name + ", Email=" + email + '\n';
    }

    
}
