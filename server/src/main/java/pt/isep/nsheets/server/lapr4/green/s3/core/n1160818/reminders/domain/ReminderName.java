package pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.domain;

import eapli.util.Strings;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Represents the name for the reminder
 *
 * @author Rui Almeida<1160818>
 */
@Embeddable
public class ReminderName implements Serializable {

    private String name;

    /**
     * Constructor for ORM
     */
    protected ReminderName() {}


    /**
     * Constructor for the reminder name
     * @param name - the name of the reminder
     */
    public ReminderName(String name) {
        if (Strings.isNullOrEmpty(name)) throw new IllegalArgumentException();

        //@Todo Validate name
        this.name = name;
    }

    /**
     * Compares a reminder name with another object
     * @param o - object to compare
     * @return - true if they're equal false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReminderName that = (ReminderName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {  return Objects.hash(name); }

    @Override
    public String toString() { return this.name; }
}
