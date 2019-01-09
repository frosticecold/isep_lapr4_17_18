/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.s2.core.n1040862.task.domain;

import eapli.framework.domain.ValueObject;
import eapli.util.Strings;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author André Oliveira <1040862@isep.ipp.pt>
 */
@Embeddable
public class TaskName implements ValueObject, Serializable {

    /**
     * User's taskName
     */
    private String taskName;

    /**
     * Constructor for ORM
     */
    protected TaskName() {}

    /**
     * Creates an instance of taskName
     *
     * @param taskName - string with user taskName
     */
    public TaskName(String taskName) {
        if (Strings.isNullOrEmpty(taskName)) throw new IllegalArgumentException();

        //TODO Validate taskName - how many characters it should have, should it support special characters?..etc
        this.taskName = taskName;
    }

    /**
     * Compares an taskName with another object
     *
     * @param o - other object
     * @return true if they're referencing the same object, false is the class of the objects are different or one of them is null. true if they have the same taskName
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskName username1 = (TaskName) o;
        return this.taskName.equals(username1.toString());
    }

    @Override
    public int hashCode() {
        return this.taskName.hashCode();
    }

    @Override
    public String toString() {
        return this.taskName;
    }
}
