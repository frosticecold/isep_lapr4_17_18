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
public class Invitation implements ValueObject, Serializable {
    
    /**
     * Invitations's message
     */
    private static final String MENSAGEM = "Bom dia! Pode me adicionar?";
   
    /**
     * Invitations destiny User's username
     */
    private Username name;
    
    /**
     * Invitations destiny User's email
     */
    private Email email;
    
    /**
     * Constructor for ORM
     */
    protected Invitation() {}
    
    /**
     * Creates an instance of email
     *
     * @param name - string with user name
     * @param email - string with user email
     */
    public Invitation(String name,String email) {
        if (Strings.isNullOrEmpty(email)) throw new IllegalArgumentException();
        if (Strings.isNullOrEmpty(name)) throw new IllegalArgumentException();

        
        this.email = new Email(email);
        this.name = new Username(name);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Invitation other = (Invitation) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Invitation:" + "Name=" + name + ", Email=" + email + ", Message=" + MENSAGEM + '\n';
    }
}
