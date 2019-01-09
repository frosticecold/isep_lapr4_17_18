package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain;

import eapli.framework.domain.ValueObject;
import eapli.util.Strings;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class used to represent the user's username as a value object
 *
 * @author Rui Almeida<1160818>
 */
@Embeddable
public class Username implements ValueObject, Serializable {

    /**
     * User's username
     */
    private String username;

    /**
     * Constructor for ORM
     */
    protected Username() {}

    /**
     * Creates an instance of username
     *
     * @param username - string with user username
     */
    public Username(String username) {
        if (Strings.isNullOrEmpty(username)) throw new IllegalArgumentException();

        //TODO Validate username - how many characters it should have, should it support special characters?..etc
        this.username = username;
    }

    /**
     * Compares an username with another object
     *
     * @param o - other object
     * @return true if they're referencing the same object, false is the class of the objects are different or one of them is null. true if they have the same username
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Username username1 = (Username) o;
        return this.username.equals(username1.toString());
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }

    @Override
    public String toString() {
        return this.username;
    }
    
}
