package pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.domain;

import eapli.framework.domain.AggregateRoot;

import java.io.Serializable;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.domain.Agenda;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.persistence.AgendaRepository;

import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;

import pt.isep.nsheets.shared.services.EventDTO;

/**
 *
 * @author MFerreira
 */
@Entity
public class Event implements AggregateRoot<Long>, Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private Date date;

    private Date hour;

    Duration duration;

    @ManyToOne
    private Agenda calendar;

    public Event(String title, String description, Date date, Date hour, Duration duration, Agenda calendar) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.duration = duration;
        this.hour = hour;
        this.calendar = calendar;
    }

    protected Event() {
        // for ORM
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    public String title() {
        return this.title;
    }

    public String description() {
        return this.description;
    }

    public Date date() {
        return this.date;
    }

    public Duration duration() {
        return duration;
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
        final Event other = (Event) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Title: " + title + " | Description: " + description + " | Date=" + date + " | Duration:" + duration;
    }

    public static Event fromDTO(EventDTO dto) throws IllegalArgumentException {
        AgendaRepository repo = PersistenceContext.repositories().agendas();
        Agenda g = null;
        for (Agenda agenda : repo.findAll()) {
            if (agenda.getName().equals(dto.getAgendaDTO().getName())) {
                g = agenda;
                break;
            }
        }
        if (g == null) {
            g = new Agenda(dto.getAgendaDTO().getName(), dto.getAgendaDTO().getDescription(), dto.getAgendaDTO().getColor());
        }

        return new Event(dto.getTitle(), dto.getDescription(), dto.getDate(), dto.getHour(), new Duration(dto.getDuration()), g);
    }

    public EventDTO toDTO() {
        return new EventDTO(this.title, this.description, this.date, this.hour, this.duration.duration(), this.calendar.getName(), this.calendar.getDescription(), this.calendar.getColor());
    }

    public void editEvent(String newTitle, String newDescription, Date newDate, Date newHour, Long newDuration) {
        this.title = newTitle;
        this.description = newDescription;
        this.date = newDate;
        this.hour = newHour;
        this.duration = new Duration(newDuration);
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Event)) {
            return false;
        }
        final Event otherRating = (Event) other;
        if (this == otherRating) {
            return true;
        }
        if (this.title.equals(otherRating.title)) {
            return false;
        }
        if (!this.description.equals(otherRating.description)) {
            return false;
        }
        if (!this.calendar.equals(otherRating.calendar)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean is(Long id) {
        return Long.compare(this.id, id) == 0;
    }

    @Override
    public Long id() {
        return this.id;
    }

    public Agenda getCalendar() {
        return calendar;
    }

    public Date getHour() {
        return hour;
    }

}
