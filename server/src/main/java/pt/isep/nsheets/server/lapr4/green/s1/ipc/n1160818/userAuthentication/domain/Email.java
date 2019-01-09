package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain;

import eapli.framework.domain.ValueObject;
import eapli.util.Strings;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * A class used to represent an user's email
 *
 * @author Rui Almeida<1160818>
 */
@Embeddable
public class Email implements ValueObject, Serializable {


    /**
     * User's email
     */
    private String email;

    /**
     * Constructor for ORM
     */
    protected Email() {}

    /**
     * Creates an instance of email
     *
     * @param email - string with user email
     */
    public Email(String email) {
        if (Strings.isNullOrEmpty(email)) throw new IllegalArgumentException();

        //TODO Validate email - it should have an @, for example..
        this.email = email;
    }

    /**
     * Compares a email with another object
     *
     * @param o - other object
     * @return true if they're referencing the same object, false is the class of the objects are different or one of them is null. true if they have the same email
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return this.email.hashCode();
    }

    @Override
    public String toString() {
        return this.email;
    }
    
}
