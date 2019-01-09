/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import gwt.material.design.client.constants.Color;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author MFerreira
 */
@SuppressWarnings("serial")
public class EventDTO implements Serializable {

    private String title;

    private String description;

    private Date date;

    private Date hour;

    private Long duration;

    private AgendaDTO agendaDTO;

    public EventDTO(String title, String description, Date date, Date hour, Long duration, AgendaDTO agendaDTO) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.duration = duration;
        this.agendaDTO = agendaDTO;
        this.hour = hour;
    }

    public EventDTO(String title, String description, Date date, Date hour, Long duration, String aName, String aDesc, Color color) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.hour = hour;
        this.duration = duration;
        this.agendaDTO = new AgendaDTO(aName, aDesc, color);
    }

    public EventDTO() {
        this.title = "";
        this.description = "";
        this.date = new Date(0, 0, 0, 0, 0, 0);
        this.duration = 0L;
        this.hour = getHour();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public Long getDuration() {
        return duration;
    }

    public AgendaDTO getAgendaDTO() {
        return agendaDTO;
    }

    @Override
    public String toString() {
        return title;
    }

    public Date getHour() {
        return hour;
    }

}
