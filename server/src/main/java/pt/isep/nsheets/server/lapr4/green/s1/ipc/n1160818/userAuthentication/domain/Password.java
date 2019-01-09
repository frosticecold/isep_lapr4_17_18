package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain;

import eapli.framework.domain.ValueObject;
import eapli.util.Strings;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class used to represent an user's password
 *
 * @author Rui Almeida<1160818>
 */
@Embeddable
public class Password implements ValueObject, Serializable {

    /**
     * User's password
     */
    private String password;

    /**
     * Constructor for ORM
     */
    protected Password() {}

    /**
     * Creates an instance of password
     *
     * @param password - string with user password
     */
    public Password(String password) {
        if (Strings.isNullOrEmpty(password)) throw new IllegalArgumentException();

        //TODO Validate password - should it have an upper/lower case letter, atleast? can it have special characters...
        this.password = password;
    }

    /**
     * Compares a password with another object
     *
     * @param o - other object
     * @return true if they're referencing the same object, false is the class of the objects are different or one of them is null. true if they have the same password
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password1 = (Password) o;
        return Objects.equals(password, password1.password);
    }

    @Override
    public int hashCode() {
        return this.password.hashCode();
    }

    @Override
    public String toString() {
        return this.password;
    }
  
}
