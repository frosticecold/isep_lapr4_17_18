package pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.domain;

import eapli.util.Strings;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Represents the description of the Reminder
 *
 * @author Rui Almeida<1160818>
 */
@Embeddable
public class Description implements Serializable {

    private String description;

    /**
     * Constructor for ORM
     */
    protected Description() {}

    /**
     * Constructor for the Reminder Description
     * @param description - the description of the reminder
     */
    public Description(String description) {
        if (Strings.isNullOrEmpty(description)) throw new IllegalArgumentException();

        //@Todo Validate description
        this.description = description;
    }

    /**
     * Compares a reminder description with another object
     * @param o - object to compare
     * @return - true if they're equal false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Description that = (Description) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() { return Objects.hash(description); }

    @Override
    public String toString() { return this.description; }
}
