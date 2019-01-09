package pt.isep.nsheets.shared.services;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Rui Almeida<1160818>
 */
public class ReminderDTO implements Serializable {

    private String name;
    private String description;
    private String username;
    private Date timestamp;
    private boolean active;

    public ReminderDTO(String name, String description, String username, Date date, boolean active) {
        this.name = name;
        this.description = description;
        this.username = username;
        this.timestamp = date;
        this.active = active;
    }

    protected ReminderDTO() {}

    public String getName() { return name; }

    public String getDescription() { return description; }

    public String getUsername() { return username; }

    public Date getDate() { return this.timestamp; }

    public boolean isActive() { return this.active; }

    public void setInactive() { active = false; }

    public void setActive() { active = true; }


}
