package pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.domain;

import eapli.framework.domain.AggregateRoot;
import pt.isep.nsheets.shared.services.ReminderDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Represents a Reminder
 *
 * @author Rui Almeida<1160818>
 */
@Entity
public class Reminder implements AggregateRoot<Long>, Serializable {

    @Id
    @GeneratedValue
    private Long id;

    /**the name of the reminder*/
    @OneToOne
    private ReminderName name;

    /**the description of the reminder*/
    @OneToOne
    private Description description;

    /**the timestamp/duedate of the reminder*/
    private Date date;

    /**the owner of the reminder*/
    private String user;

    /**for notifications in the view*/
    private boolean isActive;

    /**
     * Constructor for ORM
     */
    protected Reminder() {}

    /**
     * Constructor for the reminder. It receives a name, a description and a date (timestamp) as a parameter.
     * @param name - the name of the reminder
     * @param description - the description of the reminder
     * @param date - the date of the reminder
     */
    public Reminder(ReminderName name, Description description, Date date, String user, boolean isActive) {
        if (name == null || description == null
                || date == null || user == null) throw new IllegalArgumentException();

        this.name = name;
        this.description = description;
        this.date = date;
        this.user = user;
        this.isActive = isActive;
    }

    @Override
    public int hashCode() { return Objects.hash(id, name, description, date, user, isActive); }

    /**
     * Compares the Reminder with another object
     * @param o - object to compare with
     * @return true if they match, false if not
     */
    @Override
    public boolean sameAs(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reminder reminder = (Reminder) o;
        return  name.toString().equals(reminder.name.toString()) &&
                description.toString().equals(reminder.description.toString()) &&
                user.equals(reminder.user);
    }

    @Override
    public boolean is(Long id) { return Long.compare(id, this.id) == 0; }

    @Override
    public Long id() { return this.id; }

    /**
     * Converts a reminder object to DTO
     * @return the DTO
     */
    public ReminderDTO toDTO() {
        String name = this.name.toString();
        String description = this.description.toString();
        String username = this.user;
        return new ReminderDTO(name, description, username, date, isActive);
    }

}
