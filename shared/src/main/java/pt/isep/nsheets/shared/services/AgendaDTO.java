/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import gwt.material.design.client.constants.Color;
import java.io.Serializable;

/**
 *
 * @author Joana Oliveira
 */
@SuppressWarnings("serial")
public class AgendaDTO implements Serializable {

    private String name;

    private String description;

    private String nameBefore;

    private Color color;

    public AgendaDTO() {

    }

    public AgendaDTO(String name, String description, Color color) {
        this.name = name;
        this.description = description;
        this.color = color;
    }

    public AgendaDTO(String name, String description, String nameBefore, Color color) {
        this.name = name;
        this.description = description;
        this.nameBefore = nameBefore;
        this.color = color;
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

    @Override
    public String toString() {
        return name + "," + description + "," + color;
    }

    public String getNameBefore() {
        return nameBefore;
    }

}
