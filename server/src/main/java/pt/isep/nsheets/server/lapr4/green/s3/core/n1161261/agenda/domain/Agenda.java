/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.domain;

import eapli.framework.domain.AggregateRoot;
import gwt.material.design.client.constants.Color;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import pt.isep.nsheets.shared.services.AgendaDTO;

/**
 *
 * @author Joana Oliveira
 */
@Entity
public class Agenda implements AggregateRoot<String>, Serializable {

    @Id
    private String name;
    private String description;
    private Color color;

    protected Agenda() {
        //for ORM
    }

    public Agenda(String name, String description, Color color) {
        this.name = name;
        this.description = description;
        this.color = color;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Agenda other = (Agenda) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.color != other.color) {
            return false;
        }
        return true;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Agenda)) {
            return false;
        }
        Agenda tmp = (Agenda) other;
        if (this == tmp) {
            return true;
        }
        if (!this.name.equals(tmp.name)) {
            return false;
        }
        if (!this.description.equals(tmp.description)) {
            return false;
        }
        if (!this.color.equals(tmp.color)) {
            return false;
        }

        return true;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Color getColor() {
        return color;
    }

    public AgendaDTO toDTO() {
        return new AgendaDTO(this.name, this.description, this.color);
    }

    public static Agenda fromDTO(AgendaDTO dto) {
        return new Agenda(dto.getName(), dto.getDescription(), dto.getColor());
    }

    @Override
    public String toString() {
        return name + "," + description + "," + color;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String id() {
        return this.name;
    }

    @Override
    public boolean is(String id) {
        return this.name.equals(id);
    }

}
