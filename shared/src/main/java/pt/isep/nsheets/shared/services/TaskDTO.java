/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author André Oliveira <1040862@isep.ipp.pt>
 */
@SuppressWarnings("serial")
public class TaskDTO implements Serializable {

    private String name;
    private String description;
    private List<String> list;
    private String priority;
    private String completion;

    // USED WHEN CONVERTING FROM REPO
    public TaskDTO(String name, String description, List<String> list, String priority, String completion) {
        this.name = name;
        this.description = description;
        this.list = list;
        this.priority = priority;
        this.completion = completion;
    }

    // USED WHEN EDITING
    public TaskDTO(String name, String description, String priority, String completion) {
        this.name = name;
        this.description = description;
        this.list = new ArrayList<>();
        this.priority = priority;
        this.completion = completion;
    }

    // USED WHEN CREATING
    public TaskDTO(String name, String description, List<String> list, String priority) {
        this.name = name;
        this.description = description;
        this.list = list;
        this.priority = priority;
        this.completion = "0";
    }

    public TaskDTO() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getList() {
        return list;
    }

    public String getPriority() {
        return priority;
    }

    public String getCompletion() {
        return completion;
    }

    @Override
    public String toString() {
        return "TaskDTO{" + "name=" + name + ", description=" + description + ", list=" + list + ", priority=" + priority + ", completion=" + completion + '}';
    }
}
