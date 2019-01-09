/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import pt.isep.nsheets.shared.core.Value;

/**
 *
 * @author MFerreira
 */
public class TemporaryVariableDTO {

    public String name;
    public Value value;

    public TemporaryVariableDTO(String name, Value value) {
        this.name = name;
        this.value = value;
    }

    public String toString() {
        return this.name;
    }
}
